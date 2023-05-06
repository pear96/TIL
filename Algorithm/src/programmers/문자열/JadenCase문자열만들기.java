package programmers.문자열;

/* 2023.05.06
 * 풀이 시간)
 * 풀이 과정)
 * - split(" ") 으로 String[] 을 조절하려고 했다.
 * - 그러나 마지막에 공백이 있는 경우 처리 때문에 8번 테스트 케이스가 틀렸다.
 * - String[]을 idx로 순회하면서 length-1인 경우에만 공백을 추가해줬는데도 틀렸다.
 * - 그래서 다른 사람의 풀이를 보고 char[] 로 바꿔서 풀었다.
 * 
 * - 대,소문자열 함수를 배웠다.
 * - String : str.toUpperCase(), str.toLowerCase(),
 * - char : Character.isUpperCase(), Character.toUpperCase()
 * - 또한, 숫자인 경우에는 알파벳 문자가 아닌 경우 입력된 값 그대로를 반환하기 때문에 숫자를 따로 처리할 필요가 없다.
 * */


public class JadenCase문자열만들기 {
	
	static class Solution {
	    public static String solution(String s) {
	        char[] sent = s.toCharArray();
	        sent[0] = Character.toUpperCase(sent[0]);
	        for(int i = 1; i < sent.length; i++) {
	            // 내 앞이 공백이라면 나는 대문자.
	            if (sent[i-1] == ' ') sent[i] = Character.toUpperCase(sent[i]); 
	            else sent[i] = Character.toLowerCase(sent[i]);
	        }
	        return String.valueOf(sent);
	    }
	}

	public static void main(String[] args) {
		System.out.println(Solution.solution("for the last week"));
		//"For The Last Week"
	}

}
