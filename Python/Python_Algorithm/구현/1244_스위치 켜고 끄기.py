"""
2024.03.06
유형 : 구현
풀이 시간 : 36분

- 정답률 낮다고 겁먹지 말았어야 함
- 그리고 limit 계산 너무 무지성으로 함
"""

import math

switch_cnt = int(input())
switches = [-1] + list(map(int, input().split()))
student_cnt = int(input())


def boy(number):
    for multiple in range(number, switch_cnt+1, number):
        switches[multiple] = (switches[multiple] + 1) % 2


def girl(number):
    # 100 - number 했다가... student_cnt - number 해서 틀리고..^^
    limit = min(number - 1, switch_cnt - number)
    switches[number] = (switches[number] + 1) % 2

    for step in range(1, limit+1):
        if switches[number - step] == switches[number + step]:
            switches[number - step] = (switches[number - step] + 1) % 2
            switches[number + step] = (switches[number + step] + 1) % 2
        else:
            break


def solution():
    for _ in range(student_cnt):
        gender, number = map(int, input().split())
        boy(number) if gender == 1 else girl(number)

    switches.pop(0)
    for row in range(int(math.ceil(switch_cnt / 20))):
        for col in range(min(20, switch_cnt - row * 20)):
            print(switches[row*20+col], end=" ")
        print()


solution()