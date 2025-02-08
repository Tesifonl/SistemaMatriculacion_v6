package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

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
		
		for(int i=0;i<coleccionAlumnos.size();i++) {
			if(coleccionAlumnos.get(i)!=null) {copiaAlumnos.add(coleccionAlumnos.get(i));
			}
			else {
				copiaAlumnos.add(coleccionAlumnos.get(i));
			}
		}
		return copiaAlumnos;
	}

	
	public int getTamano() {
		int tamano=0;
		
		for (Alumno alumno:coleccionAlumnos) {
			if(alumno!=null) {tamano++;}
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
		int j=0;
		boolean encontrado=false;
		boolean noEncontrado=false;
		
		if(alumno!=null) {
			
			if (coleccionAlumnos.size()>0) {
			
				for (int i=0;i<coleccionAlumnos.size();i++) {
					if(coleccionAlumnos.get(i).equals(alumno)){
					j=i;
					encontrado=true;
					}
					noEncontrado=true;
				}
				
				if (encontrado==true) {
					return coleccionAlumnos.get(j);
				}else {
					System.out.println("No se ha encontrado este alumno en la coleccion");
					return null;
				}
			}else {
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
