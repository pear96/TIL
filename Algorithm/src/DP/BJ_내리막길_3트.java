package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_내리막길_3트 {
	static int N, M;
	static int[][] grid;
	// 이동 가능한 경로의 수는 10억 이하의 음이 아닌 정수. -> long이다!
	static long[][] dp;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static boolean inRange(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
	
	static long dfs(int r, int c) {
		if (r == N-1 && c == M-1) return 1;
		
		if (dp[r][c] != -1) return dp[r][c];
		
		dp[r][c] = 0;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (inRange(nr, nc) && grid[nr][nc] < grid[r][c]) {
				dp[r][c] += dfs(nr, nc);
			}
		}
		
		return dp[r][c];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		grid = new int[N][M];
		dp = new long[N][M];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				grid[r][c] = Integer.parseInt(st.nextToken());
				dp[r][c] = -1;
			}
		}
		
		System.out.println(dfs(0, 0));
	}

}
