class MyClass:
    def __init__(self, value=0):
        self._num = 0  # 실제 값을 저장하는 변수
        self.num = value  # 프로퍼티에 값을 할당하여 조건 적용

    @property
    def num(self):
        return self._num

    @num.setter
    def num(self, value):
        # 여기서 조건을 설정하여 원하는 경우에만 값이 변경되도록 합니다.
        if value >= 0:  # 예시: 값이 0 이상일 때만 허용
            self._num = value
        else:
            print("조건에 맞지 않습니다.")


# 클래스 사용 예시
obj = MyClass(10)  # 초기화 시에도 조건 적용
print(obj.num)  # 10 출력

obj = MyClass(-5)  # 초기화 시에 조건에 맞지 않으므로 변경되지 않음, 메시지 출력
print(obj.num)  # 여전히 0 출력