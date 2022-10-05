package swea1768;

import java.util.Arrays;

class UserSolution {
    public final static int N = 4;
    int dataCnt; // ê²??‚¬?•œ ?ˆ«? ê°œìˆ˜
    boolean[] checked; // ì¤‘ë³µ ?™•?¸?š© ë°°ì—´
    int[][] failed; // ?‹¤?Œ¨?•œ ?ˆ«??“¤(dataCnt ë§Œí¼)
    int[] number; // ?ƒê°í•˜?Š” ?ˆ«?


    // ?‚¬?‹¤?ƒ query?‘ ë¹„ìŠ·?•¨.
    // queryë¥? ?œ ?‚ ë¦¬ëŠ” ???‹  ?‚´ë¶??—?„œ ?—„ì²? ê²?ì¦í•œ?‹¤.
    // 10ë²? ?•ˆ?— ë¬´ì¡°ê±? ?‚˜?˜¤?Š” ?´?œ ?Š” 4?ë¦¬ë§ˆ?‹¤ ?ˆ«?ë¥? ?„£?–´ë³´ë©° ê²?ì¦í•˜?Š” ë°©ë²•?´ 10ê°?ì§??´ê¸? ?•Œë¬?.
    // 0123, 1234, 2345, 3456, 4567, 5678, 6789, 7890, 8901, 9012
    boolean isPossible() {
        System.out.println(Arrays.toString(number) + "ê²??‚¬?•˜?Ÿ¬ ?™”?—‰?š”");
        for (int i = 0; i < dataCnt; i++) {
            int strike = 0, ball = 0;
            for (int j = 0; j <= 3; j++) {
                if (failed[i][j] == number[j]) strike++;
                else if (checked[failed[i][j]]) ball++;
            }
            System.out.println(Arrays.toString(failed[i]) + " " + Arrays.toString(number));
            System.out.println("?Š¤?Š¸?¼?´?¬:"+strike+" & ë³?:"+ball);
            System.out.println("?‹¤?Œ¨ ?Š¤?Š¸?¼?´?¬:"+failed[i][4]+" & ë³?:"+failed[i][5]);
            if ((failed[i][4] != strike) || (failed[i][5] != ball)){
                System.out.println("ê°??Š¥?„±?´ ?—†êµ°ìš”?… ");
                // ?´? „?— ê¸°ë¡?•´?‘” ?‹¤?Œ¨ ê¸°ë¡?“¤ê³¼ì˜ strike, ball ê°’ì´ ?¼ì¹˜í•´?•¼ë§? ë§ì„ ê°??Š¥?„±?´ ?ƒê¹?.
                // ê°??Š¥?• ë§Œí•œ ì¡°ê±´?„ ì§??‚¤ë©? ?´? „ ê¸°ë¡?—?„œ ?ˆ«?ë¥? ë°”ê¿”ë³´ê±°?‚˜, ?ë¦¬ë?? ë°”ê¾¼ê²ƒì´?‹¤.
                return false;
            }
        }
        System.out.println("ê°??Š¥?„±?´ ?ˆ?Šµ?‹ˆ?‹¤!");
        return true;
    }

    // ì¤‘ë³µ?—†?Š” ?ˆ«? ?ˆœ?—´ ë§Œë“¤ê¸?
    boolean DFS(int idx) {
        if (idx >= 4) {
            // 4ê°œì§œë¦? ë§Œë“¤?—ˆ?Š”?° ?‹¤?Œ¨ ?°?´?„°?— ê±¸ë¦¬ë©? -> ê°?ë§ë„ ?—†?‹¤!
            System.out.println(Arrays.toString(number));
            if (!isPossible()){
                System.out.println("?‹¤?Œ¨!!");
                return false;
            }
            System.out.println("ì¿¼ë¦¬ë¥? ?‚ ë¦½ë‹ˆ?‹¤~");
            // ê°?ë§ì´ ?ˆ?‹¤ë©?! -> ì¿¼ë¦¬ë¥? ?‚ ? ¤ë³¸ë‹¤.
            Solution.Result result = Solution.query(number);
            System.out.println("ê²°ê³¼" + result.strike + " " + result.ball);
            // ?Š¤?Š¸?¼?´?¬ê°? 4ë©?, ? •?‹µ?„ ì°¾ì?ê²ƒì´ê³?, ?•„?‹ˆ?¼ë©?, failed?— ì¶”ê??
            if(result.strike == 4) {
                System.out.println("? •?‹µ!!");
                return true;
            }
            // ? •?‹µ?? ?•„?‹ˆì§?ë§? ê°??Š¥?„±?´ ?ˆ?Š” ?ˆ«??!
            System.out.println("? •?‹µ?? ?•„?‹ˆì§?ë§?, ê¸°ë¡?? ?•´?‘ê² ìŠµ?‹ˆ?‹¤.");
            System.arraycopy(number, 0, failed[dataCnt], 0, 4);
            failed[dataCnt][4] = result.strike;
            failed[dataCnt][5] = result.ball;
            dataCnt++;
            return false;
        }
        // ?•„ì§? 4ê°œì§œë¦? ëª? ë§Œë“¤?—ˆ?–´?š”!
        for(int i = 0; i <= 9; i++) {
            if (checked[i]) continue; // ?ˆ«? ?‚¬?š© ì¤‘ë³µ ê²??‚¬
            number[idx] = i;
            checked[i] = true;
            if (DFS(idx+1))
                // ? •?‹µ?„ ë§ì¶˜ ê²½ìš°?
                return true;
            checked[i] = false; // ì¤‘ë³µ?•´? œ
        }
        return false; // ?•„ì§? ?•„?‹ˆ?‹¤~
    }


    public void doUserImplementation(int guess[]) {
        // Implement a user's implementation function
        //
        // The array of guess[] is a return array that
        // is your guess for what digits[] would be.
        // ?ˆ«? ?•¼êµ¬ë?? ë§Œë“¤?¼ê³?? ?‚´ê°? ?ˆ«?ë¥? ë§ì¶”?Š” ?•Œê³ ë¦¬ì¦˜ì„ ì§œë³´?¼ê³???
        number = guess; // ?´?˜?Š¤ ë³??ˆ˜ë¡? ?‚¬?š©?•˜ê¸? ?œ„?•´?„œ
        dataCnt = 0;
        checked = new boolean[10];
        failed = new int[10][6];
        DFS(0);
    }
}