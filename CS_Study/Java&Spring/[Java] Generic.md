https://st-lab.tistory.com/153

# Generic

데이터 형식에 의존하지 않고, **하나의 값이 여러 다른 데이터 타입들을 가질 수 있도록** 하는 방법

데이터 타입이 클래스 내부에서 지정하는게 아니라, 외부에서 사용자에 의해 지정된다.

이때 primitive Type은 사용할 수 없다.

- 제네릭의 장점
  1. 잘못된 타입이 들어올 수 있는 것을 컴파일 단계에서 방지할 수 있다.
  2. 클래스 외부에서 타입을 지정해주기 때문에 따로 타입을 체크하고 변환할 필요가 없다.
  3. 코드의 재사용성

## 클래스  타입

```java
public class ClassName <T> { ... }
public class ClassName <T, K> { ... }

public interface InterfaceName <T> { ... }
public interface InterfaceName <T, K> { ... }
 
// HashMap의 경우 아래와 같이 선언되어있을 것이다.
public class HashMap <K, V> { ... }
```

```java
public class ClassName <T, K> { ... }
 
public class Main {
	public static void main(String[] args) {
        // 외부에서 타입을 지정한다.
		ClassName<String, Integer> a = new ClassName<String, Integer>();
	}
}
```



## 메서드 타입

```java
public <T> T genericMethod(T o) {	// 제네릭 메소드
		...
}
 
[접근 제어자] <제네릭타입> [반환타입] [메소드명]([제네릭타입] [파라미터]) {
	// 텍스트
}
```



# 필요한 이유?

**정적 메서드로 선언할 때 필요**

- static은 프로그램 실행시 이미 메모리에 올라가있다. 객체 생성을 통해 접근할 필요가 없어서 클래스 이름을 통해 바로 사용할 수 있다.
- 그러면 static 메서드는 데이터 타입을 어떻게 얻어올 수 있는가? => 제네릭 메서드를 사용한다.

```java
// 불가능
class ClassName<E> {
 
	/*
	 * 클래스와 같은 E 타입이더라도
	 * static 메소드는 객체가 생성되기 이전 시점에
	 * 메모리에 먼저 올라가기 때문에
	 * E 유형을 클래스로부터 얻어올 방법이 없다.
	 */
	static E genericMethod(E o) {	// error!
		return o;
	}
	
}

// 가능
class ClassName<E> {
 
	private E element; // 제네릭 타입 변수
 
	void set(E element) { // 제네릭 파라미터 메소드
		this.element = element;
	}
 
	E get() { // 제네릭 타입 반환 메소드
		return element;
	}
 
	// 아래 메소드의 E타입은 제네릭 클래스의 E타입과 다른 독립적인 타입이다.
	static <E> E genericMethod1(E o) { // 제네릭 메소드
		return o;
	}
 
	static <T> T genericMethod2(T o) { // 제네릭 메소드
		return o;
	}
 
}
 
```



## 제한된 Generic, 와일드 카드

- `extends` , `super` , `?` 를 활용해서 데이터 타입을 제한할 수 있다.
  - `<K extends T> ` : 상한 경계. T와 T의 자손 타입만 가능
  - `<K super T>` : 하한 경계. T와 T의 부모 타입만 가능
  - `<? extends T>` : T와 T의 자손 타입만 가능 => ?는 타입이 지정되지 않는다.
  - `<? super T>` : T와 T의 부모 타입만 가능 => ?는 타입이 지정되지 않는다.
  - `<?>` : 모든 타입 가능

```java
<K extends Number> -> Integer, Long, Double, Float 등 숫자들만 사용 가능
<E extends Comparable<? super E>> -> Comparable, 정렬을 사용하는 자료구조들
    E는 반드시 Comparable을 구현해야한다.
```

