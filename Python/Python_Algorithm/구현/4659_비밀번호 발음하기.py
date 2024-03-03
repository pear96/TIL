"""
2024.03.04
유형 : 구현, 문자열
풀이 시간 : 13분

- 이런게 실버 5지.. 대신 조건을 좀 제대로 봐야할 듯! 모음 3개 연속에서 엉뚱하게 만들뻔;
"""
vowels = {"a", "e", "i", "o", "u"}


def check(pwd):
    # 모음(a,e,i,o,u) 하나를 반드시 포함하여야 한다.
    v_contain = False
    # set을 만드나 안만드나 시간은 똑같이 걸린다. 아마 테스트 케이스가 별로 없나보다.
    # pwd_set = set(pwd)
    for vowel in vowels:
        if vowel in pwd:
            v_contain = True
            break

    if not v_contain: return False

    # 모음이 3개 혹은 자음이 3개 연속으로 오면 안 된다.
    length = len(pwd)
    v_continue = 0
    c_continue = 0

    for i in range(length):
        if pwd[i] not in vowels:
            c_continue = 0
            v_continue += 1
            if v_continue == 3:
                return False
        else:
            v_continue = 0
            c_continue += 1
            if c_continue == 3:
                return False

    # 같은 글자가 연속적으로 두번 오면 안되나, ee 와 oo는 허용한다.
    for i in range(1, length):
        if pwd[i - 1] == pwd[i] and pwd[i] not in ("e", "o"):
            return False

    return True


pwd = input()
while pwd != "end":
    if check(pwd):
        print(f"<{pwd}> is acceptable.")
    else:
        print(f"<{pwd}> is not acceptable.")
    pwd = input()
