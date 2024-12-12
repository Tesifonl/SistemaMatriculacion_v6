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
  			Asignatura [] coleccionAsignaturas=asignaturas.get();
  			matriculas.insertar(Consola.leerMatricula(Consola.leerAlumno(), coleccionAsignaturas));
  			
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
  				 
  				 for(int i=0;i<nuevoArray.length;i++)
  				 {
  					System.out.println("Estos son los datos de las matriculas de la coleccion");
  					System.out.println(nuevoArray[i]);
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
  				
  			
 			 if(matriculas.getTamano()>0) {
  				 Matricula[]nuevoArray=matriculas.get();
  				 
  				 for(int i=0;i<nuevoArray.length;i++)
  				 {
  					if (nuevoArray[i].getAlumno()==alumno)
  					System.out.println("Estos son los datos de las matriculas para el alumno seleccionado de la coleccion");
  					System.out.println(nuevoArray[i]);
  				 }
  			 }
  			 else {
  				 System.out.println(" No existen matriculas pare este alumno en el sistema");
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
  				 Matricula[] busquedaMatricula=matriculas.get();
  				 Matricula[] encontradaMatricula=null;
  				 Asignatura[] busquedaAsignatura=null;
  				 boolean encontrada=false;
  				 boolean otro=false;
  				 int contador=0;
  				 
  		
  				 ciclosFormativos.toString();
  				 
  				 for (int i=0; i<busquedaMatricula.length-1;i++)
  				 {
  					 busquedaAsignatura=busquedaMatricula[i].getColeccionAsignaturas();
  				 	 for(int j=0;j<busquedaAsignatura.length-1;j++) {
  				 		 if(busquedaAsignatura[j].getCicloFormativo().equals(Consola.getCicloFormativoPorCodigo()))
  				 		 	{
  				 			contador++;
  				 		 	encontrada=true;
  				 			encontradaMatricula[contador-1]=busquedaMatricula[i];
  				 			encontradaMatricula.toString();
  				 		 	}
  				 		 	else {
  				 			 otro=true;	 
  				 		 	}
  				 	}
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
  				 System.out.println("Introduce el curso");
				 String cursoAcademico=Entrada.cadena();	 
				 Matricula[] nuevoArray=matriculas.get();
  			
  				 
  	 			 if(matriculas.getTamano()>0) {
  	  				 
  	  				 for(int i=0;i<nuevoArray.length;i++)
  	  				 {
  	  					if (nuevoArray[i].getCursoAcademico()==cursoAcademico)
  	  					System.out.println("Estos son los datos de las matriculas para el curso academico seleccionado de la coleccion");
  	  					System.out.println(nuevoArray[i]);
  	  				 }
  	  			 }
  	  			 else {
  	  				 System.out.println(" No existen matriculas para este curso academico en el sistema");
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
