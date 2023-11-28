"""
https://velog.io/@tjdud0123/%EA%B8%B0%EB%91%A5%EA%B3%BC-%EB%B3%B4-%EC%84%A4%EC%B9%98-2020-%EC%B9%B4%EC%B9%B4%EC%98%A4-%EA%B3%B5%EC%B1%84-python
"""

def impossible(result):
    COL, ROW = 0, 1
    # 설치된 모든 기둥과 보들이 주어진 조건에 맞게 설치되어있는지 확인한다.
    for x, y, a in result:
        if a == COL:
            # 맨 밑이거나 / 아래에 기둥이 있거나 / 왼쪽에 보가 있거나, 설치 위치에 보가 있거나
            if y != 0 and (x, y - 1, COL) not in result and \
                    (x - 1, y, ROW) not in result and (x, y, ROW) not in result:
                # 다 아니면 불가능하다.
                return True
        else:
            # 아래에 기둥이 있거나 / 아래 오른쪽에 기둥이 있거나 / 양 옆에 보가 있거나
            if (x, y - 1, COL) not in result and (x + 1, y - 1, COL) not in result and \
                    not ((x - 1, y, ROW) in result and (x + 1, y, ROW) in result):
                # 마찬가지로 다 아니라면 불가능
                return True
    return False


def solution(n, build_frame):
    result = set()

    for x, y, a, build in build_frame:
        item = (x, y, a)
        if build:
            result.add(item)
            if impossible(result):  # 불가능함
                result.remove(item)
        elif item in result:
            result.remove(item)
            if impossible(result):
                result.add(item)
    answer = map(list, result)

    return sorted(answer, key=lambda x: (x[0], x[1], x[2]))