package 삼성기출.코드트리채점기;
/*
 * 메모리 초과 발생. ChatGPT로 일일이 물어봤지만 이유를 모르겠다.
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N; // 채점기의 개수
	static boolean[] working; // 채점기의 상태
	
	// 자료구조들
	
	// 대기열 우선순위(300 명령어마다 새로 만들어야함)
	static PriorityQueue<Task> waitPQ;
	
	// 대기열 문제 목록. key = url, value = [우선순위, 입장시간]
	static HashMap<String, Wait> waitList = new HashMap<>(50000);
	
	// 채점 진행 목록. key = 채점기 아이디, value = [시작시간, 도메인]
	static HashMap<Integer, Mark> markList = new HashMap<>(50000);
	// 채점 진행 도메인
	static HashSet<String> markingDomain = new HashSet<>(50000);
	
	// 채점 완료 목록. key = 도메인, value =[시작시간, 갭]
	static HashMap<String, Finish> history = new HashMap<>(50000);
	
	
	static class Wait {
		int priority;
		int inTime;
		
		public Wait(int p, int i) {
			this.priority = p;
			this.inTime = i;
		}
	}
	
	static class Mark {
		int startTime;
		String domain;
		
		public Mark(int s, String d) {
			this.startTime = s;
			this.domain = d;
		}
	}
	
	static class Finish {
		int startTime;
		int gap;
		
		public Finish(int s, int g) {
			this.startTime = s;
			this.gap = g;
		}
	}
	
	static class Task implements Comparable<Task>{
		int priority; // 우선 순위
		int inTime; // 대기열 들어온 시간
		String domain;
		String number;
		
		public Task(int p, int i, String d, String n) {
			this.priority = p;
			this.inTime = i;
			this.domain = d;
			this.number = n;
		}

		@Override
		public int compareTo(Task t) {
			if (this.priority != t.priority) {
				return this.priority - t.priority;
			}
			return this.inTime - t.inTime;
		}
		
	}
	
	static String[] urlParse(String url) {
		String[] urlSet = url.split("/");
		return urlSet;
		
	}
	
	static void init(int cnt, String u) {
		N = cnt;
		
		// 1. 채점기의 개수만큼 쉬는 상태인지 판별을 위한 boolean 배열 생성
		working = new boolean[cnt+1];
		
		// 2. 입력받은 url을 대기열에 넣는다. 0초, 우선순위 1이다.	
		waitList.put(u, new Wait(1, 0));
		
	}
	
	static void request(int t, int p, String u) {
		// 1. 채점 대기 목록에 정확히 일치하는 url이 존재한다면 큐에 추가 X
		if (waitList.containsKey(u)) return;
		// 2. 아니라면 큐에 추가
		waitList.put(u, new Wait(p, t));
	}
	
	static void mark(int t) {
		waitPQ = new PriorityQueue<>();
		
		for(String u : waitList.keySet()) {
			String[] url = urlParse(u);
			if(markingDomain.contains(url[0])) continue;
			if(history.containsKey(url[0])) {
				// 같은 도메인을 가진 가장 최근에 채점된 작업
				Finish recent = history.get(url[0]);
				if(t < recent.startTime + recent.gap*3) continue;
			}
			
			// 즉시 채점 가능한 경우!
			Wait w = waitList.get(u);
			waitPQ.add(new Task(w.priority, w.inTime, url[0], url[1]));
		}
		
		// pq 완성
		if (!waitPQ.isEmpty()) {
			Task first = waitPQ.poll();
			Mark mark = new Mark(t, first.domain);
			
			for(int i = 1; i <= N; i++) {
				// 쉬고있는 채점기를 찾았다면!
				if (!working[i]) {
					// 채점기에 넣는다.
					working[i] = true;
					markList.put(i, mark);
					markingDomain.add(first.domain);
					
					// 대기목록에서 제거한다.
					waitList.remove(first.domain+"/"+first.number);
					
					break;
				}
			}
		}
	}
	
	static void finish(int t, int id) {
		// history에 추가
		if (!markList.containsKey(id)) return;
		
		Mark finished = markList.get(id);
		history.put(finished.domain, new Finish(finished.startTime, t - finished.startTime));
		
		// 채점중에서 제거
		markList.remove(id);
		markingDomain.remove(finished.domain);
		
		// 채점기 쉬는 중
		working[id] = false;
		
	}
	
	static void count(int t) {
		// 입력으로 t가 왜 들어오는지 모르겠음...
		System.out.println(waitList.size());
	}
	
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\pear\\study\\TIL\\Algorithm\\src\\codetree\\코드트리채점기\\input.txt"));
		StringTokenizer st;
		
		int Q = Integer.parseInt(br.readLine());
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case "100":
				init(Integer.parseInt(st.nextToken()), st.nextToken());
				break;
			case "200":
				request(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), st.nextToken());
				break;
			case "300":
				mark(Integer.parseInt(st.nextToken()));
				break;
			case "400":
				finish(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				break;
			case "500":
				count(Integer.parseInt(st.nextToken()));
				break;
			}
		}
	}

}
