package Practica1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

/*
 * AUTOR:CRISTIAN SIMON MORENO  - NIP: 611487
 */
public class GestorFicheros {

	private Coleccion<String, Informacion> estructura = new ColeccionLista<String, Informacion>();
	// Ficheros de entrada y salida
	private static String FEntrada = "entrada.txt";
	private static String FSalida = "salida2.txt";

	/**
	 * Metodo que permite la gestion de un fichero 'nomFichEnt' que contiene
	 * informacion bancaria de clientes, realizando operaciones de insercion,
	 * modificacion, eliminacion, busqueda y listado, mostrando los resultados
	 * en un fichero de salida 'nomFichSal'
	 */
	public void cargarDatosFichero(String nomFichEnt, String nomFichSal) {
		Locale.setDefault(Locale.UK);
		Scanner scanFich;
		try {
			// Fichero salida
			FileWriter nuevoF = new FileWriter(nomFichSal);
			PrintWriter salida = new PrintWriter(nuevoF);
			// Fichero entrada
			scanFich = new Scanner(new File(nomFichEnt));
			while (scanFich.hasNext()) {
				String linea = scanFich.nextLine();

				// Intruccion i - INSERTAR
				if (linea.compareToIgnoreCase("i") == 0) {
					// Obtenemos la informacion del fichero
					String CCC = scanFich.next();
					String nombre = scanFich.next();
					String apellido1 = scanFich.next();
					String apellido2 = scanFich.next();
					String NIF = scanFich.next();
					double saldo = scanFich.nextDouble();
					// creamos el objeto Informacion con los datos obtenidos
					Informacion aux = new Informacion(nombre, apellido1,
							apellido2, NIF, saldo);
					// Si no esta
					if (!estructura.esta(CCC)) {
						// añadimos la nueva cuenta bancaria
						estructura.meter(CCC, aux);
						salida.println("INSERCION: " + CCC + ";"
								+ aux.getNombre() + ";" + aux.getApellido1()
								+ ";" + aux.getApellido2() + ";" + aux.getNIF()
								+ ";" + aux.getSaldo());

					} else {
						// Si esta
						salida.println("INSERCION DESECHADA: " + CCC + ";"
								+ aux.getNombre() + ";" + aux.getApellido1()
								+ ";" + aux.getApellido2() + ";" + aux.getNIF()
								+ ";" + aux.getSaldo());
					}
				}

				// Intruccion m - MODIFICAR
				else if (linea.compareToIgnoreCase("m") == 0) {
					// Obtenemos la informacion
					String CCC = scanFich.next();
					double saldo = scanFich.nextDouble();
					// Extrae la informacion
					Informacion aux = estructura.obtenerInformacion(CCC);
					if (aux != null) {
						// Actualiza el nuevo saldo y la añade a la estructura
						aux.setSaldo(saldo);
						estructura.meter(CCC, aux);
						salida.println("MODIFICACION: " + CCC + ";"
								+ aux.getSaldo());
					} else {
						// No esta
						salida.println("MODIFICACION DESECHADA: " + CCC + ";"
								+ saldo);
					}
				}

				// Intruccion e - ELIMINAR
				else if (linea.compareToIgnoreCase("e") == 0) {
					String CCC = scanFich.next();
					// Extrae su informacion
					Informacion aux = estructura.obtenerInformacion(CCC);
					// Si esta lo elimina
					if (estructura.borrar(CCC)) {
						salida.println("ELIMINACION: " + CCC + ";"
								+ aux.getNombre() + ";" + aux.getApellido1()
								+ ";" + aux.getApellido2() + ";" + aux.getNIF()
								+ ";" + aux.getSaldo());
					} else {
						// No esta
						salida.println("ELIMINACION DESECHADA: " + CCC);
					}
				}

				// Intruccion b - BUSCAR
				else if (linea.compareToIgnoreCase("b") == 0) {
					String CCC = scanFich.next();
					// Extrae su informacion
					Informacion aux = estructura.obtenerInformacion(CCC);
					// Comprueba si esta la cuenta
					if (estructura.esta(CCC)) {
						// Si esta
						salida.println("BUSQUEDA CON EXITO: " + CCC + ";"
								+ aux.getNombre() + ";" + aux.getApellido1()
								+ ";" + aux.getApellido2() + ";" + aux.getNIF()
								+ ";" + aux.getSaldo());
					} else {
						// No esta
						salida.println("BUSQUEDA SIN EXITO: " + CCC);
					}
				}

				// Intruccion l - LISTAR
				else if (linea.compareToIgnoreCase("l") == 0) {
					salida.printf(estructura.listar());
				}
			}
			// Cierra el fichero
			salida.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error con los ficheros");

		} catch (IOException e) {
			System.out.println("Error");
		} 
	}

	public static void main(String[] args) {
		GestorFicheros GF = new GestorFicheros();
		GF.cargarDatosFichero(FEntrada, FSalida);
	}

}
