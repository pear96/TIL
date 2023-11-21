def solution(msg):
    answer = []
    dic = dict()
    for i in range(26):
        dic[chr(65+i)] = i+1

    idx = 0  # 문자열 시작 위치
    new_word = 0 # 새로 추가된 문자열
    length = len(msg)
    while idx < length:
        extend = 1
        while idx+extend <= length and msg[idx:idx+extend] in dic:
            extend += 1
        answer.append(dic[msg[idx:idx+extend-1]])
        new_word += 1
        dic[msg[idx:idx+extend]] = 26 + new_word
        idx += (extend -1)
    return answer

print(solution("KAKAO"))