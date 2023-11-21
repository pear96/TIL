package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_럭키세븐_28706 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			boolean[][] dp = new boolean[N+1][7];
			dp[0][1] = true;

			boolean[][] op = new boolean[N+1][2];
			int[][] num = new int[N+1][2];
			
			for (int n = 1; n <= N; n++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int i = 0; i < 2; i++) {
					op[n][i] = ("*".equals(st.nextToken()))? true : false;
					num[n][i] = Integer.parseInt(st.nextToken());
				}
//				System.out.println(Arrays.toString(op[n]));
//				System.out.println(Arrays.toString(num[n]));
				
				for (int v = 0; v < 7; v++) {
					if (dp[n-1][v]) {
						for (int j = 0; j < 2; j++) {
							int newV = (op[n][j]) ? v * num[n][j] : v + num[n][j];
							dp[n][newV % 7] = true;
						}
					}
				}
			}
			
			
//			for (int n = 0; n <= N; n++) {
//				System.out.println(n + Arrays.toString(dp[n]));
//			}
//			System.out.println();
			
			System.out.println((dp[N][0])? "LUCKY" : "UNLUCKY");
		}
	}
}
