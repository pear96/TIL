package 문자열;

import java.util.HashSet;

public class PG_스킬트리 {
	class Solution {
		public int solution(String skill, String[] skill_trees) {
			int answer = 0;
			HashSet<Character> skill_set = new HashSet<>();
			for (char s : skill.toCharArray()) {
				skill_set.add(s);
			}

			for (String skill_tree : skill_trees) {
				boolean possible = true;
				int idx = 0;
				for (char step : skill_tree.toCharArray()) {
					if (step == skill.charAt(idx)) {
						idx++;
						if (idx == skill.length()) break;
					}
					else if (skill_set.contains(step)) {
						possible = false;
						break;
					}
				}
				if (possible) answer++;
			}

			return answer;
		}
	}
}
