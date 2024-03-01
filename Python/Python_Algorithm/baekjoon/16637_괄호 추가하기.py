N = int(input())
exp = list(input())
answer = float('-inf')


def get_num(value1, op, value2):
    if op == "+":
        return int(value1) + int(value2)
    elif op == "-":
        return int(value1) - int(value2)
    elif op == "*":
        return int(value1) * int(value2)


def dfs(idx: int, value: int, wrap_bracket: bool):
    global answer

    if idx > N - 2:
        answer = max(answer, value)
        return
    if idx == N - 2:
        answer = max(answer, get_num(value, exp[idx], exp[idx + 1]))
        return

    if wrap_bracket:
        result = get_num(exp[idx + 1], exp[idx + 2], exp[idx + 3])
        if idx > 0:
            result = get_num(value, exp[idx], result)
        dfs(idx + 4, result, True)
        dfs(idx + 4, result, False)
    else:
        result = get_num(value, exp[idx], exp[idx + 1]) if idx > 0 else int(exp[idx + 1])
        dfs(idx + 2, result, True)
        dfs(idx + 2, result, False)


if N >= 3:
    dfs(-1, 0, True)
    dfs(-1, 0, False)
else:
    answer = int(exp[0])
print(answer)
