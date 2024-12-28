package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;

public class Asignaturas {

	private Asignatura [] coleccionAsignaturas;
	private int tamano;
	private int capacidad;
	
	public Asignaturas (int capacidad) {
		if (capacidad>0) {
			coleccionAsignaturas=new Asignatura [capacidad];
		}
		else {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
	}
	
	public Asignatura[] get() {
		Asignatura[] copia=copiaProfundaAsignaturas();
		return copia;
	}
	
	private Asignatura[] copiaProfundaAsignaturas() {
		Asignatura[]copiaAsignaturas=new Asignatura[coleccionAsignaturas.length];
		
		for(int i=0;i<coleccionAsignaturas.length;i++) {
			if(coleccionAsignaturas[i]!=null) {copiaAsignaturas[i]= new Asignatura(coleccionAsignaturas[i]);
			}
			else {
				copiaAsignaturas[i]=null;
			}
		}
		return copiaAsignaturas;
	}

	public int getTamano() {
		int tamano=0;
		
		for (int i=0;i<coleccionAsignaturas.length;i++) {
			if(coleccionAsignaturas[i]!=null) {tamano++;}
		}
		
		return tamano;
	}

	
	public int getCapacidad() {
		capacidad=coleccionAsignaturas.length;
		
		return capacidad;
	}
	
	/*public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
		boolean noEncontrado=false;
		boolean encontrado=false;
	
		
		if (asignatura!=null) {
			for(int i=0;i<coleccionAsignaturas.length;i++) {
				if(coleccionAsignaturas[i]!=null && coleccionAsignaturas[i].equals(asignatura)) {
					noEncontrado=false;
					encontrado=true;
				}
				else {
					noEncontrado=true;
				}
			}
			
			if (noEncontrado==true && encontrado==false && getTamano()<getCapacidad()) {
				coleccionAsignaturas[getTamano()]=asignatura;		
			}
			
			else if(encontrado==true) {
				throw new OperationNotSupportedException("ERROR: Ya existe una asignatura con ese código.");	
			}
			else {
				throw new OperationNotSupportedException("ERROR: No se aceptan más asignaturas.");
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede insertar una asignatura nula.");
		}
	}*/
	
	public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
		
		if(asignatura!=null) {
			int indice=buscarIndice(asignatura);
			
			if(indice==999999) {
				if(getTamano()<getCapacidad()) {
					coleccionAsignaturas[getTamano()]=asignatura;
				}
				else {
					throw new OperationNotSupportedException("ERROR: No se aceptan más asignaturas.");
				}
			}
			else{
				throw new OperationNotSupportedException("ERROR: Ya existe una asignatura con ese código.");
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede insertar una asignatura nula.");
		}
	}
	
	/*private int buscarIndice(Asignatura asignatura) throws OperationNotSupportedException {
		int indice=0;
		
		if (asignatura!=null) {
			for(int i=0;i<coleccionAsignaturas.length;i++) {
				if(coleccionAsignaturas[i]!=null && coleccionAsignaturas[i].equals(asignatura)) {
					indice=i;
				}
				else {
					throw new OperationNotSupportedException("Ya existe esta asigntura");
				}
				
			}
		}
		else {
			throw new NullPointerException("Se ha recibido una asignatura nula");
		}
		return indice;
	}*/
	
	private int buscarIndice(Asignatura asignatura) throws OperationNotSupportedException {
		int indice=999999;
		boolean encontrado=false;
		boolean noEncontrado=false;
		
		if (asignatura!=null) {
			for(int i=0;i<coleccionAsignaturas.length;i++) {
				if(coleccionAsignaturas[i]!=null && coleccionAsignaturas[i].equals(asignatura)) {
					encontrado=true;
					indice=i;
				}
				else {
					noEncontrado=true;
				}
				
			}
			
			if(encontrado==true) {
				return indice;
			}
			else {
				return 999999;
			}
		}
		else {
			throw new NullPointerException("Se ha recibido una asignatura nula");
		}
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
	
	
	public Asignatura buscar(Asignatura asignatura) {
		boolean encontrado=false;
		boolean otro=false;
		int indice=0;
		Asignatura copiaAsignatura=null;
		
		if(asignatura!=null) {
			for (int i =0;i<coleccionAsignaturas.length;i++) {
				if (coleccionAsignaturas[i]!=null && coleccionAsignaturas[i].equals(asignatura)) {
					encontrado=true;
					indice=i;
					copiaAsignatura=new Asignatura(coleccionAsignaturas[indice]);
				}
				else {
					otro=true;
				}
			}	
				
			if(encontrado==true) {
				return copiaAsignatura;
				
			}else {return null;}
				
		}
		else {
			throw new NullPointerException("asignatura recibido nulo");
		}
	}
	
	/*public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
		boolean encontrado=false;
		boolean otro=false;
		int indice=0;
		
		if(asignatura!=null) {
			for (int i =0;i<coleccionAsignaturas.length;i++) {
				if (coleccionAsignaturas[i]!=null && coleccionAsignaturas[i].equals(asignatura)) {
					encontrado=true;
					indice=i;
				}
				else {
					otro=false;
				}
			}
			if (encontrado==true) {
				coleccionAsignaturas[indice]=null;
				desplazarUnaPosicionHaciaIzquiera(indice);
			}
			else {
				throw new OperationNotSupportedException("ERROR: No existe ninguna asignatura como la indicada.");
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede borrar una asignatura nula.");
		}
	}*/
	
	public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
		if(asignatura!=null) {
			int indice=buscarIndice(asignatura);
			
			if(indice!=999999) {
				coleccionAsignaturas[indice]=null;
				desplazarUnaPosicionHaciaIzquiera(indice);
			}
			else{
				throw new OperationNotSupportedException("ERROR: No existe ninguna asignatura como la indicada.");
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede borrar una asignatura nula.");
		}
	}
	
	private void desplazarUnaPosicionHaciaIzquiera(int indice) {
		
		for (int i =indice;i<coleccionAsignaturas.length-1;i++) {
			coleccionAsignaturas[i]=coleccionAsignaturas[i+1];
			}
			coleccionAsignaturas[coleccionAsignaturas.length-1]=null;

	}
	
}
