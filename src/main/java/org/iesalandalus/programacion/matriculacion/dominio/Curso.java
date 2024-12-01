package org.iesalandalus.programacion.matriculacion.dominio;

public enum Curso {

	PRIMERO("Primero"),SEGUNDO("Segundo");
	
	private String cadenaAMostrar;
	
	private Curso (String cadenaAMostrar) {	
		this.cadenaAMostrar=cadenaAMostrar;	
	}
	
	public String imprimir() {
		 return this.ordinal() + ".-" + cadenaAMostrar;
	}
	
	
	public String toString() {	
		return cadenaAMostrar;
	}

}
