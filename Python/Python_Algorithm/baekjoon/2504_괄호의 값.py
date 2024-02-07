# https://www.acmicpc.net/source/72892469

brackets = list(input())

mul = 1  # 이제까지 괄호의 누적으로 곱할 값 ex) ([ => *2*6
answer = 0  # mul * (2 or 3) 들을 더해온 값
stack = []  # 올바른 괄호열인지 판단하기 위한 스택


for i in range(len(brackets)):
    if brackets[i] == "(":
        mul *= 2
        stack.append(brackets[i])
    elif brackets[i] == "[":
        mul *= 3
        stack.append(brackets[i])
    elif brackets[i] == ")":
        if not stack or stack[-1] != "(":  # 올바르지 않은 괄호 문자열
            answer = 0
            break
        if brackets[i-1] == "(":  # () 이기 때문에 이제까지 곱해온 값 더해준다.
            answer += mul
        # 이제 곱하기를 한 단계 끈다.
        mul //= 2
        stack.pop()  # 여는 괄호도 빼준다.
    else:
        if not stack or stack[-1] != "[":  # ")"와 동일
            answer = 0
            break
        if brackets[i-1] == "[":
            answer += mul
        mul //= 3
        stack.pop()

print(answer)