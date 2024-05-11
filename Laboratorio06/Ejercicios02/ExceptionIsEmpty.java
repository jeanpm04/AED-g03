package ejercicios2;

public class ExceptionIsEmpty extends Exception {
    public ExceptionIsEmpty() {
        super("La cola está vacia");
    }
}