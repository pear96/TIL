package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_내리막길_1520_2 {
	static int R, C;
	static int[][] grid;
	static int[][] dp;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static boolean inRange(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
	
	static int dfs(int r, int c) {
		// 마지막에 도달하면 경우의 수 +1
		if (r == R-1 & c == C-1) return 1;
		
		// 이미 앞에서 방문하고 갔다면 스겜
		// 0으로 하면 갈 수 없는 곳을 계속 자꾸 방문하게 되어 시간초과 발생
		if (dp[r][c] != -1) return dp[r][c];
		
		dp[r][c] = 0;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (inRange(nr, nc) && grid[nr][nc] < grid[r][c]) {
				// 어디 갔다와 보거라
				dp[r][c] += dfs(nr, nc);
			} 
		}
		
		return dp[r][c];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		grid = new int[R][C];
		dp = new int[R][C];
		
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				grid[r][c] = Integer.parseInt(st.nextToken());
				dp[r][c] = -1;
			}
		}
		
		dfs(0, 0);
		
//		for (int r = 0; r < R; r++) {
//			System.out.println(Arrays.toString(dp[r]));
//		}
		
		System.out.println(dp[0][0]);
		

	}

}
