package btree;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BTree<Integer> arbolB = new BTree<>(3);
		// Insertar elementos
		arbolB.insert(10);
		arbolB.insert(20);
		arbolB.insert(5);
		arbolB.insert(15);
		arbolB.insert(25);
		arbolB.insert(30);
		arbolB.insert(35);
		arbolB.insert(40);
		System.out.println(arbolB);

		System.out.println(arbolB.search(10));

		arbolB.delete(40);
		System.out.println(arbolB);
		arbolB.delete(35);
		System.out.println(arbolB);
		arbolB.delete(5);
		System.out.println(arbolB);
	}
}