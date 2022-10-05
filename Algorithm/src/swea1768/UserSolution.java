package swea1768;

import java.util.Arrays;

class UserSolution {
    public final static int N = 4;
    int dataCnt; // �??��?�� ?��?�� 개수
    boolean[] checked; // 중복 ?��?��?�� 배열
    int[][] failed; // ?��?��?�� ?��?��?��(dataCnt 만큼)
    int[] number; // ?��각하?�� ?��?��


    // ?��?��?�� query?�� 비슷?��.
    // query�? ?�� ?��리는 ???�� ?���??��?�� ?���? �?증한?��.
    // 10�? ?��?�� 무조�? ?��?��?�� ?��?��?�� 4?��리마?�� ?��?���? ?��?��보며 �?증하?�� 방법?�� 10�?�??���? ?���?.
    // 0123, 1234, 2345, 3456, 4567, 5678, 6789, 7890, 8901, 9012
    boolean isPossible() {
        System.out.println(Arrays.toString(number) + "�??��?��?�� ?��?��?��");
        for (int i = 0; i < dataCnt; i++) {
            int strike = 0, ball = 0;
            for (int j = 0; j <= 3; j++) {
                if (failed[i][j] == number[j]) strike++;
                else if (checked[failed[i][j]]) ball++;
            }
            System.out.println(Arrays.toString(failed[i]) + " " + Arrays.toString(number));
            System.out.println("?��?��?��?��?��:"+strike+" & �?:"+ball);
            System.out.println("?��?�� ?��?��?��?��?��:"+failed[i][4]+" & �?:"+failed[i][5]);
            if ((failed[i][4] != strike) || (failed[i][5] != ball)){
                System.out.println("�??��?��?�� ?��군요?��");
                // ?��?��?�� 기록?��?�� ?��?�� 기록?��과의 strike, ball 값이 ?��치해?���? 맞을 �??��?��?�� ?���?.
                // �??��?��만한 조건?�� �??���? ?��?�� 기록?��?�� ?��?���? 바꿔보거?��, ?��리�?? 바꾼것이?��.
                return false;
            }
        }
        System.out.println("�??��?��?�� ?��?��?��?��!");
        return true;
    }

    // 중복?��?�� ?��?�� ?��?�� 만들�?
    boolean DFS(int idx) {
        if (idx >= 4) {
            // 4개짜�? 만들?��?��?�� ?��?�� ?��?��?��?�� 걸리�? -> �?망도 ?��?��!
            System.out.println(Arrays.toString(number));
            if (!isPossible()){
                System.out.println("?��?��!!");
                return false;
            }
            System.out.println("쿼리�? ?��립니?��~");
            // �?망이 ?��?���?! -> 쿼리�? ?��?��본다.
            Solution.Result result = Solution.query(number);
            System.out.println("결과" + result.strike + " " + result.ball);
            // ?��?��?��?��?���? 4�?, ?��?��?�� 찾�?것이�?, ?��?��?���?, failed?�� 추�??
            if(result.strike == 4) {
                System.out.println("?��?��!!");
                return true;
            }
            // ?��?��?? ?��?���?�? �??��?��?�� ?��?�� ?��?��?!
            System.out.println("?��?��?? ?��?���?�?, 기록?? ?��?��겠습?��?��.");
            System.arraycopy(number, 0, failed[dataCnt], 0, 4);
            failed[dataCnt][4] = result.strike;
            failed[dataCnt][5] = result.ball;
            dataCnt++;
            return false;
        }
        // ?���? 4개짜�? �? 만들?��?��?��!
        for(int i = 0; i <= 9; i++) {
            if (checked[i]) continue; // ?��?�� ?��?�� 중복 �??��
            number[idx] = i;
            checked[i] = true;
            if (DFS(idx+1))
                // ?��?��?�� 맞춘 경우?
                return true;
            checked[i] = false; // 중복?��?��
        }
        return false; // ?���? ?��?��?��~
    }


    public void doUserImplementation(int guess[]) {
        // Implement a user's implementation function
        //
        // The array of guess[] is a return array that
        // is your guess for what digits[] would be.
        // ?��?�� ?��구�?? 만들?���?? ?���? ?��?���? 맞추?�� ?��고리즘을 짜보?���???
        number = guess; // ?��?��?�� �??���? ?��?��?���? ?��?��?��
        dataCnt = 0;
        checked = new boolean[10];
        failed = new int[10][6];
        DFS(0);
    }
}