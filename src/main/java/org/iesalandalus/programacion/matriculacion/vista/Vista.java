package org.iesalandalus.programacion.matriculacion.vista;

import java.util.ArrayList;
import java.util.Collections;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Vista {
	
	private Controlador controlador;
	private Opcion opcion;
	
	public Vista() {

	}
	
	public void setControlador(Controlador controlador) {
		
		if(controlador!=null) {
			this.controlador=controlador;
		}else {
			throw new IllegalArgumentException("ERROR: No se ha recibido el controlador.");
		}
	}
	
	public void comenzar() {
		
		Opcion opcion=null;
				
		do{
			Consola.mostrarMenu();
			opcion=Consola.elegirOpcion();
			

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
			if (opcion.equals(Opcion.SALIR)) {
				terminar();
			}
			
		}while(opcion!=Opcion.SALIR);
		
	}
	
	public void terminar() {
		controlador.terminar();
	}
	
	

	public void insertarAlumno()  {
		try {
			controlador.insertarAlumno(Consola.leerAlumno());
			System.out.println("Alumno insertado correctamente");
		}	
		
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
		System.out.println(e.getMessage());;
		}
		catch(OperationNotSupportedException e) {
		System.out.println(e.getMessage());;}
	}
	

	public  void buscarAlumno()  {
		try {
			//controlador.buscarAlumno(Consola.getAlumnoPorDni());
			System.out.println(controlador.buscarAlumno(Consola.getAlumnoPorDni()));
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
		}
		catch(OperationNotSupportedException e) {
		System.out.println(e.getMessage());;}
	}

	public void borrarAlumno()  {
		try {
			controlador.borrarAlumno(Consola.getAlumnoPorDni());
			System.out.println("Alumno borrado correctamente");
		}				
		
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());;
		}
		catch(OperationNotSupportedException e) {
		System.out.println(e.getMessage());;}
	}


	public void mostrarAlumnos()  {
		try {
			//controlador.getAlumnos();
			ArrayList<Alumno> nuevoArrayList=controlador.getAlumnos();
			Collections.sort(nuevoArrayList);
		
				 
			for(Alumno alumno: nuevoArrayList)
			{
				System.out.println("Alumno: "+alumno);
			}
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
		}
		catch(OperationNotSupportedException e) {
		System.out.println(e.getMessage());;}
	}


	public void insertarAsignatura() {
		
		try {
			
			Asignatura asignatura=Consola.leerAsignatura();
			if (controlador.getAsignaturas()==null || Consola.asignaturaYaMatriculada(controlador.getAsignaturas(), asignatura)!=true) {
			controlador.insertarAsignatura(asignatura);
			System.out.println("Asignatura insertada correctamente");
			}else
			{
				System.out.println("Asignatura insertada anteriormente");
			}
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
		System.out.println(e.getMessage());
		}
		catch(OperationNotSupportedException e) {
		System.out.println(e.getMessage());;}
	}

	public void buscarAsignatura(){
		try{
			//controlador.buscarAsignatura(Consola.getAsignaturaPorCodigo());
			System.out.println(controlador.buscarAsignatura(Consola.getAsignaturaPorCodigo()));
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
		System.out.println(e.getMessage());
		}
		
	}

	public void borrarAsignatura() {
		
		try {
			controlador.borrarAsignatura(Consola.getAsignaturaPorCodigo());
			System.out.println("Asignatura borrada correctamente");
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
		}
		catch(OperationNotSupportedException e) {
		System.out.println(e.getMessage());;}
	}


	public void mostrarAsignaturas()  {
		try{
			
			Consola.mostrarAsignaturas(controlador.getAsignaturas());
		}				
		catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
				
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
		}
		
	}


	public void insertarCicloFormativo() {
		
		try{
			controlador.insertarCicloFormativo(Consola.leerCicloFormativo());
			System.out.println("Ciclo formativo insertado correctamente");
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
		}
		catch(OperationNotSupportedException e) {
		System.out.println(e.getMessage());;}
	}


	public void  buscarCicloFormativo()  {
		
		try{
			//controlador.buscarCicloFormativo(Consola.getCicloFormativoPorCodigo());
			System.out.println(controlador.buscarCicloFormativo(Consola.getCicloFormativoPorCodigo()));
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
		}
		catch(OperationNotSupportedException e) {
		System.out.println(e.getMessage());;}
	}


	public void  borrarCicloFormativo()  {
		
		try{
			controlador.borrarCicloFormativo(Consola.getCicloFormativoPorCodigo());
			System.out.println("Ciclo formativo borrado correctamente");
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
		}
		catch(OperationNotSupportedException e) {
		System.out.println(e.getMessage());;}
	}
	

	public void mostrarCiclosFormativos()  {
		try{
			//controlador.getCiclosFormativos();
			ArrayList<CicloFormativo>nuevoArrayList=controlador.getCiclosFormativos();
			Collections.sort(nuevoArrayList);
				
			for(CicloFormativo cicloFormativo: nuevoArrayList)
			{
				System.out.println("Ciclo formativo: "+cicloFormativo);
			}
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}


	public void  insertarMatricula() {
		try{
			controlador.insertarMatricula(Consola.leerMatricula(Consola.getAlumnoPorDni(), Consola.elegirAsignaturasMatricula()));
			System.out.println("Matricula insertada correctamente");
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
		}
		catch(OperationNotSupportedException e) {
		System.out.println(e.getMessage());;}
	}


	public void  buscarMatricula()  {
		
		try{
			//controlador.buscarMatricula(Consola.getMatriculaPorIdentificador());
			System.out.println(controlador.buscarMatricula(Consola.getMatriculaPorIdentificador()));
		}
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
	
		}
		catch(NullPointerException e) {
		System.out.println(e.getMessage());
		}
		catch(OperationNotSupportedException e) {
		System.out.println(e.getMessage());;}
	}
	

	public void  anularMatricula()  {
		
		try{
			controlador.borrarMatricula(Consola.getMatriculaPorIdentificador());
			System.out.println("Matricula anulada correctamente");
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
		System.out.println(e.getMessage());
		}
		catch(OperationNotSupportedException e) {
		System.out.println(e.getMessage());;}
	}


	public void mostrarMatriculas()   {
		try{
			//controlador.getMatriculas();
			ArrayList<Matricula> nuevoArrayList=controlador.getMatriculas();;
			Collections.sort(nuevoArrayList);

				 
			for (Matricula matricula: nuevoArrayList) {
				System.out.println("Matricula: "+ matricula);
				System.out.println("Asignatura: "+matricula.getColeccionAsignaturas().toString());
			}	
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
		System.out.println(e.getMessage());;
		}
		catch(OperationNotSupportedException e) {
		System.out.println(e.getMessage());;}
	}

	public void mostrarMatriculasPorAlumno()  {
		try{
			//controlador.getMatriculas(Consola.getAlumnoPorDni());
			ArrayList<Matricula> nuevoArrayList=controlador.getMatriculas(Consola.getAlumnoPorDni());
			Collections.sort(nuevoArrayList);

				
			for (Matricula matricula: nuevoArrayList) {
				System.out.println("Matricula: "+ matricula);
				System.out.println("Asignatura: "+matricula.getColeccionAsignaturas().toString());
			}
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
		System.out.println(e.getMessage());
		}
		catch(OperationNotSupportedException e) {
		System.out.println(e.getMessage());;}
	}

	public void mostrarMatriculasPorCicloFormativo() {
		
		try{
			//controlador.getMatriculas(Consola.getCicloFormativoPorCodigo());
			ArrayList<Matricula> nuevoArrayList=controlador.getMatriculas(Consola.getCicloFormativoPorCodigo());
			Collections.sort(nuevoArrayList);

			for (Matricula matricula: nuevoArrayList) {
				System.out.println("Matricula: "+ matricula);
				System.out.println("Asignatura: "+matricula.getColeccionAsignaturas().toString());
			}
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
		System.out.println(e.getMessage());
		}
		catch(OperationNotSupportedException e) {
		System.out.println(e.getMessage());;}
	}

	
	public void mostrarMatriculasPorCursoAcademico()   {
		
		try{
			System.out.println("Introduce el curso en formato DD-DD, por ejemplo 23-24");
			String cursoAcademico=Entrada.cadena();
			//controlador.getMatriculas(cursoAcademico);
			
			ArrayList<Matricula> nuevoArrayList=controlador.getMatriculas(cursoAcademico);
 	 		Collections.sort(nuevoArrayList);
 
 			for (Matricula matricula: nuevoArrayList) {
 				System.out.println("Matricula: "+ matricula);
 				System.out.println("Asignatura: "+matricula.getColeccionAsignaturas().toString());
 			}
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
		}
		catch(OperationNotSupportedException e) {
		System.out.println(e.getMessage());;}
		}

	}
