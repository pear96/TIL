package LCA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_LCA_11437 {
	static int N;
	static int[] parent;
	static int[] depth;
	
	// DFS로 루트 노드부터 시작하여 깊이(depth)를 구한다.
	// 양방향 연결로 인해 자식이 부모를 볼 수 있어 visited 체크가 필요하다.
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] visited;
	
	static void calDepth(int num, int d) {
		// 현재 노드 방문 처리 + 깊이 저장
		visited[num] = true;
		depth[num] = d;
		
		// 나와 연결된 노드들 중에, 이미 방문 처리 된 것은 부모라서 넘어간다.
		// 아니라면 내 자식 노드이므로, 자식노드의 parent를 나로 저장한다.
		// depth를 현재에서 하나 늘려 dfs로 들어간다.
		for(int con : adj.get(num)) {
			if (visited[con]) continue;
			parent[con] = num;
			calDepth(con, d+1);
		}
	}
	
	// a와 b의 최소 공통 조상을 찾는다.
	static int findLCA(int a, int b) {
		
		while (depth[a] != depth[b]) {
			if (depth[a] < depth[b]) {
				b = parent[b];
			} else {
				a = parent[a];
			}
		}
		// 이제 둘의 깊이는 같다.
		// 둘의 부모가 같을 때 까지 본다.
		while (a != b) {
			a = parent[a];
			b = parent[b];
		}
		// a나 b나 상관 X
		return a;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		depth = new int[N+1];
		visited = new boolean[N+1];

		// 노드의 번호는 1부터 시작한다.
		parent = new int[N+1];
		depth = new int[N+1];
		
		// 노드간의 관계를 저장한다.
		int a, b;
		adj = new ArrayList<>();
		
		for(int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>());
		}
		
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			// 양방향으로 연결해준다.
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		
		// a와 b가 Tree에 저장된 순서대로 나온다는 보장이 없다.
		// 이는 트리를 입력으로 주는 문제에서 가장 흔한 방식이다.
		// 모든 간선을 입력받기 전 까지는 누가 부모이고, 누가 자식인지 알 수 없다.
		// adj에 모든 간선의 정보를 입력해두었으므로, 이제 부모와 자식을 기록한다.
		calDepth(1, 0);
		
		// LCA를 찾는다.
		int M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			sb.append(findLCA(a, b)+"\n");
		}
		
		System.out.println(sb.toString());
	}

}
