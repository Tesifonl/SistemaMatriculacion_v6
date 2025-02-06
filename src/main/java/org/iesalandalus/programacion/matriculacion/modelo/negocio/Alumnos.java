package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;

public class Alumnos {
	
	private List <Alumno> coleccionAlumnos;
	//private int tamano;
	//private int capacidad;
	
	public Alumnos () {
		
		coleccionAlumnos=new ArrayList <Alumno>();

	}
	
	public List<Alumno> get() {
		List<Alumno> copia=copiaProfundaAlumnos();
		return copia;
	}
	
	private List<Alumno> copiaProfundaAlumnos() {
		
		List<Alumno>copiaAlumnos=new ArrayList<Alumno>();
		
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

	
	/*public int getCapacidad() {
		capacidad=coleccionAlumnos.length;
		
		return capacidad;
	}*/
	
	/*public void insertar(Alumno alumno) throws OperationNotSupportedException {
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
	}*/
	
	public void insertar(Alumno alumno) throws OperationNotSupportedException {
		
		if(alumno!=null) {
			if(coleccionAlumnos.contains(alumno)) {
				throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese dni.");
			}else {
				coleccionAlumnos.add(alumno);
				System.out.println("Alumno introducido en la lista");
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
		}
	}

	
	/*private int buscarIndice(Alumno alumno) throws OperationNotSupportedException {
		int indice=999999;
		boolean encontrado=false;
		boolean noEncontrado=false;
		
		if (alumno!=null) {
			for(int i=0;i<coleccionAlumnos.length;i++) {
				if(coleccionAlumnos[i]!=null && coleccionAlumnos[i].equals(alumno)) {
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
			throw new NullPointerException("Se ha recibido un alumno nulo");
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
	}*/
	
	
	public Alumno buscar(Alumno alumno) {
		int j=0;
		boolean encontrado,noEncontrado=false;
		
		if(alumno!=null) {
			for (int i=0;i<coleccionAlumnos.size();i++) {
				if(coleccionAlumnos.get(i).equals(alumno)){
				j=i;
				encontrado=true;
				}
				noEncontrado=true;
			}
			
			if (encontrado=true) {
				return coleccionAlumnos.get(j);
			}else {
				System.out.println("No se ha encontrado este alumno en la coleccion");
				return null;
			}

		}
		else {
			throw new NullPointerException("alumno recibido nulo");
		}
	}
	
	/*public void borrar(Alumno alumno) throws OperationNotSupportedException {
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
	}*/
	
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
	
	/*private void desplazarUnaPosicionHaciaIzquiera(int indice) {
		
		for (int i =indice;i<coleccionAlumnos.length-1;i++) {
			coleccionAlumnos[i]=coleccionAlumnos[i+1];
			}
			coleccionAlumnos[coleccionAlumnos.length-1]=null;

	}*/
	
}
