package Practica4;

/*
 * AUTORES: MIGUEL ALLUE BARON     - NIP: 593599
 * 			CRISTIAN SIMON MORENO  - NIP: 611487
 */
public class ColeccionHash<In> implements Coleccion<String, In> {

	private static final int sizeTabla = 49999;
	ColeccionLista<String, In>[] tabla;
	private int total;
	private int indice;

	/**
	 * Constructor de la clase
	 */
	@SuppressWarnings("unchecked")
	public ColeccionHash() {
		tabla = new ColeccionLista[sizeTabla];
		for (int i = 0; i < sizeTabla; i++) {
			tabla[i] = new ColeccionLista<String, In>();
		}
		total = 0;
		indice = 0;
	}

	/**
	 * Si en la coleccion no habia ningun par con identificador 'identificador',
	 * añade (identificador, informacion) a la coleccion y devuelve true.
	 * 
	 * @return boolean
	 */
	public boolean meter(String identificador, In informacion) {
		boolean mod = false;
		// calculamos la posicion en la tabla
		int claveHash = identificador.hashCode();
		int hash = Math.abs(claveHash % sizeTabla);
		if (tabla[hash].meter(identificador, informacion)) {
			total++;
			mod = true;
		}
		return mod;
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
		return tabla[hash].obtenerInformacion(identificador);
	}

	/**
	 * Si habia un par con identificador 'identificador' en la coleccion, lo
	 * borra y devuelve true
	 * 
	 * @return boolean
	 */
	public boolean borrar(String identificador) {
		boolean mod = false;
		// calculamos la posicion en la tabla
		int claveHash = identificador.hashCode();
		int hash = Math.abs(claveHash % sizeTabla);
		if (tabla[hash].borrar(identificador)) {
			total--;
			mod = true;
		}
		return mod;
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
				// TODO Auto-generated catch block
				e.printStackTrace();
				return listado;
			}
		}
		return listado;
	}

	/**
	 * Prepara el iterador usando una pila que contendra un par con el nodo del
	 * trie<Caracter,informacion> y un String con la concatenacion de los
	 * caracteres
	 */
	public void iniciarIterador() {
		indice = 0;
		tabla[0].iniciarIterador();
	}

	/**
	 * Devuelve falso si no hay elemento siguente, cierto en otro caso.
	 * 
	 * @return boolean
	 */
	public boolean haySiguiente() {
		boolean b = false;
		if (tabla[indice].haySiguiente()) {
			b = true;
		} else {
			while (!b) {
				if (tabla[indice].haySiguiente()) {
					b = true;
				}
				// Comprueba que no haya acabado la tabla y avanza
				else {
					if (indice == tabla.length - 1) {
						return false;
					}
					indice++;
					tabla[indice].iniciarIterador();
				}
			}
		}
		return b;
	}

	/**
	 * Si exite siguiente, devuelve en (identificador, informacion) el siguiente
	 * par de coleccion y avanza el cursor; si no se produce un error.
	 * 
	 * @return Id
	 * @throws NoHaySiguienteException
	 */
	public Par<String, In> siguiente() throws NoHaySiguienteException {
		if (haySiguiente()) {
			return tabla[indice].siguiente();
		} else {
			return null;
		}
	}
}
