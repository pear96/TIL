package 수학;

class PG_최고의집합 {
	public int[] solution(int n, int s) {
        int[] answer = new int[n];
        int avg = s / n;
        if (avg == 0) return new int[] {-1};
        for (int i = 0; i < n; i++) {
            answer[i] = avg;
        }
        // 수가 남을텐데, 그걸 마지막에 몰빵해줄게 아니라 걔도 고르게 나눠줘야함
        int left = s - (avg * n);
        for (int i = n-1; i > n-1-left; i--) {
            answer[i] += 1;
        }
        
        return answer;
    }
}
