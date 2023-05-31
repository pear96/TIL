package Dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

class Solution {
    PriorityQueue<Node> pq;
    ArrayList<ArrayList<Node>> adj;
    
    int maxIntensity = Integer.MAX_VALUE;
    int maxNode = Integer.MAX_VALUE;
    
    // gate를 따로 확인하지 않는 이유는, 출입구는 한번에 0으로 설정하고 볼 것이기 때문이다.
    HashSet<Integer> peaks = new HashSet<>();
    
    // i번째 노드의 값 = i번째 노드까지 오면서 본 최대 intensity값
    // 대신 pq로 거리가 가까운 부분부터 지나오기 때문에 간선 중 최대값 X
    // 최대값이 가장 작은 등산로에서의 i번째 노드까지 보면서 찾은 최대값
    int[] intensity;
    
    class Node implements Comparable<Node> {
        int num;
        int cost;
        
        public Node (int n, int c) {
            this.num = n;
            this.cost = c;
        }
        
        @Override
        public int compareTo(Node node) {
            return this.num - node.num;
        }
    }
    
    void dijkstra() {

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            
            for(Node other : adj.get(now.num)) {
                // now와 연결된 other 노드들을 보면서
                // other 노드까지의 최대 intensity가, now까지의 최대 intensity값 또는 other의 가중치보다 크다면
                // 바꿔준다.. ㅜㅜ 이해 앙됨
                if (intensity[other.num] > Math.max(intensity[now.num], other.cost)) {
                    intensity[other.num] = Math.max(intensity[now.num], other.cost);
                    // 만약 산봉우리가 아니라면, 
                    if (!peaks.contains(other.num)) pq.add(other);
                }
            }
        }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // 출입구 A에서 출발하여 index번 지점까지 올 때 가능한 최소 intensity
        intensity = new int[n+1];
        // intensity 전부 MAX값
        Arrays.fill(intensity, Integer.MAX_VALUE);
        
        pq = new PriorityQueue<>();
        adj = new ArrayList<>();
        
        // 출입구 다 넣어놓고, 출입구는 intensity 0임
        for(int gate : gates) {
            pq.add(new Node(gate, 0));
            intensity[gate] = 0;
        }
        
        for(int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for(int[] path : paths) {
            int a = path[0];
            int b = path[1];
            int c = path[2];
            // 양방향 연결
            adj.get(a).add(new Node(b, c));
            adj.get(b).add(new Node(a, c));
        }
        
        // 산봉우리 set
        for(int summit : summits) {
            peaks.add(summit);
        }
        

        dijkstra();
        
        // peaks에서만 고른다.
        Arrays.sort(summits);
        for(int summit : summits) {
            if (intensity[summit] < maxIntensity) {
                maxIntensity = intensity[summit];
                maxNode = summit;
            }
        }
        // System.out.println(Arrays.toString(intensity));
        return new int[]{maxNode, maxIntensity};
    }
}