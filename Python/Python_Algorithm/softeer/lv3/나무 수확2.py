def call_dp():  # 정방향
    dp[0][0] = arr[0][0]
    for i in range(n):
        for j in range(n):
            if i > 0 and j > 0:
                dp[i][j] = max(dp[i][j - 1], dp[i - 1][j]) + arr[i][j]
            elif i > 0:
                dp[i][j] = dp[i - 1][j] + arr[i][j]
            elif j > 0:
                dp[i][j] = dp[i][j-1] + arr[i][j]

def call_dp_reverse():  # 역방향
    reverse_dp[n-1][n-1] = arr[n-1][n-1]
    for i in range(n - 1, -1, -1):
        for j in range(n - 1, -1, -1):
            if i < n - 1 and j < n - 1:
                reverse_dp[i][j] = max(reverse_dp[i][j + 1], reverse_dp[i + 1][j]) + arr[i][j]
            elif i < n - 1:
                reverse_dp[i][j] = reverse_dp[i + 1][j] + arr[i][j]
            elif j < n - 1:
                reverse_dp[i][j] = reverse_dp[i][j+1] + arr[i][j]


n = int(input())  # 1000
arr = [list(map(int, input().split())) for _ in range(n)]

dp = [[0 for _ in range(n)] for _ in range(n)]
reverse_dp = [[0 for _ in range(n)] for _ in range(n)]
res = 0

call_dp()
call_dp_reverse()

for d in dp:
    print(d)

print()
for d in reverse_dp:
    print(d)

for i in range(n):
    for j in range(n):
        res = max(res, dp[i][j] + reverse_dp[i][j])

print(res)