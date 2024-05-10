package actividad1;

class StackArray<E> implements Stack<E> {
	private E[] array;
	// Indica el último elemento ingresado
	private int tope;
	// Indica la capacidad de la pila
	private int capacity;

	public StackArray(int capacity) {
		this.array = (E[]) new Object[capacity];
		this.capacity = capacity;
		// 'tope' se inicializa en -1
		tope = -1;
	}

	// Método para ingresar un elemento a la pila
	@Override
	public void push(E x) {
		// Verifica si la pila no está llena
		if (!isFull()) {
			array[++tope] = x;
		} else {
			System.out.println("La pila esta llena");
		}
	}

	// Método para eliminar el último elemento de la pila
	@Override
	public E pop() throws ExceptionIsEmpty {
		// Verifica si la pila no está vacía
		if (!isEmpty()) {
			// 'elemento' almacena el valor que será eliminado
			E elemento = array[tope];
			array[tope--] = null;
			return elemento;
		} else {
			// Lanza una excepción
			throw new ExceptionIsEmpty();
		}
	}

	// Método para retornar el 'tope'
	// 'tope' es el último elemento ingresado
	@Override
	public E top() throws ExceptionIsEmpty {
		// Verifica si la pila no está vacía
		if (!isEmpty()) {
			return array[tope];
		} else {
			// Lanza una excepción
			throw new ExceptionIsEmpty();
		}
	}

	// Método para verificar si la pila está vacía
	@Override
	public boolean isEmpty() {
		return this.tope == -1;
	}

	// Método para verificar si la pila está llena
	public boolean isFull() {
		return this.tope == capacity - 1;
	}

	// Método 'toString()'
	@Override
	public String toString() {
		String Lista = "";
		for (int i = 0; i <= tope; i++) {
			// Lista = Lista + array[i] + " ";
			Lista += array[i] + " ";
		}
		return Lista;
	}
}
