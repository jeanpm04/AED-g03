package actividad3;

public class Test {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PriorityQueue<Integer, Integer> colaPr1 = new PriorityQueueLinkSort<>();
		System.out.print("cola vacia?... " + colaPr1.isEmpty());
		System.out.println();
		colaPr1.enqueue(10, 1);
		colaPr1.enqueue(20, 2);
		colaPr1.enqueue(30, 0);
		colaPr1.enqueue(40, 0);
		colaPr1.enqueue(50, 1);
		colaPr1.enqueue(60, 4);

		System.out.println(colaPr1);

		try {
			System.out.println(colaPr1.back()); // Mostrar el último nodo
			System.out.println(colaPr1.front()); // Mostrar el primer nodo
			System.out.println(colaPr1.dequeue()); // desencolar
		} catch (ExceptionIsEmpty e) {
			System.out.println(e.getMessage());
		}
		System.out.println(colaPr1);

		System.out.print("cola vacia?... " + colaPr1.isEmpty());

		System.out.println();
		System.out.println("-----------------------------------");
		PriorityQueue<String, Integer> colaPr2 = new PriorityQueueLinkSort<>();
		System.out.print("cola vacia?... " + colaPr2.isEmpty());
		System.out.println();
		colaPr2.enqueue("Cliente 1", 1);
		colaPr2.enqueue("No Cliente 1", 4);
		colaPr2.enqueue("Cliente VIP 1", 0);
		colaPr2.enqueue("Cliente VIP 2", 0);
		colaPr2.enqueue("Cliente 2", 1);
		colaPr2.enqueue("No Cliente 2", 2);

		System.out.println(colaPr2);

		try {
			System.out.println(colaPr2.back()); // Mostrar el último nodo
			System.out.println(colaPr2.front()); // Mostrar el primer nodo
			System.out.println(colaPr2.dequeue()); // desencolar
		} catch (ExceptionIsEmpty e) {
			System.out.println(e.getMessage());
		}
		System.out.println(colaPr2);

		System.out.print("cola vacia?... " + colaPr2.isEmpty());

		System.out.println();
		System.out.println("-----------------------------------");
		PriorityQueue<Double, Integer> colaPr3 = new PriorityQueueLinkSort<>();
		System.out.print("cola vacia?... " + colaPr3.isEmpty());
		System.out.println();
		colaPr3.enqueue(10.5, 1);
		colaPr3.enqueue(20.5, 2);
		colaPr3.enqueue(30.5, 0);
		colaPr3.enqueue(40.5, 0);
		colaPr3.enqueue(50.5, 1);
		colaPr3.enqueue(60.5, 4);

		System.out.println(colaPr3);
	}
}
