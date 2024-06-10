package btree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	
	public boolean search(E clave) {
	    int[] pos = new int[1];
	    BNode<E> nodo = searchNode(root, clave, pos);
	    if (nodo != null) {
	        System.out.println(clave + " se encuentra en el nodo " + nodo.idNodo + " en la posición " + pos[0]);
	        return true;
	    } else {
	        System.out.println(clave + " no se encuentra en el árbol");
	        return false;
	    }
	}
	private BNode<E> searchNode(BNode<E> nodo, E clave, int[] pos) {
	    if (nodo != null) {
	        pos[0] = 0;
	        while (pos[0] < nodo.count && clave.compareTo(nodo.keys.get(pos[0])) > 0) {
	            pos[0]++;
	        }
	        if (pos[0] < nodo.count && clave.compareTo(nodo.keys.get(pos[0])) == 0) {
	            return nodo; // La clave se encontró en el nodo actual
	        } else if (nodo.childs.get(pos[0]) != null) {
	            // Si el nodo tiene hijos, buscamos recursivamente en el hijo correspondiente
	            return searchNode(nodo.childs.get(pos[0]), clave, pos);
	        } else {
	            return null; // La clave no se encontró en el nodo ni en sus hijos
	        }
	    } else {
	        return null; // El nodo es nulo, la clave no se encontró en el árbol
	    }
	}
	
	// Método de eliminación 
	public void delete(E key) {
		if(root == null) {
			System.out.println("BTree is empty...");
			return;
		}
		delete(root, key);
		if(root.count == 0) {
			if(root.childs.get(0) == null) {
				root = null;
			} else {
				root = root.childs.get(0);
			}
		}
	}
	
	private boolean delete(BNode<E> node, E key) {
		int pos[] = new int[1];
		boolean found = node.searchNode(key, pos);
		if(found) {
			if(node.childs.get(pos[0]) == null) { // nodo hoja
				removeKey(node, pos[0]);
				return true;
			} else { //nodo intermedio
				E pred = getPredecessor(node, pos[0]);
				node.keys.set(pos[0], pred);
				return delete(node.childs.get(pos[0]), pred); //este pred esta en un nodo hoja
			}
		} else {
			if(node.childs.get(pos[0]) == null) {
				return false; // el dato no existe en el arbol
			} else { //el dato podria estar en el arbol
				boolean isDeleted = delete(node.childs.get(pos[0]), key); //busqueda recursiva
				if(node.childs.get(pos[0]).count < (orden-1)/2) { // quedó con menos de la minima cap aceptable
					fix(node, pos[0]); //arreglar el árbol
				}
				return isDeleted;
			}
		}
	}
	
	private void removeKey(BNode<E> node, int index) {
		for(int i=index; i < node.count-1; i++) {
			node.keys.set(i, node.keys.get(i+1));
		}
		node.keys.set(node.count-1, null);
		node.count--;
	}
	
	private E getPredecessor(BNode<E> node, int index) {
		BNode<E> current = node.childs.get(index);
		while(current.childs.get(current.count) != null) {
			current = current.childs.get(current.count);
		}
		return current.keys.get(current.count-1);
	}
	
	private void fix(BNode<E> parent, int index) {
		if(index > 0 && parent.childs.get(index-1).count > (orden-1)/2) { // tengo un hermano izquierdo
			borrowFromLeft(parent, index); //redistribución con el izquierdo
		} else if (index < parent.count && parent.childs.get(index+1).count > (orden-1)/2) { // tengo un hermano derecho
			borrowFromRight(parent, index); //redistribución con el derecho
		} else {
			if(index > 0) { // tengo un hermano izquierdo
				merge(parent, index-1); // fusión con el izquierdo
			} else { // tengo un hermano derecho
				merge(parent, index); // fusión con el derecho
			}
		}
	}
	
	private void borrowFromLeft(BNode<E> parent, int index) {
		BNode<E> left = parent.childs.get(index-1); //nodo hermano izquierdo
		BNode<E> current = parent.childs.get(index); // nodo derecho
		for(int i=current.count-1; i >= 0; i--) {
			current.keys.set(i+1, current.keys.get(i));
		}
		current.keys.set(0, parent.keys.get(index-1));
		parent.keys.set(index-1, left.keys.get(left.count-1));
		left.keys.set(left.count-1, null);
		if(left.childs.get(left.count) != null) {
			for(int i=current.count; i >=0; i--) {
				current.childs.set(i+1, current.childs.get(i));
			}
			current.childs.set(0, left.childs.get(left.count));
			left.childs.set(left.count, null);
		}
		current.count++;
		left.count--;
	}
	
	private void borrowFromRight(BNode<E> parent, int index) {
		BNode<E> right = parent.childs.get(index+1);
		BNode<E> current = parent.childs.get(index);
		//Movemos la clave del padre al nodo actual
	    current.keys.set(current.count, parent.keys.get(index));
	    current.count++;

	    //Movemos la clave del hermano der al padre
	    parent.keys.set(index, right.keys.get(0));

	    //Si el nodo actual y el hermano derecho tienen hijos, movemos el hijo del hermano der al nodo actual
	    if(right.childs.get(0) != null) {
	        current.childs.set(current.count, right.childs.get(0));
	        right.childs.set(0, null);
	    }

	    //Movemos las claves y los hijos restantes del hermano der
	    for(int i=1; i < right.count; i++) {
	        right.keys.set(i-1, right.keys.get(i));
	        right.childs.set(i-1, right.childs.get(i));
	    }
	    //Movemos el último hijo del hermano der al nodo actual
	    right.childs.set(right.count-1, right.childs.get(right.count));
	    right.childs.set(right.count, null);
	    right.count--;
	}

	private void merge(BNode<E> parent, int index) {
	    BNode<E> left = parent.childs.get(index); //Nodo hermano izquierdo
	    BNode<E> right = parent.childs.get(index+1); //Nodo hermano derecho

	    //Movemos la clave del padre al nodo izq
	    left.keys.set(left.count, parent.keys.get(index));
	    left.count++;

	    //Movemos las claves y los hijos del hermano der al nodo izq
	    for(int i=0; i < right.count; i++) {
	        left.keys.set(left.count+i, right.keys.get(i));
	        left.childs.set(left.count+i, right.childs.get(i));
	    }
	    left.childs.set(left.count + right.count, right.childs.get(right.count));
	    // Actualizamos el contador de claves en el nodo izq
	    left.count += right.count;

	    //Eliminamos la clave del padre y el hijo der
	    for(int i=index+1; i < parent.count; i++) {
	        parent.keys.set(i-1, parent.keys.get(i));
	        parent.childs.set(i, parent.childs.get(i+1));
	    }
	    parent.childs.set(parent.count, null);
	    parent.count--;
	    right=null;
	}

	
	public String toString() {
		String s = "";
		if (isEmpty())
			s += "BTree is empty...";
		else
			s = writeTree(this.root);
		return s;
	}
	
	private String writeTree(BNode<E> current) {
	    StringBuilder cadena = new StringBuilder();
	    if(current != null) {
	        cadena.append("Id.Nodo: ").append(current.idNodo).append(", Claves Nodo: ");
	        for(int i=0; i < current.count; i++) {
	            if(current.keys.get(i) != null) {
	                cadena.append(current.keys.get(i));
	                if(i < current.count-1) {
	                    cadena.append(", ");
	                }
	            }
	        }
	        cadena.append("\n");
	        for(int i=0; i <= current.count; i++) {
	            cadena.append(writeTree(current.childs.get(i)));
	        }
	    }
	    return cadena.toString();
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
                node.keys.add(key); //Agregar la clave al nodo
                bTree.insert(key);
            }
        }
        reader.close();
        return bTree;
    }
}
