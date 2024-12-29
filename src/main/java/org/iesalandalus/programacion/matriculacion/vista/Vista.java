package org.iesalandalus.programacion.matriculacion.vista;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Vista {
	
	private Controlador controlador;
	
	public Vista() {}
	
	public void setControlador(Controlador controlador) {
		
		if(controlador!=null) {
			this.controlador=controlador;
		}else {
			throw new IllegalArgumentException("ERROR: No se ha recibido el controlador.");
		}
	}
	
	public void comenzar() throws OperationNotSupportedException {
		
		Opcion opcion=null;
				
		do{
			Consola.mostrarMenu();
			opcion=Consola.elegirOpcion();
			ejecutarOpcion(opcion);
		}while(opcion!=Opcion.SALIR);
		
		terminar();
	}
	
	public void terminar() {
		System.out.println("Ciao");
	}
	
	private void ejecutarOpcion(Opcion opcion) throws OperationNotSupportedException {
    	
		   
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


	private void insertarAlumno() throws OperationNotSupportedException {
		try {
			controlador.insertarAlumno(Consola.leerAlumno());
		}	
		
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
		System.out.println(e.getMessage());;}
	}

	private  void buscarAlumno() throws OperationNotSupportedException {
		try {
			controlador.buscarAlumno(Consola.getAlumnoPorDni());
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());}
	}

	private void borrarAlumno() throws OperationNotSupportedException {
		try {
			controlador.borrarAlumno(Consola.getAlumnoPorDni());
		}				
		
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());;}
	}


	private void mostrarAlumnos() throws OperationNotSupportedException {
		try {
			controlador.getAlumnos();
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());}
	}


	private void insertarAsignatura() throws OperationNotSupportedException {
		
		try {
			controlador.insertarAsignatura(Consola.leerAsignatura());
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
		System.out.println(e.getMessage());}
	}

	private void buscarAsignatura(){
		try{
			controlador.buscarAsignatura(Consola.getAsignaturaPorCodigo());
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
		System.out.println(e.getMessage());
		}
	}

	private void borrarAsignatura() throws OperationNotSupportedException {
		
		try {
			controlador.borrarAsignatura(Consola.getAsignaturaPorCodigo());
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());}
	}


	private void mostrarAsignaturas()  {
		try{
			controlador.getAsignaturas();
		}				
		catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
				
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
		}
		
	}


	private void insertarCicloFormativo() throws OperationNotSupportedException {
		
		try{
			controlador.insertarCicloFormativo(Consola.leerCicloFormativo());
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());}
	}


	private void  buscarCicloFormativo() throws OperationNotSupportedException {
		
		try{
			controlador.buscarCicloFormativo(Consola.getCicloFormativoPorCodigo());
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());}
	}


	private void  borrarCicloFormativo() throws OperationNotSupportedException {
		
		try{
			controlador.borrarCicloFormativo(Consola.getCicloFormativoPorCodigo());
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());}
	}

	private void mostrarCiclosFormativos()  {
		try{
			controlador.getCiclosFormativos();
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}


	private void  insertarMatricula() throws OperationNotSupportedException {
		try{
			controlador.insertarMatricula(Consola.leerMatricula(Consola.getAlumnoPorDni(), Consola.elegirAsignaturasMatricula()));
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());}
	}


	private void  buscarMatricula() throws OperationNotSupportedException {
		
		try{
			controlador.buscarMatricula(Consola.getMatriculaPorIdentificador());
		}
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
	
		}
		catch(NullPointerException e) {
		System.out.println(e.getMessage());}
	}

	private void  anularMatricula() throws OperationNotSupportedException {
		
		try{
			controlador.borrarMatricula(Consola.getMatriculaPorIdentificador());
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
		System.out.println(e.getMessage());}
	}


	private void mostrarMatriculas() throws OperationNotSupportedException  {
		try{
			controlador.getMatriculas();
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
		System.out.println(e.getMessage());;}
	}

	private void mostrarMatriculasPorAlumno() throws OperationNotSupportedException  {
		try{
			controlador.getMatriculas(Consola.getAlumnoPorDni());
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
		System.out.println(e.getMessage());}
	}

	private void mostrarMatriculasPorCicloFormativo() throws OperationNotSupportedException  {
		
		try{
			controlador.getMatriculas(Consola.getCicloFormativoPorCodigo());
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
		}
		catch(NullPointerException e) {
		System.out.println(e.getMessage());}
	}

	private void mostrarMatriculasPorCursoAcademico() throws OperationNotSupportedException  {
		
		try{
			System.out.println("Introduce el curso en formato DD-DD, por ejemplo 23-24");
			String cursoAcademico=Entrada.cadena();
			controlador.getMatriculas(cursoAcademico);
		}				
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());}
		}

	}
