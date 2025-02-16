package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import java.util.ArrayList;
import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;

public class Asignaturas {

	private ArrayList<Asignatura> coleccionAsignaturas;

	
	
	public Asignaturas () {
	
		coleccionAsignaturas=new ArrayList<Asignatura>();

	}
	
	public ArrayList<Asignatura> get() {
		ArrayList<Asignatura> copia=copiaProfundaAsignaturas();
		return copia;
	}
	
	private ArrayList<Asignatura> copiaProfundaAsignaturas() {
		
		ArrayList<Asignatura>copiaAsignatura=new ArrayList<Asignatura>();
		
		for(Asignatura asignatura: coleccionAsignaturas){
			copiaAsignatura.add(asignatura);
			}

		return copiaAsignatura;
	}

	public int getTamano() {
		int tamano=0;
		
		for (int i=0;i<coleccionAsignaturas.size();i++) {
			if(coleccionAsignaturas.get(i)!=null) {tamano++;}
		}
		
		return tamano;
	}

	
	
	public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
		
		if(asignatura!=null) {
			if(coleccionAsignaturas.contains(asignatura)) {
				throw new OperationNotSupportedException("ERROR: Ya existe una asignatura con ese codigo.");
			}else {
				coleccionAsignaturas.add(asignatura);
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede insertar una asignatura nula.");
		}
	}

	public Asignatura buscar(Asignatura asignatura) {
		boolean encontrado=false;
		boolean noEncontrado=false;
		Asignatura asignaturaArrayCreado=null;
		
		if(asignatura!=null) {
			
			if(coleccionAsignaturas.size()>0) {
				
				for (Asignatura asignaturaArray:coleccionAsignaturas) {
					
				if(asignaturaArray.equals(asignatura)) {
					encontrado=true;
					asignaturaArrayCreado=asignaturaArray;
				}else {
					noEncontrado=true;
				}	
			}
				if (encontrado!=true && noEncontrado==true) {
					return null;
				}else {
					return asignaturaArrayCreado;
				}
			}else {
				throw new NullPointerException("No hay asignaturas incluidas en la coleccion");
			}	

		}
		else {
			throw new NullPointerException("asignatura recibido nulo");
		}
	}
	

	
	public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
		if(asignatura!=null) {
			if (coleccionAsignaturas.contains(asignatura)) {
				coleccionAsignaturas.remove(asignatura);
			}
			else{
				throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede borrar una asignatura nula.");
		}
	}
	

	
}
