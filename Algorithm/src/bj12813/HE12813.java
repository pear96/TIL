package bj12813;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HE12813 {

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
		
//		for(int i=0; i<length; i++) {
//			int bitA = Integer.parseInt(String.valueOf(A.charAt(i)));
//			int bitB = Integer.parseInt(String.valueOf(B.charAt(i)));
//			and.append(bitA&bitB);
//			or.append(bitA|bitB);
//			xor.append(bitA^bitB);
//			notA.append(bitA^1);
//			notB.append(bitB^1);
//		}
		
		// 다른 풀이
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
		System.out.println("?");
		System.out.println(or.toString());
		System.out.println("?");
		System.out.println(xor.toString());
		System.out.println("?");
		System.out.println(notA.toString());
		System.out.println("?");
		System.out.println(notB.toString());
	}

}
