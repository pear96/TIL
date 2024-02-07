import sys
sys.setrecursionlimit(10 ** 7)


def input():
    return sys.stdin.readline().rstrip()


def call_dp(day: int) -> int:
    if day == N:
        return 0

    if dp[day] != -1:
        return dp[day]

    dp[day] = call_dp(day+1)
    if day + t[day] <= N:
        dp[day] = max(dp[day], p[day] + call_dp(day + t[day]))

    return dp[day]


N = int(input())
t = [0] * N
p = [0] * N
dp = [-1] * N

for i in range(N):
    t[i], p[i] = map(int, input().split())

print(call_dp(0))
# print(dp)