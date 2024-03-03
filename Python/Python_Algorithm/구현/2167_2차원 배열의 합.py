"""
2024.03.04
풀이 시간 : 대략 23분
유형 : 구현, 누적합

- N*M = 90_000, K = 10_000
- 쌩으로 매번 계산하면 K * (N*M) = 900_000_000라서 9억번이 된다.
- 그냥 dp[r][c-1] + grid[r][c] 하려다 도저히 아닌 것 같았다. (근데 다 풀어보니 다른 사람들은 이렇게 풀었다...)
- 행 마다 누적 값을 구하기로 했다. 그럼 K*(N)이다. j가 1일 경우 빼주면 안되기 때문에 처리해줬다.
- 성능은 조금 아쉽지만 빠르고 쉽게 구현할 수 있다. (이게 실버5라니..)
"""
N, M = map(int, input().split())
grid = list(list(map(int, input().split())) for _ in range(N))
row_sum = [[0] * M for _ in range(N)]

for r in range(N):
    row_sum[r][0] = grid[r][0]
    for c in range(1, M):
        row_sum[r][c] += row_sum[r][c-1] + grid[r][c]

K = int(input())

for _ in range(K):
    i, j, x, y = map(int, input().split())
    i -= 1
    j -= 2
    y -= 1
    answer = 0

    for r in range(i, x):
        answer += row_sum[r][y] - (0 if j < 0 else row_sum[r][j])

    print(answer)