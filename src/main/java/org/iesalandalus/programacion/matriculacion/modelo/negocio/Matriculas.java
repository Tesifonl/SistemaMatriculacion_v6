package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;

public class Matriculas {

	private Matricula [] coleccionMatriculas;
	private int tamano;
	private int capacidad;
	
	public Matriculas (int capacidad) {
		if (capacidad>0) {
			coleccionMatriculas=new Matricula [capacidad];
		}
		else {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
	}
	
	public Matricula[] get() throws OperationNotSupportedException {
		Matricula[] copia=copiaProfundaMatriculas();
		return copia;
	}
	
	private Matricula[] copiaProfundaMatriculas() throws OperationNotSupportedException {
		Matricula[]copiaMatriculas=new Matricula[coleccionMatriculas.length];
		
		for(int i=0;i<coleccionMatriculas.length;i++) {
			if(coleccionMatriculas[i]!=null) {copiaMatriculas[i]= new Matricula(coleccionMatriculas[i]);
			}
			else {
				copiaMatriculas[i]=null;
			}
		}
		return copiaMatriculas;
	}

	public int getTamano() {
		int tamano=0;
		
		for (int i=0;i<coleccionMatriculas.length;i++) {
			if(coleccionMatriculas[i]!=null) {tamano++;}
		}
		
		return tamano;
	}

	
	public int getCapacidad() {
		capacidad=coleccionMatriculas.length;
		
		return capacidad;
	}
	
	/*public void insertar(Matricula matricula) throws OperationNotSupportedException {
		boolean noEncontrado=false;
		boolean encontrado=false;
	
		
		if (matricula!=null) {
			for(int i=0;i<coleccionMatriculas.length;i++) {
				if(coleccionMatriculas[i]!=null && coleccionMatriculas[i].equals(matricula)) {
					noEncontrado=false;
					encontrado=true;
				}
				else {
					noEncontrado=true;
				}
			}
			
			if (noEncontrado==true && encontrado==false && getTamano()<getCapacidad()) {
				coleccionMatriculas[getTamano()]=matricula;		
			}
			
			else if(encontrado==true) {
				throw new OperationNotSupportedException("ERROR: Ya existe una matrícula con ese identificador.");	
			}
			else {
				throw new OperationNotSupportedException("ERROR: No se aceptan más matrículas.");
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede insertar una matrícula nula.");
		}
	}*/
	
	public void insertar(Matricula matricula) throws OperationNotSupportedException {
		if(matricula!=null) {
			int indice=buscarIndice(matricula);
			
			if(indice==999999) {
				if(getTamano()<getCapacidad()) {
					coleccionMatriculas[getTamano()]=matricula;
				}
				else {
					throw new OperationNotSupportedException("ERROR: No se aceptan más matrículas.");
				}
			}
			else{
				throw new OperationNotSupportedException("ERROR: Ya existe una matrícula con ese identificador.");
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede insertar una matrícula nula.");
		}
	}
	
	/*private int buscarIndice(Matricula matricula) throws OperationNotSupportedException {
		int indice=0;
		
		if (matricula!=null) {
			for(int i=0;i<coleccionMatriculas.length;i++) {
				if(coleccionMatriculas[i]!=null && coleccionMatriculas[i].equals(matricula)) {
					indice=i;
				}
				else {
					throw new OperationNotSupportedException("Ya existe esta matricula");
				}
				
			}
		}
		else {
			throw new NullPointerException("Se ha recibido una matricula nulo");
		}
		return indice;
	}*/
	
	private int buscarIndice(Matricula matricula) throws OperationNotSupportedException {
		int indice=999999;
		boolean encontrado=false;
		boolean noEncontrado=false;
		
		if (matricula!=null) {
			for(int i=0;i<coleccionMatriculas.length;i++) {
				if(coleccionMatriculas[i]!=null && coleccionMatriculas[i].equals(matricula)) {
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
			throw new NullPointerException("Se ha recibido una matricula nulo");
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
	
	
	public Matricula buscar(Matricula matricula) throws OperationNotSupportedException {
		boolean encontrado=false;
		boolean otro=false;
		int indice=0;
		Matricula copiaAlumno=null;
		
		if(matricula!=null) {
			for (int i =0;i<coleccionMatriculas.length;i++) {
				if (coleccionMatriculas[i]!=null && coleccionMatriculas[i].equals(matricula)) {
					encontrado=true;
					indice=i;
					copiaAlumno=new Matricula(coleccionMatriculas[indice]);
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
			throw new NullPointerException("matricula recibido nulo");
		}
	}
	
	/*public void borrar(Matricula matricula) throws OperationNotSupportedException {
		boolean encontrado=false;
		boolean otro=false;
		int indice=0;
		
		if(matricula!=null) {
			for (int i =0;i<coleccionMatriculas.length;i++) {
				if (coleccionMatriculas[i]!=null && coleccionMatriculas[i].equals(matricula)) {
					encontrado=true;
					indice=i;
				}
				else {
					otro=false;
				}
			}
			if (encontrado==true) {
				coleccionMatriculas[indice]=null;
				desplazarUnaPosicionHaciaIzquiera(indice);
			}
			else {
				throw new OperationNotSupportedException("ERROR: No existe ninguna matrícula como la indicada.");
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede borrar una matrícula nula.");
		}
	}*/
	
	public void borrar(Matricula matricula) throws OperationNotSupportedException {
		if(matricula!=null) {
			int indice=buscarIndice(matricula);
			
			if(indice!=999999) {
				coleccionMatriculas[indice]=null;
				desplazarUnaPosicionHaciaIzquiera(indice);
			}
			else{
				throw new OperationNotSupportedException("ERROR: No existe ninguna matrícula como la indicada.");
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede borrar una matrícula nula.");
		}
	}
	
	
	private void desplazarUnaPosicionHaciaIzquiera(int indice) {
		
		for (int i =indice;i<coleccionMatriculas.length-1;i++) {
			coleccionMatriculas[i]=coleccionMatriculas[i+1];
			}
			coleccionMatriculas[coleccionMatriculas.length-1]=null;

	}
	
	public Matricula[] get (Alumno alumno) throws OperationNotSupportedException{
		boolean encontrado=false;
		boolean otro=false;
		Matricula [] nuevaColeccion=new Matricula [coleccionMatriculas.length];
		int contador=0;
		
		if (alumno!=null) {	
			for (int i =0;i<coleccionMatriculas.length;i++) {
				if (coleccionMatriculas[i]!=null && coleccionMatriculas[i].getAlumno().equals(alumno)) {
					encontrado=true;
					contador++;
					nuevaColeccion[contador-1]=coleccionMatriculas[i];	
						
				}
				else {
					otro=false;
				}
			}	
		
			if (encontrado==true) {
				return nuevaColeccion;
			}
			else {
				return null;
			}
		}	
		else {
			throw new NullPointerException("ERROR: Se ha recibido un alumno nulo");
		}
	}
	
	public Matricula[] get (String cursoAcademico){
		boolean encontrado=false;
		boolean otro=false;
		Matricula [] nuevaColeccion=new Matricula [coleccionMatriculas.length];
		int contador=0;
		
		if (cursoAcademico!=null) {
			
			for (int i =0;i<coleccionMatriculas.length;i++) {
				if (coleccionMatriculas[i]!=null && coleccionMatriculas[i].getCursoAcademico().equals(cursoAcademico)) {
					encontrado=true;
					contador++;
					nuevaColeccion[contador-1]=coleccionMatriculas[i];	
						
				}
				else {
					otro=false;
				}
			}	
		
			if (encontrado==true) {
				return nuevaColeccion;
			}
			else {
				return null;
			}
		}
		else {
			throw new NullPointerException("ERROR: Se ha recibido un curso academico nulo");
		}
	}
	
	public Matricula[] get (CicloFormativo cicloFormativo) throws OperationNotSupportedException{
		boolean encontrado=false;
		boolean otro=false;
		Matricula [] nuevaColeccion=new Matricula [coleccionMatriculas.length];
	
		
		int contador=0;
		
		if (cicloFormativo!=null) {
			for (int i =0;i<coleccionMatriculas.length;i++) {
				if (coleccionMatriculas[i]!=null) {
					Asignatura [] nuevaColeccionAsignatura=coleccionMatriculas[i].getColeccionAsignaturas();
					
					for (int j=0;j<nuevaColeccionAsignatura.length;j++)
						if (nuevaColeccionAsignatura[j]!=null && nuevaColeccionAsignatura[j].getCicloFormativo().equals(cicloFormativo))
							{encontrado=true;
							contador++;
							nuevaColeccion[contador-1]=coleccionMatriculas[i];	
						
							}
						else {
							otro=false;
						}
					}
							
				}
				if (encontrado==true) {
					return nuevaColeccion;
				}
				else {
					return null;
				}
			
		}
		else {
			throw new NullPointerException("ERROR: Se ha recibido un ciclo formativo nulo");
		}
	
	}
}
