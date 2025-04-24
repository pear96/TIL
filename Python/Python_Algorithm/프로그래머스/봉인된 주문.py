def solution(n, bannedOrders):
    from bisect import bisect_left, bisect_right

    # 1) bannedOrders를 길이별로 분류하고 정렬
    banned_by_len = [[] for _ in range(12)]
    for s in bannedOrders:
        banned_by_len[len(s)].append(s)
    for L in range(12):
        banned_by_len[L].sort()

    # 2) 길이 L별 “가능한 주문 개수”
    #    = 26^L - bans
    pow26 = [1] * 12
    for i in range(1, 12):
        pow26[i] = pow26[i-1] * 26

    # 3) n번째 주문이 어떤 길이인지 찾기
    rem = n
    for L in range(1, 12):
        cnt = pow26[L] - len(banned_by_len[L])
        if rem <= cnt:
            length = L
            break
        rem -= cnt

    # 4) 길이가 length인 주문들 중 rem번째(1-based)를 찾아야 함
    k = rem - 1  # 0-based 순서
    ans = []

    # 5) 한 글자씩 채워 나가며 “이 아래에 남은 valid 개수”로 k 조정
    for i in range(length):
        # 남은 자리수 = length - i - 1
        block = pow26[length - i - 1]
        # banned 리스트 중에서 “이 prefix를 갖는” 문자열 수를
        # bisect로 빠르게 구하기 위한 리스트
        bans = banned_by_len[length]

        for c_ord in range(26):
            ch = chr(ord('a') + c_ord)
            prefix = ''.join(ans) + ch

            # ① 이 글자 아래(이후 자리 모두 'a'~'z') 총 몇 개 문자열이 있는지
            total = block

            # ② 그중 banned에 걸리는 건 몇 개인지?
            lo = bisect_left(bans, prefix)
            hi = bisect_right(bans, prefix + '{')  # '{' 바로 다음 문자
            bad = hi - lo

            good = total - bad
            if k < good:
                # 이 글자를 선택!
                ans.append(ch)
                break
            else:
                # 이 글자를 건너뛰고 k-=good
                k -= good

    return ''.join(ans)



print(solution(30,["d", "e", "bb", "aa", "ae"])) # "ah"
# print(solution(7388,	["gqk", "kdn", "jxj", "jxi", "fug", "jxg", "ewq", "len", "bhc"])) # "jxk"