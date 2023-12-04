[참고](https://bcp0109.tistory.com/360)

Call by Value는 **값(value)**을 넘기는 것이고, Call by Reference는 **주소값(location)**을 넘기는 것이다.

> 자바는 항상 call by value이다.

## Call by Value

- 원시 타입(int, float, double, boolean, char..)은 항상 Call by Value
- 넘겨지는 값을 복사해서 메서드에 전달하며, 모든 변경사항은 해당 메서드 안에서만 반영된다. 여기서의 변경사항은 호출 메서드에 반영되지 않는다.

![img](https://raw.githubusercontent.com/ParkJiwoon/PrivateStudy/master/java/images/screen_2022_01_30_22_01_33.png)

- test안에서 modify를 호출했지만, 값의 변경은 modify 메서드 영역 안에서만 반영된다.

## Call by Reference

- 클래스, Object, Collections, String, interface는 항상 Call by Reference다. 
- Call by Reference도 사실 이것도 **주소값을 전달하는 것**이니깐 엄밀히 따지만 call by value이다. 그러나 복사된거라 할지라도 결국 같은 객체를 가리키는 주소값이므로 실제 데이터의 값이 변경된다. 

```java
class User {
    public int age;

    public User(int age) {
        this.age = age;
    }
}

public class ReferenceTypeTest {

    @Test
    @DisplayName("Reference Type 은 주소값을 넘겨 받아서 같은 객체를 바라본다" +
                 "그래서 변경하면 원본 변수에도 영향이 있다")
    void test() {
        User a = new User(10);
        User b = new User(20);

        // Before
        assertEquals(a.age, 10);
        assertEquals(b.age, 20);

        modify(a, b);

        // After
        assertEquals(a.age, 11);
        assertEquals(b.age, 20);
    }

    private void modify(User a, User b) {
        // a, b 와 이름이 같고 같은 객체를 바라본다.
        // 하지만 test 에 있는 변수와 확실히 다른 변수다.

        // modify 의 a 와 test 의 a 는 같은 객체를 바라봐서 영향이 있음
        a.age++;

        // b 에 새로운 객체를 할당하면 가리키는 객체가 달라지고 원본에는 영향 없음
        b = new User(30);
        b.age++;
    }
}
```

1. modify()를 호출하면 a, b가 같은 객체를 바라본다.

   ![img](https://raw.githubusercontent.com/ParkJiwoon/PrivateStudy/master/java/images/screen_2022_01_30_22_50_06.png)

2. 이후 modify에서 b에 새로운 객체를 할당해주면 다른 주소를 바라보게 된다.

   ![img](https://raw.githubusercontent.com/ParkJiwoon/PrivateStudy/master/java/images/screen_2022_01_30_23_12_16.png)

3. modify가 종료되면 해당 영역의 변수들은 사라진다. User03은 나중에 GC에 의해 사라진다.

   ![img](https://raw.githubusercontent.com/ParkJiwoon/PrivateStudy/master/java/images/screen_2022_01_30_23_15_36.png)

> 가장 큰 핵심은 **호출자 변수와 수신자 파라미터는 Stack 영역 내에서 각각 존재하는 다른 변수다** 라고 생각합니다.