package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_통나무건너뛰기_11497 {

	public static void main(String[] args) throws IOException {
		StringBuilder answer = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 1 <= N  <= 10_000
			int[] nums = new int[N]; // 입력 저장
			int[] result = new int[N]; // 숫자 배치
			int maxDiff = 0; // 최대 차이
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			// 문제 풀이 전략
			// 1. 내림차순으로 정렬한다. -> (수정) 오름차순 정렬하고 뒤에서부터 온다.
			// -> 내림차순을 위해 Integer[] 배열로 생성하고 Comparator.reverseOrder() : 820ms
			// -> 오름차순으로 만들고 뒤에서부터 조회 : 452ms
			// -> 객체와 primitive의 차이가 크다.
			// 2. 가장 큰 숫자를 중간에 배치한다.
			// 3. 중간의 왼쪽, 오른쪽에 순서대로 하나씩 배치하면서 최대 차이값을 계산한다.
			Arrays.sort(nums);
				
			int idx = N-1; // nums 순회
			int step = 1; // 양 옆 거리
			int half = N / 2;
			
			result[half] = nums[idx--];
			
			while(idx >= 0) {
				if (result[half-step] == 0) {
					// 왼쪽에 배치
					result[half-step] = nums[idx--];
					maxDiff = Math.max(maxDiff, result[half-step+1] - result[half-step]);
				} else {
					// 오른쪽에 배치
					result[half+step] = nums[idx--];
					maxDiff = Math.max(maxDiff, result[half+step-1] - result[half+step]);
					step++;
				}
			}
			answer.append(maxDiff+"\n");			
		}
		answer.deleteCharAt(answer.length()-1);
		System.out.println(answer.toString());
		
	}

}
