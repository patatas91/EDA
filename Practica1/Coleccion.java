package Practica1;

/*
 * AUTOR:CRISTIAN SIMON MORENO  - NIP: 611487
 */
public interface Coleccion<Id extends Comparable<Id>, In> {

	/**
	 * Si en la coleccion no habia ningun par con identificador 'identificador',
	 * a�ade (identificador, informacion) a la coleccion y devuelve true.
	 * 
	 * @return boolean
	 */
	public boolean meter(Id identificador, In informacion);

	/**
	 * Devuelve verdad si y solo si en la coleccion hay algun par
	 * (identificador, informacion)
	 * 
	 * @return boolean
	 */
	public boolean esta(Id identificador);

	/**
	 * Dado un identificador 'identificador', devuelve la informacion asociada a
	 * el en la coleccion
	 * 
	 * @return Informacion
	 */
	public In obtenerInformacion(Id identificador);

	/**
	 * Si habia un par con identificador 'identificador' en la coleccion, lo
	 * borra y devuelve true
	 * 
	 * @return boolean
	 */
	public boolean borrar(Id identificador);

	/**
	 * Devuelve el numero de pares de la coleccion
	 * 
	 * @return int
	 */
	public int tama�o();

	/**
	 * Devuelve verdad si y solo si la coleccion no tiene ningun par
	 * 
	 * @return boolean
	 */
	public boolean esVacia();

	/**
	 * Devuelve un String con la informaci�n de todos los pares (identificador,
	 * informaci�n) almacenados en la colecci�n, separando los pares entre s�
	 * con saltos de l�nea.
	 * 
	 * @return String con la informaci�n de todos los pares (identificador,
	 *         informaci�n).
	 */
	public String listar();

	/**
	 * Prepara el iterador y su cursor para que el siguiente elemento a visitar
	 * sea el primero.
	 */
	public void iniciarIterador();

	/**
	 * Devuelve falso si ya se ha visitado el ultimo elemento, cierto en otro
	 * caso.
	 * 
	 * @return boolean
	 */
	public boolean haySiguiente();

	/**
	 * Si exite siguiente, devuelve en (identificador, informacion) el siguiente
	 * par de coleccion y avanza el cursor; si no se produce un error.
	 * 
	 * @return Id
	 * @throws NoHaySiguienteException
	 */
	public Par<Id, In> siguiente() throws NoHaySiguienteException;

}
