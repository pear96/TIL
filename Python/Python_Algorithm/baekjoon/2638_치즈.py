from collections import deque


N, M = map(int, input().split())
cheese = list(list(map(int, input().split())) for _ in range(N))
left_cheese_cnt = sum(sum(cheese[i]) for i in range(N))  # 초기 치즈 개수
air_meet = [[0] * M for _ in range(N)]  # 치즈가 공기에 얼마나 노출 되었는지
melt_time = 0


def air_spread():
    q = deque([(0, 0)])
    visited = [[False] * M for _ in range(N)]
    visited[0][0] = True

    while q:
        r, c = q.popleft()

        for dr, dc in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
            nr, nc = r + dr, c + dc
            if 0 <= nr < N and 0 <= nc < M and not visited[nr][nc]:
                if cheese[nr][nc]:
                    air_meet[nr][nc] += 1
                else:
                    visited[nr][nc] = True
                    q.append((nr, nc))


def melt_cheese():
    global left_cheese_cnt
    for r in range(N):
        for c in range(M):
            if cheese[r][c]:
                if air_meet[r][c] >= 2:
                    cheese[r][c] = 0
                    left_cheese_cnt -= 1
                else:
                    air_meet[r][c] = 0


def solution():
    global melt_time

    while left_cheese_cnt:
        air_spread()
        # for l in air_meet:
        #     print(*l)
        melt_cheese()
        # print()
        # for l in cheese:
        #     print(*l)
        melt_time += 1
        # print(f"{melt_time} 시간이 걸리는 중~~")


    print(melt_time)

solution()