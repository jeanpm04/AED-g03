package actividad2;

public class Test {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Crear una cola de enteros
		Queue<Integer> cola1 = new QueueLink<>();

		// Retorna 'true', ya que no hay nodos en la cola
		System.out.print("cola vacia?... " + cola1.isEmpty());
		System.out.println();

		// Agregar nodos a la cola de enteros
		cola1.enqueue(10);
		cola1.enqueue(20);
		cola1.enqueue(30);

		// Mostrar cola
		System.out.println(cola1);

		try {
			System.out.println(cola1.back()); // Mostrar el último nodo
			System.out.println(cola1.front()); // Mostrar el primer nodo
			System.out.println(cola1.dequeue()); // desencolar
		} catch (ExceptionIsEmpty e) {
			System.out.println(e.getMessage());
		}
		System.out.println(cola1);

		System.out.print("cola vacia?... " + cola1.isEmpty());

		// Crear una cola de String
		Queue<String> cola2 = new QueueLink<>();

		// Agregar nodos a la cola
		cola2.enqueue("1ro");
		cola2.enqueue("2do");
		cola2.enqueue("3ro");

		// Mostrar cola
		System.out.println();
		System.out.println(cola2);

		try {
			System.out.println(cola2.back()); // Mostrar el último nodo
			System.out.println(cola2.front()); // Mostrar el primer nodo
			System.out.println(cola2.dequeue()); // desencolar
		} catch (ExceptionIsEmpty e) {
			System.out.println(e.getMessage());
		}
		System.out.println(cola2); // Mostrar cola

		System.out.print("cola vacia?... " + cola2.isEmpty());

		// Crear cola de Double
		Queue<Double> cola3 = new QueueLink<>();
		// Agregar nodos
		cola3.enqueue(5.5);
		cola3.enqueue(10.5);
		cola3.enqueue(15.5);

		// Mostrar cola
		System.out.println();
		System.out.print(cola3);
		System.out.println();
		System.out.print("cola vacia?... " + cola3.isEmpty());
	}
}
