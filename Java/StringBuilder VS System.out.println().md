## `StringBuilder` VS `System.out.println()`



다른 제출기록에 비해 시간이 너무 오래걸려서 비교하다가 System.out.println에서 StringBuilder를 써보니 시간이 1/3으로 줄었다.

현업에서는 콘솔에 출력할 일이 없으니 알고리즘 문제에서 이걸로 시간 차이가 나는 것에 큰 의미를 두고싶진 않지만, 그래도 공부해보자.



#### [`System.out.println()`](https://docs.oracle.com/javase/8/docs/api/java/lang/System.html)

-   Class : `System`
-   시스템 클래스에서 제공하는 시설 중 표준 입력, 표준 출력 및 오류 출력 스트림
-   외부적으로 정의된 속성 및 환경에 대한 액세스 변수, 파일 및 라이브러리 로드 수단 및 유틸리티 배열의 일부를 빠르게 복사하는 방법
-   `System.out` 은 `PrintStream`의 클래스에 접근한다.
-   `java.io.PrintStream` 라는 클래스 안에 `println()` 함수가 정의되어 있다.
-   `println()` 함수는 문자열의 characters를 bytes로 변환하고 출력되는데, 이는 `write(int b)` 함수의 방식과 정확히 일치한다.



### [StringBuffer](https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuffer.html) VS [StringBuilder](https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuilder.html)  

-   공통점 : 둘 다 문자열을 조작할 수 있다.(mutable)
-   차이점 : StringBuffer는 **동기화**를 지원하고, StringBuilder는 지원하지 않는다.
    -   StringBuffer는 동기화가 지원되는 thread-safe 클래스이며, 멀티 스레드 환경에서 안전하게 사용할 수 있다. 따라서 여러 스레드에서 동시에 접근하여 데이터를 변경할 수 있는 상황에서도 안전하게 사용 가능하다. 함수에 `synchronized` 키워드를 사용한다.
        -   공식 문서) StringBuffer는 여러 스레드에서 안전하게 사용할 수 있도록 설계되었지만, 생성자 또는 append 또는 insert 작업에 여러 스레드에서 공유하는 소스 시퀀스가 전달되는 경우, 해당 작업이 수행되는 동안 일관되고 변경되지 않는 소스 시퀀스를 보장해야 합니다. 이를 위해서는 호출하는 코드가 작업의 호출 동안 **잠금**을 보유하거나, **불변의 소스 시퀀스**를 사용하거나, 스레드 간에 소스 시퀀스를 **공유하지 않도록** 해야 합니다.
        -   JDK 5에서는 이 클래스의 대안으로 단일 스레드에서 사용하기 위해 설계된 동등한 클래스인 StringBuilder가 추가되었습니다. StringBuilder 클래스는 모든 동일한 작업을 지원하지만 동기화를 수행하지 않기 때문에 보통 이 클래스보다 더 빠르게 동작합니다. 따라서 이 클래스 대신 StringBuilder 클래스를 사용하는 것이 좋습니다.

    -   StringBuilder는 동기화를 지원하지 않는 클래스이다. 따라서 멀티스레드 환경에서 오류가 발생할 수 있다. 하지만 단일 클래스에서는 빠르게 동작한다.
        -   `StringBuffer`의 drop-in-replacement가 `StringBuilder`이다.
        -   drop-in-replacement :  

    -   즉, 동기화가 속도에 차이를 주는 이유는 동기화를 구현하는 작업은 **오버헤드**를 발생시킬 가능성이 있기 때문이다. 동기화를 구현하려면 thread간의 `lock`을 걸거나 메모리를 추가로 사용해야하고, 오버헤드가 클 수록 작업이 느리게 수행되므로 속도에 차이를 주게 되는 것이다.




+) String 관련 공부하기 좋은 내용 : https://blog.neonkid.xyz/286



### `System.out.println()`이 `StringBuilder`보다 느린 이유



`System.out.println()`은 느린 것이 아니라, 콘솔에 연결된 `PrintStream`이 느린 것입니다. 이는 호스팅 운영 체제에서 제공하는 콘솔과 연결되어 있기 때문입니다. `System.out.println()`은 문자열을 출력하기 위해 운영 체제의 콘솔 응용 프로그램으로 바이트를 전송해야 하고, 각 문자를 트루 타입 글꼴을 사용하여 렌더링해야 하며, 새 줄을 보이는 영역에 추가하기 위해 표시 영역을 스크롤해야 하는 등의 작업을 수행해야 합니다.

 즉, `System.out.println()`은 출력 스트림 작업을 위해 `StringBuilder`를 사용하지만, 매번 새로운 `StringBuilder` 객체를 생성합니다. 이렇게 되면 `StringBuilder` 객체가 생성될 때마다 메모리 할당 및 초기화 작업이 수행되기 때문에 오버헤드가 발생합니다. 



반면 `StringBuilder`는 문자열 조작을 위한 클래스로, 내부적으로 문자 배열을 사용하여 문자열 조작 작업을 수행합니다. `StringBuilder`의 `append()` 메서드를 사용하면 내부적으로 여러 작업이 수행되지만, 이는 새로운 문자열 인스턴스를 생성하는 것보다 효율적입니다. 그래서 `StringBuilder`는 재사용 가능한 객체입니다. 즉, `StringBuilder` 객체가 생성될 때마다 새로운 메모리를 할당할 필요가 없으므로 오버헤드가 발생하지 않습니다. 



따라서 `System.out.println()`은 `StringBuilder`보다 느린 이유는 콘솔 출력 작업이 느리기 때문입니다.



+) 참고로 이때까지 써왔던 `InputStreamReader` 또한 `java.io.InputStreamReader`에 있는데, 이 클래스는 byte를 character로 변환해준다. 문자로 알아채는 특정한 charset을 갖고있다. 효율성을 위해 `BufferedReader`로 감싸주는것이 좋다고 쓰여있다.



+) 그럼 `BufferedReader`는 무엇인가? (이런 ㅁㅊ 개미지옥같으니)

-   `Reader` 클래스를 상속한다.
-   문자 입력 스트림에서 텍스트를 읽고 문자를 버퍼링하여 문자, 배열 및 선을 효율적으로 읽을 수 있도록 제공합니다.
-   버퍼 크기를 지정하거나 기본 크기를 사용할 수 있습니다.기본값은 대부분의 용도에 충분히 큽니다.
-   `FileReaders`나 `InputStreamReader`를 사용할 땐 BuffedReader로 감싸는게 좋다고 쓰여있다.
-   버퍼를 사용하지 않으면,`read()`, `readLine()` 함수를 사용할 때마다 파일에서 바이트를 읽고, 문자로 바꿔서 반환하는 과정이 일어나기 때문이라고 한다.