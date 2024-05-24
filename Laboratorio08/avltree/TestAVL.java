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
		
	
	}
}
