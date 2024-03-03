# 싱글턴

싱글턴 패턴은, 클래스가 단 하나의 인스턴스만 갖는 것을 보장하는 패턴이다.

굳이 여러개의 인스턴스가 필요하지 않은 경우, 하나의 인스턴스를 계속 재활용 할 수 있다면 메모리를 아낄 수 있다.



# Java에서 싱글턴 구현하기

무려 7가지의 방법이 있다...Inpa 님의 블로그 글을 가져왔다.

https://inpa.tistory.com/entry/GOF-%F0%9F%92%A0-%EC%8B%B1%EA%B8%80%ED%86%A4Singleton-%ED%8C%A8%ED%84%B4-%EA%BC%BC%EA%BC%BC%ED%95%98%EA%B2%8C-%EC%95%8C%EC%95%84%EB%B3%B4%EC%9E%90#%EC%8B%B1%EA%B8%80%ED%86%A4_%ED%8C%A8%ED%84%B4_%EA%B5%AC%ED%98%84_%EA%B8%B0%EB%B2%95_%EC%A2%85%EB%A5%98

## 1. Eager Initialization

- `private static final Singleton INSTANCE = new Singleton();`

- `static final` 로 미리 인스턴스를 만들어둔다. 이후 `getInstance()` 라는 메서드에 인스턴스 반환하면 된다.
- 그러나 static 멤버는 어플리케이션이 실행되고 클래스를 로딩할 때 생성되기 때문에, 당장 필요하지 않더라도 메모리에 적재한다. 근데 만약 리소스가 많이 필요한 객체인 경우, 메모리 공간 낭비가 발생하게 된다.
- 예외 처리를 할 수 없다.

```java
class Singleton {
    // 싱글톤 클래스 객체를 담을 인스턴스 변수
    private static final Singleton INSTANCE = new Singleton();

    // 생성자를 private로 선언 (외부에서 new 사용 X)
    private Singleton() {}

    public static Singleton getInstance() {
        return INSTANCE;
    }
}
```



## 2. Static block initialization

- `private static Singleton instance;`
- static block을 이용해 예외를 처리할 수 있으나, 여전히 `static` 특성으로 인해 공간 낭비는 해결하지 못함

```java
class Singleton {
    // 싱글톤 클래스 객체를 담을 인스턴스 변수
    private static Singleton instance;

    // 생성자를 private로 선언 (외부에서 new 사용 X)
    private Singleton() {}
    
    // static 블록을 이용해 예외 처리
    static {
        try {
            instance = new Singleton();
        } catch (Exception e) {
            throw new RuntimeException("싱글톤 객체 생성 오류");
        }
    }

    public static Singleton getInstance() {
        return instance;
    }
}
```



## 3. Lazy initialization

- `private static Singleton instance;`
- `getInstacne()`  메서드를 호출했을 때 인스턴스 변수가 `null` 인지 아닌지에 따라 초기화 하거나, 이미 만든 인스턴스를 반환한다.
- 공간 낭비 문제를 해결하지만, **Thread Safe 하지 않다**는 치명적 단점 존재

```java
class Singleton {
    // 싱글톤 클래스 객체를 담을 인스턴스 변수
    private static Singleton instance;

    // 생성자를 private로 선언 (외부에서 new 사용 X)
    private Singleton() {}
	
    // 외부에서 정적 메서드를 호출하면 그제서야 초기화 진행 (lazy)
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton(); // 오직 1개의 객체만 생성
        }
        return instance;
    }
}
```

### Thread Safe 하지 않은 싱글턴 생성?

- Java는 멀티 쓰레드 언어다. 그런데 위의 방법으로 싱글턴 객체를 생성하게 되면, 인스턴스의 null을 비교하는 순간에 각기 다른 스레드가 들어와 인스턴스를 2개 이상 만들 수도 있게된다.

## 4. Thread Safe initialization

- `private static Singleton instace;`
- 

```java
```

