package baekjoon.알파벳_그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HE1987 {
	
	static int row, col;
	static char[][] board;
	static boolean[] visited; // 어차피 알파벳당 한번씩이니까, 2차원 배열로 볼 필요가 없다.
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	static int answer = 1;
	
	static void DFS(int r, int c, int cnt) {
		visited[board[r][c] - 'A'] = true;
		if(answer < cnt) {
			answer = cnt;
		}
		
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 1. 범위를 넘어가는지
			if(0 > nr || row <= nr || 0 > nc || col <= nc) continue;

			// 2. 이미 방문했는지
			if(visited[board[nr][nc] - 'A']) continue;
			
			// 여기로 간다.
			DFS(nr, nc, cnt+1);
			
			// 나왔다.
			visited[board[nr][nc] - 'A'] = false;
		}
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		board = new char[row][col];
		visited = new boolean[26];
		
		// 입력 초기화
		for(int i = 0; i < row; i++) {
			char[] line = br.readLine().toCharArray();
			for(int j = 0; j < col; j++) {
				board[i][j] = line[j];
			}
		}
		
		DFS(0, 0, 1);
		
		System.out.println(answer);
	}

}
