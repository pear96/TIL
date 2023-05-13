package baekjoon.BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HE14502 {
	// 바이러스의 최소 개수를 저장한다. 이후 나중에 출력할때 안전지대값을 계산한다.
	static int answer = Integer.MAX_VALUE;
	static int N;
	static int M;
	
	// 입력받은 2차원 배열을 저장하는 곳
	static int[][] lab;
	// 벽의 개수를 미리 세어두고, 안전지대 넓이를 계산할 때 사용한다.
	static int wallCnt;
	
	// BFS 4방 탐색을 위한 델타 배열
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	// virus의 초기 위치를 배열에 저장해둔다.
	static ArrayList<Pos> virus;
	
	// BFS 탐색을 위해 필요한 클래스
	static class Pos {
		int r;
		int c;
		
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	// N*M 격자 내에 존재하는지 확인한다.
	static boolean inRange(int r, int c) {
		if(0 <= r && r < N && 0 <= c && c < M) return true;
		return false;
	}
	
	// 3개의 벽을 세우고 난 뒤, 바이러스의 면적을 구한다.
	static int bfs() {
		int virusCnt = virus.size();
		
		boolean[][] visited = new boolean[N][M];
		Queue<Pos> queue = new LinkedList<>(virus);
		
		for(Pos v : virus) {
			visited[v.r][v.c] = true;
		}
		
		while(!queue.isEmpty()) {
			Pos now = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int nextR = now.r + dr[d];
				int nextC = now.c + dc[d];
				
				if(inRange(nextR, nextC) && !visited[nextR][nextC] && lab[nextR][nextC] == 0) {
					visited[nextR][nextC] = true;
					virusCnt++;
					// 바이러스가 더 많은 경우는 볼 필요도 없다.
					if (virusCnt > answer) return -1;
					queue.add(new Pos(nextR, nextC));
				}
			}
		}
		
		return virusCnt;
	}
	
	
	// 3개의 벽을 세워본다.
	static void dfs(int cnt) {
		if (cnt == 3) {
			int virusZone = bfs();
			// 바이러스의 개수가 가장 적은 값을 기록한다.
			if (virusZone != -1 && virusZone < answer) answer = virusZone;
			return; // return을 자꾸 빠트리는데, 그러면 함수를 빠져나올 방법이 없다.
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (lab[r][c] == 0) {
					lab[r][c] = 1;
					dfs(cnt+1);
					// 세운 벽을 다시 없애준다.
					lab[r][c] = 0;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lab = new int[N][M];
		virus = new ArrayList<>();
		
		// 입력 처리. 벽의 개수와 바이러스의 위치를 저장한다.
		for (int i = 0; i < N; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
				if (lab[i][j] == 1) wallCnt += 1;
				if (lab[i][j] == 2) virus.add(new Pos(i, j));
			}
		}
		
		dfs(0);
		
		// 격자 크기에서 바이러스의 수와, 벽의 수를 뺀 값
		System.out.println(N*M - answer - wallCnt - 3);
	}

}
