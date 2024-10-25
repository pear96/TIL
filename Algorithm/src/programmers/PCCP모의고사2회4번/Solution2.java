package programmers.PCCP모의고사2회4번;

import java.util.ArrayDeque;

public class Solution2 {
    public static int solution(int n, int m, int[][] holes) {
        int[] dr = {-1, 1, 0, 0}; // 상, 하, 좌, 우 방향
        int[] dc = {0, 0, -1, 1};
        int answer = -1;

        boolean[][][] visited = new boolean[n+1][m+1][2];

        // 함정 위치를 표시한 맵
        boolean[][] holeMap = getHoleMap(n, m, holes);

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{1, 1, 0, 0});  // {row, col, 부츠 사용 여부, 시간}

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0], col = curr[1], bootUsed = curr[2], time = curr[3];
            if (row == n && col == m) {
                return time;
            }

            // 네 방향에 대해 탐색
            for (int d = 0; d < 4; d++) {
                int nRow = row + dr[d];
                int nCol = col + dc[d];

                // 부츠를 사용하지 않고 한 칸 이동
                if (isInBounds(nRow, nCol, n, m) && !holeMap[nRow][nCol] && !visited[nRow][nCol][bootUsed]) {
                    visited[nRow][nCol][bootUsed] = true;
                    queue.add(new int[]{nRow, nCol, bootUsed, time+1});
                }

                // 부츠를 사용하여 두 칸 이동 (아직 부츠를 사용하지 않은 경우에만)
                if (bootUsed == 0) {
                    int jumpRow = row + dr[d] * 2;
                    int jumpCol = col + dc[d] * 2;
                    if (isInBounds(jumpRow, jumpCol, n, m) && !holeMap[jumpRow][jumpCol] && !visited[jumpRow][jumpCol][1]) {
                        visited[jumpRow][jumpCol][1] = true;
                        queue.add(new int[]{jumpRow, jumpCol, 1, time+1});
                    }
                }
            }
        }
        return answer;
    }

    private static boolean isInBounds(int row, int col, int n, int m) {
        return row >= 1 && row <= n && col >= 1 && col <= m;
    }

    private static boolean[][] getHoleMap(int n, int m, int[][] holes) {
        boolean[][] holeMap = new boolean[n+1][m+1];
        for (int[] hole : holes) {
            holeMap[hole[0]][hole[1]] = true;
        }
        return holeMap;
    }
    public static void main(String[] args) {
        int[][] s = new int[][] {{2, 3}, {2, 3}};
        System.out.println(solution(4, 4, s));

//        int[][] s = new int[][] {{1, 4}, {2, 1}, {2, 2}, {2, 3}, {2, 4}, {3, 3}, {4, 1}, {4, 3}, {5, 3}};
//        System.out.println(solution(5, 4, s));
    }
}
