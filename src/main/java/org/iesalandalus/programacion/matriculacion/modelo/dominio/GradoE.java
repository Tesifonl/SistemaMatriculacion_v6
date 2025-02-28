package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public class GradoE extends Grado{
	
	private int numEdiciones;
	
	public GradoE (String nombre, int numAnios, int numEdiciones) {
		super(nombre);
		this.numEdiciones=numEdiciones;
	}

	public int getNumEdiciones() {
		return numEdiciones;
	}
	
	public void setNumEdiciones(int numEdiciones) {
		if(numEdiciones<0) {
			throw new IllegalArgumentException("ERROR: El numero de años no puede ser menor que cero");
		}
		else{
			this.numEdiciones=numEdiciones;
		}
	}

	@Override
	public void setNumAnios(int numAnios) {
		// TODO Auto-generated method stub
		if(numAnios==1) {
			this.numAnios=numAnios;
		}else {
			throw new IllegalArgumentException("ERROR: El numero de años debe ser 1");
		}
	}
	

	
	
}
