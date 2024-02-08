from collections import deque

ROW, COL = map(int, input().split())
plate = list(list(map(int, input().split())) for _ in range(ROW))

melted = [[False] * COL for _ in range(ROW)]


def in_range(r, c):
    return 0 <= r < ROW and 0 <= c < COL


def cheese_with_air():
    q = deque([(0, 0)])
    visited = [[False] * COL for _ in range(ROW)]
    visited[0][0] = True

    while q:
        r, c = q.popleft()

        for dr, dc in ((-1, 0), (1, 0), (0, -1), (0, 1)):
            nr, nc = r + dr, c + dc
            if in_range(nr, nc) and not visited[nr][nc]:
                visited[nr][nc] = True
                if plate[nr][nc] == 0:
                    q.append((nr, nc))
                else:
                    melted[nr][nc] = True


def melting_cheese():
    cheese_cnt = 0

    for r in range(ROW):
        for c in range(COL):
            if plate[r][c]:
                if melted[r][c]:
                    plate[r][c] = 0
                else:
                    cheese_cnt += 1

    return cheese_cnt


def solution():
    # 1시간만에 전부 녹아버릴 수 있으니(치즈가 1칸일 경우) 처음에 총 치즈 칸의 개수를 구해야만 한다.
    total_cheese, melt_time = sum(sum(plate[i]) for i in range(ROW)), 0

    while True:
        cheese_with_air()
        left_cheese = melting_cheese()
        melt_time += 1

        if left_cheese == 0:
            print(melt_time)
            print(total_cheese)
            break
        total_cheese = left_cheese


solution()

