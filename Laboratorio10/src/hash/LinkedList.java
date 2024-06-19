package hash;


public class LinkedList<T> implements TDAList<T>{
	protected Node<T> first;
	protected Node<T> last;
	
	public LinkedList() {
		this.first = null;
		this.last = null;
	}
	
	public Node<T> getFirst() {
		return this.first;
	}

	public void setFirst(Node<T> node) {
		this.first = node;
	}
	
	public Node<T> getLast() {
		return this.last;
	}

	public void setLast(Node<T> node) {
		this.last = node;
	}

	// Función para determinar si la lista está vacía
	// Poscondición: devuelve true si la lista está vacía; de lo contrario, devuelve false
	@Override
	public boolean isEmptyList() {
		return this.first == null;
	}

	// Función para devolver el número de nodos de la lista
	// Poscondición: se devuelve el valor del contador
	@Override
	public int length() {
		int cont = 0;
		Node <T> aux = this.first;
		while(aux != null) {
			cont++;
			aux = aux.getNext();
		}
		return cont;
	}

	// Función para eliminar todos los nodos de la lista
	@Override
	public void destroyList() {
		this.first = null;
	}

	// Función para determinar si searchItem está en la lista
	// Postcondición: devuelve la posición si se encuentra searchItem en la lista; de lo contrario, devuelve -1
	@Override
	public int search(T x) {
		Node <T> aux = this.first;
		int i = 0;
		while(aux != null) {
			if(aux.getData() != null && aux.getData().equals(x)) {
				return i;
			}
			aux = aux.getNext();
			i++;
		}
		return -1;
	}

	// Inserta un elemento al inicio de la lista
	@Override
	public void insertFirst(T x) {
		this.first = new Node<>(x, this.first);
		// Node <T> node = new Node<>(x);
		// node.next = first;
		// node.setNext(this.first);
		// this.first = node;
	}

	// Inserta un elemento al final de la lista
	/*@Override
	public void insertLast(T x) {
		Node <T> node = new Node<T>(x);
		Node <T> aux = this.first;
		if(this.first == null) {
			this.first = node;
		}
		else {
			while(aux.getNext() != null) {
				aux = aux.getNext();
			aux.setNext(node);
			node.setNext(null);
			}
		}
	}*/
	@Override
	public void insertLast(T x) {
	    Node<T> node = new Node<>(x);
	    Node<T> aux = this.first;

	    if(aux == null) {
	        this.first = node;
	    }
	    else {
	        while(aux.getNext() != null) {
	            aux = aux.getNext();
	        }
	        //aux.setNext(node);
	        aux.next = node;
	    }
	}


	// Función para eliminar deleteItem de la lista
	@Override
	public void removeNode(T x) {
		/*int pos = search(x);
		int cont = length();
		if(pos == -1) {
			System.out.print("Elemento no existe");
		}
		else if(pos == cont - 1) {
			int i = 0;
			Node <T> aux = this.first;
			while(i < cont - 2);{
				aux = aux.getNext();
				i++;
			}
			aux.setNext(null);
		}
		else if(pos == 0) {
			this.first = this.first.getNext();
		}
		else {
			int i = 0;
			Node <T> aux = this.first;
			while(i < pos - 1) {
				aux = aux.getNext();
				i++;
			}
			aux.setNext(aux.getNext().getNext());
		}*/
		if(!isEmptyList()) {
			if(first == last && x == first.data) {
				first = last = null;
			} else if(x == first.data) {
				first = first.next;
			} else {
				Node<T> ant, aux;
				ant = first;
				aux = first.next;
				while(aux != null && !aux.data.equals(x)) {
					ant = ant.next;
					aux = aux.next;
				}
				if(aux != null) {
					ant.next = aux.next;
					if(aux == last) {
						last = ant;
					}
				}
			}
		}
	}
	
	// Imprime la lista
	public void print() {
		Node <T> aux = this.first;
		while(aux != null) {
			System.out.print(aux.getData());
			aux = aux.getNext();
			if(aux != null) {
				System.out.print(" -> ");
			}
		}
		System.out.println("");
	}
}

