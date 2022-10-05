package codebattle1;

import java.util.HashMap;

class UserSolution {
	
	class User {
		int lastTime;
		String lastWord;
	}
	
	User[] users;
	
	class Correct {
		String word;
		int[] memberList;
		boolean onDB;
	}
	
	class Record {
		String wrong;
		HashMap<String, Correct> corrects;
	}
	
	HashMap<String, Record> records;
	
	// The below commented methods are for your reference. If you want 
	// to use it, uncomment these methods.
	
	// a랑 b랑 비교해서 같으면 0, 아니면 그외
	int mstrcmp(char[] a, char[] b) {
		int i;
		for (i = 0; a[i] != '\0'; i++) {
			if (a[i] != b[i])
				return a[i] - b[i];
		}
		return a[i] - b[i];
	}
	
	// a랑 b랑 비교해서 같으면 0, 아니면 그외
	// \0 이스케이프 문자 없이 문자열 길이로 비교
	int mstrncmp(char[] a, char[] b, int len) {
		for (int i = 0; i < len; i++) {
			if (a[i] != b[i])
				return a[i] - b[i];
		}
		return 0;
	}
	
	// a의 길이 (mstrncmp 쓰고싶으면 필요할듯)
	int mstrlen(char[] a) {
		int len = 0;

		while (a[len] != '\0')
			len++;

		return len;
	}
	
	// dest에 src를 복사하기
	void mstrcpy(char[] dest, char[] src) {
		int i = 0;
		while (src[i] != '\0') {
			dest[i] = src[i];
			i++;
		}
		dest[i] = src[i];
	}
	
	// dest에 src를 복사하는데 마지막에 \0 붙임
	void mstrncpy(char[] dest, char[] src, int len) {
		for (int i = 0; i < len; i++) {
			dest[i] = src[i];
		}
		dest[len] = '\0';
	}
	
	void init(int n) {
		// N: 사용자 수 (5 ≤ N ≤ 2,000)
		users = new User[n+1];
	}
	
	int search(int mId, int searchTimestamp, char[] searchWord, char[][] correctWord) {
		int answer = 0;
		int correctWordsIdx = 0;
		String searchW = String.valueOf(searchWord).trim();
		
		// mId: 검색 사용자의 id (1 ≤ mId ≤ N)
	    // searchTimestamp: 검색 시간. 단위는 초 (1 ≤ serachTimestamp ≤ 50,000)
	    // searchWord[11]: 검색할 단어 (4 ≤ 단어의 길이 ≤ 10)
	    // correctWord[5][11]: 교정된 단어 (4 ≤ 단어의 길이 ≤ 10)
		// 하나의 searchWord에 대해 correctWord는 최대 5개까지 가능하다.
		
		
		// 1. 해당 유저가 10초 이내에 검색한게 있는지 본다.
		User user = users[mId];
		if(user != null) {
			// 있다면 이전 검색어와 지금 검색어를 비교한다.
			if(searchTimestamp - user.lastTime <= 10) {
				
			}
			// 추가, 치환, 삭제 유형인지 확인하여, 1개이면 후보 등록, 이외는 후보 등록 안됨
			else {
				
			}
		}
		
		// 2. 없다면 오타 교정 DB에 등록되었는지 본다.
		else {
			// 후보에 등록된 적이 있는가?
			Record record = records.get(searchW);
			if(record != null) {
				// 그 오타에 대한 정타가 여러개일 수 있다
				HashMap<String, Correct> searchCorrects = record.corrects;
				// 해당 오타에 대한 정타를 보면서, DB에 등록되어있는지 보고
				for(String correctIdx:searchCorrects.keySet()) {
					if(searchCorrects.get(correctIdx).onDB == true) {
						// 등록되어 있다면 correctWord 배열에 추가
						correctWord[correctWordsIdx] = correctIdx.toCharArray();
						correctWordsIdx++;
						// 정답 수 증가
						answer++;
					}
					
				}
			}
		}
		// 교정된 단어의 개수를 반환.
		return answer;
	}
}
