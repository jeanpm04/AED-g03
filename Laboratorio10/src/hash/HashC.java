package hash;

import java.util.ArrayList;

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
	
	public HashC(int n) {
		this.m = n;
		this.table = new ArrayList<Element>(m);
		for(int i=0; i<m; i++)
			this.table.add(new Element(0,null));
	}
	private int functionHash(int key) {
		return key % m;
	}
	private int linearProbing(int dressHash, int key) {
		int i = 1;
		int address = (dressHash + i) % m;
		while(table.get(address).mark == 1 && table.get(address).reg.getKey() != key) {
			i++;
			address = (dressHash + i) % m;
		}
		return address;
	}
	
	//Método del cuadrado: key se eleva al cuadrado
    public int cuadrado(int key){
    	return (int) Math.pow(key, 2) % m;
    }
    
    //Método del pliegue: key se divide en partes iguales,
    //se suman y se obtiene el módulo
    public int pliegue(int key){
    	int sum = 0;
        while (key > 0) {
            sum += key % 100; // Suma de dígitos de dos en dos
            key /= 100;
        }
        return sum % m;
    }
	
	public void insert(int key, E reg) {
		int dressHash = functionHash(key);
		int address = dressHash;
		
		while(table.get(address).mark == 1) {
			if(table.get(address).reg.getKey() == key) {
				System.out.println("El elemento con la clave "+key+" ya existe en la tabla hash");
				//return;
			}
			address = linearProbing(address, key);
			if(address == -1) {
				System.out.println("Tabla hash llena");
				return;
			}
		}
		table.set(address, new Element(1, new Register<E>(key, reg)));
	}
	
	public E search(int key) {
        int hashedAddress = functionHash(key);
        int address = hashedAddress;

        while (table.get(address).mark != 0) {
            if (table.get(address).mark == 1 && table.get(address).reg.getKey() == key) {
                return table.get(address).reg.value;
            }
            address = linearProbing(address, key);
            if (address == hashedAddress) {
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
		}return s;
	}
}
