package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_숫자카드_2591_2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int len = input.length();
		int[] nums = new int[len];
		int[] dp = new int[len];
		
		// 숫자 배열 채우기
		for (int i = 0; i < len; i++) {
			nums[i] = input.charAt(i) - '0';
		}
		
		// dp 배열 채우기
		dp[0] = 1;
		for (int i = 1; i < len; i++) {
			if (nums[i] != 0) {
				dp[i] += dp[i-1];				
			}
			
			if (nums[i-1] != 0 && (nums[i-1] * 10 + nums[i] <= 34)) {
				dp[i] += i >= 2 ? dp[i - 2] : 1;
			}
		}
		
		System.out.println(Arrays.toString(dp));
		System.out.println(dp[len-1]);
	}

}
