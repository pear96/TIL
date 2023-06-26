"""
top-down이 안되는 이유 : 테케가 주어져서, 테케마다 테이블을 초기화 하는데 재귀까지 더해져서
bottom up : 맨 끝에다가 뭘 붙여볼 것인가?
- dp[i][j] -> 앞 i를 1 줄여보고, 뒤 j를 1 줄여보자  (대신 완벽히 통하진 않음)
- 하나의 J 를 붙여보는 경우 -> dp[i-1][j//2]
- J이외의 수를 붙여보는 경우 -> dp[i][j-1]

- 이 문제는 '특정한 수'로 끝나는 경우의 수가 아니라, 1~M까지 N개를 고르는데 조건에 맞는 '모든' 경우의 수를 구하는 것이다.
- 따라서 dp[i][j]를 1부터 j이하의 수에서 n개를 고르는 경우의 수로 설정하면, 누적된 결과를 활용할 수 있다.

dp[4][10]
(10 사용 X)
- 1 2 4 8
- 1 2 4 9
=> 4개를 골랐지만, 10을 사용하지 않았다. 그럼 [4][8] + [4][9] 가 아닌 이유는?
dp[4][8] = 1 2 4 8
dp[4][9] = 1 2 4 8 / 1 2 4 9 (이미 dp[4][9]에 포함되어있음)
따라서 dp[i][j-1] 의 경우의 수를 더하면 된다.
---------------------
(10 사용) => 3번째 숫자가 10의 반 이하면 된다.
위의 설명과 같이 dp[3][5]에 dp[3][4]가 포함되어있으므로, dp[i-1][j//2]를 고른다.
- 1 2 4 10
- 1 2 5 10
"""


import sys

N, M = 0, 0
dp = []


def input():
    return sys.stdin.readline().rstrip()


# def call_dp(idx: int, result: int):
#     if idx == N-1:
#         return 1
#
#     if dp[idx][result] != -1:
#         return dp[idx][result]
#
#     dp[idx][result] = 0
#     for nxt in range(result*2, (M // 2 ** (N-idx-2)) + 1):
#         dp[idx][result] += call_dp(idx+1, nxt)
#
#     return dp[idx][result]


T = int(input())

for tc in range(T):
    N, M = map(int, input().split())
    dp = [[0] * (M+1) for _ in range(N+1)]
    dp[0] = [1] * (M+1)  # 0개 선택 => 선택 안하기

    for cnt in range(1, N+1):
        for max_num in range(1, M+1):
            dp[cnt][max_num] = dp[cnt-1][max_num // 2] + dp[cnt][max_num-1]

    print(dp[N][M])