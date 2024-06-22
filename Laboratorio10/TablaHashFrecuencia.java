package hash;

import java.util.ArrayList;

public class TablaHashFrecuencia {
    private ArrayList<Pair<String, Integer>>[] table;
    private int m;

    public TablaHashFrecuencia(int n) {
        this.m = findClosestPrime(n);
        this.table = new ArrayList[m];
        for(int i=0; i<m; i++) {
            table[i] = new ArrayList<>();
        }
    }
    private int findClosestPrime(int n) {
		while(!isPrime(n)) {
			n++;
		}
		return n;
	}
	private boolean isPrime(int n) {
		if(n<=1) return false;
		if(n==2) return true;
		if(n % 2==0) return false;
		for(int i=3; i<=Math.sqrt(n); i+=2) {
	        if(n % i==0) return false;
	    }
	    return true;
    }
    private int functionHash(String key) {
        int dressHash=0;
        for(int i=0; i<key.length(); i++) {
        	dressHash = (dressHash + key.charAt(i)) % m;
        }
        return dressHash;
    }

    public void insert(String key) {
        int hash = functionHash(key);
        for(Pair<String, Integer> pair : table[hash]) {
            if(pair.getKey().equals(key)) {
                pair.setValue(pair.getValue() + 1);
                return;
            }
        }
        table[hash].add(new Pair<>(key, 1));
    }
    public int search(String key) {
        int hash = functionHash(key);
        for(Pair<String, Integer> pair : table[hash]) {
            if(pair.getKey().equals(key)) {
                return pair.getValue();
            }
        }
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("D.Hash\tRegister\n");
        for (int i=0; i<m; i++) {
            sb.append(i).append(" -->\t");
            if(table[i].isEmpty()) {
                sb.append("empty\n");
            }else {
                for(Pair<String, Integer> pair : table[i]) {
                    sb.append(pair).append(" ");
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String texto = "hola mundo hola adios mundo mundo";
        String[] palabras = texto.split("\\s+"); // Separar por espacios en blanco
        TablaHashFrecuencia tabla = new TablaHashFrecuencia(10);

        for (String palabra : palabras) {
            tabla.insert(palabra);
        }
        System.out.println(tabla.search("hola")); // Output: 2
        System.out.println(tabla.search("mundo")); // Output: 3
        System.out.println(tabla.search("adios")); // Output: 1

        // Imprimir estado de la tabla hash después de insertar todas las palabras
        System.out.println("Estado de tabla hash:");
        System.out.println(tabla);
    }
}
