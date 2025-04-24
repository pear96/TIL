# 안타깝게 이분탐색인걸 봐버림 ㅠㅠ
def solution(n, work_times):
    low = 1
    high = 10 ** 18 + 1

    while low < high:
        mid = (low + high) // 2  # 목표 시간
        # 목표 시간 안에 모두 처리 가능?
        people = sum(mid // work_time for work_time in work_times)

        if people >= n:
            high = mid
        else:
            low = mid - 1
    return low

solution(6,[7, 10])