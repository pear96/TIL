# 절대! A를 만들어선 안된다.
N = int(input())
K = int(input())


low = 1
high = K  # K번째 수는 절대 K보다 커질 수 없음

# O(NlogN) => 1660964
while low < high: # O(logN)
    # 특정 숫자
    mid = (low + high) // 2

    # 여기를 생각을 못했네
    count = 0
    for i in range(N):  # O(N)
        count += min(N, mid // (i+1))
    #

    # 여기까진 생각했는데
    if count >= K: # 줄여가야지
        high = mid
    else:
        low = mid + 1

print(low)