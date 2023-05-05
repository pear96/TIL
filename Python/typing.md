# [typing](https://docs.python.org/ko/3/library/typing.html)

- 3.5버전 부터 추가됨
- 파이썬 런타임은 함수와 변수 형 어노테이션을 강제하지 않습니다.
- 이 모듈은 type에 관한 힌트를 런타임 때 제공합니다.
- [`Any`](https://docs.python.org/ko/3/library/typing.html#typing.Any), [`Union`](https://docs.python.org/ko/3/library/typing.html#typing.Union), [`Callable`](https://docs.python.org/ko/3/library/typing.html#typing.Callable), [`TypeVar`](https://docs.python.org/ko/3/library/typing.html#typing.TypeVar), [`Generic`](https://docs.python.org/ko/3/library/typing.html#typing.Generic)도 포함하여 type에 관한 근본적인 지원을 제공합니다.
  - 자세한 내용 : https://peps.python.org/pep-0484/
  - 요약 : https://peps.python.org/pep-0483/

- 사용 예제

```python
from typing import List, Tuple, Dict, Set, Optional

# int 타입의 요소를 갖는 리스트 변수 예제
numbers: List[int] = [1, 2, 3, 4, 5]

# str과 int 타입의 요소를 갖는 튜플 변수 예제
person: Tuple[str, int] = ('Alice', 30)

# str 타입의 키와 bool 타입의 값을 갖는 딕셔너리 변수 예제
settings: Dict[str, bool] = {'debug': True, 'verbose': False}

# float 타입의 요소를 갖는 셋 변수 예제
grades: Set[float] = {3.5, 4.0, 4.5, 5.0}

# str 또는 None 타입의 값을 가질 수 있는 Optional 변수 예제
username: Optional[str] = None

# str을 인자로 받고, str을 반환하는 어노테이션 적용
def greeting(name: str) -> str:
    return 'Hello ' + name
```

- 변수명 옆의 `:` 는 Python의 **변수 타입 어노테이션 문법**입니다. 이 문법은 변수에 할당되는 값의 타입을 명시하는 것으로, 코드의 가독성을 높이고, 타입 체크 및 코드 검증 도구의 도움을 받을 수 있습니다.
- `->`는 Python의 함수 어노테이션(function annotation) 문법 중 하나입니다. 함수 어노테이션은 함수의 매개변수(parameter)와 반환값(return value)의 타입을 명시하는 문법입니다. 즉, `->`는 **함수의 반환값의 타입을 명시**하는데 사용됩니다.
- 위 코드에서 `greeting` 함수는 `name` 매개변수를 받아서 문자열(str) `'Hello '`와 `name`을 연결한 문자열(str)을 반환합니다. 그리고 함수 선언문 뒤에 `->` 기호를 사용하여 반환값의 타입을 명시하였습니다. 따라서 `greeting` 함수는 `str` 타입의 값을 반환합니다.
- 함수 어노테이션은 코드를 더욱 가독성 있게 만들어주며, 타입 체크 및 코드 검증 도구의 도움을 받을 수 있습니다. 그러나 함수 어노테이션은 선택적이므로, 생략해도 무방합니다.



### [cheat sheet](https://mypy.readthedocs.io/en/stable/cheat_sheet_py3.html)