# 동 서 북 남
dx = [0, 0, -1, 1]
dy = [1, -1, 0, 0]


def roll(d, dice):
    top = dice[1][1]

    if d == 0:
        dice[1][1] = dice[1][0]
        dice[1][0] = dice[3][1]
        dice[3][1] = dice[1][2]
        dice[1][2] = top
    elif d == 1:
        dice[1][1] = dice[1][2]
        dice[1][2] = dice[3][1]
        dice[3][1] = dice[1][0]
        dice[1][0] = top
    elif d == 2:
        back = dice[0][1]
        for i in range(3):
            dice[i][1] = dice[i+1][1]
        dice[3][1] = back
    elif d == 3:
        bottom = dice[3][1]
        for i in range(3, 0, -1):
            dice[i][1] = dice[i-1][1]
        dice[0][1] = bottom


def solution():
    N, M, x, y, K = map(int, input().split())
    grid = list(list(map(int, input().split())) for _ in range(N))
    dice = [[0, 0, 0] for _ in range(4)]
    orders = list(map(lambda i: int(i) - 1, input().split()))

    for d in orders:
        # 1. 지도 안에서 이동한 경우
        if 0 <= x + dx[d] < N and 0 <= y + dy[d] < M:
            x += dx[d]
            y += dy[d]

            # 2. 주사위를 굴린다.
            roll(d, dice)
            print(dice[1][1])

            # 3. 지도를 확인한다.
            if grid[x][y] == 0:
                grid[x][y] = dice[3][1]
            else:
                dice[3][1] = grid[x][y]
                grid[x][y] = 0

solution()