package 그리디;

/* 문제 풀이 :
 * 처음엔 비트 마스킹을 활용해야하나 생각했으나, 
 * 1과 0을 각 덩어리로 생각해서 둘 중 개수가 적은 것을 계산하면 된다는 것을 깨달았다.
 * 더 적은 수들의 덩어리를 뒤집어 주면 되니깐. 실제로 뒤집을 필요는 없다.
 * 또한, 1과 0의 덩어리 개수는 2 이상 차이날 수 없다.
 * 시간은 O(N = S.length())이다.
 * */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_뒤집기_1439 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		int oneCnt = 0;
		int zeroCnt = 0;
		
		if (S.charAt(0) == '0') zeroCnt++;
		else oneCnt++;
		
		for(int i = 1; i < S.length(); i++) {
			if (S.charAt(i-1) != S.charAt(i)) {
				if (S.charAt(i) == '1') oneCnt++;
				else zeroCnt++;
			}
		}
		System.out.println(Math.min(oneCnt, zeroCnt));
	}

}
