delta = [(0, 1), (-1, 0), (0, -1), (1, 0)]  # 시계방향
N, M, T = map(int, input().split())
room = list(list(map(int, input().split())) for _ in range(N))
cleaner = []


def get_cleaner():
    for r in range(N):
        if room[r][0] == -1:
            cleaner.append((r, 0))
            cleaner.append((r + 1, 0))
            return


def in_range(row, col):
    return 0 <= row < N and 0 <= col < M


def spread_dust():
    global room
    spread = [[0] * M for _ in range(N)]

    for r in range(N):
        for c in range(M):
            if room[r][c] == -1:
                spread[r][c] = -1
            elif room[r][c] > 0:
                cnt = 0
                # 4 방향으로 뿌려보고
                for dr, dc in delta:
                    if in_range(r + dr, c + dc) and room[r+dr][c+dc] != -1:
                        spread[r+dr][c+dc] += room[r][c] // 5
                        cnt += 1
                # 뿌린 만큼 빠진다.
                spread[r][c] += room[r][c] - (room[r][c] // 5) * cnt

    room = spread


def move_dust(air_row, air_col, t, arr):
    d = 0
    row, col = air_row + delta[d][0], air_col + delta[d][1]
    arr[row][col] = 0

    # 공기 청정기로 돌아오면 끝
    while row != air_row or col != air_col:
        if in_range(row + delta[d][0], col + delta[d][1]):
            # 다음 위치에 지금 위치의 먼지 복사해놓고 위치 옮기기
            arr[row+delta[d][0]][col+delta[d][1]] = room[row][col]
            row += delta[d][0]
            col += delta[d][1]
        else:
            # 범위 안에 있지 않으면 방향을 회전해야 할 때임
            # t가 0이면 위쪽, 1이면 아래쪽이다. 0 : 0 1 2 3 / 1 : 0 3 2 1
            if t == 0:
                d = (d + 1) % 4
            else:
                d = (d - 1) % 4
    arr[air_row][air_col] = -1


def wind():
    global room
    moved = [[*room[i]] for i in range(N)]
    move_dust(*cleaner[0], 0, moved)  # 위쪽
    move_dust(*cleaner[1], -1, moved)  # 아래쪽
    room = moved


def get_answer():
    answer = 0

    for row in room:
        for dust in row:
            if dust > 0:
                answer += dust

    return answer


def solution():
    get_cleaner()
    for _ in range(T):
        spread_dust()
        wind()

    print(get_answer())

solution()