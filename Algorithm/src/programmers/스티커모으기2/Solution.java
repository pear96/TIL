package programmers.스티커모으기2;

/*
 * https://blog.naver.com/PostView.naver?blogId=tlstjd436&logNo=223130147096&categoryNo=43&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=1&from=search
 * */
class Solution {
    public int solution(int sticker[]) {
        int total = sticker.length;
        int[][] dp = new int[2][total]; // 첫 번째 스티커를 뜯는 여부에 따라 나뉜다.
        
        // 첫 번째 스티커를 뜯었다면, 두 번째 스티커는 뜯을 수 없다.
        dp[0][0] = sticker[0];
        if (total > 1) {
            // 두 번째는 무조건 첫 번째를 뜯은게 최대값임
            dp[0][1] = dp[0][0];
            // 첫 번째 스티커를 뜯지 않았다면, 두 번째 스티커는 뜯은게 최대값이다.
            dp[1][1] = sticker[1];
        }
        // 첫 번째 뜯었으면 마지막은 쓸 수 없음
        for (int i = 2; i < total-1; i++) {
            // i번째를 뜯거나, 뜯지 않거나
            dp[0][i] = Math.max(dp[0][i-2]+sticker[i], dp[0][i-1]);
        }
        if (total > 1) dp[0][total-1] = dp[0][total-2];
        for (int i = 2; i < total; i++) {
            // i번째를 뜯거나, 뜯지 않거나
            dp[1][i] = Math.max(dp[1][i-2]+sticker[i], dp[1][i-1]);
        }
        
        return Math.max(dp[0][total-1], dp[1][total-1]);
    }
}