from collections import deque

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

N, L, R = map(int, input().split())
grid = list(list(map(int, input().split())) for _ in range(N))

confeds = []
people = []


def in_range(row, col):
    return 0 <= row < N and 0 <= col < N


def make_confederation(row, col, visited):
    visited[row][col] = True
    q = deque([(row, col)])
    confed = [(row, col)]
    total = grid[row][col]

    while q:
        r, c = q.popleft()

        for d in range(4):
            nr, nc = r + dr[d], c + dc[d]

            if in_range(nr, nc) and not visited[nr][nc] and L <= abs(grid[r][c] - grid[nr][nc]) <= R:
                visited[nr][nc] = True
                q.append((nr, nc))
                confed.append((nr, nc))
                total += grid[nr][nc]

    return confed, total


def open_borders():
    visited = [[False] * N for _ in range(N)]

    for r in range(N):
        for c in range(N):
            if not visited[r][c]:
                confed, count = make_confederation(r, c, visited)
                if len(confed) > 1:
                    confeds.append(confed)
                    people.append(count)

    return True if confeds else False


def move_people():
    total = len(people)
    for i in range(total):
        avg_people = people[i] // len(confeds[i])

        for r, c in confeds[i]:
            grid[r][c] = avg_people


def solution():
    answer = 0
    # 국경을 연 나라가 있다면
    while open_borders():
        # 사람을 이동시킨다.
        move_people()
        answer += 1
        confeds.clear()
        people.clear()

    print(answer)


solution()