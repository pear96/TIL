package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ_너의평점은_25206 {

	public static void main(String[] args) throws IOException {
		HashMap<String, Double> grade = new HashMap<> ();
		// 😬😬😬😬😬😬😬
		grade.put("A+", 4.5);
		grade.put("A0", 4.0);
		grade.put("B+", 3.5);
		grade.put("B0", 3.0);
		grade.put("C+", 2.5);
		grade.put("C0", 2.0);
		grade.put("D+", 1.5);
		grade.put("D0", 1.0);
		grade.put("F", 0D);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		double totalScore = 0; // 학점 * 등급
		double totalSubject = 1; // 총 학점

		StringTokenizer st;
		for (int i = 0; i < 20; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken(); // 과목 이름은 쓸 데가 없다.
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
