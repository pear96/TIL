package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class BJ_호텔_3트 {
	public static void main(String[] args) throws IOException {
		int[] dp = new int[1101];
		Arrays.fill(dp, 100_001); // 1명당 100원
		dp[0] = 0;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] cost = new int[N];
		int[] add = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cost[i] = Integer.parseInt(st.nextToken());
			add[i] = Integer.parseInt(st.nextToken());
		}

		// 0명 + x, 1명 + x 식으로 계산함. 1000 * 20 밖에 안됨
		for (int people = 0; people < 1101; people++) {
			for (int city = 0; city < N; city++) {
				if (people + add[city] <= 1100) {
					dp[people+add[city]] = Math.min(dp[people+add[city]], dp[people] + cost[city]);
				}
			}
		}

		// '적어도 C명 이상' 이기 때문에 더 많은 사람을 더 싸게 구할 수도 있다.
		int answer = Integer.MAX_VALUE;
		for (int people = 1100; people >= C; people--) {
			answer = Math.min(answer, dp[people]);
		}
		System.out.println(answer);
	}
}
