package baekjoon.그리디;

/* 알고리즘 분류 봐버려서... 답안을 본 것이나 마찬가지...😣😣 * */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class HE11000 {
	
	static class Class implements Comparable<Class>{
		int start;
		int end;
		
		public Class(int s, int e) {
			this.start = s;
			this.end = e;
		}
		
		@Override
		public int compareTo(Class c) {
			if (this.start != c.start) {
				return this.start - c.start;
			}
			return this.end - c.end;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 1 <= N <= 200,000
		int N = Integer.parseInt(br.readLine());
		// 0 <= s, e <= 1,000,000,000
		Class[] schedule = new Class[N];
		
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			schedule[i] = new Class(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
		}
		
		Arrays.sort(schedule);
		
		// 수업은 20만개, 시간은 10억까지...
		// 빠르게 해결하는 방법?
		// PQ에 끝나는 시간을 넣는다.
		// 근데 이럴려면 시작시간, 종료시간 별로 정렬해야한다.
		// 맨 앞보다 내 시작 시간이 빠르면 내 끝나는 시간을 넣는다.
		// 느리면 맨 앞을 빼고 내 끝나는 시간을 넣는다.
		// 강의실 개수는 max로 PQ의 길이가 가장 길었을 때를 저장한다.
		PriorityQueue<Integer> q = new PriorityQueue<>();
		q.add(schedule[0].end);
		
		for(int idx = 1; idx < N; idx++) {
			Class now = schedule[idx];
			
			if (now.start >= q.peek()) {
				q.poll();
			}
			q.add(now.end);
			
		}
		int answer  = q.size();
		
		System.out.print(answer);
		
	}

}
