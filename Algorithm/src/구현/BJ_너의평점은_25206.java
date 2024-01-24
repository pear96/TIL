package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ_너의평점은_25206 {

	public static void main(String[] args) throws IOException {
		HashMap<String, Double> grade = new HashMap<> () {{
			put("A+", 4.5);
			put("A0", 4.0);
			put("B+", 3.5);
			put("B0", 3.0);
			put("C+", 2.5);
			put("C0", 2.0);
			put("D+", 1.5);
			put("D0", 1.0);
			put("F", 0D);
		}};

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		double totalScore = 0; // 학점 * 등급
		double totalSubject = 0; // 총 학점

		StringTokenizer st;
		for (int i = 0; i < 20; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			double subject = Double.parseDouble(st.nextToken());
			String score = st.nextToken();
			if (!score.equals("P")) {
				totalSubject += subject;
				totalScore += subject * grade.get(score);
			}
		}
		System.out.println(totalScore / totalSubject);
	}
}
