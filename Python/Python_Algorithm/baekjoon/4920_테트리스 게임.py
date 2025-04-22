def get_type1_block(N, grid):
    maximum = float('-inf')
    for r in range(N):
        for c in range(N):
            row, col = float('-inf'), float('-inf')
            # 가로
            if c + 3 < N:
                row = sum(grid[r][c:c+4])
            # 세로
            if r + 3 < N:
                col = sum(grid[_r][c] for _r in range(r, r+4))
            maximum = max(maximum, row, col)

    return maximum

def get_type2_block(N, grid):
    maximum = float('-inf')
    for r in range(N):
        for c in range(N):
            original, rotate = float('-inf'), float('-inf')
            # 원본 모양
            if r+1 < N and c+2 < N:
                original = grid[r][c] + grid[r][c+1] + grid[r+1][c+1] + grid[r+1][c+2]
            if r+2 < N and c+1 < N:
                rotate = grid[r][c+1] + grid[r+1][c] + grid[r+1][c+1] + grid[r+2][c]

            maximum = max(maximum, original, rotate)
    return maximum


def get_type3_block(N, grid):
    maximum = float('-inf')
    for r in range(N):
        for c in range(N):
            rot0, rot1, rot2, rot3 = float('-inf'), float('-inf'), float('-inf'), float('-inf')
            # 0, 180
            if r+1 < N and c+2 < N:
                rot0 = grid[r][c] + grid[r][c+1] + grid[r][c+2] + grid[r+1][c+2]
                rot2 = grid[r][c] + grid[r+1][c] + grid[r+1][c+1] + grid[r+1][c+2]
            # 90, 270
            if r+2 < N and c+1 < N:
                rot1 = grid[r][c+1] + grid[r+1][c+1] + grid[r+2][c] + grid[r+2][c+1]
                rot3 = grid[r][c] + grid[r][c+1] + grid[r+1][c] + grid[r+2][c]
            maximum = max(maximum, rot0, rot1, rot2, rot3)
    return maximum


def get_type4_block(N, grid):
    maximum = float('-inf')
    for r in range(N):
        for c in range(N):
            rot0, rot1, rot2, rot3 = float('-inf'), float('-inf'), float(
                '-inf'), float('-inf')

            # 0, 180
            if r + 1 < N and c + 2 < N:
                rot0 = grid[r][c] + grid[r][c + 1] + grid[r][c + 2] + \
                       grid[r + 1][c + 1]
                rot2 = grid[r][c+1] + grid[r + 1][c] + grid[r + 1][c + 1] + \
                       grid[r + 1][c + 2]
            # 90, 270
            if r + 2 < N and c + 1 < N:
                rot1 = grid[r][c + 1] + grid[r + 1][c] + grid[r + 1][c + 1] + \
                       grid[r + 2][c + 1]
                rot3 = grid[r][c] + grid[r+1][c] + grid[r + 1][c+1] + \
                       grid[r + 2][c]
            maximum = max(maximum, rot0, rot1, rot2, rot3)
    return maximum


def get_type5_block(N, grid):
    maximum = float('-inf')
    for r in range(N-1):
        for c in range(N-1):
            blocks = grid[r][c] + grid[r+1][c] + grid[r][c+1] + grid[r+1][c+1]
            maximum = max(maximum, blocks)
    return maximum

def solution(N, grid):
    answer = [
        get_type1_block(N, grid),
        get_type2_block(N, grid),
        get_type3_block(N, grid),
        get_type4_block(N, grid),
        get_type5_block(N, grid)
    ]
    return max(answer)


def main():
    idx = 1
    while True:
        N = int(input())
        if N == 0: return
        grid = list(list(map(int, input().split())) for _ in range(N))
        print(f"{idx}. {solution(N, grid)}")
        idx += 1


main()