from collections import defaultdict
results = []


def making_set(idx, possibles, case):
    if idx == len(possibles):
        for result in results:
            if len(case & result) == len(case):
                return
        results.append(case.copy())
        return

    for user in possibles[idx]:
        if user not in case:
            case.add(user)
            making_set(idx + 1, possibles, case)
            case.remove(user)


def solution(user_id, banned_id):
    results.clear()
    id_len = defaultdict(list)
    for _id in user_id:
        id_len[len(_id)].append(_id)

    possibles = []

    for b_id in banned_id:
        candidates = []
        length = len(b_id)
        for u_id in id_len[length]:
            found = True
            for i in range(length):
                if b_id[i] == "*":
                    continue
                elif b_id[i] != u_id[i]:
                    found = False
                    break
            if found:
                candidates.append(u_id)
        possibles.append(candidates)

    making_set(0, possibles, set())

    return len(results)

print(solution(["frodo", "fradi", "crodo", "abc123", "frodoc"], ["fr*d*", "abc1**"])) #2
print(solution(["frodo", "fradi", "crodo", "abc123", "frodoc"],	["*rodo", "*rodo", "******"])) # 2
print(solution(["frodo", "fradi", "crodo", "abc123", "frodoc"],	["fr*d*", "*rodo", "******", "******"])) #3
"""
참고
for result in results:
    # 중복 제거했을 때 길이가 줄어들지 않는다면
    if len(set(result)) == len(banned_id):
        # 문자열로 만들어서 set으로 저장하면 쉽다....
        answer.add("".join(sorted(result)))
"""