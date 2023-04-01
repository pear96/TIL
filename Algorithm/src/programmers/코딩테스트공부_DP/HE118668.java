package programmers.코딩테스트공부_DP;

import java.util.Arrays;

public class HE118668 {
	

	public static int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
        int[][] dp = new int [151][151];
        for(int i = 0; i < 151; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[alp][cop] = 0;
        
        int goal_alg = 0, goal_cod = 0;
        
        
        for(int[] problem : problems) {
            goal_alg = Math.max(goal_alg, problem[0]);
            goal_cod = Math.max(goal_cod, problem[1]);
        }
        
        for(int alg = alp; alg <= 150; alg++) {
            for(int cod = cop; cod <= 150; cod++) {
                for(int p = 0; p < problems.length; p++) {
                	if (alg+1 <= 150)  dp[alg+1][cod] = Math.min(dp[alg+1][cod], dp[alg][cod] + 1);
                    if (cod+1 <= 150)  dp[alg][cod+1] = Math.min(dp[alg][cod+1], dp[alg][cod] + 1);
                    if (alg >= problems[p][0] && cod >= problems[p][1]) {
                        if(alg + problems[p][2] <= 150 && cod + problems[p][3] <= 150) {
                            dp[alg+problems[p][2]][cod+problems[p][3]] = 
                            Math.min(dp[alg][cod] + problems[p][4], 
                                     dp[alg+problems[p][2]][cod+problems[p][3]]);
                        }
                    } 
                }
            }
        }

        return dp[goal_alg][goal_cod];
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		solution()
	}

}
