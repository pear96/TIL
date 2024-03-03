package practice;

import java.util.Arrays;

public class CircularQueue2 {
	private int max;
	private int front;
	private int rear;
	private int[] que;
	public CircularQueue2(int capacity) {
		max = capacity+1;
		front = 0;
		rear = 0;
		que = new int[max];
	}

	public int enque(int x){
		int a = 0, b = 2;
		if (isFull()) return Integer.MAX_VALUE;
		que[rear] = x;
		rear = ++rear % max;
		return x;
	}

	public int deque(){
		if (isEmpty()) return -1;
		int x = que[front];
		front = ++front % max;
		return x;
	}

	public int peek(){
		if (isEmpty()) return -1;
		return que[front];
	}

	public int[] copyTo() {
		int[] retArr = new int[max];
		for (int i = 0; i < max; i++) {
			retArr[i] = que[(i + front) % max];
		}
		return retArr;
	}

	boolean isEmpty() {
		return front == rear;
	}

	boolean isFull() {
		return ((rear + 1) % max == front);
	}

	public static void main(String[] args) {
		CircularQueue2 q = new CircularQueue2(1);
		q.enque(1);
		System.out.println(Arrays.toString(q.que));
		System.out.println("비었나?" + q.isEmpty());
		System.out.println("다 찼나?" + q.isFull());

		System.out.println("deque : " + q.deque());
		System.out.println("비었나?" + q.isEmpty());
		System.out.println("다 찼나?" + q.isFull());

		q.enque(2);
		System.out.println(Arrays.toString(q.que));
		System.out.println("비었나?" + q.isEmpty());
		System.out.println("다 찼나?" + q.isFull());
		System.out.println("deque : " + q.deque());
		System.out.println("비었나?" + q.isEmpty());
		System.out.println("다 찼나?" + q.isFull());

		q.enque(3);
		System.out.println(Arrays.toString(q.que));
		System.out.println("비었나?" + q.isEmpty());
		System.out.println("다 찼나?" + q.isFull());
		System.out.println("deque : " + q.deque());
		System.out.println("비었나?" + q.isEmpty());
		System.out.println("다 찼나?" + q.isFull());
	}
}

