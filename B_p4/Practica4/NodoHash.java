package Practica4;

/*
 * AUTOR:CRISTIAN SIMON MORENO  - NIP: 611487
 */
public class NodoHash<E> {
		
	private E elemento;
	private NodoHash<E> siguiente;	

	/**
	 * Constructor de la clase
	 */
	public NodoHash(E elemento) {
		this.elemento = elemento;		
		siguiente = null;
	}
	
	/**
	 * Devuelve el elemento E
	 * 
	 * @return E
	 */
	public E getElemento() {
		return elemento;
	}

	/**
	 * Define el elemento E
	 * 
	 * @param E
	 */
	public void setElemento(E elemento) {
		this.elemento = elemento;
	}	

	/**
	 * Devuelve el siguiente 
	 * 
	 * @return NodoHash<E>
	 */
	public NodoHash<E> getSiguiente() {
		return siguiente;
	}
	
	/**
	 * Define el siguiente
	 * 
	 * @param sig
	 */
	public void setSiguiente(NodoHash<E> sig) {
		siguiente = sig;
	}	
		
}
