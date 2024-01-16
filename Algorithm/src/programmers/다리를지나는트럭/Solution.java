package programmers.다리를지나는트럭;

import java.util.ArrayDeque;

public class Solution {
	static class Truck {
        int weight;
        int enterTime;
        
        public Truck(int weight, int enterTime) {
            this.weight = weight;
            this.enterTime = enterTime;
        }
    }
	
    public static int solution(int bridgeLength, int weightLimit, int[] truckWeights) {
        int now = 0;
        int idx = 0;
        int bridgeWeight = 0;
        ArrayDeque<Truck> bridge = new ArrayDeque<>();

		while (idx != truckWeights.length || !bridge.isEmpty()) {
			// 기존 트럭이 내려야 하는지
			if (!bridge.isEmpty() && bridge.getFirst().enterTime + bridgeLength == now) {
				Truck out = bridge.poll();
				bridgeWeight -= out.weight;
			}
			// 새로운 트럭이 다리에 올라갈 수 있는지 확인
			if (idx < truckWeights.length && bridgeWeight + truckWeights[idx] <= weightLimit && bridge.size() < bridgeLength) {
				bridgeWeight += truckWeights[idx];
				bridge.add(new Truck(truckWeights[idx], now));
				idx++;
			}
			now++;
		}
        
        return now;
    }

	public static void main(String[] args) {
		int[] trucks = new int[] {7, 4, 5, 6};
		System.out.println(solution(2, 10, trucks));
	}

}
