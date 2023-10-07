package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BJ_숫자카드_2591 {
	static int length;
	static int[] num;
	static ArrayList<Integer> dp;
	
	static int callDP(int idx) {
		// 마지막에 도달했으면 경우의 수 1 적립
		if (idx == length) return 1;
		// 숫자에 0이 포함된 경우 카드가 없기 때문에 경우의 수 0
		if (num[idx] == 0) return 0;
		// 이미 계산된 경우 빠르게 반환 (이 위치를 또 올 수 있음, 한자리, 두자리 뒤랑 비교하느라)
		if (dp.get(idx) != -1) return dp.get(idx);
		// 0으로 초기화
		dp.set(idx, 0);
		// idx번째에 숫자 하나만 추가하면 된다. -> 다음 자리의 경우의 수 가져오기 (여기 계산하고, 나중에 줘)
		dp.set(idx, callDP(idx+1));
		// idx, idx+1 번째 숫자 두 자리는 34 이하의 값만 가능
		if ((idx+2 <= length) && ((num[idx] * 10 + num[idx+1]) <= 34)) {
			dp.set(idx, dp.get(idx) + callDP(idx+2));
		}
		return dp.get(idx);
			
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String number = br.readLine();
		length = number.length();
		num = new int[length];
		
		for(int i = 0; i < length; i++) {
			num[i] = number.charAt(i) - '0';
		}
		
		System.out.println(Arrays.toString(num));
		dp = new ArrayList<>(Collections.nCopies(length, -1));
		// ex) 숫자가 27123 일 때
		// 처음 dp = {-1, -1, -1, -1, -1}
		// 결과 dp = {6, 3, 3, 2, 1}

		System.out.println(callDP(0));
//		System.out.println(dp.toString());
	}

}
