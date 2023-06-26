import sys


def input():
    return sys.stdin.readline().rstrip()


MAX_VALUE = 100000
C, N = map(int, input().split())

# idx = 인원, value = 해당 인원을 모집하기 위한 최소비용
# dp배열의 크기를 C+100으로 해야하는 이유 :
# - C명보다 많은 고객의 수에서 최소비용이 나올 수 있다.
# - 한 도시에서 최대로 홍보할 수 있는 고객 수는 100명이다.
# - 따라서 C+1명, C+2명,.. C+100명 까지의 경우만 고려해도 된다.
dp = [MAX_VALUE] * (C+100)
dp[0] = 0
info = list(map(int, input().split()) for _ in range(N))

for cost, customer in info:
    # 주어진 인원의 수에서, 최대 고려해볼 인원 수 까지
    for i in range(customer, C + 100):
        # i명을 모집할 때, 과거의 i명까지 확보하는데 최소 비용과 비교하겠다.
        dp[i] = min(dp[i], dp[i-customer]+cost)


print(min(dp[C:]))
# for i in range(100):
#     for idx, val in zip(range(i*10, (i+1)*10), dp[i*10:(i+1)*10]):
#         print(f"{idx}명 : {val}원", end="\t")
#     print()