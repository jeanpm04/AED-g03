package btree;

import java.io.IOException;

public class Main1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            BTree<Integer> arbolB = BTree.building_Btree("arbolB.txt");
            System.out.println(arbolB);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
	}

}
