package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class BJ_좋다_1253 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i ++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);

		int answer = 0;
		for (int now = 0; now < N; now++) {
			int s = (now == 0)? 1 : 0;
			int e = (now == N-1)? N-2: N-1;

			while (s != e) {
				// 좋은 수다
				if (nums[s] + nums[e] == nums[now]) {
					answer++;
					break;
				}
				// 아모른직다
				if (nums[s] + nums[e] < nums[now]) {
					// 작으면 s를 오른쪽으로 옮겨야겠지??
					s++;
					// 지금 위치랑 같으면 한번 더 옮겨
					if (s == now) s++;
				} else {
					// 크면 e를 왼쪽으로 옮겨야겠지?
					e--;
					// 지금 위치랑 같으면 한번 더 옮겨
					if (e == now) e--;
				}
			}
		}
		System.out.println(answer);
	}
}
