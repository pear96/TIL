# stack 문제!
# (는 그냥 넣어
# )면 바로 앞이 (다? 그럼 이제 걔 빼버리고, 개수 추가해
# )인데 바로 앞이 ) 일 수가 없는데???
brackets = list(input())
total = 0
left = 0

for idx, bracket in enumerate(brackets):
    if bracket == "(":
        left += 1
    else:
        if brackets[idx-1] == "(":
            left -= 1
            total += left
        else:
            left -= 1
            total += 1

print(total)


