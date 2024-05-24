package avltree;

public class StackLink<E> implements Stack<E> {
	private Node<E> last;
	int tamanio = 0;
	
	public StackLink() {
		this.last = null;
	}
	
	// Método para insertar un nodo
	// al final de la pila
	@Override
	public void push(E x) {
		// Crear un nuevo nodo
		Node<E> nuevoNodo = new Node<E>(x);
		nuevoNodo.setNext(this.last);
		this.last = nuevoNodo;
		tamanio++;
	}
	
	// Método para eliminar el último nodo de la pila
	@Override
	public E pop() throws ExceptionIsEmpty {
		if(!isEmpty()) {
			// 'elemento' guarda el valor del nodo a eliminar
			E elemento = this.last.getData();
			this.last = this.last.getNext();
			return elemento;
		} else {
			throw new ExceptionIsEmpty();
		}
	}
	// Método para retornar el último valor ingresado (tope)
	@Override
	public E top() throws ExceptionIsEmpty {
		if(!isEmpty()) {
			// Retorna el valor del último nodo
			return this.last.getData();
		} else {
			throw new ExceptionIsEmpty();
		}
	}
	
	// Método para retornar el tamanio de la pila
	public int tamanioPila() {
		return tamanio;
	}
	
	// Método para verificar si una pila está vacía
	@Override
	public boolean isEmpty() {
		return this.last == null;
	}
	
	// Método para eliminar la pila
	/*@Override
	public void eliminarPila() throws ExceptionIsEmpty {
		if(!isEmpty()) {
			while(!isEmpty()) {
				pop();
			}
			System.out.println("Pila eliminada");
		} else {
			throw new ExceptionIsEmpty();
		}
	}*/
	
	// Método 'toString()'
	@Override
    public String toString() {
		String Lista = "";
		Node<E> aux = this.last;
		while(aux != null) {
			Lista += aux.getData() + "\n";
			aux = aux.getNext();
		}
		return Lista;
	}
}
