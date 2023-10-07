package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_호텔_1106 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] price = new int[N];
		int[] people = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			price[i] = Integer.parseInt(st.nextToken());
			people[i] = Integer.parseInt(st.nextToken());
		}
		
		// C+100인 이유 : C명보다 많은 고객의 수에서 최소 비용이 발생할 수 있음.
		// 한 도시에서 최대로 홍보할 수 있는 수는 100명임
		// ex) 1명 100원, 100명 1원
		
		int[] dp = new int[C+100];
		Arrays.fill(dp, 100000); // 최대 비용 : 1명당 100원으로 1000명 만들기
		dp[0] = 0;
		
		
		for (int city = 0; city < N; city++) {
			// 주어진 인원 수에서, 최대로 고려해볼 인원 수까지
			for (int cnt = people[city]; cnt < C+100; cnt++) {
				// cnt 만큼 모집할 때, 과거의 cnt명까지 확보했던 최소 비용과 비교.
				dp[cnt] = Math.min(dp[cnt], dp[cnt-people[city]] + price[city]);
			}
		}
		
		System.out.println(Arrays.stream(dp, C, dp.length).min().getAsInt());

	}

}
