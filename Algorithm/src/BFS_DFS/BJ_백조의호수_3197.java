package BFS_DFS;

/* 이미 코테로 고통 받았는데 알고리즘으로 또 고통받으려니.... 하..
 * 풀이의 핵심은 Deque를 나눠서 쓰는 것이다. 새로 초기화 하지않고, 이전에 탐색한 기록을 활용할 수 있도록.
 * 그리고 백조는 물로 취급해야한다.
 * 
 * 백조는 0번 또는 1번 기준으로 탐색을 시작한다. 
 * visited에 다 기록해두면서, 갈 수 없는 곳을 찾으면 다음 탐사할 곳으로 정한다. 
 * 지금까지 탐사가 가능한건 물을 통해서 온거라서, 지금 못 가는 곳은 다음에 왔을 땐 녹아있을 것이다.
 * 
 * 백조끼리 만나지 못 했다면 이제 빙하를 녹인다. water 기준으로 주변의 4방향을 녹인다.
 * 이때 water의 기존 개수만 녹여서 water 큐를 아예 비우진 말아야한다.
 * 걔들은 다음 탐사가 끝나고 나서 녹여야한다. (하루가 지난 뒤)
 * */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BJ_백조의호수_3197 {
	static int R, C, idx = 0;
	static char[][] lake;
	static Node[] swan;
	
	// 초기화는 한번만
	static boolean[][] visitedSwan;
	
	// 물의 위치들을 저장한다.
	static ArrayDeque<Node> water;
	
	// 백조가 탐색하고 있는 곳
	static ArrayDeque<Node> search;
	// 물이 녹고난 뒤 다음에 탐색할 곳
	static ArrayDeque<Node> willSearch;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static class Node {
		int r;
		int c;
		
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static boolean inRange(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
	
	// 백조끼리 만날 수 있는지 판단한다.
	static boolean meetSwan() {
		// 매번 시작할 때 비워준다.
		willSearch = new ArrayDeque<>();
		
		// 탐색가능한 곳들이 없을 때 까지 본다.
		while(!search.isEmpty()) {
			Node now = search.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = now.r + dr[d];
				int nc = now.c + dc[d];
				
				if (!inRange(nr, nc) || visitedSwan[nr][nc]) continue;
				visitedSwan[nr][nc] = true;
				// 만약 다른 백조를 찾는다면 끝난다.
				if(swan[1].r == nr && swan[1].c == nc) return true;
				
				// 물이면 지금 계속 탐사한다. 그런데 빙하면 얘들 녹아있을 테니깐 다음에 탐사한다. (중간 저장)
				if (lake[nr][nc] == 'X') {
					willSearch.add(new Node(nr, nc));
				} else search.add(new Node(nr, nc));
			}
		}
		// 이번에 볼 데는 끝났으니 다음 탐사할 곳으로 덮어씌운다.
		search = willSearch;
		return false;
	}
	
	// 얼음을 녹인다.
	static void meltingIce() {
		// 지금 물의 개수만큼만 본다.
		// 지금 찾은 빙하들은 다음 날 녹여야한다.
		int size = water.size();
		
		for(int i = 0; i < size; i++) {
			
			Node w = water.poll();
			for (int d = 0; d < 4; d++) {
				int nr = w.r + dr[d];
				int nc = w.c + dc[d];
				
				if(!inRange(nr, nc)) continue;
				if (lake[nr][nc] == 'X') {
					lake[nr][nc] = '.';
					water.add(new Node(nr, nc));
				}
			}
			
		}
	}
	
	public static void main(String[] args) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		lake = new char[R][C];
		
		// 백조 위치
		swan = new Node[2];
		swan[0] = new Node(-1, -1);
		swan[1] = new Node(-1, -1);
		
		// 물의 위치를 저장한다.
		water = new ArrayDeque<>();

		for(int r = 0; r < R; r++) {
			char[] input = br.readLine().toCharArray();
			for(int c = 0; c < C; c++) {
				lake[r][c] = input[c];
				if (input[c] == 'L') {
					swan[idx++] = new Node(r, c);
					// 백조 위치도 물로 취급해야한다.
					lake[r][c] = '.';
					water.add(new Node(r, c));
				}
				if (input[c] == '.') {
					water.add(new Node(r, c));
				}
			}
		}
		
		int time = 0;
		
		// 백조 탐사용
		search = new ArrayDeque<>();
		search.add(swan[0]);
		
		visitedSwan = new boolean[R][C];
		visitedSwan[swan[0].r][swan[0].c] = true;
		
		while (true) {
			if (meetSwan()) {
				System.out.println(time);
				return;
			}
			meltingIce();
			time++;
		}
	}

}
