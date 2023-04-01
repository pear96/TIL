package baekjoon.빙산_그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HE2573 {
	
	static class Pos {
		int row;
		int col;
		
		public Pos(int r, int c) {
			this.row = r;
			this.col = c;
		}
	}
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] north;
	static boolean[][] visited;
	static int answer = 0;
	static int N, M;
	
	static void melting() {
		visited = new boolean[N][M];
		int[][] minus = new int[N][M];
		
		// 0행과 마지막 행, 0열과 마지막 열은 0으로 시작하니깐 빙산이 생길 일이 없다.
		for(int r = 1; r < N-1; r++ ) {
			for(int c = 1; c < M-1; c++) {
				if (!visited[r][c] && north[r][c] > 0) {
					visited[r][c] = true;
					
					// BFS
					Queue<Pos> queue = new LinkedList<Pos>();
					queue.add(new Pos(r, c));
					
					while(!queue.isEmpty()) {
						Pos now = queue.poll();
						
						for(int d = 0; d < 4; d++) {
							int newRow = now.row + dr[d];
							int newCol = now.col + dc[d];
							
							if (0 <= newRow && newRow < N && 0 <= newCol && newCol < M) {
								// 빙산이면 다음 위치에 추가해주고, 아니라면 빼야하는 수를 센다.
								if (visited[newRow][newCol]) continue;
								if (north[newRow][newCol] > 0) {
									queue.add(new Pos(newRow, newCol));
									visited[newRow][newCol] = true;
								}
								else minus[now.row][now.col] += 1;
							}
						}
					}
				}
			}
		}
		
		// BFS 탐색 완료. 이제 녹인다. 참고로 0이하로 떨어지면 안된다.
		for(int r = 1; r < N-1; r++) {
			for(int c= 1; c < M-1; c++) {
				north[r][c] = north[r][c] - minus[r][c];
				if (north[r][c] < 0) north[r][c] = 0;
			}
		}
	}
	
	static int counting() {
		visited = new boolean[N][M];
		int count = 0;
		
		// 0행과 마지막 행, 0열과 마지막 열은 0으로 시작하니깐 빙산이 생길 일이 없다.
		for(int r = 1; r < N-1; r++ ) {
			for(int c = 1; c < M-1; c++) {
				if (!visited[r][c] && north[r][c] > 0) {
					visited[r][c] = true;
					count += 1;
					
					// BFS
					Queue<Pos> queue = new LinkedList<Pos>();
					queue.add(new Pos(r, c));
					
					while(!queue.isEmpty()) {
						Pos now = queue.poll();
						
						for(int d = 0; d < 4; d++) {
							int newRow = now.row + dr[d];
							int newCol = now.col + dc[d];
							
							if (0 <= newRow && newRow < N && 0 <= newCol && newCol < M && north[newRow][newCol] > 0) {
								if (visited[newRow][newCol]) continue;
								queue.add(new Pos(newRow, newCol));
								visited[newRow][newCol] = true;
							}
						}
					}
				}
			}
		}
		return count;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		north = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				north[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int iceberg = 1;
		
		while(iceberg == 1) {
			melting();
			iceberg = counting();
			answer += 1;
		}
		
		if(iceberg == 0) System.out.println(0);
		else System.out.println(answer);
	}

}
