"""
2024.03.08
유형 : 구현
풀이 시간 : 2시간 35분

- 너무 어렵다.
- 어차피 '최소값'을 구하는데, 굳이 다른 카드 쌍을 뒤집어 볼 필요가 없다.
  - 같은 숫자를 가진 카드로 이동 * 다른 숫자를 가진 카드로 이동 으로 모든 경우를 보면 되겠다고 생각했다.
- Ctrl, Enter는 직접 구현하지 않아도 된다.
  - 방향키 한번이나 Ctrl + 방향키나 횟수가 동일하다.
  - Enter는 횟수 추가만 하고 실제로 같은 카드인지 확인하지는 않는다.

- 진행 과정
  1. 각 카드 쌍의 위치를 저장한다.
    - pairs 라는 딕셔너리에(list로 하면 모든 번호가 있을 거란 보장이 없어서 카드 순회가 까다로움) 2장의 행, 열을 저장했다.
  2. 최대 6 종류의 카드가 있으며, 각 카드 쌍은 (r1, c1) <-> (r2, c2) 순서로 2개의 경우가 있다.
    - 따라서 2^6(카드의 2장 순서) * 6!(카드 종류의 순서) 의 경우의 수가 나온다. 완탐 가능
  3. 카드 종류에 따른 순서를 다 만들었다면 실제로 이동하면서 계산을 해본다.
  4. 카드가 이동할 때 두 위치의 행, 열을 비교했다.
    - 같은 위치 => 0
    - 같은 행 or 같은 열 => 중간에 있는 카드의 개수
      - 보드가 4*4로 매우 작아서 풀 수 있었다. 크면 더 어려울 듯.
    - 다른 행 and 다른 열 => 같은 행 + 같은 열 2번
"""

N = 4
pairs = dict()  # pairs[카드 숫자] = [(r1, c1), (r2, c2)]
init_board = []  # 입력받는 보드를 저장
total_card = 0  # 카드 숫자 개수
answer = 987654321  # 최소값 찾기
init_row, init_col = -1, -1  # 시작 커서 위치


def init():
    """
    카드의 숫자에 따른 위치 정보를 저장한다.
    """
    global total_card
    for r in range(N):
        for c in range(N):
            if init_board[r][c]:
                card = init_board[r][c]
                if pairs.get(card):
                    pairs[card].append((r, c))
                else:
                    pairs[card] = [(r, c)]
    total_card = len(pairs)


def get_minimal_dist(board, r1, c1, r2, c2):
    """
    (r1, c1)에서 (r2, c2)로 가는 최소 키 조작 횟수를 찾는다.
    :param board: 현재 진행중인 보드 상태 (카드가 사라졌을 수 있다.)
    :param r1:
    :param c1:
    :param r2:
    :param c2:
    :return: 최소 키 조작 횟수
    """
    if r1 == r2 and c1 == c2:
        return 0
    if r1 != r2 and c1 == c2:
        return get_same_col(board, c1, r1, r2)
    if r1 == r2 and c1 != c2:
        return get_same_row(board, r1, c1, c2)
    return get_diagnol(board, r1, c1, r2, c2)


def get_same_row(board, row, col1, col2):
    """
    두 위치가 같은 행에 있을 때 최소 조작 횟수를 구한다.
    차이값이 1, 2, 3 중 하나이므로 각 경우에 맞게 중간에 낀 카드를 고려해서 구한다.
    :param board: 진행중인 보드 상태
    :param row:
    :param col1:
    :param col2:
    :return: 최소 조작 횟수
    """

    # 차이값이 1이라면 그냥 방향키 한번만 누르면 된다.
    if abs(col1 - col2) == 1:
        return 1
    # 아니라면 중간에 낀 카드의 개수를 구한다.
    middle_card = 0
    # 열 값이 증가하는 방향으로 구하려고 small, big 값을 저장했다.
    s, b = min(col1, col2), max(col1, col2)

    # 비교하려는 두 위치는 제외하고 계산한다.
    for col in range(s + 1, b):
        if board[row][col]:
            middle_card += 1

    if abs(col1 - col2) == 2:
        # 오른쪽 방향으로만 그림을 그려대서... col2가 <- 방향일 수도 있다는걸 계속 생각 못함
        # 이거 반영해주니깐 테케 3, 25번 틀리던거 해결함...
        if board[row][col2] or col2 in (0, N - 1):
            return middle_card + 1
        # 목표점에 카드가 없는데 끝도 아니라면 무조건 2번 움직여야한다.(방향키+방향키 or Ctrl+방향키)
        return 2
    # 3만큼 차이나면 끝과 끝이므로 중간에 낀 카드값에 따라 다르다.
    if abs(col1 - col2) == 3:
        return middle_card + 1


def get_same_col(board, col, row1, row2):
    """
    같은 열에 있을 경우를 구했으며, get_same_row와 방향만 다르고 동일하다.
    :param board:
    :param col:
    :param row1:
    :param row2:
    :return:
    """
    if abs(row1 - row2) == 1:
        return 1
    middle_card = 0
    s, b = min(row1, row2), max(row1, row2)

    for row in range(s + 1, b):
        if board[row][col]:
            middle_card += 1

    if abs(row1 - row2) == 2:
        if board[row2][col] or row2 in (0, N - 1):
            return middle_card + 1
        return 2
    elif abs(row1 - row2) == 3:
        return middle_card + 1


def get_diagnol(board, row1, col1, row2, col2):
    """
    서로 대각선에 위치한 경우 →↓ vs ←↑ 를 비교해본다.
    :param board:
    :param row1:
    :param col1:
    :param row2:
    :param col2:
    :return:
    """
    result1 = get_same_row(board, row1, col1, col2) + get_same_col(board, col2, row1, row2)  # →↓
    result2 = get_same_row(board, row2, col1, col2) + get_same_col(board, col1, row1, row2)  # ←↑
    return min(result1, result2)


def game(order):
    """
    순서를 다 만들었다면 실제로 게임을 실행해본다.
    :param order: [(카드 숫자, r1, c1, r2, c2) ...] 형태로 저장되어있다.
    :return: 현재 순서로 얻는 최소 키 조작 값
    """
    # 실제로 카드를 삭제해서 횟수를 계산해야하기 때문에 보드 배열을 복사해준다.
    temp_board = [init_board[r][:] for r in range(N)]
    row, col = init_row, init_col
    result = 0

    for card, r1, c1, r2, c2 in order:
        # 커서 -> 카드 쌍 중 첫 번째 카드로 이동
        result += get_minimal_dist(temp_board, row, col, r1, c1)
        # 첫 번째 카드 -> 두 번째 카드 이동 + enter 2번
        result += get_minimal_dist(temp_board, r1, c1, r2, c2) + 2
        # 카드 삭제
        temp_board[r1][c1] = 0
        temp_board[r2][c2] = 0
        # 커서 이동
        row, col = r2, c2

    return result


def make_order(used, order):
    """
    카드를 이동할 순서를 만든다.
    :param used: 해당 카드가 순서가 정해졌는지 조회하는 딕셔너리
    :param order: 순서가 저장된 배열
    :return:
    """
    global answer
    if len(order) == total_card:
        answer = min(answer, game(order))
        return

    # 카드 숫자를 순회하며 아직 순서가 정해지지 않은 카드의 순서를 순열로 저장한다.
    for other in pairs.keys():
        if not used[other]:
            r1, c1 = pairs[other][0]
            r2, c2 = pairs[other][1]

            used[other] = True
            make_order(used, order + [(other, r1, c1, r2, c2)])
            make_order(used, order + [(other, r2, c2, r1, c1)])
            used[other] = False


def solution(board, r, c):
    global init_board, init_row, init_col
    init_board, init_row, init_col = board, r, c
    init()
    used = dict()
    for card in pairs.keys():
        used[card] = False
    make_order(used, [])

    return answer
