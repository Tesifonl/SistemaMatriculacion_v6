package org.iesalandalus.programacion.matriculacion.modelo.dominio;



public enum Modalidad {
	
	SEMIPRESENCIAL("semipresencial"),
	PRESENCIAL("presencial");
	
	private String cadenaAMostrar;


	private Modalidad (String cadenaAMostrar) {	
		this.cadenaAMostrar=cadenaAMostrar;	
	}


	public String toString() {	
		return cadenaAMostrar;
	}
	
	public String imprimir() {	
		return toString();
	}

}

