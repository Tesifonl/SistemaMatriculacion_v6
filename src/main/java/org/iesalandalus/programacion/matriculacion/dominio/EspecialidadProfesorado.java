package org.iesalandalus.programacion.matriculacion.dominio;

public enum EspecialidadProfesorado {
	
	INFORMATICA("Informatica"),SISTEMAS("Sistemas"),FOL("Fol");
	
	private String cadenaAMostrar;
	
	private EspecialidadProfesorado (String cadenaAMostrar) {	
		this.cadenaAMostrar=cadenaAMostrar;	
	}
	
	public String imprimir() {
		 return this.ordinal() + ".-" + cadenaAMostrar;
	}
	
	
	public String toString() {	
		return cadenaAMostrar;
	}
}
