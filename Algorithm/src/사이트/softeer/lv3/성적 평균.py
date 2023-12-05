import sys
input = sys.stdin.readline


N, K = map(int, input().split())

score = [0] + list(map(int, input().split()))

# 성적의 누적합
cul_score = [0] * (N+1)

for i in range(1, N+1):
    cul_score[i] += cul_score[i-1] + score[i]

for _ in range(K):
    s, e = map(int, input().split())
    average = cul_score[e] - cul_score[s-1]
    print(f"{round((average / (e-s+1)), 2):.2f}")
