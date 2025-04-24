def solution(info, n, m):
    INF = 10**9
    # 지금까지 훔친 물건들로 B의 누적 흔적이 정확히 b일 때, 달성 가능한 A의 흔적 최솟값
    dp = [INF] * m
    dp[0] = 0 # 아직 아무 것도 훔치지 않았을 때, A·B 흔적 모두 0

    for a_i, b_i in info:
        print(f"이번에 {a_i, b_i} 물건을 훔쳐볼 것이다.")
        print(dp)
        new_dp = [INF] * m
        for b in range(m):
            print(f"지금까지 B가 {b}만큼 훔쳐올때 A의 최소값을 적어두었는데, 거기서 이번 물건 추가")
            x = dp[b]
            if x == INF:
                continue
            # A가 훔칠 때
            if x + a_i < n:
                new_dp[b] = min(new_dp[b], x + a_i)
            # B가 훔칠 때
            if b + b_i < m:
                new_dp[b + b_i] = min(new_dp[b + b_i], x)
            print(new_dp)
        dp = new_dp

    ans = min(dp)
    return ans if ans < INF else -1



print(solution([[1, 2], [2, 3], [2, 1]],4,4))