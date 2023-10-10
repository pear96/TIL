package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 정답 : 올바른 등식의 '수' 구하기 = 경우의 수 구하기
 * 숫자의 개수는 최대 100개.
 * 중간동안 결과물이 0 이상 20이하여야 한다. (=백트래킹)
 * 그런데 앞에서 +, - 해가면 2*100 아닌가..?? 정답이 2**63 -1 이 보장된다 하더라도 이 식이 과연 안전한걸까?
 * */

public class BJ_1학년_2트 {
	static int N;
	static int[] num;
	static long[][] dp; // 정답이 2**63-1 이면.. long이다
	
	static long callDp(int idx, int result) {
		// 마지막에는 등호를 넣어야 하기 때문에 N-1 번째 숫자 까지만 연산해봐야한다.
		if (idx == N-1) {
			// 끝까지 왔고 목표했던 숫자를 만들었다면!
			if (result == num[N-1]) return 1;
			return 0;
		}
		
		// 올바른 경우가 아니다.
		if (result < 0 || result > 20) return 0;
		
		// 이미 계산된 적이 있는 숫자이므로 빠르게 처리한다.
		// -1은 아직 계산을 안한거고, 0은 올바르지 않은 경우이다.
		// 따라서 초기값을 -1로 설정하는 것이 적절하다.
		if (dp[idx][result] != -1) return dp[idx][result];
		
		// idx 1차원 배열로만 하면 당연히(?) 제대로 계산이 안된다.
		// 7번째 순서의 카드에 +를 처리를 해봤는데 올바른 경우가 없다고 하자.
		// 그러면 6번째 순서로 돌아왔을 때, -를 처리해봐야하는데 이미 7번을 봤기 때문에
		// 그 이상이 계산되지 않고 그냥 0이 돌아오게 된다.
		// 따라서 해당 위치에서 누적 값이 가능한 경우가 몇번인지 세야한다.
		// 순서까지 있기 때문에 겹칠일이 없을 것 같지만 연산을 직접 손으로 그려보면
		// 같은 순서에 같은 누적값을 계산하는 경우가 생긴다.
		dp[idx][result] = 0;
		
		dp[idx][result] += callDp(idx+1, result + num[idx]);
		dp[idx][result] += callDp(idx+1, result - num[idx]);
		
		return dp[idx][result];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		dp = new long[N][21];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
			Arrays.fill(dp[i], -1);
		}
		
		System.out.println(callDp(1, num[0]));
	}

}
