package btree;

import java.util.ArrayList;

public class BNode<E extends Comparable<E>> {
	protected ArrayList<E> keys;
	protected ArrayList<BNode<E>> childs;
	protected int count;
	protected int idNodo;
	protected static int nro;

	public BNode(int n) {
		this.idNodo = ++nro;
		this.keys = new ArrayList<E>(n);
		this.childs = new ArrayList<BNode<E>>(n + 1);
		this.count = 0;
		for (int i = 0; i < n; i++) {
			this.keys.add(null);
			this.childs.add(null);
		}
	}

	// Check if the current node is full
	public boolean nodeFull(int keys) {
		return count == keys;
	}

	// Check if the current node is empty
	public boolean nodeEmpty() {
		return count == 0;
	}

	// Search for a key in the current node, if found it returns true and
	// the position where it is located, otherwise, returns false and the
	// position of the child where it should descend.
	public boolean searchNode(E key, int[] pos) {
		pos[0] = 0;
		while (pos[0] < count && key.compareTo(keys.get(pos[0])) > 0) {
			pos[0]++;
		}
		if (pos[0] < count && key.compareTo(keys.get(pos[0])) == 0) {
			return true;
		} else {
			return false;
		}
	}

	// Return the keys found in the node.
	public String toString() {
		String cadena = "Nodo " + idNodo + ": ";
		for (int i = 0; i < keys.size(); i++) {
			cadena += keys.get(i);
			if (i < keys.size() - 1) {
				cadena += ", ";
			}
		}
		cadena += "\n";
		return cadena;
	}
}