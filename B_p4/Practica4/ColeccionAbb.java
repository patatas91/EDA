package Practica4;

/*
 * AUTOR:CRISTIAN SIMON MORENO  - NIP: 611487
 */
public class ColeccionAbb<Id extends Comparable<Id>, In> implements
		Coleccion<Id, In> {

	private Pila<Nodo<Par<Id, In>>> pilaArbol = new PilaDinamica<Nodo<Par<Id, In>>>();
	private Nodo<Par<Id, In>> raiz;
	private int total;

	/**
	 * Constructor de la clase
	 */
	public ColeccionAbb() {
		raiz = null;
		total = 0;
	}

	/**
	 * Si en la coleccion no habia ningun par con identificador 'identificador',
	 * añade (identificador, informacion) a la coleccion y devuelve true.
	 * 
	 * @return boolean
	 */
	public boolean meter(Id identificador, In informacion) {
		// Comprueba que la informacion no es nula
		if (!(informacion == null)) {
			int tamaño = total;
			Par<Id, In> elemento = new Par<Id, In>(identificador, informacion);
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

	private Nodo<Par<Id, In>> meterRec(Par<Id, In> elemento,
			Nodo<Par<Id, In>> aux) {
		// si el nodo es nulo
		if (aux == null) {
			aux = new Nodo<Par<Id, In>>(elemento);
			aux.setDerecha(null);
			aux.setIzquierda(null);
			total++;
		}
		// si la clave es menor -> IZQUIERDA
		else if (elemento.getIdentificador()
				.compareTo(aux.getElemento().getIdentificador()) < 0) {
			aux.setIzquierda(meterRec(elemento, aux.getIzquierda()));
		}
		// si la clave es mayor -> DERECHA
		else if (elemento.getIdentificador()
				.compareTo(aux.getElemento().getIdentificador()) > 0) {
			aux.setDerecha(meterRec(elemento, aux.getDerecha()));
		} else {
			// si ya esta el elemento actualiza su informacion
			aux.setElemento(elemento);
			// aux.setInformacion(elemento.getInformacion());
		}
		return aux;
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
		try {
			return obtenerInformacionRec(raiz, identificador);
		} catch (NoObjectException e) {
			return null;
		}
	}

	private In obtenerInformacionRec(Nodo<Par<Id, In>> aux, Id identificador)
			throws NoObjectException {
		if (aux != null) {
			In info = null;
			// si la clave es menor -> IZQUIERDA
			if (identificador.compareTo(aux.getElemento().getIdentificador()) < 0) {
				return obtenerInformacionRec(aux.getIzquierda(), identificador);
			}
			// si la clave es mayor -> DERECHA
			else if (identificador.compareTo(aux.getElemento().getIdentificador()) > 0) {
				return obtenerInformacionRec(aux.getDerecha(), identificador);
			}
			// cuando lo encuentra
			else {
				info = aux.getElemento().getInformacion();
				return info;
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
	public boolean borrar(Id identificador) {
		try {
			raiz = borrarRec(identificador, raiz);
			total--;
			return true;
			// No esta el elemento
		} catch (NoObjectException e) {
			return false;
		}
	}

	private Nodo<Par<Id, In>> borrarRec(Id identificador, Nodo<Par<Id, In>> aux)
			throws NoObjectException {
		// No esta
		if (aux == null) {
			throw new NoObjectException("Error, elemento nulo");
		}
		// La encuentra
		else if (identificador.compareTo(aux.getElemento().getIdentificador()) == 0) {
			// El nodo tiene dos hijos
			if (aux.getDerecha() != null && aux.getIzquierda() != null) {
				aux = cambiarMax(aux);
			}
			// El nodo es hoja
			else if (aux.getDerecha() == null && aux.getIzquierda() == null) {
				aux = null;
			}
			// No tiene hijo izquierdo
			else if (aux.getIzquierda() == null) {
				aux = aux.getDerecha();
			}
			// No tiene hijo derecho
			else if (aux.getDerecha() == null) {
				aux = aux.getIzquierda();
			}
		}
		// Lo busca
		// La clave del elemento es menor que la clave del Nodo actual ->
		// IZQUIERDA
		else if (identificador.compareTo(aux.getElemento().getIdentificador()) < 0) {
			aux.setIzquierda((borrarRec(identificador, aux.getIzquierda())));

		}
		// La clave del elemento es mayor que la clave del Nodo actual ->
		// DERECHA
		else if (identificador.compareTo(aux.getElemento().getIdentificador()) > 0) {
			aux.setDerecha(borrarRec(identificador, aux.getDerecha()));

		}
		return aux;
	}

	/**
	 * Pone el maximo donde estaba el nodo y lo elimina
	 * 
	 * @param aux2
	 * @return Nodo<Id,In>
	 * @throws NoObjectException
	 */
	private Nodo<Par<Id, In>> cambiarMax(Nodo<Par<Id, In>> aux2)
			throws NoObjectException {
		Nodo<Par<Id, In>> actual = aux2.getIzquierda();
		// Busca el mayor de sus hijos
		while (actual.getDerecha() != null) {
			actual = actual.getDerecha();
		}
		Id identificador = (actual.getElemento().getIdentificador());
		In informacion = (actual.getElemento().getInformacion());
		Par<Id, In> elemento = new Par<Id, In>(identificador, informacion);
		// Lo borra
		aux2 = borrarRec(actual.getElemento().getIdentificador(), aux2);
		// Actualizamos valores
		aux2.setElemento(elemento);
		return aux2;
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
		return (null == raiz);
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
				try {
					listado += siguiente().toString() + "\n";
				} catch (NoObjectException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (NoHaySiguienteException e) {
				System.out.printf("%s%n", e.getMessage());
			}
		}

		return listado;
	}

	/**
	 * Prepara el iterador usando una pila que contendra la raiz y los hijos
	 * izquierdos del arbol
	 */
	public void iniciarIterador() {
		pilaArbol = new PilaDinamica<Nodo<Par<Id, In>>>();
		Nodo<Par<Id, In>> aux = raiz;
		while (aux != null) {
			// Mientras haya hijo izquierdo lo apila
			pilaArbol.apilar(aux);
			aux = aux.getIzquierda();
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
	 */
	public Par<Id, In> siguiente() throws NoHaySiguienteException,
			NoObjectException {
		if (haySiguiente()) {
			// Coge la cima de la pila y la desapila
			Nodo<Par<Id, In>> aux = pilaArbol.getCima();
			pilaArbol.desapilar();
			// Objeto Par a devolver
			Par<Id, In> sig = new Par<Id, In>(aux.getElemento().getIdentificador(),
					aux.getElemento().getInformacion());
			aux = aux.getDerecha();
			while (aux != null) {
				pilaArbol.apilar(aux);
				aux = aux.getIzquierda();
			}
			return sig;

		} else {
			throw new NoHaySiguienteException("Error, no hay "
					+ "elemento siguiente");
		}
	}

}
