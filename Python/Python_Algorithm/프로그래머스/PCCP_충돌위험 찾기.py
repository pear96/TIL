from collections import defaultdict


def solution(points, routes):
    answer = 0
    robots = len(routes)
    r, c = [0] * robots, [0] * robots  # 각 로봇의 행, 열 값
    turn = [1] * robots  # 다음에 가야하는 point의 순서
    finished_cnt = 0
    finished = [False] * robots  # 목적지에 도착했는지
    finish = len(routes[0])

    # 초기에 위험한지 확인
    pos = defaultdict(int)
    for i in range(robots):
        # 초기 위치 설정
        r[i], c[i] = points[routes[i][0] - 1]
        pos[(r[i], c[i])] += 1
        if pos[(r[i], c[i])] == 2:
            answer += 1

    # 아직 모든 로봇이 다 나가지 못 했다면
    while finished_cnt < robots:
        pos.clear()
        for i in range(robots):
            if finished[i]:
                continue
            goal_r, goal_c = points[routes[i][turn[i]] - 1]
            # 로봇 하나씩 보면서 다음에 갈 위치 찾기
            if r[i] != goal_r:
                r[i] += 1 if r[i] < goal_r else -1
            elif c[i] != goal_c:
                c[i] += 1 if c[i] < goal_c else -1

            if r[i] == goal_r and c[i] == goal_c:
                turn[i] += 1
                if turn[i] == finish:
                    finished[i] = True
                    finished_cnt += 1
            pos[(r[i], c[i])] += 1
            if pos[(r[i], c[i])] == 2:
                answer += 1

    return answer