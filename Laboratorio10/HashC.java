package hash;

import java.util.ArrayList;

/*
 * mark-> 0: vacío / 1: ocupado
 * m-> tamaño de tabla
 */

public class HashC<E extends Comparable<E>> {
	protected class Element{
		int mark;
		Register<E> reg;
		public Element(int mark, Register<E> reg) {
			this.mark = mark;
			this.reg = reg;
		}
	}
	protected ArrayList<Element> table;
	protected int m;

	/*
	 * findClosestPrime-> Método para encontrar el número primo más cercano mayor o igual a n
	 * isPrime-> Verificar si un número es primo
	 */
	public HashC(int n) {
		this.m = findClosestPrime(n);
		//this.m = findClosestPrime((int) Math.ceil(n * 1.4));
		//System.out.println(m);
		this.table = new ArrayList<Element>(m);
		for(int i=0; i<m; i++) {
			this.table.add(new Element(0,null));
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
	
	private int quadraticProbing(int dressHash, int key) {
	    int posInit = dressHash;
	    int j=1; //#colision
	    do {
	        Element element = table.get(dressHash);
	        if (element == null || element.mark == 0 ||
	            (element.mark == 1 && element.reg == null)) {
	            return dressHash;
	        }
	        //index = index+(#colision) al cuadrado % TT
	        dressHash = (posInit + j*j) % m;
	        j++;
	    }while(dressHash != posInit);
	    return -1;
	}
    
	private int functionHash(int key) {
		return key % m;
	}
	
    private int hashCuadrado(int key) { // m = 10
        long cuadrado = (long) key * key; // 123456 * 123456 = 15241383936
        String cuadradoStr = Long.toString(cuadrado); // "15241383936"
        int numCifrasM = Integer.toString(m).length(); // 2
        int midLength = cuadradoStr.length() / 2; // 11 / 2 = 5
        int start = Math.max(0, midLength - numCifrasM / 2); // Math.max(0, 5 - 2 / 2) = 4
        int end = Math.min(start + numCifrasM, cuadradoStr.length()); // Math.min(4 + 2, 11) = 6
        String digitosMedios = cuadradoStr.substring(start, end); // "15241383936".substring(4, 6) = "13"
        int dressHash = Integer.parseInt(digitosMedios); // 13
        return Math.abs(dressHash % m); // Math.abs(13 % 10) = 3
    }

    private int hashPliegue(int key) {
        int longitudParte = Integer.toString(m).length(); // m = 10, longitudParte = 2
        String keyStr = Integer.toString(key); // key = 12345, keyStr = "12345"
        int suma = 0;

        for (int i = 0; i < keyStr.length(); i += longitudParte) {
            String parte = keyStr.substring(i, Math.min(i + longitudParte, keyStr.length()));
            /*
             * Iteración 1: parte = "12",
             * Iteración 2: parte = "34",
             * Iteración 3: parte = "5"
             */
            suma += Integer.parseInt(parte); // suma = 12 + 34 + 5 = 51
        }

        return Math.abs(suma % m); // suma = 51, suma % 10 = 1
    }

	/*
	 * linearProbing-> Búsqueda lineal
	 */
	private int linearProbing(int dressHash, int key) {
		int posInit = dressHash;
		do {
			Element element = table.get(dressHash);
			if(element==null || element.mark==0 ||
					(element.mark==1 && element.reg==null)) {
				return dressHash;
			}
			dressHash = (dressHash+1) % m;
		}while(dressHash != posInit);
		return -1;
	}
	
	public void insert(int key, E reg) {
		int dressHash = functionHash(key);
		//int address = linearProbing(dressHash, key);
		int address = quadraticProbing(dressHash, key);
		if(address != -1) {
			table.set(address, new Element(1, new Register<>(key, reg)));
		}else {
			System.out.print("Tabla hash llena\n");
		}
	}
	
	public E search(int key) {
		int dressHash = functionHash(key);
		int address = dressHash;
		int j=1;
		while(table.get(address).mark != 0) {
			if(table.get(address).mark==1 &&
					table.get(address).reg.getKey()==key) {
				return table.get(address).reg.value;
			}
			address = (dressHash+j) % m;
			j++;
			if(address!=dressHash) {
				break;
			}
		}
		return null;
	}
	
	public String toString() {
		String s= "D.Real\tD.Hash\tRegister\n";
		int i=0;
		for(Element item : table) {
			s+= (i++) + " -->\t";
			if(item.mark == 1) {
				s+= functionHash(item.reg.key) + "\t" +item.reg+"\n";
			}else {
				s+= "empty\n";
			}
		}
		return s;
	}
}
