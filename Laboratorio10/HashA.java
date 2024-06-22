package hash;

import java.util.ArrayList;

public class HashA<E extends Comparable<E>>{
	protected int m;
	protected ArrayList<LinkedList<Register<E>>> table;
	
	public HashA(int n) {
		 this.m = findClosestPrime(n);
		 this.table = new ArrayList<>(m);
		 for (int i = 0; i < m; i++) {
			 table.add(new LinkedList<>());
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
	private int functionHash(int key) {
		 return key % m;
	}
	public void insert(int key, E value) {
		int dressHash = functionHash(key);
		table.get(dressHash).insertLast(new Register<>(key, value));
	}
	public E search(int key) {
	    int dressHash = functionHash(key);	    
	    LinkedList<Register<E>> bucket = table.get(dressHash);
	    Node<Register<E>> current = bucket.getFirst();
	    while(current != null) {
	        if(current.getData().getKey() == key) {
	            return current.getData().getValue();
	        }
	        current = current.getNext();
	    }
	    return null;
	}
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("D.Hash\tRegister\n");
        for(int i=0; i<m; i++) {
            s.append(i).append(" -->\t");
            if (table.get(i).isEmptyList()) {
                s.append("empty\n");
            } else {
                Node<Register<E>> current = table.get(i).getFirst();
                while (current != null) {
                    s.append(current.getData()).append(" ");
                    current = current.getNext();
                }
                s.append("\n");
            }
        }
        return s.toString();
    }
}
