package swea1768;
import java.util.Scanner;

class Solution {
    private final static int N = 4; // ?ˆ«??Š” 4?ë¦?
    private final static int MAX_QUERYCOUNT = 1000000;

    private static int digits[] = new int[N]; // 4?ë¦? ?ˆ«?
    private static int digits_c[] = new int[10]; // ball?š© -> ê°™ì? ?ˆ«?ê°? ?ˆê¸? ?•œì§? ?™•?¸

    private static int T;  // ?…Œ?Š¤?Š¸ ì¼??´?Š¤

    private static int querycount; // ì¿¼ë¦¬ ?˜¸ì¶? ?šŸ?ˆ˜

    // the value of limit_query will be changed in evaluation
    private final static int limit_query = 2520;

    static class Result {
        public int strike;
        public int ball;
    }

    private static boolean isValid(int guess[]) {
        // guess[]?Š” ë¬¼ì–´ë³´ëŠ” ?ˆ«?, guess_c?Š” ë¬¼ì–´ë³´ëŠ” ?ˆ«?ê°? ì¤‘ë³µ?˜?Š”ì§? ?™•?¸?š©
        int guess_c[] = new int[10];

        // guess_cë¥? ? „ë¶? 0?œ¼ë¡? ë§ì¶°?†“ê³?
        for (int count = 0; count < 10; ++count) guess_c[count] = 0;
        // ? •?ƒë²”ìœ„ ?‚´?˜ ?ˆ«?ê°? ?•„?‹ˆê±°ë‚˜! ?´ë¯? ?•œë²? ?‚¬?š©?•œ ?ˆ«??¼ë©? valid ?•˜ì§? ?•Š?‹¤!
        for (int idx = 0; idx < N; ++idx) {
            if (guess[idx] < 0 || guess[idx] >= 10 || guess_c[guess[idx]] > 0) return false;
            guess_c[guess[idx]]++; // ?•„?‹ˆ?¼ë©? ?‚¬?š©?–ˆ?‹¤ê³? ?‘œê¸°í•´ì¤ë‹ˆ?‹¤.
        }
        return true;
    }

    // API : return a result for comparison with digits[] and guess[]
    // ?ƒê°í•˜?Š” ?ˆ˜?? ë¬¼ì–´ë³´ëŠ” ?ˆ˜ë¥? ë¹„êµ?•˜?Š” query ?•¨?ˆ˜! ? ê²? ?˜¸ì¶œí•  ?ˆ˜ë¡? ?œ ë¦¬í•˜?‹¤.
    public static Result query(int guess[]) {
        Result result = new Result();

        // ?¼? • ?ˆ˜ì¤? ?´?•˜ë¡? ?˜¸ì¶œí•˜ì§? ëª»í–ˆ?œ¼ë©?, ?•¡?…?‹ˆ?‹¤.
        if (querycount >= MAX_QUERYCOUNT) {
            result.strike = -1;
            result.ball = -1;
            return result;
        }
        // ?•„?‹ˆ?¼ë©? ?˜¸ì¶œí•œ ?šŸ?ˆ˜ ì¶”ê??•˜ê³?
        querycount++;
        // ë¬¼ì–´ë³´ëŠ” ?ˆ«?ê°? ë§ì´ ?˜?Š”ì§??Š” ë³´ê³ , ?•„?‹ˆ?¼ë©? ?Š¤?Š¸?¼?´?¬,ë³¼ì„ ê°ê° -1 ë°˜í™˜
        if (!isValid(guess)) {
            result.strike = -1;
            result.ball = -1;
            return result;
        }

        // ? •?ƒ?´?¼ë©? ?¼?‹¨ 0ë¶??„° ?‹œ?‘?•´
        result.strike = 0;
        result.ball = 0;

        // ë¬¼ì–´ë³´ëŠ” ?ˆ«? 4ê°œë?? ë³´ë©´?„œ
        for (int idx = 0; idx < N; ++idx)
            if (guess[idx] == digits[idx])
                result.strike++;
            else if (digits_c[guess[idx]] > 0)
                // digits_c?Š” ball?„ ?™•?¸?•˜ê¸? ?œ„?•œ Array. ?“±?¥ ?–ˆ?Š”ì§??‚˜ ë³´ëŠ”ê²?.
                result.ball++;

        return result;
    }
    // ?…? ¥ ë°›ëŠ” ?•¨?ˆ˜
    private static void initialize(Scanner sc) {
        // ?¼?‹¨ ?ˆ«? ?“±?¥ ?šŸ?ˆ˜ 0?œ¼ë¡? ì´ˆê¸°?™” ?•´ì£¼ê³ 
        for (int count = 0; count < 10; ++count) digits_c[count] = 0;
        // ?™œ ?´?”°êµ¬ë¡œ ì§œë¥´?Š”ê±°ì•¼?
        String input;
        do input = sc.next(); while(input.charAt(0) < '0' || input.charAt(0) > '9');

        for (int idx = 0; idx < N; ++idx) {
            digits[idx] = input.charAt(idx) - '0'; // '0'?? ?™œ ë¹¼ëŠ” ê±°ì„??… ?… 
            digits_c[digits[idx]]++;
        }

        querycount = 0;
    }

    // ? •?‹µ?¸ì§? ?™•?¸ ?•˜?Š” ?•¨?ˆ˜
    private static boolean check(int guess[]) {
        for (int idx = 0; idx < N; ++idx)
            if (guess[idx] != digits[idx]) return false;
        return true;
    }

    public static void main(String[] args) throws Exception
    {

        int total_score = 0;
        int total_querycount = 0;

        System.setIn(new java.io.FileInputStream("sample_input.txt"));

        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        UserSolution usersolution = new UserSolution();
        for (int testcase = 1; testcase <= T; ++testcase) {
            initialize(sc);

            int guess[] = new int[N];
            usersolution.doUserImplementation(guess);
            // ??? ¸?‹¤! ì¿¼ë¦¬ ?˜¸ì¶? ?šŸ?ˆ˜ ë§Œë•…?¨.
            // ?´ ??ë¦? ê²½ìš°ê°? ?´?•´ê°? ?•ˆ?˜?Š”?°?? ?™œ ë§Œë•…?´ ?˜?Š”ê±°ì???
            if (!check(guess)) querycount = MAX_QUERYCOUNT;
            // ë§ì·„?œ¼ë©? ? ?ˆ˜ ì¶”ê?
            if (querycount <= limit_query) total_score++;
            System.out.printf("#%d %d\n", testcase, querycount);
            // total query ?šŸ?ˆ˜?— ì§?ê¸? query ?šŸ?ˆ˜ ì¶”ê?
            total_querycount += querycount;
        }
        if (total_querycount > MAX_QUERYCOUNT) total_querycount = MAX_QUERYCOUNT;
        System.out.printf("total score = %d\ntotal query = %d\n", total_score * 100 / T, total_querycount);
        sc.close();
    }
}