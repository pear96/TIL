package 트리;

//https://www.acmicpc.net/problem/1167


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Node {
	int num;
	int distance;
	
	Node(int num, int distance) {
		this.num = num;
		this.distance= distance;
	}
}

public class BJ_트리의지름_1167 {
	
	static ArrayList<Node>[] dist;
	static boolean[] visited;
	
	static int farNum = 0;
	static int answer = 1;
	
	static void dfs(int nodeNum, int distance) {
		if (answer < distance) {
			farNum = nodeNum;
			answer = distance;
		}
		
		visited[nodeNum] = true;
		
		for(int i = 0; i < dist[nodeNum].size(); i++) {
			Node nextNode = dist[nodeNum].get(i);
			if (visited[nextNode.num]) continue;
			dfs(nextNode.num, distance + nextNode.distance);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int V = Integer.parseInt(br.readLine());
		
		dist = new ArrayList[V+1];
		
		for(int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			int point = Integer.parseInt(st.nextToken());
			dist[point] = new ArrayList<Node>();
			// 일단 최대값으로 채워놓는다. 나중에 플로이드 워샬로 계산하기 위함
			
			while(true) {
				int nPoint = Integer.parseInt(st.nextToken());
				if (nPoint == -1) break;
				int nDist = Integer.parseInt(st.nextToken());
				// 입력 값에 애초에 양방향을 본다...
				dist[point].add(new Node(nPoint, nDist));
			}
		}
		
		visited = new boolean[V+1];
		dfs(1, 0);
		
		visited = new boolean[V+1];
		dfs(farNum, 0);
		
		System.out.println(answer);
	}

}
