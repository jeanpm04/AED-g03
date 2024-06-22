package hash;

public class TestHash {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashC<String> table1 = new HashC<>(11);
		table1.insert(34, "Ana");
		table1.insert(3, "Luis");
		table1.insert(7, "Carlos");
		table1.insert(30, "Maria");
		table1.insert(11, "Jose");
		table1.insert(8, "Elena");
		table1.insert(7, "Pablo");//Clave duplicada
		table1.insert(23, "Laura");
		table1.insert(41, "Miguel");
		table1.insert(16, "Lucia");
		table1.insert(34, "Jorge");//Clave duplicada
		table1.insert(50, "Gian");//Tabla hash llena
		System.out.println(table1);
		System.out.println(table1.search(30));
		System.out.println(table1.search(60));
		
		/*HashA<String> table2 = new HashA<String>(11);
		table2.insert(34, "Ana");
		table2.insert(3, "Luis");
		table2.insert(7, "Carlos");
		table2.insert(30, "Maria");
		table2.insert(11, "Jose");
		table2.insert(8, "Elena");
		table2.insert(7, "Pablo");//Clave duplicada
		table2.insert(23, "Laura");
		table2.insert(41, "Miguel");
		table2.insert(16, "Lucia");
		table2.insert(34, "Jorge");//Clave duplicada
        System.out.println(table2);
        System.out.println(table2.search(30));
        System.out.println(table2.search(60));*/
	}
}
