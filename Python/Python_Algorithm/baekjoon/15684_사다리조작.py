"""
Pypy3로는 통과 하지만, Python3로는 통과를 못 해서 다른 사람의 코드를 봤다.
그랬더니, 세로선 마다 몇개의 가로선이 연결되어있는지를 계산하는 것을 찾았다.
그 이유는 세로선에 연결된 가로선이 홀수 개라면, 시작점과 도착점이 일치하지 않는 다는 것이기 때문이다.
따라서 가로선을 추가해 시작점과 도착점이 일치하도록 해야한다...
"""
N, M, H = map(int, input().split())
ladder = [[False] * (N+1) for _ in range(H+1)]
horizon_cnt = [0] * (N+1)
answer = 4  # 3개 초과는 불가능. 최소값 찾기

# 주어진 가로선 M개
for _ in range(M):
    a, b = map(int, input().split())
    ladder[a][b] = True
    horizon_cnt[b] += 1


def move_through_ladder():
    for start in range(1, N):
        col = start

        for row in range(1, H+1):
            if ladder[row][col-1]:
                col -= 1
            elif ladder[row][col]:
                col += 1

        if col != start:
            return False
    return True


def make_ladder(count, start_row, start_col):
    global answer

    if count > 3 or answer <= count:
        return
    if move_through_ladder():
        answer = min(answer, count)
        return
    # 각 세로선에 연결된 가로선의 개수가 홀수인 세로선이 총 몇개인지 구하고,
    # 추가로 필요한 가로선의 개수가 지금 추가 가능한 가로선의 개수보다 많다면,
    # 더 이상 추가해도 의미 없기 때문에 중단한다.
    if sum([1 for i in horizon_cnt if i % 2 == 1]) > 3 - count:
        return
    for row in range(start_row, H+1):
        for col in range(start_col if start_row == row else 1, N):
            if not ladder[row][col-1] and not ladder[row][col] and not ladder[row][col+1]:
                ladder[row][col] = True
                horizon_cnt[col] += 1
                make_ladder(count+1, row, col+2)
                ladder[row][col] = False
                horizon_cnt[col] -= 1


def solution():
    make_ladder(0, 1, 1)
    print(answer if answer < 4 else -1)

solution()