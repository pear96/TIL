package 이분탐색;

//https://www.acmicpc.net/problem/1920


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* BS => Binary Search
 * 이분 탐색에도 있길래 사용
 * */
public class BJ_그림_1920_BS {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		// 이분탐색을 위해 정렬
		Arrays.sort(nums);
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		for(int j = 0; j < M; j++) {
			int target = Integer.parseInt(st.nextToken());
			
			int low = 0; // idx 첫 번째
			int high = N; // idx 마지막
			
			while(low+1 < high) {
				int mid = (low + high) / 2;

				// 내가 원하는 값보다 지금 보는게 크면, 내 뒤는 필요 없다.
				// 뒤를 지우기 위해 high를 mid로 낮춘다.
				if (target < nums[mid]) high = mid;
				// 내가 원하는 값보다 지금 보는게 같거나 작으면, 내 앞은 필요가 없다.
				// 앞을 지우기 위해 low를 mid로 높인다.
				else low = mid;
			}
			
			if (nums[low] == target) sb.append("1\n");
			else sb.append("0\n");
		}
		System.out.println(sb.toString());
	}

}
