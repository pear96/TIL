package baekjoon.에너지모으기_그래프;

/* boolean 배열을 사용할지, int 배열을 직접 수정해서 parameter로 넘길지 고민했는데
 * 그냥 전역변수에 list를 만들고 실제로 빼고, 원래 위치에 넣었다 (list.add(idx, value)) 해서 품
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HE16198 {
	static int answer = 0;
	static int N;
	static int[] marbles;
	static boolean[] exist;
	
	static void dfs(int cnt, int score) {
		if(cnt == N-2) {
			answer = Math.max(answer, score);
			return;
		}
		
		for (int i = 1; i < N-1; i++) {
			if (exist[i] == false) continue;
			
			exist[i] = false;
			int left = 0, right = 0;
			
			// 왼쪽 구슬 중 사용 가능한 것
			for(int l = i-1; l >= 0; l--) {
				if (exist[l]) {
					left = marbles[l];
					break;
				}
			}
			
			// 오른쪽 구슬 중 사용 가능한 것
			for(int r = i+1; r < N; r++) {
				if (exist[r]) {
					right = marbles[r];
					break;
				}
			}
			
			dfs(cnt+1, score + left * right);
			exist[i] = true;
		}
	}
		
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		marbles = new int[N];
		exist = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			marbles[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.fill(exist, true);
		
		dfs(0, 0);
		
		System.out.println(answer);
	}

}
