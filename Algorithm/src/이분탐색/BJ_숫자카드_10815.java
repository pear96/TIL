package 이분탐색;

//https://www.acmicpc.net/problem/10815


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_숫자카드_10815 {
	static int N, M;
	static int[] cards, query;
	
	public static boolean binarySearch(int q) {
		// low와 high는 각각 idx가 된다.
		// mid 는 숫자를 볼 인덱스. 
		// cards[mid]가 원하는 값보다 작으면 low를 올리고
		// cards[mid]가 원하는 값보다 크면 high를 내리며
		// 같은 경우 해당 값이 정답이다.
		int low = -1;
		int high = N;
		int mid;
		
		while (low + 1 < high) {
			mid = (low + high) / 2;
			
			if (cards[mid] == q) {
				return true;
			} else if (cards[mid] < q) {
				low = mid;
			} else {
				high = mid;
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); // 1 <= N <= 50만
		cards = new int[N]; // -천만 ~ 천만. 같은 수가 적힌 경우는 X
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		// 이분 탐색 하려면 정렬은 필수
		Arrays.sort(cards);
		
		M = Integer.parseInt(br.readLine()); // 1 <= M <= 50만
		query = new int[M]; // -천만 ~ 천만. 같은 수가 적힌 경우는 X
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			query[i] = Integer.parseInt(st.nextToken());
		}
		
		
		// 이분 탐색
		// O(M) * O(log2천만)
		for(int i = 0; i < M; i++) {
			int q = query[i];
			if (binarySearch(q)) {
				sb.append(1 + " ");
			} else {
				sb.append(0 + " ");
			}
		}
		
		sb.deleteCharAt(2*M-1); // 맨 마지막 공백 제거
		System.out.println(sb.toString());
	}

}
