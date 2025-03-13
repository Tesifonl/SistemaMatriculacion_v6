package org.iesalandalus.programacion.matriculacion.modelo;

import java.util.ArrayList;
import java.util.Collections;
import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.memoria.Alumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.memoria.Asignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.memoria.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.memoria.Matriculas;
import org.iesalandalus.programacion.matriculacion.vista.Consola;


public class Modelo {

	private Alumnos alumnos;
	private Matriculas matriculas;
	private Asignaturas asignaturas;
	private CiclosFormativos ciclosFormativos;

	
	
	public Modelo() {}
	
	public void comenzar() {
    	alumnos=new Alumnos();
    	asignaturas=new Asignaturas();
    	ciclosFormativos=new CiclosFormativos();
    	matriculas=new Matriculas();
	}
	
	public void terminar (){
		System.out.print("Hasta luego");
	}
	
	public  void insertarAlumno(Alumno alumno) throws OperationNotSupportedException {
		
		if(alumno!=null) {

				alumnos.insertar(alumno);	
	
		}else {
			throw new NullPointerException("ERROR: No se ha recibido el alumno");
		}
    }
    
    
	public Alumno buscarAlumno(Alumno alumno)  {
	  		
		if (alumno!=null) {

			//System.out.println(alumnos.buscar(alumno));
			return alumnos.buscar(alumno);

		}else {
			throw new NullPointerException("ERROR: No se ha recibido el alumno");
		}
      }
    
    
    
    
    
	public void borrarAlumno(Alumno alumno) throws OperationNotSupportedException {

		if (alumno!=null) {
	  			
				alumnos.borrar(alumno);
	  			
		}else {
			throw new NullPointerException("ERROR: No se ha recibido el alumno");
		}
      }
    
    
	public ArrayList<Alumno> getAlumnos()  {
  		
	
  			 if(alumnos.getTamano()>0) {
  				 ArrayList<Alumno> nuevoArrayList=alumnos.get();
  				 /*Collections.sort(nuevoArrayList);
  		
  				 
  				 for(Alumno alumno: nuevoArrayList)
  				 {
  					 System.out.println("Alumno: "+alumno);
  				 }*/
  				 return nuevoArrayList;
  			 }
  			 else {
  				throw new NullPointerException(" No existen alumnos en el sistema");
  				
  			 }

      }
    
	
	public void insertarAsignatura(Asignatura asignatura) throws OperationNotSupportedException {
	  		
		if(asignatura!=null) {
	
				asignaturas.insertar(asignatura);

		}else {
			throw new NullPointerException("ERROR: No se ha recibido la asignatura");
		}
      }
    
	
    public Asignatura buscarAsignatura(Asignatura asignatura) {
	  		
    	if(asignatura!=null) {

    		if (asignaturas.buscar(asignatura)!=null) {
    			//System.out.println(asignaturas.buscar(asignatura));
    			return asignaturas.buscar(asignatura);
    		}
    		else {
    			throw new NullPointerException("ERROR: No se ha encontrado asignaturas por ese codigo");
    		}

    	}else {
    		throw new NullPointerException("ERROR: No se ha recibido la asignatura");
    	}
      }
    
    public void borrarAsignatura(Asignatura asignatura) throws OperationNotSupportedException {
	  		
    	if(asignatura!=null) {

	 
    			asignaturas.borrar(asignatura);
    		
	  			
    	}else {
    		throw new NullPointerException("ERROR: No se ha recibido la asignatura");
    	}
      }

    
    public ArrayList<Asignatura> getAsignaturas()  {
  		
 
  			 if(asignaturas.getTamano()>0) {
  				 ArrayList<Asignatura>nuevoArrayList=asignaturas.get();
  				 Collections.sort(nuevoArrayList);


  				 return nuevoArrayList;
  				 }
  			 else {
  				 
  				throw new NullPointerException("No existen asignaturas dadas incluidas en el sistema");
  			 }	

      }
    
	
    public void insertarCicloFormativo(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
	  		
    	if(cicloFormativo!=null) {

	  			ciclosFormativos.insertar(cicloFormativo);
	  			

    	}else {
    		throw new NullPointerException("ERROR: No se ha recibido el ciclo formativo");
    	}
      }
    

    public CicloFormativo  buscarCicloFormativo(CicloFormativo cicloFormativo) {
	  		
    	if(cicloFormativo!=null) {

    		if(ciclosFormativos.buscar(cicloFormativo)!=null) {
    	
    			//System.out.println(ciclosFormativos.buscar(cicloFormativo));
    			return ciclosFormativos.buscar(cicloFormativo);
    		}
    		else {
    			throw new NullPointerException("ERROR: No se encuentra ciclos formativos para ese codigo");
    		}
	
    	}else {
    		throw new NullPointerException("ERROR: No se ha recibido el ciclo formativo");
    	}
      }
    
    
    public void  borrarCicloFormativo(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
	  		
    	if(cicloFormativo!=null) {

    			ciclosFormativos.borrar(cicloFormativo);

    	}else {
    		throw new NullPointerException("ERROR: No se ha recibido el ciclo formativo");
    	}
      }
    
    
    public ArrayList<CicloFormativo> getCiclosFormativos()  {

  			 if(ciclosFormativos.getTamano()>0) {
  				 ArrayList<CicloFormativo>nuevoArrayList=ciclosFormativos.get();
  				 /*Collections.sort(nuevoArrayList);
  				
  				 for(CicloFormativo cicloFormativo: nuevoArrayList)
  				 {
  					 System.out.println("Alumno: "+cicloFormativo);
  				 }*/
  				 return nuevoArrayList;
  			 } 
  	  			
  			 else {
  				throw new NullPointerException(" No existen ciclos formativos en el sistema");
  				
  			 }
  			
      }
    
    public void  insertarMatricula(Matricula matricula) throws OperationNotSupportedException {
	  		
    	if(matricula!=null) {
			
	  			matriculas.insertar(matricula);
	  			

    	}else {
    		throw new NullPointerException("ERROR: No se ha recibido la matricula");
    	}
      }
    
    
    public Matricula buscarMatricula(Matricula matricula) throws OperationNotSupportedException {
	  		
    	if(matricula!=null) {
    		
    		if(matriculas.buscar(matricula)!=null) {

    			//System.out.println(matriculas.buscar(matricula));
    			return matriculas.buscar(matricula);
    		}
    		else {
    			
    			throw new NullPointerException("ERROR: No hay matriculas para este codigo");
    		}

    	}else {
    		throw new NullPointerException("ERROR: No se ha recibido la matricula");
    	}
      }
    
    
    public void borrarMatricula(Matricula matricula) throws OperationNotSupportedException {
	  		
    	if(matricula!=null) {

    			matriculas.borrar(matricula);

    	}else {
    		throw new NullPointerException("ERROR: No se ha recibido la matricula");
    	}
      }
    
    
    public ArrayList<Matricula> getMatriculas() throws OperationNotSupportedException  {
  	
  			 if(matriculas.getTamano()>0) {
  				
  				 ArrayList<Matricula> nuevoArrayList=matriculas.get();
  				/* Collections.sort(nuevoArrayList);

  				 
  				 for (Matricula matricula: nuevoArrayList) {
  					 System.out.println("Matricula: "+ matricula);
  					 System.out.println("Asignatura: "+matricula.getColeccionAsignaturas().toString());
  				 }*/
  				 return matriculas.get();
  			 }
  			 else {
  				throw new NullPointerException(" No existen matriculas en el sistema");
  				 
  			 }
  			

      }
    
    public ArrayList<Matricula> getMatriculas (Alumno alumno) throws OperationNotSupportedException  {
	  		
    	if(alumno!=null) {
    	
 			
 			if (matriculas.get(alumno)!=null) {
 				/*ArrayList<Matricula> nuevoArrayList=matriculas.get(alumno);
 				Collections.sort(nuevoArrayList);

 				
 				for (Matricula matricula: nuevoArrayList) {
 					System.out.println("Matricula: "+ matricula);
 					System.out.println("Asignatura: "+matricula.getColeccionAsignaturas().toString());
 				}*/
 				 return matriculas.get(alumno);
 			}
 			else {
 				throw new NullPointerException("ERROR: No hay matriculas para este alumno");
 			}			
    	}else {
    		throw new NullPointerException("ERROR: No se ha recibido el alumno para consultar la matricula");
    	}
      }
    

    public ArrayList<Matricula> getMatriculas(CicloFormativo cicloFormativo) throws OperationNotSupportedException  {
	  		
    	if(cicloFormativo!=null) {

 			if (matriculas.get(cicloFormativo)!=null) {
 				
 				/*ArrayList<Matricula> nuevoArrayList=matriculas.get(cicloFormativo);
 				Collections.sort(nuevoArrayList);

 				for (Matricula matricula: nuevoArrayList) {
 					System.out.println("Matricula: "+ matricula);
 					System.out.println("Asignatura: "+matricula.getColeccionAsignaturas().toString());
 				}*/
 				
 				 return matriculas.get(cicloFormativo); 
 			}
 			else {
 				throw new NullPointerException("ERROR: No hay matriculas para este ciclo formativo");
 			}

    	}else {
    		throw new NullPointerException("ERROR: No se ha recibido el ciclo formativo para consultar la matricula");
    	}
      }
    
    public ArrayList<Matricula>getMatriculas(String cursoAcademico) throws OperationNotSupportedException  {
	  		
    	if(cursoAcademico!=null) {
    		
 			
 			if (matriculas.get(cursoAcademico)!=null) {
 				
 				/*ArrayList<Matricula> nuevoArrayList=matriculas.get(cursoAcademico);
 	 			Collections.sort(nuevoArrayList);
 
 				for (Matricula matricula: nuevoArrayList) {
 					System.out.println("Matricula: "+ matricula);
 					System.out.println("Asignatura: "+matricula.getColeccionAsignaturas().toString());
 				}*/
 	 			
 				 return matriculas.get(cursoAcademico);
			}
			else {
				throw new NullPointerException("ERROR: No hay matriculas para este curso academico");
			}

    	}else {
    		throw new NullPointerException("ERROR: No se ha recibido el curso academico para consultar la matricula");
    	}
      }

    
}
