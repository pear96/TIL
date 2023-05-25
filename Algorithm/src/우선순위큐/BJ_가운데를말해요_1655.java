package 우선순위큐;
// https://gh402.tistory.com/32

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class BJ_가운데를말해요_1655 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		// 중간값 + 이전의 값들
		PriorityQueue<Integer> before = new PriorityQueue<>(Collections.reverseOrder());
		// 중간값 이후의 값들
		PriorityQueue<Integer> after = new PriorityQueue<>();

		// i가 짝수 + number가 중간값보다 크면 => after에 그냥 넣음
		// i가 홀수 + number가 중간값보다 크면 => 중간값 before에 넣고, after에 number 넣고, after의 처음이 중간값 됨
		for(int i = 0; i < N; i++) {
			int number = Integer.parseInt(br.readLine());
			
			if (before.size() == after.size()) {
				before.add(number);
				
				// 엇 근데 before.peek() 보다 after.peek()가 작으면 바꿔줘야됨
				if (!after.isEmpty() && before.peek() > after.peek()) {
					after.add(before.poll());
					before.add(after.poll());
				}
				
			}
			else {
				after.add(number);
				// 엇 근데 before.peek() 보다 after.peek()가 작으면 바꿔줘야됨
				if (before.peek() > after.peek()) {
					after.add(before.poll());
					before.add(after.poll());
				}
			}
			
			sb.append(before.peek() +"\n");
		}
		System.out.println(sb);
	}

}
