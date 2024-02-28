N = int(input())
taller = list(map(int, input().split()))
line = [0] * N

for i in range(N):
    left = taller[i]
    idx = 0

    while True:
        if left:
            if line[idx] == 0:
                left -= 1
            idx += 1
        else:
            if line[idx] != 0:
                idx += 1
            else:
                line[idx] = i+1
                break

print(*line)
