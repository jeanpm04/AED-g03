package ejercicios2;

public class PriorityQueueLinked<E, P> implements PriorityQueue<E, P> {
    private QueueLink<E>[] queues; // declara  un arreglo de la cola queues
    private int numPriorities;

    @SuppressWarnings("unchecked") // Suprime advertencia sobre cast no comprobado
    public PriorityQueueLinked(int numPriorities) {
        this.numPriorities = numPriorities;
        //inicializa el arreglo de la cola con el num_prioridad
        queues = (QueueLink<E>[]) new QueueLink[numPriorities];
        for (int i = 0; i < numPriorities; i++) {
            queues[i] = new QueueLink<>(); //crea nueva cola enlazada y asignando al arreglo
        }
    }

    @Override
    //insertar
    public void enqueue(E x, P pr) { 
        int priorityIndex = calculatePriorityIndex(pr); //va calcular el indice de la prioridad 
        queues[priorityIndex].enqueue(x); //Agregar el elemento a la cola correspondiente al nivel de prioridad
    }

    @Override
    //eliminar
    public E dequeue() throws ExceptionIsEmpty {
        for (int i = 0; i < numPriorities; i++) {
            if (!queues[i].isEmpty()) { //verifica si la cola en el nivel actual de prioridad no este vacia 
                return queues[i].dequeue(); //elimina el 1er elemento de la cola en este nivel de prioridad
            }
        }
        throw new ExceptionIsEmpty();
    }

    @Override
    // obtener el 1er elemento
    public E front() throws ExceptionIsEmpty {
        for (int i = 0; i < numPriorities; i++) {
            if (!queues[i].isEmpty()) {
                return queues[i].front(); //el 1er elemento de la cola en este nivel de prioridad sin eliminarlo
            }
        }
        throw new ExceptionIsEmpty();
    }

    @Override
    //obtener el ultimo elemento
    public E back() throws ExceptionIsEmpty {
        for (int i = numPriorities - 1; i >= 0; i--) { //comienza desde un nivel mas alto 
            if (!queues[i].isEmpty()) {
                return queues[i].back();  // ultimo elemento 
            }
        }
        throw new ExceptionIsEmpty();
    }

    @Override
    //verifiacra si la cola esta vacia 
    public boolean isEmpty() {
        for (int i = 0; i < numPriorities; i++) {
            if (!queues[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private int calculatePriorityIndex(P priority) {
        // Este método debería calcular el índice de prioridad basado en el valor de 'priority'
        // Implementa la lógica necesaria según tus requisitos
        return 0; // Este es solo un valor de ejemplo
    }
}
