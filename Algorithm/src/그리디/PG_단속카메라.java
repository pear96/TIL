package 그리디;

import java.util.Arrays;
import java.util.Comparator;

public class PG_단속카메라 {
	// 다른 사람 코드 참고
	// 진출 시점으로만 정렬해도 되는구나.. + lambda식 정렬
	class Solution {
		public int solution(int[][] routes) {
			int answer = 0;
			Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));
			int last = Integer.MIN_VALUE;

			for (int[] car : routes) {
				if (car[0] > last) {
					// 카메라 한대 더
					answer++;
					last = car[1];
				}
			}

			return answer;
		}
	}
	// 내가 원래 짠 코드
	class Solution2 {
		public int solution(int[][] routes) {
			int answer = 0;
			// 진입, 진출 시점 작은 순으로 정렬
			Arrays.sort(routes, new Comparator<int[]>() {
				public int compare(final int[] car1, final int[] car2) {
					if (car1[0] != car2[0]) return Integer.compare(car1[0], car2[0]);
					return Integer.compare(car1[1], car2[1]);
				}
			});
			int enter = routes[0][0];
			int leave = routes[0][1];
			answer = 1;

			for (int i = 1; i < routes.length; i++) {
				if (routes[i][0] > leave) {
					// 카메라 한대 더
					answer++;
					enter = routes[i][0];
					leave = routes[i][1];
				} else {
					enter = Math.min(enter, routes[i][0]);
					leave = Math.min(leave, routes[i][1]);
				}
			}

			return answer;
		}
	}

}
