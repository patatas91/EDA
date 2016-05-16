package Practica2;

import java.util.Random;
import Practica1.ColeccionLista;

/*
 * AUTOR:CRISTIAN SIMON MORENO  - NIP: 611487
 */
public class PruebasBuscar<Id extends Comparable<Id>, In> {

	private static ColeccionAbb<String, Informacion> arbol = new ColeccionAbb<String, Informacion>();
	private static ColeccionLista<String, Informacion> lista = new ColeccionLista<String, Informacion>();
	private static long tiempoInicio;
	private static long totalTiempo;

	/**
	 * Devuelve un numero aleatorio contenido entre min y max
	 * @param max
	 * @param min
	 * @return aleatorio
	 */
	private static int aleatorio(int max, int min) {
		int num = (int) (Math.round(Math.random() * (max - min))) + min;
		return num;
	}
	
	/**
	 * Genera numero cuenta corriente aleatorio
	 * @return String
	 */
	private static String numCuentaAleatorio() {
		String CCC = "";
		for (int z = 0; z < 20; z++) {
			int numero = aleatorio(0, 9);
			CCC = CCC + numero;
		}
		return CCC;
	}

	/**
	 * Genera datos aleatorios para insertarlos en la estrucctura de datos
	 * Lista.
	 * @param n
	 * @param arbol
	 * @param lista
	 */
	private static void informacionAleatoria(int n,
			ColeccionAbb<String, Informacion> arbol,
			ColeccionLista<String, Informacion> lista) {
		// Genera n usuarios
		for (int i = 0; i < n; i++) {
			// Genera numero cuenta corriente aleatorio
			String CCC = numCuentaAleatorio();
			// Nombre(i) y apellidos(i)
			String nombre = "nombre" + i;
			String apellido1 = "ap1" + i;
			String apellido2 = "ap2" + i;
			// Genera NIFs Aleatorios.
			String NIF = "";
			for (int x = 0; x < 8; x++) {
				Random aleatorios = new Random();
				int aleatorio = aleatorios.nextInt(9);
				NIF = NIF + aleatorio;
			}
			int resultado;
			char letra;
			resultado = (int) (Math.random() * 26 + 65);
			letra = (char) resultado;
			NIF = NIF + letra;
			double saldo = Math.random() * 1000;

			Informacion aux = new Informacion(nombre, apellido1, apellido2,
					NIF, saldo);

			// Mete los elementos en las dos estructuras
			arbol.meter(CCC, aux);
			lista.meter(CCC, aux);

		}
	}

	/**
	 * Genera n busquedas sobre la estructura ColeccionLista con el 
	 * identificador <identificador>
	 * @param n
	 * @param identificador
	 */
	private static void buscarLista(int n, String identificador) {
		for (int i = 0; i < n; i++) {
			lista.esta(identificador);
		}
	}

	/**
	 * Genera n busquedas sobre la estructura ColeccionABB con el 
	 * identificador <identificador>
	 * @param n
	 * @param identificador
	 */
	private static void buscarArbol(int n, String identificador) {
		for (int i = 0; i < n; i++) {
			arbol.esta(identificador);
		}
	}
	
	/**
	 * Vacia las estructuras
	 */
	private static void vaciarEstructuras() {
		arbol = new ColeccionAbb<String, Informacion>();
		lista = new ColeccionLista<String, Informacion>();
	}

	public static void main(String[] args) {	
		/*
		 * Bateria para 1000 Elementos
		 */
		// Insercion de datos
		System.out.println("Introduciendo 1000 elementos en colecciones...");
		informacionAleatoria(1000, arbol, lista);
		System.out.println("Prueba 10000 busquedas sobre 1000 elementos.");
		// Calcula el Tiempo -> LISTA
		tiempoInicio = System.currentTimeMillis();
		buscarLista(10000, numCuentaAleatorio());
		totalTiempo = System.currentTimeMillis() - tiempoInicio;
		System.out
				.println("   Tiempo para class tads.colecciones.ColeccionLista: "
						+ totalTiempo + " ms.");
		// Calcula el Tiempo -> ARBOL
		tiempoInicio = System.currentTimeMillis();
		buscarArbol(10000, numCuentaAleatorio());
		totalTiempo = System.currentTimeMillis() - tiempoInicio;
		System.out
				.println("   Tiempo para class tads.colecciones.ColeccionABB: "
						+ totalTiempo + " ms.\n");

		/*
		 * Bateria para 10000 Elementos
		 */
		// Vaciamos las estructuras:
		vaciarEstructuras();
		// Insercion de datos
		System.out.println("Introduciendo 10000 elementos en colecciones...");
		informacionAleatoria(10000, arbol, lista);
		System.out.println("Prueba 10000 busquedas sobre 10000 elementos.");
		// Calcula el Tiempo -> LISTA
		tiempoInicio = System.currentTimeMillis();
		buscarLista(10000, numCuentaAleatorio());
		totalTiempo = System.currentTimeMillis() - tiempoInicio;
		System.out
				.println("   Tiempo para class tads.colecciones.ColeccionLista: "
						+ totalTiempo + " ms.");
		// Calcula el Tiempo -> ARBOL
		tiempoInicio = System.currentTimeMillis();
		buscarArbol(10000, numCuentaAleatorio());
		totalTiempo = System.currentTimeMillis() - tiempoInicio;
		System.out
				.println("   Tiempo para class tads.colecciones.ColeccionABB: "
						+ totalTiempo + " ms.\n");

		/*
		 * Bateria para 100000 Elementos
		 */
		// Vaciamos las estructuras:
		vaciarEstructuras();
		// Insercion de datos
		System.out.println("Introduciendo 100000 elementos en colecciones...");
		informacionAleatoria(100000, arbol, lista);
		System.out.println("Prueba 10000 busquedas sobre 100000 elementos.");
		// Calcula el Tiempo -> LISTA
		tiempoInicio = System.currentTimeMillis();
		buscarLista(10000, numCuentaAleatorio());
		totalTiempo = System.currentTimeMillis() - tiempoInicio;
		System.out
				.println("   Tiempo para class tads.colecciones.ColeccionLista: "
						+ totalTiempo + " ms.");
		// Calcula el Tiempo -> ARBOL
		tiempoInicio = System.currentTimeMillis();
		buscarArbol(10000, numCuentaAleatorio());
		totalTiempo = System.currentTimeMillis() - tiempoInicio;
		System.out
				.println("   Tiempo para class tads.colecciones.ColeccionABB: "
						+ totalTiempo + " ms.\n");
	}
}
