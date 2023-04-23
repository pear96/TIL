# `Integer` VS `int`

Java에서 int와 Integer는 모두 정수를 나타내는 데이터 타입입니다. 하지만 이 둘은 몇 가지 차이점이 있습니다.

int는 기본 데이터 타입(primitive data type)이며, 32비트 정수를 나타냅니다. int는 메모리를 덜 차지하고, 처리 속도가 빠릅니다. int는 null 값을 가질 수 없고, 객체가 아니므로 메서드를 호출할 수 없습니다.

Integer는 객체 래퍼 클래스(wrapper class) 중 하나로, int의 값을 객체로 래핑합니다. Integer는 null 값을 가질 수 있고, 객체이므로 메서드를 호출할 수 있습니다. 또한 int 값을 다루는 다양한 유틸리티 메서드도 제공합니다. 하지만 int보다 더 많은 메모리를 차지하고 처리 속도가 느립니다.

일반적으로 int는 원시값을 저장하고 계산할 때 사용하며, Integer는 컬렉션 등 객체를 다룰 때 사용됩니다. 또한 메서드의 매개변수나 반환값으로 null 값을 허용해야 할 경우 Integer를 사용할 수 있습니다.