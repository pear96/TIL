package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_수들의합_1789 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long S = Long.parseLong(br.readLine());
		long num = 1;
		
		while (S >= num) {
			System.out.println(S +" " +num);
			S -= num;
			num++;
		}
		
		System.out.println(num-1);
	}

}
