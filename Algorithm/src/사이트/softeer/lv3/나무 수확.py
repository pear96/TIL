N = int(input())
tree = [[0] * (N+1) for _ in range(N+1)]

for i in range(1, N+1):
    tree[i][1:] = list(map(int, input().split()))

# 스프링 쿨러를 설치 한 경우 / 설치하지 않은 경우로 총 3차원 DP를 만들어야함
did = [[0] * (N+1) for _ in range(N+1)]
yet = [[0] * (N+1) for _ in range(N+1)]

did[1][1] = tree[0][0] * 2
yet[1][1] = tree[0][0]


def solution():
    for r in range(1, N+1):
        for c in range(1, N+1):
            # 스프링 쿨러를 설치하지 않을거면 별 차이는 없음
            yet[r][c] = max(yet[r-1][c], yet[r][c-1]) + tree[r][c]

            # 스프링 쿨러를 설치할 때 이제 조금 어려움
            # 이미 스프링 쿨러가 설치된 값을 사용해서 현재 위치에 그냥 수도만 연결하는 값과
            # 스프링 쿨러가 아직 설치되지 않은 값을 사용해서 현재 위치에 스프링 쿨러를 설치하는 값 중
            # 더 큰 값을 비교해가면 된다.
            did[r][c] = max(max(did[r-1][c], did[r][c-1]) + tree[r][c], max(yet[r-1][c], yet[r][c-1]) + tree[r][c]*2)

    print(did[N][N])

solution()