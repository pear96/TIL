package practice;

import java.util.Arrays;

public class CircularQueue {
	private int[] elements;
	private int front;
	private int rear;
	private int maxSize;

	public CircularQueue(int size) {
		this.elements = new int[size + 1];
		//더미 공간, isEmpty와 Full 상태를 구별하기 위함
		this.front = 0;
		this.rear = 0;
		this.maxSize = size+1;
	}

	public void offer(int data) {
		if (this.isFull()) {
			throw new IllegalStateException();
		}

		this.elements[this.rear] = data;
		this.rear = (this.rear + 1) % this.maxSize;
	}

	public int poll() {
		if (this.isEmpty()) {
			throw new IllegalStateException();
		}
		int out = this.elements[this.front];
		this.front = (this.front + 1) % this.maxSize;
		return out;
	}

	public int peek() { // 데이터를 빼지 않고 확인만
		if (this.isEmpty()) {
			throw new IllegalStateException();
		}
		// 모듈러 안해도 되나??;;
		return this.elements[(this.front + 1) % this.maxSize];
	}

	public int size() {
		if (this.front <= this.rear) {
			return this.rear - this.front;
		}
		return this.maxSize - this.front + this.rear;
	}

	public void clear() {
		this.front = 0; // 어차피 데이터 넣으면 초기화되니 이렇게 하면
		this.rear = 0; // 초기화
	}

	public boolean isEmpty() {
		return this.front == this.rear;
	}

	private boolean isFull() {
		return (this.rear + 1) % this.maxSize == this.front;
		// rear 바로 뒤에 front가 있으면 큐가 꽉 찬 상태이다.
		// 한바퀴 돌면 front와 rear위치가 다시 바뀐다. rear+1 이 큐 사이즈보다
		// 커질 수 있기에, %연산으로 확실하게 확인한다.
	}

	public static void main(String[] args) {
		CircularQueue q = new CircularQueue(1);
		q.offer(1);
		System.out.println(Arrays.toString(q.elements));
		System.out.println("비었나?" + q.isEmpty());
		System.out.println("다 찼나?" + q.isFull());

		System.out.println("poll : " + q.poll());
		System.out.println("비었나?" + q.isEmpty());
		System.out.println("다 찼나?" + q.isFull());

		q.offer(2);
		System.out.println(Arrays.toString(q.elements));
		System.out.println("비었나?" + q.isEmpty());
		System.out.println("다 찼나?" + q.isFull());
		System.out.println("poll : " + q.poll());
		System.out.println("비었나?" + q.isEmpty());
		System.out.println("다 찼나?" + q.isFull());

		q.offer(3);
		System.out.println(Arrays.toString(q.elements));
		System.out.println("비었나?" + q.isEmpty());
		System.out.println("다 찼나?" + q.isFull());
		System.out.println("poll : " + q.poll());
		System.out.println("비었나?" + q.isEmpty());
		System.out.println("다 찼나?" + q.isFull());



		// q.offer(1);
		// q.offer(2);
		// q.offer(3);
		// System.out.println("비었나?" + q.isEmpty());
		// System.out.println("다 찼나?" + q.isFull());
		// System.out.println("peek : " + q.peek());
		// System.out.println("poll : " + q.poll());
		// System.out.println("poll : " + q.poll());
		// System.out.println("poll : " + q.poll());
		// System.out.println("비었나?" + q.isEmpty());
		// System.out.println("다 찼나?" + q.isFull());
		// q.offer(1);
		// System.out.println("비었나?" + q.isEmpty());
		// System.out.println("다 찼나?" + q.isFull());
		// System.out.println("poll : " + q.poll());
		// System.out.println("비었나?" + q.isEmpty());
	}
}
