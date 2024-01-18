package programmers.연속된부분수열의합;

class Solution {
	public int[] solution(int[] sequence, int k) {
		int total = sequence.length;
		int start = 0;
		int end = 0;
		int[] answer = new int[] {0, total};
		int sum = sequence[start];

		while (start < total && end < total) {
			if (sum == k && answer[1] - answer[0] > end - start) {
				answer[0] = start;
				answer[1] = end;
			} else if (sum < k && end < total-1) {
				sum += sequence[++end];
			} else {
				sum -= sequence[start++];
			}
		}

		return answer;
	}
}