from collections import deque

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

grid = list(list(input()) for _ in range(12))


def in_range(r, c):
    return 0 <= r < 12 and 0 <= c < 6


def bomb():
    visit = [[False] * 6 for _ in range(12)]
    explode = False

    for row in range(12):
        for col in range(6):
            if grid[row][col] != "." and not visit[row][col]:
                visit[row][col] = True
                q = deque([(row, col)])
                group = [(row, col)]

                while q:
                    r, c = q.popleft()
                    for d in range(4):
                        nr = r + dr[d]
                        nc = c + dc[d]

                        if in_range(nr, nc) and not visit[nr][nc] and grid[nr][nc] == grid[r][c]:
                            visit[nr][nc] = True
                            q.append((nr, nc))
                            group.append((nr, nc))

                if len(group) >= 4:
                    explode = True
                    for row, col in group:
                        grid[row][col] = '.'

    return explode


def gravity():
    for col in range(6):
        blank = 0
        for row in range(11, -1, -1):
            if grid[row][col] == '.':
                blank += 1
            elif blank:
                grid[row+blank][col] = grid[row][col]
                grid[row][col] = '.'


def solution():
    combo = 0

    while bomb():
        combo += 1
        gravity()

    print(combo)

solution()