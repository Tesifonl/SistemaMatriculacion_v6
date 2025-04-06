package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum Curso {

	PRIMERO("primero"),SEGUNDO("segundo");
	
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
