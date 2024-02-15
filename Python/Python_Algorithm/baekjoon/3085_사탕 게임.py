"""
시간이 3892ms가 나왔는데 너무 느려서 다른 빠른 코드를 참고했다.
https://www.acmicpc.net/source/73174093
1. 같은 색인 경우 비교하지 않았더니 2516ms로 감소했다.
2. 사탕을 바꿔서 영향 받는 부분만 계산했더니 틀렸다.
=> 같은 색인 경우 교환하는 경우도 사실은 계산을 해야했기 때문이다.
3. 이를 초기에 계산한 뒤 시간 복잡도는 124ms로 감소했다.
"""
N = int(input())
candy = list(list(input()) for _ in range(N))
answer = 0


def search_row(row):
    global answer
    # [시간 복잡도 감소 2] 바꿔서 영향 받는 부분만 계산하면 된다.
    # 행 방향으로 가장 긴 사탕 부분 구함
    color = candy[row][0]
    count = 1

    for c in range(1, N):
        if color == candy[row][c]:
            count += 1
        else:
            answer = max(answer, count)
            color = candy[row][c]
            count = 1
    answer = max(answer, count)


def search_col(col):
    global answer
    # 열 방향으로 가장 긴 사탕 부분 구함
    color = candy[0][col]
    count = 1
    for r in range(1, N):
        if color == candy[r][col]:
            count += 1
        else:
            answer = max(answer, count)
            color = candy[r][col]
            count = 1
    answer = max(answer, count)


def solution():
    # 시간 복잡도를 위해 같은 색상끼리는 교환하는 경우를 계산하지 않았다.
    # 그런데 같은 색상끼리 교환하는 것도 경우에 포함이 된다. 원래는.
    # 그래서 아무 교환도 하지 않은 상태(같은 색끼리 교환하는 상황)의 값도 구해줘야한다.
    for r in range(N):
        search_row(r)
    for c in range(N):
        search_col(c)

    for r in range(N):
        for c in range(N):
            # [시간 복잡도 감소 1] 같은 색상끼리는 바꿔도 시간 낭비
            if r + 1 < N and candy[r][c] != candy[r+1][c]:
                candy[r][c], candy[r+1][c] = candy[r+1][c], candy[r][c]
                search_row(r)
                search_row(r + 1)
                search_col(c)
                candy[r][c], candy[r+1][c] = candy[r+1][c], candy[r][c]
            if c + 1 < N and candy[r][c] != candy[r][c+1]:
                candy[r][c], candy[r][c+1] = candy[r][c+1], candy[r][c]
                search_col(c)
                search_col(c+1)
                search_row(r)
                candy[r][c], candy[r][c+1] = candy[r][c+1], candy[r][c]

    print(answer)

solution()