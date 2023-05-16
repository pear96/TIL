tc = int(input())

light = [
    [1, 1, 1, 0, 1, 1, 1],
    [0, 0, 1, 0, 1, 0, 0],
    [0, 1, 1, 1, 0, 1, 1],
    [0, 1, 1, 1, 1, 1, 0],
    [1, 0, 1, 1, 1, 0, 0],
    [1, 1, 0, 1, 1, 1, 0],
    [1, 1, 0, 1, 1, 1, 1],
    [1, 1, 1, 0, 1, 0, 0],
    [1, 1, 1, 1, 1, 1, 1],
    [1, 1, 1, 1, 1, 1, 0]
]


def count_diff(a, b):
    cnt = 0
    lightA = light[a]
    lightB = light[b]

    for i in range(7):
        if lightA[i] != lightB[i]:
            cnt += 1

    return cnt


for t in range(tc):
    a, b = input().split()
    lenA, lenB = len(a), len(b)
    cnt = 0
    if lenA < lenB:
        a = "-" * (lenB - lenA) + a
    elif lenB < lenA:
        b = "-" * (lenA - lenB) + b

    length = max(lenA, lenB)

    for i in range(length):
        if a[i] == "-":
            cnt += sum(light[int(b[i])])
        elif b[i] == "-":
            cnt += sum(light[int(a[i])])
        else:
            cnt += count_diff(int(a[i]), int(b[i]))
    print(cnt)