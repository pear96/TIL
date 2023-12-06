|      | 추상 클래스                                                  | 인터 페이스                                           |
| ---- | ------------------------------------------------------------ | ----------------------------------------------------- |
| 구현 | `abstract`                                                   | `interface`                                           |
| 내용 | 일반 클래스 + 추상 메서드                                    | 추상 메서드의 집합                                    |
| 동작 | `상태`와 `동작` 을 함께 가짐                                 | `동작` 만 **정의**                                    |
| 관계 | `is-a` 관계를 가지는 클래스 계층 구조                        | `can-do` 관계를 가지는 다양한 클래스 간의 공통된 동작 |
| 중점 | 클래스의 **확장**                                            | 클래스의 **동작**                                     |
| 특징 | 추상 클래스의 인스턴스를 만들 수 없음. 확장한 하위 클래스가 인스턴스를 만들어야함. | 클래스들이 구현해야하는 동작의 집합                   |



[추상 클래스 예시 코드]

```java
abstract class Animal {
    String name;

    Animal(String name) {
        this.name = name;
    }

    abstract void sound(); // sound는 추상 메서드이며, 구현 내용이 없음

    void sleep() {
        System.out.println("Zzzz...");
    }
}

class Dog extends Animal {
    Dog(String name) {
        super(name);
    }

    void sound() {
        System.out.println("Woof!"); // 추상 클래스를 상속한 하위 클래스에서 구현
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog("Buddy");
        dog.sound(); // Woof!
        dog.sleep(); // Zzzz...
    }
}

```



[인터페이스 예시 코드]

```java
interface Shape { // 메서드를 만들라는 내용만 있음
    double calculateArea();

    double calculatePerimeter();
}

class Circle implements Shape {
    double radius;

    Circle(double radius) {
        this.radius = radius;
    }
	
    // 실제 전부 구현해야함.
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(5);
        System.out.println("Area: " + circle.calculateArea());
        System.out.println("Perimeter: " + circle.calculatePerimeter());
    }
}

```



결론

- 추상 클래스는 클래스의 일부 기능을 구현하면서, **확장 가능한 기반 클래스**를 만들 때 사용한다.
- 인터페이스는 **여러 클래스에서 공통으로 구현해야하는 메서드를 정의**할 때 사용한다.



## 인터페이스의 default Method

https://siyoon210.tistory.com/95

왜 만들었나? 인터페이스는 함수를 추가하면 하위에서 싹 다 구현해줘야하는데, default로 추가하면 그러지 않아도 되기 때문이다