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
            
        } catch (ItemDuplicated e) {
            System.out.println("Elemento duplicado");
        }
    }
}
