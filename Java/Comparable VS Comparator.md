# `Comparable<T>`

- interface이다.
- pacakge : java.lang (따로 import하지 않음)
- T는 비교하려는 Object의 **Type**을 의미한다.
- 정렬하려는 객체의 클래스에 구현되며, 객체들 간의 기본 정렬 순서를 정의한다.
  - 이 클래스를 통해 만든 객체 정렬시 natural ordering을 구현하는 것과 같다.
- `compareTo()` 메서드를 구현해야한다.
- 매개변수로 전달된 다른 객체와 비교하여 현재 객체가 작은지, 큰지, 같은지를 반환한다.
  - 작으면 음수, 같으면 0, 크면 양수

```java
public class MyObject implements Comparable<MyObject> {
    private int id;
    private String name;

    public MyObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter methods for id and name

    @Override
    public int compareTo(MyObject obj) {
        return this.id - obj.id;
    }
}

```





# `Comparator<T>`

- interface이다.
- package : java.util
- 정렬하려는 객체의 클래스와는 **독립적으로** 정렬 순서를 정의한다.
- 객체의 특정 속성에 따라 정렬 순서를 지정할 수 있다.
- `compare(o1, o2)` 메서드를 구현해야한다. 두개의 객체를 매개 변수로 전달 받아 비교하여 순서를 반환한다.

```java
import java.util.Comparator;

public class MyObject {
    private int id;
    private String name;

    public MyObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Comparator<MyObject> IdComparator = new Comparator<MyObject>() {
        public int compare(MyObject obj1, MyObject obj2) {
            return obj1.id - obj2.id;
        }
    };

    public static Comparator<MyObject> NameComparator = new Comparator<MyObject>() {
        public int compare(MyObject obj1, MyObject obj2) {
            return obj1.name.compareTo(obj2.name);
        }
    };
}


```







## 정리

- Comparable은 객체의 **기본 정렬 순서**를 지정하는 인터페이스. 객체 클래스에 구현
- Comparator은 객체의 **특정 속성**에 따라 정렬 순서를 지정하는 인터페이스. 외부에서 구현
- Comparable과 Comparator의 선택은 객체가 기본 정렬 순서를 가지고 있는 경우 Comparable을 구현하면 됩니다. 객체의 기본 정렬 순서가 없거나, 기본 정렬 순서와는 다른 순서로 정렬해야 할 경우에는 Comparator를 구현합니다. Comparator는 하나의 클래스에 대해 **여러 가지 정렬 방식**을 제공할 수 있습니다.