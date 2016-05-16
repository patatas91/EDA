package Practica2;

/*
 * AUTOR:CRISTIAN SIMON MORENO  - NIP: 611487
 */
public class PilaDinamica<E> implements Pila<E> {

	private Celda<E> primera;
	private Celda<E> actual;
	private int tamaño;

	/**
	 * Crea una pila vacía
	 */
	public PilaDinamica() {
		primera = null;
		tamaño = 0;
	}

	/**
	 * Añade un nuevo elemento [elemento] al principio de la pila
	 * 
	 * @param elemento            
	 */
	public void apilar(E elemento) {
		Celda<E> nuevo = new Celda<E>(elemento);
		nuevo.setSiguiente(primera);
		primera = nuevo;
		tamaño++;
	}

	/**
	 * Devuelve [cierto] si la pila no tiene elementos
	 * 
	 * @return cierto
	 */
	public boolean esVacia() {
		return tamaño == 0;
	}

	/**
	 * Quita el elemento contenido al principio de la pila
	 * 
	 * @param elemento            
	 * @throws NoHaySiguienteException
	 */
	public void desapilar() throws NoHaySiguienteException {
		// comprueba si es vacia
		if (!esVacia()) {
			// si tiene tamaño 1
			if (tamaño == 1) {
				primera = null;
				tamaño--;
			} else {
				primera = primera.getSiguiente();
				tamaño--;
			}
		} else {
			throw new NoHaySiguienteException("Error, no hay " + "elementos");
		}
	}

	/**
	 * Devuelve la cima de la pila
	 * 
	 * @return primer elemento de la pila
	 * @throws NoObjectException
	 */
	public E getCima() throws NoObjectException {
		if (!esVacia()) {
			return primera.getElemento();
		} else {
			throw new NoObjectException("Error, no hay " + "elementos");
		}

	}

	/**
	 * Devuelve el numero de elementos de la pila
	 * 
	 * @return tamaño
	 */
	public int getTamaño() {
		return tamaño;
	}

	/**
	 * Muestra por pantalla los datos almacenados en la pila
	 */
	public void inicializarIteradorPila() {
		actual = primera;
	}

	/**
	 * Devuelve [cierto] si hay elemento siguiente en la pila
	 */
	public boolean haySiguientePila() {
		return actual != null;
	}

	/**
	 * Si hay siguiente elemento en la pila lo devuelve. Si no hay elemento
	 * siguiente en la pila devuelve [null]
	 * 
	 * @return elemento
	 * @throws NoHaySiguienteException
	 */
	public E siguientePila() throws NoHaySiguienteException {
		if (haySiguientePila()) {
			E elemento = actual.getElemento();
			actual = actual.getSiguiente();
			return elemento;
		} else {
			throw new NoHaySiguienteException("Error, no hay "
					+ "elemento siguiente");
		}
	}

	/**
	 * Muestra el contenido de la Pila
	 */
	public void mostrar() {
		try {
			inicializarIteradorPila();
			while (haySiguientePila()) {
				System.out.println(siguientePila().toString());

			}
		} catch (NoHaySiguienteException e) {
		}
	}

}
