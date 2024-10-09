package co.edu.konradlorenz.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import co.edu.konradlorenz.model.*;
import co.edu.konradlorenz.view.Ventana;

public class Control {
	
	protected EstudianteJugador objEstudianteJugador = new EstudianteJugador();
	protected Estacion objEstacion = new Estacion();

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
					crearJugadorConstructor();
					//asignarJugadorAEstacion();
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
			}
		}while(i != 0);
		
	}

	public void crearJugadorConstructor() {
		String n, f;
		long id,cod;
		n = Ventana.pedirString("Ingrese el nombre");
		id = Long.parseLong(Ventana.pedirString("Ingrese la identificación"));
		f = Ventana.pedirString("Ingrese la fecha");
		cod = Long.parseLong(Ventana.pedirString("Ingrese el código"));

		objEstudianteJugador = new EstudianteJugador(n, id, f, cod); //Se crea el EstudianteJugador
		objEstacion.setListaJugadores(objEstudianteJugador); //Se añadé por primera vez a la estación
	}

	
	/*
	public void crearJugadorSetter() {
		objEstudianteJugador.setNombre(Ventana.pedirString("nombre"));
		objEstudianteJugador.setId(Long.parseLong(Ventana.pedirString("identificación")));
		objEstudianteJugador.setFecha(Ventana.pedirString("fecha"));
		objEstacion.setListaJugadores(objEstudianteJugador);
	}
	*/

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
        String nombreJugador = Ventana.pedirString("Ingrese el nombre del jugador a cambiar de estación");
        
        if(objEstacion.listaJugadores().getNombre().equals(nombreJugador)) {
            objEstacion.eliminarJugador(objEstudianteJugador);
            asignarJugadorAEstacion();
        	
        }else {
        	System.out.println("El estudiante no existe en la lista.");
        	
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
