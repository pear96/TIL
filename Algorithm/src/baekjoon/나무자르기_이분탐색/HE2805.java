package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HE2805 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 나무의 수
		int M = Integer.parseInt(st.nextToken()); // 원하는 나무의 길이
		int[] trees = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) trees[i] = Integer.parseInt(st.nextToken());
		
		
		// 이분 탐색. parameter = 자르려는 나무의 높이 중 최대값
		// 나무 높이의 범위는 0 ~ 10^9-1 이다. low는 high보다 1 작으므로
		// low의 위치는 0 ~ 10^9-1이 보장된다.
		int low = 0, high = 1000000000;
		int mid = (low + high) / 2;
		long cut = 0;
		
		while(low+1 < high) {
			mid = (low + high) / 2; // 이 나무 높이를 기준으로 잘라보겠다.
			cut = 0; // 자른 나무 길이 누적
			
			for(int i = 0; i < N; i++) {
				if(trees[i] >= mid) {
					cut += trees[i] - mid; // 나무 높이를 초과해 자를 수는 없다.
				}
			}
			
			if(cut >= M) low = mid; // 나무 길이가 남는다 = 자르려는 높이를 높여도 된다.
			else high = mid; // 나무 길이가 모자라다 = 자르려는 높이를 낮춰야한다.
		}
		
		System.out.println(low);
		
	}

}
