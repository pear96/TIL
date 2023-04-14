package programmers.경주로건설;

import java.util.Arrays;
import java.util.PriorityQueue;


public class HE67259 {
	class Solution {
	    // 우하좌상
	    int N;
	    int[] dr = {0, 1, 0, -1};
	    int[] dc = {1, 0, -1, 0};
	    
	    class Node implements Comparable<Node> {
	        int row;
	        int col;
	        int dir;
	        int price;
	        
	        public Node(int _row, int _col, int _dir, int _pri) {
	            this.row = _row; // 위치
	            this.col = _col;
	            this.dir = _dir; // 해당 위치로 이동해온 방향
	            this.price = _pri; // 누적 비용
	        }
	        
	        @Override
	        public int compareTo(Node other) {
	            return this.price - other.price;
	        }
	        
	    }
	    
	    boolean inRange(int r, int c) {
	        return 0 <= r && r < N && 0 <= c && c < N;
	    }
	    
	    public int solution(int[][] board) {
	        int answer = Integer.MAX_VALUE;
	        N = board.length;
	        PriorityQueue<Node> queue = new PriorityQueue<>();
	        
	        int[][][] visited = new int[N][N][4];
	        Arrays.fill(visited[0][0], -1);
	        
	        if (board[0][1] == 0) {
	        	queue.add(new Node(0, 1, 0, 1));
	        	visited[0][1][0] = 1;
	        }
	        if (board[1][0] == 0) {
	        	queue.add(new Node(1, 0, 1, 1));
	        	visited[1][0][1] = 1;
	        } 
	        
	        while(!queue.isEmpty()) {
	            Node now = queue.poll();
	            if (now.row == N-1 && now.col == N-1) {
	            	answer = now.price;
	            	break;
	            }
	            
	            for (int d = 0; d < 4; d++) {
	                int nextR = now.row + dr[d];
	                int nextC = now.col + dc[d];
	                // 방향이 같으면 1, 아니면 6
	                int nextP = now.price + ((now.dir == d) ? 1: 6);
	                
	                
	                if (!inRange(nextR, nextC)) continue;
	                if (board[nextR][nextC] == 1) continue;
	                if (visited[nextR][nextC][d] != 0 && visited[nextR][nextC][d] < nextP) continue;
	                
	                visited[nextR][nextC][d] = nextP;
	                queue.add(new Node(nextR, nextC, d, nextP));
	                
	            }
	        }

	        return answer*100;
	    }
	}
}
