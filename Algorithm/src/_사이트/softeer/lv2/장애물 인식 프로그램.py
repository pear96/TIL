"""
BFS
"""
from collections import deque

import sys

input = sys.stdin.readline

N = int(input())

grid = list(list(input()) for _ in range(N))
visited = list([0] * N for _ in range(N))

dr = [1, -1, 0, 0]
dc = [0, 0, -1, 1]

blocks = []


def in_range(r, c):
    return 0 <= r < N and 0 <= c < N


for r in range(N):
    for c in range(N):
        if grid[r][c] == "1" and not visited[r][c]:
            cnt = 1
            visited[r][c] = 1

            q = deque()
            q.append((r, c))

            while q:
                now_r, now_c = q.popleft()

                for d in range(4):
                    next_r, next_c = now_r + dr[d], now_c + dc[d]

                    if not in_range(next_r, next_c):
                        continue
                    if visited[next_r][next_c] or grid[next_r][next_c] == "0":
                        continue

                    visited[next_r][next_c] = 1
                    cnt += 1
                    q.append((next_r, next_c))

            blocks.append(cnt)

blocks.sort()

print(len(blocks))
for block in blocks:
    print(block)