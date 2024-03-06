N, M = map(int, input().split())
dna = [input() for _ in range(N)]
to_idx = {"A": 0, "C": 1, "G": 2, "T": 3}
to_char = ["A", "C", "G", "T"]


def solution():
    answer = ""
    hamming = 0
    # 앞에서 한자리씩 모든 DNA에 대해서 본다.
    for col in range(M):
        candidate = [0] * 4
        # 지금 위치에서 ACGT 가 몇번 등장했는지 기록한다.
        for row in range(N):
            candidate[to_idx[dna[row][col]]] += 1

        # 가장 많이 등장한 문자를 찾는다.
        letter, cnt = 3, 0
        for i in range(4):
            if candidate[i] > cnt:
                cnt = candidate[i]
                letter = i
        answer += to_char[letter]

        # 가장 많이 등장한 문자와 다른 문자들이 몇개 있었는지 더한다.
        for i in range(4):
            if i == letter:
                continue
            hamming += candidate[i]

    print(answer)
    print(hamming)


solution()


"""
대박 간편한 풀이..
https://www.acmicpc.net/source/74363442

n, m = map(int, input().split())
ans = ''
k = 0
lst = [input() for i in range(n)]
for i in range(m):
    t = [0, 0, 0, 0]
    for j in range(n):
        t['ACGT'.index(lst[j][i])] += 1  # 이렇게 하면 dict, list 필요없음
    ans += 'ACGT'[t.index(max(t))]
    k += sum(t) - max(t) # 이렇게 하면 반복문으로 돌 필요 없음
print(ans)
print(k)
"""