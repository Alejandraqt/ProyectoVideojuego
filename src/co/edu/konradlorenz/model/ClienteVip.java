package co.edu.konradlorenz.model;

public interface ClienteVip {

	public static final long DESCUENTO = 500;
	
	public abstract boolean calcularDescuento(int minutos);
	
}
