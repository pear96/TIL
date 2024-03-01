N = int(input())
fav = [set() for _ in range(N*N+1)]
order = []
seats = [[0] * N for _ in range(N)]


def get_fav_info():
    """
    좋아하는 친구 목록을 set으로 저장해서 추후에 `in` 연산을 빠르게 할 수 있도록 한다.
    """
    for _ in range(N*N):
        info = list(map(int, input().split()))
        num = info[0]
        fav[num] = set(info[1:])
        order.append(num)


def in_range(r, c):
    return 0 <= r < N and 0 <= c < N


def set_seat():
    """
    순서대로 학생을 보면서, 가장 최적의 위치를 찾는다.
    친구가 많은 순, 빈칸이 많은 순, 행이 작은 순, 열이 작은 순은 tuple 비교로 가능하다.
    💥💥 이때 best_row, best_col을 0, 0으로 해서 틀렸다. N, N으로 해야 가장 작은 값을 구할 수 있다.
    """
    for num in order:
        favorites = fav[num]
        best_row, best_col = N, N
        most_friend, most_empty = 0, 0

        for r in range(N):
            for c in range(N):
                if not seats[r][c]:
                    friend, empty = 0, 0
                    for dr, dc in ((-1, 0), (1, 0), (0, -1), (0, 1)):
                        nr, nc = r + dr, c + dc
                        if in_range(nr, nc):
                            if seats[nr][nc] in favorites:
                                friend += 1
                            elif seats[nr][nc] == 0:
                                empty += 1

                    if (most_friend, most_empty, -best_row, -best_col) < (friend, empty, -r, -c):
                        best_row, best_col, most_friend, most_empty = r, c, friend, empty

        seats[best_row][best_col] = num


def get_satisfaction_score():
    """
    인접한 위치를 구하는게 그냥 동서남북 보는 것과 동일하다.
    """
    score = 0

    for r in range(N):
        for c in range(N):
            num = seats[r][c]
            friend_cnt = 0
            for dr, dc in ((-1, 0), (1, 0), (0, -1), (0, 1)):
                nr, nc = r + dr, c + dc
                if in_range(nr, nc) and seats[nr][nc] in fav[num]:
                    friend_cnt += 1

            score += 10 ** (friend_cnt - 1) if friend_cnt else 0

    print(score)


def solution():
    get_fav_info()
    set_seat()
    get_satisfaction_score()

solution()