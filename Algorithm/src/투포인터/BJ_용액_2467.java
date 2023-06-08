package 투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_용액_2467 {
	static int N;
	static int[] liq;
	static int[] answer = new int[2];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		N = Integer.parseInt(br.readLine());
		liq = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i ++) {
			liq[i] = Integer.parseInt(st.nextToken());
		}
		// 정렬
		Arrays.sort(liq);
		
		// 투포인터
		int start = 0, end = N-1;
		// 0에 가까운 용액
		int minGap = Integer.MAX_VALUE;
		
		while (start < end) {
			int sum = liq[start] + liq[end];
			if (minGap > Math.abs(sum)) {
				minGap = Math.abs(sum);
				answer[0] = liq[start];
				answer[1] = liq[end];
			}
			
			if (sum < 0) start++;
			else if (sum > 0) end--;
			else break; // 뭐지 0이면 그냥 끝아닌가..
		}
		System.out.println(answer[0] + " " + answer[1]);
		
	}

}
