package ejercicios2;

public interface PriorityQueue<E, P> {
    void enqueue(E x, P pr);
    E dequeue() throws ExceptionIsEmpty;
    E front() throws ExceptionIsEmpty;
    E back() throws ExceptionIsEmpty;
    boolean isEmpty();
}
