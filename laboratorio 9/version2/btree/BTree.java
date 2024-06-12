package btree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BTree<E extends Comparable<E>> {
	private BNode<E> root;
	private int orden;
	private boolean up;
	private BNode<E> nDes;

	public BTree(int orden) {
		this.orden = orden;
		this.root = null;
	}

	public boolean isEmpty() {
		return this.root == null;
	}

	public void insert(E cl) {
		up = false;
		E mediana;
		BNode<E> pnew;
		mediana = push(this.root, cl);
		if (up) {
			pnew = new BNode<E>(this.orden);
			pnew.count = 1;
			pnew.keys.set(0, mediana);
			pnew.childs.set(0, this.root);
			pnew.childs.set(1, nDes);
			this.root = pnew;
		}
	}

	private E push(BNode<E> current, E cl) {
		int pos[] = new int[1];
		E mediana;
		if (current == null) {
			up = true;
			nDes = null;
			return cl;
		} else {
			boolean fl;
			fl = current.searchNode(cl, pos);
			if (fl) {
				System.out.println("Item duplicado\n");
				up = false;
				return null;
			}
			mediana = push(current.childs.get(pos[0]), cl);
			if (up) {
				if (current.nodeFull(this.orden - 1))
					mediana = dividedNode(current, mediana, pos[0]);
				else {
					up = false;
					putNode(current, mediana, nDes, pos[0]);
				}
			}
			return mediana;
		}
	}

	private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
		int i;
		for (i = current.count - 1; i >= k; i--) {
			current.keys.set(i + 1, current.keys.get(i));
			current.childs.set(i + 2, current.childs.get(i + 1));
		}
		current.keys.set(k, cl);
		current.childs.set(k + 1, rd);
		current.count++;
	}

	private E dividedNode(BNode<E> current, E cl, int k) {
		BNode<E> rd = nDes;
		int i, posMdna;
		posMdna = (k <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1;
		nDes = new BNode<E>(this.orden);
		for (i = posMdna; i < this.orden - 1; i++) {
			nDes.keys.set(i - posMdna, current.keys.get(i));
			nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
		}
		nDes.count = (this.orden - 1) - posMdna;
		current.count = posMdna;
		if (k <= this.orden / 2)
			putNode(current, cl, rd, k);
		else
			putNode(nDes, cl, rd, k - posMdna);
		E median = current.keys.get(current.count - 1);
		nDes.childs.set(0, current.childs.get(current.count));
		current.count--;
		return median;
	}

	public String toString() {
		String s = "";
		if (isEmpty())
			s += "BTree is empty...";
		else
			s = writeTree(this.root, null);
		return s;
	}

	private String writeTree(BNode<E> current, BNode<E> parent) {
		if (current == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("Id.Nodo: ").append(current.idNodo).append(", Claves Nodo: ")
				.append(current.keys.subList(0, current.count)).append(", Id.Padre: ")
				.append(parent == null ? "--" : parent.idNodo).append(", Id.Hijos: [");
		boolean firstChild = true;
		for (int i = 0; i <= current.count; i++) {
			if (current.childs.get(i) != null) {
				if (!firstChild)
					sb.append(", ");
				sb.append(current.childs.get(i).idNodo);
				firstChild = false;
			}
		}
		sb.append("]\n");
		for (int i = 0; i <= current.count; i++) {
			if (current.childs.get(i) != null) {
				sb.append(writeTree(current.childs.get(i), current));
			}
		}
		return sb.toString();
	}

	public boolean search(E cl) {
		int[] pos = new int[1];
		BNode<E> nodo = searchNode(root, cl, pos);
		if (nodo != null) {
			System.out.println(cl + " se encuentra en el nodo " + nodo.idNodo + " en la posición " + pos[0]);
			return true;
		} else {
			System.out.println(cl + " no se encuentra en el árbol");
			return false;
		}
	}

	private BNode<E> searchNode(BNode<E> nodo, E cl, int[] pos) {
		if (nodo != null) {
			pos[0] = 0;
			while (pos[0] < nodo.count && cl.compareTo(nodo.keys.get(pos[0])) > 0) {
				pos[0]++;
			}
			if (pos[0] < nodo.count && cl.compareTo(nodo.keys.get(pos[0])) == 0) {
				return nodo; // La clave se encontró en el nodo actual
			} else if (nodo.childs.get(pos[0]) != null) {
				// Si el nodo tiene hijos, buscamos recursivamente en el hijo correspondiente
				return searchNode(nodo.childs.get(pos[0]), cl, pos);
			} else {
				return null; // La clave no se encontró en el nodo ni en sus hijos
			}
		} else {
			return null; // El nodo es nulo, la clave no se encontró en el árbol
		}
	}

	// Método de eliminación
	public void delete(E cl) {
		delete(this.root, cl);
	}

	private void delete(BNode<E> nodo, E cl) {
		if (nodo == null) {
			return;
		}
		int pos[] = new int[1];
		boolean found = nodo.searchNode(cl, pos);
		if (found) {
			if (nodo.childs.get(pos[0]) == null) {
				removeKey(nodo, pos[0]);
			} else {
				getPredecessor(nodo, pos[0]);
			}
		} else {
			delete(nodo.childs.get(pos[0]), cl);
			if (nodo.childs.get(pos[0]) != null && nodo.childs.get(pos[0]).count < (orden - 1) / 2) {
				fix(nodo, pos[0]);
			}
		}
	}

	private void removeKey(BNode<E> nodo, int index) {
		for (int i = index; i < nodo.count - 1; i++) {
			nodo.keys.set(i, nodo.keys.get(i + 1));
		}
		nodo.count--;
	}

	private void getPredecessor(BNode<E> nodo, int pos) {
		BNode<E> predNode = nodo.childs.get(pos);
		E predKey = null;
		while (predNode.childs.get(predNode.count) != null) {
			predNode = predNode.childs.get(predNode.count);
		}
		predKey = predNode.keys.get(predNode.count - 1);
		delete(predNode, predKey);
		nodo.keys.set(pos, predKey);
	}

	private void fix(BNode<E> parent, int index) {
		if (index > 0 && parent.childs.get(index - 1).count > (orden - 1) / 2) {
			borrowFromLeft(parent, index);
		} else if (index < parent.count && parent.childs.get(index + 1).count > (orden - 1) / 2) {
			borrowFromRight(parent, index);
		} else {
			if (index == parent.count) {
				index--;
			}
			merge(parent, index);
		}
	}

	private void borrowFromLeft(BNode<E> parent, int index) {
		BNode<E> leftChild = parent.childs.get(index - 1);
		BNode<E> rightChild = parent.childs.get(index);
		rightChild.count++;
		for (int i = rightChild.count - 1; i > 0; i--) {
			rightChild.keys.set(i, rightChild.keys.get(i - 1));
		}
		rightChild.keys.set(0, parent.keys.get(index - 1));
		parent.keys.set(index - 1, leftChild.keys.get(leftChild.count - 1));
		leftChild.count--;
	}

	private void borrowFromRight(BNode<E> parent, int index) {
		BNode<E> leftChild = parent.childs.get(index);
		BNode<E> rightChild = parent.childs.get(index + 1);
		leftChild.keys.set(leftChild.count, parent.keys.get(index));
		parent.keys.set(index, rightChild.keys.get(0));
		leftChild.count++;
		for (int i = 0; i < rightChild.count - 1; i++) {
			rightChild.keys.set(i, rightChild.keys.get(i + 1));
		}
		rightChild.count--;
	}

	private void merge(BNode<E> nodo, int index) {
		BNode<E> leftChild = nodo.childs.get(index);
		BNode<E> rightChild = nodo.childs.get(index + 1);
		leftChild.keys.set(leftChild.count, nodo.keys.get(index));
		leftChild.count++;
		for (int i = 0; i < rightChild.count; i++) {
			leftChild.keys.set(leftChild.count + i, rightChild.keys.get(i));
		}
		leftChild.count += rightChild.count;
		for (int i = index; i < nodo.count - 1; i++) {
			nodo.keys.set(i, nodo.keys.get(i + 1));
			nodo.childs.set(i + 1, nodo.childs.get(i + 2));
		}
		nodo.count--;
	}

	public static BTree<Integer> building_Btree(String fileName) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		int orden = Integer.parseInt(reader.readLine().trim());
		BTree<Integer> bTree = new BTree<>(orden);

		String line;
		while ((line = reader.readLine()) != null) {
			String[] parts = line.split(",");
			int nivel = Integer.parseInt(parts[0]);
			int idNodo = Integer.parseInt(parts[1]);
			BNode<Integer> node = new BNode<>(orden);
			for (int i = 2; i < parts.length; i++) {
				int key = Integer.parseInt(parts[i].trim());
				node.keys.add(key); // Agregar la clave al nodo
				bTree.insert(key);
			}
		}
		reader.close();
		return bTree;
	}
}