package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1학년_5557 {
	static int N;
	static int[] num;
	static long[][] dp;
	
	static long callDp(int idx, int result) {
		if (idx == N-1) {
			return result == num[N-1] ? 1 : 0;
		}
		
		if (result < 0 || result > 20) return 0;
		
		if (dp[idx][result] != -1) return dp[idx][result];
		
		dp[idx][result] = 0;
		dp[idx][result] += callDp(idx+1, result + num[idx]);
		dp[idx][result] += callDp(idx+1, result - num[idx]);
		
		return dp[idx][result];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		dp = new long[N][21];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
			Arrays.fill(dp[i], -1);
		}
		
		System.out.println(callDp(1, num[0]));
	}

}
