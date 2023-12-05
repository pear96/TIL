package 사이트.softeer.lv2.GBC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] rule = new int[100];
        int[] record = new int[100];
        int answer = 0;

        int height = 0;
        int range, limit, speed;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            range = Integer.parseInt(st.nextToken());
            limit = Integer.parseInt(st.nextToken());

            for(int h = 0; h < range; h++) {
                rule[h+height] = limit;
            }
            height += range;
        }

        height = 0;

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            range = Integer.parseInt(st.nextToken());
            speed = Integer.parseInt(st.nextToken());

            for(int j = 0; j < range; j++) {
                if (rule[j+height] < speed) {
                    answer = Math.max(answer, speed - rule[j+height]);
                }
            }
            height += range;
        }

        System.out.println(answer);
    }
}