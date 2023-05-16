package 트리;

//https://www.acmicpc.net/problem/1991


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BJ_트리순회_1991 {
	static int N;
	static int[] left, right;
	static StringBuilder sb;
	
	static void preOrder(int idx) {
		// idx 방문
		sb.append(String.valueOf((char) (idx + 'A')));
		if (left[idx] >= 0)	preOrder(left[idx]);
		if (right[idx] >= 0) preOrder(right[idx]);
	}
	
	static void inOrder(int idx) {
		if (left[idx] >= 0)	inOrder(left[idx]);
		sb.append(String.valueOf((char) (idx + 'A')));
		if (right[idx] >= 0) inOrder(right[idx]);
	}
	
	static void postOrder(int idx) {
		if (left[idx] >= 0)	postOrder(left[idx]);
		if (right[idx] >= 0) postOrder(right[idx]);
		sb.append(String.valueOf((char) (idx + 'A')));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		left = new int[N];
		right = new int[N];
		
		for(int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			int p = input[0].charAt(0) - 'A';
			int l = input[1].charAt(0) - 'A';
			int r = input[2].charAt(0) - 'A';
			left[p] = l;
			right[p] = r;
//			String left = String.valueOf((char) (l + 'A'));
//			String right = String.valueOf((char) (r + 'A'));
//			System.out.println(l + " " + left + " " + r + " " + right);
		}
		sb = new StringBuilder();
		preOrder(0);
		sb.append("\n");
		
		inOrder(0);
		sb.append("\n");
		
		postOrder(0);
		sb.append("\n");
		
		System.out.println(sb.toString());
	}

}
