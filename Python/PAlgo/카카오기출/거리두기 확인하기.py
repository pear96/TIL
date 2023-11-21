from collections import deque

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]
ROOM = 5


def in_range(r, c):
    return 0 <= r < ROOM and 0 <= c < ROOM


def check(place):
    visited = [[False] * ROOM for _ in range(ROOM)]

    for row in range(ROOM):
        for col in range(ROOM):
            if place[row][col] == "P":
                visited[row][col] = True
                q = deque([(row, col)])

                while q:
                    r, c = q.popleft()

                    for d in range(4):
                        nr = r + dr[d]
                        nc = c + dc[d]

                        if in_range(nr, nc) and not visited[nr][nc] and (abs(row - nr) + abs(col - nc)) <= 2:
                            if place[nr][nc] == "P":
                                return 0
                            if place[nr][nc] == "O":
                                visited[nr][nc] = True
                                q.append((nr, nc))
    return 1


def solution(places):
    answer = []

    for place in places:
        answer.append(check(place))

    return answer