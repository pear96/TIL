package _사이트.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BJ15558_점프게임 {
	
	static class Pos {
		int row;
		int col;
		int time;
		
		public Pos(int row, int col, int time) {
			this.row = row;
			this.col = col;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		boolean possible = false;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		boolean[][] safe = new boolean[2][N];
		boolean[][] visited = new boolean[2][N];
		
		for(int row = 0; row < 2; row++) {
			char[] line = br.readLine().toCharArray();
			for (int col = 0; col < N; col++) {
				safe[row][col] = (line[col] == '1')? true : false;
			}
		}
		ArrayDeque<Pos> q = new ArrayDeque<>();
		q.add(new Pos(0, 0, 0));
		visited[0][0] = true;
		
		while (!q.isEmpty()) {
			Pos now = q.pollFirst();
			
			if (now.col + 1 >= N || now.col + K >= N || now.col - 1 >= N) {
				possible = true;
				break;
			}
			if (now.col+1 > now.time && safe[now.row][now.col+1] && !visited[now.row][now.col+1]) {
				visited[now.row][now.col+1] = true;
				q.add(new Pos(now.row, now.col+1, now.time+1));
			}
			if (now.col-1 > now.time && safe[now.row][now.col-1] && !visited[now.row][now.col-1]) {
				visited[now.row][now.col-1] = true;
				q.add(new Pos(now.row, now.col-1, now.time+1));
			}
			if (now.col+K > now.time && safe[1-now.row][now.col+K] && !visited[1-now.row][now.col+K]) {
				visited[1-now.row][now.col+K] = true;
				q.add(new Pos(1-now.row, now.col+K, now.time+1));
			}
		}
		
		System.out.println((possible)? '1': '0');
	}

}
