package avltree;

import java.util.LinkedList;
import java.util.Queue;

import avltree.BSTree.Node;

public class AVLTree<E extends Comparable<E>> extends BSTree<E> {
	class NodeAVL extends Node {
		protected int bf;

		public NodeAVL(E data) {
			super(data);
			this.bf = 0;
		}

		public String toString() {
			return "Data: " + this.data + ", BF: " + this.bf;
		}

	}

	private boolean height; // indicador de cambio de altura

	public void insert(E x) throws ItemDuplicated {
		this.height = false;
		this.root = insert(x, (NodeAVL) this.root);
	}

	protected Node insert(E x, NodeAVL node) throws ItemDuplicated {
		NodeAVL fat = node;

		if (node == null) {
			this.height = true;
			fat = new NodeAVL(x);
		} else {
			int resC = node.data.compareTo(x);
			if (resC == 0) {
				throw new ItemDuplicated();
			}
			if (resC < 0) {
				fat.right = insert(x, (NodeAVL) node.right);
				if (this.height) {
					switch (fat.bf) {
					case -1:
						fat.bf = 0;
						this.height = false;
						break;
					case 0:
						fat.bf = 1;
						this.height = true;
						break;
					case 1: // bf = 2
						fat = balanceToLeft(fat);
						this.height = false;
						break;
					}
				}
			} else {
				fat.left = insert(x, (NodeAVL) node.left); // Insertar a la izquierda
				if (this.height) {
					switch (fat.bf) {
					case 1:
						fat.bf = 0;
						this.height = false;
						break;
					case 0:
						fat.bf = -1;
						this.height = true;
						break;
					case -1: // bf = -2
						fat = balanceToRight(fat);
						this.height = false;
						break;
					}
				}
			}

		}
		return fat;
	}

	private NodeAVL balanceToLeft(NodeAVL node) {
		NodeAVL hijo = (NodeAVL) node.right;
		switch (hijo.bf) {
		case 1:
			node.bf = 0;
			hijo.bf = 0;
			node = rotateSL(node);
			break;
		case -1:
			NodeAVL nieto = (NodeAVL) hijo.left;
			switch (nieto.bf) {
			case -1:
				node.bf = 0;
				hijo.bf = 1;
				break;
			case 0:
				node.bf = 0;
				hijo.bf = 0;
				break;
			case 1:
				node.bf = 1;
				hijo.bf = 0;
				break;
			}
			nieto.bf = 0;
			node.right = rotateSR(hijo);
			node = rotateSL(node);
		}
		return node;
	}

	private NodeAVL balanceToRight(NodeAVL node) {
		NodeAVL hijo = (NodeAVL) node.left;
		switch (hijo.bf) {
		case -1:
			node.bf = 0;
			hijo.bf = 0;
			node = rotateSR(node);
			break;
		case 1:
			NodeAVL nieto = (NodeAVL) hijo.right;
			switch (nieto.bf) {
			case 1:
				node.bf = 0;
				hijo.bf = -1;
				break;
			case 0:
				node.bf = 0;
				hijo.bf = 0;
				break;
			case -1:
				node.bf = -1;
				hijo.bf = 0;
				break;
			}
			nieto.bf = 0;
			node.left = rotateSL(hijo);
			node = rotateSR(node);
		}
		return node;
	}

	private NodeAVL rotateSL(NodeAVL node) {
		NodeAVL p = (NodeAVL) node.right;
		node.right = p.left;
		p.left = node;
		node = p;
		return node;
	}

	private NodeAVL rotateSR(NodeAVL node) {
		NodeAVL p = (NodeAVL) node.left;
		node.left = p.right;
		p.right = node;
		node = p;
		return node;
	}
	
	////
	protected NodeAVL remove(E x, NodeAVL node) throws ItemNoFound {
	    NodeAVL fat = node;

	    if (node == null) {
	        throw new ItemNoFound(); // Elemento no encontrado
	    } else {
	        int resC = node.data.compareTo(x);
	        if (resC == 0) {
	            // Elemento encontrado, procedemos a eliminarlo
	            if (node.left == null && node.right == null) {
	                // Caso 1: Nodo es una hoja, se elimina directamente
	                fat = null;
	            } else if (node.right == null) {
	                // Caso 2: Nodo tiene solo un hijo a la izquierda
	                fat = (NodeAVL)node.left;
	            } else if (node.left == null) {
	                // Caso 2: Nodo tiene solo un hijo a la derecha
	                fat = (NodeAVL)node.right;
	            } else {
	                // Caso 3: Nodo tiene ambos hijos
	                NodeAVL successor = subEliminacion(node);
	                successor.left = node.left;
	                successor.right = node.right;
	                fat = successor;
	            }
	        } else if (resC < 0) {
	            node.left = remove(x, (NodeAVL)node.left); // Buscar en el subárbol izquierdo
	            if (this.height) {
	                switch (node.bf) {
	                    case 1:
	                        node.bf = 0;
	                        this.height = false;
	                        break;
	                    case 0:
	                        node.bf = -1;
	                        this.height = true;
	                        break;
	                    case -1: // bf = -2
	                        fat = balanceToRight(node);
	                        this.height = false;
	                        break;
	                }
	            }
	        } else {
	            node.right = remove(x, (NodeAVL)node.right); // Buscar en el subárbol derecho
	            if (this.height) {
	                switch (node.bf) {
	                    case -1:
	                        node.bf = 0;
	                        this.height = false;
	                        break;
	                    case 0:
	                        node.bf = 1;
	                        this.height = true;
	                        break;
	                    case 1: // bf = 2
	                        fat = balanceToLeft(node);
	                        this.height = false;
	                        break;
	                }
	            }
	        }
	    }
	    return fat;
	}

    private NodeAVL subEliminacion(NodeAVL nodo) {
        NodeAVL p = nodo;
        NodeAVL aux = (NodeAVL) nodo.right; // Comenzamos desde el subárbol derecho

        // Avanzamos hacia el nodo más a la izquierda en el subárbol derecho
        while (aux.left != null) {
            p = aux;
            aux = (NodeAVL) aux.left;
        }

        // Desconectamos el sucesor de su posición original
        if (p != nodo) {
            p.left = aux.right;
        } else {
            nodo.right = aux.right;
        }

        return aux;
    }
    
    public void recorridoPorAmplitud() {
        if (root == null) {
            System.out.println("El árbol está vacío.");
            return;
        }

        Queue<NodeAVL> queue = new LinkedList<>();
        queue.add((NodeAVL) root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // Tamaño del nivel actual

            for (int i = 0; i < levelSize; i++) {
                NodeAVL current = queue.poll();
                System.out.print(current.data + " ");

                // Agregar los hijos del nodo actual a la cola
                if (current.left != null) {
                    queue.add((NodeAVL) current.left);
                }
                if (current.right != null) {
                    queue.add((NodeAVL) current.right);
                }
            }

            System.out.println(); // Nueva línea para separar niveles
        }
    }

}