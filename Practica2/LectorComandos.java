package Practica2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

/*
 * AUTORES: MIGUEL ALLUE BARON     - NIP: 593599
 * 			CRISTIAN SIMON MORENO  - NIP: 611487
 */
public class LectorComandos {

	private Coleccion<String, Informacion> estructura = new ColeccionAbb<String, Informacion>();
	// Ficheros de entrada y salida
	private static String FEntrada = "1.txt";
	private static String FSalida = "salida.txt";

	/**
	 * Gestiona la insercion de un usuario
	 * 
	 * @param e
	 * @param s
	 */
	private void insertar(Scanner e, PrintWriter s) {
		// Obtenemos la informacion del fichero
		String CCC = e.next();
		String nombre = e.next();
		String apellido1 = e.next();
		String apellido2 = e.next();
		String NIF = e.next();
		double saldo = e.nextDouble();
		// creamos el objeto Informacion con los datos obtenidos
		Informacion aux = new Informacion(nombre, apellido1, apellido2, NIF,
				saldo);
		// Si no esta
		if (!estructura.esta(CCC)) {
			// añadimos la nueva cuenta bancaria
			estructura.meter(CCC, aux);
			s.println("INSERCI\u00d3N: " + CCC + ";" + aux.getNombre() + ";"
					+ aux.getApellido1() + ";" + aux.getApellido2() + ";"
					+ aux.getNIF() + ";" + aux.getSaldo());

		} else {
			// Si esta
			s.println("INSERCI\u00d3N DESECHADA: " + CCC + ";"
					+ aux.getNombre() + ";" + aux.getApellido1() + ";"
					+ aux.getApellido2() + ";" + aux.getNIF() + ";"
					+ aux.getSaldo());
		}
	}

	/**
	 * Gestiona la modificacion de un usuario
	 * 
	 * @param e
	 * @param s
	 */
	private void modificar(Scanner e, PrintWriter s) {
		// Obtenemos la informacion
		String CCC = e.next();
		double saldo = e.nextDouble();
		// Extrae la informacion
		Informacion aux = estructura.obtenerInformacion(CCC);
		if (aux != null) {
			// Actualiza el nuevo saldo y la añade a la estructura
			aux.setSaldo(saldo);
			estructura.meter(CCC, aux);
			s.println("MODIFICACI\u00d3N: " + CCC + ";" + aux.getSaldo());
		} else {
			// No esta
			s.println("MODIFICACI\u00d3N DESECHADA: " + CCC + ";" + saldo);
		}
	}

	/**
	 * Gestiona la eliminacion de un usuario
	 * 
	 * @param e
	 * @param s
	 */
	private void eliminar(Scanner e, PrintWriter s) {
		String CCC = e.next();
		// Extrae su informacion
		Informacion aux = estructura.obtenerInformacion(CCC);
		// Si esta lo elimina
		if (estructura.borrar(CCC)) {
			s.println("ELIMINACI\u00d3N: " + CCC + ";" + aux.getNombre() + ";"
					+ aux.getApellido1() + ";" + aux.getApellido2() + ";"
					+ aux.getNIF() + ";" + aux.getSaldo());
		} else {
			// No esta
			s.println("ELIMINACI\u00d3N DESECHADA: " + CCC);
		}
	}

	/**
	 * Gestiona la busqueda de un usuario
	 * 
	 * @param e
	 * @param s
	 */
	private void buscar(Scanner e, PrintWriter s) {
		String CCC = e.next();
		// Extrae su informacion
		Informacion aux = estructura.obtenerInformacion(CCC);
		// Comprueba si esta la cuenta
		if (estructura.esta(CCC)) {
			// Si esta
			s.println("B\u00daSQUEDA CON \u00c9XITO: " + CCC + ";"
					+ aux.getNombre() + ";" + aux.getApellido1() + ";"
					+ aux.getApellido2() + ";" + aux.getNIF() + ";"
					+ aux.getSaldo());
		} else {
			// No esta
			s.println("B\u00daSQUEDA SIN \u00c9XITO: " + CCC);
		}
	}
	
	/**
	 * Lista los elementos de 'estructura' 
	 * @param estruc
	 * @param s
	 */
	private void listar(Coleccion<String,Informacion> estruc, PrintWriter s) {
		s.println("Tama\u00f1o de la colecci\u00f3n: "
				+ estructura.tamaño());
		s.printf(estructura.listar());
	}

	/**
	 * Metodo que permite la gestion de un fichero 'nomFichEnt' que contiene
	 * informacion bancaria de clientes, realizando operaciones de insercion,
	 * modificacion, eliminacion, busqueda y listado, mostrando los resultados
	 * en un fichero de salida 'nomFichSal'
	 */
	private void cargarDatosFichero(String nomFichEnt, String nomFichSal) {
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
					insertar(scanFich, salida);
				}

				// Intruccion m - MODIFICAR
				else if (linea.compareToIgnoreCase("m") == 0) {
					modificar(scanFich, salida);
				}

				// Intruccion e - ELIMINAR
				else if (linea.compareToIgnoreCase("e") == 0) {
					eliminar(scanFich, salida);
				}

				// Intruccion b - BUSCAR
				else if (linea.compareToIgnoreCase("b") == 0) {
					buscar(scanFich, salida);
				}

				// Intruccion l - LISTAR
				else if (linea.compareToIgnoreCase("l") == 0) {
					listar(estructura, salida);
				}
			}
			// Cierra el fichero
			salida.close();
		} catch (FileNotFoundException e) {
			System.err.println("Error con los ficheros");

		} catch (IOException e) {
			System.err.println("Error");
		}
	}

	public static void main(String[] args) {
		LectorComandos GF = new LectorComandos();
		GF.cargarDatosFichero(FEntrada, FSalida);
	}

}