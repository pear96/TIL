package 정렬;

import java.util.Arrays;

/*
 * 배열 A, B의 원소의 개수, 크기가 각각 1000 이하이므로, 
 * 누적합이 1000 * 1000 * 1000 = 10^9라서 int값 범위 내이다. (int =>  2,147,483,647)
 * A와 B의 곱의 누적합이 최소가 되려면, 어떻게 해야할까?
 * A를 정렬하고, B를 정렬하고, 서로 반대 위치에서 곱해가면 되는걸까?
 * 완전탐색을 하려면 1000!이라는 어마무시한 숫자가 나오니깐 말이 안되잖아?
 * 근데 뭔가 이런 비슷한 문제를 parametric search로 풀었던 기억이 나는데... 
 * (아 정렬까진 맞고 최소, 최대 값을 이상하게 구했었음)
 * https://school.programmers.co.kr/learn/courses/30/lessons/12941
 * */

public class PG_최솟값만들기 {
	
	class Solution
	{
	    public int solution(int []A, int []B)
	    {
	        int answer = 0;

	        Arrays.sort(A);
	        Arrays.sort(B);
	        
	        for(int i = 0; i < A.length; i++) {
	            answer += A[i] * B[B.length-1-i];
	        }

	        return answer;
	    }
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
