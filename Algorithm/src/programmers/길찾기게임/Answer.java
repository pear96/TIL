package programmers.길찾기게임;

/*
 * 시간 복잡도 = O(NlogN)
 *  makeTree 함수의 O(logN)과 나머지 부분의 O(NlogN + N)으로 결정되며, 이는 O(NlogN)과 동일합니다.
 * */


import java.util.Arrays;
import java.util.Comparator;


public class Answer {
	// 순회시 저장하기 위해 class 변수로 선언
    int[][] answer;
    int idx;
    
    // 노드 저장 방법
    class Node {
        int x;
        int y;
        int number; // 각 노드의 순서를 반환해야한다.
        // 이진 트리 저장 방법
        Node left;
        Node right;
        
        public Node(int x, int y, int number, Node left, Node right) {
            this.x = x;
            this.y = y;
            this.number = number;
            this.left = left;
            this.right = right;
        }
        
    }
    
    // O(h)이지만, 이진 트리는 모든 노드 갯수가 N일 때, 최대 h = logN 이므로
    // 최악의 시간 복잡도는 O(logN)
    public void makeTree(Node parent, Node child) {
        if (parent.x > child.x) {
            // 부모의 x가 크면 왼쪽 자식
            if(parent.left == null) parent.left = child;
            // 만약에 이미 있으면, 그 아래로 내려가본다.
            else makeTree(parent.left, child);
        } else {
            // 부모의 x보다 작으면 오른쪽 자식일 수 있음
            if(parent.right == null) parent.right = child;
            // 만약에 이미 있으면, 그 아래로 내려가본다.
            else makeTree(parent.right, child);
        }
    }
    
    // O(N)
    public void preOrder(Node now) {
        answer[0][idx++] = now.number;
        if(now.left != null) preOrder(now.left);
        if(now.right != null) preOrder(now.right);
    }
    
 // O(N)
    public void postOrder(Node now) {
        if(now.left != null) postOrder(now.left);
        if(now.right != null) postOrder(now.right);
        answer[1][idx++] = now.number;
    }
    
    
    public int[][] solution(int[][] nodeinfo) {
        Node[] node = new Node[nodeinfo.length];
        answer = new int[2][nodeinfo.length];
        
        // O(N) 노드의 순서를 반환해야하므로, i+1을 저장해야한다.
        for(int i = 0; i < nodeinfo.length; i++) {
            node[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i+1, null, null);
        }
        
        // 레벨별로 나눠서 저장하려고 했는데, 정렬로 해결이 된다.
        // 우선 y(레벨)이 큰 순서대로(위에서부터) 저장한다.
        // 레벨이 같은 경우엔 x값이 작은(왼쪽)을 기준으로 저장한다.
        // y값에 맞춰 정렬하지 않으면 부모 -> 자식 순서로 저장할 수 없다.
        // O(NlogN)
        Arrays.sort(node, new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
            	// y값 내림차순 정렬
            	// 인터넷에 검색하면 y가 같은 경우 x가 작은 순으로 정렬하는데, 안해도 통과됨.
            	// 노드를 생성할 때 x를 고려하여 왼, 오를 정하니 딱히 상관 없는게 맞음.
                return n2.y - n1.y;
            }
        });
        
        // 루트 노드 설정
        Node root = node[0];
        
        // root 노드를 기준으로 위에서 정렬한 순서대로 노드의 위치를 찾아간다.
        // 처음엔 node[i-1], node[i] 라고 생각했는데, 양 옆끼리 위치를 어떻게 찾겠다는건지...
        for(int i = 1; i < node.length; i++) {
            makeTree(root, node[i]);
        }
        
        // 순회를 해야한다. answer에 저장하기 위해 번거롭지만 idx를 따로 초기화 한다.
        // idx를 parameter로 넘기면 해결할 수 없는걸까?
        idx = 0;
        preOrder(root);
        
        idx = 0;
        postOrder(root);
            
        return answer;
    }
    
}