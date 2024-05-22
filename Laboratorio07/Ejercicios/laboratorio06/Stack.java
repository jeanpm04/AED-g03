package laboratorio06;

public interface Stack<E> {
	void push(E x);
	E pop() throws ExceptionIsEmpty;
	E top() throws ExceptionIsEmpty;
	boolean isEmpty();
	// void eliminarPila() throws ExceptionIsEmpty;
}
