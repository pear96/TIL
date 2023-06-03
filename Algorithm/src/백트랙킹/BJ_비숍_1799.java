package 백트랙킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_비숍_1799 {
	static int N;
	static int[] answer = new int[2];
	static int[][] board;
	static boolean[][] isBlack;
	
	static int[] dr = new int[] {-1, -1};
	static int[] dc = new int[] {-1, 1};
	
	static boolean inRange(int row, int col) {
		return 0 <= row && row < N && 0 <= col && col < N;
	}
	
	static boolean possible(int row, int col) {
		// 어차피 내려가면서 만드니까 아래엔 비숍이 없다.
		for(int d = 0; d < 2; d++) {
			int nr = row;
			int nc = col;
			while(true) {
				if (!inRange(nr, nc)) break;
				if (board[nr][nc] == 2) return false;
				nr += dr[d];
				nc += dc[d];
			}
		}
		return true;
	}
	
	static void makeCase(int idx, boolean black, int cnt) {
		for(int i = idx+1; i < N*N; i++) {
			int r = i / N;
			int c = i % N;
			
			if (isBlack[r][c] != black || board[r][c] != 1 || !possible(r, c)) continue;
			
			board[r][c] = 2;
			makeCase(i, black, cnt+1);
			board[r][c] = 1;
		}
		answer[black? 0 : 1] = Math.max(answer[black? 0 : 1], cnt);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 체스판의 크기
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		isBlack = new boolean[N][N];
		
		// 체스판 입력 (1: 둘 수 있음, 0 : 둘 수 없음, 2: 이미 있음)
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				isBlack[i][j] = (i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1);
			}
		}
		makeCase(-1, true, 0);
		makeCase(-1, false, 0);
		
		System.out.println(answer[0] + answer[1]);
	}

}
