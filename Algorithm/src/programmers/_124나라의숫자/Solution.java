package programmers._124나라의숫자;

public class Solution {
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();
        int[] num = {4, 1, 2};
        while (n > 0) {
            int r = n % 3;
            n /= 3;
            answer.append(num[r]);
            // 6 을 3으로 나누면 2, 0이다. 실제 값은 14다.
            // 0은 4가 되며, 몫에서 1을 빼야한다. 
            if (r == 0) {
                n--;
            }
        }
        return answer.reverse().toString();
    }
}