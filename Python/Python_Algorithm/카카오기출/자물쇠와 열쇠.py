def check(key, lock, start_r, start_c, holes, N, M):
    filled = 0  # 채운 홈 개수
    for r in range(M):
        if start_r + r >= N:
            break
        for c in range(M):
            if start_c + c >= N:
                break
            if lock[start_r + r][start_c + c] == 0 and key[r][c] == 1:
                filled += 1
            if lock[start_r + r][start_c + c] == 1 and key[r][c] == 1:
                return False
    if filled == holes:
        print("정답 찾음")
        print(f"위치 : {start_r, start_c}")
        print("자물쇠 모양")
        for l in key:
            print(*l)
    return True if filled == holes else False


def solution(key, lock):
    holes = 0
    N, M = len(lock), len(key)

    for r in range(N):
        for c in range(N):
            holes += 1 if lock[r][c] == 0 else 0

    keys = [key]

    copy_key = [line[:] for line in key]
    for _ in range(3):
        rotated = list(map(list, zip(*copy_key[::-1])))
        keys.append(rotated)
        copy_key = [line[:] for line in rotated]

    for r in range(N):
        for c in range(N):
            for rotated_key in keys:
                if check(rotated_key, lock, r, c, holes, N, M):
                    return True

    return False

print(solution([[0, 0, 0], [1, 0, 0], [0, 1, 1]], [[1, 1, 1], [1, 1, 0], [1, 0, 1]]))