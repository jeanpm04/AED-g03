package linkedlist;

public class LinkedList<T> implements TDAList<T> {
	protected Node<T> first;

	public LinkedList() {
		this.first = null;
	}

	public Node<T> getFirst() {
		return first;
	}

	public void setFirst(Node<T> first) {
		this.first = first;
	}

	@Override
	public boolean isEmptyList() {
		return first == null;
	}

	@Override
	public int length() {
		int count = 0;
		Node<T> aux = first;
		while (aux != null) {
			count++;
			aux = aux.getNext();
		}
		return count;
	}

	@Override
	public void destroyList() {
		first = null;

	}

	@Override
	public int search(T x) {
		int index = 0;
		Node<T> aux = first;
		while (aux != null) {
			if (aux.getData().equals(x)) {
				return index;
			}
			aux = aux.getNext();
			index++;
		}
		return -1;
	}

	@Override
	public void insertFirst(T x) {
		Node<T> nuevoNodo = new Node<>(x);
		nuevoNodo.setNext(first);
		first = nuevoNodo;

	}

	@Override
	public void insertLast(T x) {
		Node<T> nuevoNodo = new Node<>(x);
		if (isEmptyList()) {
			first = nuevoNodo;
		} else {
			Node<T> aux = first;
			while (aux.getNext() != null) {
				aux = aux.getNext();
			}
			aux.setNext(nuevoNodo);
		}
	}

	@Override
	public void removeNode(T x) {
		if (isEmptyList()) {
			return;
		}
		if (first.getData().equals(x)) {
			first = first.getNext();
			return;
		}
		Node<T> previous = null;
		Node<T> current = first;

		while (current != null && !current.getData().equals(x)) {
			previous = current;
			current = current.getNext();
		}
		if (current != null) {
			previous.setNext(current.getNext());
		}
	}

	public T searchData(T data) {
		Node<T> nodo = this.first;
		while (nodo != null && !nodo.getData().equals(data))
			nodo = nodo.getNext();
		if (nodo != null)
			return nodo.getData();
		return null;
	}

	public T getNth(int p) {
		if (p < 0) {
			System.out.println("Índice fuera de rango");
			return null;
		}
		Node<T> aux = first;
		int i = 0;
		while (aux != null && i < p) {
			aux = aux.getNext();
			i++;
		}
		if (aux == null) {
			System.out.println("Índice fuera de rango");
			return null;
		}
		return aux.getData();
	}

	public void deleteNth(int p) {
		if (p == 0) {
			if (first != null) {
				first = first.getNext();
			}
			return;
		}
		int i = 0;
		Node<T> aux = first;
		while (aux != null && i < p - 1) {
			aux = aux.getNext();
			i++;
		}
		if (aux == null || aux.getNext() == null) {
			System.out.println("Fuera de rango");
			return;
		}
		aux.setNext(aux.getNext().getNext());
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		Node<T> current = first;
		while (current != null) {
			result.append(current.getData().toString());
			if (current.getNext() != null) {
				result.append(" -> ");
			}
			current = current.getNext();
		}
		return result.toString();
	}
}