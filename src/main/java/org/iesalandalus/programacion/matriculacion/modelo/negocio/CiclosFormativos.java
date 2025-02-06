package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;

public class CiclosFormativos {
	
	private List <CicloFormativo> coleccionCiclosFormativos;
	//private int tamano;
	//private int capacidad;
	
	public CiclosFormativos () {
		
		coleccionCiclosFormativos=new ArrayList<CicloFormativo> ();

	}
	
	public List<CicloFormativo> get() {
		List<CicloFormativo> copia=copiaProfundaCiclosFormativos();
		return copia;
	}
	
	private List<CicloFormativo> copiaProfundaCiclosFormativos() {
		
		List<CicloFormativo>copiaCicloFormativo=new ArrayList<CicloFormativo>();
		
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
			if(coleccionCiclosFormativos!=null) {tamano++;}
		}
		
		return tamano;
	}

	
	/*public int getCapacidad() {
		capacidad=coleccionCiclosFormativos.length;
		
		return capacidad;
	}*/
	
	public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
		boolean noEncontrado=false;
		boolean encontrado=false;
	
		
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
	
	/*private int buscarIndice(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
		int indice=0;
		
		if (cicloFormativo!=null) {
			for(int i=0;i<coleccionCiclosFormativos.length;i++) {
				if(coleccionCiclosFormativos[i]!=null && coleccionCiclosFormativos[i].equals(cicloFormativo)) {
					indice=i;
				}
				else {
					throw new OperationNotSupportedException("Ya existe este ciclo formativo");
				}
				
			}
		}
		else {
			throw new NullPointerException("Se ha recibido un ciclo formativo nulo");
		}
		return indice;
	}*/
	
	/*private int buscarIndice(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
		int indice=999999;
		boolean encontrado=false;
		boolean noEncontrado=false;
		
		if (cicloFormativo!=null) {
			for(int i=0;i<coleccionCiclosFormativos.length;i++) {
				if(coleccionCiclosFormativos[i]!=null && coleccionCiclosFormativos[i].equals(cicloFormativo)) {
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
	}
	*/
	
	public CicloFormativo buscar(CicloFormativo cicloFormativo) {
		int j=0;
		boolean encontrado,noEncontrado=false;
		
		if(cicloFormativo!=null) {
			for (int i=0;i<coleccionCiclosFormativos.size();i++) {
				if(coleccionCiclosFormativos.get(i).equals(cicloFormativo)){
				j=i;
				encontrado=true;
				}
				noEncontrado=true;
			}
			
			if (encontrado=true) {
				return coleccionCiclosFormativos.get(j);
			}else {
				System.out.println("No se ha encontrado este ciclo formativo en la coleccion");
				return null;
			}
				
		}
		else {
			throw new NullPointerException("ciclo formativo recibido nulo");
		}
	}
	
	/*public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
		boolean encontrado=false;
		boolean otro=false;
		int indice=0;
		
		if(cicloFormativo!=null) {
			for (int i =0;i<coleccionCiclosFormativos.length;i++) {
				if (coleccionCiclosFormativos[i]!=null && coleccionCiclosFormativos[i].equals(cicloFormativo)) {
					encontrado=true;
					indice=i;
				}
				else {
					otro=false;
				}
			}
			if (encontrado==true) {
				coleccionCiclosFormativos[indice]=null;
				desplazarUnaPosicionHaciaIzquiera(indice);
			}
			else {
				throw new OperationNotSupportedException("ERROR: No existe ningún ciclo formativo como el indicado.");
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede borrar un ciclo formativo nulo.");
		}
	}*/
	
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
	
	/*private void desplazarUnaPosicionHaciaIzquiera(int indice) {
		
		for (int i =indice;i<coleccionCiclosFormativos.length-1;i++) {
			coleccionCiclosFormativos[i]=coleccionCiclosFormativos[i+1];
			}
			coleccionCiclosFormativos[coleccionCiclosFormativos.length-1]=null;

	}*/
}
