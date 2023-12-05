package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class BJ_수들의합4 {

	public static void main(String[] args) throws IOException {
		long answer = 0; // N * (N+1) / 2 개
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] prefix = new int[N+1];
		HashMap<Integer, Integer> count = new HashMap<>();

		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) {
			prefix[i] = prefix[i-1] + Integer.parseInt(st.nextToken());
			if (prefix[i] == K) answer++;

			if (count.containsKey(prefix[i] - K)) answer += count.get(prefix[i] - K);

			if (count.containsKey(prefix[i])) count.put(prefix[i], count.get(prefix[i])+1);
			else count.put(prefix[i], 1);
		}

		System.out.println(answer);
	}
}
