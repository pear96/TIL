package BFS_DFS;

import java.util.ArrayDeque;

public class PG_숫자변환하기 {
	class Solution {
		public int solution(int x, int y, int n) {
			ArrayDeque<Integer> q = new ArrayDeque<>();

			int[] visited = new int[y+1];
			q.add(x);
			visited[x] = 1;

			while (!q.isEmpty()) {
				int now = q.poll();
				for (int g : new int[] {n, now, 2*now}) {
					if (now+g <= y && visited[now+g] == 0) {
						visited[now+g] = visited[now]+1;
						q.add(now+g);
					}
				}
			}
			return visited[y]-1;
		}
	}
}
