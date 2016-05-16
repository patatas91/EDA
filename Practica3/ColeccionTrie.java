package Practica3;

/*
 * AUTORES: MIGUEL ALLUE BARON     - NIP: 593599
 * 			CRISTIAN SIMON MORENO  - NIP: 611487
 */
public class ColeccionTrie<In> implements Coleccion<String, In> {

	private Pila<Par<NodoTrie<String, In>, String>> pilaArbol = new PilaDinamica<Par<NodoTrie<String, In>, String>>();
	private NodoTrie<String, In> raiz;
	private int total;

	/**
	 * Constructor de la clase
	 */
	public ColeccionTrie() {
		raiz = null;
		total = 0;
	}

	/**
	 * Planta una palabra en el arbol
	 * 
	 * @param aux
	 * @return NodoTrie<String, In>
	 */
	private NodoTrie<String, In> plantarPalabra(Par<String, In> aux) {
		String resto = aux.getPrimero();
		// long(identificador) = 0
		if (resto.length() == 0) {
			return new NodoTrie<String, In>('$', aux.getSegundo());
		}
		// long(identificador) != 0
		else {
			resto = aux.getPrimero().substring(1, aux.getPrimero().length());
			NodoTrie<String, In> tAux = plantarPalabra(new Par<String, In>(
					resto, aux.getSegundo()));
			NodoTrie<String, In> nuevo = new NodoTrie<String, In>(aux
					.getPrimero().charAt(0), null);
			nuevo.setPrimogenito(tAux);
			return nuevo;
		}
	}

	/**
	 * Si en la coleccion no habia ningun par con identificador 'identificador',
	 * añade (identificador, informacion) a la coleccion y devuelve true.
	 * 
	 * @return boolean
	 */
	public boolean meter(String identificador, In informacion) {
		// Comprueba que la informacion no es nula
		if (!(informacion == null)) {
			int tamaño = total;
			Par<String, In> elemento = new Par<String, In>(identificador,
					informacion);
			raiz = meterRec(elemento, raiz);
			// Elemento nuevo
			if (tamaño != total) {
				return true;
				// Elemento modificado
			} else
				return false;
		} else
			return false;
	}

	private NodoTrie<String, In> meterRec(Par<String, In> elemento,
			NodoTrie<String, In> nodo) {
		String resto;
		// si el nodo es nulo
		if (nodo == null) {
			total++;
			return plantarPalabra(elemento);
		}
		// nodo != null
		else {
			// long(identificador) = 0
			if (elemento.getPrimero().length() == 0) {
				if (nodo.getCaracter() != '$') {
					NodoTrie<String, In> tAux = new NodoTrie<String, In>('$',
							null);
					tAux.setSigHermano(nodo);
					nodo = tAux;
					total++;
				}
			}
			// long(identificador) != 0
			else {
				// identificador menor -> insertamos a la izquierda
				if (elemento.getPrimero().charAt(0) < nodo.getCaracter()) {
					NodoTrie<String, In> tAux = plantarPalabra(elemento);
					tAux.setSigHermano(nodo);
					nodo = tAux;
					total++;
					// identificador igual -> recorremos primogenitos
				} else if (elemento.getPrimero().charAt(0) == nodo
						.getCaracter()) {
					resto = elemento.getPrimero().substring(1,
							elemento.getPrimero().length());
					nodo.setPrimogenito(meterRec(new Par<String, In>(resto,
							elemento.getSegundo()), nodo.getPrimogenito()));
					// identificador mayor -> buscamos a la derecha
				} else {
					nodo.setSigHermano(meterRec(elemento, nodo.getSigHermano()));
				}
			}
			return nodo;
		}
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
		try {
			return obtenerInformacionRec(raiz, identificador);
			// No esta el elemento
		} catch (NoObjectException e) {
			return null;
		}
	}

	private In obtenerInformacionRec(NodoTrie<String, In> aux,
			String identificador) throws NoObjectException {
		if (aux != null) {
			String resto = identificador;
			In info = null;
			// long(identificador) = 0
			if (identificador.length() == 0) {
				info = aux.getInformacion();
				return info;
			} else {
				// Identificador MENOR que el del nodo
				if (identificador.charAt(0) < aux.getCaracter()) {
					return null;
				}
				// Identificador IGUAL que el del nodo
				else if (identificador.charAt(0) == aux.getCaracter()) {
					resto = identificador.substring(1, identificador.length());
					return obtenerInformacionRec(aux.getPrimogenito(), resto);
				}
				// Identificador MAYOR que el del nodo
				else {
					return obtenerInformacionRec(aux.getSigHermano(),
							identificador);
				}
			}
		} else
			throw new NoObjectException("Error, elemento nulo");
	}

	/**
	 * Si habia un par con identificador 'identificador' en la coleccion, lo
	 * borra y devuelve true
	 * 
	 * @return boolean
	 */
	public boolean borrar(String identificador) {
		try {
			raiz = borrarRec(identificador, raiz);
			total--;
			return true;
			// No esta el elemento
		} catch (NoObjectException e) {
			return false;
		}
	}

	private NodoTrie<String, In> borrarRec(String identificador,
			NodoTrie<String, In> aux) throws NoObjectException {
		if (aux != null) {
			String resto = identificador;
			// long(identificador) = 0
			if (identificador.length() == 0) {
				// Lo elimina
				if (aux.getCaracter() == '$') {
					aux = aux.getSigHermano();
				}
			} else {
				// Identificador IGUAL que el del nodo
				if (identificador.charAt(0) == aux.getCaracter()) {
					resto = identificador.substring(1, identificador.length());
					aux.setPrimogenito(borrarRec(resto, aux.getPrimogenito()));
					// Lo elimina
					if (aux.getPrimogenito() == null) {
						aux = aux.getSigHermano();
					}
				}
				// Identificador MAYOR que el del nodo
				else if (identificador.charAt(0) > aux.getCaracter()) {
					aux.setSigHermano(borrarRec(identificador,
							aux.getSigHermano()));
				} else {
					throw new NoObjectException("Error, elemento nulo");
				}
			}
		} else {
			throw new NoObjectException("Error, elemento nulo");
		}
		return aux;
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
	 * @throws NoObjectException
	 */
	public String listar() {
		String listado = "";
		iniciarIterador();
		while (haySiguiente()) {
			try {
				try {
					listado += siguiente().toString() + "\n";
				} catch (NoObjectException e) {
				}
			} catch (NoHaySiguienteException e) {
				System.out.printf("%s%n", e.getMessage());
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
		// Crea la pila
		pilaArbol = new PilaDinamica<Par<NodoTrie<String, In>, String>>();
		String cadena = "";
		// Coge la raiz y guarda el primer caracter
		NodoTrie<String, In> nodo = raiz;
		cadena = cadena + nodo.getCaracter();
		Par<NodoTrie<String, In>, String> aux = new Par<NodoTrie<String, In>, String>(
				nodo, cadena);
		// Apilamos el par
		if (aux != null) {
			pilaArbol.apilar(aux);
		}
	}

	/**
	 * Devuelve falso si no hay elemento siguente, cierto en otro caso.
	 * 
	 * @return boolean
	 */
	public boolean haySiguiente() {
		return !pilaArbol.esVacia();
	}

	/**
	 * Si exite siguiente, devuelve en (identificador, informacion) el siguiente
	 * par de coleccion y avanza el cursor; si no se produce un error.
	 * 
	 * @return Id
	 * @throws NoHaySiguienteException
	 * @throws NoObjectException
	 */
	public Par<String, In> siguiente() throws NoHaySiguienteException,
			NoObjectException {
		// Coge el primer elemento de la pila(raiz)
		Par<NodoTrie<String, In>, String> aux = pilaArbol.getCima();
		NodoTrie<String, In> nodoAux;
		// Coge la cadena
		String cadenaAux = aux.getSegundo();

		// Mientras el primogenito no sea '$' va concatenando la palabra
		// y apilando los pares
		while (aux.getPrimero().getPrimogenito().getCaracter() != '$') {
			nodoAux = aux.getPrimero().getPrimogenito();
			cadenaAux = aux.getSegundo() + nodoAux.getCaracter();
			aux = new Par<NodoTrie<String, In>, String>(nodoAux, cadenaAux);
			pilaArbol.apilar(aux);
		}
		// Una vez la palabra ha finalizado sacamos su informacion
		In informacion = aux.getPrimero().getPrimogenito().getInformacion();
		// Apilamos '$'
		aux = new Par<NodoTrie<String, In>, String>(aux.getPrimero()
				.getPrimogenito(), cadenaAux);
		pilaArbol.apilar(aux);
		// Mientras que la cima no sea nula y el hermano del nodo sea nulo
		// -> desapilamos
		while (pilaArbol.getCima() != null
				&& pilaArbol.getCima().getPrimero().getSigHermano() == null) {
			pilaArbol.desapilar();
		}
		// Si el nodo tiene hermano
		if (!pilaArbol.esVacia()) {
			NodoTrie<String, In> hermano = pilaArbol.getCima().getPrimero()
					.getSigHermano();
			String resto = pilaArbol
					.getCima()
					.getSegundo()
					.substring(0, pilaArbol.getCima().getSegundo().length() - 1);
			// Desapilamos el nodo y apilamos el hermano
			pilaArbol.desapilar();
			aux = new Par<NodoTrie<String, In>, String>(hermano, resto
					+ hermano.getCaracter());
			pilaArbol.apilar(aux);
		}
		return new Par<String, In>(cadenaAux, informacion);
	}

}