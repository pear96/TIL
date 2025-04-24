from collections import deque

N = int(input())
K = int(input())
apples = {tuple(int(x)-1 for x in input().split()) for _ in range(K)}
L = int(input())
# 시계방향) 오른쪽 1, 왼쪽 -1
turns = {int(x): (1 if c == 'D' else -1) for x, c in (input().split() for _ in range(L))}


# 뱀
# 뱀은 맨위 맨좌측에 위치하고 뱀의 길이는 1 이다. 뱀은 처음에 오른쪽을 향한다.
body = deque([(0, 0)]) # 맨 앞 머리, 맨 뒤 꼬리
# 시계방향) 상 우 하 좌
dr = [-1, 0, 1, 0]
dc = [0, 1, 0, -1]
look = 1

game_time = 0

while True:
    game_time += 1
    # 뱀은 매 초마다 이동을 하는데 다음과 같은 규칙을 따른다.
    # 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
    head_r, head_c = body[0]
    nxt_head = (head_r + dr[look], head_c + dc[look])
    # 만약 벽이나 자기자신의 몸과 부딪히면 게임이 끝난다.
    if nxt_head in body or not (0 <= nxt_head[0] < N and 0 <= nxt_head[1] < N):
        break
    # 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
    if nxt_head in apples:
        apples.remove(nxt_head)
    # 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
    else:
        body.pop()
    body.appendleft(nxt_head)


    if turns.get(game_time):
        # 게임 시작 시간으로부터 X초가 끝난 뒤에 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전시킨다는 뜻이다.
        look = (look + turns[game_time]) % 4

print(game_time)