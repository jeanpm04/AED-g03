package hash;

public class Empleado implements Comparable<Empleado> {
	private int codigo;
	private String nombre;
	private String direccion;

	public Empleado(int codigo, String nombre, String direccion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.direccion = direccion;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String toString() {
		return "Codigo: " + this.codigo + " Nombre: " + this.nombre + " Direccion: " + this.direccion;
	}

	public int hashCode() {
		return this.codigo;
	}

	public int compareTo(Empleado emp) {
		return this.codigo - emp.codigo;
	}
}
