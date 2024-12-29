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
		Consola.mostrarMenu();
		ejecutarOpcion(Consola.elegirOpcion());
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
		controlador.insertarAlumno(Consola.leerAlumno());
	}

	private  void buscarAlumno() throws OperationNotSupportedException {
		controlador.buscarAlumno(Consola.getAlumnoPorDni());
	}

	private void borrarAlumno() throws OperationNotSupportedException {
		controlador.borrarAlumno(Consola.getAlumnoPorDni());
	}


	private void mostrarAlumnos() throws OperationNotSupportedException {
		controlador.getAlumnos();
	}


	private void insertarAsignatura() throws OperationNotSupportedException {
		controlador.insertarAsignatura(Consola.leerAsignatura());
	}

	private void buscarAsignatura(){
		controlador.buscarAsignatura(Consola.getAsignaturaPorCodigo());
	}

	private void borrarAsignatura() throws OperationNotSupportedException {
		controlador.borrarAsignatura(Consola.getAsignaturaPorCodigo());
	}


	private void mostrarAsignaturas()  {
		controlador.getAsignaturas();
	}


	private void insertarCicloFormativo() throws OperationNotSupportedException {
		controlador.insertarCicloFormativo(Consola.leerCicloFormativo());
	}


	private void  buscarCicloFormativo() throws OperationNotSupportedException {
		controlador.buscarCicloFormativo(Consola.getCicloFormativoPorCodigo());
	}


	private void  borrarCicloFormativo() throws OperationNotSupportedException {
		controlador.borrarCicloFormativo(Consola.getCicloFormativoPorCodigo());
	}

	private void mostrarCiclosFormativos()  {
		controlador.getCiclosFormativos();
	}


	private void  insertarMatricula() throws OperationNotSupportedException {
		controlador.insertarMatricula(Consola.leerMatricula(Consola.getAlumnoPorDni(), Consola.elegirAsignaturasMatricula()));
	}


	private void  buscarMatricula() throws OperationNotSupportedException {
		controlador.buscarMatricula(Consola.getMatriculaPorIdentificador());
	}

	private void  anularMatricula() throws OperationNotSupportedException {
		controlador.borrarMatricula(Consola.getMatriculaPorIdentificador());
	}


	private void mostrarMatriculas() throws OperationNotSupportedException  {
		controlador.getMatriculas();
	}

	private void mostrarMatriculasPorAlumno() throws OperationNotSupportedException  {
		controlador.getMatriculas(Consola.getAlumnoPorDni());
	}

	private void mostrarMatriculasPorCicloFormativo() throws OperationNotSupportedException  {
		controlador.getMatriculas(Consola.getCicloFormativoPorCodigo());
	}

	private void mostrarMatriculasPorCursoAcademico() throws OperationNotSupportedException  {
		System.out.println("Introduce el curso en formato DD-DD, por ejemplo 23-24");
		String cursoAcademico=Entrada.cadena();
		controlador.getMatriculas(cursoAcademico);
  }



}
