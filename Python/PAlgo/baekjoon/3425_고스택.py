def calculate(init, programs):
    LIMIT = 10 ** 9
    stack = [init]
    error = False

    for program in programs:
        if program == "END":
            break
        elif program.startswith("NUM"):
            _, num = program.split()
            stack.append(int(num))
        elif program == "POP":
            if len(stack) < 1:
                error = True
                break
            stack.pop()
        elif program == "INV":
            if len(stack) < 1:
                error = True
                break
            stack[-1] = -stack[-1]
        elif program == "DUP":
            if len(stack) < 1:
                error = True
                break
            stack.append(stack[-1])
        elif program == "SWP":
            if len(stack) < 2:
                error = True
                break
            stack[-2], stack[-1] = stack[-1], stack[-2]
        elif program == "ADD":
            if len(stack) < 2:
                error = True
                break
            top = stack.pop()
            second = stack.pop()
            if abs(top + second) > LIMIT:
                error = True
                break
            else:
                stack.append(top+second)
        elif program == "SUB":
            if len(stack) < 2:
                error = True
                break
            top = stack.pop()
            second = stack.pop()
            if abs(second - top) > LIMIT:
                error = True
                break
            else:
                stack.append(second - top)
        elif program == "MUL":
            if len(stack) < 2:
                error = True
                break
            top = stack.pop()
            second = stack.pop()
            if abs(top * second) > LIMIT:
                error = True
                break
            else:
                stack.append(top * second)
        else:
            if len(stack) < 2:
                error = True
                break

            top = stack.pop()
            if top == 0:
                error = True
                break
            second = stack.pop()

            if program == "DIV":
                result = abs(second) // abs(top)
                if abs(result) > LIMIT:
                    error = True
                    break
                if top < 0:
                    if second >= 0:
                        stack.append(-result)
                    else:
                        stack.append(result)
                elif top > 0:
                    if second < 0:
                        stack.append(-result)
                    else:
                        stack.append(result)
            elif program == "MOD":
                result = abs(second) % abs(top)
                if abs(result) > LIMIT:
                    error = True
                    break
                if second < 0:
                    stack.append(-result)
                else:
                    stack.append(result)

    if len(stack) != 1 or error:
        return "ERROR"
    else:
        return str(stack[0])


answer = ""
while True:
    order = input()
    if order == "QUIT":
        print(answer.rstrip())
        break
    else:
        orders = [order]
        while order != "END":
            order = input()
            orders.append(order)
        cnt = int(input())
        for _ in range(cnt):
            num = int(input())
            # print(num, orders)
            # 계산기 작동
            answer += calculate(num, orders) + "\n"
    input()  # 공백 뛰어넘기
    answer += "\n"
