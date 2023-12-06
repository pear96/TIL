package 사이트.programmers;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class PG_뒤에있는큰수찾기 {
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	static class Node implements Comparable<Node> {
		int value;
		int idx;

		public Node(int v, int i) {
			this.value = v;
			this.idx = i;
		}

		public int compareTo(Node other) {
			if (this.value > other.value) return -1;
			if (this.value == other.value) {
				return this.idx - other.idx;
			}
			return 1;
		}
	}

	static Node getBackMax(int v, int i) {
		LinkedList<Node> putAgain = new LinkedList<>();
		Node backMax = pq.poll();
		putAgain.add(backMax);

		while(!pq.isEmpty()) {
			Node now = pq.poll();
			putAgain.add(now); // pq도 순환 좀 됐으면 좋겠다
			// v보다 값은 큰데 더 가까이 있으면 업데이트
			if (now.value > v && now.idx < backMax.idx) backMax = now;
			// 이제 그만 봐도 됨
			if (now.value <= v) break;
		}

		for(Node temp : putAgain) {
			pq.add(temp);
		}
		System.out.println("값:"+v+",위치:"+i+"의 뒷큰수:"+backMax.value+" "+backMax.idx);
		return backMax;
	}

	public static int[] solution(int[] numbers) {
		int cnt = numbers.length;
		int[] answer = new int[cnt];
		answer[cnt-1] = -1;
		pq.add(new Node(numbers[cnt-1], cnt-1));

		for(int i = numbers.length-2; i >= 0; i--) {
			Node backMax = getBackMax(numbers[i], i);
			answer[i] = (backMax.value > numbers[i])? backMax.value: -1;
		}

		return answer;
	}

	public static void main(String[] args) {
		solution(new int[] {2, 3, 3, 5});
	}
}
