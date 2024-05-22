package seccion6;

public class BSTree<E extends Comparable<E>>{
	
	class Node{
		protected E data;
		protected Node left, rigth;
		public Node right;
		
		public Node(E data) {
			this(data,null,null);
		}
		
		public Node(E data, Node left, Node rigth) {
			this.data = data;
			this.left = left;
			this.rigth =rigth;
		}
	}
	
	private Node root;
	private boolean higth;
	
	public BSTree() {
		this.root = null;
	}
	
	public boolean ismpety() {
		return this.root == null;
	}
	
	//metodo que inserta un elemento al BST
	//si el elemento ya existe en el arbol levanta la excepcion itemduplicated
	public void insert(E x) throws ItemDuplicated {
	    root = insertRec(root, x);
	}

	Node insertRec(Node root, E x) throws ItemDuplicated {
		//caso 1:
	    if (root == null) {
	        root = new Node(x);
	        return root;
	    }
	    
	    //caso 2: 
	    if (x.compareTo(root.data) < 0) {
	        root.left = insertRec(root.left, x);
	    }else if (x.compareTo(root.data) > 0) {
	        root.right = insertRec(root.right, x);
	    } else {
	        throw new ItemDuplicated();//el dato existe 
	    }
	    return root;
	}
	
	//metodo que busca un elemento y retorna su informacion
	//si no existe se levanta la excepcion Itemnofound
	public E search(E x) throws ItemNoFound {
	    Node result = searchRec(root, x);
	    if (result == null) throw new ItemNoFound();
	    return result.data;
	}

	Node searchRec(Node root, E x) {
		// caso1 :
	    if (root==null || root.data.equals(x)) {
	        return root;
	    }
	    //caso2: 
	    if (x.compareTo(root.data) < 0) {
	        return searchRec(root.left, x);
	    }
	    return searchRec(root.rigth, x);
	}

	
	//eliminar:
	public void remove(E x) throws ItemNoFound {
	    root = removeRec(root, x);
	}

	Node removeRec(Node root, E x) throws ItemNoFound {
		//sin econtrar el nodo 
	    if (root == null)  throw new ItemNoFound();

	    if (x.compareTo(root.data) < 0)
	        root.left = removeRec(root.left, x);
	    else if (x.compareTo(root.data) > 0)
	        root.right = removeRec((BSTree<E>.Node) root.right, x);
	    else {
	    	//caso 1: nodo es una hoja termina 
	        if (root.left == null && root.rigth == null) {
	            return null;
	        }
	        
	        // caso 2: nodo tiene un solo hijo
	        if (root.left == null) {
	            return root.rigth;
	        }else if(root.rigth == null) {
	        	return root.left;
	        }
	        
	        //caso3: tiene dos hijos : sucesor inorden vmin der
	        root.data = minRemove(root.right);
	        //eliminamos el sucesor der 
	        root.right = removeRec(root.right, root.data);
	    }
	    return root;
	}
	
	private E minRemove(Node root) {
	    E minv = root.data;
	    while (root.left != null) {
	        minv = root.left.data;
	        root = root.left;
	    }
	    return minv;
	}
	
	public E min() throws ItemNoFound {
	    if (root == null) throw new ItemNoFound();
	    return minRemove(root);
	}
	

	public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringRec(root, sb, 0);
        return sb.toString();
    }

    void toStringRec(Node root, StringBuilder sb, int level) {
        if (root != null) {
            toStringRec(root.left, sb, level + 1);
            sb.append(" ".repeat(level * 2));  // Añade indentación para representar la profundidad del nodo
            sb.append(root.data.toString()).append("\n");
            toStringRec(root.right, sb, level + 1);
        }
    }
}