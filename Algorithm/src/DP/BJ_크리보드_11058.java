package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_크리보드_11058 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] dp = new long[N+1];
		for(int i = 1; i <= N; i++) {
			dp[i] = dp[i-1]+1;

			for(int j = 3; j < i; j++) {
				dp[i] = Math.max(dp[i], dp[i-j] * (j-1));
			}
		}
		System.out.println(Arrays.toString(dp));
		System.out.println(dp[N]);
	}
}
