package actividad2;

public class QueueLink<E> implements Queue<E> {
	private Node<E> first;
	private Node<E> last;

	public QueueLink() {
		this.first = null;
		this.last = null;
	}

	// Método que inserta un nodo x al
	// final de la cola (encolar)
	@Override
	public void enqueue(E x) {
		// Crea un nuevo nodo con el elemento x
		Node<E> nuevoNodo = new Node<E>(x);
		// Verifica si la cola no está vacía
		if (!isEmpty()) {
			// El next de last será el nuevo nodo
			this.last.setNext(nuevoNodo);
			// Ahora last apunta (referencia) hacia el nuevo nodo
			this.last = nuevoNodo;
		} else {
			// Cuando la cola esté vacía
			this.first = this.last = nuevoNodo;
		}
	}

	// Método para desencolar
	@Override
	public E dequeue() throws ExceptionIsEmpty {
		// Verifica si la cola no está vacía
		if (!isEmpty()) {
			// 'elemento' almacena el valor que será eliminado
			E elemento = this.first.getData();
			// El nuevo first será su next
			// Si la cola solo tiene un nodo su next será null
			this.first = this.first.getNext();
			return elemento;
		} else {
			// Lanza una excepción si la cola está vacía
			throw new ExceptionIsEmpty("La cola esta vacia");
		}
	}

	// Método que retorna el elemento del frente de la cola
	@Override
	public E front() throws ExceptionIsEmpty {
		// Verifica si la cola no está vacía
		if (!isEmpty()) {
			// Retorna el dato de first
			return this.first.getData();
		} else {
			// Lanza una excepción si la cola está vacía
			throw new ExceptionIsEmpty("La cola esta vacia");
		}
	}

	// Método que retorna el elemento del final de la cola
	@Override
	public E back() throws ExceptionIsEmpty {
		// Verifica si la cola no está vacía
		if (!isEmpty()) {
			// Retorna el dato de last
			return this.last.getData();
		} else {
			// Lanza una excepción si la cola está vacía
			throw new ExceptionIsEmpty("La cola esta vacia");
		}
	}

	// Método para verificar si la cola está vacía
	@Override
	public boolean isEmpty() {
		return this.first == null;
	}

	// Método 'toString()'
	@Override
	public String toString() {
		String Lista = "[";
		Node<E> aux = this.first;
		while (aux != null) {
			Lista += aux.getData();
			if (aux.getNext() != null) {
				Lista += ", ";
			}
			aux = aux.getNext();
		}
		Lista += "]";
		return Lista;
	}
}
