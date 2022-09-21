package SWEA1768;
import java.util.Scanner;

class Solution {
    private final static int N = 4; // 숫자는 4자리
    private final static int MAX_QUERYCOUNT = 1000000;

    private static int digits[] = new int[N]; // 4자리 숫자
    private static int digits_c[] = new int[10]; // ball용 -> 같은 숫자가 있긴 한지 확인

    private static int T;  // 테스트 케이스

    private static int querycount; // 쿼리 호출 횟수

    // the value of limit_query will be changed in evaluation
    private final static int limit_query = 2520;

    static class Result {
        public int strike;
        public int ball;
    }

    private static boolean isValid(int guess[]) {
        // guess[]는 물어보는 숫자, guess_c는 물어보는 숫자가 중복되는지 확인용
        int guess_c[] = new int[10];

        // guess_c를 전부 0으로 맞춰놓고
        for (int count = 0; count < 10; ++count) guess_c[count] = 0;
        // 정상범위 내의 숫자가 아니거나! 이미 한번 사용한 숫자라면 valid 하지 않다!
        for (int idx = 0; idx < N; ++idx) {
            if (guess[idx] < 0 || guess[idx] >= 10 || guess_c[guess[idx]] > 0) return false;
            guess_c[guess[idx]]++; // 아니라면 사용했다고 표기해줍니다.
        }
        return true;
    }

    // API : return a result for comparison with digits[] and guess[]
    // 생각하는 수와 물어보는 수를 비교하는 query 함수! 적게 호출할 수록 유리하다.
    public static Result query(int guess[]) {
        Result result = new Result();

        // 일정 수준 이하로 호출하지 못했으면, 땡입니다.
        if (querycount >= MAX_QUERYCOUNT) {
            result.strike = -1;
            result.ball = -1;
            return result;
        }
        // 아니라면 호출한 횟수 추가하고
        querycount++;
        // 물어보는 숫자가 말이 되는지는 보고, 아니라면 스트라이크,볼을 각각 -1 반환
        if (!isValid(guess)) {
            result.strike = -1;
            result.ball = -1;
            return result;
        }

        // 정상이라면 일단 0부터 시작해
        result.strike = 0;
        result.ball = 0;

        // 물어보는 숫자 4개를 보면서
        for (int idx = 0; idx < N; ++idx)
            if (guess[idx] == digits[idx])
                result.strike++;
            else if (digits_c[guess[idx]] > 0)
                // digits_c는 ball을 확인하기 위한 Array. 등장 했는지나 보는것.
                result.ball++;

        return result;
    }
    // 입력 받는 함수
    private static void initialize(Scanner sc) {
        // 일단 숫자 등장 횟수 0으로 초기화 해주고
        for (int count = 0; count < 10; ++count) digits_c[count] = 0;
        // 왜 이따구로 짜르는거야?
        String input;
        do input = sc.next(); while(input.charAt(0) < '0' || input.charAt(0) > '9');

        for (int idx = 0; idx < N; ++idx) {
            digits[idx] = input.charAt(idx) - '0'; // '0'은 왜 빼는 거임?ㅠㅠ
            digits_c[digits[idx]]++;
        }

        querycount = 0;
    }

    // 정답인지 확인 하는 함수
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
            // 틀렸다! 쿼리 호출 횟수 만땅됨.
            // 이 틀린 경우가 이해가 안되는데?? 왜 만땅이 되는거지??
            if (!check(guess)) querycount = MAX_QUERYCOUNT;
            // 맞췄으면 점수 추가
            if (querycount <= limit_query) total_score++;
            System.out.printf("#%d %d\n", testcase, querycount);
            // total query 횟수에 지금 query 횟수 추가
            total_querycount += querycount;
        }
        if (total_querycount > MAX_QUERYCOUNT) total_querycount = MAX_QUERYCOUNT;
        System.out.printf("total score = %d\ntotal query = %d\n", total_score * 100 / T, total_querycount);
        sc.close();
    }
}