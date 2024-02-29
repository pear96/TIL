from collections import deque

N, M, R = map(int, input().split())
short = min(N, M)
grid = list(list(map(int, input().split())) for _ in range(N))
q_grid = []  # 한 둘레를 deque로 저장한다.

# N, M 중 더 짧은 길이를 기준으로 반만큼 시작 점을 정하며 들어간다.
for start in range(short // 2):
    r, c, d = start, start, 0
    gap = 0
    line = []
    # 안쪽 테두리로 갈 수록 위, 아래 1칸씩 더 범위가 줄어든다.
    limit_row = N - 1 - start*2
    limit_col = M - 1 - start*2

    # 시계 방향으로 이동
    while gap < 2 * (limit_row + limit_col):
        line.append(grid[r][c])
        if gap < limit_row:
            r += 1
        elif gap < limit_row + limit_col:
            c += 1
        elif gap < 2 * limit_row + limit_col:
            r -= 1
        else:
            c -= 1
        gap += 1

    q_grid.append(deque(line))

q_cnt = len(q_grid)
for idx in range(q_cnt):
    # 사이클을 제거하고 남은 횟수만큼만 돌린다.
    q_grid[idx].rotate(R % len(q_grid[idx]))

    # 시계방향으로 이동하는 것은 위와 동일하다.
    r, c, d = idx, idx, 0
    gap = 0
    limit_row = N - 1 - idx * 2
    limit_col = M - 1 - idx * 2

    while gap < 2 * (limit_row + limit_col):
        grid[r][c] = q_grid[idx].popleft()
        if gap < limit_row:
            r += 1
        elif gap < limit_row + limit_col:
            c += 1
        elif gap < 2 * limit_row + limit_col:
            r -= 1
        else:
            c -= 1
        gap += 1

for line in grid:
    print(*line)
