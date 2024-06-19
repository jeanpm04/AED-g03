package hash;

public class TestHash {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashC<String> table = new HashC<>(11);
		table.insert(34, "Ana");
		table.insert(3, "Luis");
		table.insert(7, "Carlos");
		table.insert(30, "Maria");
		table.insert(11, "Jose");
		table.insert(8, "Elena");
		table.insert(7, "Pablo"); //clave duplicada
		table.insert(23, "Laura");
		table.insert(41, "Miguel");
		table.insert(16, "Lucia");
		table.insert(34, "Jorge"); //clave duplicada
		System.out.println(table);
		
		HashA<String> hashA = new HashA<String>(11);
		hashA.insert(34, "Ana");
		hashA.insert(3, "Luis");
		hashA.insert(7, "Carlos");
		hashA.insert(30, "Maria");
		hashA.insert(11, "Jose");
		hashA.insert(8, "Elena");
		hashA.insert(7, "Pablo"); //clave duplicada
		hashA.insert(23, "Laura");
		hashA.insert(41, "Miguel");
		hashA.insert(16, "Lucia");
		hashA.insert(34, "Jorge"); //clave duplicada
        System.out.println(hashA);
	}
}
