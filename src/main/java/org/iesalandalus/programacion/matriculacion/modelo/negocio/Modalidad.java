package org.iesalandalus.programacion.matriculacion.modelo.negocio;



public enum Modalidad {
	
	SEMIPRESENCIAL("Semipresencial"),
	PRESENCIAL("Presencial");
	
	private String cadenaAMostrar;


	private Modalidad (String cadenaAMostrar) {	
		this.cadenaAMostrar=cadenaAMostrar;	
	}


	public String toString() {	
		return this.ordinal() + ".-" + cadenaAMostrar;
	}

}

