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
	private boolean heigth;
	
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
	    if (root == null) {
	        root = new Node(x);
	        return root;
	    }

	    if (x.compareTo(root.data) < 0)
	        root.left = insertRec(root.left, x);
	    else if (x.compareTo(root.data) > 0)
	        root.right = insertRec((BSTree<E>.Node) root.right, x);
	    else 
	        throw new ItemDuplicated();

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
	    if (root==null || root.data==x)
	        return root;

	    if (root.data.compareTo(x) < 0)
	        return searchRec((BSTree<E>.Node) root.right, x);

	    return searchRec(root.left, x);
	}
	
	public String toString() {
	    return toStringRec(root);
	}
	
	public void remove(E x) throws ItemNoFound {
	    root = removeRec(root, x);
	}

	Node removeRec(Node root, E x) throws ItemNoFound {
	    if (root == null)  throw new ItemNoFound();

	    if (x.compareTo(root.data) < 0)
	        root.left = removeRec(root.left, x);
	    else if (x.compareTo(root.data) > 0)
	        root.right = removeRec((BSTree<E>.Node) root.right, x);
	    else {
	        if (root.left == null)
	            return (BSTree<E>.Node) root.right;
	        else if (root.right == null)
	            return root.left;

	        root.data = minRemove((BSTree<E>.Node) root.right);

	        root.right = removeRec((BSTree<E>.Node) root.right, root.data);
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
	

	String toStringRec(Node root) {
	    String result = "";
	    if (root != null) {
	        result += toStringRec(root.left);
	        result += root.data.toString() + " ";
	        result += toStringRec((BSTree<E>.Node) root.right);
	    }
	    return result;
	}
}