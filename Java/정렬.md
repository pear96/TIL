# 정렬

## `int[] `배열을 오름차순, 내림차순 정렬하는 방법

### 오름차순 정렬

int[] 배열을 오름차순으로 정렬하기 위해서는 `Arrays.sort()` 메서드를 사용합니다. `Arrays.sort()` 메서드는 기본적으로 오름차순으로 정렬합니다.

```java
int[] arr = {5, 2, 8, 1, 6};

// 1. Arrays.sort(배열)
Arrays.sort(arr);

// 2. Arrays.paralleSort(배열) <- 배열의 크기가 큰 경우 빠른 속도로 정렬
Arrays.parallelSort(arr);

// 3. IntStream
// Arrays.stream(배열) -> IntStream 변환
// Arryas.stream(배열).sorted().toArray() -> 정렬 후 다시 int[] 배열로 전환
int[] sortedArr = Arrays.stream(arr).sorted().toArray();
```



### 내림차순 정렬

int[] 배열을 내림차순으로 정렬하려면, `Arrays.sort()` 메서드를 사용하되, 정렬 방식을 변경해야 합니다. 이를 위해 `Collections.reverseOrder()` 메서드를 사용합니다.

```java
Integer[] arr = {5, 2, 8, 1, 6};

// 1. Arrays.sort(배열, Collections.reverseOrder())
Arrays.sort(arr, Collections.reverseOrder());

// 2. IntStream + Collections.reverseOrder()
int[] reversedArr = Arrays.stream(arr) // Stream<Integer>
        .boxed() // Stream<Integer>
        .sorted(Collections.reverseOrder())
        .mapToInt(Integer::intValue) // Stream<Integer> -> int[]
        .toArray();
```



## `LinkedList<Integer>`을 오름차순, 내림차순 정렬하는 방법

### 오름차순 정렬 => Collections

`LinkedList<Integer>`을 오름차순으로 정렬하기 위해서는 `Collections.sort()` 메서드를 사용합니다. `LinkedList<Integer>`은 List 인터페이스를 구현하고 있기 때문에 `Collections.sort()` 메서드를 사용할 수 있습니다.

```java
LinkedList<Integer> list = new LinkedList<Integer>(Arrays.asList(5, 2, 8, 1, 6));

// 1. Collections
Collections.sort(list);

// 2. stream().sorted()
List<Integer> sortedList = list.stream().sorted().collect(Collectors.toList());
```

이 방법은 `List` 인터페이스를 구현한 모든 클래스에 대해 사용할 수 있으며, 내부적으로 해당 클래스의 `sort()` 메서드를 호출합니다.



### 내림차순 정렬 => Comparator

`LinkedList<Integer>`을 내림차순으로 정렬하려면, `Collections.sort()` 메서드를 사용하되, 정렬 방식을 변경해야 합니다. 이를 위해 `Comparator` 인터페이스를 구현한 클래스를 만들어서 `Collections.sort()` 메서드에 전달합니다.

```java
LinkedList<Integer> list = new LinkedList<Integer>(Arrays.asList(5, 2, 8, 1, 6));

// 1. Collections.reverseOrder() + Comparator<Integer>
Comparator<Integer> reverseOrder = Collections.reverseOrder();
Collections.sort(list, reverseOrder);

// 2. stream().sorted(Comparator.reverseOrder())
List<Integer> sortedList = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
```

위 코드에서 `Collections.reverseOrder()` 메서드는 `Comparator<Integer>` 인터페이스를 구현한 클래스를 반환합니다. 따라서 `Comparator<Integer> reverseOrder = Collections.reverseOrder();` 를 사용하여 reverseOrder 변수에 저장하고, 이를 `Collections.sort()` 메서드의 두 번째 인자로 전달합니다.





## Parallel Sorting

Java 8 이상에서는 `Arrays.sort()`, `Collections.sort()`, `Stream.sorted()` 메서드에서 parallel sorting을 지원합니다. parallel sorting을 사용하면 배열과 리스트의 크기가 큰 경우에 더욱 빠른 속도로 정렬할 수 있습니다. parallel sorting을 사용하려면, `Arrays.parallelSort()`, `List.parallelStream().sorted()` 메서드를 사용하면 됩니다.

```java
javaCopy codeint[] arr = {5, 2, 8, 1, 6};
Arrays.parallelSort(arr);
System.out.println(Arrays.toString(arr)); // 출력: [1, 2, 5, 6, 8]

LinkedList<Integer> list = new LinkedList<Integer>(Arrays.asList(5, 2, 8, 1, 6));
List<Integer> sortedList = list.parallelStream().sorted().collect(Collectors.toList());
System.out.println(sortedList); // 출력: [1, 2, 5, 6, 8]
```

하지만 parallel sorting을 사용할 때에는, 먼저 배열이나 리스트를 병렬처리할 수 있는 크기로 나누어야 하기 때문에, 작은 크기의 배열이나 리스트에 대해서는 오히려 속도가 느려질 수 있습니다. 따라서 배열이나 리스트의 크기가 작을 때에는, parallel sorting 대신에 일반적인 sorting 방법을 사용하는 것이 좋습니다.