package _사이트.softeer.lv2.거리합구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main
{	
	static long[] subtreeSize; // 왜 여기도 long이어야 하는거지? N은 2*10^5 잖아...
	static long[] distance;
	static ArrayList<ArrayList<Node>> adj;
	static int N;
	
	static class Node {
		int number;
		int dist;
		
		public Node(int _number, int _dist) {
			this.number = _number;
			this.dist = _dist;
		}
	}
	
	public static void countSubTree(int me, int parent) {
		// 일단 하나 세고
		subtreeSize[me] = 1;
		
		for(Node node : adj.get(me)) {
			// 양방향 저장이라 체크해줘야됨
			if (node.number != parent) {
				// bottom Up
				countSubTree(node.number, me);
				// 내 자식의 자식들은 내 자식
				subtreeSize[me] += subtreeSize[node.number];
				
				// 내 자식과의 거리 * 내 자식의 자식들의 개수 => 내 자식의 자식들에 대한 거리 누적
				// 근데 이건 제일 조상만 제대로 계산됨
				distance[me] += (long) (distance[node.number] + subtreeSize[node.number] * node.dist);
			}
		}
	}
	
	public static void calDist(int me, int parent) {
		// 제일 조상을 기준으로 나머지들을 계산해 나갈거임
		for(Node node : adj.get(me)) {
			// 양방향 저장이라 체크해줘야됨
			if (node.number != parent) {
				// 나와 내 자식의 거리 * 내 자식의 자식 개수 는 뺀다.
				// 나와 내 자식의 거리 * (전체 - 내 자식의 자식의 개수)는 더한다.
				// - node.dist * subtreeSize[node.number]
				// + node.dist * (N - subtreeSize[node.number])
				// => node.dist * (N - 2 * subtreeSize[node.number])
				distance[node.number] = (long) (distance[me] + node.dist * (N - 2 * subtreeSize[node.number]));
				
				// 밑에도 전파를 해야됨 TopDown
				calDist(node.number, me);
			}
		}
		
	}
	
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        
        // 연결 리스트
        adj = new ArrayList<>();
        
        for(int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }
        
        // 입력 처리
        for(int j = 0; j < N-1; j++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            
            adj.get(x-1).add(new Node(y-1, t));
            adj.get(y-1).add(new Node(x-1, t));
        }
        
        // 내 밑에 노드 몇개 있는지 계산
        subtreeSize = new long[N];
        // i 번째 노드와 전체 노드에 대한 거리의 합
        distance = new long[N];
        
        
        countSubTree(0, 0);
        calDist(0, 0);
            
		for(int k = 0; k < N; k++) {
			sb.append(distance[k]+"\n");
		}
		System.out.println(sb.toString());
    }
}