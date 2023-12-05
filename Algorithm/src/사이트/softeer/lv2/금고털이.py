"""
Knapsack 알고리즘
무게당 가치가 높은 것부터 정렬하여 순차적으로 담음
"""

import sys
input = sys.stdin.readline

answer = 0

W, N = map(int, input().split())

jewel = [[0, 0] for _ in range(N)]

for i in range(N):
    jewel[i] = list(map(int, input().split()))

jewel.sort(key=lambda x:-x[1])

for i in range(N):
    if W == 0:
        break
    if W >= jewel[i][0]:
        W -= jewel[i][0]
        answer += (jewel[i][0] * jewel[i][1])
    else:
        answer += (W * jewel[i][1])
        W = 0


print(answer)