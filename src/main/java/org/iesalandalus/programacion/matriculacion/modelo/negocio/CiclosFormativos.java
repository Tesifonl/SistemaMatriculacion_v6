package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;

public class CiclosFormativos {
	
	private ArrayList <CicloFormativo> coleccionCiclosFormativos;

	
	public CiclosFormativos () {
		
		coleccionCiclosFormativos=new ArrayList<CicloFormativo> ();

	}
	
	public ArrayList<CicloFormativo> get() {
		ArrayList<CicloFormativo> copia=copiaProfundaCiclosFormativos();
		return copia;
	}
	
	private ArrayList<CicloFormativo> copiaProfundaCiclosFormativos() {
		
		ArrayList<CicloFormativo>copiaCicloFormativo=new ArrayList<CicloFormativo>();
		
		for(int i=0;i<coleccionCiclosFormativos.size();i++) {
			if(coleccionCiclosFormativos.get(i)!=null) {copiaCicloFormativo.add(coleccionCiclosFormativos.get(i));
			}
			else {
				copiaCicloFormativo.add(coleccionCiclosFormativos.get(i));
			}
		}
		return copiaCicloFormativo;
	}

	public int getTamano() {
		int tamano=0;
		
		for (CicloFormativo cicloFormativo: coleccionCiclosFormativos) {
			if(cicloFormativo!=null) {tamano++;}
		}
		
		return tamano;
	}

	

	
	public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {

		
		if (cicloFormativo!=null) {
			
				if(coleccionCiclosFormativos.contains(cicloFormativo)) {
					throw new OperationNotSupportedException("ERROR: Ya existe un ciclo formativo con ese codigo.");
				}else {
					coleccionCiclosFormativos.add(cicloFormativo);
					System.out.println("Ciclo formativo introducido en la lista");
				}
		}
		else {
			throw new NullPointerException("ERROR: No se puede insertar un ciclo formativo nulo.");
		}
	}
	
	
	
	public CicloFormativo buscar(CicloFormativo cicloFormativo) {
		int j=0;
		boolean encontrado=false;
		boolean noEncontrado=false;
		
		if(cicloFormativo!=null) {
			
			if(coleccionCiclosFormativos.size()>0) {
				for (int i=0;i<coleccionCiclosFormativos.size();i++) {
					if(coleccionCiclosFormativos.get(i).equals(cicloFormativo)){
					j=i;
					encontrado=true;
					}
					noEncontrado=true;
				}
				
				if (encontrado==true) {
					return coleccionCiclosFormativos.get(j);
				}else {
					System.out.println("No se ha encontrado este ciclo formativo en la coleccion");
					return null;
				}
			}else {
				throw new NullPointerException("No hay ciclos formativos incluidos en la coleccion");
			}	
		}
		else {
			throw new NullPointerException("ciclo formativo recibido nulo");
		}
	}
	

	public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
		if(cicloFormativo!=null) {
			if (coleccionCiclosFormativos.contains(cicloFormativo)) {
				coleccionCiclosFormativos.remove(cicloFormativo);
			}
			else{
				throw new OperationNotSupportedException("ERROR: No existe ningún ciclo formativo como el indicado.");
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede borrar un ciclo formativo nulo.");
		}
	}
	

}
