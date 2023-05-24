# MST vs Dijkstra vs BFS

> 공통점 : '그래프'와 관련된 알고리즘

|      | MST                                                          | Dijkstra                                                     | BFS                                                          |
| :--: | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 목적 | 모든 정점를 최소 비용으로 연결                               | 특정 정점에서 다른 '모든' 정점까지의 최단 경로               | 모든 정점 단순 방문                                          |
| 설명 | 신장 트리는 N개의 정점을 포함하는 **무향** 그래프에서 N-1개의 간선으로 구성된 트리이다. 따라서 '최소' 신장 트리는, 간선에 가중치를 두어 신장 트리를 구성하는 가중치의 합이 **최소**인 신장 트리를 의미한다. | 간선에 가중치가 있는 **유향**그래프에서 두 정점 사이의 경로 중 간선의 가중치 합이 최소인 경로이며, **음의 가중치**를 허용하지 않는다. | 시작 정점에서 이웃을 방문하는 식으로 진행                    |
| 방식 | Prim 알고리즘 <br />- 현재 정점과 연결된 간선 중 가장 가중치가 적은 것 순으로 방문<br />- PriorityQueue 활용<br />Kruskal 알고리즘<br />- 모든 간선을 가중치 순으로 오름차순 정렬하여, 간선 중 가장 가중치가 적은 것 순으로 방문<br />- 방문한 정점은 Tree에 속한 것이다. 사이클을 막기 위해 Union-Find 기법 필요 | 시작 정점에서 거리가 **최소**인 정점부터 선택해 나가며 최단 경로를 구한다.<br />- PriorityQueue 사용. Prim과 유사함.<br />- 시작 ~ A 거리 VS (시작 ~ 현재 거리 + 현재 ~ A 거리) 를 비교하며 업데이트 한다. | - Queue(Deque)를 사용한다.<br />- 문제의 상황에 맞게 조건을 설정해야한다. ex) 델타 배열, 방문 조회, 거리 누적<br />- 참고로 경로를 구하려면 2차원 배열에 (x2, y2)에 도달하기 전 위치인 (x1, y1)을 저장하면 된다. |



## 코드 비교

### MST(Prim)

```java
public class BJ_네트워크연결_1922_Prim {
	
	static class Node implements Comparable<Node>{
		int num;
		int price;
		
		public Node(int n, int p) {
			this.num = n;
			this.price = p;
		}
		
        // PriorityQueue 사용을 위한 Comparable 구현
		@Override
		public int compareTo(Node n) {
			return this.price - n.price;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		HashMap<Integer, ArrayList<Node>> cost = new HashMap<>();
		
        // 연결 리스트 생성
		StringTokenizer st;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			ArrayList<Node> listA = cost.getOrDefault(a, new ArrayList<>());
			ArrayList<Node> listB = cost.getOrDefault(b, new ArrayList<>());
			
			listA.add(new Node(b, c));
			cost.put(a, listA);
			
			listB.add(new Node(a, c));
			cost.put(b, listB);
		}
		
		boolean[] visited = new boolean[N];
		PriorityQueue<Node> q = new PriorityQueue<>();
		
		// 0 번째(시작) 노드 연결된 곳 PQ에 넣고 시작하기
		for(Node n:cost.get(0)) {
			q.add(n);
		}

		// 시작점은 계산 할게 없음
		visited[0] = true;
		
		
		int answer = 0;
		
		while(!q.isEmpty()) {
            // 가장 가중치가 낮은 간선부터 확인한다.
			Node now = q.poll();
			// 이미 갔던 곳이면 pass
			if (visited[now.num]) continue;
				
			// 연결된 곳 까지 가는 비용
			answer += now.price;
				
			// 연결된 곳 가기
			visited[now.num] = true;
			
			// 연결된 곳에서 또 찾기
			for(Node n : cost.get(now.num)) {
				q.add(n);
			}
		}
		System.out.println(answer);
	}
}

```



### Dijkstra

```java
public class PG_배달_12978 {
	class Solution {
	    
	    class Node implements Comparable<Node> {
	        int num;
	        int cost;
	        
	        public Node (int n, int c) {
	            this.num = n;
	            this.cost = c;
	        }
	        
	        @Override
	        public int compareTo(Node n) {
	            return this.cost - n.cost;
	        }
	    }

	    public int solution(int N, int[][] road, int K) {
	        int answer = 0;
	        
	        HashMap<Integer, ArrayList<Node>> conn = new HashMap<>();
	        
	        for (int i = 0; i < road.length; i++) {
	            int a = road[i][0];
	            int b = road[i][1];
	            int c = road[i][2];
	            
	            // 양방향 연결
	            
	            ArrayList<Node> listA = conn.getOrDefault(a, new ArrayList<Node>());
	            listA.add(new Node(b, c));
	            conn.put(a, listA);
	            
	            ArrayList<Node> listB = conn.getOrDefault(b, new ArrayList<Node>());
	            listB.add(new Node(a, c));
	            conn.put(b, listB);
	        }
	        
	        int[] dist = new int[N+1];
	        
	        // 최대값 설정
	        for (int i = 2; i <= N; i++) {
	            dist[i] = Integer.MAX_VALUE;
	        }
	        
	        boolean[] visited = new boolean[N+1];
	        
	        PriorityQueue<Node> q = new PriorityQueue<>();
	        q.add(new Node(1, 0));
	        
	        while(!q.isEmpty()) {
	            Node now = q.poll();
	            
	            // 노드에서 이동 가능한 다른 노드들을 확인하기 전, 즉 우선 순위 큐에서 뽑힌 노드를 방문하는 시점에서 노드를 방문 처리해야 합니다. 
                // 그렇지 않으면 후에 나오는 짧은 경로를 계산할 수 없음.
	            
	            if (visited[now.num]) continue;
	            
	            visited[now.num] = true;
	            
	            for(Node other : conn.get(now.num)) {
	                
	                // 더 짧은 경로가 있다면 업데이트 하자!
	                if (dist[other.num] > other.cost + dist[now.num]) {
	                    dist[other.num] = other.cost + dist[now.num];
	                    q.add(new Node(other.num, dist[other.num]));
	                }
	            }
	        }
	        
	        for (int i = 1; i <= N; i++) {
	            if (dist[i] <= K) answer++;
	        }
	        
	        return answer;
	    }
	}
}
```

|      | MST                                                          | Dijkstra                                                     |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ |
|      | - 시작 정점에서 시작<br />- 현재 정점에 연결된 간선 중 가장 가중치가 낮은 간선 선택<br />- 모든 정점을 방문할 때까지 반복 | - 시작 정점에서 시작<br />- 현재 정점에서 인접한 정점 중 가장 가중치가 작은 정점 선택<br />- 모든 정점을 방문할 때 까지 반복 |
|      | 모든 정점을 연결하는데, 그 비용이 최소임<br />시작 정점에서 특정 정점까지 최단 경로를 보장하지 않음 | 시작 정점에서 부터 모든 정점까지 각.각 최단 비용 계산        |





### MST(Kruskal)

```java
public class BJ_네트워크연결_1922_Kruskal {
	/**
	 * 각 컴퓨터를 연결하는데 필요한 비용이 주어졌을 때 모든 컴퓨터를 연결하는데 필요한 최소비용을 출력하라. 
	 * 최소 신장 트리 기본 문제. Prim(PriorityQueue) & Kruskal(Union + Find)
	 * 1. 모든 간선을 가중치에 따라 오름차순으로 정렬한다. (간선 = (비용, a, b))
	 * 2. 가중치가 가장 낮은 간선부터 선택하여 집합으로 만든다(Find하고 Union 시킴)
	 * 2-1. 이미 같은 상태라면? 다음 간선으로 넘어간다.
	 * 3. 했으면 그 다음 가중치가 낮은 간선을 보며 반복한다. 남은 간선이 없을때까지
	 */

    // 트리의 각 정점의 부모
	static int[] parent;
	
    // b의 조상을 찾아와서 그 조상의 부모는 a의 조상이다.
	static void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		parent[parentB] = parentA;
	}
	
    // idx의 조상을 찾는다. idx == parent[idx]가 될 때까지
	static int find(int idx) {
		while (parent[idx] != idx) {
			idx = parent[idx];
		}
		return parent[idx];
	}
	
	static class Edge implements Comparable<Edge> {
        int cost;
        int a;
        int b;
        
        public Edge(int c, int a, int b) {
            this.cost = c;
            this.a = a;
            this.b = b;
        }
        // 간선 비교를 위한 PriorityQueue 필요
        @Override
        public int compareTo(Edge other) {
            return this.cost - other.cost;
        }
    }
    
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int answer = 0;
        
        // 노드간의 연결 관계 저장
        PriorityQueue<Edge> q = new PriorityQueue<>();
        
        // 각 노드 방문 확인
        parent = new int[N+1];
        for(int i = 1; i <= N; i++) {
        	// 처음엔 스스로가 부모
        	parent[i] = i;
        }
        
        
        StringTokenizer st;
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            // 단방향 X
            q.add(new Edge(c, a, b));
        }

        
        while(!q.isEmpty()) {
            Edge e = q.poll();
            // Prim은 이 부분에서 거리를 비교한다.
            if(find(e.a) != find(e.b)) {
            	answer += e.cost;
            	union(e.a, e.b);
            }
        }
        System.out.println(answer);
    }
}
```