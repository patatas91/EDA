package Practica0;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;

/*
 * AUTOR:CRISTIAN SIMON MORENO  - NIP: 611487
 */
public class prueba {
	
	public static void main (String[] args) {
		
		File nuevoF = new File(args[0]);
		Formatter salida = null;
		try {
			salida = new Formatter(nuevoF);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String h = "jhsfow";
		salida.format("%s", h);
		salida.close();
	}
	
	

}
