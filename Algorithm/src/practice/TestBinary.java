package practice;

public class TestBinary {

	public static void main(String[] args) {
		int A = 1;
		System.out.println("A값 :" + A); // 1
		System.out.println("A 이진수 값 :" + Integer.toBinaryString(A)); // 1
		System.out.println("A 1번 오른쪽으로 밀면? : " + Integer.toBinaryString(A >> 1)); // 0
		System.out.println("A 10번 왼쪽으로 밀면? : " + Integer.toBinaryString(A << 10)); // 10000000000
		System.out.println("A 10번 왼쪽으로 밀고 1 빼면? : " + Integer.toBinaryString((A << 10) - 1)); // 1111111111
		int B = (int) (Math.pow(2, 10))-1;
		System.out.println(B); // 1023
		System.out.println(Integer.toBinaryString(B)); // 1111111111
		System.out.println(Integer.bitCount(B)); // 10
		
		System.out.println(1 << 0); // 1
		System.out.println(1 << 1); // 2
		System.out.println(1 << 2); // 4
		System.out.println(1 >> 2); // 0
		System.out.println(1 >> 1); // 0
		System.out.println(1 >> 0); // 1
	}

}
