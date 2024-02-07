def solution(s):
    answer = 1001
    total_length = len(s)
    for length in range(1, total_length):
        result = 0
        prev = s[0:length]
        cnt = 1
        pos = length

        while pos + (length - 1) < total_length:
            if s[pos:pos + length] == prev:
                cnt += 1
            else:
                result += len(str(cnt)) + length if cnt != 1 else length
                prev = s[pos:pos + length]
                cnt = 1
            print(f"{length} : 위치 {pos}, 이전 : {prev}, 누적 개수 : {cnt}, 현재 상황 : {result}")
            pos += length

        print(f"결과 {result}, 개수 : {cnt}, 마지막 위치 : {pos}")
        result += len(str(cnt)) + length if cnt != 1 else length
        if pos != total_length:
            result += total_length - pos
        print(f"answer : {answer} vs result : {result}")
        print()
        answer = min(answer, result)
    return answer

print(solution("aabbaccc"))  # 7