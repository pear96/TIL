SIZE = 50
table = []
parent = [[] for _ in range(SIZE)]


# 조상을 찾는다.
def find_ancestor(r, c):
    if [r, c] != parent[r][c]:
        parent[r][c] = find_ancestor(*parent[r][c])
    return parent[r][c]


# 해당 위치에 저장된 데이터가 중요한게 아니다. 조상의 데이터를 조회한다.
def get_data(r, c):
    ar, ac = find_ancestor(r, c)
    return table[ar][ac]


# old인지 확인하기 위해서 table[r][c]로 찾지 마라.
# 부모의 값이 일치하는지 찾는게 진짜로 병합된 셀 값 바꾸는 방법이다.
def update_by_value(old, new):
    for r in range(SIZE):
        for c in range(SIZE):
            if get_data(r, c) == old:
                pr, pc = find_ancestor(r, c)
                table[pr][pc] = new


# 그 위치의 값을 업데이트 할거지만, 역시나 조상의 값을 바꿔야 한다.
def update_by_pos(r, c, value):
    r, c = int(r) - 1, int(c) - 1
    ar, ac = find_ancestor(r, c)
    table[ar][ac] = value


# 진짜로 병합하기
def merge(r1, c1, r2, c2):
    if r1 == r2 and c1 == c2:
        return
    r1, c1, r2, c2 = r1 - 1, c1 - 1, r2 - 1, c2 - 1

    ar1, ac1 = find_ancestor(r1, c1)
    ar2, ac2 = find_ancestor(r2, c2)

    if table[ar1][ac1] is None:
        parent[ar1][ac1] = [ar2, ac2]
    else:
        parent[ar2][ac2] = [ar1, ac1]


# 병합 해제하기
# 처음에는 각 위치별 자손의 위치를 저장했는데 그냥 2중 for문 돌면서 자식 찾아내면 됨
def unmerge(r, c):
    r, c = r - 1, c - 1
    ar, ac = find_ancestor(r, c)
    value = table[ar][ac]

    seperates = []

    for row in range(SIZE):
        for col in range(SIZE):
            if [ar, ac] == find_ancestor(*parent[row][col]):
                seperates.append((row, col))

    for row, col in seperates:
        parent[row][col] = [row, col]
        table[row][col] = None

    table[r][c] = value
    parent[r][c] = [r, c]


# 역시나 조상을 찾고 그 조상의 값을 출력해야함
def print_cell(r, c):
    r, c = r - 1, c - 1
    ar, ac = find_ancestor(r, c)
    return table[ar][ac] if table[ar][ac] is not None else "EMPTY"


def solution(commands):
    global table
    answer = []
    table = [[None] * SIZE for _ in range(SIZE)]
    for r in range(SIZE):
        for c in range(SIZE):
            parent[r].append([r, c])

    for command in commands:
        command = command.split()
        order = command.pop(0)
        if order == "UPDATE":
            if len(command) == 2:
                update_by_value(*command)
            else:
                update_by_pos(*command)
        elif order == "MERGE":
            merge(*map(int, command))
        elif order == "UNMERGE":
            unmerge(*map(int, command))
        elif order == "PRINT":
            answer.append(print_cell(*map(int, command)))

    return answer