"""
[풀이 시간] 1시간 24분
- 기사의 위치가 겹칠 수 있다고 생각했다가 아님을 깨달았다.
  -> 2차원 배열에서 기사 움직이기 힘들다...
- 사라진 기사에게 명령을 내릴 수 있음을 빼먹었다.
"""
from collections import deque

GRID, KNIGHTS, ORDERS = map(int, input().split())
chess = list(list(map(int, input().split())) for _ in range(GRID))

# 상 우 하 좌
dr = [-1, 0, 1, 0]
dc = [0, 1, 0, -1]

# 기사들 번호가 적인 2차원 배열
knights = [[0] * GRID for _ in range(GRID)]


# 기사들 정보를 담은 HashMap
class Knight:
    def __init__(self, r, c, h, w, k):
        self.r = r
        self.c = c
        self.h = h
        self.w = w
        self.health = k
        self.damage = 0

    def __repr__(self):
        return f"위치 :{self.r, self.c} / 범위 : {self.h, self.w} / 체력 : {self.health} & 데미지 : {self.damage}"


info = dict()


def init_knights():
    for i in range(KNIGHTS):
        r, c, h, w, k = map(int, input().split())
        info[i + 1] = Knight(r - 1, c - 1, h, w, k)


def draw_power():
    global knights
    # 초기화
    knights = [[0] * GRID for _ in range(GRID)]

    for idx, knight in info.items():
        for h in range(knight.h):
            for w in range(knight.w):
                knights[knight.r + h][knight.c + w] = idx


def push_knights(starter, d):
    pushed = {starter}
    q = deque([starter])

    while q:
        idx = q.popleft()
        now = info[idx]

        # 상하
        if d in (0, 2):
            nr = now.r + dr[d] if d == 0 else now.r + now.h - 1 + dr[d]
            if nr < 0 or GRID <= nr:
                return set()
            for c in range(now.w):
                nc = now.c + c
                # 벽이 있다면 모두 움직일 수 없다.
                if nc < 0 or GRID <= nc or chess[nr][nc] == 2:
                    return set()
                new = knights[nr][nc]
                if new != 0 and new not in pushed:
                    pushed.add(new)
                    q.append(new)

        # 좌우
        else:
            nc = now.c + dc[d] if d == 3 else now.c + now.w - 1 + dc[d]
            if nc < 0 or GRID <= nc:
                return set()
            for r in range(now.h):
                nr = now.r + r
                if nr < 0 or GRID <= nr or chess[nr][nc] == 2:
                    return set()
                new = knights[nr][nc]
                if new != 0 and new not in pushed:
                    pushed.add(new)
                    q.append(new)

    return pushed


def move_knights(will_move, d):
    for knight in will_move:
        info[knight].r += dr[d]
        info[knight].c += dc[d]


def calculate_damage(moved, starter):
    moved.remove(starter)
    disappear = set()

    for number in moved:
        knight = info[number]
        damage = 0

        for h in range(knight.h):
            for w in range(knight.w):
                if chess[knight.r + h][knight.c + w] == 1:
                    damage += 1

        knight.damage += damage
        if knight.health <= knight.damage:
            disappear.add(number)

    for number in disappear:
        info.pop(number)


def get_answer():
    answer = 0
    for knight in info.values():
        answer += knight.damage
    print(answer)


def solution():
    # 기사 정보 기록
    init_knights()
    # 명령 수행
    for _ in range(ORDERS):
        # 현재 기사 정보에 따른 영향력 2차원 배열에 기록
        number, direction = map(int, input().split())
        if not info.get(number):
            continue
        draw_power()
        # 이동해야할 기사들 가져오기
        will_move = push_knights(number, direction)
        if will_move:
            # 실제로 이동하기
            move_knights(will_move, direction)
            # 이동한 결과 반영
            draw_power()
            # 함정 계산
            calculate_damage(will_move, number)

    get_answer()


solution()
