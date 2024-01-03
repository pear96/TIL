https://tecoble.techcourse.co.kr/post/2021-10-23-java-synchronize/

https://steady-coding.tistory.com/556

https://jeong-pro.tistory.com/227

# Synchronization 개념

- synchronization을 쓰는 이유 :  하나의 자원에 하나의 스레드만 접근할 수 있도록 하기 위해서
- 한 스레드가 점유하고 있는 블럭은 반납할 때까지 다른 스레드들이 접근할 수 없다.
- `synchronized` 키워드를 사용한다.
- **모니터** 또는 **락**으로 구현된다. 하나의 쓰레드만이 모니터를 가질 수 있다. 스레드가 락을 요구하면, 모니터에 입장해야한다. 잠겨진 모니터에 접근하려는 모든 다른 스레드들은 대기해야한다.

# Synchronization 종류

자바에선 2개의 동기화가 존재한다.

1. 프로세스 동기화
2. 스레드 동기화
   1. Mutual Exclusive : 데이터를 공유하는 동안 스레드가 서로 간섭하지 않도록 도와줍니다
      1. Synchronized method => 메서드에 붙일 수 있다.
      2. Synchronized block => 아무 블락에 붙일 수 있다.
      3. Static synchronization => ==클래스 락== 을 얻는 방법
   2. Cooperation(Inter-thread communication in Java)



## Class Lock VS Instance Lock

GPT와의 무수한 대화 끝에 겨우 조금이나마 알게 되었다.

우선 JVM에선 클래스 정보와 인스턴스의 정보가 다른 곳에 저장된다는 것을 알 것이다.

- Method Area : ==클래스==, ==메서드==, static 변수 등의 정보를 저장. 클래스 로더에 의해 로드된 클래스 정보 저장
- Heap : ==객체 인스턴스==, ==배열== 저장. `new` 키워드로 생성된 객체들
- Stack : ==메서드 호출과 관련된 정보==. 각 스레드마다 자신만의 스택이 존재하며, ==지역 변수==, ==메서드 호출에 필요한 데이터== 저장.

=> 이로 인해 클래스락과 인스턴스 락은 다른 메모리 영역에 대해 관여하기 때문에 **==독립적으로 동작한다!==**

| 클래스 락                                                    | 인스턴스 락                                                  |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| 클래스의 메타데이터 정보를 메소드 영역(Method Area)에 저장되므로 클래스 자체에 의해 관리됨. | 힙 영역(Heap)에 할당된 인스턴스를 사용하는 스레드들 간에만 공유 |
| **클래스 레벨의 synchronized 블록**이나 **static synchronized 메서드**는 클래스 락을 사용하여 해당 클래스의 동기화를 담당 | **인스턴스 레벨의 synchronized 메서드**나 **synchronized 블록**은 인스턴스 레벨의 락을 사용하여 해당 인스턴스의 동기화를 담당 |
| 모든 인스턴스가 해당 클래스의 클래스 락 공유                 | 인스턴스 마다 인스턴스 락을 따로 관리                        |
| 클래스 레벨의 락은 클래스 객체를 조작하여 정적 변수에 접근합니다. | 객체 내부의 상태를 보호하거나 메서드들 간에 상호 배제를 달성하는 데 사용 |



따라서 클래스 레벨의 락과 인스턴스 레벨의 락은 JVM의 메모리 구조에서 각각 다른 공간에 저장되고 관리되기 때문에 독립적으로 동작할 수 있는 것입니다. 클래스 레벨의 락은 클래스의 메타데이터 영역에 저장되고, 인스턴스 레벨의 락은 객체 인스턴스가 할당된 힙 영역에 저장되어 있기 때문에, 서로 영향을 주지 않고 별도로 동작합니다.

```java
package practice;

public class StaticFunction {

	private static String HERO;

	public static void main(String[] agrs) {
		System.out.println("Test start!");
		new Thread(() -> {
			for (int i = 0; i < 1000000; i++) {
				StaticFunction.batman();
			}
		}).start();

		new Thread(() -> {
			for (int i = 0; i < 1000000; i++) {
				StaticFunction.superman();
			}
		}).start();

		StaticFunction sfunction = new StaticFunction();
		new Thread(() -> {
			for (int i = 0; i < 1000000; i++) {
				sfunction.ironman();
			}
		}).start();
		System.out.println("Test end!");

	}
	
	public static synchronized void batman() {
		HERO = "batman";

		try {
			long sleep = (long) (Math.random() * 100);
			Thread.sleep(sleep);
			if ("batman".equals(HERO) == false) {
				System.out.println("synchronization broken - batman");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static synchronized void superman() {
		HERO = "superman";

		try {
			long sleep = (long) (Math.random() * 100);
			Thread.sleep(sleep);
			if ("superman".equals(HERO) == false) {
				System.out.println("synchronization broken - superman");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void ironman() {
		HERO = "ironman";

		try {
			long sleep = (long) (Math.random() * 100);
			Thread.sleep(sleep);
			if ("ironman".equals(HERO) == false) {
				System.out.println("synchronization broken - ironman");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
// 출처: https://tourspace.tistory.com/55 [투덜이의 리얼 블로그:티스토리]
```

- `batman()`, `superman()` : `static synchronized` => 클래스 락을 획득한다. A 스레드가 클래스 락을 획득했다면 B 스레드는 A 스레드가 클래스 락을 해제할 때까지 기다려야 한다.
- `ironman()` : `synchronized` => 인스턴스 락을 획득한다. `sfunction` 이라는 인스턴스에 대한 락을 가지며, 늦게 실행된 다른 스레드가 `sfunction`이라는 인스턴스의 락을 획득하려면 기다려야한다.

**참고 사항**

인스턴스 락을 획득하고서는 클래스 변수를 수정할 수 있는가? 그렇다! ironman()은 클래스 락이 아닌 인스턴스 락 레벨에 관여하기 때문에 클래스 락이 걸려있든 말든 신경도 안쓰고, 그냥 마음대로 바꿔버릴 수 있다.

그러나 인스턴스 락만 획득하고서는 클래스 변수를 수정하면, 다른 스레드에게 전부 영향이 가므로 클래스 락을 획득한 다른 스레드가 보는 클래스 변수마저 바꿔버릴 수 있다.

작동은 하지만, 바람직하지 않다. 그래서 클래스 변수를 수정하고 싶다면 클래스 락을 획득 하는게 옳다. 라는 것이다.

또한 여러개의 인스턴스를 만드는 경우에는 인스턴스 락만 획득하면 여전히 클래스 변수를 멋대로 바꿔버릴 수 있다. 클래스 변수는 인스턴스 영역에 저장된게 아니라 Method Area에 저장되어있기 때문에, 락이 안걸려있는거나 마찬가지이기 때문이다.

# Monitor

- 모니터는 데이터 접근 및 동기화를 위한 높은 수준의 추상화를 제공해서 프로세스 동기화를 단순하게 한다.
- 단일 구성에서 상호 배제, 조건 변수, 데이터 캡슐화를 제공한다.
- 공유 자원을 캡슐화 하고, 해당 자원에 대한 접근을 제공하는 모듈이다. 특정 시간에 하나의 프로세스(스레드)만 공유 자원에 접근할 수 있도록 보장하고, 자원이 사용 가능해질 때 까지 대기중인 프로세스들은 중단된다.
- 세부 정보를 숨기는 추상화를 제공하며 동시 프로그램의 구현을 단순화한다. 세마포어 및 락(Lock)과 같은 복잡한 값이 필요하지 않다.
- 모니터의 장점은 복잡한 동시 시스템을 간단하게 구현하면서 높은 수준의 추상화를 제공한다는 점이다. 
  - 세마포어를 사용하는 것 보다 적은 에러를 발생시키고 병렬 프로그래밍에 적합하다.
- 모니터의 단점은 컴파일러가 멀티 프로세스 환경에서 임계 영역에 대한 접근을 관리하기 위해 운영체제에서 어떤 기능을 사용할 수 있는지 알아야한다는 점이다. 
- 그러나 세마포어, 락과 같은 하위 수준의 동기화보다 높은 추상화로 인해 오버헤드가 많이 발생할 수 있다.
  - 모든 동기화 문제에 적용할 수 있는 것은 아니며, 하위 단계의 추상화가 더 적절할 수 있다.
- ==자바의 모든 인스턴스는 Monitor 를 가지고 있으며(Object 내부) Monitor 를 통해 Thread 동기화를 수행한다.== 
- 프로세스간의 상호 배제를 위해 프로그래밍 언어에서 제공되며 Java에서는 ` synchronized` 가 있다.
  1. 특정한 모듈, 패키지안에 통합된 조건 변수 또는 절차이다.
  2. 모니터 외부에서 실행되는 프로세스는 모니터 내부 변수에 접근할 수 없지만, 모니터의 procedures를 호출할 수 있다.
  3. 모니터 내부에서는 한번에 하나의 프로세스만 코드를 실행할 수 있다.

![monitors](https://media.geeksforgeeks.org/wp-content/cdn-uploads/gq/2015/06/monitors-300x255.png)

## Readers-Writers Problem





# Volatile



# Atomic Class