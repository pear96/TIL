package programmers.PCCP모의고사2회3번;

import java.util.ArrayDeque;

public class Solution {
    public static int solution(int[] menu, int[] order, int k) {
        int answer = 0;
        int orderCount = order.length;
        int nowCustomers = 1;
        int nowTime = 0;
        int customerIdx = 0;
        ArrayDeque<Integer> waitingQueue = new ArrayDeque<>();
        int menuMakingTime = menu[order[customerIdx]];

        while (!waitingQueue.isEmpty() || customerIdx < orderCount) {
            if (nowTime > 0 && menuMakingTime > 0) {
                menuMakingTime--;
            }
            // 메뉴 제조 및 손님 퇴장
            if (menuMakingTime == 0) {
                if (nowTime > 0 && nowCustomers > 0) nowCustomers--;
                if (!waitingQueue.isEmpty()) menuMakingTime = waitingQueue.poll();
            }

            // 2. 손님 입장
            if (nowTime > 0 && nowTime % k == 0 && ++customerIdx < orderCount) {
                nowCustomers++;
                waitingQueue.add(menu[order[customerIdx]]);
                if (menuMakingTime == 0) menuMakingTime = waitingQueue.poll();
            }


            // 3. 시간 흐름
            answer = Math.max(answer, nowCustomers);
            System.out.println(String.format("현재 시각 : %d초. 사람수 : %d 명. 최대 인원 수 : %d", nowTime, nowCustomers, answer));
            nowTime++;
        }

        return answer;
    }

    public static void main(String[] args) {
//        int[] menu = new int[] {5,6,7,11};
//        int[] order = new int[] {1, 2, 3, 3,2,1,1};
//        int k = 10;

//        int[] menu = new int[] {5, 12, 30};
//        int[] order = new int[] {2, 1, 0, 0, 0, 1, 0};
//        int k = 10;


//        int[] menu = new int[] {5, 12, 30};
//        int[] order = new int[] {1, 2, 0, 1};
//        int k = 10;

        int[] menu = new int[] {5};
        int[] order = new int[] {0,0,0,0,0};
        int k = 5;
        System.out.println(solution(menu, order, k)); // 1
    }
}
