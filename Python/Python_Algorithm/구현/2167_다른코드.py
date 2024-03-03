"""
https://www.acmicpc.net/source/74017782

[r][c] 에 대해 누적값을 구할 때, 누적값[r-1][c] + 누적값[r][c-1] 을 더하면
누적값 [r-1][c-1]을 2번 더하게 된다. 따라서 누적값[r-1][c-1]값을 빼주고, 현재 위치값[r][c]를 더한다.

(i -> x), (j -> y)의 누적값을 구할때, 누적값[x][y]에서 누적값[i-1][y]와 누적값[x][j-1]을 뺀다.
그러면 누적값[i-1][j-1]이 두번 빠지게 된다. 따라서 누적값[i-1][j-1]을 다시 더한다.

말로 쓰면 어려울 수 있지만, 그림으로 그려보면 간단하다..
"""
import sys


def solution():
    N, M = map(int, sys.stdin.readline().split())
    matrix = []
    prefix_sum = [[0 for _ in range(M + 1)] for _ in range(N + 1)]
    for _ in range(N):
        matrix.append(list(map(int, sys.stdin.readline().split())))

    for i in range(1, N + 1):
        for j in range(1, M + 1):
            prefix_sum[i][j] = matrix[i - 1][j - 1] + prefix_sum[i - 1][j] + prefix_sum[i][j - 1] - prefix_sum[i - 1][
                j - 1]

    K = int(sys.stdin.readline().rstrip())
    for _ in range(K):
        i, j, x, y = map(int, sys.stdin.readline().split())
        answer = prefix_sum[x][y] - prefix_sum[i - 1][y] - prefix_sum[x][j - 1] + prefix_sum[i - 1][j - 1]
        print(answer)


solution()