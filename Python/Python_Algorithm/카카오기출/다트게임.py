def solution(dartResult):
    answer = 0
    scores = []
    num = ""
    for t in dartResult:
        if t.isdecimal():
            num += t
        elif t.isalpha():
            if t == "S":
                scores.append(int(num))
            elif t == "D":
                scores.append(int(num) ** 2)
            elif t == "T":
                scores.append(int(num) ** 3)
            num = ""
        elif t == "#":
            scores[-1] *= -1
        else:
            scores[-1] *= 2
            if len(scores) >= 2:
                scores[-2] *= 2
    return sum(scores)