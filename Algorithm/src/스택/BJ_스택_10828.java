package 스택;

//https://www.acmicpc.net/problem/10828


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_스택_10828 {
	
	public static void main(String[] args) throws IOException {
		Stack<Integer> stack = new Stack<Integer>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			switch (order) {
			case "push":
				stack.add(Integer.parseInt(st.nextToken()));
				break;
			case "pop":
				if (stack.isEmpty()) {
					sb.append("-1\n");
				} else {
					int num = stack.pop();
					sb.append(num + "\n");
				}
				break;
			case "size":
				sb.append(stack.size() + "\n");
				break;
			case "empty" : 
				if (stack.isEmpty()) sb.append("1\n");
				else sb.append("0\n");
				break;
			case "top":
				if (stack.isEmpty()) sb.append("-1\n");
				else sb.append(stack.peek() + "\n");
				break;
			}
		}
		System.out.println(sb.toString());
	}
}
