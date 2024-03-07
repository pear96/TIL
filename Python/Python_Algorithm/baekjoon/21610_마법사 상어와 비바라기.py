"""
2024.03.07
유형 : 구현
풀이 시간 : 34분

- 옛날에 Python3 시간초과 난거보니까 구름을 배열로 관리하면 시간초과 나는 듯?
"""
N, M = map(int, input().split())
water = list(list(map(int, input().split())) for _ in range(N))
cloud = set()  # 5번에서 3번에서 사라진 구름이 아닌지 빠르게 판단하려고 set 사용

# ←, ↖, ↑, ↗, →, ↘, ↓, ↙
dr = [0, -1, -1, -1, 0, 1, 1, 1]
dc = [-1, -1, 0, 1, 1, 1, 0, -1]


def move_clouds(di, si):
    global cloud
    # 앞에서 옮겨놓고 그 구름을 또 옮겨야할 수도 있기 때문에..
    moved_cloud = set()

    for r, c in cloud:
        moved_cloud.add(((r+dr[di]*si) % N, (c+dc[di]*si) % N))

    cloud.clear()
    cloud = moved_cloud


def rain():
    for r, c in cloud:
        water[r][c] += 1


def magic():
    # 2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다.
    # 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
    # 이동과 다르게 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸이 아니다.

    for r, c in cloud:
        basket_cnt = 0

        for d in range(4):
            nr, nc = r + dr[d*2+1], c + dc[d*2+1]
            if 0 <= nr < N and 0 <= nc < N and water[nr][nc]:
                basket_cnt += 1

        water[r][c] += basket_cnt


def evaporate():
    global cloud
    # 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다.
    # 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
    new_cloud = set()

    for r in range(N):
        for c in range(N):
            if water[r][c] >= 2 and (r, c) not in cloud:
                water[r][c] -= 2
                new_cloud.add((r, c))
    cloud.clear()
    cloud = new_cloud


def get_total_water():
    total = 0

    for line in water:
        total += sum(line)

    print(total)


def solution():
    global cloud
    # 비바라기를 시전하면 (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 비구름이 생긴다.
    cloud = {(N-1, 0), (N-1, 1), (N-2, 0), (N-2, 1)}

    for _ in range(M):
        di, si = map(int, input().split())
        move_clouds(di-1, si)
        rain()
        magic()
        evaporate()
    get_total_water()


solution()
