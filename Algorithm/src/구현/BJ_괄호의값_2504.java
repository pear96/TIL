package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BJ_괄호의값_2504 {
	static char[] input;  // 입력받은 괄호 문자열
	static boolean[] visit;  // 닫는 괄호 판별을 위해
	static int idx;  // 문자열 순회하는 전체 인덱스

	static boolean check() {
		// 올바른 괄호 문자열인지 확인한다.
		Deque<Character> stack = new ArrayDeque<>();

		for (char c : input) {
			if (c == '(' || c == '[')
				stack.push(c);
			else if (c == ')') {
				if (!stack.isEmpty() && stack.peek() == '(')
					stack.pop();
				else
					return false;
			} else if (c == ']') {
				if (!stack.isEmpty() && stack.peek() == '[')
					stack.pop();
				else
					return false;
			}
		}
		return stack.isEmpty();
	}

	static int dfs (int now) {
		// dfs 탐색으로 값을 반환한다.
		visit[now] = true;

		if (input[now] == '(') {
			// () 라서 2를 반환한다.
			if (input[now+1] == ')') {
				visit[now+1] = true;
				idx = now + 1;
				return 2;
			} else {
				// ()가 아니기 때문에 곱해가야 한다.
				int sum = 0;
				idx++;
				// 곱하기 시작한 괄호의 짝이 나타날 때까지
				while (!(input[idx] == ')' && !visit[idx])) {
					sum += dfs(idx);
					idx++;
				}
				visit[idx] = true;
				return 2 * sum;
			}
		}
		// [ 인 경우
		if (input[now+1] == ']') {
			// []라서 3을 반환한다.
			visit[now+1] = true;
			idx = now + 1;
			return 3;
		} else {
			int sum = 0;
			idx++;
			while (!(input[idx] == ']' && !visit[idx])) {
				sum += dfs(idx);
				idx++;
			}
			visit[idx] = true;
			return 3 * sum;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine().toCharArray();
		visit = new boolean[input.length];

		int answer = 0;
		if (check()) {
			while (idx < input.length) {
				if (!visit[idx]) answer += dfs(idx);
				idx++;
			}
		}
		System.out.println(answer);
	}
}
