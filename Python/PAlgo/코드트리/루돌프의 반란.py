"""
[풀이시간] 측정 불가 3~4 시간 이상
"""
from collections import deque

# 델타 배열
# 산타 - 상 우 하 좌
s_delta = [(-1, 0), (0, 1), (1, 0), (0, -1)]
# 루돌프 - 8방향(시계방향)
r_delta = [(-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1), (0, -1), (-1, -1)]

# N M P C D
GRID, TURN, SANTA_CNT, RUDOLF_POWER, SANTA_POWER = map(int, input().split())
# 루돌프 위치
rudolf_r, rudolf_c = [int(x) - 1 for x in input().split()]
# 산타 정보
santa = [[0, 0] for _ in range(SANTA_CNT+1)]  # 위치
faint = [0] * (1+SANTA_CNT)  # 기절
out = [False] * (1+SANTA_CNT)  # 탈락
score = [0] * (1+SANTA_CNT)  # 점수


def santa_init():
    for _ in range(SANTA_CNT):
        idx, row, col = map(int, input().split())
        santa[idx] = [row - 1, col - 1]


def in_range(row, col):
    return 0 <= row < GRID and 0 <= col < GRID


def get_distance(r1, c1, r2, c2):
    return (r1 - r2) ** 2 + (c1 - c2) ** 2


def get_nearest_santa():
    # 루돌프는 가장 가까운 산타를 향해 1칸 돌진합니다.
    near_santa = 0
    near_r, near_c, near_d = -1, -1, 1_000_000

    for idx in range(1, SANTA_CNT+1):
        # 단, 게임에서 탈락하지 않은 산타 중 가장 가까운 산타를 선택해야 합니다.
        if out[idx]: continue
        s_row, s_col = santa[idx]
        dist = get_distance(s_row, s_col, rudolf_r, rudolf_c)
        # 만약 가장 가까운 산타가 2명 이상이라면, r 좌표가 큰 산타를 향해 돌진합니다.
        # r이 동일한 경우, c 좌표가 큰 산타를 향해 돌진합니다.
        if (near_d, -near_r, -near_c) > (dist, -s_row, -s_col):
            near_santa = idx
            near_r, near_c, near_d = s_row, s_col, dist

    return near_santa, near_r, near_c


def move_rudolf():
    global rudolf_r, rudolf_c

    santa_idx, santa_row, santa_col = get_nearest_santa()
    near_r, near_c, near_dist = rudolf_r, rudolf_c, 1_000_000
    # 루돌프는 상하좌우, 대각선을 포함한 인접한 8방향 중 하나로 돌진할 수 있습니다.
    # (편의상 인접한 대각선 방향으로 전진하는 것도 1칸 전진하는 것이라 생각합니다.)
    for d in range(8):
        dr, dc = r_delta[d]
        row, col = rudolf_r + dr, rudolf_c + dc
        if in_range(row, col):
            dist = get_distance(santa_row, santa_col, row, col)
            # 가장 우선순위가 높은 산타를 향해 8방향 중 가장 가까워지는 방향으로 한 칸 돌진합니다.
            if dist < near_dist:
                near_dist, near_r, near_c = dist, row, col
                if near_r == santa_row and near_c == santa_col:
                    collide_rudolf_santa(1, santa_idx, d)
                    break

    rudolf_r, rudolf_c = near_r, near_c


def check_other_santa(r, c, me):
    for you in range(1, SANTA_CNT+1):
        if out[you] or you == me:
            continue
        if santa[you][0] == r and santa[you][1] == c:
            return you
    return 0


def move_santas():
    # 산타는 1번부터 P번까지 순서대로 움직입니다.
    for idx in range(1, SANTA_CNT+1):
        # 기절했거나 이미 게임에서 탈락한 산타는 움직일 수 없습니다.
        if out[idx] or faint[idx]:
            continue
        s_row, s_col = santa[idx]
        # 움직일 수 있는 칸이 없다면 산타는 움직이지 않습니다.
        move_row, move_col, move_dist = s_row, s_col, get_distance(s_row, s_col, rudolf_r, rudolf_c)

        # 산타는 루돌프에게 거리가 가장 가까워지는 방향으로 1칸 이동합니다.
        # 산타는 상하좌우로 인접한 4방향 중 한 곳으로 움직일 수 있습니다.
        # 이때 가장 가까워질 수 있는 방향이 여러 개라면, 상우하좌 우선순위에 맞춰 움직입니다.
        for d in range(4):
            dr, dc = s_delta[d]
            nr, nc = s_row + dr, s_col + dc
            dist = get_distance(nr, nc, rudolf_r, rudolf_c)
            # 산타는 다른 산타가 있는 칸이나 게임판 밖으로는 움직일 수 없습니다.
            # 움직일 수 있는 칸이 있더라도 만약 루돌프로부터 가까워질 수 있는 방법이 없다면 산타는 움직이지 않습니다.
            if in_range(nr, nc) and not check_other_santa(nr, nc, idx) and dist < move_dist:
                move_row, move_col, move_dist = nr, nc, dist
                santa[idx] = [move_row, move_col]
                if move_row == rudolf_r and move_col == rudolf_c:
                    collide_rudolf_santa(2, idx, d)
                    break


def move_in_a_row(collide, start, d):
    q = deque([start])
    o_d = 2 - d if d % 2 == 0 else 4 - d
    # 산타는 충돌 후 착지하게 되는 칸에 다른 산타가 있다면 그 산타는 1칸 해당 방향으로 밀려나게 됩니다.
    # 그 옆에 산타가 있다면 연쇄적으로 1칸씩 밀려나는 것을 반복하게 됩니다.
    # 게임판 밖으로 밀려나오게 된 산타의 경우 게임에서 탈락됩니다.
    while q:
        me = q.popleft()
        for you in range(1, SANTA_CNT+1):
            if you == me or out[you]:
                continue
            if santa[me][0] == santa[you][0] and santa[me][1] == santa[you][1]:
                santa[you][0] += s_delta[o_d][0] if collide == 2 else r_delta[d][0]
                santa[you][1] += s_delta[o_d][1] if collide == 2 else r_delta[d][1]
                if not in_range(santa[you][0], santa[you][1]):
                    out[you] = True
                else:
                    q.append(you)
                break

def wake_and_score():
    for idx in range(1, SANTA_CNT+1):
        faint[idx] -= 1 if faint[idx] > 0 else 0
        score[idx] += 1 if not out[idx] else 0


def collide_rudolf_santa(collide, santa_idx, d):
    # 루돌프가 움직여서 충돌이 일어난 경우, 해당 산타는 C만큼의 점수를 얻게 됩니다.
    # 이와 동시에 산타는 루돌프가 이동해온 방향으로 C 칸 만큼 밀려나게 됩니다.
    if collide == 1:
        score[santa_idx] += RUDOLF_POWER
        santa[santa_idx][0] += r_delta[d][0] * RUDOLF_POWER
        santa[santa_idx][1] += r_delta[d][1] * RUDOLF_POWER
    # 산타가 움직여서 충돌이 일어난 경우, 해당 산타는 D만큼의 점수를 얻게 됩니다.
    # 이와 동시에 산타는 자신이 이동해온 반대 방향으로 D 칸 만큼 밀려나게 됩니다.
    elif collide == 2:
        score[santa_idx] += SANTA_POWER
        o_d = 2 - d if d % 2 == 0 else 4 - d
        santa[santa_idx][0] += s_delta[o_d][0] * SANTA_POWER
        santa[santa_idx][1] += s_delta[o_d][1] * SANTA_POWER
    # 밀려나는 것은 포물선 모양을 그리며 밀려나는 것이기 때문에 이동하는 도중에 충돌이 일어나지는 않고 정확히 원하는 위치에 도달하게 됩니다.
    # 만약 밀려난 위치가 게임판 밖이라면 산타는 게임에서 탈락됩니다.
    if not in_range(santa[santa_idx][0], santa[santa_idx][1]):
        out[santa_idx] = True
    else:
        # 만약 밀려난 칸에 다른 산타가 있는 경우 상호작용이 발생합니다.
        move_in_a_row(collide, santa_idx, d)
    # 기절
    faint[santa_idx] = 2



def solution():
    santa_init()
    for _ in range(TURN):
        # 1. 루돌프 움직임
        move_rudolf()
        # 2. 산타가 순서대로 움직임
        move_santas()
        # 3. 탈락하지 않은 산타에겐 점수를, 기절한 산타는 남은 시간 1 줄이기
        wake_and_score()

    print(*score[1:])

solution()