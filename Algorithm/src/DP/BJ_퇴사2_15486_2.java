package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Bottom up으로 풀면 Python은 되는데 Java는 안됐던 문제
 * */

public class BJ_퇴사2_15486_2 {
	static int N;
	static int[] dp;
	static int[] pay;
	static int[] day;
	
	static int callDp(int today) {
		if (today == N) return 0;
		
		if (dp[today] != -1) return dp[today];
		
		dp[today] = 0;
		
		// 오늘 일을 한 경우
		if (today + day[today] <= N) {
			dp[today] = pay[today] + callDp(today + day[today]);
		}
		// 오늘 일을 안(못)한 경우
		dp[today] = Math.max(dp[today], callDp(today+1));
		
		return dp[today];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N];
		day = new int[N];
		pay = new int[N];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int workDay = Integer.parseInt(st.nextToken());
			int workPay = Integer.parseInt(st.nextToken());
			
			dp[i] = -1;
			day[i] = workDay;
			pay[i] = workPay;
		}
		
		System.out.println(callDp(0));
//		System.out.println(Arrays.toString(dp));
	}

}
