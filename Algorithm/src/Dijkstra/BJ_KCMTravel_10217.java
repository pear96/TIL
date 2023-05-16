package Dijkstra;

// https://www.acmicpc.net/problem/10217

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_KCMTravel_10217 {
	static class Ticket implements Comparable<Ticket>{
		int arrival;
		int cost;
		int time;
		
		public Ticket(int a, int c, int t) {
			this.arrival = a;
			this.cost = c;
			this.time = t;
		}

		@Override
		public int compareTo(Ticket o) {
			if(this.time == o.time) return cost - o.cost;
			return this.time - o.time;
		}
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[][] dp = new int[N+1][M+1]; // 시작부터 도착지까지 걸린 비용일 때 최소 시간
			ArrayList<Ticket>[] airline = new ArrayList[N+1]; // 연결점
			
			// 초기화
			for(int i = 1; i <= N; i++) {
				airline[i] = new ArrayList<Ticket>();
				Arrays.fill(dp[i], Integer.MAX_VALUE);
			}
			
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				airline[u].add(new Ticket(v, c, d));
			}
			
			// 다익스트라
			PriorityQueue<Ticket> pq = new PriorityQueue<Ticket>();
			pq.offer(new Ticket(1, 0, 0));
			dp[1][0] = 0;

			int answer  = Integer.MAX_VALUE;
			
			while(!pq.isEmpty()) {
				Ticket now = pq.poll();
				int arrival = now.arrival;
				int cost = now.cost;
				int time = now.time;
				
				if(arrival == N) {
					answer = time;
					break;
				}
				
				for(Ticket next : airline[arrival]) {
					int nextArrival = next.arrival;
					int nextCost = cost + next.cost;
					int nextTime = time + next.time;
					
					if (nextCost > M) continue;
					if (dp[nextArrival][nextCost] > nextTime) {
						for(int j = nextCost; j <= M; j++) {
							// 해당 공항까지 최단 시간을 찾았다면 전부 그 값으로 맞춰줌
							if (dp[nextArrival][j] > nextTime) dp[nextArrival][j] = nextTime;
						}
						pq.add(new Ticket(nextArrival, nextCost, nextTime));
					}
					
				}
				
			}
			
			System.out.println(answer == Integer.MAX_VALUE ? "Poor KCM" : answer);
			
		}
	}

}
