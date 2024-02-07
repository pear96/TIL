INF = float("inf")
END = 100_001
dp = [INF] * 100_001
subin, sister = map(int, input().split())
dp[subin] = 0

for i in range(subin, -1, -1):
    if 0 <= i + 1 < END:
        dp[i + 1] = min(dp[i + 1], dp[i] + 1)
    if 0 <= i - 1 < END:
        dp[i - 1] = min(dp[i - 1], dp[i] + 1)
    if 0 <= i * 2 < END:
        dp[i * 2] = min(dp[i * 2], dp[i] + 1)

for i in range(subin, 100_001):
    if 0 <= i + 1 < END:
        dp[i+1] = min(dp[i+1], dp[i]+1)
    if 0 <= i - 1 < END:
        dp[i-1] = min(dp[i-1], dp[i]+1)
    if 0 <= i * 2 < END:
        dp[i*2] = min(dp[i*2], dp[i]+1)

print(dp[sister])