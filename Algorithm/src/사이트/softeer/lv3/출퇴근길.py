import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline


N, M = map(int, input().split())
edge = [[] for _ in range(N+1)]
edge_r = [[] for _ in range(N+1)]

for _ in range(M):
    a, b = map(int, input().split())
    edge[a].append(b)
    edge_r[b].append(a)

s, t = map(int,  input().split())


def dfs(now, adj, visit):
    if visit[now]:
        return
    visit[now] = True
    for other in adj[now]:
        dfs(other, adj, visit)
    return


from_s = [False] * (N+1)
from_s[t] = True # 도착지 한번만 가게
dfs(s, edge, from_s)

from_t = [False] * (N+1)
from_t[s] = True # 출발지 한번만 가게
dfs(t, edge, from_t)


# 반전

to_s = [False] * (N+1)
dfs(s, edge_r, to_s)

to_t = [False] * (N+1)
dfs(t, edge_r, to_t)

answer = 0

for i in range(1, N+1):
    if from_s[i] and from_t[i] and to_s[i] and to_t[i]:
        answer +=1

# 출발지, 도착지 빼야됨
print(answer-2)