package Practica0;

/*
 * AUTOR:CRISTIAN SIMON MORENO  - NIP: 611487
 */

public class ColeccionEstatica<Id,In> implements Coleccion<Id,In>{
	private static final int MAX = 5;
	private Par<Id,In>[] v;	
	private int total;
	private int indice;
	
	/**
	 * Constructor de la clase
	 */
	@SuppressWarnings("unchecked")
	public ColeccionEstatica() {
		v = new Par[MAX];
		total = 0;
	}

	/**
	 * Si en la coleccion no habia ningun par con identificador 'identificador', añade 
	 * (identificador, informacion) a la coleccion y devuelve true.
	 * @return boolean 
	 */
	public boolean meter(Id identificador, In informacion) {
		boolean nuevo = false;	
		Par<Id,In> aux = new Par<Id,In>(identificador, informacion);		
		iniciarIterador();		
		if(esVacia()){
			v[indice]=aux;
			//indice++;
			total++;
			nuevo = true;
		}
		else {
			if(!esta(identificador)) {
				//Comprueba si no se ha sobrepasado el vector
				if (total<v.length) {
					//El usuario no esta, lo añadimos al final												
					v[total]=aux;
					//indice++;					
					total++;					
					nuevo = true;
				}
				//FALTA ESPACIO
			}
			else {
				v[indice-1].setInformacion(informacion);			
				nuevo = false;
			}
		}
		return nuevo;
	}

			/*while (haySiguiente()) {
				//El usuario ya esta, se actualiza su informacion
				if (esta(identificador)) {
					v[indice].setInformacion(informacion);
					total = total+1;				
				}
				else {
					//Comprueba si no se ha sobrepasado el vector
					if (indice<v.length) {
						//El usuario no esta, lo añadimos al final					
						Par<Id,In> aux = new Par<Id,In>(identificador, informacion);					
						v[indice]=aux;
						indice++;
						//nuevo = true;
						total = total+1;
						//acabado=true;
						return true;
					}
				}
			}
			return false;
		}*/

	/**
	 * Devuelve verdad si y solo si en la coleccion hay algun par (identificador, informacion)
	 * @return boolean
	 */
	public boolean esta(Id identificador) {
		//boolean encontrado = false;
		iniciarIterador();
		//if(!esVacia()){					
			while (haySiguiente()){// && !encontrado) {
				try {
					
					if((siguiente().getIdentificador()).equals(identificador)) {						
						
						return true;
					}
				} catch (NoHaySiguienteException e) {}
			}
		//}
		return false;
		
	}

	/**
	 * Dado un identificador 'identificador', devuelve la informacion asociada a el en la
	 * coleccion
	 * @return String
	 */
	public In obtenerInformacion(Id identificador) {		
		In info = null;
		iniciarIterador();
		if(esta(identificador)){
			info = v[indice-1].getInformacion();
		}
		return info;
	}

	/**
	 * Si habia un par con identificador 'identificador' en la coleccion, lo borra y 
	 * devuelve true
	 * @return boolean
	 */
	public boolean borrar(Id identificador) {
		boolean borrado = false;
		//boolean acabar = false;
		//Comprueba si el usuario esta
		if (esta(identificador)) {
			while (haySiguiente()) {
				try {
					//Borra el usuario y mueve todos una posicion
					v[indice-1] = siguiente();
				} catch (NoHaySiguienteException e) {}
			}
			total = total-1;
			borrado = true;
		}
		return borrado;
	}

	/**
	 * Devuelve el numero de pares de la coleccion
	 * @return int
	 */
	public int tamaño() {		
		return total;
	}

	/**
	 * Devuelve verdad si y solo si la coleccion no tiene ningun par
	 * @return boolean
	 */
	public boolean esVacia() {
		return (total==0);
	}

	/**
	 * Devuelve un String con la información de todos los pares (identificador, información)
	 * almacenados en la colección, separando los pares entre sí
	 * con saltos de línea.
	 * @return String con la información de todos los pares (identificador, información).
	 */
	public String listar() {
		String listado = "";		
		iniciarIterador();
		while (haySiguiente()) {			
			try {
				//System.out.println(siguiente().toString());
				listado += siguiente().toString() + "\n";
				
			} catch (NoHaySiguienteException e) {}
		}		
			
		return listado;
	}
	
	/**
	 * Prepara el iterador y su cursor para que el siguiente elemento a visitar sea el primero.
	 */
	public void iniciarIterador() {
		indice=0;		
	}

	/**
	 * Devuelve falso si ya se ha visitado el ultimo elemento, cierto en otro caso.
	 * @return boolean
	 */
	public boolean haySiguiente() {
		return indice!=total;				
	}

	/**
	 * Si exite siguiente, devuelve en (identificador, informacion) el siguiente par de coleccion
	 * y avanza el cursor; si no se produce un error.
	 * @return Id
	 * @throws NoHaySiguienteException
	 */
	public Par<Id, In> siguiente() throws NoHaySiguienteException {
		indice++;
		return v[indice-1];
	}

	
}
