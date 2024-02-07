from itertools import combinations


def uniqueness(cols, relation, total_row):
    rows = set()

    for rel in relation:
        row = ""
        for col in cols:
            row += str(rel[col])  # 행을 문자열로 만든다.
        rows.add(row)

    return True if len(rows) == total_row else False


def solution(relation):
    answer = 0

    candidates = []
    total_row = len(relation)
    total_col = len(relation[0])
    columns = set(range(total_col))  # (0, 1, 2...)

    key_len = 1
    while key_len <= len(columns):
        combs = combinations(columns, key_len)  # key_len 만큼의 조합
        for comb in combs:
            now = set(comb)
            minimality = True
            for candidate in candidates:
                if (now | candidate) == now:
                    minimality = False
                    break
            if minimality and uniqueness(comb, relation, total_row):
                candidates.append(now)
                answer += 1
        key_len += 1

    return answer


# print(solution([["100","ryan","music","2"],["200","apeach","math","2"],["300","tube","computer","3"],["400","con","computer","4"],["500","muzi","music","3"],["600","apeach","music","2"]]))
print(solution([ ["a","1","aaa","c","ng"],
["a","1","bbb","e","g"],
["c","1","aaa","d","ng"],
["d","2","bbb","d","ng"]]))