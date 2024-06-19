package hash;

import java.util.ArrayList;

public class HashEmpleado<Empleado> {
	class Element {
		public int mark;
		Empleado empleado;

		public Element(int mark, Empleado empleado) {
			this.mark = mark;
			this.empleado = empleado;
		}
	}

	protected ArrayList<Element> table;
	protected int m;

	public HashEmpleado(int n) {
		this.m = n;
		this.table = new ArrayList<>(m);
		for (int i = 0; i < m; i++) {
			this.table.add(new Element(0, null));
		}
	}

	private int functionHash(int key) {
		return key % m;
	}

	//Solución de colisiones
	private int quadraticProbing(ArrayList<Element> table, int hash, int key) {
		int i = 0;
		int j = hash;
		while (table.get(j).mark == 1 && i < this.m) {
			if (table.get(j).mark == 1 && table.get(j).empleado.equals(key)) {
				return -1;
			}
			i++;
			j = (hash + i * i) % m;
		}
		if (i < m) {
			return j;
		} else {
			return -1;
		}
	}

	public void insert(int key, Empleado empleado) {
		int hash = functionHash(key);
		int j = quadraticProbing(this.table, hash, key);
		if (j != -1) {
			this.table.set(j, new Element(1, empleado));
		}
	}

	public Empleado search(int key) {
		int hash = functionHash(key);
		int i = 0;
		int j = hash;
		while (this.table.get(j).mark != 0 && i < this.m) {
			if (this.table.get(j).mark == 1 && this.table.get(j).empleado.equals(key)) {
				return this.table.get(j).empleado;
			}
			i++;
			j = (hash + i * i) % this.m;
		}
		return null;
	}

	public String toString() {
		String s = "D.Real\tD.Hash\tEmpleado\n";
		int i = 0;
		for (Element item : table) {
			s += (i++) + " -->\t";
			if (item.mark == 1) {
				s += functionHash(item.hashCode()) + "\t" + item.empleado + "\n";
			} else {
				s += "Empty\n";
			}
		}
		return s;
	}
}