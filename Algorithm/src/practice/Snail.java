package practice;

import java.util.Arrays;

public class Snail {
	static int[] len = {2,2,2};
	static int cnt;
	static int edge = 1;
	static int[][] grid;
	static int num = 1;
	
	// 우 하 좌 상
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	static void snail(int len, int r, int c) {
		int dir = 0;
		int move = 0;
		
		int nr = r;
		int nc = c;
		
		while(move < len * len) {
			// 숫자 넣기
			grid[nr][nc] = num++;
			
			// 이동
			nr += dr[dir];
			nc += dc[dir];
			
			if (r > nr || c > nc || nr >= r + len || nc >= c + len || grid[nr][nc] != 0) {
				// 이동 취소
				nr -= dr[dir];
				nc -= dc[dir];
				
				// 방향 바꿈
				dir = (dir + 1) % 4;
				
				// 다시 이동
				nr += dr[dir];
				nc += dc[dir];
			}
			move++;
		}
	}
	
	static void dq(int idx, int l, int r, int c) {
		int gridSize = len[idx];
		
		if (idx == 0) {
			snail(gridSize, r, c);
			return;
		}
		
		for(int _r = 0; _r < gridSize; _r++) {
			for(int _c = 0; _c < gridSize; _c++) {
				dq(idx-1, (l / gridSize), r + (l/gridSize) * _r, c+ (l/gridSize) * _c);
			}
		}
	}

	public static void main(String[] args) {
		cnt = len.length;
		for(int l : len) edge *= l;
		grid = new int[edge][edge];
		dq(cnt-1, edge, 0, 0);
		
		for(int i = 0; i < edge; i++) {
			System.out.println(Arrays.toString(grid[i]));
		}
	}

}
