package practice;

import java.util.PriorityQueue;

public class practicePriorityQueue {

	static class Node implements Comparable<Node>{
		int dis;
		int row;
		int col;
		
		public Node(int d, int r, int c) {
			this.dis = d;
			this.row = r;
			this.col = c;
		}
		
		@Override
		public int compareTo(Node other) {
			return this.dis - other.dis;
		}
	}
	
	public static void main(String[] args) {
		
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(new Node(0, 0, 0));
		q.add(new Node(1, -1, 1));
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			System.out.println(n.dis + " "+ n.row + " " + n.col);			
		}
	}

}
