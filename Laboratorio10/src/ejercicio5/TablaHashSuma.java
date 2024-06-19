package ejercicio5;

import java.util.ArrayList;
import java.util.List;

//Clase Pair para representar pares de valores
class Pair {
	private int first;
	private int second;

	public Pair(int first, int second) {
		this.first = first;
		this.second = second;
	}

	public int getFirst() {
		return first;
	}

	public int getSecond() {
		return second;
	}

	@Override
	public String toString() {
		return "(" + first + ", " + second + ")";
	}
}

//Clase HashC para manejar el hash cerrado
class HashC {
	protected class Element {
		int mark;
		int key;

		public Element(int mark, int key) {
			this.mark = mark;
			this.key = key;
		}
	}

	protected ArrayList<Element> table;
	protected int m;

	public HashC(int n) {
		this.m = n;
		this.table = new ArrayList<>(m);
		for (int i = 0; i < m; i++) {
			this.table.add(new Element(0, -1));
		}
	}

	private int functionHash(int key) {
		return key % m;
	}

	private int linearProbing(int dressHash, int key) {
		int i = 1;
		int address = (dressHash + i) % m;
		while (table.get(address).mark == 1 && table.get(address).key != key) {
			i++;
			address = (dressHash + i) % m;
		}
		return address;
	}

	public void insert(int key) {
		int dressHash = functionHash(key);
		int address = dressHash;

		while (table.get(address).mark == 1) {
			address = linearProbing(address, key);
			if (address == dressHash) {
				System.out.println("Tabla hash llena");
				return;
			}
		}
		table.set(address, new Element(1, key));
	}

	public boolean buscar(int key) {
		int dressHash = functionHash(key);
		int i = 0;
		int address = dressHash;
		while (table.get(address).mark != 0 && i < m) {
			if (table.get(address).mark == 1 && table.get(address).key == key) {
				return true;
			}
			i++;
			address = (dressHash + i * i) % m;
		}
		return false;
	}
}

//Clase TablaHashSuma para encontrar pares que suman un dado valor
class TablaHashSuma {
	private HashC hashTable;

	public TablaHashSuma(int tamaño) {
		hashTable = new HashC(tamaño);
	}

	public void insertar(int key) {
		hashTable.insert(key);
	}

	public boolean buscar(int key) {
		return hashTable.buscar(key);
	}

	public List<Pair> encontrarPares(List<Integer> lista, int suma) {
		List<Pair> pares = new ArrayList<>();
		for (int numero : lista) {
			int complemento = suma - numero;
			if (buscar(complemento)) {
				pares.add(new Pair(complemento, numero));
			}
			insertar(numero);
		}
		return pares;
	}

	public static void main(String[] args) {
		List<Integer> lista = List.of(1, 2, 3, 4, 5);
		int suma = 6;

		TablaHashSuma tabla = new TablaHashSuma(10);
		List<Pair> pares = tabla.encontrarPares(lista, suma);

		System.out.println("Pares que suman " + suma + ": " + pares);
	}
}
