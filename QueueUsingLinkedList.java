package projects;

class LinkedList<T> {
	T data;
	LinkedList<T> next;

	public LinkedList(T data) {
		this.data = data;
		this.next = null;
	}

}

public class QueueUsingLinkedList<T> {

	int size;
	LinkedList<T> front;
	LinkedList<T> rear;

	public QueueUsingLinkedList() {
		size = 0;
		front = null;
		rear = null;
	}

	public void enqueue(T data) {
		size++;
		if (front == null) {
			LinkedList<T> newNode = new LinkedList<T>(data);
			front = newNode;
			rear = newNode;
		} else {
			LinkedList<T> newNode = new LinkedList<T>(data);
			rear.next = newNode;
			rear = newNode;
		}
	}

	public T dequeue() throws EmptyQueueException {
		LinkedList<T> temp = null;
		if (front == null) {
			throw new EmptyQueueException();
		} else {
			temp = front;
			front = front.next;
			size--;
			if (size == 0) {
				front = null;
				rear = null;
			}
		}
		return temp.data;
	}

	public T front() throws EmptyQueueException {
		if (front == null) {
			throw new EmptyQueueException();
		}

		return front.data;

	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

}
