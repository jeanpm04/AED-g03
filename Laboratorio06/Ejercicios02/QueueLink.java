package ejercicios2;

public class QueueLink<E> {
    private Node<E> first; 
    private Node<E> last;  

    public QueueLink() {
        this.first = null;
        this.last = null;
    }

    // el metodo para agregar un elemento de cola
    public void enqueue(E data) {
        Node<E> newNode = new Node<>(data);
        if (isEmpty()) {  
            this.first = newNode; //el 1er nodo sera el nuevo nodo
            this.last = newNode;   
        } else { 
            this.last.setNext(newNode); //el sgte del último nodo sera el nuevo nodo
            this.last = newNode; //nuevo nodo se convierte el ultimo nodo
        }
    }

    //// Metodo para eliminar y devolver el 1er elemento de la cola
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty();
        }
        E data = this.first.getData();  //obtendra el dato del 1er nodo
        this.first = this.first.getNext(); //sgte nodo se convierte en el 1er nuevo nodo
        if (this.first == null) { //despues de eliminar cola esta esta vacia 
            this.last = null;	
        }
        return data; //1er nodo 
    }

     //metodo para obtener el 1er elemento de la cola sin eliminarlo
    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty();
        }
        return this.first.getData();
    }

     //metodo para obtener el ultimo elemento de la cola sin eliminarlo
    public E back() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty();
        }
        return this.last.getData();
    }

    // metodo para verificar si la cola esta vacía
    public boolean isEmpty() {
        return this.first == null;
    }
}

