package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public class GradoD extends Grado{
	
	private Modalidad modalidad;
	
	public GradoD(String nombre,int numAnios,Modalidad modalidad) {
		super(nombre);
		setNumAnios(numAnios);
		setModalidad(modalidad);
	}

	public Modalidad getModalidad() {
		return modalidad;
	}
	
	public void setModalidad (Modalidad modalidad) {
		if(modalidad!=null){
			this.modalidad=modalidad;
		}else {
			throw new NullPointerException("ERROR: Se ha recibido una modalidad nula");
		}
	}
	


	@Override
	public void setNumAnios(int numAnios) {
		// TODO Auto-generated method stub
		if(numAnios<2 || numAnios>3) {
			throw new IllegalArgumentException("ERROR: El numero de años no puede ser distinto a 2 o 3");
		}else {
			this.numAnios=numAnios;
		}
	}
	

	@Override
	public String toString() {

		return super.toString()+"[ años= "+numAnios+" modalidad= " + modalidad + "]";
	}
	
}
