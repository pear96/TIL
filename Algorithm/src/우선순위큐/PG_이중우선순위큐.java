package 우선순위큐;

import java.util.Collections;
import java.util.PriorityQueue;

class PG_이중우선순위큐 {
	public int[] solution(String[] operations) {
        int[] answer = new int[2];
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
        
        for(String operation : operations) {
            String[] op = operation.split(" ");
            if (op[0].equals("I")) {
                // 숫자 삽입
                int num = Integer.parseInt(op[1]);
                maxQ.add(num);
            } else {
                if (op[1].equals("1")) {
                    // 최대값 삭제
                    while(!minQ.isEmpty()) maxQ.add(minQ.poll());
                    maxQ.poll();
                } else if (op[1].equals("-1")) {
                    // 최소값 삭제
                    while(!maxQ.isEmpty()) minQ.add(maxQ.poll());
                    minQ.poll();
                }
            }
        }
        // 최대값 조회
        if (!maxQ.isEmpty() || !minQ.isEmpty()) {
            while(!minQ.isEmpty()) maxQ.add(minQ.poll());
            answer[0] = maxQ.poll();
        }
        if (!minQ.isEmpty() || !maxQ.isEmpty()) {
            while(!maxQ.isEmpty()) minQ.add(maxQ.poll());
            answer[1] = minQ.poll();
        }
        return answer;
    }
}
