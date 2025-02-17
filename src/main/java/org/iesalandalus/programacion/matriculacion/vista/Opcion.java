package org.iesalandalus.programacion.matriculacion.vista;

public enum Opcion {
	
	SALIR("Salir") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vista.terminar();;
			
		}
	}, 
	INSERTAR_ALUMNO("Insertar alumno") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vista.insertarAlumno();
		}
	},
	BUSCAR_ALUMNO("Buscar alumno") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vista.buscarAlumno();
		}
	},
	BORRAR_ALUMNO("Borrar alumno") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vista.borrarAlumno();
		}
	},
	MOSTRAR_ALUMNOS("Mostrar alumnos") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vista.mostrarAlumnos();
		}
	},
	INSERTAR_CICLO_FORMATIVO("Insertar ciclo formativo") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vista.insertarCicloFormativo();
		}
	},
	BUSCAR_CICLO_FORMATIVO("Buscar ciclo formativo") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vista.buscarCicloFormativo();
		}
	},
	BORRAR_CICLO_FORMATIVO("Borrar ciclo formativo") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vista.borrarCicloFormativo();
		}
	},
	MOSTRAR_CICLOS_FORMATIVOS("Mostrar ciclos formativos") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vista.mostrarCiclosFormativos();
		}
	}, 
	INSERTAR_ASIGNATURA("Insertar asignatura") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vista.insertarAsignatura();
		}
	},
	BUSCAR_ASIGNATURA("Buscar asignatura") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vista.buscarAsignatura();
		}
	},
	BORRAR_ASIGNATURA("Borrar asignatura") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vista.borrarAsignatura();
		}
	},
	MOSTRAR_ASIGNATURAS("Mostrar asignaturas") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vista.mostrarAsignaturas();
		}
	},
	INSERTAR_MATRICULA("Insertar matricula") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vista.insertarMatricula();
		}
	},
	BUSCAR_MATRICULA("Buscar matricula") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vista.buscarMatricula();
		}
	},
	ANULAR_MATRICULA("Anular matricula") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vista.anularMatricula();
		}
	},
	MOSTRAR_MATRICULAS("Mostrar matricula") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vista.mostrarMatriculas();
		}
	},
	MOSTRAR_MATRICULAS_ALUMNO("Mostrar matriculas de un alumno") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vista.mostrarMatriculasPorAlumno();
		}
	},
	MOSTRAR_MATRICULAS_CICLO_FORMATIVO(" Mostrar matriculas de un ciclo formativo") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vista.mostrarMatriculasPorCicloFormativo();
		}
	},
	MOSTRAR_MATRICULAS_CURSO_ACADEMICO(" Mostrar matriculas de un curso ademico") {
		@Override
		public void ejecutar() {
			// TODO Auto-generated method stub
			vista.mostrarMatriculasPorCursoAcademico();
		}
	
	};
	
	private String cadenaAMostrar;
	private static Vista vista;
	
	private Opcion (String cadenaAMostrar) {	
		this.cadenaAMostrar=cadenaAMostrar;	
	}
	
	
	public String toString() {	
		return this.ordinal() + ".-" + cadenaAMostrar;
	}
	
	
	public void setVista(Vista vista) {
		
		if (vista!=null) {
		Opcion.vista=vista;
		}else {
			throw new IllegalArgumentException("Error: No se ha recibido la vista");
		}
	}
	
	public abstract void ejecutar();
}
