package Practica2;

/*
 * AUTOR:CRISTIAN SIMON MORENO  - NIP: 611487
 */
public interface Pila<E> {

	/**
	 * Añade el elemento [elemento] a la cima de la pila.
	 * 
	 * @param elemento
	 */
	public void apilar(E elemento);

	/**
	 * Devuelve [cierto] si la pila no tiene elementos
	 * 
	 * @return cierto
	 */
	public boolean esVacia();

	/**
	 * Elimina el elemento en la cima de la pila. En caso contrario se produce
	 * un error
	 * 
	 * @throws SiguienteException
	 */
	public void desapilar() throws NoHaySiguienteException;

	/**
	 * Devuelve la cima de la pila. En caso contrario se produce un error
	 * 
	 * @throws NoObjectExceptionException
	 */
	public E getCima() throws NoObjectException;

	/**
	 * Devuelve el numero de elementos de la pila.
	 */
	public int getTamaño();

	/**
	 * Inicializa un iterador interno de la pila
	 */
	public void inicializarIteradorPila();

	/**
	 * Devuelve [cierto] si hay elemento siguiente en el interador interno de la
	 * pila
	 * 
	 * @return cierto
	 */
	public boolean haySiguientePila();

	/**
	 * Si hay siguiente elemento en el iterador interno de la pila lo devuelve.
	 * En caso contrario se produce un error
	 * 
	 * @throws SiguienteException
	 */
	public E siguientePila() throws NoHaySiguienteException;

}
