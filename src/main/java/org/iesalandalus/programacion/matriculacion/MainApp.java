package org.iesalandalus.programacion.matriculacion;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.dominio.Curso;
import org.iesalandalus.programacion.matriculacion.dominio.EspecialidadProfesorado;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.negocio.Matriculas;
import org.iesalandalus.programacion.matriculacion.vista.Consola;
import org.iesalandalus.programacion.matriculacion.vista.Opcion;
import org.iesalandalus.programacion.utilidades.Entrada;

public class MainApp {
	
    public static final int CAPACIDAD=3;
    public static Alumnos alumnos;
    public static Matriculas matriculas;
    public static Asignaturas asignaturas;
    public static CiclosFormativos ciclosFormativos;
    
    
    private static void ejecutarOpcion(Opcion opcion) throws OperationNotSupportedException {
    	
   
    		if (opcion.equals(Opcion.INSERTAR_ALUMNO)) {
    			insertarAlumno();
    		}
    		if (opcion.equals(Opcion.BORRAR_ALUMNO)) {
    			borrarAlumno();
    		}
    		if (opcion.equals(Opcion.BUSCAR_ALUMNO)) {
    			buscarAlumno();
    		}
    		if (opcion.equals(Opcion.MOSTRAR_ALUMNOS)) {
    			mostrarAlumnos();
    		}
    		
       		if (opcion.equals(Opcion.INSERTAR_ASIGNATURA)) {
    			insertarAsignatura();
    		}
    		if (opcion.equals(Opcion.BORRAR_ASIGNATURA)) {
    			borrarAsignatura();
    		}
    		if (opcion.equals(Opcion.BUSCAR_ASIGNATURA)) {
    			buscarAsignatura();
    		}
    		if (opcion.equals(Opcion.MOSTRAR_ASIGNATURAS)) {
    			mostrarAsignaturas();
    		}
    		
       		if (opcion.equals(Opcion.INSERTAR_CICLO_FORMATIVO)) {
    			insertarCicloFormativo();
    		}
    		if (opcion.equals(Opcion.BORRAR_CICLO_FORMATIVO)) {
    			borrarCicloFormativo();
    		}
    		if (opcion.equals(Opcion.BUSCAR_CICLO_FORMATIVO)) {
    			buscarCicloFormativo();
    		}
    		if (opcion.equals(Opcion.MOSTRAR_CICLOS_FORMATIVOS)) {
    			mostrarCiclosFormativos();
    		}
    		if (opcion.equals(Opcion.INSERTAR_MATRICULA)) {
    			insertarMatricula();
    		}
    		if (opcion.equals(Opcion.BUSCAR_MATRICULA)) {
    			buscarMatricula();
    		}
    		if (opcion.equals(Opcion.ANULAR_MATRICULA)) {
    			anularMatricula();
    		}
    		if (opcion.equals(Opcion.MOSTRAR_MATRICULAS)) {
    			mostrarMatriculas();
    		}
    		if (opcion.equals(Opcion.MOSTRAR_MATRICULAS_ALUMNO)) {
    			mostrarMatriculasPorAlumno();
    		}
    		if (opcion.equals(Opcion.MOSTRAR_MATRICULAS_CICLO_FORMATIVO)) {
    			mostrarMatriculasPorCicloFormativo();
    		}
    		if (opcion.equals(Opcion.MOSTRAR_MATRICULAS_CURSO_ACADEMICO)) {
    			mostrarMatriculasPorCursoAcademico();
    		}
    		
    }
   

    private static void insertarAlumno() throws OperationNotSupportedException {
		try {	
			alumnos.insertar(Consola.leerAlumno());	

			}
			catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
		
			}
			catch(NullPointerException e) {
			System.out.println(e.getMessage());
			
			}
    }
    
    private static void buscarAlumno() throws OperationNotSupportedException {
  		try {
  			Alumno alumno=Consola.getAlumnoPorDni();
  			if( alumnos.buscar(alumno)!=null) {
  				System.out.println("Alumno econtrado");
  				System.out.println(alumno.toString());
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
    
    
    
    
    
    private static void borrarAlumno() throws OperationNotSupportedException {
  		try {
  			
  			Alumno alumno=Consola.getAlumnoPorDni();
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
    
    
    private static void mostrarAlumnos() throws OperationNotSupportedException {
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
  			 }
  			 else {
  				 System.out.println(" No existen alumnos en el sistema");
  			 }
  			
  			}
  			catch(IllegalArgumentException e) {
  				System.out.println(e.getMessage());
  		
  			}
  			catch(NullPointerException e) {
  			System.out.println(e.getMessage());
  			
  			}
      }
    
    
    private static void insertarAsignatura() throws OperationNotSupportedException {
  		try {
  			asignaturas.insertar(Consola.leerAsignatura());
  			
  			}
  			catch(IllegalArgumentException e) {
  				System.out.println(e.getMessage());
  		
  			}
  			catch(NullPointerException e) {
  			System.out.println(e.getMessage());
  			
  			}
      }
    
    private static void buscarAsignatura(){
  		try {
  			Asignatura asignatura=Consola.getAsignaturaPorCodigo();
  			if( asignaturas.buscar(asignatura)!=null) {
  				System.out.println("Asignatura econtrada");
  				System.out.println(asignatura.toString());
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
    
    private static void borrarAsignatura() throws OperationNotSupportedException {
  		try {
  			Asignatura asignatura=Consola.getAsignaturaPorCodigo();
  			if( asignaturas.buscar(asignatura)!=null) {
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

    
    private static void mostrarAsignaturas()  {
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
  				 }
  			 else {
  				 System.out.println(" No existen asignaturas en el sistema");
  			 }
  			
  			}
  			catch(IllegalArgumentException e) {
  				System.out.println(e.getMessage());
  		
  			}
  			catch(NullPointerException e) {
  			System.out.println(e.getMessage());
  			
  			}
      }
    
    
    private static void insertarCicloFormativo() throws OperationNotSupportedException {
  		try {
  			ciclosFormativos.insertar(Consola.leerCicloFormativo());
  			
  			}
  			catch(IllegalArgumentException e) {
  				System.out.println(e.getMessage());
  		
  			}
  			catch(NullPointerException e) {
  			System.out.println(e.getMessage());
  			
  			}
      }
    

    private static void  buscarCicloFormativo() throws OperationNotSupportedException {
  		try {
  			CicloFormativo cicloFormativo=Consola.getCicloFormativoPorCodigo();
  			if( ciclosFormativos.buscar(cicloFormativo)!=null) {
  				System.out.println("Ciclo formativo econtrado");
  				System.out.println(cicloFormativo.toString());
  			}
  			else {
  				System.out.println("No encontrado el ciclo en la coleccion");
  			}
  			}
  			catch(IllegalArgumentException e) {
  				System.out.println(e.getMessage());
  		
  			}
  			catch(NullPointerException e) {
  			System.out.println(e.getMessage());
  			
  			}
      }
    
    
    private static void  borrarCicloFormativo() throws OperationNotSupportedException {
  		try {
  			CicloFormativo cicloFormativo=Consola.getCicloFormativoPorCodigo();
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
    
    private static void mostrarCiclosFormativos()  {
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
  	  			}
  			 else {
  				 System.out.println(" No existen ciclos formativos en el sistema");
  			 }
  			
  			}
  			catch(IllegalArgumentException e) {
  				System.out.println(e.getMessage());
  		
  			}
  			catch(NullPointerException e) {
  			System.out.println(e.getMessage());
  			
  			}
      }
    
   
    private static void  insertarMatricula() throws OperationNotSupportedException {
  		try {
  			/*No se si tengo que pedir los datos del alumno y asignaturas o rescatarlos de la coleccion obligando a que se den de alta*/
  			
  			/*Asignatura [] coleccionAsignaturas=asignaturas.get();*/
  			
  			Alumno alumno=Consola.getAlumnoPorDni();
  			System.out.println("Indica el numero de asignaturas que vas a introducir, debe ser mayor que 0");
  			Asignatura [] coleccionAsignaturas=new Asignatura[10];
  			int numeroAsignaturas=Entrada.entero();
  			for (int i=0;i<numeroAsignaturas;i++) {
  				coleccionAsignaturas[i]=Consola.getAsignaturaPorCodigo();
  			}
  			matriculas.insertar(Consola.leerMatricula(alumno, coleccionAsignaturas));
  			
  			}
  			catch(IllegalArgumentException e) {
  				System.out.println(e.getMessage());
  		
  			}
  			catch(NullPointerException e) {
  			System.out.println(e.getMessage());
  			
  			}
      }
    
    
    private static void  buscarMatricula() throws OperationNotSupportedException {
  		try {
  			Matricula matricula=Consola.getMatriculaPorIdentificador();
  			if( matriculas.buscar(matricula)!=null) {
  				System.out.println("Ciclo formativo econtrado");
  				System.out.println(matricula.toString());
  			}
  			else {
  				System.out.println("No encontrada la matricula en el sistema");
  			}
  			 
  			}
  			catch(IllegalArgumentException e) {
  				System.out.println(e.getMessage());
  		
  			}
  			catch(NullPointerException e) {
  			System.out.println(e.getMessage());
  			
  			}
      }
    
    private static void  anularMatricula() throws OperationNotSupportedException {
  		try {
  			
  			Matricula matricula=Consola.getMatriculaPorIdentificador();
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
    
    
    private static void mostrarMatriculas() throws OperationNotSupportedException  {
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
  			 }
  			 else {
  				 System.out.println(" No existen matriculas en el sistema");
  			 }
  			
  			}
  			catch(IllegalArgumentException e) {
  				System.out.println(e.getMessage());
  		
  			}
  			catch(NullPointerException e) {
  			System.out.println(e.getMessage());
  			
  			}
      }
    
    private static void mostrarMatriculasPorAlumno() throws OperationNotSupportedException  {
  		try {
  			Alumno alumno=Consola.getAlumnoPorDni();
  			boolean encontrado=false;
  				
  			
 			 if(matriculas.getTamano()>0) {
  				 Matricula[]nuevoArray=matriculas.get();
  				 
  				 for(int i=0;i<nuevoArray.length;i++)
  				 {
  					if (nuevoArray[i]!=null && nuevoArray[i].getAlumno().equals(alumno)) {
  					System.out.println("Estos son los datos de las matriculas para el alumno seleccionado de la coleccion");
  					System.out.println(nuevoArray[i]);
  					}
  					else
  					{
  						encontrado=true;
  					}
  				 }
  			 }
  			 else {
  				 System.out.println(" No existen matriculas en el sistema");
  			 }
  			 
  			}
  			catch(IllegalArgumentException e) {
  				System.out.println(e.getMessage());
  		
  			}
  			catch(NullPointerException e) {
  			System.out.println(e.getMessage());
  			
  			}
      }

    	private static void mostrarMatriculasPorCicloFormativo() throws OperationNotSupportedException  {
  		try {
  				 Matricula[] nuevoArray1=matriculas.get();
  				 Asignatura[] nuevoArray2=null;
  				 int otro=0;
  				 mostrarCiclosFormativos();
  				 System.out.println("Nota: Aunque no se haya dado de alta el ciclo, puede existir una matricula con él asignado");
  				 CicloFormativo cicloFormativo=Consola.getCicloFormativoPorCodigo();
  				 
  				 
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
  				 			
  			}
  			catch(IllegalArgumentException e) {
  			System.out.println(e.getMessage());
  			}
  			catch(NullPointerException e) {
  			System.out.println(e.getMessage());
  			}
      }
    
    private static void mostrarMatriculasPorCursoAcademico() throws OperationNotSupportedException  {
  		try {
  				 System.out.println("Introduce el curso en formato DD-DD, por ejemplo 23-24");
				 String cursoAcademico=Entrada.cadena();	 
				 Matricula[] nuevoArray=matriculas.get();
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
  			}
  			catch(IllegalArgumentException e) {
  			System.out.println(e.getMessage());
  			}
  			catch(NullPointerException e) {
  			System.out.println(e.getMessage());
  			}
      }
    
    public static void main(String[] args) throws OperationNotSupportedException {
    	Opcion opcion=null;
    	alumnos=new Alumnos(100);
    	asignaturas=new Asignaturas(50);
    	ciclosFormativos=new CiclosFormativos(5);
    	matriculas=new Matriculas(100);
    	
    	do {
    		Consola.mostrarMenu();
    		opcion=Consola.elegirOpcion();
    		ejecutarOpcion(opcion);
    	}while(!opcion.equals(Opcion.SALIR));
    	


        System.out.println("Hasta luego!!!!");
    }



}
