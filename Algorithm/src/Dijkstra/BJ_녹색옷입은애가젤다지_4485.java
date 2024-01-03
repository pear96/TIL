package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_녹색옷입은애가젤다지_4485 {
	static class Node implements Comparable<Node> {
		int row;
		int col;
		int rp;

		public Node (int row, int col, int rp) {
			this.row = row;
			this.col = col;
			this.rp = rp;
		}

		public int compareTo(Node o) {
			return this.rp - o.rp;
		}
	}

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int turn = 1;
		while (true) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0) break;
			int[][] cave = new int[N][N];
			int[][] rupee = new int[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					cave[r][c] = Integer.parseInt(st.nextToken());
				}
				Arrays.fill(rupee[r], Integer.MAX_VALUE);
			}

			rupee[0][0] = cave[0][0];
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.add(new Node(0, 0, cave[0][0]));

			while(!pq.isEmpty()) {
				Node now = pq.poll();
				// 다른 위치 확인
				for (int d = 0; d < 4; d++) {
					int nr = now.row + dr[d];
					int nc = now.col + dc[d];
					if (0 > nr || N <= nr || 0 > nc || N <= nc) continue;
					// 등호 빼먹어서 중복처리가 안됨
					if (rupee[nr][nc] <= cave[nr][nc] + rupee[now.row][now.col]) continue;
					rupee[nr][nc] = cave[nr][nc] + rupee[now.row][now.col];
					pq.add(new Node(nr, nc, cave[nr][nc]));
				}
			}
			System.out.println("Problem "+ turn++ + ": " +rupee[N-1][N-1]);
		}
	}
}
