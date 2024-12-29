package org.iesalandalus.programacion.matriculacion.controlador;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.vista.Vista;

public class Controlador {
	
	private Modelo modelo;
	private Vista vista;
	
	
	public Controlador(Modelo modelo, Vista vista) {
		
		if (modelo!=null && vista!=null) {
			this.modelo=modelo;
			this.vista=vista;
			vista.setControlador(this);
			
			
		}else {
			throw new IllegalArgumentException("ERROR: No se ha recibido o la vista o el controlador.");
		}
	}
	
	public void comenzar() throws OperationNotSupportedException {
		modelo.comenzar();
		vista.comenzar();;
	}
	
	public void terminar() {
		modelo.terminar();
		vista.terminar();
		
	}
	
	public  void insertarAlumno(Alumno alumno) throws OperationNotSupportedException {
		
		try {
			modelo.insertarAlumno(alumno);
		}
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());	
		}
		catch(NullPointerException e) {
		System.out.println(e.getMessage());}
    }
    
    
	public Alumno buscarAlumno(Alumno alumno) throws OperationNotSupportedException {
	  	
		try {
			return modelo.buscarAlumno(alumno);
		}				
		
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return null;
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
			return null;
		}
     }
    
    
    
	public void borrarAlumno(Alumno alumno) throws OperationNotSupportedException {
		
		try {
			modelo.borrarAlumno(alumno);
		}				
		
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());}
    }
    
    
	public Alumno[] getAlumnos() throws OperationNotSupportedException {
  		
		try {
			return modelo.getAlumnos();
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return null;
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
			return null;
		}
    }
    
	
	public void insertarAsignatura(Asignatura asignatura) throws OperationNotSupportedException {
	  		
		try{
			modelo.insertarAsignatura(asignatura);
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());}
    }
    
    public Asignatura buscarAsignatura(Asignatura asignatura){
	  	
    	try {	
    	return modelo.buscarAsignatura(asignatura);
	  	}				
	  	
	  	catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return null;
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
			return null;
		}
    }
    
    public void borrarAsignatura(Asignatura asignatura) throws OperationNotSupportedException {
	  	
    	try {
    		modelo.borrarAsignatura(asignatura);
    	}				
    	catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());}
    }

    
    public Asignatura[] getAsignaturas()  {
    	try {
    	return modelo.getAsignaturas();
    	}
		
    	catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return null;
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
			return null;
		}
    }
    
	
    public void insertarCicloFormativo(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
	  	
    	try {
    		modelo.insertarCicloFormativo(cicloFormativo);
    	}				
    	catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());}
    }
    

    public CicloFormativo  buscarCicloFormativo(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
	  		
    	try{
    		return modelo.buscarCicloFormativo(cicloFormativo);
    	}				
    	catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return null;
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
			return null;
		}
    }
    
    
    public void  borrarCicloFormativo(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
	  	
    	try {
    		modelo.borrarCicloFormativo(cicloFormativo);
    	}				
    	catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());}
    }
    
    public CicloFormativo[] getCiclosFormativos()  {
    	
    	try{
    		return modelo.getCiclosFormativos();
    	}				
    	catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return null;
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
			return null;
    	}
    }
    
    public void  insertarMatricula(Matricula matricula) throws OperationNotSupportedException {
	  	
    	try {
    		modelo.insertarMatricula(matricula);
    	}				
    	catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());}
    }
    
    
    public Matricula buscarMatricula(Matricula matricula) throws OperationNotSupportedException {
	  	
    	try {	
	  		return modelo.buscarMatricula(matricula);
    	}				
    	catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return null;
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
			return null;}
      }
    
    
    public void borrarMatricula(Matricula matricula) throws OperationNotSupportedException {
	  	
    	try {
    		modelo.borrarMatricula(matricula);
    	}				
    	catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());}
      }
    
    
    public Matricula[] getMatriculas() throws OperationNotSupportedException  {
    	try {
    		return modelo.getMatriculas();
    	}				
    	catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return null;
		}
		catch(NullPointerException e) {
		System.out.println(e.getMessage());
		return null;
		}
      }
    
    public Matricula[] getMatriculas (Alumno alumno) throws OperationNotSupportedException  {
	  	
    	try {
    		return modelo.getMatriculas(alumno);
    	}				
    	catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return null;
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
			return null;
			}
      }

    public Matricula[] getMatriculas(CicloFormativo cicloFormativo) throws OperationNotSupportedException  {
	  	
    	try {
    		return modelo.getMatriculas(cicloFormativo);
    	}				
    	catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return null;
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
			return null;
			}
      }
    
    public Matricula[] getMatriculas(String cursoAcademico) throws OperationNotSupportedException  {
	  		
    	try {
    		return modelo.getMatriculas(cursoAcademico);
    	}				
    	catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return null;
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
			return null;
			}
      }


}
