package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_가장큰정사각형_1915 {
	static int N, M;
	
	static boolean inRange(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		boolean[][] grid = new boolean[N][M];
		int[][] dp = new int[N][M]; // (x, y) 위치에서 가능한 최대 정사각형의 크기
		
		for (int r = 0; r < N; r++) {
			char[] line = br.readLine().toCharArray();
			for (int c = 0; c < M; c++) {
				grid[r][c] = line[c] == '1' ? true : false;
			}
		}
		
		
		for (int r = N-1; r >= 0; r--) {
			for (int c = M-1; c >= 0; c--) {
				if (grid[r][c]) {
					int nr = (inRange(r+1, c) && dp[r+1][c] > 0) ? dp[r+1][c] : 0; 
					int nc = (inRange(r, c+1) && dp[r][c+1] > 0) ? dp[r][c+1] : 0; 
					int nd = (inRange(r+1, c+1) && dp[r+1][c+1] > 0) ? dp[r+1][c+1] : 0;
					
					dp[r][c] = Math.min(nr, Math.min(nc, nd)) + 1;
				}
			}
		}
		
		int answer = 0;
		for (int r = 0; r < N; r++) {
			answer = Math.max(answer, Arrays.stream(dp[r]).max().getAsInt());
		}
		System.out.println(answer*answer);
	}

}
