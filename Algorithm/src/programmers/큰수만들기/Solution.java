package programmers.큰수만들기;

import java.util.*;

class Solution {
	public String solution(String number, int k) {
		// stack 써서 이미 들어간게 더 작은 수면 가능할 때까지(k) 빼면 될 것 같은데
		int cnt = number.length() - k;
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		stack.add(number.charAt(0) - '0');
		for (int i = 1; i < number.length(); i++) {
			int num = number.charAt(i) - '0';
			while (!stack.isEmpty() && stack.getLast() < num && k > 0) {
				stack.removeLast();
				k--;
			}
			stack.add(num);
		}
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < cnt; i++) {
			answer.append(String.valueOf(stack.removeFirst()));
		}
		return answer.toString();
	}
}