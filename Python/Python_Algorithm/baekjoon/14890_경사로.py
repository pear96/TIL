N, L = map(int, input().split())
ground = list(list(map(int, input().split())) for _ in range(N))
answer = 0


def move_to_end(row):
    used = [False] * N
    # https://www.acmicpc.net/source/71811674
    for now in range(1, N):
        if abs(row[now] - row[now-1]) > 1:
            return 0
        if row[now] > row[now-1]:  # 상승
            # now-1 ~ now - L만큼 앞까지 경사로가 없다면
            for back in range(1, L+1):
                if now-back < 0 or used[now-back] or row[now-1] != row[now-back]:
                    return 0
                used[now-back] = True
        elif row[now] < row[now-1]:  # 하락
            for front in range(L):
                # L-1 만큼은 평탄해야한다. 이미 경사로가 설치되어 있거나, 평평하지 않다면 탈락
                if now+front >= N or used[now+front] or row[now] != row[now+front]:
                    return 0
                used[now+front] = True
    return 1


def find_route(grid):
    global answer

    for row in grid:
        if move_to_end(row):
            answer += 1


def solution():
    vertical_ground = list(map(list, zip(*ground[::-1])))

    find_route(ground)
    find_route(vertical_ground)

    print(answer)


solution()