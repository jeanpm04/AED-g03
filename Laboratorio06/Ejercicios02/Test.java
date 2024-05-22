package ejercicios2;

public class Test {
    public static void main(String[] args) {
        // Crear una cola de prioridad con 3 niveles de prioridad
        PriorityQueue<Integer, Integer> colaPrioridad = new PriorityQueueLinked<>(4);

        try {
            // Agregar elementos a la cola de prioridad
            colaPrioridad.enqueue(10, 1); // baja prioridad
            colaPrioridad.enqueue(26, 1); // baja prioridad
            colaPrioridad.enqueue(13, 1); // baja prioridad
            colaPrioridad.enqueue(35, 1); // baja prioridad
            colaPrioridad.enqueue(22, 3); // media prioridad
            colaPrioridad.enqueue(18, 3); // media prioridad
            colaPrioridad.enqueue(29, 3); // media prioridad
            colaPrioridad.enqueue(12, 3); // alta prioridad
            colaPrioridad.enqueue(34, 3); // alta prioridad

            // Mostrar el elemento al frente de la cola de prioridad
            System.out.println("Elemento al frente: " + colaPrioridad.front());

            // Mostrar el elemento al final de la cola de prioridad
            System.out.println("Elemento al final: " + colaPrioridad.back());

            // Eliminar un elemento de la cola de prioridad
            System.out.println("Desencolando elemento: " + colaPrioridad.dequeue());

            // Verificar si la cola de prioridad está vacía
            System.out.println("¿La cola de prioridad está vacía? " + colaPrioridad.isEmpty());

        } catch (ExceptionIsEmpty e) {
            System.out.println("Excepción: " + e.getMessage());
        }
    }
}