package co.edu.konradlorenz.model;

public class EstudianteJugador extends Jugador {

	private long codigo;
	
	public EstudianteJugador() {
		super();
	}
	
	public EstudianteJugador(long codigo) {
		super();
		this.codigo = codigo;
	}
	
	public EstudianteJugador(String nombre, long id, String fecha) {
		super(nombre, id, fecha);
	}
	
	public EstudianteJugador(String nombre, long id, String fecha, long codigo) {
		super(nombre, id, fecha);
		this.codigo = codigo;
	}
	
	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	
	@Override
	public String toString() {
		return super.toString()+"EstudianteJugador [codigo=" + codigo + "]";
	}

	@Override
	public long pagar(int minutos) {
		boolean desc = calcularDescuento(minutos);
		if(desc) {
			return 100*minutos-DESCUENTO;
		}else {
			return 100*minutos;
		}
	}

	@Override
	public boolean calcularDescuento(int minutos) {
		if(minutos > 60) {
			return true;
		}else {
			return false;
		}	
	}
	
}
