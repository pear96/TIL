def solution(cap, n, deliveries, pickups):
    answer = 0
    deliver, pickup = 0, 0

    for i in range(n):
        deliver += deliveries[i]
        pickup += pickups[i]

        while deliver > 0 or pickup > 0:
            deliver -= cap
            pickup -= cap
            answer += (i + 1) * 2

    return answer

# def solution(cap, n, deliveries, pickups):
#     answer = 0
#     deliver, pickup = 0, 0
#
#     for i in range(n-1, -1, -1):
#         deliver += deliveries[i]
#         pickup += pickups[i]
#
#         while deliver > 0 or pickup > 0:
#             deliver -= cap
#             pickup -= cap
#             answer += (i + 1) * 2
#
#     return answer


print(solution(4, 5, [1, 0, 3, 1, 2], [0, 3, 0, 4, 0]))