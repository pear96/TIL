package 스택;

/* 스택인건 아는데 그냥.. 완탐으로 풀었음
 * */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_쇠막대기_10799 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		
		int answer = 0;
		int pipe = 0;
		int idx = 0;
		
		while (idx < input.length()) {
			if (input.charAt(idx) == '(') {
				if (input.charAt(idx+1) == '(') {
					// idx는 파이프다.
					pipe++;
				} else {
					// idx는 레이저다.
					answer += pipe;
					// 레이저는 한 괄호쌍이다. 한 칸 뛰어서 검사한다.
					idx++;
				}
			} else {
				// 레이저의 닫는 괄호는 조회하지 못 하도록 뛰어넘는다.
				// 따라서 닫는 괄호가 나왔다면 이건 파이프의 끝이다.
				answer++;
				pipe--;
			}
			idx++;
		}
		
		System.out.println(answer);
	}

}
