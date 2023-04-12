/* 원하는 정답 : 다시 칠해야하는 정사각형의 최소 개수
 * 시작) 10:10 끝) 11:03
 * [문제 상황]
 * 1. N*M 크기의 보드가 있다.
 * 2. 이 보드를 잘라 8*8의 체스판을 만든다.
 * 3. 맨 왼쪽 위가 검은색, 흰색인 두가지 경우가 있다.
 * [해결 방법]
 * 1. 흰색, 검은색 시작으로 M*N 2차원 배열을 2개 만든다.
 * 2. 체스판 확인을 위해 (M-8) * (N-8) 개의 시작 위치를 순회한다.
 * 3. 각 시작위치마다 2개의 종류에 대해 얼마나 다른지 개수를 센다.
 * 4. 두 개수중 더 적은 것을 정답으로 업데이트 한다. 
 * */
package baekjoon.체스판다시칠하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class HE1018 {
	static int N;
	static int M;
	static int[] dr = {0, 1};
	static int[] dc = {1, 0};
	
	static class Pos {
		int r;
		int c;
		
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static boolean inRange(int r, int c) {
		if (0 <= r && r < N && 0 <= c && c < M) return true;
		return false;
	}
	
	static boolean[][] fill(boolean start) {
		boolean[][] arr = new boolean[N][M];
		boolean[][] visited = new boolean[N][M];
		LinkedList<Pos> queue = new LinkedList<>();
		
		arr[0][0] = start;
		queue.add(new Pos(0, 0));
		visited[0][0] = true;
		
		while(!queue.isEmpty()) {
			Pos now = queue.poll();
			for(int d = 0; d < 2; d++) {
				int nextR = now.r + dr[d];
				int nextC = now.c + dc[d];
				if (inRange(nextR, nextC) && !visited[nextR][nextC]) {
					arr[nextR][nextC] = !arr[now.r][now.c];
					visited[nextR][nextC] = true;
					queue.add(new Pos(nextR, nextC));
				}
			}
			
		}
		return arr;
	}

	public static void main(String[] args) throws IOException {
		int answer = Integer.MAX_VALUE;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		boolean[][] black = fill(false);
		boolean[][] white = fill(true);
		
		boolean[][] board = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (input[j] == 'B') board[i][j] = false;
				else board[i][j] = true;
			}
		}
		
		for(int startR = 0; startR < N-7; startR++) {
			for(int startC = 0; startC < M-7; startC++) {
				int blackCnt = 0;
				int whiteCnt = 0;
				
				for (int r = 0; r < 8; r++) {
					for (int c = 0; c < 8; c++) {
						if (black[startR+r][startC+c] != board[startR+r][startC+c]) blackCnt += 1;
						if (white[startR+r][startC+c] != board[startR+r][startC+c]) whiteCnt += 1;
					}
				}
				int minFix = Math.min(whiteCnt, blackCnt);
				answer = Math.min(answer, minFix);
			}
		}
		System.out.println(answer);
	}

}