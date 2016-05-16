package Practica0;

/*
 * AUTOR:CRISTIAN SIMON MORENO  - NIP: 611487
 */
public class Par <Id, In>{
	
	private Id identificacion;
	private In informacion;
	
	public Par (Id id, In info) {
		this.identificacion = id;
		this.informacion = info;		
	}
	
	public Id getIdentificador() {
		return identificacion;		
	}
	
	public In getInformacion() {
		return informacion;		
	}

	public void setIdentificador(Id ident) {
		identificacion=ident;		
	}
	
	public void setInformacion(In info) {
		informacion=info;		
	}
	
	/**
     * Devuelve un String con el nombre y la cuota del usuario
     */
    public String toString() {
    	String linea = String.format("%s;%s", getIdentificador(), getInformacion().toString());
    	return(linea);
    }//Fin toString

}
