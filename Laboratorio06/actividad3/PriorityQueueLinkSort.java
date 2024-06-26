package actividad3;

public class PriorityQueueLinkSort<E, P extends Comparable<P>> implements PriorityQueue<E, P> {
	class EntryNode {
		E data;
		P priority;

		EntryNode(E data, P priority) {
			this.data = data;
			this.priority = priority;
		}
	}

	private Node<EntryNode> first;
	private Node<EntryNode> last;

	public PriorityQueueLinkSort() {
		this.first = null;
		this.last = null;
	}

	// Método que inserta un nodo (x, pr)
	// a la cola según la prioridad
	@Override
	public void enqueue(E x, P pr) {
		// Crea un nuevo nodo con el elemento x y su pr
		EntryNode nodo = new EntryNode(x, pr);
		Node<EntryNode> nuevoNodo = new Node<>(nodo);

		// Verifica si la cola no está vacía
		if (!isEmpty()) {
			// Crea un aux que empieza en first
			// y ant que será un nodo antes de aux (null)
			Node<EntryNode> aux = this.first;
			Node<EntryNode> ant = null;

			// Verifica que aux sea diferente de null y
			// compara que la pr del nuevo nodo sea mayor que la
			// pr del nodo aux (first) en una primera instancia
			while (aux != null && pr.compareTo(aux.getData().priority) > 0) {
				ant = aux;
				aux = aux.getNext();
			}

			// Verifica que aux sea diferente de null y
			// compara que las pr sean iguales
			while (aux != null && pr.compareTo(aux.getData().priority) == 0) {
				ant = aux;
				aux = aux.getNext();
			}

			// Si ant es diferente de null la inserción no se hace
			// al inicio de la cola
			if (ant != null) {
				// nuevoNodo.getNext() = ant.getNext();
				nuevoNodo.setNext(ant.getNext());
				ant.setNext(nuevoNodo);
			} else {
				// La inserción se hace al inicio de la cola
				nuevoNodo.setNext(this.first);
				this.first = nuevoNodo;
			}

			// Actualizar last al nuevo nodo si se inserta al final de la cola
			if (nuevoNodo.getNext() == null) {
				this.last = nuevoNodo;
			}

		} else {
			// Caso especial:
			// Cuando la cola está vacía
			this.first = this.last = nuevoNodo;
		}

	}

	// Método para desencolar
	@Override
	public E dequeue() throws ExceptionIsEmpty {
		// Verifica si la cola no está vacía
		if (!isEmpty()) {
			// 'elemento' almacena el valor que será eliminado
			E elemento = this.first.getData().data;
			// El nuevo first será su next
			// Si la cola solo tiene un nodo su next será null
			this.first = this.first.getNext();
			return elemento;
		} else {
			// Lanza una excepción si la cola está vacía
			throw new ExceptionIsEmpty();
		}
	}

	// Método que retorna el elemento del frente de la cola
	@Override
	public E front() throws ExceptionIsEmpty {
		// Verifica si la cola no está vacía
		if (!isEmpty()) {
			// Retorna el dato de first
			return this.first.getData().data;
		} else {
			// Lanza una excepción si la cola está vacía
			throw new ExceptionIsEmpty();
		}
	}

	// Método que retorna el elemento del final de la cola
	@Override
	public E back() throws ExceptionIsEmpty {
		// Verifica si la cola no está vacía
		if (!isEmpty()) {
			// Retorna el dato de last
			return this.last.getData().data;
		} else {
			// Lanza una excepción si la cola está vacía
			throw new ExceptionIsEmpty();
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
		Node<EntryNode> aux = this.first;
		while (aux != null) {
			Lista += "(" + aux.getData().data + ", " + aux.getData().priority + ")";
			if (aux.getNext() != null) {
				Lista += ", ";
			}
			aux = aux.getNext();
		}
		Lista += "]";
		return Lista;
	}
}
