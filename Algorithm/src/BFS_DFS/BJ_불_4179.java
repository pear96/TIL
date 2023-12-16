package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BJ_불_4179 {
    static int R, C;
    static class Node {
        boolean isFire;
        int row;
        int col;

        public Node (boolean isFire, int row, int col) {
            this.isFire = isFire;
            this.row = row;
            this.col = col;
        }
    }

    static boolean inRange(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    public static void main(String[] args) throws IOException {
        // 불은 2개 이상일 수 있었다.......
        int answer = Integer.MAX_VALUE;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        ArrayDeque<Node> deque = new ArrayDeque<>();

        boolean[][] wall = new boolean[R][C];
        boolean[][] fire = new boolean[R][C];
        int[][] jihoon = new int[R][C];

        int jr = 0, jc = 0;
        int fr = 0, fc = 0;

        for (int r = 0; r < R; r++) {
            char[] input = br.readLine().toCharArray();
            for (int c = 0; c < C; c++) {
                switch (input[c]) {
                    case '#':
                        wall[r][c] = true;
                        break;
                    case 'J':
                        jr = r;
                        jc = c;
                        break;
                    case 'F':
                        fire[r][c] = true;
                        deque.add(new Node(true, r, c));
                        break;
                }
            }
        }

        jihoon[jr][jc] = 1;
        deque.add(new Node(false, jr, jc));

        while(!deque.isEmpty()) {
            Node now = deque.poll();
            if (now.isFire) {
                for (int d = 0; d < 4; d++) {
                    int nr = now.row + dr[d];
                    int nc = now.col + dc[d];
                    if (inRange(nr, nc) && !wall[nr][nc] && !fire[nr][nc]) {
                        fire[nr][nc] = true;
                        deque.add(new Node(true, nr, nc));
                    }
                }
            } else {
                for (int d = 0; d < 4; d++) {
                    int nr = now.row + dr[d];
                    int nc = now.col + dc[d];
                    if (!inRange(nr, nc)) {
                        answer = Math.min(answer, jihoon[now.row][now.col]+1);
                    }
                    else if (jihoon[nr][nc] == 0 && !wall[nr][nc] && !fire[nr][nc]) {
                        jihoon[nr][nc] = jihoon[now.row][now.col]+1;
                        deque.add(new Node(false, nr, nc));
                    }
                }
            }
        }
        System.out.println((answer != Integer.MAX_VALUE)? answer-1: "IMPOSSIBLE");
    }
}
