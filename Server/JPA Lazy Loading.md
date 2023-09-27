# JPA Lazy Loading

- 문제 상황

  ```
  2023-08-15 17:45:54.266 ERROR 29356 --- [nio-8080-exec-7] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is java.lang.NullPointerException: Cannot invoke "com.bigdata.wooahgong.place.entity.Place.getPlaceSeq()" because the return value of "com.bigdata.wooahgong.feed.entity.Feed.getPlace()" is null] with root cause
  ```

  feed 테이블에 place_seq가 있는데, feed의 place를 못 찾았다.

  ```java
  public List<GetMyFeedsRes> getMyLikeFeeds(String token, String nickname, Pageable pageable) {
          // 토큰으로 유저 찾기
          User user = userRepository.findByEmail(getEmailByToken(token)).orElseThrow(() ->
                  new CustomException(ErrorCode.NOT_OUR_USER));
          User Owner = userRepository.findByNickname(nickname).orElseThrow(() ->
                  new CustomException(ErrorCode.NOT_OUR_USER));
          Page<FeedLike> pages = feedLikeRepository.findByUserOrderByModifiedDateDesc(Owner, pageable);
          List<GetMyFeedsRes> getMyFeedsResList = new ArrayList<>();
          for (FeedLike feedLike : pages) {
              Feed feed = feedLike.getFeed();
              String image = null;
              if (feed.getFeedImages().size() != 0) {
                  image = imageService.getImage(feed.getFeedImages().get(0).getImageUrl());
              }
              getMyFeedsResList.add(GetMyFeedsRes.builder()
                      .feedSeq(feed.getFeedSeq())
                      .imageUrl(image)
                      .placeSeq(feed.getPlace().getPlaceSeq()) // 여기서 문제 발생!
                      .build());
          }
          return getMyFeedsResList;
      }
  ```

  Feed Entity 내용

  ```java
  package com.bigdata.wooahgong.feed.entity;
  
  import com.bigdata.wooahgong.comment.entity.Comment;
  import com.bigdata.wooahgong.common.util.BaseTimeEntity;
  import com.bigdata.wooahgong.place.entity.Place;
  import com.bigdata.wooahgong.user.entity.FeedLike;
  import com.bigdata.wooahgong.user.entity.User;
  import com.fasterxml.jackson.annotation.JsonBackReference;
  import com.fasterxml.jackson.annotation.JsonManagedReference;
  import lombok.Builder;
  import lombok.Getter;
  import lombok.NoArgsConstructor;
  import lombok.Setter;
  
  import javax.persistence.*;
  import java.util.ArrayList;
  import java.util.List;
  
  @Entity
  @Setter
  @Getter
  @NoArgsConstructor
  public class Feed extends BaseTimeEntity {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long feedSeq;
  
      @Column(nullable = false)
      private String content;
  
      @Column(nullable = false)
      private Double ratings;
  
      @Column(nullable = false)
      private String thumbnail;
  
      // 외래키 설정
      @ManyToOne
      @JoinColumn(name = "user_seq") // 외래키 매핑
      @JsonBackReference // 순환 참조 방어
      private User user;
  
      @ManyToOne
      @JoinColumn(name = "place_seq") // 외래키 매핑
      @JsonBackReference // 순환 참조 방어
      private Place place;
  
      // 중간 테이블 설정
      @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
      @JsonManagedReference
      private List<FeedLike> feedLikes = new ArrayList<>();
  
      @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
      @JsonManagedReference
      private List<FeedImage> feedImages = new ArrayList<>();
  
      @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
      @JsonManagedReference
      private List<Comment> comments = new ArrayList<>();
  
      @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
      @JsonManagedReference
      private List<FeedMood> feedMoods = new ArrayList<>();
  
      public void updateContent(String content) {
          this.content = content;
      }
  
      @Builder
      public Feed(String content, Double ratings, String thumbnail, User user, Place place) {
          this.content = content;
          this.ratings = ratings;
          this.thumbnail = thumbnail;
          this.user = user;
          this.place = place;
      }
  }
  
  
  ```

- 문제 원인 => JPA의 Lazy Loading 때문이다.

  - **Eager vs Lazy Loading**: JPA에서 연관 관계의 기본 로딩 전략은 `LAZY` 입니다. 이는 연관된 엔터티를 바로 로드하지 않고, 실제로 엔터티에 접근할 때만 데이터베이스에서 로드한다는 의미입니다. `feed.getPlace()`를 호출할 때 `Place` 엔터티를 로드하지 못한 경우 `NullPointerException`이 발생할 수 있습니다. 이를 해결하려면 로딩 전략을 `EAGER`로 변경하거나, 특정 쿼리를 실행할 때 `JOIN FETCH`를 사용하여 필요한 엔터티를 함께 로드할 수 있습니다.

- 해결 방법 => 사실 place Entity의 내용 중 다른 속성은 필요하지 않고, place_seq만 필요했기 때문에 굳이 Place를 동시에 올릴 필요 없이, place_seq만 접근할 수 있도록 하고 싶었다. 그래서 Feed Entity에 place_seq를 직접 추가했다.

  - 만약 `Place` 엔터티의 다른 정보는 필요 없고, 오직 `place_seq`만 필요하다면 `Place` 엔터티를 로드하는 대신 `place_seq` 값을 직접 매핑할 수 있습니다. 이렇게 하면 `Place` 엔터티에 대한 추가적인 데이터베이스 조회 없이 `place_seq` 값을 가져올 수 있습니다.

    이를 위해 `Feed` 엔터티에 직접 `placeSeq` 필드를 추가하고 `@JoinColumn`을 통해 해당 필드를 매핑하면 됩니다:

    ```java
    @Entity
    public class Feed extends BaseTimeEntity {
        // ... 기존의 필드들 ...
    
        @Column(name = "place_seq", insertable = false, updatable = false)
        private Long placeSeq;
    
        @ManyToOne
        @JoinColumn(name = "place_seq")
        @JsonBackReference // 순환 참조 방어
        private Place place;
    }
    ```

    위의 코드에서 주목해야 할 부분은 다음과 같습니다:

    1. `placeSeq` 필드는 `place_seq` 컬럼에 매핑되며, `insertable = false` 및 `updatable = false` 옵션을 사용하여 해당 필드가 삽입 또는 업데이트 작업에 포함되지 않도록 합니다.
    2. `Place` 엔터티에 대한 참조 (`place` 필드)는 그대로 유지되므로 필요한 경우에만 해당 엔터티를 로드할 수 있습니다.
    3. 만약 `Place` 엔터티를 전혀 사용하지 않으려면 `place` 필드와 관련 코드를 완전히 제거할 수도 있습니다.

    이렇게 하면 `feed.getPlaceSeq()`를 통해 직접 `place_seq` 값을 가져올 수 있습니다.

---

- 이렇게 되면 placeSeq가 Place와 내용이 달라질 수도 있을까?
- Place 객체를 아예 지워버리면, 외래키 설정은 어떻게 되는거지??









# Jenkins Agent

- 문제 상황

  front는 멀쩡하게 wooahgong_frontend 로 만들어지는데, back이랑 bigdata가 계속 wooahgong2_backend, wooahgong2_bigdata로 생성되고 무엇보다 새로 업데이트한 내용이 반영되지 않았음

- 문제 원인

  Jenkinsfile의 파이프라인 내에서 온갖 stage에서 agent any를 넣었기 때문에 매번 단계마다 다른 공간 / 실행자를 사용할 수 있는 것이었음. 그리고 기존에 왜 되었는지는 모르겠고, 왜 갑자기 어느 순간부터 바뀌었는지는 모르겠지만 쨌든 front와 달리 back이랑 bigdata가 계속 다른 agent를 사용한 것으로 보임.

  ```
  agent any 지시문을 사용하면 Jenkins는 파이프라인에서 이 지시문을 사용하는 모든 스테이지에 대해 실행자(또는 노드)를 선택하고, 각 스테이지마다 새로운 작업 공간(workspace)을 할당할 수 있습니다.
  
  파이프라인 내에서 여러 병렬 스테이지가 실행될 때, 각 스테이지는 동일한 노드에서 실행되지 않을 수 있습니다. 따라서 agent any를 사용하는 각 스테이지는 해당 노드의 새로운 작업 공간에서 실행될 수 있습니다. 이는 병렬 실행을 지원하기 위한 Jenkins의 방식입니다.
  
  원하는 동작을 달성하려면, 모든 스테이지가 동일한 작업 공간에서 실행되도록 지정해야 합니다. 이를 위해 파이프라인의 상단에서 agent any를 설정하고 병렬 스테이지에서는 agent none을 사용하여 해당 스테이지가 특정 작업 공간을 생성하지 않도록 합니다.
  ```

- 해결 방법

  최상단에 agent를 설정해주고, 이후의 agent는 전부 지워버림

  ```
  최상단의 agent any는 파이프라인 전체의 기본 실행자와 작업 공간을 설정합니다. 이후의 모든 스테이지는 기본으로 이 작업 공간을 사용하게 됩니다.
  
  만약 별도의 스테이지에서 agent를 재지정하지 않는다면, 해당 스테이지는 기본 작업 공간에서 실행됩니다. 따라서, 각 스테이지에서 agent none을 설정하면, 각 스테이지는 최상단에서 지정된 작업 공간에서 실행됩니다.
  
  요약하면, 최상단에서 agent any를 설정하고, 이후의 스테이지에서는 agent none을 사용하면, 모든 스테이지가 동일한 작업 공간에서 실행됩니다. 따라서, 모든 스테이지에서 agent none을 설정해도 무방합니다.
  
  agent 구문은 Jenkins Pipeline에서 필수적인 부분은 아닙니다. 그러나 agent 구문이 없으면 Jenkins는 해당 파이프라인 혹은 스테이지에 어떤 실행자에서 실행될지 모르게 됩니다.
  
  최상위 레벨의 agent를 생략하면, 파이프라인은 시작되지 않습니다. 파이프라인은 agent가 지정될 때까지 대기 상태에 머물게 됩니다.
  
  스테이지 레벨에서 agent를 생략하면, 해당 스테이지는 최상위 레벨에서 지정된 agent 설정을 사용합니다. 만약 최상위 레벨에도 agent 설정이 없다면, 그 스테이지는 시작되지 않습니다.
  
  따라서, 최상위 레벨 혹은 각 스테이지에서 agent 설정을 지정하지 않는다면, 파이프라인 혹은 스테이지는 실행되지 않을 수 있습니다.
  ```

  