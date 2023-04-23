# [ArrayList](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayList.html) VS [ArrayDeque](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayDeque.html) VS [LinkedList](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/LinkedList.html)

Java에서 `LinkedList`와 `ArrayDeque`는 모두 `Queue` 인터페이스를 구현하는 컬렉션 클래스입니다. 그러나 내부 구현 방식과 특징이 다릅니다.

- `ArrayList`
  - 인터페이스 : `List`
  - 내부적으로 배열(Array)을 이용하여 데이터를 저장합니다.
  - 인덱스를 통한 랜덤 액세스(random access)가 빠르며, 데이터의 추가와 삭제가 느립니다.
  - 추가와 삭제가 느리지만, 크기가 고정되어 있지 않기 때문에 유연한 크기 조정이 가능합니다.
  - 일반적으로 데이터의 검색이나 랜덤 액세스가 많을 때 사용됩니다.
- `ArrayDeque`
  - 인터페이스 : `Queue`
  - 내부 구현 방식: 동적 배열(Dynamic Array)
  - 요소 추가/삭제 시간 복잡도: 앞/뒤로 추가/삭제할 때 O(1), 중간에 추가/삭제할 때 O(n)
  - 임의 접근 시간 복잡도: O(1)
  - 요소 순회 방식: 순차적으로 요소를 참조하는 방식으로 순회
  - 사용 예시: 스택, 큐, 덱 등을 구현할 때

- `LinkedList`
  - 인터페이스 : `Queue` , `List
  - 내부 구현 방식: 이중 연결 리스트(Double Linked List)
  - 요소 추가/삭제 시간 복잡도: O(1)
  - 임의 접근(인덱스를 사용한 요소 검색 등) 시간 복잡도: O(n)
  - 요소 순회 방식: 순방향 또는 역방향으로 요소를 참조하는 방식으로 순회
  - 사용 예시: 큐 또는 스택 구현, 중간 삽입/삭제가 많은 경우

따라서, `LinkedList`는 요소의 추가/삭제가 많은 경우에 유용하며, `ArrayDeque`는 요소의 추가/삭제가 많은 경우와 스택, 큐, 덱 등을 구현할 때 유용합니다. 

또한, `LinkedList`는 중간에 요소를 삽입/삭제하는 경우에도 `ArrayDeque`보다 더 나은 성능을 보이지만, 임의 접근이 불가능하므로 인덱스를 사용한 요소 검색 등의 작업에서는 성능이 떨어집니다.



[요약]

- ArrayList : 데이터의 검색이나 랜덤 액세스가 많은 경우
- LinkedList : 데이터의 추가와 삭제가 빈번하게 발생하는 경우
- ArrayDeque : 큐나 스택의 구현체