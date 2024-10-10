package co.edu.konradlorenz.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import co.edu.konradlorenz.model.*;
import co.edu.konradlorenz.view.Ventana;

public class Control {
	
	protected EstudianteJugador objEstudianteJugador = new EstudianteJugador();
	protected Estacion objEstacion = new Estacion();
	protected ArrayList<Estacion> listaEstaciones = new ArrayList<Estacion>();

	public void run() {
		byte i = 0;
		do {
			i = Ventana.mostrarMenu();
			
			switch(i) {
				case 0:
					Ventana.mostrarMensaje("Saliendo...");
					break;
				case 1:
					Ventana.mostrarMensaje("--Asignar jugador a estacion--");
					Ventana.mostrarMensaje("Creando nuevo jugador...");
					EstudianteJugador estudianteJugador = crearJugadorConstructor();

					byte key = Ventana.mostrarMenuEstaciones();

					switch (key) {
						case 0:
							Ventana.mostrarMensaje("Saliendo...");	
							break;
						case 1:
							asignarJugadorAEstacion(estudianteJugador, crearNuevaEstacion());
							break;
						case 2:
							if(!listaEstaciones.isEmpty()){
								asignarJugadorAEstacion(estudianteJugador, seleccionarEstacion());
							}else{
								Ventana.mostrarMensaje("No hay estaciones disponibles.");
								asignarJugadorAEstacion(estudianteJugador, crearNuevaEstacion());
							}
							break;
						default:
							Ventana.mostrarMensaje("Opción incorrecta.");
							break;
					}
					break;
				case 2:
					Ventana.mostrarMensaje("--Agregar tiempo a jugador--");
					agregarTiempoAJugador();
					break;
				case 3:
					Ventana.mostrarMensaje("--Cambiar jugador a estación--");
					cambiarJugadorDeEstacion();
					break;
				case 4:
					Ventana.mostrarMensaje("--Registrar salida de jugador--");
					registrarSalidaDeJugador();
					break;
				case 5:
					Ventana.mostrarMensaje("--Mostrar ingresos totales--");
					ingresosTotales();
					break;
				case 6:
					Ventana.mostrarMensaje("--Agregar estación--");
					crearNuevaEstacion();
					break;
				case 7:
					Ventana.mostrarMensaje("--Mostrar estaciones--");
					mostrarListaEstaciones();
					break;
				default:
					Ventana.mostrarMensaje("Opción incorrecta.");
					break;
			}
		}while(i != 0);
	}

	public EstudianteJugador crearJugadorConstructor() {
		String n, f;
		long id,cod;
		n = Ventana.pedirString("Ingrese el nombre");
		id = Long.parseLong(Ventana.pedirString("Ingrese la identificación"));
		f = Ventana.pedirString("Ingrese la fecha"); //Fecha manual
		//f = getActualTime(); //Fecha automática
		cod = Long.parseLong(Ventana.pedirString("Ingrese el código"));

		EstudianteJugador estudianteJugador = new EstudianteJugador(n, id, f, cod);
		return estudianteJugador;
	}

	private Estacion crearNuevaEstacion(){
		Ventana.mostrarMensaje("Creando nueva estación...");
		Estacion nuevaEstacion = new Estacion();
		listaEstaciones.add(nuevaEstacion);
		Ventana.mostrarMensaje("Nueva estación añadida a la lista de estaciones...");
		return nuevaEstacion;
	}

	private Estacion seleccionarEstacion(){
		mostrarListaEstaciones();
		Estacion estacionSeleccionada = listaEstaciones.get(Integer.parseInt(Ventana.pedirString("Seleccione la estación"))-1);
		return estacionSeleccionada;
	}

	private void asignarJugadorAEstacion(Jugador jugador, Estacion estacionSeleccionada) {
		estacionSeleccionada.setListaJugadores(jugador);
		Ventana.mostrarMensaje("El jugador fue asignado con exito");
    }

	private void mostrarListaEstaciones() {
		int i = 1;
		for (Estacion estacion : listaEstaciones) {
			Ventana.mostrarMensaje(i + ". " + estacion.toString());
			i++;
		}
    }

    private void agregarTiempoAJugador() {
    	long idJugador = Long.parseLong(Ventana.pedirString("Ingrese la identificación del jugador para agregar tiempo"));
		boolean found = false;

    	for (Estacion estacion : listaEstaciones) {
			if(found == false){
				for (Jugador jugador : estacion.getListaJugadores()) {

					if (jugador.getId() == idJugador) {
						int tiempoActual = jugador.getTiempo();
						int tiempoNuevo = Integer.parseInt(Ventana.pedirString("Ingrese el tiempo a agregar en minutos"));
						jugador.setTiempo(tiempoActual + tiempoNuevo);
						found = true;
						break;
					}
				}
			}else break;
		}
		String mensaje = found == true ? "Tiempo agregado correctamente.": "El jugador no existe.";
		Ventana.mostrarMensaje(mensaje); 
    }

    
    private void cambiarJugadorDeEstacion() {
        long idJugador = Long.parseLong(Ventana.pedirString("Ingrese la identificación del jugador a cambiar de estación"));
        boolean found = false;

		for (Estacion estacion : listaEstaciones) {
			if(found == false){
				for (Jugador jugador : estacion.getListaJugadores()) {

					if (jugador.getId() == idJugador) {
						asignarJugadorAEstacion(jugador, seleccionarEstacion());
						estacion.eliminarJugador(jugador);
						found = true;
						break;
					}
				}
			}else break;
		}
		String mensaje = found == true ? "El jugador ha sido cambiado de estación.": "El jugador no existe.";
		Ventana.mostrarMensaje(mensaje); 
    }

    private void registrarSalidaDeJugador() {
        long idJugador = Long.parseLong(Ventana.pedirString("Ingrese la identificación del jugador que sale"));
		boolean found = false;

		for (Estacion estacion : listaEstaciones) {
			if(found == false){
				for (Jugador jugador : estacion.getListaJugadores()) {

					if (jugador.getId() == idJugador) {
						estacion.eliminarJugador(jugador);
						found = true;
						break;
					}
				}
			}else break;
		}
		String mensaje = found == true ? "Jugador ha salido de la estación." : "El jugador no existe."; //Fecha manual
		//String mensaje = found == true ? "Jugador ha salido de la estación a las: " + getActualTime() : "El jugador no existe."; //Fecha automática
		Ventana.mostrarMensaje(mensaje);
    }

    private void ingresosTotales() {
    	double total=0;

		for (Estacion estacion : listaEstaciones) {

			for (Jugador jugador : estacion.getListaJugadores()) {
				total += jugador.pagar(jugador.getTiempo());
			}
			break;
		}
    	Ventana.mostrarMensaje("Los ingresos totales fueron: $" + total);
    }
	
	/*/ <- Agregar * entre barras para comentar el método.
	private String getActualTime(){
		LocalDateTime fechaHoraActual = LocalDateTime.now(); //Formato de LocalDate Time: YYYY-MM-DDTHH:MM:SS
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); //Formato más agradable a la vista.
		String actualTime = fechaHoraActual.format(formato);
		return actualTime;
	}
	//*/

}
