package hash_normal;

import Practica1.Coleccion;
import Practica1.ColeccionLista;
import Practica1.Informacion;



/*
 * AUTOR:CRISTIAN SIMON MORENO  - NIP: 611487
 */
public class ColeccionHash<In> implements Coleccion<String, In> {
	
	private Coleccion<String, Informacion> lista = new ColeccionLista<String, Informacion>();	
	private static final int sizeTabla = 49999;
	NodoHash<Par<String, In>>[] tabla;
	private int total;
	
	/**
	 * Constructor de la clase
	 */ 	
	@SuppressWarnings("unchecked")
	public ColeccionHash() {		
		tabla = new NodoHash[sizeTabla];
		for (int i=0; i<sizeTabla; i++){
			tabla[i] = null;
		}		
		total = 0;
	}

	/**
	 * Si en la coleccion no habia ningun par con identificador 'identificador',
	 * añade (identificador, informacion) a la coleccion y devuelve true.
	 * 
	 * @return boolean
	 */
	public boolean meter(String identificador, In informacion) {
		boolean nuevo = false;
		Par<String, In> aux = new Par<String, In>(identificador, informacion);
		NodoHash<Par<String, In>> nodo = new NodoHash<Par<String, In>>(aux);
		// calculamos la posicion en la tabla
		int claveHash = identificador.hashCode();
		int hash = Math.abs(claveHash % sizeTabla);
		// Si la posicion esta vacia
		if (tabla[hash] == null) {
			tabla[hash] = nodo;
			tabla[hash].setSiguiente(null);
			total++;
			nuevo = true;
		}
		// Si la posicion ya esta ocupada (COLISION)
		else {
			NodoHash<Par<String, In>> posicion = tabla[hash];
			// recorre la lista comparando las claves
			while (posicion.getElemento().getIdentificador() != identificador
					&& posicion.getSiguiente() != null) {
				posicion = posicion.getSiguiente();
			}
			// si ya esta el valor lo sustituye
			if (identificador.compareTo(posicion.getElemento()
					.getIdentificador()) == 0) {
				posicion.setElemento(aux);
			}
			// si no esta lo añade
			else {
				posicion.setSiguiente(nodo);
				nodo.setSiguiente(null);
				total++;
				nuevo = true;
			}
		}
		return nuevo;
	}

	/**
	 * Devuelve verdad si y solo si en la coleccion hay algun par
	 * (identificador, informacion)
	 * 
	 * @return boolean
	 */
	public boolean esta(String identificador) {
		return obtenerInformacion(identificador) != null;
	}

	/**
	 * Dado un identificador 'identificador', devuelve la informacion asociada a
	 * el en la coleccion
	 * 
	 * @return In
	 */
	public In obtenerInformacion(String identificador) {
		int claveHash = identificador.hashCode();
		int hash = Math.abs(claveHash % sizeTabla);
		boolean encontrado = false;
		In informacion = null;
		try {
			NodoHash<Par<String, In>> aux = tabla[hash];
			while (aux.getSiguiente() != null || !encontrado) {
				if (aux.getElemento().getIdentificador()
						.compareTo(identificador) == 0) {
					informacion = aux.getElemento().getInformacion();
					encontrado = true;
				} else {
					aux = aux.getSiguiente();
				}
			}
			return informacion;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Si habia un par con identificador 'identificador' en la coleccion, lo
	 * borra y devuelve true
	 * 
	 * @return boolean
	 */
	public boolean borrar(String identificador) {		
		// calculamos la posicion en la tabla
		int claveHash = identificador.hashCode();
		int hash = Math.abs(claveHash % sizeTabla);
		boolean encontrado = false;
		// comprueba que tiene elementos
		if (tabla[hash] != null) {
			// si esta el primero
			if ((tabla[hash].getElemento().getIdentificador())
					.compareTo(identificador) == 0) {
				tabla[hash] = tabla[hash].getSiguiente();				
				total--;
			// Lo buscamos
			} else if (tabla[hash].getSiguiente() != null) {
				NodoHash<Par<String, In>> aux = tabla[hash].getSiguiente();
				NodoHash<Par<String, In>> aux2 = tabla[hash];
				while (aux != null && !encontrado) {
					// cuando lo encuentra
					if (aux.getElemento().getIdentificador()
							.compareTo(identificador) == 0) {
						aux2.setSiguiente(aux.getSiguiente());
						encontrado = true;
						total--;
					}
					// si no lo encuentra avanza una posicion
					else {
						aux2 = aux;
						aux = aux.getSiguiente();
					}
				}
			}
		}
		return encontrado;
	}

	/**
	 * Devuelve el numero de pares de la coleccion
	 * 
	 * @return int
	 */
	public int tamaño() {
		return total;
	}

	/**
	 * Devuelve verdad si y solo si la coleccion no tiene ningun par
	 * 
	 * @return boolean
	 */
	public boolean esVacia() {
		return (total == 0);
	}

	/**
	 * Devuelve un String con la información de todos los pares (identificador,
	 * información) almacenados en la colección, separando los pares entre sí
	 * con saltos de línea.
	 * 
	 * @return String con la información de todos los pares (identificador,
	 *         información).
	 */
	public String listar() {
		String listado = "";
		iniciarIterador();
		while (haySiguiente()) {
			listado += siguiente().toString() + "\n";
		}
		return listado;
	}

	/**
	 * Prepara el iterador usando una pila que contendra un par con el nodo del
	 * trie<Caracter,informacion> y un String con la concatenacion de los
	 * caracteres
	 */
	public void iniciarIterador() {
		
	}

	/**
	 * Devuelve falso si no hay elemento siguente, cierto en otro caso.
	 * 
	 * @return boolean
	 */
	public boolean haySiguiente() {
		
	}

	/**
	 * Si exite siguiente, devuelve en (identificador, informacion) el siguiente
	 * par de coleccion y avanza el cursor; si no se produce un error.
	 * 
	 * @return Id
	 */
	public Par<String, In> siguiente() {
		
	}
}
