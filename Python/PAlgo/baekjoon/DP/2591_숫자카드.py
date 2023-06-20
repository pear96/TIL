num = list(map(int, input()))
length = len(num)
dp = [-1] * length


# 마지막 카드에서 부터 앞으로!
def call_dp(idx:int):
    # 마지막 숫자(한자리)
    if idx == length:
        return 1

    # 10, 20, 30과 같은 숫자로 중간에 0이 있을 수 있음
    # 그런데 0은 카드에 없음.
    if num[idx] == 0:
        return 0

    # 해당 위치 까지를 이미 구한 적이 있음
    if dp[idx] != -1:
        return dp[idx]

    dp[idx] = 0
    # 숫자 한 자리만 더하는 경우
    dp[idx] += call_dp(idx+1)

    # 숫자 두자리를 더하는 경우에는 해당 값이 34 이하여야한다.
    if idx + 2 <= length and (num[idx]*10 + num[idx+1]) <= 34:
        dp[idx] += call_dp(idx+2)

    return dp[idx]

print(call_dp(0))
print(dp)