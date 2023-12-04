package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BJ_직사각형탈출_16973 {
	static class Pos {
		int r;
		int c;
		
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean[][] blocked = new boolean[N][M];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				if (st.nextToken().equals("1")) {
					blocked[r][c] = true;
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int SR = Integer.parseInt(st.nextToken())-1;
		int SC = Integer.parseInt(st.nextToken())-1;
		int FR = Integer.parseInt(st.nextToken())-1;
		int FC = Integer.parseInt(st.nextToken())-1;
		
		// 미리 갈 수 없는 곳 체크해두기
		for(int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (blocked[r][c]) {
					for (int rGap = 0; rGap > -H; rGap--) {
						for (int cGap = 0; cGap > -W; cGap--) {
							if (0 <= r+rGap && r+rGap < N && 0 <= c+cGap && c+cGap < M) {
								blocked[r+rGap][c+cGap] = true;								
							}
						}
					}
				}
			}
		}
		
		ArrayDeque<Pos> q = new ArrayDeque<>();
		int[][] visited = new int[N][M];
		visited[SR][SC] = 1;
		q.add(new Pos(SR, SC));
		
		while(!q.isEmpty()) {
			Pos now = q.poll();
			
			for(int d = 0; d < 4; d++) {
				int nextRow = now.r + dr[d];
				int nextCol = now.c + dc[d];
				
				if (0 <= nextRow && nextRow + H - 1 < N && 0 <= nextCol && nextCol + W - 1 < M 
						&& visited[nextRow][nextCol] == 0 && !blocked[nextRow][nextCol]) {
					visited[nextRow][nextCol] = visited[now.r][now.c] + 1;
					q.add(new Pos(nextRow, nextCol));
				}
			}
			
		}
		
		System.out.println(visited[FR][FC]-1);
	}

}
