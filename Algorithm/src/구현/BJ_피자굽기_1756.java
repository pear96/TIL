package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_피자굽기_1756 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int D = Integer.parseInt(st.nextToken()); // 오븐 깊이
		int N = Integer.parseInt(st.nextToken()); // 피자 반죽 개수

		// int[] oven = new int[D]; // 오븐 지름
		int[] minD = new int[D]; // 누적 최소 지름

		// 오븐 세팅
		int minDepth = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < D; i++) {
			minDepth = Math.min(minDepth, Integer.parseInt(st.nextToken()));
			minD[i] = minDepth;
		}

		// 반죽 넣기
		int lastIdx = D;
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			lastIdx--;
			int dough = Integer.parseInt(st.nextToken());
			while (lastIdx >= 0 && minD[lastIdx] < dough) {
				lastIdx--;
			}
			if (lastIdx < 0) break;
		}

		System.out.println(lastIdx+1);
	}
}
