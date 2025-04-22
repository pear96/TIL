# 최대 랜선의 길이 -> upper bound
# low = 가능한 최소값, high = 불가능한 최소값
# 정답 : low - 1

K, N = map(int, input().split())
cables = list(int(input()) for _ in range(K))

low = 1
high = 2 ** 31

# 항상 여기가 어려워...
while low < high:
    mid = (low + high) // 2
    # count
    count = sum(cable // mid for cable in cables)

    if count >= N:  # 조건 만족해? 그럼 오른쪽으로 고고
        low = mid + 1
    else:
        high = mid # 안되면 어쩔 수 없징;;

print(low - 1)