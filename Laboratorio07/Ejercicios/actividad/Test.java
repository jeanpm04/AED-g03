package actividad;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			BSTree<Integer> tree = new BSTree<>();
			tree.insert(7);
			tree.insert(2);
			tree.insert(9);
			tree.insert(1);
			tree.insert(5);
			tree.insert(3);
			
			BSTree<Integer> tree1 = new BSTree<>();
			tree1.insert(7);
			tree1.insert(2);
			tree1.insert(9);
			tree1.insert(1);
			tree1.insert(5);
			tree1.insert(3);
			
			System.out.println("¿Los árboles tienen la misma área?: " + sameArea(tree, tree1));

		} catch(ItemDuplicated e){
			System.out.println("No se puede insertar un elemento duplicado.");
		}
	}

	public static <E extends Comparable<E>> boolean sameArea(BSTree<E> arbol1, BSTree<E> arbol2) {
		return arbol1.areaBST() == arbol2.areaBST();
	}

}
