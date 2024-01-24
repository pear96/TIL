package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_스택_10828 {
	public static void main(String[] args) throws IOException {
		StringBuilder answer = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 스택을 구현해라?? 일단 N개 이상의 수가 쌓일 수는 없으니 int 배열 만들어준다.
		int[] stack = new int[N];
		// 스택의 마지막 위치
		int pointer = -1;

		// 명령어 실행
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			switch (order) {
				case "push":
					stack[++pointer] = Integer.parseInt(st.nextToken());
					break;
				case "pop":
					answer.append((pointer == -1) ? -1 : stack[pointer--]);
					answer.append("\n");
					break;
				case "size":
					answer.append(pointer+1);
					answer.append("\n");
					break;
				case "empty":
					answer.append((pointer == -1)? 1 : 0);
					answer.append("\n");
					break;
				case "top":
					answer.append((pointer == -1)? -1 : stack[pointer]);
					answer.append("\n");
					break;
			}
		}
		System.out.println(answer);

	}

}
