"""
https://www.acmicpc.net/board/view/83783
"""
start = input()
goal = input()

while goal != "" and start != goal:
    if goal[-1] == "A":
        goal = goal[:-1]
    elif goal[-1] == "B":
        goal = goal[:-1]
        goal = goal[::-1]

print(1 if start == goal else 0)
