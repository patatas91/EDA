package Practica4;

/*
 * AUTOR:CRISTIAN SIMON MORENO  - NIP: 611487
 */
public class Par<Id, In> {

	private Id identificacion;
	private In informacion;

	/**
	 * Constructor de la clase
	 */
	public Par(Id id, In info) {
		this.identificacion = id;
		this.informacion = info;
	}

	/**
	 * Devuelve el Id
	 * 
	 * @return identificacion
	 */
	public Id getIdentificador() {
		return identificacion;
	}

	/**
	 * Devuelve el In
	 * 
	 * @return informacion
	 */
	public In getInformacion() {
		return informacion;
	}

	/**
	 * Define el Id
	 * 
	 * @param ident
	 */
	public void setIdentificador(Id ident) {
		identificacion = ident;
	}

	/**
	 * Define el In
	 * 
	 * @param info
	 */
	public void setInformacion(In info) {
		informacion = info;
	}

	/**
	 * Devuelve un String con el Identificador y la Informacion del usuario
	 * separados por ';'
	 */
	public String toString() {
		
		String linea = String.format("%s;%s", getIdentificador(),
				getInformacion().toString());
		
		return (linea);
	}

}
