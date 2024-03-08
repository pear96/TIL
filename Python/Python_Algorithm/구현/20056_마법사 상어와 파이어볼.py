from collections import defaultdict
import math

N, M, K = map(int, input().split())
fire = defaultdict(list)

dr = [-1, -1, 0, 1, 1, 1, 0, -1]
dc = [0, 1, 1, 1, 0, -1, -1, -1]


def move_all_fire():
    global fire
    moved_fire = defaultdict(list)

    for rc, fires in fire.items():
        r, c = rc
        for m, s, d in fires:
            nr, nc = (r + dr[d]*s) % N, (c + dc[d]*s) % N
            moved_fire[(nr, nc)].append((m, s, d))

    fire = moved_fire


def combine_fire():
    disappear = set()
    for rc, fires in fire.items():
        r, c = rc
        fire_cnt = len(fires)
        if fire_cnt >= 2:
            total_mass = 0
            total_speed = 0
            dir_even = 0
            dir_odd = 0

            for m, s, d in fires:
                total_mass += m
                total_speed += s
                if d % 2 == 0:
                    dir_even += 1
                else:
                    dir_odd += 1

            # 질량이 0인 파이어볼은 소멸되어 없어진다.
            new_mass = math.floor(total_mass / 5)
            if new_mass > 0:
                new_speed = math.floor(total_speed / fire_cnt)
                # 파이어볼은 4개의 파이어볼로 나누어진다.
                if fire_cnt == dir_even or fire_cnt == dir_odd:
                    # 합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 방향은 0, 2, 4, 6이 되고
                    fire[(r, c)] = [(new_mass, new_speed, new_dir*2) for new_dir in range(4)]
                else:
                    # 그렇지 않으면 1, 3, 5, 7이 된다.
                    fire[(r, c)] = [(new_mass, new_speed, new_dir * 2 + 1) for new_dir in range(4)]
            else:
                disappear.add((r, c))

    # 이렇게 안하면 순회중에 dict 바꿨다고 오류남
    for r, c in disappear:
        fire.pop((r, c))


def get_fire_mass():
    answer = 0
    for fires in fire.values():
        for mass, _, _ in fires:
            answer += mass

    print(answer)


def solution():
    for _ in range(M):
        r, c, m, s, d = map(int, input().split())
        fire[(r-1, c-1)].append((m, s, d))

    for _ in range(K):
        move_all_fire()
        combine_fire()

    get_fire_mass()

solution()