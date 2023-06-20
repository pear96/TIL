import sys


def input():
    return sys.stdin.readline().rstrip()


N = int(input())
nums = list(map(int, input().split()))
goal = nums[N-1]
dp = [[-1] * 21 for _ in range(N)]


def call_dp(idx: int, result: int) -> int:
    # 마지막 숫자에 도달하면, 경우의 수 +1
    if idx == N-1:
        if result == goal:
            return 1
        else:
            return 0

    if result < 0 or result > 20:
        return 0

    if dp[idx][result] != -1:
        return dp[idx][result]

    # idx번째 숫자에서 현재 값이 j일때, 남은 수로 만들 수 있는 유효한 식의 수
    dp[idx][result] = 0
    dp[idx][result] += call_dp(idx + 1, result + nums[idx])
    dp[idx][result] += call_dp(idx + 1, result - nums[idx])

    return dp[idx][result]


print(call_dp(1, nums[0]))
# call_dp(0, 0) 이 틀리는 이유
# 0 0 1 9 9 4 5 일 때, call_dp(1, 0+0) 과 call_dp(1, 0-0)으로 인해 같은 경우를 2번 더하게 됨