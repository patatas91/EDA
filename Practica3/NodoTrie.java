package Practica3;

/*
 * AUTORES: MIGUEL ALLUE BARON     - NIP: 593599
 * 			CRISTIAN SIMON MORENO  - NIP: 611487
 */
public class NodoTrie<C,In> {
	
	private char C;
	private In informacion;
	private NodoTrie<C,In> primogenito;
	private NodoTrie<C,In> sigHermano;

	/**
	 * Constructor de la clase
	 */
	public NodoTrie(char caracter, In elemento) {
		this.C = caracter;
		this.informacion = elemento;		
		primogenito = null;
		sigHermano = null;
	}
	
	/**
	 * Devuelve el caracter C
	 * 
	 * @return C
	 */
	public char getCaracter() {
		return C;
	}

	/**
	 * Define el caracter C
	 * 
	 * @param C
	 */
	public void setCaracter(char caracter) {
		this.C = caracter;
	}

	/**
	 * Devuelve la informacion In
	 * 
	 * @return In
	 */
	public In getInformacion() {
		return informacion;
	}

	/**
	 * Define la informacion informacion
	 * 
	 * @param informacion
	 */
	public void setInformacion(In informacion) {
		this.informacion = informacion;
	}
	
	/**
	 * Devuelve el primogenito
	 * 
	 * @return NodoTrie<C,In>
	 */
	public NodoTrie<C,In> getPrimogenito() {
		return primogenito;
	}
	
	/**
	 * Define el primogenito
	 * 
	 * @param pri
	 */
	public void setPrimogenito(NodoTrie<C,In> pri) {
		primogenito = pri;
	}

	/**
	 * Devuelve el siguiente hermano
	 * 
	 * @return NodoTrie<C,In>
	 */
	public NodoTrie<C,In> getSigHermano() {
		return sigHermano;
	}
	
	/**
	 * Define el siguiente hermano
	 * 
	 * @param sig
	 */
	public void setSigHermano(NodoTrie<C,In> sig) {
		sigHermano = sig;
	}	
		
}
