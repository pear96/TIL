from collections import deque

N, K = map(int, input().split())
belt = deque(list(range(N*2)))
live = list(map(int, input().split()))
robots = list()
turn = 0


def out_robots():
    out = belt[N - 1]
    idx = 0
    cnt = len(robots)
    while idx < cnt:
        if robots[idx] == out:
            robots.pop(idx)
            cnt -= 1
        else:
            idx += 1


def move_robots():
    for idx in range(len(robots)):
        nxt = (robots[idx] + 1) % (2 * N)
        # 내구도가 0이거나 해당 위치에 로봇이 있다면 안됨
        if live[nxt] < 1 or nxt in robots:
            continue
        # 아니라면 이동
        robots[idx] = nxt
        live[nxt] -= 1
        idx += 1


while live.count(0) < K:
    # 1. 벨트 회전
    belt.rotate(1)
    out_robots()
    # 2. 로봇 이동
    move_robots()
    out_robots()
    # 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
    head = belt[0]
    if live[head] > 0:
        robots.append(head)
        live[head] -= 1

    turn += 1

print(turn)