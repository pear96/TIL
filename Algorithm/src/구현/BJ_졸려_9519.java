package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ_졸려_9519 {
	static String change(String word) {
		int len = word.length();
		StringBuilder result = new StringBuilder();
		int back = len - 1 - ((len % 2 == 1)? 1 : 0);

		for (int i = 0; i < len; i += 2) {
			result.append(word.charAt(i));
		}
		for (int i = back; i >= 0; i -= 2) {
			result.append(word.charAt(i));
		}

		return result.toString();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());
		String input = br.readLine();
		ArrayList<String> history = new ArrayList<>();
		history.add(input);
		String word = change(input);

		while (!word.equals(input)) {
			history.add(word);
			word = change(word);
		}
		System.out.println(history.get(X%history.size()));
	}
}
