package baekjoon.부분수열의합_비트마스킹;

/* 96ms, 13496KB
 * https://velog.io/@abc5259/%EB%B0%B1%EC%A4%80-14225-%EB%B6%80%EB%B6%84%EC%88%98%EC%97%B4%EC%9D%98-%ED%95%A9-JAVA
 * DFS로 풀고싶으면 해당 숫자를 고르는 경우와, 고르지 않는 경우를 둘 다 DFS로 돌아주면 된다.
 * */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HE14225_DFS {
	static int N;
	static int[] num;
	static boolean[] exist;
	
	public static void dfs(int idx, int sum) {
		if (idx == N) exist[sum] = true;
		else {
			// 완전 탐색
			dfs(idx+1, sum + num[idx]);
			dfs(idx+1, sum);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		exist = new boolean[20*100000+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) num[i] = Integer.parseInt(st.nextToken());
		
		dfs(0, 0);
		
		for(int i = 1; i < 20*100000+1; i++) {
			if (!exist[i]) {
				System.out.println(i);
				break;
			}
		}
	}

}
