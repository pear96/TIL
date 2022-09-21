package SWEA1768;

import java.util.Arrays;

class UserSolution {
    public final static int N = 4;
    int dataCnt; // 검사한 숫자 개수
    boolean[] checked; // 중복 확인용 배열
    int[][] failed; // 실패한 숫자들(dataCnt 만큼)
    int[] number; // 생각하는 숫자


    // 사실상 query랑 비슷함.
    // query를 덜 날리는 대신 내부에서 엄청 검증한다.
    // 10번 안에 무조건 나오는 이유는 4자리마다 숫자를 넣어보며 검증하는 방법이 10가지이기 때문.
    // 0123, 1234, 2345, 3456, 4567, 5678, 6789, 7890, 8901, 9012
    boolean isPossible() {
        System.out.println(Arrays.toString(number) + "검사하러 왔엉요");
        for (int i = 0; i < dataCnt; i++) {
            int strike = 0, ball = 0;
            for (int j = 0; j <= 3; j++) {
                if (failed[i][j] == number[j]) strike++;
                else if (checked[failed[i][j]]) ball++;
            }
            System.out.println(Arrays.toString(failed[i]) + " " + Arrays.toString(number));
            System.out.println("스트라이크:"+strike+" & 볼:"+ball);
            System.out.println("실패 스트라이크:"+failed[i][4]+" & 볼:"+failed[i][5]);
            if ((failed[i][4] != strike) || (failed[i][5] != ball)){
                System.out.println("가능성이 없군요ㅠ");
                // 이전에 기록해둔 실패 기록들과의 strike, ball 값이 일치해야만 맞을 가능성이 생김.
                // 가능할만한 조건을 지키며 이전 기록에서 숫자를 바꿔보거나, 자리를 바꾼것이다.
                return false;
            }
        }
        System.out.println("가능성이 있습니다!");
        return true;
    }

    // 중복없는 숫자 순열 만들기
    boolean DFS(int idx) {
        if (idx >= 4) {
            // 4개짜리 만들었는데 실패 데이터에 걸리면 -> 가망도 없다!
            System.out.println(Arrays.toString(number));
            if (!isPossible()){
                System.out.println("실패!!");
                return false;
            }
            System.out.println("쿼리를 날립니다~");
            // 가망이 있다면! -> 쿼리를 날려본다.
            Solution.Result result = Solution.query(number);
            System.out.println("결과" + result.strike + " " + result.ball);
            // 스트라이크가 4면, 정답을 찾은것이고, 아니라면, failed에 추가?
            if(result.strike == 4) {
                System.out.println("정답!!");
                return true;
            }
            // 정답은 아니지만 가능성이 있는 숫자?!
            System.out.println("정답은 아니지만, 기록은 해두겠습니다.");
            System.arraycopy(number, 0, failed[dataCnt], 0, 4);
            failed[dataCnt][4] = result.strike;
            failed[dataCnt][5] = result.ball;
            dataCnt++;
            return false;
        }
        // 아직 4개짜리 못 만들었어요!
        for(int i = 0; i <= 9; i++) {
            if (checked[i]) continue; // 숫자 사용 중복 검사
            number[idx] = i;
            checked[i] = true;
            if (DFS(idx+1))
                // 정답을 맞춘 경우?
                return true;
            checked[i] = false; // 중복해제
        }
        return false; // 아직 아니다~
    }


    public void doUserImplementation(int guess[]) {
        // Implement a user's implementation function
        //
        // The array of guess[] is a return array that
        // is your guess for what digits[] would be.
        // 숫자 야구를 만들라고? 내가 숫자를 맞추는 알고리즘을 짜보라고??
        number = guess; // 클래스 변수로 사용하기 위해서
        dataCnt = 0;
        checked = new boolean[10];
        failed = new int[10][6];
        DFS(0);
    }
}