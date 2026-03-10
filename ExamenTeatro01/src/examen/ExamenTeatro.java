package examen;

import java.util.Scanner;

public class ExamenTeatro {

	public static void main(String[] args) {
		// Creamos escaner
		Scanner sc = new Scanner(System.in);

		// Creamos Array de la sala
		String sala[][] = new String[6][10];

		for (int i = 0; i < 6; i++) {
		    for (int j = 0; j < 10; j++) {
		        sala[i][j] = "L";
		    }
		}

		// Menu Teatro
		System.out.println("Teatro");
		System.out.println("1. Mostrar sala");
		System.out.println("2. Reservar asiento suelto");
		System.out.println("3. Reservar grupo en una fila");
		System.out.println("4. Confirmar reservas (R -> O)");
		System.out.println("5. Cancelar reservas (R -> L)");
		System.out.println("6. Estadísticas");
		System.out.println("7. Salir");

		int opcion = sc.nextInt();

		// Ejecución de opciones

		switch (opcion) {
		case 1:
			pintarSala(sala);
			break;
		case 2:
			System.out.println("Introduzca la fila:");
			int fila = sc.nextInt();
			System.out.println("Introduzca la columna:");
			int columna = sc.nextInt();
			reservarAsiento(sala, fila, columna);
			break;
		case 3:
			System.out.println("Introduzca la fila:");
			fila = sc.nextInt();
			System.out.println("Introduzca el numero de personas:");
			columna = sc.nextInt();
			reservarGrupoEnFila(sala, fila, columna);
			break;
		case 4:
			confirmarReservas(sala);
			break;
		case 5:
			cancelarReservas(sala);
			break;
		case 6:
			mostrarEstadisticas(sala);
			break;
		case 7:
			System.out.println("Saliendo del programa...");
			break;
		default:
			System.out.println("Opción no válida.");
		}

		sc.close();
	}

	/**
	 * Funcion para mostrar al usuario los asientos y sus estados
	 * 
	 * @param array de sala del teatro
	 * @return Pinta los estados de los asientos en una tabla
	 */
	public static void pintarSala(String[][] sala) {

		for (int i = 0; i < sala.length + 1; i++) {

			

			for (int j = 0; j < sala[i].length + 1; j++) {
				
				System.out.println(" |");
				
				if (i == 0) {
					System.out.println(i + 1);
				} else if (j == 0) {
					System.out.println(j + 1);
				} else {
					System.out.println(sala[i][j]);
				}
				
				System.out.println("| ");

			}

			System.out.println("_");

		}

	}

	/**
	 * Funcion para saber si el asiento esta disponible
	 * 
	 * @param sala     del teatro
	 * @param filas    del teatro
	 * @param columnas del teatro
	 * @return booleano de si es valido
	 */
	public static boolean esPosicionValida(String[][] sala, int filas, int columnas) {
		boolean esValida = true;

		if (sala[filas - 1][columnas - 1].equals('R') || sala[filas - 1][columnas - 1].equals('O')) {
			esValida = false;
		}

		return esValida;
	}

	/**
	 * Funcion para contar el estado de los asientos
	 * 
	 * @param sala
	 * @param estado
	 * @return nos devuelve cuanto asientos hay del estado que preguntemos
	 */
	public static int contarEstado(String[][] sala, String estado) {

		int contadorEstado = 0;

		for (int i = 0; i < sala.length + 1; i++) {

			for (int j = 0; j < sala[i].length + 1; j++) {

				if (sala[i][j].equals(estado.toUpperCase())) {

					contadorEstado += 1;
				}
			}
		}

		return contadorEstado;
	}

	/**
	 * Funcion que nos permite reservar asientos
	 * 
	 * @param sala
	 * @param fila
	 * @param columna
	 * @return nos devuelve true si es posible reservarlo
	 */
	public static boolean reservarAsiento(String[][] sala, int fila, int columna) {

		boolean esPosible = true;

		if (fila < 6 || columna < 10) {
			System.out.print("Esa fila o columna no esta en el rango");
			esPosible = false;

		} else if (esPosicionValida(sala, fila, columna)) {
			sala[fila - 1][columna - 1] = "R";

			System.out.println("Se ha reservado el asiento.");
		} else {
			System.out.println("Ese asiento ya esta reservado.");
			esPosible = false;
		}

		return esPosible;
	}

	/**
	 * Funcion para reservar asientos en un fila
	 * 
	 * @param sala
	 * @param fila
	 * @param numeroPersonas
	 * @return datos de los asientos reservados
	 */
	public static int[] reservarGrupoEnFila(String[][] sala, int fila, int numeroPersonas) {

		int[] datos = new int[2];

		int asientosJuntos = 0;

		for (int i = 0; i < sala.length + 1; i++) {

			for (int j = 0; j < sala[i].length + 1; j++) {

				if (sala[i][j].equals("L")) {

					asientosJuntos += 1;

				} else {

					asientosJuntos = 0;
				}

				if (asientosJuntos == numeroPersonas) {

					for (int posicion = 0; posicion <= numeroPersonas; posicion++) {

						sala[i][j - posicion] = "R";

						datos[0] = j + 1;
						datos[1] = (j + 1) - numeroPersonas;
					}
					break;
				}
			}
		}

		return datos;

	}

	/**
	 * Funcion para confirmar las reservas de los asientos
	 * 
	 * @param sala Cambia las reservas por ocupado
	 */
	public static void confirmarReservas(String[][] sala) {

		for (int i = 0; i < sala.length + 1; i++) {

			for (int j = 0; j < sala[i].length + 1; j++) {

				if (sala[i][j].equals("R")) {

					sala[i][j] = "O";
				}

			}

		}

	}

	/**
	 * Funcion para cancelar las reservas de los asientos
	 * 
	 * @param sala del teatro Cambia las reservas por libre
	 */
	public static void cancelarReservas(String[][] sala) {

		for (int i = 0; i < sala.length + 1; i++) {

			for (int j = 0; j < sala[i].length + 1; j++) {

				if (sala[i][j].equals("R")) {

					sala[i][j] = "L";
				}

			}

		}

	}

	/**
	 * Funcion para mostrar las estadísticas del teatro mostrandonos el estado de
	 * los asientos
	 * 
	 * @param sala
	 */
	public static void mostrarEstadisticas(String[][] sala) {

		System.out.println("Libres: " + contarEstado(sala, "L"));

		System.out.println("Reservados: " + contarEstado(sala, "R"));

		System.out.println("Ocupados: " + contarEstado(sala, "O"));

		System.out.println("Porcentaje de ocupacion: " + ((contarEstado(sala, "O") / 60) * 100.0) + "%");

		System.out.println("Fila más ocupada: " + filaMasOcupada(sala));
	}

	/**
	 * Funcion para calcular cual es la fila mas ocupada
	 * @param sala
	 * @return Devuelve la fila con mas asientos ocupados
	 */
	public static int filaMasOcupada(String[][] sala) {

		int contOcupados = 0;
		int MasOcupados = 0;

		for (int i = 0; i < sala.length + 1; i++) {

			for (int j = 0; j < sala[i].length + 1; j++) {

				if (sala[i][j].equals("O")) {
					contOcupados++;
				}

			}

			if (contOcupados > MasOcupados) {

				MasOcupados = i;
			}
			contOcupados = 0;
		}

		return MasOcupados;
	}

}

