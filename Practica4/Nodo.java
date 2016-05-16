package Practica4;

/*
 * AUTOR:CRISTIAN SIMON MORENO  - NIP: 611487
 */
public class Nodo<T> {

	private T elemento;
	private Nodo<T> derecha;
	private Nodo<T> izquierda;

	/**
	 * Constructor de la clase
	 */
	public Nodo(T elemento) {
		this.elemento = elemento;		
		derecha = null;
		izquierda = null;
	}

	/**
	 * Devuelve el elemento T
	 * 
	 * @return T
	 */
	public T getElemento() {
		return elemento;
	}

	/**
	 * Define el elemento elemento
	 * 
	 * @param elemento
	 */
	public void setElemento(T elemento) {
		this.elemento = elemento;
	}
	
	/**
	 * Devuelve el hijo derecho
	 * 
	 * @return Nodo<T>
	 */
	public Nodo<T> getDerecha() {
		return derecha;
	}

	/**
	 * Devuelve el hijo izquierdo
	 * 
	 * @return Nodo<T>
	 */
	public Nodo<T> getIzquierda() {
		return izquierda;
	}

	/**
	 * Define el hijo derecho
	 * 
	 * @param dcha
	 */
	public void setDerecha(Nodo<T> dcha) {
		derecha = dcha;
	}

	/**
	 * Define el hijo izquierdo
	 * 
	 * @param izq
	 */
	public void setIzquierda(Nodo<T> izq) {
		izquierda = izq;
	}	
}
