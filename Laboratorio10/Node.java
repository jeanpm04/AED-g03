package hash;

public class Node<T> {
	public T data;
	public Node<T> next;

	Node(T data) {
		this(data, null);
	}

	Node(T d, Node<T> n) {
		this.data = d;
		this.next = n;
	}

	public T getData() {
		return this.data;
	}

	public Node<T> getNext() {
		return this.next;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setNext(Node<T> n) {
		this.next = n;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", next=" + next + "]";
	}
}
