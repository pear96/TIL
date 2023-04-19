# [Stream](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/package-summary.html)

Java의 `Stream`은 자바 8에서 추가된 새로운 기능 중 하나로, 컬렉션, 배열, I/O 등의 데이터 소스를 다루는 데 사용됩니다. Stream은 요소들의 연속적인 흐름이라는 의미에서 이름이 지어졌습니다.

`Stream`은 데이터 소스의 요소들을 필터링, 매핑, 정렬, 그룹화 등 다양한 연산을 수행하여 처리할 수 있습니다. 이를 통해 코드의 가독성과 유지보수성이 향상되고, 코드 작성량도 줄일 수 있습니다.

Stream API는 중간 연산과 최종 연산으로 구분됩니다. 중간 연산은 데이터 소스의 요소를 변환하거나 필터링하는 등의 연산을 수행하고, 최종 연산은 최종 결과를 도출하는 연산을 수행합니다. 중간 연산은 연속적으로 체인 형태로 호출할 수 있으며, 최종 연산이 호출되기 전까지 실제로 수행되지 않습니다. 이를 "지연 연산"이라고 합니다.

Stream API는 병렬 처리를 지원하기 때문에 대용량 데이터의 처리도 빠르게 처리할 수 있습니다.

Stream API의 예시로는 다음과 같은 것들이 있습니다.

- `filter(Predicate<T> predicate)`: 주어진 조건식에 해당하는 요소를 필터링하여 새로운 Stream을 반환합니다.
- `map(Function<T, R> mapper)`: 각 요소를 주어진 함수에 따라 변환하여 새로운 Stream을 반환합니다.
- `sorted()`: 요소를 기본 정렬 기준으로 정렬하여 새로운 Stream을 반환합니다.
- `collect(Collector<T, A, R> collector)`: 최종 결과를 수집하여 반환합니다.

Java 8 이후로는 Stream API가 널리 사용되고 있으며, 자바 9부터는 Stream API가 개선되어 추가 기능이 제공되었습니다.



# `List<Integer>` -> `int[]`

``` java
int[] example1 = list.stream().mapToInt(i->i).toArray();
// OR
int[] example2 = list.stream().mapToInt(Integer::intValue).toArray();
```

간단한 `Stream#toArray`는 `Object[]` 배열을 반환하므로 우리가 원하는 것이 아닙니다. 또한 `A[]`를 반환하는 `Stream#toArray(IntFunction<A[]> generator)`도 원하는 바가 아닙니다. 왜냐하면 제네릭 타입 A는 기본 타입 `int`를 표현할 수 없기 때문입니다.

따라서 참조 타입인 `Integer` 대신 기본 타입 `int`를 처리하기 위해 설계된 스트림이 있으면 좋겠습니다. 왜냐하면 그 `toArray` 메서드가 `int[]` 배열을 반환할 가능성이 높기 때문입니다(`Object[]` 또는 박싱된 `Integer[]`를 반환하는 것은 int에는 부자연스럽습니다). 다행히도 Java 8에는 그러한 스트림이 있으며, 그것은 `IntStream`입니다.

이제 우리가 해결해야 할 일은 `Stream<Integer>` (list.stream()에서 반환되는 값)를 `IntStream`으로 어떻게 변환할지 알아내는 것입니다.

Stream 문서에서 `IntStream`을 반환하는 메서드를 빠르게 찾아보면, 우리의 솔루션은 `mapToInt(ToIntFunction<? super T> mapper)` 메서드에 이르게 됩니다. 우리는 `Integer`에서 `int`로의 매핑을 제공해야 합니다.

`ToIntFunction`은 **함수형 인터페이스**이므로 람다 또는 메서드 참조를 통해 인스턴스를 제공할 수 있습니다.

어쨌든 `Integer`를 `int`로 변환하기 위해 `Integer#intValue`를 사용할 수 있으므로 `mapToInt` 안에서 다음과 같이 작성할 수 있습니다:

`mapToInt( (Integer i) -> i.intValue() )`
(또는 일부는 다음을 선호할 수 있습니다: `mapToInt(Integer::intValue)`.)

그러나 비슷한 코드는 언박싱을 사용하여 생성될 수 있으며, 컴파일러는 이 람다의 결과가 `int` 타입이어야 한다는 것을 알고 있습니다(`mapToInt`에서 사용되는 람다는 `ToIntFunction` 인터페이스의 구현으로서, `int applyAsInt(T value)` 타입의 메서드 본문을 기대하며 `int`를 반환해야 합니다).

따라서 간단히 다음과 같이 작성할 수 있습니다:

`mapToInt((Integer i)->i)`

또한 `List<Integer>#stream()`이 `Stream<Integer>`를 반환하므로 컴파일러가 (Integer i)의 `Integer` 타입을 추론할 수 있으므로 생략할 수 있습니다. 이로 인해 다음과 같이 작성할 수 있습니다.

`mapToInt(i -> i)`









