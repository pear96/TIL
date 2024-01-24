package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_방번호_1475 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] numbers = br.readLine().replaceAll("9", "6").toCharArray();
		int[] cnt = new int[9];
		for (char num : numbers) {
			cnt[num - '0']++;
		}

		int answer = 0;
		for (int i = 0; i < 9; i++) {
			if (i == 6) answer = Math.max(answer, cnt[i] / 2 + ((cnt[i] % 2 == 0) ? 0 : 1));
			else answer = Math.max(answer, cnt[i]);
		}
		System.out.println(answer);
	}

}
