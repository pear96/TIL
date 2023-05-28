package DP;

/* https://school.programmers.co.kr/learn/courses/30/lessons/12945
 * 다 풀고 % 연산 까먹음
 * */

public class PG_피보나치수 {
	public int solution(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        
        for(int i = 2; i <= n; i++) {
            dp[i] = (dp[i-2] + dp[i-1]) % 1234567;
        }
        
        return dp[n];
    }
}
