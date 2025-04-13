package org.iesalandalus.programacion.matriculacion.vista.texto;

import com.mysql.cj.jdbc.exceptions.OperationNotSupportedException;

public enum Opcion {
	
	SALIR("Salir") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vistaTexto.terminar();;
			
		}
	}, 
	INSERTAR_ALUMNO("Insertar alumno") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vistaTexto.insertarAlumno();
		}
	},
	BUSCAR_ALUMNO("Buscar alumno") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vistaTexto.buscarAlumno();
		}
	},
	BORRAR_ALUMNO("Borrar alumno") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vistaTexto.borrarAlumno();
		}
	},
	MOSTRAR_ALUMNOS("Mostrar alumnos") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vistaTexto.mostrarAlumnos();
		}
	},
	INSERTAR_CICLO_FORMATIVO("Insertar ciclo formativo") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			// TODO Auto-generated method stub
			vistaTexto.insertarCicloFormativo();
		}
	},
	BUSCAR_CICLO_FORMATIVO("Buscar ciclo formativo") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vistaTexto.buscarCicloFormativo();
		}
	},
	BORRAR_CICLO_FORMATIVO("Borrar ciclo formativo") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vistaTexto.borrarCicloFormativo();
		}
	},
	MOSTRAR_CICLOS_FORMATIVOS("Mostrar ciclos formativos") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vistaTexto.mostrarCiclosFormativos();
		}
	}, 
	INSERTAR_ASIGNATURA("Insertar asignatura") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vistaTexto.insertarAsignatura();
		}
	},
	BUSCAR_ASIGNATURA("Buscar asignatura") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vistaTexto.buscarAsignatura();
		}
	},
	BORRAR_ASIGNATURA("Borrar asignatura") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vistaTexto.borrarAsignatura();
		}
	},
	MOSTRAR_ASIGNATURAS("Mostrar asignaturas") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vistaTexto.mostrarAsignaturas();
		}
	},
	INSERTAR_MATRICULA("Insertar matricula") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vistaTexto.insertarMatricula();
		}
	},
	BUSCAR_MATRICULA("Buscar matricula") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vistaTexto.buscarMatricula();
		}
	},
	ANULAR_MATRICULA("Anular matricula") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vistaTexto.anularMatricula();
		}
	},
	MOSTRAR_MATRICULAS("Mostrar matricula") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vistaTexto.mostrarMatriculas();
		}
	},
	MOSTRAR_MATRICULAS_ALUMNO("Mostrar matriculas de un alumno") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vistaTexto.mostrarMatriculasPorAlumno();
		}
	},
	MOSTRAR_MATRICULAS_CICLO_FORMATIVO(" Mostrar matriculas de un ciclo formativo") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vistaTexto.mostrarMatriculasPorCicloFormativo();
		}
	},
	MOSTRAR_MATRICULAS_CURSO_ACADEMICO(" Mostrar matriculas de un curso ademico") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vistaTexto.mostrarMatriculasPorCursoAcademico();
		}
	
	};
	
	private String cadenaAMostrar;
	private static VistaTexto vistaTexto;
	
	private Opcion (String cadenaAMostrar) {	
		this.cadenaAMostrar=cadenaAMostrar;	
	}
	
	
	public String toString() {	
		return this.ordinal() + ".-" + cadenaAMostrar;
	}
	
	
	public void setVista(VistaTexto vistaTexto) {
		
		if (vistaTexto !=null) {
		Opcion.vistaTexto = vistaTexto;
		}else {
			throw new IllegalArgumentException("Error: No se ha recibido la vista");
		}
	}
	
	public abstract void ejecutar() throws OperationNotSupportedException;
	
	//con esto obligo a que cada opcion de enumerado tenga un metodo asociado.
}
