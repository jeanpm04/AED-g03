package seccion6;

public class Main {
    public static void main(String[] args) {
        try {
            // Crear un árbol binario de búsqueda (BST) de enteros
            BSTree<Integer> tree = new BSTree<>();
            
            // Insertar elementos en el árbol
            tree.insert(15);
            tree.insert(10);
            tree.insert(20);
            tree.insert(8);
            tree.insert(12);
            tree.insert(17);
            tree.insert(25);
            
            // Mostrar el árbol
            System.out.println("Árbol después de las inserciones:");
            System.out.println(tree);
            
            // Buscar un elemento en el árbol
            try {
                int searchValue = 10;
                System.out.println("Buscando " + searchValue + ": Encontrado " + tree.search(searchValue));
            } catch (ItemNoFound e) {
                System.out.println("Elemento no encontrado");
            }
            
            // Eliminar un elemento del árbol
            try {
                int deleteValue = 10;
                tree.remove(deleteValue);
                System.out.println("Árbol después de eliminar " + deleteValue + ":");
                System.out.println(tree);
            } catch (ItemNoFound e) {
                System.out.println("Elemento no encontrado para eliminar");
            }
            
            // Mostrar el valor mínimo del árbol
            try {
                System.out.println("Valor mínimo en el árbol: " + tree.min());
            } catch (ItemNoFound e) {
                System.out.println("El árbol está vacío");
            }
            
            //ejercicio1:
            // Contar nodos no-hojas
            int nonLeafNodesCount = tree.countNodes();
            System.out.println("Número de nodos no-hojas en el árbol: " + nonLeafNodesCount);
            
            // Calcular la altura de un nodo
            try {
                int heightNodeValue = 15;
                int height = tree.height(heightNodeValue);
                System.out.println("Altura del nodo " + heightNodeValue + ": " + height);
            } catch (ItemNoFound e) {
                System.out.println("Nodo no encontrado para calcular su altura");
            }
            
            
            
         // Ejercicio 2: 
            //Verificar si dos árboles tienen la misma área
            BSTree<Integer> tree1 = new BSTree<>();
            tree1.insert(10);
            tree1.insert(5);
            tree1.insert(15);
            
            BSTree<Integer> tree2 = new BSTree<>();
            tree2.insert(20);
            tree2.insert(10);
            tree2.insert(30);
            
            boolean sameArea = sameArea(tree1, tree2);
            System.out.println("Los dos árboles tienen la misma área: " + sameArea);
            
            
        } catch (ItemDuplicated e) {
            System.out.println("Elemento duplicado");
        }   
    }
    //ejercicio2
    //verifica si dos árboles binarios tienen la misma área
    public static boolean sameArea(BSTree<?> tree1, BSTree<?> tree2) {
        return tree1.areaBST() == tree2.areaBST();
        }
}
