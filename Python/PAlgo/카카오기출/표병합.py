from collections import deque

SIZE = 50
table = []
parent = [[] for _ in range(SIZE)]
child = dict()


def find_ancestor(r, c):
    if [r, c] != parent[r][c]:
        parent[r][c] = find_ancestor(*parent[r][c])
    return parent[r][c]


def update_by_value(old, new):
    for r in range(SIZE):
        for c in range(SIZE):
            if table[r][c] == old:
                table[r][c] = new


def update_by_pos(r, c, value):
    r, c = int(r) - 1, int(c) - 1
    ar, ac = find_ancestor(r, c)
    table[ar][ac] = value


def merge(r1, c1, r2, c2):
    if r1 == r2 and c1 == c2:
        return
    r1, c1, r2, c2 = r1 - 1, c1 - 1, r2 - 1, c2 - 1

    ar1, ac1 = find_ancestor(r1, c1)
    ar2, ac2 = find_ancestor(r2, c2)

    if table[ar1][ac1] is None and table[ar2][ac2] is not None:
        parent[ar1][ac1] = [ar2, ac2]
        # 바로 아래 자식을 저장한다.
        if child.get((ar2, ac2)):
            child[(ar2, ac2)].append((ar1, ac1))
        else:
            child[(ar2, ac2)] = [(ar1, ac1)]
    else:
        parent[ar2][ac2] = [ar1, ac1]
        if child.get((ar1, ac1)):
            child[(ar1, ac1)].append((ar2, ac2))
        else:
            child[(ar1, ac1)] = [(ar2, ac2)]


def unmerge(r, c):
    r, c = r - 1, c - 1
    ar, ac = find_ancestor(r, c)
    value = table[ar][ac]
    # 바로 아래 자식들 위치 저장
    if child.get((ar, ac)):
        q = deque([(ar, ac)])

        while q:
            row, col = q.popleft()
            table[row][col] = None
            parent[row][col] = [row, col]
            if child.get((row, col)):
                for cr, cc in child[(row, col)]:
                    q.append((cr, cc))
                child.pop((row, col))
    table[r][c] = value
    parent[r][c] = [r, c]


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