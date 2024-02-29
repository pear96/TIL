from itertools import permutations

N, M, K = map(int, input().split())
original_grid = list(list(map(int, input().split())) for _ in range(N))
infos = list()
answer = float('inf')

for _ in range(K):
    r, c, s = map(int, input().split())
    infos.append((r-1, c-1, s))

# 회전 연산 순서에 따른 모든 케이스
perms = permutations(infos, K)


def copy_grid(grid, sr, sc, s):
    arr = [[0] * M for _ in range(N)]

    for r in range(sr - s, sr + s + 1):
        for c in range(sc - s, sc + s + 1):
            arr[r][c] = grid[r][c]

    return arr


def get_num(grid):
    num = float('inf')

    for row in grid:
        num = min(num, sum(row))

    return num


def rotate_grid(input_grid, orders):
    grid = []
    for row in input_grid:
        grid.append(row[:])

    for sr, sc, s in orders:
        arr = copy_grid(grid, sr, sc, s)

        # 한 테두리씩 이동
        for gap in range(s):
            # 맨 윗줄에서 오른쪽으로 이동
            for right in range((s-gap)*2):
                grid[sr-s+gap][sc-s+gap+right+1] = arr[sr-s+gap][sc-s+gap+right]
            # 맨 오른쪽 줄에서 아래로
            for down in range((s-gap)*2):
                grid[sr-s+gap+down+1][sc+s-gap] = arr[sr-s+gap+down][sc+s-gap]
            # 맨 아랫줄에서 왼쪽으로
            for left in range((s-gap)*2):
                grid[sr+s-gap][sc+s-gap-left-1] = arr[sr+s-gap][sc+s-gap-left]
            # 맨 왼쪽 줄에서 위
            for up in range((s-gap)*2):
                grid[sr+s-gap-up-1][sc-s+gap] = arr[sr+s-gap-up][sc-s+gap]
    # 값 구하기
    return get_num(grid)


for perm in perms:
    answer = min(answer, rotate_grid(original_grid, perm))

print(answer)