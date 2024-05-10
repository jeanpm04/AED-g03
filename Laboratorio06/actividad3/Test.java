package actividad3;

public class Test {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PriorityQueue<Integer, Integer> colaPrioridad1 = new PriorityQueueLinkSort<>();
		colaPrioridad1.enqueue(10, 1);
		colaPrioridad1.enqueue(20, 2);
		colaPrioridad1.enqueue(30, 0);
		
		
		System.out.println(colaPrioridad1);
	}

}
