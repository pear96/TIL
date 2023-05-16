package Dijkstra;

// https://school.programmers.co.kr/learn/courses/30/lessons/12978

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

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
	            // visited 처리 시점에서 틀림
	            // 노드를 방문 처리하는 시점이 if (visited[other.num]) continue;와 visited[other.num] = true; 사이에서 발생합니다. 
	            // 이 경우, 우선 순위 큐에서 뽑힌 노드에서 이동 가능한 다른 노드들을 확인하면서 해당 노드를 방문 처리합니다.
	            // 하지만, 이는 다익스트라 알고리즘의 원칙에 위배됩니다. 
	            // 왜냐하면 아직 최단 거리가 확정되지 않은 상태에서 노드를 방문 처리하게 되면, 
	            // 그 노드를 통해 이동 가능한 더 짧은 경로를 놓치게 될 수 있기 때문입니다.
	            
	            // 예를 들어 2번에서 3번 노드로 가는 경로가 있고 기존 보다 짧은 거리라 방문 처리를 했다고 합시다.
	            // 하지만, 4번에서 3번 노드로 가는 경로가 더 최단거리인 경우 이미 2번에서 방문처리를 했기 때문에 4번에서 방문할 수 없습니다.
	            
	            // 따라서 노드를 방문 처리하는 시점을 변경해야 합니다. 
	            // 노드에서 이동 가능한 다른 노드들을 확인하기 전, 즉 우선 순위 큐에서 뽑힌 노드를 방문하는 시점에서 노드를 방문 처리해야 합니다. 
	            
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
