# 상한액. 가능한한 최대의 총 예산.
# 최대 => upper bound
N = int(input())
budgets = list(map(int, input().split()))
total = int(input())

# 이분탐색을 할것인가 말것인가
if total >= sum(budgets):
    print(max(budgets))
else:
    low = 1
    high = total + 1

    while low < high:
        mid = (low + high) // 2

        # mid 값이면, total을 넘지 않는가?
        result = sum(budget if budget < mid else mid for budget in budgets)

        if total >= result:
            low = mid + 1 # 더줘
        else:
            high = mid

    print(low - 1)

