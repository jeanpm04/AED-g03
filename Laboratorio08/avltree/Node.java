package avltree;

public class Node<T> {
	protected T data;
	protected Node<T> left, right;
	protected Node<T> next; // Mantenemos el campo next

	public Node(T data) {
		this(data, null, null, null); // Agregamos el campo next al constructor
	}

	public Node(T data, Node<T> left, Node<T> right, Node<T> next) { // Agregamos next al constructor
		this.data = data;
		this.left = left;
		this.right = right;
		this.next = next; // Inicializamos el campo next
	}

	// Getters y setters para next
	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	// Resto de los getters y setters
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getLeft() {
		return left;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", left=" + left + ", right=" + right + ", next=" + next + "]";
	}
}
