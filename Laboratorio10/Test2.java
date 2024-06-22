package hash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Leer el archivo EMPLEADO.TXT
        try (BufferedReader br = new BufferedReader(new FileReader("EMPLEADO.TXT"))) {
        	// Lee la primera linea
            int numEmpleados = Integer.parseInt(br.readLine().trim());
            // Creamos la tabla hash
            HashA<Empleado> hashTable = new HashA<>(numEmpleados);
            // Lee cada empleado
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int codigo = Integer.parseInt(parts[0].trim());
                String nombre = parts[1].trim();
                String direccion = parts[2].trim();
                Empleado empleado = new Empleado(codigo, nombre, direccion);
                // Insertar cada empleado en la tabla hash
                hashTable.insert(codigo, empleado);
            }
            // Mostrar la tabla hash
            System.out.println(hashTable);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
