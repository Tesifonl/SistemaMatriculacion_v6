package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public abstract class Grado {
	
	protected String nombre;
	protected String iniciales;
	protected int numAnios;
	
	public Grado (String nombre) {
		setNombre(nombre);
		}
	
	
	public String getNombre() {
		return nombre;
	}
	
	protected void setNombre(String nombre) {
		if(nombre!=null) {
			this.nombre=nombre;
			setIniciales();
			}else {
				throw new NullPointerException ("Error: No se ha recibido el nombre");
			}
	}
	
	private void setIniciales() {
		
		String [] coleccionString=getNombre().split(" ");
		String letras="";
		String iniciales="";
		
		int j=0;
		
		for(String letra: coleccionString) {
			j++;
			letras=letra.substring(0, 1).toUpperCase();
			iniciales=iniciales+letras;
		}
		this.iniciales=iniciales;
	}
	

	public abstract void setNumAnios(int numAnios);


	@Override
	public String toString() {
		return  "(" + iniciales + ")"+nombre;
	}
	
	
	
	}


