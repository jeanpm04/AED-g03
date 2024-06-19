package ejercicio4;

import java.util.ArrayList;

public class TablaHashFrecuencia {
	protected class Element {
		int mark;
		Register<String> reg;

		public Element(int mark, Register<String> reg) {
			this.mark = mark;
			this.reg = reg;
		}
	}

	protected ArrayList<Element> table;
	protected int m;

	public TablaHashFrecuencia(int n) {
		this.m = n;
		this.table = new ArrayList<>(m);
		for (int i = 0; i < m; i++)
			this.table.add(new Element(0, null));
	}

	private int functionHash(String key) {
		int hash = 0;
		for (int i = 0; i < key.length(); i++) {
			hash = (hash + key.charAt(i)) % m;
		}
		return hash;
	}

	private int linearProbing(int dressHash, String key) {
		int i = 1;
		int address = (dressHash + i) % m;
		while (table.get(address).mark == 1 && !table.get(address).reg.getKey().equals(key)) {
			i++;
			address = (dressHash + i) % m;
		}
		return address;
	}

	public void insert(String key) {
		int dressHash = functionHash(key);
		int address = dressHash;

		while (table.get(address).mark == 1) {
			if (table.get(address).reg.getKey().equals(key)) {
				//Si la palabra ya existe, incrementamos su contador
				table.get(address).reg.setValue(table.get(address).reg.getValue() + 1);
				return;
			}
			address = linearProbing(address, key);
			if (address == dressHash) {
				System.out.println("Tabla hash llena");
				return;
			}
		}
		//Si la posición está vacía, insertamos la nueva palabra con frecuencia 1
		table.set(address, new Element(1, new Register<>(key, 1)));
	}

	public int frecuencia(String key) {
		int dressHash = functionHash(key);
		int address = dressHash;

		while (table.get(address).mark != 0) {
			if (table.get(address).mark == 1 && table.get(address).reg.getKey().equals(key)) {
				return table.get(address).reg.getValue();
			}
			address = linearProbing(address, key);
			if (address == dressHash) {
				break;
			}
		}
		return 0;
	}

	public String toString() {
		String s = "D.Real\tD.Hash\tRegister\n";
		int i = 0;
		for (Element item : table) {
			s += (i++) + " -->\t";
			if (item.mark == 1) {
				s += functionHash(item.reg.getKey()) + "\t" + item.reg + "\n";
			} else {
				s += "empty\n";
			}
		}
		return s;
	}

	public static void main(String[] args) {
		String texto = "hola mundo hola adios mundo mundo";
		String[] palabras = texto.split("\\s+"); //Separar por espacios en blanco

		TablaHashFrecuencia tabla = new TablaHashFrecuencia(10);

		for (String palabra : palabras) {
			tabla.insert(palabra);
		}

		System.out.println(tabla.frecuencia("hola")); // Output: 2
		System.out.println(tabla.frecuencia("mundo")); // Output: 3
		System.out.println(tabla.frecuencia("adios")); // Output: 1

		//Imprimir estado de la tabla hash después de insertar todas las palabras
		System.out.println("Estado de tabla hash:");
		System.out.println(tabla);
	}
}

class Register<T> {
	private T key;
	private int value;

	public Register(T key, int value) {
		this.key = key;
		this.value = value;
	}

	public T getKey() {
		return key;
	}

	public void setKey(T key) {
		this.key = key;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "(" + key + ", " + value + ")";
	}
}
