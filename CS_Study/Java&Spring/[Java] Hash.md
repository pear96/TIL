- 완전한 해시 함수를 만들기란 불가능하다. Java의 `hashCode()`의 반환타입은 `int`인데, 2^32개로 모든 객체를 분리할 순 없으며, 2^32 크기의 배열을 만들어서 관리해야한다.
- 해시 충돌을 어떻게 낮추느냐가 관건이다.



# 해시 충돌

> 대표적으로 Open Addressing과 Seperate Chaining이 있다.
>
> OpenAddressing은 빈자리가 나올 때 까지, Seperate Chaining은 같이 쓰는 것이다.

## OpenAddressing

- Linear(+1) Probing, Double(*2) Probing, Quadratic(^2) Probing 종류로 나눠질 수 있다.
  - Linear에 비해 Double, Quadratic이 Cluster가 생길 확률이 적다.
- 최악의 경우 O(N)이다. 
- 연속된 공간에 데이터를 저장하기 때문에 Seperate Chaining에 비해 캐시 효율이 좋다.
  - 그러나 데이터의 크기가 커질수록 캐시 적중률이 낮아져 효율이 떨어진다.

## Seperate Chaining

- Java가 선택한 방법(Open Addressing은 데이터 삭제를 효율적으로 하기 어렵다.)
- JDK 8부터는 `get()` 호출에 대한 기대값이 log(N/M) 이 되었다. (M은 해시 버킷 크기)
  - 데이터의 개수가 많아지면, Linked List 대신 Tree를 사용하기 때문이다!
  - 하나의 해시 버킷에 8개의 키-값 쌍이 모이면 Red Black Tree로 변환 (Tree Map과 거의 같다고 한다..)
  - 6개로 낮아지면 다시 LinkedList
  - 7개로 안하는 이유는 하나 넣고 뺀다고 계속 바뀌는걸 막기 위해
- Red Black 트리 순회시 대소 판단은 **해시 함수 값**을 기준으로 한다. 그러면 Total Ordering에 문제가 생기는데 이를 `tieBreakOrder()`로 해결한다 (?)

- `tieBreakOrder()` : 두 개의 키의 해시값이 같아도 동등하지 않을 수 있다. 임의로 대소 관계를 지정.
- 

### load Factor

0.75

- 해시 버킷 크기를 두배로 확장하는 임계점은 현재의 데이터 개수가 'loadFactor * 현재 해시 버킷 개수'에 이를 때이다.
- 임계점에 이르면 항상 해시 버킷 크기를 2배로 확장한다.

### 보조 해시 함수

- 해시 버킷 크기를 항상 2배로 키우기 때문에 해시값을 x.hashCode() % M은 2^a(=M)개만 사용하게 된다. 이 때문에 보조 해시 함수가 필요하다
- JDK8 부터 매우 단순해졌는데, 우선 해시 충돌이 많이 발생하면 자료구조를 바꾸기 때문이다.(링리 -> 트리) 또한 최근의 해시 함수는 균등 분포가 잘 되게 만들어지는 경향이 많아 보조 해시 함수의 효과가 크지 않기 땜