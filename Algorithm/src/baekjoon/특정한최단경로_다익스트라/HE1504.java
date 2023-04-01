package baekjoon.특정한최단경로_다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Node implements Comparable<Node> {
	int num;
	int weight;
	
	Node(int num, int weight) {
		this.num = num;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Node o) {
		return weight - o.weight;
	}
}


public class HE1504 {
	static ArrayList<ArrayList<Node>> graph;
	static int[] dist;
	static int N, E;
	static final int INF = 200000000;
	
	
	static int dijkstra(int start, int end) {
		// 최대값으로 거리 다 채우고, 최단거리 계산하면서 업데이트
		dist = new int[N+1];
		Arrays.fill(dist, INF);
		
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(new Node(start, 0));
		dist[start] = 0;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int now = node.num;
			int weight = node.weight;
			
			if (dist[now] < weight) continue;
			for (int i = 0; i < graph.get(now).size(); i++) {
				Node next = graph.get(now).get(i);
				int cost = dist[now] + next.weight;
				if (cost < dist[next.num]) {
					// 현재 노드를 거쳐 가는게, 다른 노르도 가는 것 보다 빠를 경우
					dist[next.num] = cost;
					q.offer(new Node(next.num, cost));
				}
			}
		}
		return dist[end];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph.get(from).add(new Node(to, cost));
			graph.get(to).add(new Node(from, cost));
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		// 1 -> v1 -> v2 -> N
		int path_1 = dijkstra(1, v1);
		path_1 += dijkstra(v1, v2);
		path_1 += dijkstra(v2, N);
		
		// 1 -> v2 -> v1 -> N
		int path_2 = dijkstra(1, v2);
		path_2 += dijkstra(v2, v1);
		path_2 += dijkstra(v1, N);
		
		int answer = Math.min(path_1, path_2);
		
		if (answer >= INF) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}

}
