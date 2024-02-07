def convert(num, n):
    result = ""
    while num > 0:
        mod = num % n
        result += str(mod) if mod < 10 else chr(55 + mod)
        num //= n
    return result[::-1]


def solution(n, t, m, p):
    answer = ''
    converted = "0"
    num = 1
    while len(converted) <= t * m:
        converted += convert(num, n)
        num += 1

    for i in range(t):
        answer += converted[i * m + p - 1]
    return answer