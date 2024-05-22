package actividad;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// Creamos un nuevo árbol binario de búsqueda
			BSTree<Integer> tree = new BSTree<>();

			// Insertamos algunos elementos
			/*tree.insert(50);
			tree.insert(30);
			tree.insert(70);
			tree.insert(20);
			tree.insert(40);
			tree.insert(60);
			tree.insert(80);
			tree.insert(10);*/
			tree.insert(7);
			tree.insert(2);
			tree.insert(9);
			tree.insert(1);
			tree.insert(5);
			tree.insert(3);

			// Mostramos el árbol
			System.out.println("Árbol después de la inserción: ");
			System.out.println(tree);

			// Buscamos un elemento
			int elementToSearch = 9;
			try {
				Integer foundElement = tree.search(elementToSearch);
				System.out.println("El elemento " + elementToSearch + " fue encontrado: " + foundElement);
			} catch (ItemNoFound e) {
				System.out.println("El elemento " + elementToSearch + " no fue encontrado.");
			}

			// Eliminamos un elemento
			int elementToRemove = 2;
			try {
				tree.remove(elementToRemove);
				System.out.println("Elemento " + elementToRemove + " eliminado correctamente.");
			} catch (ItemNoFound e) {
				System.out.println("El elemento " + elementToRemove + " no fue encontrado para eliminar.");
			}

			// Mostramos el árbol después de la eliminación
			System.out.println("Árbol después de eliminar el elemento " + elementToRemove + ": ");
			System.out.println(tree);
			
			try {
			    // Obtener el elemento mínimo sin eliminarlo
			    System.out.println("El elemento mínimo es: " + tree.minRemove());
			} catch (ItemNoFound e) {
			    System.out.println("Error: No se encontró el elemento mínimo.");
			}
			System.out.println(tree);


		} catch (ItemDuplicated e) {
			System.out.println("No se puede insertar un elemento duplicado.");
		}
		
	}

}
