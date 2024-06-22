package hash;

public class TablaHashCoordenadas {
    private int m;
    private LinkedList<Pair<Pair<Integer, Integer>, String>>[] table;

    @SuppressWarnings("unchecked")
    public TablaHashCoordenadas(int n) {
        this.m = n;
        this.table = new LinkedList[m];
        for (int i = 0; i < m; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int functionHash(int[] key) {
        return (key[0] * 31 + key[1]) % m;
    }

    public void insert(int[] key, String reg) {
        int dressHash = functionHash(key);
        LinkedList<Pair<Pair<Integer, Integer>, String>> lista = table[dressHash];
        for (Node<Pair<Pair<Integer, Integer>, String>> node = lista.getFirst(); node != null; node = node.getNext()) {
            Pair<Pair<Integer, Integer>, String> pair = node.getData();
            if (pair.getKey().equals(new Pair<>(key[0], key[1]))) {
                pair.setValue(reg);
                return;
            }
        }
        lista.insertLast(new Pair<>(new Pair<>(key[0], key[1]), reg));
    }

    public String search(int[] key) {
        int dressHash = functionHash(key);
        LinkedList<Pair<Pair<Integer, Integer>, String>> lista = table[dressHash];
        for (Node<Pair<Pair<Integer, Integer>, String>> node = lista.getFirst(); node != null; node = node.getNext()) {
            Pair<Pair<Integer, Integer>, String> pair = node.getData();
            if (pair.getKey().equals(new Pair<>(key[0], key[1]))) {
                return pair.getValue();
            }
        }
        return null;
    }

    public String eliminar(int[] key) {
        int dressHash = functionHash(key);
        LinkedList<Pair<Pair<Integer, Integer>, String>> lista = table[dressHash];
        for (Node<Pair<Pair<Integer, Integer>, String>> node = lista.getFirst(); node != null; node = node.getNext()) {
            Pair<Pair<Integer, Integer>, String> pair = node.getData();
            if (pair.getKey().equals(new Pair<>(key[0], key[1]))) {
                lista.removeNode(pair);
                return pair.getValue();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        TablaHashCoordenadas table = new TablaHashCoordenadas(10);
        table.insert(new int[]{1, 2}, "valor1");
        table.insert(new int[]{3, 4}, "valor2");
        System.out.println(table.search(new int[]{1, 2})); // Output: valor1
        System.out.println(table.search(new int[]{3, 4})); // Output: valor2
        System.out.println(table.eliminar(new int[]{1, 2})); // Output: valor1
        System.out.println(table.search(new int[]{1, 2})); // Output: null
    }
}
