package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_앱_7579 {
	static final int MAX_COST = 10001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int answer = MAX_COST;
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] price = new int[N];
		int[] memory = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			price[i] = Integer.parseInt(st.nextToken());
		}
		
		// M의 최대 값은 10_000_000 이기 때문에 dp 테이블로 사용하기엔 부적합하다.
		// 그렇다면 내가 사용할 수 있는 숫자는 앱의 수와, 비용이다.
		// 따라서 dp[사용한 비용] = 얻을 수 있는 최대 메모리 로 설정하고,
		// dp테이블을 만들고 난 뒤, M을 최초로 넘기는 비용이 정답이 된다.
		// 답이 index가 아니라 value에 있는 문제
		// 최대 비용은 100개 * 100원 이다. (비용의 합으로 해도 되지만..)
		int[] dp = new int[MAX_COST];
		
		for (int app = 0; app < N; app++) {
			// 역순으로 보지 않으면 같은 앱을 2번 이상 비활성화 시키는 사태가 일어남
			for (int cost = MAX_COST-1; cost >= 0; cost--) {
				if (cost >= price[app]) {
					// dp[cost] => 이번 앱을 비활성화 시키지 않았을 때 메모리 (유지)
					// dp[cost-price[app]] + memory[app] => 이번 앱을 비활성화 시켰을 때 메모리 (추가)
					dp[cost] = Math.max(dp[cost], dp[cost-price[app]] + memory[app]);
					if (dp[cost] >= M) answer = Math.min(answer, cost);
				}
			}
		}
		System.out.println(answer);
	}

}
