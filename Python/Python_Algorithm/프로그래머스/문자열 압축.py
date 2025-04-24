# 1000 ^ 2 완탐?

def solution(s):
    answer = s
    length = len(s)

    for l in range(1, length + 1):
        print()
        print(f"이번 길이 : {l}")
        result = ''
        # prv = s[:l]
        # print(f"{l}일때 prv : {prv}")
        count = 1
        # 길이 8, 3씩 보면
        # 012, 345, 678, 9<- 안돼!
        # 8 //3 => 2
        # 0, 1
        for i in range(0, length // l):
            now = s[l * i:l * (i + 1)]
            nxt = s[l * (i + 1):l * (i + 2)]
            print(f"now : {now}, nxt: {nxt}")
            if now == nxt:
                print("똑같다.")
                count += 1
            else:
                if count > 1:
                    result += f'{count}'
                result += now
                count = 1
                print(f"다르네 : {result}")
        if length % l:
            print(s[-(length % l):])
            result += s[-(length % l):]

        print(f"최종 결과 : {result}")
        if len(answer) > len(result):
            answer = result

    return len(answer)



print(solution("abcabcdede"))