package Practica1;

/*
 * AUTOR:CRISTIAN SIMON MORENO  - NIP: 611487
 */
public class Celda<Id, In> {

	Id identificador;
	In informacion;
	Celda<Id, In> siguiente;

	/**
	 * Constructor de la clase
	 */
	public Celda(Id iden, In info) {
		this.identificador = iden;
		this.informacion = info;
		siguiente = null;
	}

	/**
	 * Devuelve el identificador Id
	 * 
	 * @return Id
	 */
	public Id getIdentificador() {
		return identificador;
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
	 * Define el identificador iden
	 * 
	 * @param iden
	 */
	public void setIdentificador(Id iden) {
		identificador = iden;
	}

	/**
	 * Define la informacion info
	 * 
	 * @param info
	 */
	public void setInformacion(In info) {
		informacion = info;
	}

	/**
	 * Devuelve la Celda siguiente
	 * 
	 * @return Celda<Id,In>
	 */
	public Celda<Id, In> getSiguiente() {
		return siguiente;
	}

	/**
	 * Define la Celda siguiente
	 * 
	 * @param sig
	 */
	public void setSiguiente(Celda<Id, In> sig) {
		siguiente = sig;
	}

	/**
	 * Devuelve un String con el Par contenido en la Celda
	 */
	public String toString() {
		return identificador.toString() + ";" + informacion.toString();
	}

}
