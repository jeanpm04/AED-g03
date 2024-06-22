package hash;

import java.util.ArrayList;

public class TablaHashSuma {
    private HashA<Integer> hashTable;

    public TablaHashSuma(int n) {
        this.hashTable = new HashA<>(n);
    }

    public void insertar(int key) {
        Integer frecuencia = hashTable.search(key);
        if(frecuencia!=null) {
            hashTable.insert(key, frecuencia + 1);
        }else {
            hashTable.insert(key, 1);
        }
    }
    public boolean buscar(int key) {
        Integer frecuencia = hashTable.search(key);
        return frecuencia!=null && frecuencia > 0;
    }

    public ArrayList<Pair<Integer, Integer>> encontrarPares(int[] lista, int suma) {
        ArrayList<Pair<Integer, Integer>> pares = new ArrayList<>();
        for(int numero : lista) {
            int complemento = suma-numero;
            if(buscar(complemento)) {
                pares.add(new Pair<>(complemento, numero));
            }
            insertar(numero);
        }
        return pares;
    }

    public static void main(String[] args) {
        int[] lista = {1, 2, 3, 4, 5};
        int suma=6;

        TablaHashSuma tablaHashSuma = new TablaHashSuma(10);
        ArrayList<Pair<Integer, Integer>> pares = tablaHashSuma.encontrarPares(lista, suma);

        for(Pair<Integer, Integer> par : pares) {
            System.out.println("(" + par.getKey() + ", " + par.getValue() + ")");
        }
        // Output esperado: (2, 4) y (1, 5)
    }
}
