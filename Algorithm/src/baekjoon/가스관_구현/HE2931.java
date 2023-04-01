package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
	int r, c;
	
	public Pair(int r, int c) {
		this.r = r;
		this.c = c;
	}
	
}

public class HE2931 {
	static int R, C;
	
	static Pair north = new Pair(-1, 0);
	static Pair south = new Pair(1, 0);
	static Pair east = new Pair(0, 1);
	static Pair west = new Pair(0, -1);
	static Pair[][] block = new Pair[][] {
		{},
		{north, south},
		{west, east},
		{north, south, east, west},
		{south, east},
		{north, east},
		{north, west},
		{south, west} // 이게되네?
	};
	
	static Pair[] delta = {north, east, south, west};
	
	static boolean inRange(int r, int c) {
		if(0 <= r && r < R && 0 <= c && c < C) return true;
		return false;
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int[][] plan = new int[R][C];
		boolean[][] visited = new boolean[R][C];
		Pair moscow = new Pair(0, 0);
		Pair blankSpace;
		ArrayList<Pair> blankDir = new ArrayList<Pair>();
		
		for(int i=0; i<R; i++) {
			String input = br.readLine();
			for(int j=0; j<C; j++) {
				char type = input.charAt(j);
				switch (type) {
				case '.': plan[i][j] = 0; break;
				case '|': plan[i][j] = 1; break;
				case '-': plan[i][j] = 2; break;
				case '+': plan[i][j] = 3; break;
				case '1': plan[i][j] = 4; break;
				case '2': plan[i][j] = 5; break;
				case '3': plan[i][j] = 6; break;
				case '4': plan[i][j] = 7; break;
				case 'M': 
					moscow = new Pair(i, j);
					plan[i][j] = 0;
					break;
				case 'Z': plan[i][j] = 0; break;
				}
			}
		}
		
		System.out.println("------------------");
		
		// 맵 출력
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.print(plan[i][j]);
				}
			System.out.println();
		}
		
		
		boolean found = false;

		Queue<Pair> queue = new LinkedList<Pair>();
		Pair pos = new Pair(0, 0);
		queue.add(moscow);
		
		while(!found) {
			pos = queue.poll();
			int row = pos.r, col = pos.c;
			visited[row][col] = true;
			int cnt = 0; // 현재 블록에서 갈 수 있는 모든 방향과 다 이어져있는지 확인해야한다.
			
			for(Pair d : delta) {
				int nRow = row + d.r, nCol = col + d.c;
				if(inRange(nRow, nCol) && !visited[nRow][nCol] && plan[nRow][nCol] > 0) {
					// 범위 내고, 방문한적 없고, 파이프가 있으면 갈 수 있다.
					cnt++;
					queue.add(new Pair(nRow, nCol));
					visited[nRow][nCol] = true;
					break;
				}
			}
			if(cnt < block[plan[row][col]].length - 1) {
//				System.out.println("안이어짐 : " + row + " " + col);
				found = true;
			}
		}
		
		// 마지막 위치에서 빈 곳을 찾아낸다.
//		System.out.println("여기부터 볼건데 " + pos.r + " " + pos.c);
		for(int i=0; i < block[plan[pos.r][pos.c]].length; i++ ) {
			// 마지막 위치의 파이프가 이어주는 방향에서만 본다.
			Pair d = block[plan]
			int bRow = pos.r+d.r, bCol = pos.c+d.c;
			if(inRange(bRow, bCol) && !visited[bRow][bCol] && plan[bRow][bCol] == 0) {
				blankSpace = new Pair(bRow, bCol);
				blankDir.add(blankSpace)
//				System.out.println(blankSpace.r + " & " + blankSpace.c);
				break;
			}
		}
		
		for(Pair d : delta) {
			
		}
		
		
		
	}

}
