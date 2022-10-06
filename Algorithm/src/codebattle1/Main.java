package codebattle1;

import java.util.Scanner;

class Solution {
	
	private static int n, m;
	
	private final static UserSolution usersolution = new UserSolution();
	
	private static char[][] words = new char[4000][11];
	
	private static int mstrcmp(char[] a, char[] b)
	{
		int i;
		for (i = 0; a[i] != '\0'; i++)
		{
			if (a[i] != b[i])
				return a[i] - b[i];
		}
		System.out.println(a[i]);
		System.out.println(b[i]);
		System.out.println(a[i] == b[i]);
		return a[i] - b[i];
	}

	private static void String2Char(String s, char[] b) {
		int n = s.length();
		for (int i = 0; i < n; ++i) {
			b[i] = s.charAt(i);
		}
		b[n] = '\0';
	}
	
	private static void inputWords(int wordCnt, Scanner sc) {
		
		for (int i = 0; i < wordCnt; ++i) {
			String2Char(sc.next(), words[i]);	
		}
	}
	
	private static boolean run(int m, Scanner sc) {
		
		boolean accepted = true;
		char[][] correctWord = new char[5][11];
		char[][] answerWord = new char[5][11];
		 
		// m번 만큼 돌면서
		while(m-- > 0) {
			
			int id, timestamp, correctWordN, answerWordN;
			int wordIdx;
			
			id = sc.nextInt(); // 사용자 id
			timestamp = sc.nextInt(); // 입력 시간
			wordIdx = sc.nextInt(); // 오타
			
			// 교정된 단어(correctWord)와 교정된 단어의 개수(correctWordN) 반환
			correctWordN = usersolution.search(id, timestamp, words[wordIdx], correctWord);
			// 정답 단어의 개수
			answerWordN = sc.nextInt();
			System.out.println(m + " 번째  내 반환 값 = " + correctWordN + " 정답 값 = "+answerWordN );
			
			for (int i = 0; i < answerWordN; ++i) {
				// 정답의 개수만큼 문자열을 char[] 로 저장
				// 아 정답이 있으면 문자열이 나오는구나 죄다 0이 와서 없었던거임
				String2Char(sc.next(), answerWord[i]);
			}
			
			if (correctWordN != answerWordN) {			
				// 애초에 교정된 단어의 개수와 정답의 개수 부터 다름(최대 5개)
				accepted = false;
			} else {
				// 교정된 단어 하나씩 비교
				for (int i = 0; i < answerWordN; ++i) {
					boolean isExist = false;
					System.out.println("-------------------");
					System.out.println(answerWord[0].length);
					System.out.println(correctWord[0].length);
					
					System.out.println(answerWord[0]);
					System.out.println(correctWord[0]);
					
					for (int j = 0; j < correctWordN ; ++j) {
						//char[]이랑 char[] 비교
						if (mstrcmp(answerWord[i], correctWord[j]) == 0) {
							isExist = true;
						}
					}
					
					if (!isExist) {
						accepted = false;
					}
				}
			}
		}
		
		return accepted;
	}
	
	public static void main(String[] args) throws Exception {
		
		int test, T;
		int wordCnt;
		
		 System.setIn(new java.io.FileInputStream("src/codebattle1/input.txt"));
		
		Scanner sc = new Scanner(System.in);
		// 테스트 케이스(1)
		T = sc.nextInt();
		
		for (test = 1 ; test <= T ; ++test) {
			// 단어 개수(5)
			wordCnt = sc.nextInt();
			
			// wordCnt 단어 개수 저장
			// 입력 문자를 11자리의 char로 변환(대체 왜)
			inputWords(wordCnt, sc);
			// 초기 N 값과 검색 횟수 m
			n = sc.nextInt();
			m = sc.nextInt();
			
			usersolution.init(n);
			
			if (run(m, sc)) {
				System.out.println("#" + test + " 100");
			} else {
				System.out.println("#" + test + " 0");
			}
		}
	}
}