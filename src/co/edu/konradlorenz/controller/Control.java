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

	protected LocalDateTime fechaHoraActual = LocalDateTime.now(); //Formato de LocalDate Time: YYYY-MM-DDTHH:MM:SS
	protected DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); //Formato más agradable a la vista.
	protected String actualTime = fechaHoraActual.format(formato);

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
		f = Ventana.pedirString("Ingrese lA fecha");
		cod = Long.parseLong(Ventana.pedirString("Ingrese el codigo"));

		EstudianteJugador estudianteJugador = new EstudianteJugador(n, id, f, cod);
		return estudianteJugador;

		//objEstacion.setListaJugadores(objEstudianteJugador);
	}

	
	/*/ <- Añadir * en medio de las barras para comentar bloque
	public void crearJugadorSetter() {
		objEstudianteJugador.setNombre(Ventana.pedirString("nombre"));
		objEstudianteJugador.setId(Long.parseLong(Ventana.pedirString("identificación")));
		objEstudianteJugador.setFecha(Ventana.pedirString("fecha"));
		objEstacion.setListaJugadores(objEstudianteJugador);
	}
	// */
	
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

	/*/ <- Añadir * en medio de las barras para comentar bloque
    private void asignarJugadorAEstacion() {
        String nombreJugador = Ventana.pedirString("Ingrese el nombre del jugador a asignar");
        
        if(objEstacion.listaJugadores().getNombre().equals(nombreJugador)) {
        
        	objEstacion.setListaJugadores(objEstudianteJugador);
        	System.out.println("El jugador fue asignado con exito");
            
        }else {
        	System.out.println("El estudiante no existe en la lista.");
        	
        }
        System.out.println(objEstacion.listaJugadores());

    }
	// */

	// <- Añadir * en medio de las barras para comentar bloque
    private void asignarJugadorAEstacion(Jugador jugador, Estacion estacionSeleccionada) {
		estacionSeleccionada.setListaJugadores(jugador);
		Ventana.mostrarMensaje("El jugador fue asignado con exito");
    }
	// */

	// <- Añadir * en medio de las barras para comentar bloque
	private void mostrarListaEstaciones() {
		int i = 1;
		for (Estacion estacion : listaEstaciones) {
			Ventana.mostrarMensaje(i + ". " + estacion.toString());
			i++;
		}
    }
	// */

    private void agregarTiempoAJugador() {
        String nombreJugador = Ventana.pedirString("Ingrese el nombre del jugador para agregar tiempo");
        
        if(objEstacion.listaJugadores().getNombre().equals(nombreJugador)) {
            objEstudianteJugador.setTiempo(Integer.parseInt(Ventana.pedirString("Ingrese el tiempo a agregar en minutos")));
            Ventana.mostrarMensaje("Tiempo agregado correctamente.");
        }else {
        	System.out.println("El estudiante no existe en la lista.");
        	
        } 
        System.out.println(objEstacion.listaJugadores());
    }

    
    private void cambiarJugadorDeEstacion() {
        long idJugador = Long.parseLong(Ventana.pedirString("Ingrese la identificación del jugador a cambiar de estación"));
        
		for (Estacion estacion : listaEstaciones) {

			for (Jugador jugador : estacion.getListaJugadores()) {

				if (jugador.getId() == idJugador) {
					asignarJugadorAEstacion(jugador, seleccionarEstacion());
					estacion.eliminarJugador(jugador);
					break;
            	} else {
					Ventana.mostrarMensaje("El estudiante no existe en la lista.");
					break;
				}
			}
			break;
		}
         
        System.out.println(objEstacion.listaJugadores());
        
    }

    private void registrarSalidaDeJugador() {
        String nombreJugador = Ventana.pedirString("Ingrese el nombre del jugador que sale");
        
        if(objEstacion.listaJugadores().getNombre().equals(nombreJugador)) {
        	objEstacion.eliminarJugador(objEstudianteJugador);
            Ventana.mostrarMensaje("Jugador ha salido de la estación.");
        	
        }else {
        	System.out.println("El estudiante no existe en la lista.");
        	
        } 
        System.out.println(objEstacion.listaJugadores());
    }

    private void ingresosTotales() {
    	double total=0;
    	
    	if(objEstudianteJugador instanceof ClienteVip) {
    		if(objEstudianteJugador.calcularDescuento(objEstudianteJugador.getTiempo())){
        		 total += objEstudianteJugador.pagar(objEstudianteJugador.getTiempo())-ClienteVip.DESCUENTO;
    		}else {
    			total += objEstudianteJugador.pagar(objEstudianteJugador.getTiempo());
    		}
    	}else {
    		total += objEstudianteJugador.pagar(objEstudianteJugador.getTiempo());
    		
    	}
    	Ventana.mostrarMensaje("Los ingresos totales son: $" + total);
    }
	

}
