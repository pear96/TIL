# 최대값 = upper bound => 정답은 low-1
# N과 M의 수가 정상이 아님 => 이분탐색
N, M = map(int, input().split())
trees = list(map(int, input().split()))

low = 0
high = 2 * (10 ** 9) + 1

while low < high:
    mid = (low + high) // 2

    # 조건에 부합하는가!
    cut = sum(max(0, tree - mid) for tree in trees)

    if cut >= M:
        low = mid + 1
    else:
        high = mid

print(low-1)