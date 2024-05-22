package actividad;

public class BSTree<E extends Comparable<E>> {
	class Node {
		protected E data;
		protected Node left, right;

		public Node(E data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}

		public Node(E data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	private Node root;

	public BSTree() {
		this.root = null;
	}

	public boolean isEmpty() {
		return this.root == null;
	}

	// Método que inserta un elemento al BST.
	// Si el elemento ya existe en el árbol levanta la excepción ItemDuplicated
	public void insert(E x) throws ItemDuplicated {
		Node nuevoNodo = new Node(x);
		if (isEmpty()) {
			this.root = nuevoNodo;
		} else {
			Node p = null;
			Node aux = this.root;

			while (aux != null) {
				p = aux;
				if (nuevoNodo.data.compareTo(aux.data) < 0) {
					aux = aux.left;
				} else if (nuevoNodo.data.compareTo(aux.data) > 0) {
					aux = aux.right;
				} else {
					throw new ItemDuplicated();
				}
			}
			if (nuevoNodo.data.compareTo(p.data) > 0) {
				p.right = nuevoNodo;
			} else {
				p.left = nuevoNodo;
			}
		}
	}

	// Método que busca un elemento y retorna su información.
	// Si no existe se levanta la excepción ItemNoFound
	public E search(E x) throws ItemNoFound {
		Node aux = this.root;
		while (aux != null) {
			if (aux.data.compareTo(x) > 0) {
				aux = aux.left;
			} else if (aux.data.compareTo(x) < 0) {
				aux = aux.right;
			} else {
				return aux.data;
			}
		}
		throw new ItemNoFound();
	}

	// Método que elimina un elemento del BST.
	// Si no existe se levanta la excepción ItemNoFound
	public void remove(E x) throws ItemNoFound {
		if (!isEmpty()) {
			boolean isLeftChild = true;
			boolean isRoot = false;
			Node p = null;
			Node aux = this.root;

			// Buscamos el nodo a eliminar
			while (aux != null && aux.data != x) {
				p = aux;
				if (x.compareTo(aux.data) < 0) {
					aux = aux.left;
					isLeftChild = true;
				} else {
					aux = aux.right;
					isLeftChild = false;
				}
			}
			// Si el nodo a eliminar no se encuentra, lanzamos una excepción
			if (aux == null) {
				throw new ItemNoFound();
			}
			/*if (aux == root) {
				 Node nodito = subEliminacion(aux);
				 aux.data = nodito.data;
				 this.root = aux;
			}
			/*if (aux == this.root) { // Si el nodo a eliminar es la raíz
				Node sucesor = subEliminacion(aux);
				if (sucesor != null) {
					sucesor.left = this.root.left;
					sucesor.right = this.root.right;
					this.root = sucesor;
				} else {
					// Si el nodo raíz no tiene hijo derecho
					this.root = this.root.left;
				}
			} else { // Si el nodo a eliminar no es la raíz*/
				// Caso 1: Si se trata de una hoja se elimina directamente
				if (aux.left == null && aux.right == null) {
					if (isLeftChild) {
						p.left = null;
					} else {
						p.right = null;
					}
				}
				// Caso 2: Si tiene un único hijo
				else if (aux.right == null) {
					if (isLeftChild) {
						p.left = aux.left;
					} else {
						p.right = aux.left;
					}
				} else if (aux.left == null) {
					if (isLeftChild) {
						p.left = aux.right;
					} else {
						p.right = aux.right;
					}
				} else {
					// caso 3
					Node nodito = subEliminacion(aux);
					/*
					 * c.data = nodito.data;
					 */

					nodito.left = aux.left;
					nodito.right = aux.right;
					if (isLeftChild) {
						p.left = nodito;
					} else {
						p.right = nodito;
					}

				}
			}
		/*}*/ else {
			// Si el árbol está vacío, lanzamos una excepción
			throw new ItemNoFound();
		}
	}

	private Node subEliminacion(Node nodo) {
		Node p = nodo;
	    Node aux = nodo; // Comenzamos desde el subárbol derecho
	    aux=aux.right;
	    // Avanzamos hacia el nodo más a la izquierda en el subárbol derecho
	    while (aux.left != null) {
	        p = aux;
	        aux = aux.left;
	    }

	    /*// Desconectamos el sucesor de su posición original
	    if (p != nodo) {
	        p.left = aux.right;
	    } else {
	        nodo.right = aux.right;
	    }*/
	    p.left = aux.right;
	    return aux;

	}

	
	public E minRemove() throws ItemNoFound{
		 Node nodito = minRemoveReturn();
		 return nodito.data;
	}
	private Node minRemoveReturn() throws ItemNoFound {
		 if (isEmpty()){
			 throw new ItemNoFound();
		 }
		 Node nodito = root;
		 while (nodito.left != null){
			 nodito = nodito.left;
		 }
		 remove(nodito.data);
		 return nodito;
		 }
	
	// Retorna la cadena que tiene toda la información del BST.
	// Utiliza alguno de los recorridos
	@Override
	public String toString() {
		return toString(this.root);
	}

	// Método recursivo para recorrer el árbol (preorden) y construir la cadena
	private String toString(Node node) {
		if (node == null)
			return "";
		String result = node.data.toString() + " ";
		result += toString(node.left);
		result += toString(node.right);
		return result;
	}

}
