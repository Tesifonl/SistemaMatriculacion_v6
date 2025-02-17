package org.iesalandalus.programacion.matriculacion.modelo.negocio;

public enum TiposGrado {

	GRADOD("Grado D"),
	GRADOE("Grado E");
	
	private String cadenaAMostrar;


	private TiposGrado (String cadenaAMostrar) {	
		this.cadenaAMostrar=cadenaAMostrar;	
	}


	public String toString() {	
		return this.ordinal() + ".-" + cadenaAMostrar;
	}
	
}
