package 백트랙킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ_지름길_1446 {
	static int N, goal;
	static LinkedList<Short> paths;
	static int answer = Integer.MAX_VALUE;
	
	static class Short implements Comparable<Short>{
		int start;
		int end;
		int distance;
		
		public Short(int s, int e, int d) {
			this.start = s;
			this.end = e;
			this.distance = d;
		}
		
		@Override
		public int compareTo(Short other) {
			if (this.start == other.start) {
				// 지름길로 짧아지는 길이의 비율이 큰 것 순으로 정렬
				double thisRatio = (double) (this.end - this.start - this.distance) / (this.end - this.start);
				double otherRatio = (double) (other.end - other.start - other.distance) / (other.end - other.start);
				return (int) (otherRatio - thisRatio);
			}
			return this.start - other.start;
		}
	}
		
	/**
	 * @param now 현재 위치
	 * @param idx 현재 인덱스
	 * @param sum 누적 거리
	 */
	static void backtracking(int now, int idx, int sum) {
		if (idx == paths.size()) {
			// 종료조건
//			System.out.println("목적지 : " + goal + ", 현재 : " +now + ", answer = " + answer + ", 지금 거리 = " + (sum + (goal-now)));
			answer = Math.min(answer, sum + (goal - now));
			return;
		}
		
		// 백트래킹
		if (sum > answer) return;
		
		Short path = paths.get(idx);
		
		if (now <= path.start && path.end <= goal && path.distance < (path.end - path.start)) {
			// idx 지름길을 사용하는 경우
//			System.out.println(idx + "번 지름길 사용 : " + path.end + "로 가고, " + (sum+(path.start - now)+path.distance) + "만큼 누적됨");
			backtracking(path.end, idx+1, sum+(path.start - now)+path.distance);
		}
		// 그냥 지나가는 경우
//		System.out.println(idx+"번 지름길 패스 : "+now + "에서 안 움직임, " + sum + " 그대로 누적");
		backtracking(now, idx+1, sum);
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		goal = Integer.parseInt(input[1]);
		StringTokenizer st;
		paths = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			if (e <= goal && (e-s) > d) {
				paths.add(new Short(s, e, d));
			}
		}
		
		Collections.sort(paths);
		
//		for(int i = 0; i < paths.size(); i++) {
//			Short temp = paths.get(i);
//			System.out.println(temp.start + ", "  + temp.end + ", " + temp.distance + ". 비율 : " + ((double) (temp.end - temp.start - temp.distance) / (temp.end - temp.start)));
//		}
		
		backtracking(0, 0, 0);
		System.out.println(answer);
	}

}
