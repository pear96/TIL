package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_그룹단어체커_1316 {
	static int checkGroup (char[] word) {
		boolean[] visited = new boolean[26];
		char last = word[0];
		for (char alphabet : word) {
			if (last != alphabet && visited[alphabet - 'a']) return 0;
			visited[alphabet - 'a'] = true;
			last = alphabet;
		}
		return 1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int answer = 0;
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			answer += checkGroup(br.readLine().toCharArray());
		}
		System.out.println(answer);
	}

}
