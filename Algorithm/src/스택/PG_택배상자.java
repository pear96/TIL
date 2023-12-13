package 스택;

import java.util.ArrayDeque;

public class PG_택배상자 {
	public static int solution(int[] order) {
		int idx = 0; // 택배기사가 정한 순서
		ArrayDeque<Integer> sub = new ArrayDeque<>(); // 보조 벨트
		// 기존 벨트에는 1 ~ N의 숫자 상자 존재
		for (int num = 1; num <= order.length; num++) {
			sub.add(num); // 일단 올려
			while(!sub.isEmpty()) {
				// 다를 때 까지 보면서 보조 벨트를 비움
				if (sub.peekLast() == order[idx]) {
					sub.removeLast();
					idx++;
				} else break; // 순서와 보조 벨트 가장 위의 상자가 다르면 종료 (보조 벨트에 상자가 쌓여있음)
			}
		}

		return idx;
	}

	public static void main(String[] args) {
		// int[] order = new int[] {5,4,3,2,1};
		int[] order = new int[] {4,3,1,2,5};
		System.out.println(solution(order));
	}
}
