N, M = map(int, input().split())
office = list(list(map(int, input().split())) for _ in range(N))
total_cctv = 0
wall = 0
cctv = []
unseen = N*M


class CCTV:
    def __init__(self, t, r, c):
        self.t = t
        self.r = r
        self.c = c


delta_arr = [[],
             [[(-1, 0)], [(1, 0)], [(0, -1)], [(0, 1)]],
             [[(-1, 0), (1, 0)], [(0, 1), (0, -1)]],
             [[(1, 0), (0, 1)], [(1, 0), (0, -1)], [(-1, 0), (0, 1)], [(-1, 0), (0, -1)]],
             [[(1, 0), (0, -1), (0, 1)], [(-1, 0), (0, -1), (0, 1)], [(-1, 0), (1, 0), (0, 1)],
              [(-1, 0), (1, 0), (0, -1)]],
             [[(-1, 0), (1, 0), (0, -1), (0, 1)]]
             ]


def init():
    global total_cctv, wall

    for r in range(N):
        for c in range(M):
            if 1 <= office[r][c] <= 5:
                cctv.append(CCTV(office[r][c], r, c))
                total_cctv += 1
            if office[r][c] == 6:
                wall += 1


def get_cover(dir_list):
    visited = [[0] * M for _ in range(N)]

    for idx in range(total_cctv):
        delta = delta_arr[cctv[idx].t][dir_list[idx]]
        r, c = cctv[idx].r, cctv[idx].c
        visited[r][c] = 1

        for dr, dc in delta:
            for step in range(1, 8):
                # r += dr ㅇㅈㄹ 하다가 헤멤....^^... 왜 하면 안되냐면.. 5번 CCTV 만들어서 봐바
                nr, nc = r + dr * step, c + dc * step
                if 0 <= nr < N and 0 <= nc < M and office[nr][nc] != 6:
                    visited[nr][nc] = 1
                else:
                    break

    cover_space = 0
    for r in range(N):
        for c in range(M):
            if visited[r][c]:
                cover_space += 1
    return cover_space


def make_case(idx, dir_list):
    global unseen
    dir_type = [0, 4, 2, 4, 4, 1]

    if idx == total_cctv:
        unseen = min(unseen, N*M - get_cover(dir_list) - wall)
    else:
        for d in range(dir_type[cctv[idx].t]):
            make_case(idx + 1, dir_list + [d])


def solution():
    init()
    make_case(0, [])

    print(unseen)

solution()
