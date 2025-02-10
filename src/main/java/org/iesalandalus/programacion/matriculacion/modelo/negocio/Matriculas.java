package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import java.util.ArrayList;
import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;

public class Matriculas  {

	private ArrayList<Matricula>  coleccionMatriculas;

	
	public Matriculas () {

		coleccionMatriculas=new ArrayList<Matricula> ();
		

	}
	
	public ArrayList<Matricula> get() throws OperationNotSupportedException {
		ArrayList<Matricula> copia=copiaProfundaMatriculas();
		return copia;
	}
	
	private ArrayList<Matricula> copiaProfundaMatriculas() throws OperationNotSupportedException {
		
		ArrayList<Matricula>copiaMatricula=new ArrayList<Matricula>();
		
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


	
	public void insertar(Matricula matricula) throws OperationNotSupportedException {
		
		if(matricula!=null) {
			if(coleccionMatriculas.contains(matricula)) {
				throw new OperationNotSupportedException("ERROR: Ya existe una matricula con ese codigo.");
			}else {
				coleccionMatriculas.add(matricula);
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede insertar una matrícula nula.");
		}
	}

	
	public Matricula buscar(Matricula matricula) throws OperationNotSupportedException {
		int j=0;
		boolean encontrado=false;
		boolean noEncontrado=false;
		
		if(matricula!=null) {
			
			if(coleccionMatriculas.size()>0) {
				for (int i=0;i<coleccionMatriculas.size();i++) {
					if(coleccionMatriculas.get(i).equals(matricula)){
					j=i;
					encontrado=true;
					}
					noEncontrado=true;
				}
				
				if (encontrado==true) {
					return coleccionMatriculas.get(j);
				}else {
					System.out.println("No se ha encontrado este alumno en la coleccion");
					return null;
				}
			}else {
				throw new NullPointerException("No hay matriculas incluidas en la coleccion");
			}
		}
		else {
			throw new NullPointerException("matricula recibido nulo");
		}
	}
	
	
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
	

	
	public ArrayList<Matricula> get (Alumno alumno) throws OperationNotSupportedException{
		boolean encontrado=false;
		boolean otro=false;
		ArrayList<Matricula> nuevaColeccion=new ArrayList<Matricula>();
		
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
	
	public ArrayList<Matricula> get (String cursoAcademico){
		boolean encontrado=false;
		boolean otro=false;
		ArrayList<Matricula> nuevaColeccion=new ArrayList<Matricula>();
		
		
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
	
	public ArrayList<Matricula> get (CicloFormativo cicloFormativo) throws OperationNotSupportedException{
		boolean encontrado=false;
		boolean otro=false;
		ArrayList<Matricula> nuevaColeccion=new ArrayList<Matricula>();
	
	
		
		if (cicloFormativo!=null) {
			for (int i =0;i<coleccionMatriculas.size();i++) {
				if (coleccionMatriculas.get(i)!=null) {
					ArrayList<Asignatura> nuevaColeccionAsignatura=coleccionMatriculas.get(i).getColeccionAsignaturas();
					
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
