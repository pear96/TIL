package programmers.베스트앨범;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
	static class Genre implements Comparable<Genre> {
		String name;
		long total;

		public Genre (String name, long total) {
			this.name = name;
			this.total = total;
		}

		public int compareTo(Genre other) {
			return (other.total - this.total >= 0)? 1 : -1;
		}
	}

	static class Music {
		int idx;
		int play;

		public Music (int idx, int play) {
			this.idx = idx;
			this.play = play;
		}
	}

	public static int[] solution(String[] genres, int[] plays) {
		HashMap<String, Long> genrePlay = new HashMap<>();
		HashMap<String, ArrayDeque<Music>> genreList = new HashMap<>();

		for (int i = 0; i < genres.length; i++) {
			// 장르별 총 재생 횟수 저장
			if (genrePlay.containsKey(genres[i])) {
				genrePlay.put(genres[i], genrePlay.get(genres[i]) + plays[i]);
			} else genrePlay.put(genres[i], (long) plays[i]);

			// 장르에 해당하는 노래 저장
			Music now = new Music(i, plays[i]);
			ArrayDeque<Music> q = new ArrayDeque<>();

			if (genreList.containsKey(genres[i])) {
				// 해당 장르에 저장된 노래가 있다면
				q = genreList.get(genres[i]);
				if (q.size() == 1) {
					// 노래가 한 곡 있다면 재생 횟수를 비교해 앞, 뒤에 넣는다.
					if (q.peek().play > plays[i]) q.addLast(now);
					else q.addFirst(now);
				} else {
					// 2곡이라면 앞, 뒤 비교해서 넣고 맨 뒤에는 지운다.
					if (q.getFirst().play < plays[i]) {
						q.addFirst(now);
						q.removeLast();
					} else if (q.getLast().play < plays[i]) {
						q.removeLast();
						q.addLast(now);
					} // 들어갈 수 없다면 그냥 패스
				}
			} else {
				// 없다면 일단 넣는다.
				q.add(now);
				genreList.put(genres[i], q);
			}
		}

		// 장르별로 정렬해야한다.
		Genre[] genreSort = new Genre[genrePlay.size()];
		int idx = 0;
		for (Map.Entry<String, Long> genre : genrePlay.entrySet()) {
			genreSort[idx++] = new Genre(genre.getKey(), genre.getValue());
		}
		Arrays.sort(genreSort);

		// 정답을 담을 배열
		ArrayList<Integer> best = new ArrayList();

		for (Genre genre : genreSort) {
			ArrayDeque<Music> q = genreList.get(genre.name);
			while (!q.isEmpty()) {
				best.add(q.poll().play);
			}
		}

		int[] answer = new int[best.size()];

		for (int i = 0; i < best.size(); i++) {
			answer[i] = best.get(i);
		}


		return answer;
	}

	public static void main(String[] args) {
		String[] genres = new String[] {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = new int[] {500, 600, 150, 800, 2500};
		solution(genres, plays);
	}
}