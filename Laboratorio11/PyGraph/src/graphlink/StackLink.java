package graphlink;

import linkedlist.*;

public class StackLink<E> {
	private Node<E> first;

	public StackLink() {
		this.first = null;
	}

	public void push(E x) {
		Node<E> aux = new Node<E>(x);
		if (isEmpty()) {
			first = aux;
		} else {
			aux.setNext(first);
			first = aux;
		}
	}

	public E pop() throws ExceptionIsEmpty {
		try {
			if (isEmpty()) {
				throw new ExceptionIsEmpty("La pila esta vacia");
			}
			E data = first.getData();
			first = first.getNext();
			return data;

		} catch (ExceptionIsEmpty e) {
			System.out.println("La pila esta vacia");
			return null;
		}
	}

	public E top() throws ExceptionIsEmpty {
		try {
			if (isEmpty()) {
				throw new ExceptionIsEmpty("La pila esta vacia");
			}
			return first.getData();

		} catch (ExceptionIsEmpty e) {
			System.out.println("La pila esta vacia");
			return null;
		}
	}

	public boolean isEmpty() {
		return this.first == null;
	}

	public String toString() {
		String message = "";
		if (isEmpty()) {
			message = "La pila esta vacia";
		} else {
			Node<E> aux = first;
			message += aux;
			while (aux.getNext() != null) {
				message += " -> ";
				message += aux.getNext();
				aux = aux.getNext();
			}
		}
		return message;
	}
}