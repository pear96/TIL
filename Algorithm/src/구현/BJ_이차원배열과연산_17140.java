package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ_이차원배열과연산_17140 {

	static class Number implements Comparable<Number> {
		int num;
		int cnt;

		public Number(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}

		public int compareTo(Number other) {
			if (this.cnt == other.cnt) return this.num - other.num;
			return this.cnt - other.cnt;
		}
	}

	static int[] sortByNumber(HashMap<Integer, Integer> appear) {
		Number[] info = new Number[appear.size()];
		int idx = 0;

		for (Map.Entry<Integer, Integer> pair : appear.entrySet()) {
			info[idx++] = new Number(pair.getKey(), pair.getValue());
		}
		Arrays.sort(info);

		// 100개 이상 안되게
		int total = Math.min(info.length, 50);

		int[] row = new int[total * 2];
		for (int i = 0; i < total; i++) {
			row[i*2] = info[i].num;
			row[i*2+1] = info[i].cnt;
		}
		return row;
	}

	static int[][] calR(int[][] grid, int row, int col) {
		int[][] result = new int[row][];
		int maxCol = 0;

		// 1. 한 행씩 숫자의 등장 횟수를 저장하기
		for (int r = 0; r < row; r++) {
			HashMap<Integer, Integer> appear = new HashMap<>();
			for (int c = 0; c < col; c++) {
				appear.put(grid[r][c], appear.getOrDefault(grid[r][c], 0) + 1);
			}
			appear.remove(0);
			result[r] = sortByNumber(appear);
			maxCol = Math.max(maxCol, result[r].length);
		}

		// 2. 0 채우기
		for (int r = 0; r < row; r++) {
			if (result[r].length < maxCol) {
				int[] extend = new int[maxCol];
				System.arraycopy(result[r], 0, extend, 0, result[r].length);
				result[r] = extend;
			}
		}

		return result;
	}

	static int[][] reverse(int[][] grid, int row, int col) {
		int[][] reversed = new int[col][row];

		for (int c = 0; c < col; c++) {
			for (int r = 0; r < row; r++) {
				reversed[c][r] = grid[r][c];
			}
		}

		return reversed;
	}
	static int[][] calC(int[][] grid, int row, int col) {
		int[][] reversed = reverse(grid, row, col);
		int[][] calculated = calR(reversed, col, row);
		return reverse(calculated, calculated.length, calculated[0].length);
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		final int R = Integer.parseInt(st.nextToken()) - 1;
		final int C = Integer.parseInt(st.nextToken()) - 1;
		final int K = Integer.parseInt(st.nextToken());
		int answer = 0;
		int[][] grid = new int[3][3];

		// 1. 초기 정보 입력
		for (int r = 0; r < 3; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 3; c++) {
				grid[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		while (answer <= 100) {
			int row = grid.length;
			int col = grid[0].length;

			if (row > R && col > C && grid[R][C] == K) break;

			// 2. 행, 열 비교
			if (row >= col) {
				grid = calR(grid, row, col);
			} else {
				grid = calC(grid, row, col);
			}
			answer++;
		}
		if (answer > 100) answer = -1;
		System.out.println(answer);
	}
}
