package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_용액_2467 {
	static int N;
	static int[] answer = new int[2];
	static int[] liquid;
	
	static void binary () {
		int leastGap = Integer.MAX_VALUE;
		
		// liquid 하나씩 보면서, 짝지를 찾아준다.
        for(int l = 0; l < N-1; l++) {
            // 나와 반대부호를 가진 값과 가장 근사한 것을 찾는다.
            int now = liquid[l];
            int low = l+1, high = N - 1;
            int mid;

            while(low <= high) {
                mid = (low + high) / 2;

                if (leastGap > Math.abs(now + liquid[mid])) {
                    leastGap = Math.abs(now + liquid[mid]);
                    answer[0] = now;
                    answer[1] = liquid[mid];
                }

                if (now + liquid[mid] <= 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
	}
	
	static void twoPointer() {
		int left = 0;
		int right = N-1;
		int sum = Integer.MAX_VALUE;
		
		while (left < right) {
			// 값 갱신
			if (Math.abs(sum) > Math.abs(liquid[left] + liquid[right])) {
				sum = liquid[left] + liquid[right];
				answer[0] = liquid[left];
				answer[1] = liquid[right];
			}
			
			// 갱신은 했고, 더 있나 탐색해보기
			// 합이 0보다 크면 right가 양수에 클 가능성이 높은 값이니 얘를 줄여보고
			// 0보다 작다면 left가 음수에 작을 가능성이 높은 값이니 얘를 키워본다.
			if (liquid[left] + liquid[right] > 0) {
				right--;
			} else left++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// 이미 정렬된 값이 저장됨
		liquid = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			liquid[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(liquid);
		
//		twoPointer();
		binary();
		
		System.out.println(answer[0] + " " + answer[1]);
		
	}

}
