package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class BJ_1학년_3트 {
	static int N;
	static long[][] dp;
	static int[] nums;
	
	static long dfs(int idx, int num) {
		// 종료 조건
		if (idx == N-1) {
			if (num == nums[N-1]) return 1;
			return 0;
		}
		// 안되는 조건
		if (0 > num || 20 < num) return 0;

		// 백트래킹
		if (dp[idx][num] != -1) return dp[idx][num];
		
		// 계산하기
		dp[idx][num] = 0;
		dp[idx][num] += dfs(idx+1, num + nums[idx]);
		dp[idx][num] += dfs(idx+1, num - nums[idx]);
		
		return dp[idx][num];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		dp = new long[N][21];
		nums = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			Arrays.fill(dp[i], -1);
		}
		
		System.out.println(dfs(1, nums[0]));
		
	}
}
