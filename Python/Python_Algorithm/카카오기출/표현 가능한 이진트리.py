import math
def dfs(b, parent):
    i = len(b) // 2
    if not b:  # 리프 노드에 도달했다면
        return True  # 포화 이진트리 될 수 있다.

    # 부모 노드가 더미 노드일 때 자식이 더미노드가 아니라면 포화 이진 트리가 될 수 없음
    if not parent and b[i] == '1':
        return False

    # 서브 트리 탐색
    left = dfs(b[:i], b[i] == '1')
    right = dfs(b[i+1:], b[i] == '1')
    # 둘 다 True 여야만 포화 이진 트리를 만들 수 있다.
    return left and right


def solution(numbers):
    answer = []

    for number in numbers:
        # 1. 숫자를 이진수 문자열로 변환한다.
        b = bin(number)[2:]

        # 2. 변환한 이진수를 포화이진트리 형태로 만들어준다.
        # 2-1. 포화 이진 트리의 크기는 이진수 길이 보다 큰 2 ** n - 1 중 가장 작은 것
        p = 0
        while (2 ** p) - 1 < len(b):
            p += 1
        # 2-2. 포화 이진 트리가 아닌 경우 더미노드(0)를 추가한다.
        b = '0' * ((2 ** p - 1) - len(b)) + b
        print(b)
        # 3. 포화 이진 트리인지 판단한다. DFS 탐색
        # 3-1. DFS 탐색 (이진트리, 현재 노드 위치, 부모)
        if b[len(b)//2] == '1' and dfs(b, True):
            answer.append(1)
        else:
            answer.append(0)
        # 3-2. 부모 노드가 더미 노드일 때 자식 노드가 더미 노드가 아니라면 이진 트리로 표현할 수 없다.

    return answer


print(solution([7, 42, 5]))
print(solution([63, 111, 95]))