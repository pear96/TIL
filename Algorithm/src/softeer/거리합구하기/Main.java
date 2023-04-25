package softeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[][] adj = new int[N][N];
        
        // 초기값 설정
        for(int i = 0; i < N; i++) {
            Arrays.fill(adj[i], 1000_001);
            adj[i][i] = 0;
        }

        // 입력 처리
        for(int j = 0; j < N-1; j++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            adj[x-1][y-1] = t;
            adj[y-1][x-1] = t;
        }


        // 플로이드 워샬
        for(int start = 0; start < N; start++) {
            for(int end = 0; end < N; end++) {
            	for(int mid = 0; mid < N; mid++) {
                    if (start == mid || end == mid || start == end) continue;
//                    System.out.println("start :" + start + " end : " + end + " mid : " + mid);
//                    System.out.println("s-e : " + adj[start][end] + ", s-m :" + adj[start][mid] + ", m-e : " + adj[mid][end]);
                    if (adj[start][end] > adj[start][mid] + adj[mid][end]) {
                        adj[start][end] = adj[start][mid] + adj[mid][end];
                        adj[end][start] = adj[start][mid] + adj[mid][end];
                    }
                }
            }
        }

//        for(int a = 0; a < N; a++) {
//            System.out.println(Arrays.toString(adj[a]));
//        }

         for(int k = 0; k < N; k++) {
             long sum = Arrays.stream(adj[k]).sum();
             sb.append(sum+"\n");
         }
         System.out.println(sb.toString());
    }
}