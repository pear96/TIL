from itertools import combinations

N, M, D = map(int, input().split())
init_field = list(list(map(int, input().split())) for _ in range(N))
enemy_cnt = sum(sum(init_field[i]) for i in range(N))


def archer_attack(positions, field):
    # 공격 당한 적의 위치{(row, col)}를 저장한다.
    # 동시에 공격 처리를 위해서 위치를 찾았다고 바로 필드에서 0으로 바꾸지 않는다.
    attacked = set()

    # 궁수 위치 별로 확인한다.
    for archer_col in positions:
        enemy_row, enemy_col, distance = -1, -1, N*M

        # 궁수는 아래에 있으므로 아래부터 확인한다.
        for row in range(N-1, -1, -1):
            for col in range(M):
                if field[row][col]:
                    now_distance = abs(N - row) + abs(archer_col - col)
                    # 거리가 같은 경우 왼쪽 우선이다. (>= 하면 오른쪽, > 하면 왼쪽)
                    # -> 이렇게 하면 행이 더 먼데 열이 더 왼쪽인 경우를 못 찾는다.
                    if D >= now_distance and (distance, enemy_col) > (now_distance, col):
                        distance = now_distance
                        enemy_row, enemy_col = row, col

        # 공격할 대상을 찾았을 경우 attacked에 추가한다.
        if enemy_row != -1 and enemy_col != -1:
            attacked.add((enemy_row, enemy_col))

    for row, col in attacked:
        field[row][col] = 0

    return len(attacked)


def move_enemy(field):
    enemy_in_castle = 0

    # 맨 마지막 줄을 먼저 처리한다.
    for col in range(M):
        if field[N-1][col]:
            enemy_in_castle += 1
            field[N-1][col] = 0

    # 역순으로 올라가면서 적을 아래로 이동한다.
    for row in range(N-2, -1, -1):
        for col in range(M):
            if field[row][col]:
                field[row][col] = 0
                field[row+1][col] = 1

    return enemy_in_castle


def game(archer_position_comb, init_field):
    archer_kill_enemy_cnt = 0
    castle_enemy_cnt = 0
    field = list([*init_field[i]] for i in range(N))

    while archer_kill_enemy_cnt + castle_enemy_cnt < enemy_cnt:
        archer_kill_enemy_cnt += archer_attack(archer_position_comb, field)
        castle_enemy_cnt += move_enemy(field)

    return archer_kill_enemy_cnt


def solution():
    most_killed_enemy_count = 0

    for archer_position_comb in combinations(range(M), 3):
        most_killed_enemy_count = max(most_killed_enemy_count, game(archer_position_comb, init_field))
    print(most_killed_enemy_count)


solution()