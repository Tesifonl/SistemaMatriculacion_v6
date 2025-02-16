package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import java.util.ArrayList;
import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;

public class Alumnos {
	
	private ArrayList <Alumno> coleccionAlumnos;

	
	public Alumnos () {
		
		coleccionAlumnos=new ArrayList <Alumno>();

	}
	
	public ArrayList<Alumno> get() {
		ArrayList<Alumno> copia=copiaProfundaAlumnos();
		return copia;
	}
	
	
	private ArrayList<Alumno> copiaProfundaAlumnos() {
		
		ArrayList<Alumno>copiaAlumnos=new ArrayList<Alumno>();
		
		for(Alumno alumno:coleccionAlumnos) {
				copiaAlumnos.add(alumno);
			}
		return copiaAlumnos;
	}

	
	public int getTamano() {
		int tamano=0;
		
		for (int i=0; i<coleccionAlumnos.size();i++) {
			if(coleccionAlumnos.get(i)!=null) {tamano++;}
		}
		
		return tamano;
	}

	
	
	public void insertar(Alumno alumno) throws OperationNotSupportedException {
		
		if(alumno!=null) {
			if(coleccionAlumnos.contains(alumno)) {
				throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese dni.");
			}else {
				coleccionAlumnos.add(alumno);
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
		}
	}
	
	
	public Alumno buscar(Alumno alumno) {

		boolean encontrado=false;
		boolean noEncontrado=false;
		Alumno AlumnoArrayCreado=null;
		
		if(alumno!=null) {
			
			if (coleccionAlumnos.size()>0) {
				
				for (Alumno alumnoArray:coleccionAlumnos) {
				
				if(alumnoArray.equals(alumno)) {
					encontrado=true;
					AlumnoArrayCreado=alumnoArray;
				}else {
					noEncontrado=true;
				}	
			}
				if (encontrado!=true && noEncontrado==true) {
					return null;
				}else {
					return AlumnoArrayCreado;
				}
			}
			else {
			throw new NullPointerException("No hay alumnos incluidos en la coleccion");		
			}
		}
		else {
			throw new NullPointerException("alumno recibido nulo");
		}
	}

	
	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		if(alumno!=null) {
			if (coleccionAlumnos.contains(alumno)) {
				coleccionAlumnos.remove(alumno);
			}
			else{
				throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede borrar un alumno nulo.");
		}
	}
	

}
