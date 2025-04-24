CHESS = 8
def check(row, col, board):
    black = 0  # (0,0)을 B로 시작할 때
    white = 0  # (0,0)을 W로 시작할 때

    for dr in range(CHESS):
        for dc in range(CHESS):
            current = board[row+dr][col+dc]
            # (dr+dc)%2 == 0 위치는 시작색과 동일해야하고, 1 위치는 반대색을 가져야 함
            # B 시작에서 안바꿨으면 W 시작에선 어차피 바꿔야하는 위치임
            if (dr + dc) % 2 == 0:
                if current != 'B':
                    black += 1
                if current != 'W':
                    white += 1
            else:
                if current != 'W':
                    black += 1
                if current != 'B':
                    white += 1

    return min(black, white)


def solution():
    N, M = map(int, input().split())
    board = list(list(input()) for _ in range(N))
    answer = float('inf')

    for r in range(N-7):
        for c in range(M-7):
            answer = min(answer, check(r, c, board))
    print(answer)

solution()