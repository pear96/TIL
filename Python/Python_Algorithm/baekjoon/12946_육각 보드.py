import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

N = int(input())
answer = 0

dr = [-1, -1, 0, 1, 1, 0]
dc = [0, 1, 1, 0, -1, -1]

info = list(list(input().rstrip()) for _ in range(N))
color = [[-1] * N for _ in range(N)]


def in_range(r, c):
    return 0 <= r < N and 0 <= c < N


def dfs(r, c):
    global answer
    # 우선 한가지 색을 칠했다고 설정하고
    answer = max(answer, 1)

    for d in range(6):
        nr, nc = r + dr[d], c + dc[d]
        if in_range(nr, nc) and info[nr][nc] == 'X':
            # 어 얜 색이 칠해져 있어야 하는데 안되어있네?
            if color[nr][nc] == -1:
                color[nr][nc] = 1 - color[r][c]
                dfs(nr, nc)
                answer = max(answer, 2)
            else:
                # 나랑 겹치잖아
                if color[nr][nc] == color[r][c]:
                    answer = max(answer, 3)
                    return


for row in range(N):
    for col in range(N):
        if info[row][col] == 'X' and color[row][col] == -1:
            color[row][col] = 0  # 어떤 색을 칠했다.
            dfs(row, col)


print(answer)