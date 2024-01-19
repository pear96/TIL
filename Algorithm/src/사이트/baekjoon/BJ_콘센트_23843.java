package 사이트.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_콘센트_23843 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Integer[] devices = new Integer[N];
		st = new StringTokenizer(br.readLine());		
		for(int i = 0; i < N; i++) {
			devices[i] = Integer.parseInt(st.nextToken());
		}
		// 1. 충전시간 내림차순 정렬
		Arrays.sort(devices, Collections.reverseOrder());
		int idx = 0;
		int[] concents = new int[M];
		
		// 2. 콘센트 돌면서 가장 빠른 곳에다가 할당
		while (idx < N) {
			int concent = Integer.MAX_VALUE;
			int position = 0;
			for (int i = 0; i < M; i++) {
				if (concent > concents[i]) {
					concent = concents[i];
					position = i;
				}
			}
			
			concents[position] += devices[idx++];
		}
		
		System.out.println(Arrays.stream(concents).max().getAsInt());
	}

}
