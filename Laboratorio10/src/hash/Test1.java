package hash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Test1 {

	public static void main(String[] args) {
		String file = "Empleado.txt";
		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line;

			//Leer la cantidad de empleados (primer línea del archivo)
			line = br.readLine();
			int n = Integer.parseInt(line.trim());

			System.out.println("Cantidad de empleados: " + n);

			//Crear tabla hash con encadenamiento (hash abierto)
			HashA<Empleado> hashAbierto = new HashA<>(n);

			//Leer cada línea del archivo y crear objetos Empleado
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				int codigo = Integer.parseInt(data[0].trim());
				String nombre = data[1].trim();
				String direccion = data[2].trim();
				Empleado emp = new Empleado(codigo, nombre, direccion);
				hashAbierto.insert(emp.getCodigo(), emp);
				System.out.println(emp);
			}

			//Imprimir estado de la tabla hash después de dispersar todo el archivo
			System.out.println("Estado de tabla hash:");
			System.out.println(hashAbierto);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null)
					fr.close();
				if (br != null)
					br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
