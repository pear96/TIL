package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_가르침_1062 {
	static int N, K;
	static String[] word;
	static boolean[] visited;
	static int answer = 0;
	
	static void backtracking(int alpha, int cnt) {
		// 필수로 사용해야하는 알파벳 제외한 개수를 모두 채웠다.
		if (cnt == K - 5) {
			// 몇개의 단어를 읽을 수 있는가?
			int count = 0;
			for (int i = 0; i < N; i++) {
				boolean readable = true;
				for(int c = 0; c < word[i].length(); c++) {
					if (!visited[word[i].charAt(c) - 'a']) {
						readable = false;
						break;
					}
				}
				if (readable) {
					count++;
				}
			}
			answer = Math.max(answer, count);
			return;
		}
		
		
		
		for (int i = alpha; i < 26; i++) {
			// 사용하지 않은 알파벳이라면
			if (visited[i] == false) {
				visited[i] = true;
				// i 알파벳 사용하기로 함. 사용한 알파벳 수 +1
				backtracking(i, cnt+1);
				// 사용 해제 후 다른 알파벳 보러 감
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
		
		word = new String[N];
		
		// 접두사 'anta'와 접미사 'tica' 제거 후 저장 
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			str.substring(4, str.length()-4);
			word[i] = str;
		}
		
		// 특수 상황
		if (K < 5) {
			System.out.println("0");
			return;
		}
		if (K == 26) {
			System.out.println(N);
			return;
		}
		
		
		// 알파벳 사용 체크
		visited = new boolean[26];
		visited['a' - 'a'] = true;
		visited['n' - 'a'] = true;
		visited['t' - 'a'] = true;
		visited['i' - 'a'] = true;
		visited['c' - 'a'] = true;
		
		backtracking(0, 0);
		System.out.println(answer);
	}

}
