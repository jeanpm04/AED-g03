package ejercicio1;

public class Test {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Crear pila de enteros
		Stack<Integer> pila1 = new StackLink<>();

		try {
			pila1.pop();
		} catch (ExceptionIsEmpty e) {
			System.out.println(e.getMessage());
		}

		// Agregar nodos a la pila
		pila1.push(5);
		pila1.push(10);
		pila1.push(15);
		pila1.push(20);
		pila1.push(25);
		// pila1.push(30);

		// Mostrar pila
		System.out.print(pila1);

		// Eliminar último nodo
		try {
			Integer elemento = pila1.pop();
			System.out.println("\nElemento eliminado: " + elemento);
		} catch (ExceptionIsEmpty e) {
			System.out.println(e.getMessage());
		}
		System.out.print(pila1);

		// Mostrar el tope
		try {
			System.out.println("\ntope: " + pila1.top());
		} catch (ExceptionIsEmpty e) {
			System.out.println(e.getMessage());
		}

		System.out.print("pila vacia?... " + pila1.isEmpty());

		// Crear pila de String
		Stack<String> pila2 = new StackLink<>();
		// Agregar nodos
		pila2.push("1ro");
		pila2.push("2do");
		pila2.push("3ro");

		// Mostrar pila
		System.out.println();
		System.out.print(pila2);

		// Eliminar último nodo
		try {
			pila2.pop();
		} catch (ExceptionIsEmpty e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
		System.out.print(pila2);

		// Mostrar el tope
		try {
			System.out.println("\ntope: " + pila2.top());
		} catch (ExceptionIsEmpty e) {
			System.out.println(e.getMessage());
		}

		System.out.print("pila vacia?... " + pila2.isEmpty());

		// Crear pila de Double
		Stack<Double> pila3 = new StackLink<>();
		// Agregar nodos a la pila
		pila3.push(5.5);
		pila3.push(10.5);
		pila3.push(15.5);

		// Mostrar pila
		System.out.println();
		System.out.print(pila3);
		
		/*try {
			pila3.eliminarPila();
		} catch (ExceptionIsEmpty e) {
			System.out.println(e.getMessage());
		}
		System.out.print(pila3);
		System.out.print("...");*/
	}
}
