package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum Grado {
	
	GDCFGB("GDCFCB"),GDCFGM("GDCFGM"),GDCFGS("GDCFGS");
	
	private String cadenaAMostrar;
	
	private Grado (String cadenaAMostrar) {	
		this.cadenaAMostrar=cadenaAMostrar;	
	}
	
	public String imprimir() {
		 return this.ordinal() + ".-" + cadenaAMostrar;
	}
	
	
	public String toString() {	
		return cadenaAMostrar;
	}
}
