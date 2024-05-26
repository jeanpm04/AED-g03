package avltree;

public class TestAVL {
	public static void main(String[] args) throws ItemDuplicated {
		AVLTree<Integer> avlTree = new AVLTree<>();

		// Caso de prueba 1: Desbalance por la izquierda, requiere rotación simple a la
		// derecha (RSR)
		avlTree.insert(15);
		avlTree.insert(10);
		avlTree.insert(20);
		avlTree.insert(5);
		System.out.println(avlTree);
		avlTree.insert(2);
		System.out.println(avlTree);
		avlTree = new AVLTree<>();
		// Caso de prueba 2: Desbalance por la izquierda, requiere rotación simple a la
		// derecha (RSR)
		avlTree.insert(15);
		avlTree.insert(5);
		avlTree.insert(20);
		avlTree.insert(2);
		avlTree.insert(10);
		System.out.println(avlTree);
		avlTree.insert(1);
		System.out.println(avlTree);
		try {
			avlTree.remove(2);
        } catch (ItemNoFound e) {
            System.out.println("Elemento no encontrado en el árbol AVL: " + e.getMessage());
        }
		System.out.println(avlTree);
		try {
			avlTree.remove(1);
        } catch (ItemNoFound e) {
            System.out.println("Elemento no encontrado en el árbol AVL: " + e.getMessage());
        }
		System.out.println(avlTree);
		System.out.println("Recorrido por amplitud en el árbol AVL:");
        avlTree.recorridoPorAmplitud();
	}
}
