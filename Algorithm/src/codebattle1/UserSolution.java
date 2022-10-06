package codebattle1;

import java.util.ArrayList;
import java.util.HashMap;

class UserSolution {
	
	class User {
		int lastTime; // 마지막으로 검색한 시간
		String lastWord; // 마지막으로 검색한 단어
		
		public User(int _lastTime, String _lastWord) {
			this.lastTime = _lastTime;
			this.lastWord = _lastWord;
		}
	}
	
//	class Record {
//		HashMap<String, Correct> corrects; // 오타에 대한 정타
//		
//		public Record(HashMap<String, Correct> _corrects) {
//			this.corrects = _corrects;
//		}
//	}
	
	class Correct {
//		String word; // 정타
		ArrayList<Integer> memberList; // 오타-정타 입력한 사람들
		boolean onDB; // DB 등록 여부
		
		public Correct(ArrayList<Integer> _memberList, boolean _onDB) {
//			this.word = _word;
			this.memberList = _memberList;
			this.onDB = _onDB;
		}
	}
	
	User[] users;
	HashMap<String, HashMap<String, Correct>> records;
	
	
	int ifAdd(String typo, String word) {
		int possible = 0;
		int wordIdx = 0;
		int typoSize = typo.length();
		int wordSize = word.length();
		// 글자 하나가 추가됨
		if (typoSize == wordSize + 1) {
			// 한개만 달라야함.
			int chance = 1;
			for (int i=0; i < typoSize; i++) {
				if (wordIdx < typoSize-1) {
					if(typo.charAt(i) != word.charAt(wordIdx)) {
						if(chance <= 0) {
							break;
						}
						chance--;
					}
					else {
						wordIdx++;
					}
				}
			}
			if (wordIdx == wordSize) {
				possible = 1;
			}
		}
		return possible;
	}
	
	int ifReplace(String typo, String word) {
		int possible = 0;
		int typoIdx = 0;
		// 글자 하나가 다름
		if (typo.length() == word.length()) {
			// 한개만 달라야함.
			int chance = 1;
			for (int i=0; i < word.length(); i++) {
				if(typo.charAt(typoIdx) != word.charAt(i)) {
					if(chance <= 0) {
						break;
					}
					chance--;
				}
				typoIdx++;
			}
			if (typoIdx == typo.length()) {
				possible = 1;
			}
		}
		// 글자 하나가 변경 됨
		return possible;
	}
	
	int ifDelete(String typo, String word) {
		int possible = 0;
		int typoIdx = 0;
		// 글자 하나가 삭제됨
		if (typo.length() == word.length() - 1) {
			// 한개만 달라야함.
			int chance = 1;
			for (int i=0; i < word.length(); i++) {
				if(i < word.length()-1) {
					if(typo.charAt(typoIdx) != word.charAt(i)) {
						if(chance <= 0) {
							break;
						}
						chance--;
					}
					else {
						typoIdx++;
					}
				}
			}
			if (typoIdx == typo.length()) {
				possible = 1;
			}
		}
		return possible;
	}
	
	boolean isTypo(String typo, String word) {
		int type = 0;
		
		type += ifAdd(typo, word);
		type += ifReplace(typo, word);
		type += ifDelete(typo, word);
		
		if (type == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	void init(int n) {
		// N: 사용자 수 (5 ≤ N ≤ 2,000)
		users = new User[n+1];
		records = new HashMap<String, HashMap<String, Correct>>();
	}
	
	int search(int mId, int searchTimestamp, char[] searchWord, char[][] correctWord) {
		int correctWordsCount = 0;
		int correctWordsIdx = 0;
		String nowSearchingWord = String.valueOf(searchWord).trim();
		
		// mId: 검색 사용자의 id (1 ≤ mId ≤ N)
	    // searchTimestamp: 검색 시간. 단위는 초 (1 ≤ serachTimestamp ≤ 50,000)
	    // searchWord[11]: 검색할 단어 (4 ≤ 단어의 길이 ≤ 10)
	    // correctWord[5][11]: 교정된 단어 (4 ≤ 단어의 길이 ≤ 10)
		// 하나의 searchWord에 대해 correctWord는 최대 5개까지 가능하다.
		
		// 해당 유저가 검색한 기록
		User user = users[mId];
		
		if(user == null) {
			users[mId] = new User(searchTimestamp, nowSearchingWord);
		}
		else {
			System.out.println("[" + searchTimestamp + "검색] " + mId + "번 유저 => 마지막 검색 시간 : " + user.lastTime + " / 마지막 검색어 : " + user.lastWord + " / 지금 검색어 : " + nowSearchingWord);
			// 1. 해당 유저가 10초 이내에 검색한게 있는지 본다.
			// 있으면 지금 정타, 없으면 지금 오타.
			String lastSearchedWord = user.lastWord;
			if(searchTimestamp - user.lastTime <= 10) {
				// 10초 내에 검색한 기록이 있으면 마지막 검색어(오타) 기준으로 찾아야됨.
				HashMap<String, Correct> record = records.get(lastSearchedWord);
				if (record == null) {
					// 오타에 대한 기록이 없다. 오타 + 정타 조합 만든다.
					if (isTypo(lastSearchedWord, nowSearchingWord)) {
						// 부합하면 후보로 등록하셈
						ArrayList<Integer> mList = new ArrayList<Integer>();
						mList.add(mId);
						// 정타에 대한 정보
						Correct correctData = new Correct(mList, false);
						HashMap<String, Correct> correctMap = new HashMap<String, Correct>();
						correctMap.put(nowSearchingWord, correctData);
						// 오타와 정타 묶기
						records.put(lastSearchedWord, correctMap);
					}
				}
				else {
					// 오타 + 정타 조합이 있는지 확인한다.
					Correct correctRecord = record.get(nowSearchingWord);
					if (correctRecord != null) {
						// 이미 등록된 오타 + 정타 조합. 검증 필요 X
						// 사용자 수 추가
						ArrayList<Integer> mList = correctRecord.memberList;
						// 이미 있는 사용자면 넘어가야한다.
						if (!mList.contains(mId)) {
							mList.add(mId);
							if (mList.size() >= 3) {
								correctRecord.onDB = true;
							}
						}
					}
					else {
						// 10초내 검색이라 지금 검색어가 정타임.
						// 오타 + 정타 조합이 없음 => 오타가 3가지 조건 중 하나에 부합하는지 봐야함.
						if (isTypo(lastSearchedWord, nowSearchingWord)) {
							// 부합하면 후보로 등록하셈
							ArrayList<Integer> mList = new ArrayList<Integer>();
							mList.add(mId);
							correctRecord = new Correct(mList, false);
							record.put(nowSearchingWord, correctRecord);
						}
					}
				}
			}
			// 사용자 검색 기록 업데이트
			user.lastTime = searchTimestamp;
			user.lastWord = nowSearchingWord;
		}
		// 2. 없다면 오타 교정 DB에 등록되었는지 본다.(지금은 nowSearchingWord가 오타)
		HashMap<String, Correct> record = records.get(nowSearchingWord);
		if(record != null) {
			// 그 오타에 대한 정타가 여러개일 수 있다
			// 해당 오타에 대한 정타를 보면서, DB에 등록되어있는지 보고
			for(String correctIdx:record.keySet()) {
				if(record.get(correctIdx).onDB == true) {
					// 등록되어 있다면 correctWord 배열에 추가
					char[] charArray = new char[11];
					char[] correctToChar = correctIdx.toCharArray();
					for(int i=0; i < correctToChar.length; i++) {
						charArray[i] = correctToChar[i];
					}
					correctWord[correctWordsIdx] = charArray;
					correctWordsIdx++;
					// 정답 수 증가
					correctWordsCount++;
				}
			}
		}
		
		// 교정된 단어의 개수를 반환.
		return correctWordsCount;
	}
}
