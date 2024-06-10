package btree;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BTree<Integer> arbolB = new BTree<>(3);
		arbolB.insert(10);
	    arbolB.insert(20);
	    arbolB.insert(5);
	    arbolB.insert(15);
	    arbolB.insert(25);
        System.out.println(arbolB);
        System.out.println(arbolB.search(10));
        arbolB.delete(25);
        System.out.println(arbolB);
        arbolB.delete(15);
        System.out.println(arbolB);
	}
}
