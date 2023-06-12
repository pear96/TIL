package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_막대기_1094 {
	static int N;
	
	static void solveWithArray() {
		// 2 ** i 로 돌면서 존재하는지, 개수 확인
		int[] num = new int[65];
		// 가장 작은 수
		int min = 64;
		
		num[64] = 1;
		
		while(true) {
			// 현재 막대기들의 합
			int sum = 0;
			for(int i = 0; i < 7; i++) {
				int power = (int) Math.pow(2, i);
				sum += power * num[power];
				// 젤 작은 수 업데이트
				if (num[power] > 0) min = Math.min(min, power);
			}
			if (sum == N) break;
			// N과 같지 않으니 젤 작은걸 자른다.
			int minHalf = min / 2;
			// 개수 반영
			num[min] -= 1;
			num[minHalf] += 1;
			// 나머지들의 합을 비교함
			sum += (-min+minHalf);
			
			if (sum < N) {
				// 버릴까 고민한 막대기 다시 붙임
				sum += minHalf;
				num[minHalf] += 1;
			}
		}
		
		// 막대기 몇개 있나 확인
		int cnt = 0;
		
		for(int i = 0; i < 7; i++) {
			int power = (int) Math.pow(2, i);
			cnt += num[power];
		}
		
		System.out.println(cnt);
	}
	
	static void BitMask() {
		int cnt = 0;
		for(int i = 0; i < 9; i++) {
			if ((N & (1 << i)) != 0) cnt++;
		}
		System.out.println(cnt);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		
	}

}
