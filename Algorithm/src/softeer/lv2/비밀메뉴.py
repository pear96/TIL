"""
1. 개쉬움 ) join으로 문자열로 만들어 in 쓰기
2. 개어려움 ) KMP 사용하기
"""


# 패턴내에서 일치 길이 구하기
# 각 idx에서 연속적으로 얼마나 일치하는지
# 패턴 = [a, b, c, d, a, b, c, e]
# 결과 = [0, 0, 0, 0, 1, 2, 3, 0]
def get_pattern(pattern):
    length = len(pattern)
    cnt = 0
    p = [0] * length

    for i in range(1, length):
        # 아직 처음까진 안돌아갔는데, 여전히 맞지 않는 경우
        while cnt > 0 and pattern[i] != pattern[cnt]:
            # cnt-1개까지 맞았던 개수로 돌아가자
            cnt = p[cnt - 1]

        # 연속으로 일치하는 경우
        if pattern[i] == pattern[cnt]:
            cnt += 1
            # i번까지 cnt개가 연속으로 일치함
            p[i] = cnt

    return p


def kmp(s, p):
    len_s, len_p = len(s), len(p)
    p_idx = 0

    pattern = get_pattern(p)

    for i in range(len_s):
        while p_idx > 0 and s[i] != p[p_idx]:
            # 이전에 일치한 위치로 돌아감
            p_idx = pattern[p_idx - 1]

        # 다시 비교해보자
        if s[i] == p[p_idx]:
            if p_idx == len_p:
                return True
            else:
                p_idx += 1

    return False


M, N, K = map(int, input().split())
password = list(map(int, input().split()))
user = list(map(int, input().split()))

if kmp(user, password):
    print("secret")
else:
    print("normal")
