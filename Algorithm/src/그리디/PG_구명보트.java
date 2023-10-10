package 그리디;

import java.util.Arrays;

public class PG_구명보트 {

	public static void main(String[] args) {
		int[] people = new int[] {70, 50, 80, 50};
		System.out.println(solution(people, 100));

	}
	
	public static int solution(int[] people, int limit) {
		int answer = 0;
        Arrays.sort(people);
        // System.out.println(Arrays.toString(people));
        
        int s = 0;
        int l = people.length - 1;
        
        while (s < l) {
            if (people[s] + people[l] <= limit) {
                s++;
                l--;
                answer++;
            } else {
                l--;
                answer++;
            }
        }
        
        // 홀수 (혼자 탈 수 밖에 없음)
        if (s == l) answer++;
        
        return answer;
    }

}
