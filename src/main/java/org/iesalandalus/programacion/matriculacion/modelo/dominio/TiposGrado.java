package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum TiposGrado {

	GRADOD("Grado D"),
	GRADOE("Grado E");
	
	private String cadenaAMostrar;


	private TiposGrado (String cadenaAMostrar) {	
		this.cadenaAMostrar=cadenaAMostrar;	
	}


	public String toString() {	
		return cadenaAMostrar;
	
	}
	
	public String imprimir() {	
		return toString();
	}
	
}
