package 그리디;

public class PG_기지국설치 {
    class Solution {
        public int solution(int n, int[] stations, int w) {
            int answer = 0;
            int start = 1;
            int width = 2 * w + 1;

            for (int station : stations) {
                if (start < station - w) {
                    int end = station-w;
                    answer += (end - start)/ width;
                    answer += ((end-start)% width == 0)? 0 : 1;
                }
                start = station + w + 1;
            }

            if (start <= n) {
                answer += (n - start+1)/(2*w+1);
                answer += ((n-start+1)%(2*w+1) == 0)? 0 : 1;
            }

            return answer;
        }
    }
}
