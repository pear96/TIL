package 스택;

import java.util.*;

class PG_뒤에있는큰수찾기 {
	ArrayDeque<Integer> q = new ArrayDeque<>();

	int getBackMax(int num) {
		int backMax = num;
		while (!q.isEmpty()) {
			int x = q.getLast();
			if (x > num) {
				backMax = x;
				break;
			} else {
				q.removeLast();
			}
		}
		// System.out.println(q);
		return backMax;
	}


	public int[] solution(int[] numbers) {
		int cnt = numbers.length;
		int[] answer = new int[cnt];

		for(int i = cnt-1; i >= 0; i--) {
			int backMax = getBackMax(numbers[i]);
			answer[i] = (backMax > numbers[i])? backMax:-1;
			q.add(numbers[i]);
		}

		return answer;
	}
}