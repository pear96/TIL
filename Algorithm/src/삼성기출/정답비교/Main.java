package 삼성기출.정답비교;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader brA = new BufferedReader(new FileReader("C:\\Users\\pear\\study\\TIL\\Algorithm\\src\\codetree\\정답비교\\output.txt"));
		BufferedReader brB = new BufferedReader(new FileReader("C:\\Users\\pear\\study\\TIL\\Algorithm\\src\\codetree\\정답비교\\answer.txt"));
		
		for(int i = 0; i < 5016; i++) {
			String a = brA.readLine();
			String b = brB.readLine();
			if(!a.equals(b) ) {
				System.out.println(i + "번 줄에서 틀림. 정답 : " + a + ", 내답: " + b);
				break;
			}
		}
		System.out.println("결과는?");
	}

}
