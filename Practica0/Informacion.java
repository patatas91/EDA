package Practica0;

/*
 * AUTOR:CRISTIAN SIMON MORENO  - NIP: 611487
 */
public class Informacion {
	
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String NIF;
	private double saldo;
	
	public Informacion(String nom, String ap1, String ap2, String N, double sal) {
		this.nombre = nom;
		this.apellido1 = ap1;
		this.apellido2 = ap2;
		this.NIF = N;
		this.saldo = sal;		
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nom) {
		this.nombre = nom;
	}
	
	public String getApellido1() {
		return apellido1;
	}
	
	public void setApellido1(String ap1) {
		this.apellido1 = ap1;
	}
	
	public String getApellido2() {
		return apellido2;
	}
	
	public void setApellido2(String ap2) {
		this.apellido2 = ap2;
	}
	
	public String getNIF() {
		return NIF;
	}
	
	public void setNIF(String n) {
		this.NIF = n;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(double sal) {
		this.saldo = sal;
	}
	
	/**
     * Devuelve un String con el nombre y la cuota del usuario
     */
    public String toString() {
    	String linea = String.format("%s;%s;%s;%s;%.2f", getNombre(), getApellido1(), 
    			getApellido2(), getNIF(), getSaldo());
    	return(linea);
    }//Fin toString
	

}
