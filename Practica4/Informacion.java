package Practica4;

/*
 * AUTORES: MIGUEL ALLUE BARON     - NIP: 593599
 * 			CRISTIAN SIMON MORENO  - NIP: 611487
 */
public class Informacion {

	private String nombre;
	private String apellido1;
	private String apellido2;
	private String NIF;
	private double saldo;

	/**
	 * Constructor de la clase
	 */
	public Informacion(String nom, String ap1, String ap2, String N, double sal) {
		this.nombre = nom;
		this.apellido1 = ap1;
		this.apellido2 = ap2;
		this.NIF = N;
		this.saldo = sal;
	}

	/**
	 * Devuelve el nombre
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Define el nombre nom
	 * 
	 * @param nom
	 */
	public void setNombre(String nom) {
		this.nombre = nom;
	}

	/**
	 * Devuelve el primer apellido
	 * 
	 * @return apellido1
	 */
	public String getApellido1() {
		return apellido1;
	}

	/**
	 * Define el primer apellido ap1
	 * 
	 * @param ap1
	 */
	public void setApellido1(String ap1) {
		this.apellido1 = ap1;
	}

	/**
	 * Devuelve el segundo apellido
	 * 
	 * @return apellido2
	 */
	public String getApellido2() {
		return apellido2;
	}

	/**
	 * Define el segundo apellido ap2
	 * 
	 * @param ap2
	 */
	public void setApellido2(String ap2) {
		this.apellido2 = ap2;
	}

	/**
	 * Devuelve el NIF
	 * 
	 * @return NIF
	 */
	public String getNIF() {
		return NIF;
	}

	/**
	 * Define el NIF
	 * 
	 * @param n
	 */
	public void setNIF(String n) {
		this.NIF = n;
	}

	/**
	 * Devuelve el saldo
	 * 
	 * @return saldo
	 */
	public double getSaldo() {
		return saldo;
	}

	/**
	 * Define el saldo
	 * 
	 * @param sal
	 */
	public void setSaldo(double sal) {
		this.saldo = sal;
	}

	/**
	 * Devuelve un String con el nombre apellidos, NIF y saldo separados por ';'
	 */
	public String toString() {
		String linea = String.format("%s;%s;%s;%s;%.2f", getNombre(),
				getApellido1(), getApellido2(), getNIF(), getSaldo());
		return (linea);
	}

}
