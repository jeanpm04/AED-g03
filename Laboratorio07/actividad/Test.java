package actividad;

public class Test {

	public static void main(String[] args) throws ItemDuplicated {
		// TODO Auto-generated method stub
		try {
			BSTree<Integer> tree1 = new BSTree<>();
			tree1.insert(7);
			tree1.insert(2);
			tree1.insert(9);
			tree1.insert(1);
			tree1.insert(5);
			tree1.insert(3);
			
			BSTree<Integer> tree2 = new BSTree<>();
			tree2.insert(7);
			tree2.insert(2);
			tree2.insert(9);
			tree2.insert(1);
			tree2.insert(5);
			tree2.insert(3);
			
			System.out.println("Los arboles tienen la misma area?: " + sameArea(tree1, tree2));

		} catch(ItemDuplicated e){
			throw new ItemDuplicated();
		}
	}

	public static <E extends Comparable<E>> boolean sameArea(BSTree<E> arbol1, BSTree<E> arbol2) {
		return arbol1.areaBST() == arbol2.areaBST();
	}

}
