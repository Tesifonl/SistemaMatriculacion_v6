package org.iesalandalus.programacion.matriculacion.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;

public class Alumnos {
	
	private Alumno [] coleccionAlumnos;
	private int tamano;
	private int capacidad;
	
	public Alumnos (int capacidad) {
		if (capacidad>0) {
		coleccionAlumnos=new Alumno [capacidad];
		}
		else {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
	}
	
	public Alumno[] get() {
		Alumno[] copia=copiaProfundaAlumnos();
		return copia;
	}
	
	private Alumno[] copiaProfundaAlumnos() {
		Alumno[]copiaAlumnos=new Alumno[coleccionAlumnos.length];
		
		for(int i=0;i<coleccionAlumnos.length;i++) {
			if(coleccionAlumnos[i]!=null) {copiaAlumnos[i]= new Alumno(coleccionAlumnos[i]);
			}
			else {
				copiaAlumnos[i]=null;
			}
		}
		return copiaAlumnos;
	}

	public int getTamano() {
		int tamano=0;
		
		for (int i=0;i<coleccionAlumnos.length;i++) {
			if(coleccionAlumnos[i]!=null) {tamano++;}
		}
		
		return tamano;
	}

	
	public int getCapacidad() {
		capacidad=coleccionAlumnos.length;
		
		return capacidad;
	}
	
	public void insertar(Alumno alumno) throws OperationNotSupportedException {
		boolean noEncontrado=false;
		boolean encontrado=false;
	
		
		if (alumno!=null) {
			for(int i=0;i<coleccionAlumnos.length;i++) {
				if(coleccionAlumnos[i]!=null && coleccionAlumnos[i].equals(alumno)) {
					noEncontrado=false;
					encontrado=true;
				}
				else {
					noEncontrado=true;
				}
			}
			
			if (noEncontrado==true && encontrado==false && getTamano()<getCapacidad()) {
				coleccionAlumnos[getTamano()]=alumno;		
			}
			
			else if(encontrado==true) {
				throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese dni.");	
			}
			else {
				throw new OperationNotSupportedException("ERROR: No se aceptan más alumnos.");
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
		}
	}
	
	private int buscarIndice(Alumno alumno) throws OperationNotSupportedException {
		int indice=0;
		
		if (alumno!=null) {
			for(int i=0;i<coleccionAlumnos.length;i++) {
				if(coleccionAlumnos[i]!=null && coleccionAlumnos[i].equals(alumno)) {
					indice=i;
				}
				else {
					throw new OperationNotSupportedException("Ya existe este alumno");
				}
				
			}
		}
		else {
			throw new NullPointerException("Se ha recibido un alumno nulo");
		}
		return indice;
	}
	
	private boolean tamanoSuperado(int indice) {
		boolean superado=false;
		
		if (indice>getTamano()) {
			superado=true;
			}
		return superado;
	}
	
	private boolean capacidadSuperada(int indice) {
		boolean superado=false;
		
		if(indice>getCapacidad()) {
			superado=true;
		}
		return superado;
	}
	
	
	public Alumno buscar(Alumno alumno) {
		boolean encontrado=false;
		boolean otro=false;
		int indice=0;
		Alumno copiaAlumno=null;
		
		if(alumno!=null) {
			for (int i =0;i<coleccionAlumnos.length;i++) {
				if (coleccionAlumnos[i]!=null && coleccionAlumnos[i].equals(alumno)) {
					encontrado=true;
					indice=i;
					copiaAlumno=new Alumno(coleccionAlumnos[indice]);
				}
				else {
					otro=true;
				}
			}	
				
			if(encontrado==true) {
				return copiaAlumno;
				
			}else {return null;}
				
		}
		else {
			throw new NullPointerException("alumno recibido nulo");
		}
	}
	
	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		boolean encontrado=false;
		boolean otro=false;
		int indice=0;
		
		if(alumno!=null) {
			for (int i =0;i<coleccionAlumnos.length;i++) {
				if (coleccionAlumnos[i]!=null && coleccionAlumnos[i].equals(alumno)) {
					encontrado=true;
					indice=i;
				}
				else {
					otro=false;
				}
			}
			if (encontrado==true) {
				coleccionAlumnos[indice]=null;
				desplazarUnaPosicionHaciaIzquiera(indice);
			}
			else {
				throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede borrar un alumno nulo.");
		}
	}
	
	private void desplazarUnaPosicionHaciaIzquiera(int indice) {
		
		for (int i =indice;i<coleccionAlumnos.length-1;i++) {
			coleccionAlumnos[i]=coleccionAlumnos[i+1];
			}
			coleccionAlumnos[coleccionAlumnos.length-1]=null;

	}
	
}
