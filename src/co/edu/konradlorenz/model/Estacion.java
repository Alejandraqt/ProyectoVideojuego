package co.edu.konradlorenz.model;

import java.util.ArrayList;

public class Estacion { 

	private ArrayList<Jugador> listaJugadores = new ArrayList<>();

	public Estacion() {
	}

	public Estacion(ArrayList<Jugador> listaJugadores) {
		this.listaJugadores = listaJugadores;
	}

	/*/ <- Añadir * en medio de las barras para comentar bloque
	public Jugador getListaJugadores(int indice) {
		return listaJugadores.get(indice);
	}
	//*/

	public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

	public void setListaJugadores(Jugador jugador) {
		this.listaJugadores.add(jugador);
	}
	
	public void eliminarJugador(Jugador jugador) {
		this.listaJugadores.remove(jugador);
	}
	
	// <- Añadir * en medio de las barras para comentar bloque
	public Jugador listaJugadores() {
        for (Jugador jugador: listaJugadores) {
            return jugador;
        }
		return null;
    }
	//*/
	

	@Override
	public String toString() {
		return "Estacion [listaJugadores=" + listaJugadores + "]";
	}
	
}
