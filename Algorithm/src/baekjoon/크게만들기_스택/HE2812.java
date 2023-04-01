package baekjoon.크게만들기_스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class HE2812 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int answer = 0;
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		String str = br.readLine();
		int[] input = new int[N];
		for(int i = 0; i < N; i++) {
			input[i] = str.charAt(i) - '0';
		}
		
		LinkedList<Integer> stack = new LinkedList<Integer>();
		
		for(int num : input) {
			while(K > 0 && !stack.isEmpty() && stack.getLast() < num) {
				stack.removeLast();
				K -= 1;
			}
			stack.add(num);
		}
		
		for(int i = 0; i < K; i++) {
			stack.removeLast();
		}
		
		StringBuilder sb = new StringBuilder();
		for(int num : stack) {
			sb.append(num);
		}
		
		System.out.println(sb.toString());
	}

}
