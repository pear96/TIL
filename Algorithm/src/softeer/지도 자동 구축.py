# 2 -> (2+1) = 3 -> (3 + 2) = 5 -> (5 + 4) = 9

dot = 2

N = int(input())

for _ in range(N):
    dot = 2 * dot - 1

print(dot*dot)