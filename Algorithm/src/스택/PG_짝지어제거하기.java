package 스택;

/* https://school.programmers.co.kr/learn/courses/30/lessons/12973
 * LV2 + 문자열 같지만 잘 보면 스택 문제
 * */

import java.util.ArrayDeque;

public class PG_짝지어제거하기 {
	public int solution(String s)
    {	
		// Stack보다 ArrayDeque가 조금 더 빠름
		ArrayDeque<Character> stack = new ArrayDeque<>();

        for(int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty() && stack.peek() == s.charAt(i)) stack.pop();
            else stack.push(s.charAt(i));
        }
        
        return stack.isEmpty() ? 1 : 0;
    }
}
