package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;

public class Matriculas {

	private List<Matricula>  coleccionMatriculas;
	//private int tamano;
	//private int capacidad;
	
	public Matriculas () {

		coleccionMatriculas=new ArrayList<Matricula> ();

	}
	
	public List<Matricula> get() throws OperationNotSupportedException {
		List<Matricula> copia=copiaProfundaMatriculas();
		return copia;
	}
	
	private List<Matricula> copiaProfundaMatriculas() throws OperationNotSupportedException {
		
		List<Matricula>copiaMatricula=new ArrayList<Matricula>();
		
		for(int i=0;i<coleccionMatriculas.size();i++) {
			if(coleccionMatriculas.get(i)!=null) {copiaMatricula.add(coleccionMatriculas.get(i));
			}
			else {
				copiaMatricula.add(coleccionMatriculas.get(i));
			}
		}
		return copiaMatricula;
	}

	public int getTamano() {
		int tamano=0;
		
		for (int i=0;i<coleccionMatriculas.size();i++) {
			if(coleccionMatriculas.get(i)!=null) {tamano++;}
		}
		
		return tamano;
	}

	
	/*public int getCapacidad() {
		capacidad=coleccionMatriculas.length;
		
		return capacidad;
	}*/
	
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
			if(coleccionMatriculas.contains(matricula)) {
				throw new OperationNotSupportedException("ERROR: Ya existe una matricula con ese codigo.");
			}else {
				coleccionMatriculas.add(matricula);
				System.out.println("Matricula introducido en la lista");
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
	
	/*private int buscarIndice(Matricula matricula) throws OperationNotSupportedException {
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
	*/
	
	public Matricula buscar(Matricula matricula) throws OperationNotSupportedException {
		int j=0;
		boolean encontrado,noEncontrado=false;
		
		if(matricula!=null) {
			for (int i=0;i<coleccionMatriculas.size();i++) {
				if(coleccionMatriculas.get(i).equals(matricula)){
				j=i;
				encontrado=true;
				}
				noEncontrado=true;
			}
			
			if (encontrado=true) {
				return coleccionMatriculas.get(j);
			}else {
				System.out.println("No se ha encontrado este alumno en la coleccion");
				return null;
			}
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
			if (coleccionMatriculas.contains(matricula)) {
				coleccionMatriculas.remove(matricula);
			}
			else{
				throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede borrar una matrícula nula.");
		}
	}
	
	
	/*private void desplazarUnaPosicionHaciaIzquiera(int indice) {
		
		for (int i =indice;i<coleccionMatriculas.length-1;i++) {
			coleccionMatriculas[i]=coleccionMatriculas[i+1];
			}
			coleccionMatriculas[coleccionMatriculas.length-1]=null;

	}*/
	
	public List<Matricula> get (Alumno alumno) throws OperationNotSupportedException{
		boolean encontrado=false;
		boolean otro=false;
		List<Matricula> nuevaColeccion=new ArrayList<Matricula>();
		
		if (alumno!=null) {	
			for (int i =0;i<coleccionMatriculas.size();i++) {
				if (coleccionMatriculas.get(i)!=null && coleccionMatriculas.get(i).getAlumno().equals(alumno)) {
					encontrado=true;
					nuevaColeccion.add(coleccionMatriculas.get(i));	
						
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
	
	public List<Matricula> get (String cursoAcademico){
		boolean encontrado=false;
		boolean otro=false;
		List<Matricula> nuevaColeccion=new ArrayList<Matricula>();
		
		
		if (cursoAcademico!=null) {
			
			for (int i =0;i<coleccionMatriculas.size();i++) {
				if (coleccionMatriculas.get(i)!=null && coleccionMatriculas.get(i).getCursoAcademico().equals(cursoAcademico)) {
					encontrado=true;
					nuevaColeccion.add(coleccionMatriculas.get(i));	
						
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
	
	public List<Matricula> get (CicloFormativo cicloFormativo) throws OperationNotSupportedException{
		boolean encontrado=false;
		boolean otro=false;
		List<Matricula> nuevaColeccion=new ArrayList<Matricula>();
	
	
		
		if (cicloFormativo!=null) {
			for (int i =0;i<coleccionMatriculas.size();i++) {
				if (coleccionMatriculas.get(i)!=null) {
					List<Asignatura> nuevaColeccionAsignatura=coleccionMatriculas.get(i).getColeccionAsignaturas();
					
					for (int j=0;j<nuevaColeccionAsignatura.size();j++)
						if (nuevaColeccionAsignatura.get(j)!=null && nuevaColeccionAsignatura.get(j).getCicloFormativo().equals(cicloFormativo))
							{encontrado=true;
							nuevaColeccion.add(coleccionMatriculas.get(i));					
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
