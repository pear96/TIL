package 수학;

//https://www.acmicpc.net/problem/17436


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_소수의배수_17436 {
	static int N;
	static long M;
	static long answer = 0;
	static int[] primes;
	
	public static void combination(int cnt, int start, int[] picks, boolean[] visited) {
		if(cnt == 0) {
			long divider = 1;
			// 고른 수들을 곱해준다.
			for(int pick: picks) {
				divider *= pick;
			}
			
			if (divider > M) return;
			
			// 그 값을 나눈 몫 연산
			if(picks.length % 2 == 1) answer += (M / divider);
			else answer -= (M / divider);
			System.out.println("정답 : " + answer + "<< 곱 : " + divider + " 고른 수 : " + Arrays.toString(picks));
		} else {
			for(int i = start; i < N; i++) {
				visited[i] = true;
				picks[cnt-1] = primes[i];
				combination(cnt-1, i+1, picks, visited);
				
				visited[i] = false;				
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Long.parseLong(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		primes = new int[N];
		for(int i=0; i < N; i++) {
			primes[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int cnt = 1; cnt < N+1; cnt++) {
			int[] pick = new int[cnt];
			boolean[] visited = new boolean[N];
			combination(cnt, 0, pick, visited);
		}
		
		
		System.out.println(answer);
	}
}
