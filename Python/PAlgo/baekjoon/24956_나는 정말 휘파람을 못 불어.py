n = int(input())
s = input()

answer = 0
w, wh, whe = 0, 0, 0
MOD = 10 ** 9 + 7

for i in range(n):
    if s[i] == 'W': w += 1
    if s[i] == 'H': wh += w
    if s[i] == 'E':
        answer = (2 * answer + whe) % MOD
        whe += wh

print(answer)