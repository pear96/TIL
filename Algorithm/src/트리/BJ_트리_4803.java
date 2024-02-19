package 트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_트리_4803 {
	static int[] ancestor;
	static int find(int node) {
		if (node == ancestor[node]) {
			return node;
		}
		ancestor[node] = find(ancestor[node]);
		return ancestor[node];
	}

	static void union(int nodeA, int nodeB) {
		int ancestorNodeA = find(nodeA);
		int ancestorNodeB = find(nodeB);

		if (ancestorNodeA != ancestorNodeB) {
			// 둘 중 하나라도 사이클이라고 판별된 경우
			if (ancestor[ancestorNodeA] == 0 || ancestor[ancestorNodeB] == 0) {
				ancestor[ancestorNodeA] = 0;
				ancestor[ancestorNodeB] = 0;
			} else if (ancestor[ancestorNodeA] == ancestorNodeA) {
				ancestor[ancestorNodeB] = ancestorNodeA;
			} else {
				ancestor[ancestorNodeA] = ancestorNodeB;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int testcase = 1;
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		while (N != 0 || M != 0) {
			sb.append("Case ").append(testcase++).append(": ");
			ancestor = new int[N+1]; // 자기 자신과 조상이 동일하면 자기가 root
			for (int idx = 1; idx <= N; idx++) {
				ancestor[idx] = idx; // 일단 자기 자신을 조상으로 설정해둠
			}

			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int nodeA = Integer.parseInt(st.nextToken());
				int nodeB = Integer.parseInt(st.nextToken());
				int ancestorNodeA = find(nodeA);
				int ancestorNodeB = find(nodeB);
				if (ancestorNodeA == ancestorNodeB) {
					// 동일하면 cycle이 생긴 것이다.
					ancestor[ancestorNodeA] = 0;
				} else {
					union(ancestorNodeA, ancestorNodeB);
				}
			}

			// 트리의 개수 구하기
			int tree = 0;
			for (int idx = 1; idx <= N; idx++) {
				if (ancestor[idx] == idx) tree++;
			}
			if (tree > 1) {
				sb.append("A forest of ").append(tree).append(" trees.\n");
			} else if (tree == 1) {
				sb.append("There is one tree.\n");
			} else {
				sb.append("No trees.\n");
			}

			// 다음 입력 받기
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
		}

		System.out.println(sb.deleteCharAt(sb.length()-1));
	}
}
