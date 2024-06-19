package hash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Test {
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

			//ArrayList para almacenar los empleados
			ArrayList<Empleado> empleados = new ArrayList<>(n);

			//Leer cada línea del archivo y crear objetos Empleado
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				int codigo = Integer.parseInt(data[0].trim());
				String nombre = data[1].trim();
				String departamento = data[2].trim();
				Empleado emp = new Empleado(codigo, nombre, departamento);
				empleados.add(emp);
				System.out.println(emp);
			}

			//Calcular tamaño de m para HashEmpleado
			int m = n + (int) (n * 0.4);

			//Crear instancia de HashEmpleado
			HashEmpleado<Empleado> hash = new HashEmpleado<>(m);

			//Insertar empleados en hash (HashEmpleado)
			for (Empleado emp : empleados) {
				hash.insert(emp.getCodigo(), emp);
			}

			//Imprimir estado de hash
			System.out.println("Estado de hash:");
			System.out.println(hash);

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
