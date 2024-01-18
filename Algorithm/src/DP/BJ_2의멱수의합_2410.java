package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2의멱수의합_2410 {
	public static void main(String[] args) throws IOException {
		final int DIV = 1_000_000_000;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		dp[1] = 1;
		for (int i = 2; i <= N; i++) {
			if (i % 2 == 0) dp[i] = (dp[i-1] + dp[i/2]) % DIV;
			else dp[i] = dp[i-1];
		}
		System.out.println(dp[N]);
	}
}
