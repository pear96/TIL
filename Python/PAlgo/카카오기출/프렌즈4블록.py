BLANK = ' '
# →, ↘, ↓
dr = [0, 1, 1]
dc = [1, 1, 0]


def solution(m, n, board):
    answer = 0
    board = [list(board[r]) for r in range(m)]

    # 1. while
    while True:
        # 각 위치별로 지울 블록들 찾기, set으로 관리해서 중복을 제거한다.
        # 이후 set의 위치들을 빈칸 처리한다.
        delete = set()
        for r in range(m-1):
            for c in range(n-1):
                if board[r][c] != BLANK:
                    block = set()
                    for d in range(3):
                        nr, nc = r + dr[d], c + dc[d]
                        if board[nr][nc] == board[r][c]:
                            block.add((nr, nc))
                    if len(block) == 3:
                        delete.add((r, c))
                        delete |= block

        # 블록들 지우기
        if delete:
            answer += len(delete)
            delete = list(delete)
            for r, c in delete:
                board[r][c] = BLANK
            # 중력 처리
            for col in range(n):
                last = m-1
                for row in range(m-1, -1, -1):
                    if board[row][col] != BLANK:
                        board[last][col] = board[row][col]
                        last -= 1
                for row in range(last, -1, -1):
                    board[row][col] = BLANK
        else:
            break

    return answer