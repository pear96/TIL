from itertools import permutations


def cal(num1, num2, op):
    if op == '-':
        return num1 - num2
    elif op == '+':
        return num1 + num2
    elif op == '*':
        return num1 * num2


def get_number(op_order, nums, ops):
    n_stack = [nums[0]]
    o_stack = []
    op_cnt = len(ops)
    i = 0

    # 스택 안에 있는 연산자의 우선순위가 더 높다면 계산 먼저 해줘야함
    while i < op_cnt:
        while o_stack and op_order[o_stack[-1]] >= op_order[ops[i]]:
            now_op = o_stack.pop()
            num2 = n_stack.pop()
            num1 = n_stack.pop()
            n_stack.append(cal(num1, num2, now_op))

        o_stack.append(ops[i])
        n_stack.append(nums[i + 1])
        i += 1

    while o_stack:
        now_op = o_stack.pop()
        num2 = n_stack.pop()
        num1 = n_stack.pop()
        n_stack.append(cal(num1, num2, now_op))

    return abs(n_stack[0])


def solution(expression):
    answer = 0

    # 입력값 숫자 / 연산자로 분리
    nums, ops = [], []
    num = ""
    for c in expression:
        if c.isdecimal():
            num += c
        else:
            nums.append(int(num))
            num = ""
            ops.append(c)
    nums.append(int(num))

    op_set = set(ops)
    orders = list(permutations(range(len(op_set))))

    for op_order in orders:
        result = get_number(dict(zip(op_set, op_order)), nums, ops)
        answer = max(answer, result)

    return answer

print(solution("50*6-3*2"))