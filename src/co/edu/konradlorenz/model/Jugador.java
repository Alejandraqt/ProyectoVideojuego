package co.edu.konradlorenz.model;

public abstract class Jugador implements ClienteVip {
	private String nombre;
	private long id;
	private String fecha;
	private int tiempo;
	
	public Jugador() {
	}

	public Jugador(String nombre, long id, String fecha) {
		this.nombre = nombre;
		this.id = id;
		this.fecha = fecha;
	}

	public Jugador(String nombre, long id, String fecha, int tiempo) {
		this.nombre = nombre;
		this.id = id;
		this.fecha = fecha;
		this.tiempo = tiempo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	
	@Override
	public String toString() {
		return "Jugador [nombre=" + nombre + ", id=" + id + ", fecha=" + fecha + ", tiempo=" + tiempo + "]";
	}

	public abstract long pagar(int minutos);
	
}
