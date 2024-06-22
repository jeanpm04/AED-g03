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

    @Override
    public int compareTo(Empleado o) {
        return Integer.compare(this.codigo, o.codigo);
    }

    /*@Override
    public String toString() {
        return codigo + ": " + nombre + ", " + direccion;
    }*/
    @Override
    public String toString() {
        return nombre + ", " + direccion;
    }
}
