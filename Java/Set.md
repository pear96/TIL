Q. Set의 중복 처리는 어떻게 이루어지는 걸까?

A. `equals()` 와 `hashCode()` 로 비교한다.

```java
/**
 * Set과 특정 Object를 비교합니다. 
 * 만약 특정 Object 또한 Set이며, 두 Set이 같은 크기이고, 
 * 특정 Set이 가진 모든 요소가 현재 Set에 포함되어있다면(반대로도) true를 반환합니다.
 * This definition ensures that the equals method works properly 
 * across different implementations of the set interface.
 *
 * @param o object to be compared for equality with this set
 * @return {@code true} if the specified object is equal to this set
 */
boolean equals(Object o);

/**
 * hashcode 값을 반환합니다. 
 * 이 set의 모든 요소들의 hashcode값을 합친 값이 set의 hashcode입니다. (null 요소는 0입니다) 
 * This ensures that {@code s1.equals(s2)} implies that
 * {@code s1.hashCode()==s2.hashCode()} for any two sets {@code s1}
 * and {@code s2}, as required by the general contract of
 * {@link Object#hashCode}.
 *
 * @return the hash code value for this set
 * @see Object#equals(Object)
 * @see Set#equals(Object)
 */
int hashCode();
```



우선 해시 테이블에서 해시 코드가 같은 값이 있는지 비교한다.

-> 있다면 equals로 실제 객체 값이 동등한지 비교한다. 동등하지 않다면 삽입한다.

-> 없다면 삽입한다.

> GPT 답변
>
> 
>
> Set은 내부적으로 해시 테이블(hash table)을 사용하여 요소를 저장합니다. 해시 테이블은 배열과 연결 리스트 혹은 트리로 구성됩니다. 각 요소는 해시 함수를 사용하여 특정 인덱스로 매핑되며, 동일한 인덱스를 가진 요소들은 연결 리스트나 트리 구조에서 저장됩니다.
>
> 해시 충돌이 발생한 경우에는 동일한 인덱스에 저장된 요소들을 순회하며 `equals()` 메서드를 사용하여 동등성을 비교합니다. 이때 객체의 `equals()` 메서드를 정확하게 구현하여 동등성을 올바르게 판단해야 합니다.
>
> 일반적으로 좋은 해시 함수를 사용하고, 충돌을 최소화하기 위해 충분한 크기의 해시 테이블을 사용하며, 해시 함수와 `equals()` 메서드를 상호 호환되게 구현하는 것이 중요합니다. 이를 통해 Set은 중복 요소를 효율적으로 관리할 수 있습니다.
>
> 따라서 Set 내부의 데이터는 해시 테이블을 기반으로 저장되며, 해시 충돌이 발생하는 경우에는 연결 리스트나 트리를 사용하여 중복된 요소들을 저장합니다. 이러한 방식으로 Set은 중복 데이터를 효과적으로 제거하고 고유한 요소들만을 유지합니다.





우선, HashSet은 내부적으로 `HashMap`을 가지고있다.

```java
// 빈 Set
public HashSet() {
    map = new HashMap<>();
}

// 초기값이 있는 Set
public HashSet(Collection<? extends E> c) {
    map = HashMap.newHashMap(Math.max(c.size(), 12));
    addAll(c);
}
```



삽입, 삭제, 조회는 HashMap으로 이루어진다.

```java
// 삽입
public boolean add(E e) {
    return map.put(e, PRESENT)==null;
}
// 조회
public boolean contains(Object o) {
    return map.containsKey(o);
}
// 삭제
public boolean remove(Object o) {
    return map.remove(o)==PRESENT;
}
```

