package ejercicio3;

public class Stacklink<E> {
    private Node<E> top;

    public void push(E x) {
        top = new Node<>(x, top);
    }

    public boolean isEmpty() {
        return top == null;
    }

    public E top() {
        if (isEmpty()) {
            return null;
        }
        return top.getData();
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E data = top.getData();
        top = top.getNext();
        return data;
    }

    private static class Node<E> {
        private E data;
        private Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }
    }
}