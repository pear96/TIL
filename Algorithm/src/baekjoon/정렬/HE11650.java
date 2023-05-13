package baekjoon.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


class Pos implements Comparable<Pos>{
	int x;
	int y;
	
	public Pos(int _x, int _y) {
		this.x = _x;
		this.y = _y;
	}

	@Override
	public int compareTo(Pos p) {
		return this.x != p.x? this.x-p.x: this.y-p.y;
	}

}


public class HE11650 {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		Pos[] list = new Pos[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			list[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(list);
		
		for (int i = 0; i < N; i++) {
			sb.append(list[i].x + " " + list[i].y + "\n");
		}
		System.out.println(sb);
	}

}
