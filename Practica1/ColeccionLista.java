package Practica1;

import Practica4.NoHaySiguienteException;
import Practica4.Par;

/*
 * AUTOR:CRISTIAN SIMON MORENO  - NIP: 611487
 */
public class ColeccionLista<Id extends Comparable<Id>, In> implements
		Coleccion<Id, In> {

	private Celda<Id, In> primera;
	private Celda<Id, In> actual;
	private int total;

	/**
	 * Constructor de la clase
	 */
	public ColeccionLista() {
		primera = null;
		actual = null;
		total = 0;
	}

	/**
	 * Si en la coleccion no habia ningun par con identificador 'identificador',
	 * añade (identificador, informacion) a la coleccion y devuelve true.
	 * 
	 * @return boolean
	 */
	public boolean meter(Id identificador, In informacion) {
		boolean nuevo = false;
		// crea una celda con los datos a meter
		Celda<Id, In> aux = new Celda<Id, In>(identificador, informacion);
		// si esta vacia
		if (esVacia()) {
			primera = aux;
			total = 1;
			nuevo = true;
		} else {
			// insercion al principio
			if (identificador.compareTo(primera.getIdentificador()) < 0) {
				aux.setSiguiente(primera);
				primera = aux;
				total++;
				nuevo = true;
			}
			// busca punto de insercion
			else {
				Celda<Id, In> anterior;
				anterior = primera;
				actual = primera;
				// itera mientras la clave sea menor a la clave
				// del elemento
				while (actual.getSiguiente() != null
						&& (actual.getSiguiente().getIdentificador())
								.compareTo(identificador) <= 0) {
					anterior = actual;
					actual = actual.getSiguiente();
				}
				// la clave es igual a la clave del elemento, cambia el valor
				if (identificador.compareTo(actual.getIdentificador()) == 0) {
					actual.setInformacion(informacion);
				}
				// insercion entre dos registros o al final
				else {
					anterior = actual;
					aux.setSiguiente(anterior.getSiguiente());
					anterior.setSiguiente(aux);
					total++;
					nuevo = true;
				}
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
	public boolean esta(Id identificador) {
		return obtenerInformacion(identificador) != null;
	}

	/**
	 * Dado un identificador 'identificador', devuelve la informacion asociada a
	 * el en la coleccion
	 * 
	 * @return String
	 */
	public In obtenerInformacion(Id identificador) {
		boolean encontrado = false;
		Par<Id, In> aux = null;
		In info = null;
		iniciarIterador();
				
		// itera mientras hay elemento siguiente y no se encuentre
		while (haySiguiente() && !encontrado) {
			try {
				aux = siguiente();
			} catch (NoHaySiguienteException e1) {
				System.out.printf("%s%n", e1.getMessage());
				return null;
			}
			// si es menor
			if(aux.getIdentificador().compareTo(identificador) < 0) {
				return null;
			}
			// si lo encuentra
			else if (aux.getIdentificador().compareTo(identificador) == 0) {
				encontrado = true;
				info = aux.getInformacion();
			}
		}
		
		return info;
	}


	/**
	 * Si habia un par con identificador 'identificador' en la coleccion, lo
	 * borra y devuelve true
	 * 
	 * @return boolean
	 */
	public boolean borrar(Id identificador) {
		boolean borrado = false;
		boolean encontrado = false;
		// Celdas auxiliares
		Celda<Id, In> aux;
		Celda<Id, In> aux2;
		// comprueba que tiene elementos
		if (!esVacia()) {
			// si esta el primero
			if (primera.getIdentificador().compareTo(identificador) == 0) {
				aux = primera;
				primera = primera.getSiguiente();
				borrado = true;
				total--;
			}
			// si no esta el primero y hay mas de un elemento
			else if (primera.getSiguiente() != null) {
				aux = primera.getSiguiente();
				aux2 = primera;
				while (aux != null && !encontrado) {
					// si lo encuentra
					if (aux.getIdentificador().compareTo(identificador) == 0) {
						aux2.setSiguiente(aux.getSiguiente());
						borrado = true;
						total--;
						encontrado = true;
					}
					// si no lo encuentra avanza una posicion
					else {
						aux2 = aux;
						aux = aux.getSiguiente();
					}
				}
			}
		}
		return borrado;
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
			try {
				listado += siguiente().toString() + "\n";
			} catch (NoHaySiguienteException e) {
				System.out.printf("%s%n", e.getMessage());
			}
		}

		return listado;
	}

	/**
	 * Prepara el iterador y su cursor para que el siguiente elemento a visitar
	 * sea el primero.
	 */
	public void iniciarIterador() {
		actual = primera;
	}

	/**
	 * Devuelve falso si ya se ha visitado el ultimo elemento, cierto en otro
	 * caso.
	 * 
	 * @return boolean
	 */
	public boolean haySiguiente() {
		return actual != null;
	}

	/**
	 * Si exite siguiente, devuelve en (identificador, informacion) el siguiente
	 * par de coleccion y avanza el cursor; si no se produce un error.
	 * 
	 * @return Id
	 * @throws NoHaySiguienteException
	 */
	public Par<Id, In> siguiente() throws NoHaySiguienteException {
		// Comprueba si hay siguiente
		if (haySiguiente()) {
			Id identificador = actual.getIdentificador();
			In informacion = actual.getInformacion();
			// avanza una posicion
			actual = actual.siguiente;
			// Crea el Par con los datos a devolver
			Par<Id, In> aux = new Par<Id, In>(identificador, informacion);
			return aux;
		} else {
			throw new NoHaySiguienteException(
					"Error, no hay elemento siguiente");
		}
	}

}
