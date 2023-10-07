package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_로또_2758 {
	static int N, M;
	static long[][] dp;
	
//	static int callDp(int idx, int result) {
//		if (idx == N-1) return 1;
//		
//		if (result > M) return 0;
//		
//		if (dp[idx][result] != -1) return dp[idx][result];
//		
//		dp[idx][result] = 0;
//		
//		for(int nextNum = result*2; nextNum <= (M / Math.pow(2, N-idx-2)); nextNum++) {
//			dp[idx][result] += callDp(idx+1, nextNum);
//		}
//		
//		return dp[idx][result];
//		
//	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			dp = new long[N+1][M+1];
			// '특정한 수'로 끝나는 걸 찾는게 아니라, 모든 경우의 수를 찾는다.
			// dp[i][j] 는 i번째 수를 선택했을 때 1부터 j까지의 경우의 수이다.
			// N = 4, M = 10에서 dp[4][8] => 1이다. (1 2 4 8)
			// dp[4][9] 는 (1 2 4)에 9를 마지막에 더한 것과, 누적이므로 (1 2 4 8)이라는 경우도 포함한다.
			
			Arrays.fill(dp[0], 1);
			
			for (int cnt = 1; cnt <= N; cnt++) {
				for (int max = 1; max <= M; max++) {
					// 지금 숫자(max)를 붙이기 전 개수 : dp[cnt-1][max/2]
					// 누적을 위해 이전 것도 포함 : dp[cnt][max-1]
					dp[cnt][max] = dp[cnt-1][max / 2] + dp[cnt][max-1];
				}
			}
			System.out.print(dp[N][M]);
		}
	}

}
