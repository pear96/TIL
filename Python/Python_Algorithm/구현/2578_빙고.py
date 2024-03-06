"""
2024.03.06
유형 : 구현
풀이 시간 : 20분

- 오버해서 함수 나눴나..
- 다른 사람 보니까 가로, 세로, 대각선 동시에 확인했던데 시간 복잡도만 늘어났나 싶기도 하고
"""

BINGO_SIZE = 5
cheolsu = list(list(map(int, input().split())) for _ in range(BINGO_SIZE))
answer = []
for _ in range(BINGO_SIZE):
    answer += list(map(int, input().split()))


def check_row():
    finished_row_cnt = 0

    for row in range(BINGO_SIZE):
        if not any(cheolsu[row]):
            finished_row_cnt += 1

    return finished_row_cnt


def check_col():
    finished_col_cnt = 0

    for col in range(BINGO_SIZE):
        empty = True
        for row in range(BINGO_SIZE):
            if cheolsu[row][col]:
                empty = False
                break
        finished_col_cnt += 1 if empty else 0

    return finished_col_cnt


def check_cross():
    to_right, to_left = 1, 1

    for i in range(BINGO_SIZE):
        if cheolsu[i][i]:
            to_right = 0
            break

    for i in range(BINGO_SIZE):
        if cheolsu[BINGO_SIZE-i-1][i]:
            to_left = 0
            break

    return to_right + to_left


def set_zero(num):
    for r in range(BINGO_SIZE):
        for c in range(BINGO_SIZE):
            if cheolsu[r][c] == num:
                cheolsu[r][c] = 0
                return


def solution():
    for i in range(BINGO_SIZE * BINGO_SIZE):
        set_zero(answer[i])
        if check_row() + check_col() + check_cross() >= 3:
            return i+1


print(solution())