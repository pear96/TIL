Java의 객체지향 특징 : A PIE (Abstract 추상화, Polymorphism 다형성, Inheritance 상속, Encapsulation 캡슐화)

이 중 캡슐화의 사용 예시로 '접근 제한자'를 둘 수 있다.

접근 제한자 : 클래스의 멤버 변수, 메서드에 외부 접근을 제한할 수 있다.

직접 변수를 수정하는 것에 비해 `getter` 와 `setter` 를 사용하는게 좋은 이유

- 외부에서 직접 변수에 접근하는 것을 방지
- setter로 유효성 검사 수행
- 값을 설정할 때마다 로깅한다던가, 변경 사항을 추적하는 작업 추가 가능
- 내부 구현을 변경하지 않고도 클래스 인터페이스 수정

[직접 변수를 수정할 때]

```java
// 직접 변수를 수정하는 경우
public class Person {
    public String name;

    public static void main(String[] args) {
        Person person = new Person();
        person.name = "John"; // 변수에 직접 접근하여 값 설정
        System.out.println(person.name); // John 출력

        // 변수를 직접 수정하는 경우, 제한 없이 값 변경 가능
        person.name = null;
        System.out.println(person.name); // null 출력
    }
}

```



[getter, setter 사용]

```java
// Getter와 Setter를 사용하는 경우
public class Person {
    private String name;

    public void setName(String name) {
        if (name != null) {
            this.name = name; // 유효성 검사를 통한 값 설정
        }
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("John"); // Setter를 통한 값 설정
        System.out.println(person.getName()); // John 출력

        // Setter를 사용하면 유효성 검사 가능
        person.setName(null);
        System.out.println(person.getName()); // John 출력 (유효성 검사에 의해 null 값은 설정되지 않음)
    }
}

```

1. `setName()` 에서 `null`을 검사할 수 있다.
2. `name`이 `private` 이라 외부에서 직접 접근할 수 없다.
3. `name`의 유효성 검사나 값 변환 로직을 변경해도, 클래스 외부에서는 수정 없이 메서드 호출 가능
4. `setName()` 에서 로깅 추가같이 변경 사항 추적이 쉬울 수 있음