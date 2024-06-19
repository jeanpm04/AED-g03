package ejercicio6;

import java.util.ArrayList;

//Clase Pair para almacenar un par de objetos de diferentes tipos
class Pair<T, U> {
	private T first;
	private U second;

	public Pair(T first, U second) {
		this.first = first;
		this.second = second;
	}

	public T getFirst() {
		return first;
	}

	public void setFirst(T first) {
		this.first = first;
	}

	public U getSecond() {
		return second;
	}

	public void setSecond(U second) {
		this.second = second;
	}

	@Override
	public String toString() {
		return "(" + first + ", " + second + ")";
	}
}

//Clase TablaHashCoordenadas para manejar coordenadas (x, y) en una tabla hash
public class TablaHashCoordenadas {
	private int m;
	private ArrayList<Pair<Integer, String>>[] table;

	public TablaHashCoordenadas(int n) {
		this.m = n;
		this.table = new ArrayList[m];
		for (int i = 0; i < m; i++) {
			table[i] = new ArrayList<>();
		}
	}

	private int functionHash(int[] key) {
		return (key[0] + key[1]) % m;
	}

	public void insert(int[] key, String reg) {
		int dressHash = functionHash(key);
		ArrayList<Pair<Integer, String>> lista = table[dressHash];
		for (Pair<Integer, String> pair : lista) {
			if (pair.getFirst().equals(key[0]) && pair.getSecond().equals(reg)) {
				return;
			}
		}
		lista.add(new Pair<>(key[0], reg));
	}

	public String search(int[] key) {
		int dressHash = functionHash(key);
		ArrayList<Pair<Integer, String>> lista = table[dressHash];
		for (Pair<Integer, String> pair : lista) {
			if (pair.getFirst().equals(key[0])) {
				return pair.getSecond();
			}
		}
		return null;
	}

	public String eliminar(int[] key) {
		int dressHash = functionHash(key);
		ArrayList<Pair<Integer, String>> lista = table[dressHash];
		for (int i = 0; i < lista.size(); i++) {
			Pair<Integer, String> pair = lista.get(i);
			if (pair.getFirst().equals(key[0])) {
				lista.remove(i);
				return pair.getSecond();
			}
		}
		return null;
	}

	public static void main(String[] args) {
		TablaHashCoordenadas table = new TablaHashCoordenadas(10);
		table.insert(new int[] { 1, 2 }, "valor1");
		table.insert(new int[] { 3, 4 }, "valor2");

		System.out.println(table.search(new int[] { 1, 2 })); // Output: valor1
		System.out.println(table.search(new int[] { 3, 4 })); // Output: valor2

		System.out.println(table.eliminar(new int[] { 1, 2 })); // Output: valor1
		System.out.println(table.search(new int[] { 1, 2 })); // Output: null
	}
}
