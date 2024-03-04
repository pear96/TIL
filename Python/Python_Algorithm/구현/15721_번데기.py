"""
2024.03.04
유형 : 구현, 완탐
풀이 시간 : 1시간?

- 코드 예쁘게 짜는걸 포기하면 오히려 빨리 풀 수 있다...
"""
A = int(input())
T = int(input())
guho = int(input())


def solution():
    bbeon = 0
    degi = 0
    idx = 0
    turn = 1

    while True:
        # 뻔 데기 뻔 데기
        for _ in range(2):
            bbeon += 1
            if guho == 0 and T == bbeon:
                return idx
            idx += 1

            degi += 1
            if guho == 1 and T == degi:
                return idx
            idx += 1

        # 뻔 * n
        for _ in range(turn+1):
            bbeon += 1
            if guho == 0 and T == bbeon:
                return idx
            idx += 1

        # 데기 * n
        for _ in range(turn+1):
            degi += 1
            if guho == 1 and T == degi:
                return idx
            idx += 1
        turn += 1

print(solution()%A)