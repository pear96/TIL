package BFS_DFS;

// https://www.acmicpc.net/problem/17071

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_숨바꼭질5_17071 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N, K;
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int time = 0;
		boolean[][] visited = new boolean[2][500001]; // [0]은 짝수, [1]은 홀수
		
		Queue<Integer> q = new LinkedList<>();
		q.add(N);
		visited[0][N] = true;
		boolean found = visited[0][K];
		
		while(!found) {
			time += 1;
			K += time;
			if(K > 500000) break;
			
			Queue<Integer> nextQ = new LinkedList<>();
			
			while(!q.isEmpty()) {
				int now = q.poll();
				
				if(now-1 >= 0 && visited[time%2][now-1] == false) {
					visited[time%2][now-1] = true;
					nextQ.add(now-1);
				}
				if(now+1 <= 500000 && visited[time%2][now+1] == false) {
					visited[time%2][now+1] = true;
					nextQ.add(now+1);
				}
				if(now*2 <= 500000 && visited[time%2][now*2] == false) {
					visited[time%2][now*2] = true;
					nextQ.add(now*2);
				}
			}
			q = nextQ;
			
			if(visited[time%2][K]) {
				found = true;
				break;
			}
		}
		
		if (found == false) {
			System.out.println(-1);
		} else {
			System.out.println(time);
		}
	}

}
