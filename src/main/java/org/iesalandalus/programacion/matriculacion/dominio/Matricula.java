package org.iesalandalus.programacion.matriculacion.dominio;

import java.time.LocalDate;

public class Matricula {
	
	private Alumno alumno;
	private static final int MAXIMO_MESES_ANTERIOR_ANULACION=0;
	private static final int MAXIMO_DIAS_ANTERIOR_MATRICULA=0;
	private static final int MAXIMO_NUMERO_HORAS_MATRICULA=0;
	private static final int MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA=0;
	private static final String ER_CURSO_ACADEMICO="";
	public String FORMATO_FECHA="";
	
	private int idMatricula;
	private String cursoAcademico;
	private LocalDate fechaMatriculacion;
	private LocalDate fechaAnulacion;
	
	
	public Matricula (int idMatricula,String cursoAcademico,LocalDate fechaMatriculacion,Alumno alumno) {
		
	}

}
