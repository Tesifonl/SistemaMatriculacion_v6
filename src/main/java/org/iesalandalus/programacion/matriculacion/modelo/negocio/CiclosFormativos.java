package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import java.util.ArrayList;
import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
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
		
		for(CicloFormativo cicloFormativo: coleccionCiclosFormativos) {
			copiaCicloFormativo.add(cicloFormativo);
			}
	
		return copiaCicloFormativo;
	}

	public int getTamano() {
		int tamano=0;
		
		for (int i=0;i<coleccionCiclosFormativos.size();i++) {
			if(coleccionCiclosFormativos.get(i)!=null) {tamano++;}
		}
		
		return tamano;
	}

	

	
	public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {

		
		if (cicloFormativo!=null) {
			
				if(coleccionCiclosFormativos.contains(cicloFormativo)) {
					throw new OperationNotSupportedException("ERROR: Ya existe un ciclo formativo con ese codigo.");
				}else {
					coleccionCiclosFormativos.add(cicloFormativo);
				}
		}
		else {
			throw new NullPointerException("ERROR: No se puede insertar un ciclo formativo nulo.");
		}
	}
	
	
	
	public CicloFormativo buscar(CicloFormativo cicloFormativo) {
		boolean encontrado=false;
		boolean noEncontrado=false;
		CicloFormativo cicloFormativoArrayCreado=null;
		
		if(cicloFormativo!=null) {
				
				if(coleccionCiclosFormativos.size()>0) {
					
					for (CicloFormativo cicloFormativoArray:coleccionCiclosFormativos) {
						
					if(cicloFormativoArray.equals(cicloFormativo)) {
						encontrado=true;
						cicloFormativoArrayCreado=cicloFormativoArray;
					}else {
						noEncontrado=true;
					}	
				}
					if (encontrado!=true && noEncontrado==true) {
						return null;
					}else {
						return cicloFormativoArrayCreado;
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
