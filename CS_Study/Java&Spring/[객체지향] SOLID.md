# 객체지향 설계 원칙이란?



# Single Response Principle



# Open Closed Principle



# Liskov Substitution Priciple

리스코브 치환 원칙 = 서브 타입은 언제나 기반 타입으로 교체할 수 있어야한다. 상위 클래스 타입으로 객체를 선언하여 하위 클래스의 인스턴스를 받으면, 업캐스팅 된 상태에서 부모의 메서드를 사용해도 동작이 의도대로 흘러가야한다.

[Inpa - 리스코프 치환 원칙](https://inpa.tistory.com/entry/OOP-%F0%9F%92%A0-%EC%95%84%EC%A3%BC-%EC%89%BD%EA%B2%8C-%EC%9D%B4%ED%95%B4%ED%95%98%EB%8A%94-LSP-%EB%A6%AC%EC%8A%A4%EC%BD%94%ED%94%84-%EC%B9%98%ED%99%98-%EC%9B%90%EC%B9%99)

```java
void myData() {
	// Collection 인터페이스 타입으로 변수 선언
    Collection data = new LinkedList();
    data = new HashSet(); // 중간에 전혀 다른 자료형 클래스를 할당해도 호환됨
    
    modify(data); // 메소드 실행
}

void modify(Collection data){
    list.add(1); // 인터페이스 구현 구조가 잘 잡혀있기 때문에 add 메소드 동작이 각기 자료형에 맞게 보장됨
    // ...
}
```

- 부모 클래스로 만든 인스턴스를 자식 클래스로 만든 인스턴스로 교체해도 정상적으로 동작해야한다. 
- 자식 클래스가 부모 클래스의 메소드 시그니처를 변경하거나, 메서드를 기존 의도와 다르게 오버라이딩 하면 안된다.
- 이때 상속은 ==자식 클래스 IS-A 부모 클래스== 일때만 가능하도록 제한해야하며, 이외에는 ==확장==으로 해결해야한다.
- 업캐스팅, 다운 캐스팅 참고

```java
// 업캐스팅 : 자식 클래스의 인스턴스를 부모 클래스 타입으로 바꿈
Child child = new Child();
Parent parent = child;
// 다운캐스팅 : 부모 클래스의 타입을 가진 객체를 자식 클래스 타입으로 바꿈
Parent parent = new Child();
Child child = (Child) parent;
```



# Interface







# Diversion of Injection