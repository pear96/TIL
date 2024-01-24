package 구현;

import java.util.ArrayDeque;

public class BJ_셀프넘버_4673 {
	static int d(int n) {
		int result = n;
		while (n > 0) {
			result += n % 10;
			n /= 10;
		}
		return result;
	}
	public static void main(String[] args) {
		boolean[] made = new boolean[10_001];
		for (int i = 1; i <= 10_000; i++) {
			ArrayDeque<Integer> q = new ArrayDeque<>();
			q.add(i);

			while (!q.isEmpty()) {
				int n = q.poll();
				int dn = d(n);
				if (dn <= 10_000 && !made[dn]) {
					made[dn] = true;
					q.add(dn);
				}
			}
		}

		StringBuilder answer = new StringBuilder();
		for (int i = 1; i <= 10_000; i++) {
			if (!made[i]) {
				answer.append(i);
				answer.append("\n");
			}
		}
		System.out.println(answer);
	}

}
