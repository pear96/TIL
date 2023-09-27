# Redis



- redis 설치 시 비밀번호 설정 방법

  1. **Redis 설정 파일 열기**: Redis 설정 파일은 대부분 `/etc/redis/redis.conf` 위치에 있습니다.

     ```
     bashCopy code
     sudo nano /etc/redis/redis.conf
     ```

  2. **비밀번호 설정**: 파일 내에서 `requirepass`를 찾아 해당 라인의 주석을 해제하고 원하는 비밀번호로 설정합니다. 예를 들면:

     ```
     Copy code
     requirepass yourpasswordhere
     ```

  3. **변경사항 저장 후 Redis 서버 재시작**:

     ```
     bashCopy code
     sudo systemctl restart redis
     ```

  4. 이제 Redis에 접속할 때마다 비밀번호를 사용해야 합니다. 예를 들어, Redis CLI를 사용하여 서버에 접속하려면:

     ```
     bashCopy code
     redis-cli
     ```

     그 다음 `auth` 명령어로 비밀번호를 입력합니다.

     ```
     Copy code
     auth yourpasswordhere
     ```

  5. Spring Boot에서 Configuration 하는 법

     ```java
     import org.springframework.data.redis.connection.RedisPassword;
     import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
     import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
     import org.springframework.context.annotation.Bean;
     import org.springframework.context.annotation.Configuration;
     
     @Configuration
     public class RedisConfig {
     
         @Bean
         public JedisConnectionFactory redisConnectionFactory() {
             RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
             
             config.setHostName("localhost");
             config.setPort(6379);
             config.setPassword(RedisPassword.of("yourpasswordhere"));  // 비밀번호 설정
             
             return new JedisConnectionFactory(config);
         }
     }
     
     ```

     

- redis에서 조회하는 법

  - hash value 조회 : `hvals 키이름`

  - GEO 조회 : 

    - `ZCARD 키이름` : Redis의 Sorted Set(ZSET)의 개수 반환

    - `ZRANGE 키이름 시작인덱스 마지막 인덱스` : 키이름을 가진 모든 내용을 조회할 수 있음

      - ZSET의 내용을 조회하려면 `ZRANGE` 명령어를 사용할 수 있습니다. 

        예를 들어, `PlacePos`라는 ZSET의 모든 내용을 조회하려면 아래와 같은 명령어를 사용합니다:

        ```bash
        ZRANGE PlacePos 0 -1
        ```

        여기서 `0`은 시작 인덱스, `-1`은 마지막 인덱스를 나타냅니다. 따라서 위의 명령어는 ZSET의 처음부터 끝까지의 모든 값을 반환합니다.

        만약 위치 정보와 함께 점수(score, 여기서는 실제로 위도/경도를 나타내는 값)도 같이 조회하려면, `WITHSCORES` 옵션을 추가로 사용할 수 있습니다:

        ```bash
        ZRANGE PlacePos 0 -1 WITHSCORES
        ```

        이 명령어를 실행하면 ZSET에 저장된 값(value)과 그에 대응하는 점수(score)가 함께 반환됩니다.





---

노션에 적고싶은데 메모리 터질까봐 크롬을 못 킴



# 우분투

## .zip 압축 한글 해제

Ubuntu에서는 `zip` 파일 포맷으로 압축된 파일들을 쉽게 압축 해제할 수 있습니다. `unzip`이라는 도구를 사용하면 됩니다. 만약 이 도구가 설치되어 있지 않다면, 먼저 설치해야 합니다.

1. **unzip 도구 설치**:
   ```bash
   sudo apt update
   sudo apt install unzip
   ```

2. **zip 파일 압축 해제**:
   ```bash
   unzip filename.zip
   ```

이 명령어를 사용하면 `filename.zip` 내의 모든 파일과 디렉토리가 현재 디렉토리에 압축 해제됩니다. 

다른 옵션과 함께 `unzip`을 사용하면, 특정 디렉토리에 압축 해제하거나, 압축된 파일 내부의 목록을 보는 등의 작업도 가능합니다. `man unzip` 명령어를 통해 `unzip` 도구의 매뉴얼을 확인할 수 있습니다.

Ubuntu에서 ZIP 압축을 해제할 때 한글 파일 이름이 깨지는 경우, 이는 대체로 ZIP 파일의 인코딩 문제와 관련이 있습니다. ZIP 파일의 인코딩이 CP949(또는 EUC-KR)로 되어 있을 가능성이 높습니다. 이러한 인코딩 문제를 해결하기 위해 `unar`라는 도구를 사용할 수 있습니다.

다음은 Ubuntu에서 `unar`를 사용하여 ZIP 파일의 한글 이름이 깨지지 않도록 압축을 해제하는 방법입니다:

1. `unar` 패키지 설치:

   ```bash
   sudo apt-get update
   sudo apt-get install unar
   ```

2. ZIP 파일 압축 해제:

   ```bash
   unar [압축파일.zip]
   ```

   예를 들어 `example.zip` 파일을 압축 해제하려면:

   ```bash
   unar example.zip
   ```

이렇게 하면 `unar`가 ZIP 파일 내의 한글 파일 이름을 올바르게 처리하고, 해당 파일 이름이 깨지지 않도록 압축을 해제합니다.



## tar 압축 해제

`tar` 명령어는 여러 파일 및 디렉터리를 하나의 아카이브 파일로 묶거나, 그 아카이브 파일을 풀 때 사용됩니다. `tar` 파일의 압축 해제 방법은 압축 방식(예: gzip, bzip2)에 따라 약간 다를 수 있습니다.

1. **.tar 파일 압축 해제**
   
   ```bash
   tar -xvf filename.tar
   ```

2. **.tar.gz 파일 (또는 .tgz 파일) 압축 해제**
   
   Gzip 압축을 사용한 경우입니다.
   
   ```bash
   tar -xzvf filename.tar.gz
   ```

3. **.tar.bz2 파일 압축 해제**
   
   Bzip2 압축을 사용한 경우입니다.
   
   ```bash
   tar -xjvf filename.tar.bz2
   ```

여기서 옵션들의 의미는 다음과 같습니다:

- `-x`: 압축 해제
- `-v`: 상세한 출력(verbose)
- `-f`: 파일 이름 지정

압축 해제 후 해당 디렉터리나 파일들이 현재 위치에 생성됩니다.





# 폴더 옮기기 + 대량 파일 옮기기

```bash
mv 원본_폴더/* 대상_폴더/
```

- Argument list too long 에러

  "Argument list too long" 에러는 한 번에 너무 많은 파일을 처리하려고 할 때 발생하는 문제입니다. 이러한 경우에는 명령어가 시스템의 제한을 초과하게 되어 발생하는 것입니다. 이 문제를 해결하려면 몇 가지 방법이 있습니다:

  1. **find와 xargs를 사용하기**: `find` 명령어로 파일 목록을 가져와서 `xargs` 명령어를 사용하여 파일을 이동하거나 복사할 수 있습니다. 예를 들어:

     ```
     find . -maxdepth 1 -type f -exec mv {} ../ \;
     ```

     위 명령어는 현재 폴더의 모든 파일을 상위 폴더로 이동시킵니다.

  2. **rsync 사용하기**: `rsync` 명령어는 파일 복사와 동기화에 사용되며, 큰 파일 집합을 다루는 데 효과적입니다.

     ```
     rsync -av --remove-source-files . ../
     ```

     위 명령어는 현재 폴더의 파일을 상위 폴더로 복사한 후, 복사한 파일을 삭제합니다.

  3. **반복문 사용하기**: 반복문을 사용하여 파일을 하나씩 이동하거나 복사할 수 있습니다.

     ```
     for file in *; do mv "$file" ../; done
     ```

  위의 방법 중 하나를 선택하여 "Argument list too long" 에러를 피할 수 있습니다. 선택한 방법에 따라 명령어를 조정해야 할 수도 있습니다.



## 한글 깨짐

우분투 서버에서 한국어가 깨지는 문제를 해결하려면 다음과 같은 단계를 따를 수 있습니다:

1. **로케일 설정 확인**:
   로케일은 시스템의 지역 및 언어 설정을 나타내는데, 한국어를 제대로 표시하려면 해당 로케일이 설정되어 있어야 합니다. 다음 명령어로 현재 로케일 설정을 확인할 수 있습니다:

   ```
   locale
   ```

   로케일이 올바른지 확인하고, 한국어 로케일(`ko_KR.UTF-8`)이 설치되어 있는지 확인합니다.

2. **로케일 추가 설정**:
   한국어 로케일이 설치되어 있지 않은 경우에는 다음 명령어로 추가 설정을 할 수 있습니다:

   ```
   sudo locale-gen ko_KR.UTF-8
   sudo dpkg-reconfigure locales
   ```

   위 명령어를 실행하여 한국어 로케일을 추가하고 설정합니다.

3. **터미널 폰트 확인 및 변경**:
   한국어가 제대로 보이도록 폰트 설정을 확인하고 변경할 수 있습니다. 터미널에서 한국어 폰트를 사용하도록 설정합니다.

4. **SSH 클라이언트 설정 (원격 접속 시)**:
   SSH 클라이언트에서 한국어를 올바르게 표시하려면 다음 명령어를 사용하여 로케일 환경 변수를 설정합니다:

   ```
   export LANG=ko_KR.UTF-8
   ```

   또는 SSH 설정 파일(`/etc/ssh/ssh_config`)에 다음 줄을 추가하여 한국어 로케일을 설정합니다:

   ```
   SendEnv LANG LC_*
   ```

위의 단계를 따라 한국어가 제대로 표시되도록 설정할 수 있습니다. 서버에서 한국어를 사용하려면 로케일 및 폰트 설정을 올바르게 구성하는 것이 중요합니다.







# Spatial Index

## Index 설정

Spring Boot에서 JPA를 사용해 엔터티에 인덱스를 적용하려면 `@Table` 어노테이션의 `indexes` 속성을 사용할 수 있습니다. 그러나, JPA는 기본적으로 공간 데이터 타입에 대한 지원이 제한적입니다. 따라서, 공간 데이터 타입에 인덱스를 적용하려면 몇 가지 추가 작업이 필요합니다.

1. 의존성 추가: `pom.xml` 파일에 다음 의존성을 추가합니다.

```xml
<dependency>
    <groupId>com.vividsolutions</groupId>
    <artifactId>jts</artifactId>
    <version>1.13</version>
</dependency>
```

2. `hibernate-spatial` 추가: `pom.xml` 파일에 다음 의존성을 추가합니다.

```xml
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-spatial</artifactId>
    <version>${hibernate.version}</version>
</dependency>
```

3. `Point` 타입의 인덱스 생성: `Point` 타입의 컬럼에 인덱스를 생성하려면, 다음과 같이 `@Column` 어노테이션에 `columnDefinition` 속성을 추가합니다.

```java
@Column(columnDefinition = "Point", nullable = false)
@Index(name = "idx_location")
private Point location;
```

여기서 `idx_location`은 인덱스의 이름입니다.

그러나, `@Index` 어노테이션은 JPA 2.1부터 지원됩니다. 따라서, JPA 2.1 이상의 버전을 사용하고 있다면 위의 방법을 사용할 수 있습니다. 그렇지 않다면, 데이터베이스의 네이티브 쿼리를 사용해 인덱스를 생성해야 합니다.

또한, MySQL의 경우 `SPATIAL INDEX`를 생성해야 합니다. 이는 `@Index` 어노테이션으로는 생성할 수 없기 때문에, 다음과 같이 `@Table` 어노테이션의 `indexes` 속성을 사용해 `INDEX`를 생성하고, 별도로 데이터베이스의 네이티브 쿼리를 사용해 `SPATIAL INDEX`를 생성해야 합니다.

```java
@Entity
@Table(indexes = {@Index(name = "idx_location", columnList = "location")})
```

그런 다음, 데이터베이스의 네이티브 쿼리를 사용해 `SPATIAL INDEX`를 생성합니다.

```sql
CREATE SPATIAL INDEX idx_location ON Place (location);
```

이렇게하면, `Place` 엔터티의 `location` 컬럼에 인덱스가 적용됩니다.





## SRID

SRID는 "Spatial Reference ID"의 약자로, 공간 데이터의 좌표계를 식별하는 데 사용되는 고유한 식별자입니다. 예를 들어, SRID 4326은 WGS 84 좌표계를 나타냅니다. 이는 대부분의 GPS 데이터에 사용되는 좌표계입니다.

MySQL에서 `POINT` 컬럼에 SRID를 설정하려면 다음과 같이 `CREATE TABLE` 또는 `ALTER TABLE` 문에서 `SRID` 속성을 추가해야 합니다.

```sql
CREATE TABLE Place (
    ...
    location POINT SRID 4326 NOT NULL,
    ...
);
```

또는

```sql
ALTER TABLE Place
MODIFY location POINT SRID 4326 NOT NULL;
```

이렇게하면, `location` 컬럼에 SRID가 설정되고, 쿼리 최적화기는 `location` 컬럼에 대한 공간 인덱스를 사용할 수 있습니다.

Spring Boot와 JPA를 사용하는 경우, `@Column` 어노테이션의 `columnDefinition` 속성을 사용하여 SRID를 설정할 수 있습니다.

```java
@Column(columnDefinition = "POINT SRID 4326", nullable = false)
private Point location;
```

이렇게하면, JPA가 데이터베이스 스키마를 생성하거나 업데이트할 때 `location` 컬럼에 SRID를 설정합니다.

그러나, JPA는 `POINT` 컬럼에 SRID를 설정하는 것 외에도 공간 데이터를 사용하는 데 필요한 다른 작업을 자동으로 수행하지 않습니다. 따라서, `POINT` 컬럼에 인덱스를 생성하거나 `POINT` 컬럼을 사용하는 쿼리를 작성하려면 여전히 데이터베이스의 네이티브 쿼리를 사용해야 합니다.

SRID 4326은 WGS 84 (World Geodetic System 1984) 좌표계를 나타냅니다. 이 좌표계는 전 세계의 위성, 항공, 해상 등 다양한 분야에서 널리 사용되는 표준 좌표계입니다. 대부분의 GPS 데이터는 WGS 84 좌표계를 사용하므로, GPS 데이터를 사용하거나 전 세계적으로 표준화된 좌표계를 사용하려면 SRID 4326을 사용하는 것이 좋습니다.

그러나, 특정 국가 또는 지역에서 사용하는 고유한 좌표계가 있는 경우, 그 좌표계의 SRID를 사용해야 합니다. 예를 들어, 미국에서는 NAD 83 (North American Datum 1983) 좌표계를 사용하므로, 미국의 특정 지역에 대한 데이터를 저장하려면 NAD 83의 SRID를 사용해야 합니다.

따라서, 사용하는 데이터의 좌표계와 어플리케이션의 요구사항에 따라 적절한 SRID를 선택해야 합니다. 일반적으로, 특별한 이유가 없다면 SRID 4326을 사용하는 것이 좋습니다.



> 그렇다면 기존에는 ST_DIstance_Sphere 함수로 사용자의 위치(위도, 경도)와 반경(radius)를 기준으로 해당 범위 내에 있는 장소들을 반환하도록 했는데, 이렇게 하면 인덱스의 혜택을 딱히 보지 못하는건가요?

네, 그렇습니다. `ST_Distance_Sphere` 함수는 거리 계산을 위해 스피어(sphere)를 사용하므로, 이 함수는 MBR(Minimum Bounding Rectangle) 기반의 SPATIAL 인덱스를 사용하지 않습니다. 따라서, `ST_Distance_Sphere` 함수를 사용하는 쿼리는 SPATIAL 인덱스의 이점을 받지 못합니다.

SPATIAL 인덱스는 주로 MBR 기반의 공간 쿼리에 유용합니다. 예를 들어, `MBRContains`, `MBROverlaps`, `MBRIntersects` 등의 함수를 사용하는 쿼리는 SPATIAL 인덱스의 이점을 받을 수 있습니다.

만약 사용자의 위치와 반경을 기준으로 해당 범위 내에 있는 장소들을 조회하려면, `MBRContains` 함수를 사용하여 해당 범위를 포함하는 MBR을 만들고, 이 MBR과 겹치는 위치를 조회하는 방법을 사용할 수 있습니다. 예를 들어, 다음과 같이 쿼리를 작성할 수 있습니다.

```sql
SELECT * 
FROM Place 
WHERE MBRContains(ST_GeomFromText('POLYGON((minX minY, maxX minY, maxX maxY, minX maxY, minX minY))'), location);
```

이 쿼리에서 `minX`, `minY`, `maxX`, `maxY`는 사용자의 위치와 반경을 기준으로 계산된 MBR의 좌표입니다. `location`은 `Place` 테이블의 `location` 컬럼입니다.

이 방법을 사용하면, SPATIAL 인덱스의 이점을 받을 수 있습니다. 그러나, 이 방법은 정확한 거리를 계산하지 않습니다. 따라서, 이 쿼리를 사용하여 해당 범위에 겹치는 위치를 먼저 조회한 후, `ST_Distance_Sphere` 함수를 사용하여 정확한 거리를 계산하고, 원하는 범위 내에 있는 장소들을 필터링하는 방법을 사용할 수 있습니다.



## MBR 계산하기

사용자의 위치(위도, 경도)와 반경(radius)를 기준으로 상,하,좌,우로 떨어진 좌표를 계산하려면, 다음과 같은 공식을 사용할 수 있습니다.

1. 위도 1도는 약 111킬로미터입니다. 따라서, 위도 1도는 약 111,000미터입니다.
2. 경도 1도는 약 111킬로미터 * 코사인(위도)입니다. 따라서, 경도 1도는 약 111,000 * 코사인(위도)미터입니다.

이를 바탕으로, 상,하,좌,우로 떨어진 좌표를 계산하려면 다음과 같이 할 수 있습니다.

1. 상: 위도 + (radius / 111,000)
2. 하: 위도 - (radius / 111,000)
3. 좌: 경도 - (radius / (111,000 * 코사인(위도)))
4. 우: 경도 + (radius / (111,000 * 코사인(위도)))

예를 들어, 사용자의 위치가 (37.5665, 126.9780)이고, 반경이 1킬로미터인 경우, 상,하,좌,우로 떨어진 좌표는 다음과 같이 계산할 수 있습니다.

1. 상: 37.5665 + (1,000 / 111,000) = 37.5755
2. 하: 37.5665 - (1,000 / 111,000) = 37.5575
3. 좌: 126.9780 - (1,000 / (111,000 * 코사인(37.5665))) = 126.9661
4. 우: 126.9780 + (1,000 / (111,000 * 코사인(37.5665))) = 126.9899

따라서, 상,하,좌,우로 1킬로미터 떨어진 좌표는 약 (37.5755, 126.9661), (37.5575, 126.9899)입니다.

이 좌표를 사용하여 `MBRContains` 함수를 사용하는 쿼리를 작성할 수 있습니다.