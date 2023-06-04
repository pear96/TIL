package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ_좌표정렬하기2_11651 {
	static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class NodeComparator implements Comparator<Node> {
		@Override
		public int compare(Node o1, Node o2) {
			if (o1.y != o2.y) return o1.y - o2.y;
			return o1.x - o2.x;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Node[] list = new Node[N];
		
		StringTokenizer st;
		
		for(int i = 0; i < N; i++ ) {
			 st = new StringTokenizer(br.readLine());
			list[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		// 배열 정렬 -> Arrays.sort(), List 인터페이스 정렬 -> Collections.sort()
		Arrays.sort(list, new NodeComparator());
		
		for(int i = 0; i < N; i++) {
			sb.append(list[i].x + " " + list[i].y + "\n");
		}
		// 마지막 개행 삭제
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb.toString());
	}

}
