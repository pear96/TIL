package 그래프;

/* 참고 : https://ilmiodiario.tistory.com/97
* 3차원 배열 만드는 것 까진 해냈는데 끝발(?)이 아쉬웠다.
* */
class PG_방문길이 {
	// 상 좌 하 우
	int[] dr = {-1, 0, 1, 0};
	int[] dc = {0, -1, 0, 1};

	public int solution(String dirs) {
		int answer = 0;
		// 방문 배열. 3번째는 어느 방향으로 이 위치에 진입했는가를 표기한다.
		// 좌우, 상하 는 같은 길을 사용한 것이다.
		boolean[][][] visited = new boolean[11][11][4];
		// -5 ~ 5 => 0 ~ 10 로 범위 바꿈
		// 0, 0 => 5,5로 옮김
		int row = 5, col = 5;
		int dir = 0;

		for (char d : dirs.toCharArray()) {
			// 상하 / 좌우 를 같이 취급한다.
			if (d == 'U') dir = 0;
			if (d == 'D') dir = 2;
			if (d == 'L') dir = 1;
			if (d == 'R') dir = 3;

			int nextRow = row + dr[dir], nextCol = col + dc[dir];
			// 좌표 평면을 넘어간다면 무시
			if (0 <= nextRow && nextRow <= 10 && 0 <= nextCol && nextCol <= 10) {
				// 처음 방문한 곳
				if (!visited[nextRow][nextCol][dir]) {
					answer++;
					int o_dir = (dir % 2 == 0)? 2 - dir : 4 - dir;
					visited[row][col][o_dir] = true;
					visited[nextRow][nextCol][dir] = true;
					// System.out.println("위치 "+ nextRow + " " + nextCol +" 방향 : "+ d +" 길이 : " + answer);
				}
				row = nextRow;
				col = nextCol;
			}
		}
		return answer;
	}
}