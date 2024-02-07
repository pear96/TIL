def solution(files):
    answer = []
    results = []
    total = len(files)

    for idx in range(total):
        file_name = files[idx]
        head = ""
        number = ""
        for _char in file_name:
            if _char.isdecimal() and len(number) <= 5:
                number += _char
            else:
                if number:
                    # 여기서 number를 int형으로 바꿨더니 런타임에러 발생
                    # Tail 부분이 없을 경우 number가 숫자로 바뀌지 않고 저장됨
                    # 그럼 문자열과 숫자를 비교 정렬하게 되므로 에러 발생
                    # TypeError: '<' not supported between instances of 'str' and 'int'
                    break
                else:
                    if _char.isalpha():
                        _char = _char.lower()
                    head += _char
        results.append((head, int(number), idx))

    results.sort()
    for _, _, idx in results:
        answer.append(files[idx])

    return answer