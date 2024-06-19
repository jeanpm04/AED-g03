package hash;

import java.util.ArrayList;

public class HashA<E extends Comparable<E>> {
	private int m;
	private ArrayList<LinkedList<Register<E>>> table;

	public HashA(int n) {
		this.m = n;
		this.table = new ArrayList<>(m);
		for (int i = 0; i < m; i++) {
			table.add(new LinkedList<>());
		}
	}

	private int functionHash(int key) {
		return key % m;
	}

	public void insert(int key, E value) {
		int dressHash = functionHash(key);
		LinkedList<Register<E>> lista = table.get(dressHash);
		lista.insertLast(new Register<>(key, value));
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			sb.append(i).append(" --> ");
			LinkedList<Register<E>> chain = table.get(i);
			if (chain.isEmptyList()) {
				sb.append("empty");
			} else {
				Node<Register<E>> node = chain.getFirst();
				while (node != null) {
					sb.append(node.getData()).append(" -> ");
					node = node.getNext();
				}
				sb.setLength(sb.length() - 4);
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
