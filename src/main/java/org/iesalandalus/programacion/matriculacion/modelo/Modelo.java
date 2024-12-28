package org.iesalandalus.programacion.matriculacion.modelo;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Matriculas;


public class Modelo {

	private Alumnos alumnos;
	private Matriculas matriculas;
	private Asignaturas asignaturas;
	private CiclosFormativos ciclosFormativos;
	private final int CAPACIDAD=100;
	
	
	public Modelo() {}
	
	public void comenzar() {
    	alumnos=new Alumnos(CAPACIDAD);
    	asignaturas=new Asignaturas(CAPACIDAD);
    	ciclosFormativos=new CiclosFormativos(CAPACIDAD);
    	matriculas=new Matriculas(CAPACIDAD);
	}
	
	public void terminar (){
		System.out.print("Se ha terminado");
	}
	
	public  void insertarAlumno(Alumno alumno) throws OperationNotSupportedException {
		try {	
			alumnos.insertar(alumno);	

			}
			catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
		
			}
			catch(NullPointerException e) {
			System.out.println(e.getMessage());
			
			}
    }
    
    
	public Alumno buscarAlumno(Alumno alumno) throws OperationNotSupportedException {
  		try {
  			//Alumno alumno=Consola.getAlumnoPorDni();
  			if( alumnos.buscar(alumno)!=null) {
  				System.out.println("Alumno econtrado");
  				System.out.println(alumno.toString());
  				Alumno nuevaInstanciaAlumno=new Alumno(alumno);
  				return nuevaInstanciaAlumno;
  			}
  			else {
  				System.out.println("No encontrado en la coleccion");
  				return null;
  			}
  			
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
  			
  			//Alumno alumno=Consola.getAlumnoPorDni();
  			if( alumnos.buscar(alumno)!=null) {
  				System.out.println("Alumno econtrado y borrado");
  				alumnos.borrar(alumno);
  			}
  			else {
  				System.out.println("No encontrado en la coleccion");
  			}
  			
  			}
  			catch(IllegalArgumentException e) {
  				System.out.println(e.getMessage());
  		
  			}
  			catch(NullPointerException e) {
  			System.out.println(e.getMessage());
  			
  			}
      }
    
    
	public Alumno[] getAlumnos() throws OperationNotSupportedException {
  		try {
  			 if(alumnos.getTamano()>0) {
  				 Alumno[]nuevoArray=alumnos.get();
  				 boolean encontrado=false;
  				 
  				 for(int i=0;i<nuevoArray.length;i++)
  				 { if (nuevoArray[i]!=null) {
  					 System.out.println("Estos son los datos de los alumnos de la coleccion");
  					 System.out.println(nuevoArray[i]);
  				 	}
  				 	else {encontrado=true;}
  				 }
  				 return nuevoArray;
  			 }
  			 else {
  				 System.out.println(" No existen alumnos en el sistema");
  				 return null;
  			 }
  			
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
  		try {
  			asignaturas.insertar(asignatura);
  			
  			}
  			catch(IllegalArgumentException e) {
  				System.out.println(e.getMessage());
  		
  			}
  			catch(NullPointerException e) {
  			System.out.println(e.getMessage());
  			
  			}
      }
    
    public Asignatura buscarAsignatura(Asignatura asignatura){
  		try {
  			//Asignatura asignatura=Consola.getAsignaturaPorCodigo();
  			if( asignaturas.buscar(asignatura)!=null) {
  				System.out.println("Asignatura econtrada");
  				System.out.println(asignatura.toString());
  				Asignatura nuevaInstanciaAsigantura=new Asignatura(asignatura);
  				return nuevaInstanciaAsigantura;
  			}
  			else {
  				System.out.println("No encontrado en la coleccion");
  				return null;
  			}
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
  			//Asignatura asignatura=Consola.getAsignaturaPorCodigo();
  			if(asignaturas.buscar(asignatura)!=null) {
  				System.out.println("Asignatura econtrada y borrada");
  				asignaturas.borrar(asignatura);
  			}
  			else {
  				System.out.println("Asignatura no encontrada en la coleccion");
  			}
  			
  			}
  			catch(IllegalArgumentException e) {
  				System.out.println(e.getMessage());
  		
  			}
  			catch(NullPointerException e) {
  			System.out.println(e.getMessage());
  			
  			}
      }

    
    public Asignatura[] getAsignaturas()  {
  		try {
  			 if(asignaturas.getTamano()>0) {
  				 Asignatura[]nuevoArray=asignaturas.get();
  				 boolean encontrado=false;
  				 
  				 for(int i=0;i<nuevoArray.length;i++)
  				 {
  					 if (nuevoArray[i]!=null) {
  	  					 System.out.println("Estos son los datos de las asignaturas de la coleccion");
  	  					 System.out.println(nuevoArray[i]);
  	  				 }
  	  				 else {encontrado=true;}
  				 }
  				 return nuevoArray;
  				 }
  			 else {
  				 System.out.println(" No existen asignaturas en el sistema");
  				 return null;
  			 }
  			
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
  			ciclosFormativos.insertar(cicloFormativo);
  			
  			}
  			catch(IllegalArgumentException e) {
  				System.out.println(e.getMessage());
  		
  			}
  			catch(NullPointerException e) {
  			System.out.println(e.getMessage());
  			
  			}
      }
    

    public CicloFormativo  buscarCicloFormativo(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
  		try {
  			//CicloFormativo cicloFormativo=Consola.getCicloFormativoPorCodigo();
  			if( ciclosFormativos.buscar(cicloFormativo)!=null) {
  				System.out.println("Ciclo formativo econtrado");
  				System.out.println(cicloFormativo.toString());
  				CicloFormativo nuevaInstanciaCicloFormativo=new CicloFormativo(cicloFormativo);
  				return nuevaInstanciaCicloFormativo;
  			}
  			else {
  				System.out.println("No encontrado el ciclo en la coleccion");
  				return null;
  			}
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
  			//CicloFormativo cicloFormativo=Consola.getCicloFormativoPorCodigo();
  			if( ciclosFormativos.buscar(cicloFormativo)!=null) {
  				System.out.println("Ciclo formativo econtrado y borrado");
  				ciclosFormativos.borrar(cicloFormativo);
  			}
  			else {
  				System.out.println("Ciclo formativo no encontrado en la coleccion");
  			}
  			 
  			}
  			catch(IllegalArgumentException e) {
  				System.out.println(e.getMessage());
  		
  			}
  			catch(NullPointerException e) {
  			System.out.println(e.getMessage());
  			
  			}
      }
    
    public CicloFormativo[] getCiclosFormativos()  {
  		try {
  			 
  			 if(ciclosFormativos.getTamano()>0) {
  				 CicloFormativo[]nuevoArray=ciclosFormativos.get();
  				 boolean encontrado=false;
  				 
  				 for(int i=0;i<nuevoArray.length;i++)
  				 
  	  				 { if (nuevoArray[i]!=null) {
  	  					 System.out.println("Estos son los datos de los ciclos formativos de la coleccion");
  	  					 System.out.println(nuevoArray[i]);
  	  				 	}
  	  				 	else {encontrado=true;}
  					 
  	  				 }
  				 return nuevoArray;
  	  			}
  			 else {
  				 System.out.println(" No existen ciclos formativos en el sistema");
  				 return null;
  			 }
  			
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
  			/*Alumno alumno=Consola.getAlumnoPorDni();
  			System.out.println("Indica el numero de asignaturas que vas a introducir, debe ser mayor que 0");
  			Asignatura [] coleccionAsignaturas=new Asignatura[10];
  			int numeroAsignaturas=Entrada.entero();
  			for (int i=0;i<numeroAsignaturas;i++) {
  				coleccionAsignaturas[i]=Consola.getAsignaturaPorCodigo();*/
  			matriculas.insertar(matricula);
  			
  			}
  			catch(IllegalArgumentException e) {
  				System.out.println(e.getMessage());
  		
  			}
  			catch(NullPointerException e) {
  			System.out.println(e.getMessage());
  			
  			}
      }
    
    
    public Matricula buscarMatricula(Matricula matricula) throws OperationNotSupportedException {
  		try {
  			//Matricula matricula=Consola.getMatriculaPorIdentificador();
  			if( matriculas.buscar(matricula)!=null) {
  				System.out.println("Ciclo formativo econtrado");
  				System.out.println(matricula.toString());
  				Matricula nuevaInstanciaMatricula=new Matricula (matricula);
  				return nuevaInstanciaMatricula;
  			}
  			else {
  				System.out.println("No encontrada la matricula en el sistema");
  				return null;
  			}
  			 
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
    
    public void borrarMatricula(Matricula matricula) throws OperationNotSupportedException {
  		try {
  			//Matricula matricula=Consola.getMatriculaPorIdentificador();
  			if( matriculas.buscar(matricula)!=null) {
  				System.out.println("Matricula econtrada y borrada");
  				matriculas.borrar(matricula);
  			}
  			else {
  				System.out.println("Matricula no encontrada en la coleccion");
  			}
  			 
  			 
  			}
  			catch(IllegalArgumentException e) {
  				System.out.println(e.getMessage());
  		
  			}
  			catch(NullPointerException e) {
  			System.out.println(e.getMessage());
  			
  			}
      }
    
    
    public Matricula[] getMatriculas() throws OperationNotSupportedException  {
  		try {
  			 if(matriculas.getTamano()>0) {
  				 Matricula[]nuevoArray=matriculas.get();
  				 boolean encontrado=false;
  				 
  				 for(int i=0;i<nuevoArray.length;i++)
  				 {
  					if (nuevoArray[i]!=null) {
  						System.out.println("Estos son los datos de las matriculas de la coleccion");
  						System.out.println(nuevoArray[i]);
  						Asignatura[] arrayAsignaturas=nuevoArray[i].getColeccionAsignaturas();
  						for (int j=0;j<arrayAsignaturas.length;j++)
  						{ if(arrayAsignaturas[j]!=null) {
  								System.out.println("Estas son sus asignaturas");
  								System.out.println(arrayAsignaturas[j]);
  							}
  							else {encontrado=true;}
  						}
  					}else {
  						encontrado=true;
  					}
  				 }
  				 return nuevoArray;
  			 }
  			 else {
  				 System.out.println(" No existen matriculas en el sistema");
  				 return null;
  			 }
  			
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
  			//Alumno alumno=Consola.getAlumnoPorDni();
  			boolean noEncontrado=false;
  			boolean encontrado=false;
  				
  			
 			 if(matriculas.getTamano()>0) {
  				 Matricula[]nuevoArray=matriculas.get();
  				 Matricula[]nuevoArray2=matriculas.get();
  				 int contador=0;
  				 
  				 for(int i=0;i<nuevoArray.length;i++)
  				 {
  					if (nuevoArray[i]!=null && nuevoArray[i].getAlumno().equals(alumno)) {
  					System.out.println("Estos son los datos de las matriculas para el alumno seleccionado de la coleccion");
  					System.out.println(nuevoArray[i]);
  					contador++;
  					nuevoArray2[contador-1]=nuevoArray[i];
  					encontrado=true;
  					}
  					else
  					{
  					noEncontrado=true;
  					}
  				 }
  				 
  				 if(encontrado==true){
  					 return nuevoArray2;
  				 }
  				 else {
  					 return null;
  				 }
  			 }
  			 else {
  				 System.out.println(" No existen matriculas en el sistema");
  				 return null;
  			 }
  			 
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
  				 Matricula[] nuevoArray1=matriculas.get();
  				 Asignatura[] nuevoArray2=null;
  				 Matricula[] nuevoArray3=null;
  				 int contador=0;
  				 int otro=0;
  				 
  				 /*mostrarCiclosFormativos();
  				 System.out.println("Nota: Aunque no se haya dado de alta el ciclo, puede existir una matricula con él asignado");
  				 CicloFormativo cicloFormativo=Consola.getCicloFormativoPorCodigo();*/
  				 
  				 
  				 for (int i=0; i<nuevoArray1.length-1;i++)
  				 {
  					 if (nuevoArray1[i]!=null) {
  					 nuevoArray2=nuevoArray1[i].getColeccionAsignaturas();
  				 	 for(int j=0;j<nuevoArray2.length-1;j++) {
  				 		 if(nuevoArray2[j]==null) 
  				 			 
  				 		 {
  				 			 otro=1;
  				 		 }
  				 		 else if(nuevoArray2[j].getCicloFormativo().equals(cicloFormativo))
  				 		 	{
  				 			System.out.println("Para ese ciclo se ha encontrado las siguientes matriculas");
  				 		 	System.out.println(nuevoArray1[i]);
  				 		 	contador++;
  				 		 	nuevoArray3[contador-1]=nuevoArray1[i];
  				 		 	}
  				 		 else{
  				 			otro=2;
  				 		 }
  				 	  }
  					 }
  					otro=3;
  				 }
  				 
  			    switch (otro) {
  	            case 1:
  	                System.out.println("No hay asignaturas dadas de alta en las matriculas");
  	                break;
  	            case 2:
  	                System.out.println("No existe esta asignatura en ninguna matricula");
  	                break;
  	            case 3:
  	                System.out.println("No existen mas matriculas dadas de alta");
  	                break;
  	          default:
                  System.out.println("No existen mas matriculas dadas de alta");
  			    }
  			   
  			   return nuevoArray3;
  				 			
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
  				 /*System.out.println("Introduce el curso en formato DD-DD, por ejemplo 23-24");
				 String cursoAcademico=Entrada.cadena();*/	 
				 Matricula[] nuevoArray=matriculas.get();
				 Matricula[] nuevoArray2=null;
				 int contador=0;
				 int otro=0;
  			
  				 
  	 			 if(matriculas.getTamano()>0) {
  	  				 
  	  				 for(int i=0;i<nuevoArray.length;i++)
  	  				 {
  	  					if(nuevoArray[i]==null) {
  	  						otro=1;
  	  					}
  	  					else if (nuevoArray[i].getCursoAcademico().equals(cursoAcademico)) {
  	  						System.out.println("Estos son los datos de las matriculas para el curso academico seleccionado de la coleccion");
  	  						System.out.println(nuevoArray[i]);
  	  						contador++;
  	  						nuevoArray2[contador-1]=nuevoArray[i];
  	  					}
  	  					else
  	  					{
  	  					 otro=2;
  	  					}
  	  				 }
  	  			 }
  	  			 else {
  	  				 otro=3;
  	  			 }
   			  switch (otro) {
   			  	case 1:
  	                System.out.println("No existen ninguna matricula");
  	                break;
  	            case 2:
  	                System.out.println("No existen ninguna matricula para ese curso");
  	                break;
  	            case 3:
  	                System.out.println("No existen mas matriculas para ese curso dadas de alta");
  	                break;
  	            default:
  	            	System.out.println("No existen mas matriculas para ese curso dadas de alta");
  			    }
   			  return nuevoArray2;
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
