package 누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class BJ_태상이의훈련소생활_19951 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] ground = new int[N];
		for(int i = 0; i < N; i++) ground[i] = Integer.parseInt(st.nextToken());

		int[] diff = new int[N];
		for(int order = 0; order < M; order++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			diff[a-1] += k;
			if (b < N) diff[b] += -k;
		}
		// 누적합 구하기
		for (int i = 1; i < N; i++) diff[i] += diff[i-1];
		// 변동사항 반영하기
		StringJoiner answer = new StringJoiner(" ");
		for (int i = 0; i < N; i++) {
			ground[i] += diff[i];
			answer.add(String.valueOf(ground[i]));
		}

		System.out.println(answer);
	}
}
