package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_내리막길_1520 {
	static int M, N;
	static int[][] map;
	static long[][] dp;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static long dfs(int row, int col) {
		// 끝 도달에 성공했으니, 1개 경우의 수 추가
		// row와 col이 마지막 지점에 도달하는 경우는 딱 한번이다.
		// 한번 끝에 도달했으면 1부터 시작하여 계속 쌓아나간다.
		if (row == M-1 && col == N-1) return 1;
		
		// 이미 계산된 곳이라면, 추가만 하면 된다.
		if (dp[row][col] != -1) return dp[row][col];
		
		// 0으로 초기화
		dp[row][col] = 0;
		
		for(int d = 0; d < 4; d++) {
			int nr = row + dr[d];
			int nc = col + dc[d];
			
			if (0 > nr || nr >= M || 0 > nc || nc >= N) continue;
			if (map[row][col] > map[nr][nc]) {
				// 나보다 작은 곳들까지 가는데 몇개의 루트가 있는지 추가함
				dp[row][col] += dfs(nr, nc);
			}
		}
		
		// 이 위치에서 구한 경우의 수는, dfs 호출 이전의 위치에 추가된다.
		return dp[row][col];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		dp = new long[M][N];
		
		for(int r = 0; r < M; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				dp[r][c] = -1;
			}
		}
		
		System.out.println(dfs(0, 0));
	}

}
