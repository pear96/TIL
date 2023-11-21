[참고 링크](https://www.youtube.com/watch?v=qrQZOPZmt0w&ab_channel=%EB%A0%88%ED%8A%B8%EB%A1%9Cretr0)

# Serialization(직렬화) 란 무엇인가?

Object(객체)를 String 또는 bytes로 바꾸는 것이다. 객체를 바이트 스트림으로 변환하는 과정

왜냐면 오브젝트는 메모리에만 올라갈 수 있는데, String이나 bytes는 드라이브, 통신선에도 올라갈 수 있기 때문이다.

반대로 이미 존재하는 String, bytes를 Object로 만드는건 Deserialization(역직렬화)이다.

ex) 유니티에서 편집한 인스턴스는 메모리에 있느니 유니티를 끄면 사라져야하는데, 다시 키면 남아있다. 왜? 이 인스턴스는 Serializable 하기 때문에 사실은 인스턴스를 편집한 것 같지만, 그 정보가 저장되어있는 텍스트 파일을 편집한 것이기 때문이다.

ex) 스타트랙에서 텔레포트는 사람을 원자단위로 분해한 데이터를 다른 행성으로 전송해 다시 재조립한다.



1번 컴퓨터는 Window고, 2번 컴퓨터는 Mac이다. 1번 컴퓨터에서 만든 객체는 메모리에 저장되어있는데, 그냥 2번으로 옮기면 읽을 수 없다. 대신 둘 다 2진법을 사용한다는 공통점이 있기 때문에, 직렬화된 데이터를 통신선으로 전송해서, 2번 컴퓨터에서 해당 데이터를 다시 역직렬화해서 객체로 사용하는 것이다.

Object <-> 텍스트 / btyes / xml / json / yaml

- 컴파일러가 확인해주지 않는다
- 객체를 읽고, 작성하는 메서들을 마음대로 커스텀 할 수 있다?

### 핵심 문제 : Java Serialization is not part of the object model

조각상 클래스와 위치 클래스가 있다고 치자

```java
// getter, hashCode, equals, toString 생략

public class Statue implements Serializable {
    private final String name;
    private final int height;
    private final Location location;
    
    public Statue(String name, int height, Location location) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(location);
        if (height <= 0)
            throw new IllegalArgumentException(String.format("%d", height));
       	this.name = name;
        this.height = height;
        this.location = location;
    }
}

class Location implements Serializable {
    private final String city;
    private final String country;
    
    public Location(String city, String country) {
        Objects.requireNonNull(city);
        Objects.requireNonNull(country);
		this.city = city;
        this.country = country;
    }
}
```

이 객체를 serial.data에 저장해보자.

```java
var statue = new Statue("자유의 여신상", 46, new Location("뉴욕", "미국"));

try (var oos = new ObjectOutputStream(
		new FileOutputStream("serial.data"))) {
    oos.writeObject(statue);
}
```

그리고 다시 읽어보자

```java
Statue statue;
try(var ios = new ObjectInputStream(
		new FileInputStream("serial.data"))) {
    statue = (Statue) ios.readObject();
}
```

![image-20231024174455629](Serialization(직렬화) 란 무엇인가.assets/image-20231024174455629.png)

읽는 순서는 타입에 맞춰 모든 값이 비어있는 Statue가 생성된다.  이때 클래스의 생성자에 맞추는게 아니고, super클래스(Object)의 생성자에 맞춰 생성되고 나서 타입이 정해진다.

그리고 name, height 정보가 채워진다. 이제 모든 값이 비어있는 Location 인스턴스가 생성되고, 값이 채워진다.



## Java Record

- 자바 레코드는 튜플이다
- 일반 클래스에 비해 데이터 전송에 매우 적합하다.

아까 적었던 Location 클래스에서 boilerplate? 미리 준비해둬야 하는 메서드가 매우 많다.

![image-20231024174903051](Serialization(직렬화) 란 무엇인가.assets/image-20231024174903051.png)

IDE가 도와줄 수 있지만, 이를 관리하는 것은 쉽지 않은 일이다. 하지만 record를 사용한다면??

```java
public record Location (String city, String country) {}
```

이 한줄로 끝낼 수 있다~





## 궁금증

- 그렇다면 나는 원래 프로젝트에서 그냥 class 만들고 응답했는데, 왜 record는 Serializable을 implements하는걸까??
  - 이미 구현하고 있기 때문에 명시적으로 `implements Serializable` 을 쓰나 안쓰나 똑같다.
  - record는 주로 데이터를 전달하거나 보관하는데 사용된다. 네트워크를 통해 전송하거나 파일에 저장할 가능성이 높아서 자동으로 포함시켰다.
  - 그리고 웹으로 응답하는건 Json으로 직렬화 하는 것이기 때문에, 기존 클래스도 문제없이 직렬화가 된 것이다. (Spring Jackson이 자동으로 변환해줌)
  - Json으로 직렬화 하는 것과 Java에서 직렬화 하는 것은 상관이 없다. 
- 그럼 직렬화를 써야하는 순간은 언제인가??
  - 파일 저장, 소켓 통신 등등...





## [serialVersionUid가 뭐고, 왜 써야하는가?](https://stackoverflow.com/questions/285793/what-is-a-serialversionuid-and-why-should-i-use-it)

> The serialization runtime associates with each serializable class a version number, called a `serialVersionUID`, which is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. If the receiver has loaded a class for the object that has a different `serialVersionUID` than that of the corresponding sender's class, then deserialization will result in an [`InvalidClassException`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/InvalidClassException.html). A serializable class can declare its own `serialVersionUID` explicitly by declaring a field named `serialVersionUID` that must be static, final, and of type `long`:

직렬화는모든 직렬화 가능한 클래스의 svUID와 연결이 되어있다. svUID는 직렬화 할 수 있는 객체를 위한 클래스들을 로드한 직렬화 객체의 보내는 사람과 받는 사람이 확인하기 위한 역직렬화 할때 사용된다.

만약 수신자가 발신자의 클래스와 다른 UID를 가진 객체를 불러들인다면, 역직렬화는 InvalidClassException을 뿜을것이다.

직렬화 가능한 클래스는 명시적으로 static, final에 long 타입에 serialVersionUID를 가진 자신만의 UID를 선언할 수 있다.

> ```java
> ANY-ACCESS-MODIFIER static final long serialVersionUID = 42L;
> ```

> If a serializable class does not explicitly declare a `serialVersionUID`, then the serialization runtime will calculate a default `serialVersionUID` value for that class based on various aspects of the class, as described in the Java(TM) Object Serialization Specification. However, it is *strongly recommended* that all serializable classes explicitly declare `serialVersionUID` values, since the default `serialVersionUID` computation is highly sensitive to class details that may vary depending on compiler implementations, and can thus result in unexpected `InvalidClassExceptions` during deserialization. Therefore, to guarantee a consistent `serialVersionUID` value across different java compiler implementations, a serializable class must declare an explicit `serialVersionUID` value. It is also strongly advised that explicit `serialVersionUID` declarations use the private modifier where possible, since such declarations apply only to the immediately declaring class — `serialVersionUID` fields are not useful as inherited members.

만약 직렬화 가능한 클래스가 명시적으로 UID를 선언하지 않는다면, 그 클래스를 위해 직렬화 런타입에서  다양한 방면을 고려해  기본 UID를 계산한다.

하지만 직렬화 가능한 클래스는 명시적으로 UID를 선언하는 것을 매우 강력하게 권장한다. 왜냐하면 기본 UID 계산은 컴파일러 구현에 따라 달라지는 클래스의 세부사항에 대해 매우 쉽게 영향받기 때문이다. 

이는 역직렬화 과정에서 예상하지 못한 InvalidClassException을 뿜을 수 있다.

따라서, 자바 컴파일러가 바뀐다 할지라도 일관적인 UID를 보장하기 위해서, 직렬화가 가능한 클래스는 명시적으로 UID를 작성해야한다.

그리고 가능하면 private 접근자를 사용하는 것이 매우 권장되는데, 왜냐하면 그 선언은 선언하고 있는 클래스에만 적용되기 때문이다. 상속받은 클래스에서는 소용이 없다.



=> 즉, 직렬화 가능한 객체에서는 수신자와 발신자가 같은 클래스를 직렬화, 역직렬화 하기 위해 고유한 serialVersionUID를 사용해야한다.





# Java record가 Serialization에서 좋은 이유

value를 stream에서 읽는다

레코드 자체가 직렬화에 잘 맞게 설계되어있다.

먼소리야?????

![image-20231024180012810](Serialization(직렬화) 란 무엇인가.assets/image-20231024180012810.png)





## Serialization 예시

`Person.java`

```java
import java.io.Serializable;

public record Person(String name, int age) implements Serializable {
	private static final long serialVersionUID = 1L;  // 추천: serialVersionUID를 정의합니다.
}

```

`SerializationExample.java`

```java

import java.io.*;

public class SerializationExample {
	public static void main(String[] args) {
		Person person = new Person("John", 30);

		// 직렬화
		try (FileOutputStream fileOut = new FileOutputStream("person.txt");
			 ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(person);
			System.out.println("Serialized data is saved in person.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}

		Person deserializedPerson = null;

		// 역직렬화
		try (FileInputStream fileIn = new FileInputStream("person.txt");
			 ObjectInputStream in = new ObjectInputStream(fileIn)) {
			deserializedPerson = (Person) in.readObject();
			System.out.println("Deserialized Person:");
			System.out.println("Name: " + deserializedPerson.name());
			System.out.println("Age: " + deserializedPerson.age());
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Person class not found");
			c.printStackTrace();
			return;
		}
	}
}
```

