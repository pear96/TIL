package swea1768;
import java.util.Scanner;

class Solution {
    private final static int N = 4; // ?��?��?�� 4?���?
    private final static int MAX_QUERYCOUNT = 1000000;

    private static int digits[] = new int[N]; // 4?���? ?��?��
    private static int digits_c[] = new int[10]; // ball?�� -> 같�? ?��?���? ?���? ?���? ?��?��

    private static int T;  // ?��?��?�� �??��?��

    private static int querycount; // 쿼리 ?���? ?��?��

    // the value of limit_query will be changed in evaluation
    private final static int limit_query = 2520;

    static class Result {
        public int strike;
        public int ball;
    }

    private static boolean isValid(int guess[]) {
        // guess[]?�� 물어보는 ?��?��, guess_c?�� 물어보는 ?��?���? 중복?��?���? ?��?��?��
        int guess_c[] = new int[10];

        // guess_c�? ?���? 0?���? 맞춰?���?
        for (int count = 0; count < 10; ++count) guess_c[count] = 0;
        // ?��?��범위 ?��?�� ?��?���? ?��?��거나! ?���? ?���? ?��?��?�� ?��?��?���? valid ?���? ?��?��!
        for (int idx = 0; idx < N; ++idx) {
            if (guess[idx] < 0 || guess[idx] >= 10 || guess_c[guess[idx]] > 0) return false;
            guess_c[guess[idx]]++; // ?��?��?���? ?��?��?��?���? ?��기해줍니?��.
        }
        return true;
    }

    // API : return a result for comparison with digits[] and guess[]
    // ?��각하?�� ?��?? 물어보는 ?���? 비교?��?�� query ?��?��! ?���? ?��출할 ?���? ?��리하?��.
    public static Result query(int guess[]) {
        Result result = new Result();

        // ?��?�� ?���? ?��?���? ?��출하�? 못했?���?, ?��?��?��?��.
        if (querycount >= MAX_QUERYCOUNT) {
            result.strike = -1;
            result.ball = -1;
            return result;
        }
        // ?��?��?���? ?��출한 ?��?�� 추�??���?
        querycount++;
        // 물어보는 ?��?���? 말이 ?��?���??�� 보고, ?��?��?���? ?��?��?��?��?��,볼을 각각 -1 반환
        if (!isValid(guess)) {
            result.strike = -1;
            result.ball = -1;
            return result;
        }

        // ?��?��?��?���? ?��?�� 0�??�� ?��?��?��
        result.strike = 0;
        result.ball = 0;

        // 물어보는 ?��?�� 4개�?? 보면?��
        for (int idx = 0; idx < N; ++idx)
            if (guess[idx] == digits[idx])
                result.strike++;
            else if (digits_c[guess[idx]] > 0)
                // digits_c?�� ball?�� ?��?��?���? ?��?�� Array. ?��?�� ?��?���??�� 보는�?.
                result.ball++;

        return result;
    }
    // ?��?�� 받는 ?��?��
    private static void initialize(Scanner sc) {
        // ?��?�� ?��?�� ?��?�� ?��?�� 0?���? 초기?�� ?��주고
        for (int count = 0; count < 10; ++count) digits_c[count] = 0;
        // ?�� ?��?��구로 짜르?��거야?
        String input;
        do input = sc.next(); while(input.charAt(0) < '0' || input.charAt(0) > '9');

        for (int idx = 0; idx < N; ++idx) {
            digits[idx] = input.charAt(idx) - '0'; // '0'?? ?�� 빼는 거임??��?��
            digits_c[digits[idx]]++;
        }

        querycount = 0;
    }

    // ?��?��?���? ?��?�� ?��?�� ?��?��
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
            // ???��?��! 쿼리 ?���? ?��?�� 만땅?��.
            // ?�� ??�? 경우�? ?��?���? ?��?��?��?��?? ?�� 만땅?�� ?��?��거�???
            if (!check(guess)) querycount = MAX_QUERYCOUNT;
            // 맞췄?���? ?��?�� 추�?
            if (querycount <= limit_query) total_score++;
            System.out.printf("#%d %d\n", testcase, querycount);
            // total query ?��?��?�� �?�? query ?��?�� 추�?
            total_querycount += querycount;
        }
        if (total_querycount > MAX_QUERYCOUNT) total_querycount = MAX_QUERYCOUNT;
        System.out.printf("total score = %d\ntotal query = %d\n", total_score * 100 / T, total_querycount);
        sc.close();
    }
}