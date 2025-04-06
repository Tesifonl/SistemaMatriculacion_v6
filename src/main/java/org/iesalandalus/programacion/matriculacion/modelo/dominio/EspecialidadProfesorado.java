package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum EspecialidadProfesorado {
	
	INFORMATICA("informatica"),SISTEMAS("sistemas"),FOL("fol");
	
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
