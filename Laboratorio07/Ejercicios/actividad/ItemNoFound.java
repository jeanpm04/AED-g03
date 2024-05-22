package actividad;

public class ItemNoFound extends Exception {
	public ItemNoFound() {
		super("Elemento no se encuentra en el BTS");
	}
}
