# [Stream](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/package-summary.html)

Java의 `Stream`은 자바 8에서 추가된 새로운 기능 중 하나로, **컬렉션, 배열, I/O 등의 데이터 소스를 다루는 데 사용**됩니다. Stream은 요소들의 연속적인 흐름이라는 의미에서 이름이 지어졌습니다.

`Stream`은 데이터 소스의 요소들을 **필터링, 매핑, 정렬, 그룹화** 등 다양한 연산을 수행하여 처리할 수 있습니다. 

이를 통해 **코드의 가독성과 유지보수성이 향상**되고, 코드 작성량도 줄일 수 있습니다. Stream API는 **병렬 처리**를 지원하기 때문에 대용량 데이터의 처리도 빠르게 처리할 수 있습니다.

Stream API는 **중간 연산**과 **최종 연산**으로 구분됩니다. 

- 중간 연산은 데이터 소스의 요소를 변환하거나 필터링하는 등의 연산을 수행하고, 중간 연산은 연속적으로 체인 형태로 호출할 수 있으며, 최종 연산이 호출되기 전까지 실제로 수행되지 않습니다. 이를 **"지연 연산"**이라고 합니다.
- 최종 연산은 최종 결과를 도출하는 연산을 수행합니다. 



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

- 단순히 `toArray()`를 사용하면 `Object[]` 배열을 반환하여 원하는 값을 얻을 수 없다. `Stream#toArray(IntFunction<A[]> generator)`도 `A[]`를 반환하여 쓸 수 없다. 왜냐하면 제네릭 타입 `A`는 기본 타입 `int`를 표현할 수 없기 때문이다.
- 따라서 참조(Reference) 타입인 `Integer` 대신 기본(Primitive) 타입 `int`를 처리하기 위해 설계된 스트림을 찾아야한다. 다행히도 Java 8에는 그러한 스트림이 있으며, 그것은 `IntStream`입니다.
- 이제 우리가 해결해야 할 일은 `Stream<Integer>` (`list.stream()`에서 반환되는 값)를 `IntStream`으로 어떻게 변환할지 알아내는 것입니다. (Reference -> Primitive)



#### `mapToInt()` 

- Stream 문서에서 `IntStream`을 반환하는 메서드를 빠르게 찾아보면, 우리의 솔루션은 `mapToInt(ToIntFunction<? super T> mapper)` 메서드에 이르게 됩니다. 우리는 `Integer`에서 `int`로의 매핑을 제공해야 합니다.
- `ToIntFunction`은 **함수형 인터페이스**이므로 람다 또는 메서드 참조를 통해 인스턴스를 제공할 수 있습니다.
- 어쨌든 `Integer`를 `int`로 변환하기 위해 `Integer#intValue`를 사용할 수 있으므로 `mapToInt` 안에서 다음과 같이 작성할 수 있습니다:
- `mapToInt( (Integer i) -> i.intValue() )`
  (또는 일부는 다음을 선호할 수 있습니다: `mapToInt(Integer::intValue)`.)
- 그러나 비슷한 코드는 언박싱을 사용하여 생성될 수 있으며, 컴파일러는 이 람다의 결과가 `int` 타입이어야 한다는 것을 알고 있습니다(`mapToInt`에서 사용되는 람다는 `ToIntFunction` 인터페이스의 구현으로서, `int applyAsInt(T value)` 타입의 메서드 본문을 기대하며 `int`를 반환해야 합니다).
- 따라서 간단히 다음과 같이 작성할 수 있습니다:
  - `mapToInt((Integer i)->i)`
- 또한 `List<Integer>#stream()`이 `Stream<Integer>`를 반환하므로 컴파일러가 `(Integer i)`의 `Integer` 타입을 추론할 수 있으므로 생략할 수 있습니다. 이로 인해 다음과 같이 작성할 수 있습니다.
  - `mapToInt(i -> i)`





#  `int[]` -> `List<Integer>` 

```java
int[] arr = {1, 2, 3, 4, 5}; // 변환할 int 배열

LinkedList<Integer> list = new LinkedList<>(Arrays.stream(arr).boxed().collect(Collectors.toList()));
```

1. `Arrays.stream(바꾸고싶은 배열)` : int[]을 Stream으로 변환한다.
2. `boxed()` : `IntStream`을 `Stream<Integer>` 로 변환한다.
3. `collect()` : `Stream<Integer>` -> `List<Integer>` 을 생성한다.





## LinkedList 내의 모든 원소 더하기

```java
LinkedList<Integer> list = new LinkedList<>();
list.add(1);
list.add(2);
list.add(3);

int sum = list.stream().reduce(0, Integer::sum);
long sum = list.stream().mapToLong(Integer::longValue).sum();
```

1. `stream()` : `IntSteam`을 생성한다. 
2. `mapToLong()` : `IntSteam`은 `int` 값만 처리할 수 있는 스트림이다. 따라서 `LongSteam`으로 변환해야한다. 
3. `sum()` : `LongSteam`에 `sum()` 메서드로 모든 요소를 더한다.



#### 람다 표현식

`Integer::longValue`는 메서드 레퍼런스(Method reference)로, `Integer` 객체에서 `long` 값을 추출하는 데 사용되는 메서드입니다.

Java 8 이후부터 메서드 레퍼런스는 람다 표현식(lambda expression)의 축약형으로 사용됩니다. 메서드 레퍼런스를 사용하면 람다 표현식의 내용을 더 간결하고 명확하게 만들 수 있습니다.

예를 들어, 다음 람다 표현식은 `Integer` 값을 `long` 값으로 변환합니다.

```java
x -> x.longValue()
```

하지만, 이 람다 표현식은 더 간단한 메서드 레퍼런스로 대체될 수 있습니다.

```java
Integer::longValue
```

메서드 레퍼런스의 구성 요소는 다음과 같습니다.

- `Integer` : 메서드를 가진 클래스
- `::` : 구분자
  - `::`는 이중 콜론(double colon)으로 읽힙니다. 이는 Java 8 이전의 버전에서 이미 사용되던 기존의 문법 중 하나입니다. `::`는 메서드 레퍼런스를 표시하는 데 이중 콜론을 사용하였습니다. Java 8에서는 메서드 레퍼런스를 도입하면서, 기존의 이중 콜론 문법을 확장하여 `::`를 구분자로 사용하게 되었습니다.
- `longValue` : 메서드 이름

따라서, `Integer::longValue`는 `Integer` 클래스의 `longValue` 메서드를 참조하는 메서드 레퍼런스입니다. 이 메서드 레퍼런스는 `Integer` 객체에서 `long` 값을 추출하는 데 사용됩니다.





### `sum()` VS `reduce()`

- `sum()` 메서드는 스트림의 요소들을 **합한 결과**를 반환합니다. 예를 들어, `IntStream`에서 `sum()` 메서드를 호출하면 스트림의 모든 요소들을 더한 값을 반환합니다.
- `reduce()` 메서드는 스트림의 요소들을 **연산**하여 하나의 결과값을 반환합니다. 연산 방식은 람다식으로 지정할 수 있습니다. 예를 들어, 스트림의 요소들을 곱하는 연산을 수행할 수 있습니다.

`reduce()` 메서드는 더 많은 유연성을 제공하며, 다양한 연산을 수행할 수 있습니다. 예를 들어, 스트림의 요소 중 최대값, 최소값, 평균값 등을 구하는 연산을 수행할 수 있습니다. 또한, `reduce()` 메서드는 초기값을 지정할 수 있습니다. **초기값을 지정하면 스트림이 비어있을 때 반환할 기본값을 지정할 수 있습니다.**

하지만, `reduce()` 메서드는 보다 복잡한 연산을 수행할 때 유용하지만, 단순한 연산을 수행하는 경우에는 `sum()` 메서드를 사용하는 것이 더 간단합니다.

```java
T reduce(T identity, BinaryOperator<T> accumulator)
```

- `identity` : 초기값. 스트림이 비어있을 때 반환할 기본값
- `accumulator` : 계산 람다식

예시)

```java
int[] array = {1, 2, 3, 4, 5};

int result = Arrays.stream(array).reduce(1, (x, y) -> x * y);
System.out.println(result); // 출력 결과: 120
```





### `boxed()`

`boxed()` 메서드는 Java 8에서 `Stream`에서 `IntStream`/`LongStream`/`DoubleStream` 등의 기본 타입 스트림을 사용하는 경우, 해당 요소를 박싱(boxing)하여 스트림의 요소 타입을 해당 **기본 타입의 래퍼 클래스로 변환**하는 메서드입니다.

예를 들어, `IntStream`에서 `Stream<Integer>`로 변환하고 싶을 때, `boxed()` 메서드를 사용할 수 있습니다. 이 메서드는 스트림의 요소를 해당 기본 타입의 래퍼 클래스로 변환하여 스트림의 요소 타입을 변경합니다. 즉, `IntStream`의 `int` 요소를 `Integer`로 박싱합니다.



```java
IntStream intStream = IntStream.of(1, 2, 3, 4, 5);
Stream<Integer> integerStream = intStream.boxed();
```

- 위 코드에서 `IntStream`의 `of()` 메서드를 사용하여 `IntStream`을 생성하고, `boxed()` 메서드를 사용하여 `Stream<Integer>`로 변환합니다.
- `boxed()` 메서드를 사용하면, 기본 타입 스트림을 다양한 방식으로 처리할 수 있습니다. 예를 들어, `IntStream`에서 `sum()` 메서드를 사용하여 모든 요소의 합을 구할 수 있습니다.

```java
IntStream intStream = IntStream.of(1, 2, 3, 4, 5);
int sum = intStream.sum();
```



하지만, `Stream<Integer>`에서 `sum()` 메서드를 직접 사용할 수는 없습니다. 이 경우, `boxed()` 메서드를 사용하여 `Integer`로 박싱한 뒤, `sum()` 메서드를 사용하여 요소의 합을 계산할 수 있습니다.

```java
Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
int sum = integerStream.mapToInt(Integer::intValue).sum();
```

위 코드에서 `Stream<Integer>`에서 `mapToInt()` 메서드를 사용하여 `IntStream`으로 변환한 뒤, `sum()` 메서드를 사용하여 요소의 합을 계산합니다.

-> `Steam<Integer>` 에서 `sum()`을 쓰려고 물어본게 아니라, `int[]` 에서 `List<Integer>`로 바꿀 때 `Steam<Integer>`가 필요해서 `boxed()`를 사용한 것.
