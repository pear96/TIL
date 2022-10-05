package swea1768;

import java.util.Arrays;

class UserSolution {
    public final static int N = 4;
    int dataCnt; // κ²??¬? ?«? κ°μ
    boolean[] checked; // μ€λ³΅ ??Έ?© λ°°μ΄
    int[][] failed; // ?€?¨? ?«??€(dataCnt λ§νΌ)
    int[] number; // ?κ°ν? ?«?


    // ?¬?€? query? λΉμ·?¨.
    // queryλ₯? ? ? λ¦¬λ ???  ?΄λΆ??? ?μ²? κ²?μ¦ν?€.
    // 10λ²? ?? λ¬΄μ‘°κ±? ??€? ?΄? ? 4?λ¦¬λ§?€ ?«?λ₯? ?£?΄λ³΄λ©° κ²?μ¦ν? λ°©λ²?΄ 10κ°?μ§??΄κΈ? ?λ¬?.
    // 0123, 1234, 2345, 3456, 4567, 5678, 6789, 7890, 8901, 9012
    boolean isPossible() {
        System.out.println(Arrays.toString(number) + "κ²??¬??¬ ???");
        for (int i = 0; i < dataCnt; i++) {
            int strike = 0, ball = 0;
            for (int j = 0; j <= 3; j++) {
                if (failed[i][j] == number[j]) strike++;
                else if (checked[failed[i][j]]) ball++;
            }
            System.out.println(Arrays.toString(failed[i]) + " " + Arrays.toString(number));
            System.out.println("?€?Έ?Ό?΄?¬:"+strike+" & λ³?:"+ball);
            System.out.println("?€?¨ ?€?Έ?Ό?΄?¬:"+failed[i][4]+" & λ³?:"+failed[i][5]);
            if ((failed[i][4] != strike) || (failed[i][5] != ball)){
                System.out.println("κ°??₯?±?΄ ?κ΅°μ? ");
                // ?΄? ? κΈ°λ‘?΄? ?€?¨ κΈ°λ‘?€κ³Όμ strike, ball κ°μ΄ ?ΌμΉν΄?Όλ§? λ§μ κ°??₯?±?΄ ?κΉ?.
                // κ°??₯? λ§ν μ‘°κ±΄? μ§??€λ©? ?΄?  κΈ°λ‘?? ?«?λ₯? λ°κΏλ³΄κ±°?, ?λ¦¬λ?? λ°κΎΌκ²μ΄?€.
                return false;
            }
        }
        System.out.println("κ°??₯?±?΄ ??΅??€!");
        return true;
    }

    // μ€λ³΅?? ?«? ??΄ λ§λ€κΈ?
    boolean DFS(int idx) {
        if (idx >= 4) {
            // 4κ°μ§λ¦? λ§λ€???° ?€?¨ ?°?΄?°? κ±Έλ¦¬λ©? -> κ°?λ§λ ??€!
            System.out.println(Arrays.toString(number));
            if (!isPossible()){
                System.out.println("?€?¨!!");
                return false;
            }
            System.out.println("μΏΌλ¦¬λ₯? ? λ¦½λ?€~");
            // κ°?λ§μ΄ ??€λ©?! -> μΏΌλ¦¬λ₯? ? ? €λ³Έλ€.
            Solution.Result result = Solution.query(number);
            System.out.println("κ²°κ³Ό" + result.strike + " " + result.ball);
            // ?€?Έ?Ό?΄?¬κ°? 4λ©?, ? ?΅? μ°Ύμ?κ²μ΄κ³?, ???Όλ©?, failed? μΆκ??
            if(result.strike == 4) {
                System.out.println("? ?΅!!");
                return true;
            }
            // ? ?΅?? ??μ§?λ§? κ°??₯?±?΄ ?? ?«??!
            System.out.println("? ?΅?? ??μ§?λ§?, κΈ°λ‘?? ?΄?κ² μ΅??€.");
            System.arraycopy(number, 0, failed[dataCnt], 0, 4);
            failed[dataCnt][4] = result.strike;
            failed[dataCnt][5] = result.ball;
            dataCnt++;
            return false;
        }
        // ?μ§? 4κ°μ§λ¦? λͺ? λ§λ€??΄?!
        for(int i = 0; i <= 9; i++) {
            if (checked[i]) continue; // ?«? ?¬?© μ€λ³΅ κ²??¬
            number[idx] = i;
            checked[i] = true;
            if (DFS(idx+1))
                // ? ?΅? λ§μΆ κ²½μ°?
                return true;
            checked[i] = false; // μ€λ³΅?΄? 
        }
        return false; // ?μ§? ???€~
    }


    public void doUserImplementation(int guess[]) {
        // Implement a user's implementation function
        //
        // The array of guess[] is a return array that
        // is your guess for what digits[] would be.
        // ?«? ?Όκ΅¬λ?? λ§λ€?Όκ³?? ?΄κ°? ?«?λ₯? λ§μΆ? ?κ³ λ¦¬μ¦μ μ§λ³΄?Όκ³???
        number = guess; // ?΄??€ λ³??λ‘? ?¬?©?κΈ? ??΄?
        dataCnt = 0;
        checked = new boolean[10];
        failed = new int[10][6];
        DFS(0);
    }
}