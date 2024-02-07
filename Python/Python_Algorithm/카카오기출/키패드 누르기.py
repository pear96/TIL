def solution(numbers, hand):
    answer = ''
    # 숫자에 따른 위치, 10 = *, 11 = #
    pos = [[0, 0] for _ in range(10)]
    for i in range(3):
        for j in range(3):
            pos[i * 3 + j + 1] = [i, j]
    pos[0] = [3, 1]
    left = [3, 0]
    right = [3, 2]

    for num in numbers:
        if num in (1, 4, 7):
            answer += "L"
            left = pos[num]
        elif num in (3, 6, 9):
            answer += "R"
            right = pos[num]
        else:
            l_dist = abs(left[0] - pos[num][0]) + abs(left[1] - pos[num][1])
            r_dist = abs(right[0] - pos[num][0]) + abs(right[1] - pos[num][1])

            if l_dist > r_dist:
                answer += "R"
                right = pos[num]
            elif l_dist < r_dist:
                answer += "L"
                left = pos[num]
            else:
                if hand == "right":
                    answer += "R"
                    right = pos[num]
                else:
                    answer += "L"
                    left = pos[num]
    return answer