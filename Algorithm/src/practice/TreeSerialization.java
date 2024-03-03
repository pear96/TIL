package practice;

public class TreeSerialization {
	static class Node {
		int val;
		Node left;
		Node right;
		Node(int x) {val = x;}
	}
	
	static int idx = 0;
	
	static Node deserialize(int[] serial) {
		if (serial == null || serial.length == 0) {
			return null;
		}
		
		return buildTree(serial);
	}
	static Node buildTree(int[] serial) {
		if (idx >= serial.length || serial[idx] == -1) {
			idx++;
			return null;
		}
		
		Node node = new Node(serial[idx++]);
		node.left = buildTree(serial);
		node.right = buildTree(serial);
		
		return node;
	}
	
	static boolean validate(Node node) {
		if (node == null) return true;
		
		// 나의 왼쪽 자식이 있고 + (왼쪽 자식의 왼쪽 자식이 있고 + 왼쪽 자식의 오른쪽이 있다 -> 왼쪽 자식 검증하러 감)
		if (node.left != null && (node.left.left != null || node.left.right != null)) {
            return validate(node.left);
        } else if (node.left != null) {
        	// 자식이 없으면 안된다. 
            return false;
        }
		
		// 나의 오른쪽 자식이 있고 + (오른쪽 자식도 양 쪽에 자식이 있다면 오른쪽 자식으로 들어감)
        if (node.right != null && (node.right.left != null || node.right.right != null)) {
            return validate(node.right);
        } else if (node.right != null) {
        	// 자식이 없으면 안된다.
            return false;
        }
        
        // 여긴 ㅜ머지?
        return true;

	}
	
	static void printTree(Node node) {
		System.out.println("나 : " + node.val);
		if(node.left != null) {
			System.out.println(node.val + "의 왼쪽 : " + node.left.val);
			printTree(node.left);
		}
		if(node.right != null) {
			System.out.println(node.val + "의 오른쪽 : " + node.right.val);
			printTree(node.right);
		}
	}
	
	public static void main(String[] args) {
		int[] serial = new int[] {3, 5, 9, -1, -1, 11, 4, 6, 8, 1, 2, 10, -1, -1, 11, 21, 7, 14, 16};
		Node root = deserialize(serial);
		printTree(root);
//		System.out.println(validate(root));
	}

}
