package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_퇴사2_15486 {
	static int N;
	static int[] p, t, dp;
	
	static int dpMethod(int day) {
		if (day == N) return 0;
		
		if (dp[day] != -1) return dp[day];
		
		dp[day] = dpMethod(day+1);
		if (day + t[day] <= N) {
			dp[day] = Math.max(dp[day], p[day] + dp[day+t[day]]);
		}
		return dp[day];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		t = new int[N];
		p = new int[N];
		dp = new int[N+1];
		
		for(int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			t[i] = Integer.parseInt(input[0]);
			p[i] = Integer.parseInt(input[1]);
		}
		
		for(int day = N-1; day >= 0 ; day--) {
			dp[day] = dp[day+1];
			if (day + t[day] <= N) {
//				System.out.println("day : " + day + ", day-t[day] : " + (day-t[day]) + ", dp[day - t[day] : " + (dp[day - t[day]]) + ", p[day] : " + p[day] + ", dp[day] : " + dp[day]);
				dp[day] = Math.max(dp[day], p[day] + dp[day + t[day]]);
			}
		}
		System.out.println(dp[0]);
		
	}

}
