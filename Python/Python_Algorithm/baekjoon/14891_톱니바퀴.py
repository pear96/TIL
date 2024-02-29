from collections import deque

cog = []

for _ in range(4):
    cog.append(deque(list(map(int, input()))))

K = int(input())

for _ in range(K):
    cog_num, d = map(int, input().split())
    cog_num -= 1

    direction = [0] * 4
    direction[cog_num] = d

    for left in range(cog_num-1, -1, -1):
        if cog[left+1][6] != cog[left][2]:
            direction[left] = -direction[left+1]
        else:
            break

    for right in range(cog_num+1, 4):
        if cog[right-1][2] != cog[right][6]:
            direction[right] = -direction[right-1]
        else:
            break

    for idx in range(4):
        cog[idx].rotate(direction[idx])

answer = 0
for i in range(4):
    answer += cog[i][0] * (2 ** i)

print(answer)