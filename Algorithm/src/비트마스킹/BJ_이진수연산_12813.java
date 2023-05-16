package 비트마스킹;

//https://www.acmicpc.net/problem/12813


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_이진수연산_12813 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String A = br.readLine();
		String B = br.readLine();
		int length = A.length();
		
		StringBuilder and = new StringBuilder();
		StringBuilder or = new StringBuilder();
		StringBuilder xor = new StringBuilder();
		StringBuilder notA = new StringBuilder();
		StringBuilder notB = new StringBuilder();
		
		// 풀이 1. int로 바꾸어서 푼다. (연산자에 맞춰)
		for(int i=0; i<length; i++) {
			int bitA = Integer.parseInt(String.valueOf(A.charAt(i)));
			int bitB = Integer.parseInt(String.valueOf(B.charAt(i)));
			and.append(bitA&bitB);
			or.append(bitA|bitB);
			xor.append(bitA^bitB);
			// bit^1을 한 이유는, 1이면 같은 수라서 0이 되고, 0이면 다른 수라서 1이 반환된다.
			notA.append(bitA^1);
			notB.append(bitB^1);
		}
		
		// 풀이 2. char로 바꾸어서 푼다. (정의 그대로)
		for(int j=0; j<length; j++) {
			char charA = A.charAt(j);
			char charB = B.charAt(j);
			
			and.append((charA == '1' && charB == '1') ? '1' : '0');
			or.append((charA == '1' || charB == '1') ? '1' : '0');
			xor.append((charA != charB) ? '1' : '0');
			notA.append((charA == '1') ? '0' : '1');
			notB.append((charB == '1') ? '0' : '1');
		}
		
		System.out.println(and.toString());
		System.out.println(or.toString());
		System.out.println(xor.toString());
		System.out.println(notA.toString());
		System.out.println(notB.toString());
	}

}
