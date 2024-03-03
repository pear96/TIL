package 사이트.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_서강그라운드_14938 {
	static int N, M;
	static int answer = 0;
	static int[] items;
	static int[][] con;
	
	static class Node implements Comparable<Node>{
		int num;
		int dist;
		
		public Node(int num, int dist) {
			this.num = num;
			this.dist = dist;
		}
		
		public int compareTo(Node n) {
			return this.dist - n.dist;
		}
		
		public String toString() {
			return num + "번,  " + dist + " 거리"; 
		}
	}
	
	static void bfs(int start) {
		int totalItem = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		int[] distance = new int[N];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			for (int other = 0; other < N; other++) {
				// 연결이 되어있고, 이미 기록된 것 보다 가깝다
				int connectionCost = con[now.num][other];
				int newDist = now.dist + connectionCost;
				if (connectionCost > 0 && distance[other] > newDist) {
					distance[other] = newDist;
					pq.add(new Node(other, newDist));
				}
			}
		}
        // M 이내이면
		for (int i = 0; i < N; i++) {
			if (distance[i] <= M) totalItem += items[i];
		}
		
		answer = Math.max(answer, totalItem);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		items = new int[N];
		con = new int[N][N];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int l = Integer.parseInt(st.nextToken());
			con[a][b] = l;
			con[b][a] = l;
		}

		for (int i = 0; i < N; i++) {
			bfs(i);
		}
		
		
		System.out.println(answer);
	}

}
