package programmers.이모티콘할인행사;

import java.util.Arrays;

public class HE150368 {

	static int cntUser, cntEmoji;
    static int[][] user;
    static int[] emoji;
    static int[] discount;
    static int[] answer = {0, 0};
    static int[] percentages = {10, 20, 30, 40};
    
    
    public static void sale(int e) {

        if (e == cntEmoji) {
            // 최종결산
            int[] revenue = new int[cntUser];
            
            int emoticonPlus = 0, totalRevenue = 0;
            
            // 각 사용자별 모든 이모티콘에 대한 결제액 계산 O(M * N)
            for (int i = 0; i < cntUser; i++) {
                for(int j = 0; j < cntEmoji; j++) {
                    if (discount[j] >= user[i][0]) revenue[i] += (emoji[j] * (100 - discount[j])) / 100;
                }
            }
            
            // 계산된 결과를 바탕으로 이모티콘 플러스 할지 말지 본다.
            for (int i = 0; i < cntUser; i++) {
                if (revenue[i] >= user[i][1]) emoticonPlus += 1;
                else totalRevenue += revenue[i];
            }
            System.out.println("할인 " + Arrays.toString(discount));
            System.out.println("수익 " + Arrays.toString(revenue));
            System.out.println("이플 : " + emoticonPlus + " 수익 : " + totalRevenue);
            
            // 정답 업데이트
            if (emoticonPlus > answer[0] || (emoticonPlus == answer[0] && totalRevenue > answer[1])) {
                answer[0] = emoticonPlus;
                answer[1] = totalRevenue;
            }
            
            System.out.println("현재 정답 " + answer[0] + " & " + answer[1]);
            System.out.println();
            
        } else {
            // 각 이모티콘별 할인율 조합 만들기
            for (int percent : percentages) {
                discount[e] = percent;
                sale(e+1);
            }
        }
    }
    
    
    public static int[] solution(int[][] users, int[] emoticons) {
    	user = users;
        emoji = emoticons;
        
        cntUser = users.length;
        cntEmoji = emoticons.length;
        
        discount = new int[cntEmoji];
        sale(0);
        
        return answer;
    }
	
	public static void main(String[] args) {
//		int[][] iU = {{40, 10000}, {25, 10000}};
		int[][] iU = {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, 
				{27, 9200}, {32, 6900}};
//		int[] iE = {7000, 9000};
		int[] iE = {1300, 1500, 1600, 4900};
		System.out.println(Arrays.toString(solution(iU, iE)));
//		int num = (int) (700 * (1 / 10.0));
//		System.out.println(num);
	}
}
