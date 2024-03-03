- @ResponseStatus + ResponseEntity 지우기

- 이름이 같으면 @RequestPart도 굳이

- delete는 반환값 없어도 된다.
- download는 서블릿 받으면 된다.

- 서비스에서 발생할 수 있는 Exception은 하나에서 다 처리될 수 있도록 만들어둠
- 핸들러는 나중에 내가 바꿔주고
- Errorcd -> enum으로 생성해서
- Service Exception
  - `errCode` : 에러 구분용 코드
  - `errMessage` : 에러 세부 메세지
  - `debugMessage` : 사용자에게 노출되지 않는 서버 로그 메세지
  - RuntimeException 상속을 받는다.
- `var` : 타입이 뭔지는 아는데 다 일일이 적기 귀찮을 때
- apache-commons
  - `StringUtils` 
  - `CollectionUtils`
- enum의 필요성
  - 매번 새로운 예외를 만들 필요가 없다. 필요할 때마다 메세지 추가해주면 된다.
- 핸들러에서 디버그 메세지 고객에게 보내지 않도록 설정해야됨

---

`Files` 를 처리하는것?!

- `Files.copy` `Files.delete` 는 이거 빼고 돌아가면 된다!!
- IOException 이런게 너무 많아...  => Utils로 빼기
  - 비즈니스 로직에 어울리지 않으니까



----

FileUtil은 Mocking 해놨기 때문에 없는 셈 치면 된다!!

- `MockedStatic` -> 파일 관련 Util을 신경쓰지 않아도 되도록 해놧다.
- FileUtil도 테스트를 해야한다. 
  - 따라서 `MockedStatic<File> ` 로 Files와 관련된 메서드 처리함

테스트 커버리지로 보면 어느 부분을 더 검증해야하는지 알 수 있다!

아무것도 반환하지 않으면 BDD로 검증하기 어렵다!