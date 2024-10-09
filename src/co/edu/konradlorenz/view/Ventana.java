package co.edu.konradlorenz.view;

import java.util.Scanner;

public class Ventana {

	private static Scanner leer = new Scanner(System.in);

	public static byte mostrarMenu() {
		System.out.println("- - - - Centro de Videojuegos - - - -");
		System.out.println("- 0. Salir                          -");
		System.out.println("- 1. Asignar jugador a estación     -");
		System.out.println("- 2. Agregar tiempo a jugador       -");
		System.out.println("- 3. Cambiar jugador a estación     -");
		System.out.println("- 4. Registrar salida de jugador    -");
		System.out.println("- 5. Mostrar ingresos totales       -");
		System.out.println("- 6. Agregar estación               -");
		System.out.println("- 7. Mostrar estaciones             -");
		System.out.println("- - - - - - - - - - - - - - - - - - -");
		System.out.println("Ingrese la opción:");
		byte opcion = leer.nextByte();
		leer.nextLine();

		return opcion;
	}

	public static void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}

	public static String pedirString(String info) {
		System.out.println(info+": ");
		String dato = leer.nextLine();
		return dato;
	}

	public static byte mostrarMenuEstaciones() {
		System.out.println("- - - Estaciones de videojuegos - - -");
		System.out.println("- 0. Salir                          -");
		System.out.println("- 1. Crear nueva estación           -");
		System.out.println("- 2. Seleccionar estación           -");
		System.out.println("- - - - - - - - - - - - - - - - - - -");
		System.out.println("Ingrese la opción");
		byte opcion = leer.nextByte();
		leer.nextLine();

		return opcion;
	}
	
}
