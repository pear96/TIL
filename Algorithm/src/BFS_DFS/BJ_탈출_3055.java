package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BJ_탈출_3055 {
	static final int WATER = 1;
	static final int ROCK = 2;
	static final int START = 3;
	static final int EXIT = 4;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int R, C;
	static int[][] grid;
	static int[][] spread;
	
	static Pos start;
	static Pos exit;
	
	
	static class Node extends Pos {
		int t;

		public Node(int r, int c, int t) {
			super(r, c);
			this.t = t;
		}
	}
	
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
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		grid = new int[R][C];
		init(br);
		int time = bfs();
		
		if (time == 0) System.out.println("KAKTUS");
		else System.out.println(time-1);
		
		
	}
	
	static void init(BufferedReader br) throws IOException {
		for(int i = 0; i < R; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				char value = line[j];
				
				// 빈칸은 자연스럽게 0이 됨
				switch (value) {
					case '*':
						grid[i][j] = WATER;
						break;
					case 'X':
						grid[i][j] = ROCK;
						break;
					case 'S':
						grid[i][j] = START;
						start = new Pos(i, j);
						break;
					case 'D':
						grid[i][j] = EXIT;
						exit = new Pos(i, j);
						break;
				}
			}
		}
	}
	
	static int bfs() {		
		ArrayDeque<Node> deque = new ArrayDeque<>();
		int[][] visited = new int[R][C];
		
		deque.add(new Node(start.r, start.c, 0));
		visited[start.r][start.c] = 1;
		int time = -1;
		
		while(!deque.isEmpty()) {
			Node now = deque.pollFirst();
			
			if (now.t != time) {
				waterSpread();
				time = now.t;
			}
			
			for(int d = 0; d < 4; d++) {
				int nr = now.r + dr[d];
				int nc = now.c + dc[d];
				
				if (inRange(nr, nc) && visited[nr][nc] == 0 && (grid[nr][nc] == 0 || grid[nr][nc] == EXIT)) {
					deque.add(new Node(nr, nc, now.t+1));
					visited[nr][nc] = visited[now.r][now.c] + 1;
				}
			}
			
		}
		
		return visited[exit.r][exit.c];
	}
	
	static void waterSpread() {
		spread = new int[R][C];
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (grid[r][c] == WATER) {
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if (inRange(nr, nc) && grid[nr][nc] == 0) {
							spread[nr][nc] = 1;
						}
					}
				}
			}
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (spread[r][c] == 1) {
					grid[r][c] = WATER;
				}
			}
		}
	}
	
	static boolean inRange(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}

}
