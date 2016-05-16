package Practica2;

/*
 * AUTOR:CRISTIAN SIMON MORENO  - NIP: 611487
 */
public class Celda<E> {
	/*
	 * Atributos de la clase
	 */
	private E elemento;
	private Celda<E> siguiente;

	/**
	 * Metodo constructor de la clase
	 * 
	 * @param elemento
	 */
	public Celda(E elemento) {
		this.elemento = elemento;
		siguiente = null;
	}

	/**
	 * Devuelve el elemento [elemento] del nodo
	 * 
	 * @return elemento
	 */
	public E getElemento() {
		return elemento;
	}

	/**
	 * Asigna al elemento [elemento] el elemento [nuevo]
	 */
	public void setElemento(E nuevo) {
		elemento = nuevo;
	}

	/**
	 * Devuelve el puntero siguiente de la celda
	 * 
	 * @return siguiente
	 */
	public Celda<E> getSiguiente() {
		return siguiente;
	}

	/**
	 * Asigna a siguiente el elemento [proximo]
	 * 
	 * @param proximo
	 */
	public void setSiguiente(Celda<E> proximo) {
		siguiente = proximo;
	}

	/**
	 * Devuelve un String con el elemento
	 */
	public String toString() {
		return elemento.toString();
	}

}
