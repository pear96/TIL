package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class BJ_백설공주와일곱난쟁이_3040 {
	// 중간 인덱스 값을 제거하기 위해선 LinkeList가 나을 거라고 생각했는데
	// 채점을 해보니 시간에는 차이가 없음.. 입력값이 작아서 차이가 없나보다.
	static LinkedList<Integer> dwarfs;
	static int diff;
	static int[] fake;
	
	static void findFake() {
		for(int i = 0; i < 9; i++) {
			for(int j = i+1; j < 9; j++) {
				if ((dwarfs.get(i) + dwarfs.get(j)) == diff) {
					fake = new int[] {i, j};
					return;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 일곱명일 때 100이니깐 아홉명이면 무조건 100 넘음
		// 넘는 수의 합이 되는 두개를 찾으면 된다.
		
		// 난쟁이들의 값에서 빼야하는 두개의 수를 찾고, 그 수를 제거
		// 배열이 아닌 LinkedList를 활용 - 중간의 값을 제거하려면 ArrayList보다 적합함
		int sum = 0;
		dwarfs = new LinkedList<>();
		for(int i = 0; i < 9; i++) {
			dwarfs.add(Integer.parseInt(br.readLine()));
			sum += dwarfs.get(i);
		}
		
		// diff의 합과 정확히 맞는 2 난쟁이 찾아야됨 
		diff = sum - 100;
		findFake();	
		
		dwarfs.remove(fake[1]);
		dwarfs.remove(fake[0]);
		
		
		for(int dwarf : dwarfs) {
			System.out.println(dwarf);
		}
	}

}
