/* 원하는 정답 : 다시 칠해야하는 정사각형의 최소 개수
 * 시작) 10:10 끝) 11:03
 * [문제 상황]
 * 1. N*M 크기의 보드가 있다.
 * 2. 이 보드를 잘라 8*8의 체스판을 만든다.
 * 3. 맨 왼쪽 위가 검은색, 흰색인 두가지 경우가 있다.
 * [BFS 해결 방법] : 시간 92ms, 메모리 12120KB
 * 1. 흰색, 검은색 시작으로 M*N 2차원 배열을 2개 만든다.
 * 2. 체스판 확인을 위해 (M-8) * (N-8) 개의 시작 위치를 순회한다.
 * 3. 각 시작위치마다 2개의 종류에 대해 얼마나 다른지 개수를 센다.
 * 4. 두 개수중 더 적은 것을 정답으로 업데이트 한다.
 * [2차원 배열] : 시간 84ms, 메모리 11720KB
 * 1. 흰색, 검은색 중 하나를 골라 얼마나 일치하는지 확인한다.
 * 2. 8*8에서 일치하는 수를 뺀 경우가 반대의 색으로 시작했을 때 일치한 정사각형의 갯수이다.
 * 3. 따라서 둘 중 더 많이 일치한 수를 고르고, 다시 64에서 빼서 다시 칠해야하는 정사각형의 개수를 업데이트한다.
 * */
package baekjoon.체스판다시칠하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HE1018 {
	
	static int answer = Integer.MAX_VALUE;
	static boolean[][] board;
	
	static void check(int startR, int startC) {
		boolean color = true; // 흰색으로 시작
		int cnt = 0;
		
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
//				System.out.println(r +","+c+" " +board[startR+r][startC+c] + " " + color);
				if (board[startR+r][startC+c] == color) cnt++;
				color = !color;
			}
			color = !color; // 다음 줄은 다른 색으로 시작해야해서 홀수로 바꿔야됨
		}
		// cnt : 흰색으로 시작했을 때 제대로 칠해진 정사각형의 개수
		// 64 - cnt : 검은색으로 시작했을 때 제대로 칠해진 정사각형의 개수
		// 둘 중 더 큰 것을 고르고, 다시 칠해야하는 정사각형의 개수를 구해야하니깐 64에서 뺀다.
		cnt = cnt > 64 - cnt ? cnt : 64 - cnt;
		answer = Math.min(answer, 64 - cnt);
		
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		board = new boolean[N][M];
		
		// B면 false, W면 true
		for(int i = 0; i < N; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (input[j] == 'B') board[i][j] = false;
				else board[i][j] = true;
			}
		}
		
		// 계산
		for(int startR = 0; startR < N-7; startR++) {
			for(int startC = 0; startC < M-7; startC++) {
				check(startR, startC);
			}
		}
		System.out.println(answer);
	}

}