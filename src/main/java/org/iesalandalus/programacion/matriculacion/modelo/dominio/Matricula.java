package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.naming.OperationNotSupportedException;

public class Matricula implements Comparable<Matricula>  {
	
	private Alumno alumno;
	private ArrayList<Asignatura> coleccionAsignaturas;
	
	public static final int MAXIMO_MESES_ANTERIOR_ANULACION=6;
	public static final int MAXIMO_DIAS_ANTERIOR_MATRICULA=15;
	public static final int MAXIMO_NUMERO_HORAS_MATRICULA=1000;
	public static final int MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA=10;
	private final String ER_CURSO_ACADEMICO="[0-9]{2}-[0-9]{2}";
	public static String FORMATO_FECHA="dd/MM/yyyy";
	private int idMatricula;
	private String cursoAcademico;
	private LocalDate fechaMatriculacion;
	private LocalDate fechaAnulacion;
	
	private DateTimeFormatter formatoFechaString = DateTimeFormatter.ofPattern(FORMATO_FECHA);
	
	/*\( - Escapa el carácter ( para que sea tratado como un paréntesis literal.*/
	
	public Matricula (int idMatricula,String cursoAcademico,LocalDate fechaMatriculacion,Alumno alumno,ArrayList<Asignatura> coleccionAsignaturas) throws OperationNotSupportedException {
		setIdMatricula(idMatricula);
		setCursoAcademico(cursoAcademico);
		setFechaMatriculacion(fechaMatriculacion);
		setAlumno(alumno);
		setColeccionAsignaturas(coleccionAsignaturas);
	}
	
	public Matricula (Matricula matricula) throws OperationNotSupportedException {
		if (matricula==null) {
			throw new NullPointerException("ERROR: No es posible copiar una matrícula nula.");
		}else {
			setIdMatricula(matricula.getIdMatricula());
			setCursoAcademico(matricula.getCursoAcademico());
			setFechaMatriculacion(matricula.getFechaMatriculacion());
			setAlumno(matricula.getAlumno());
			setColeccionAsignaturas(matricula.getColeccionAsignaturas());
		}
		
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		if (alumno==null) {
			throw new NullPointerException("ERROR: El alumno de una matrícula no puede ser nulo.");
		}else {this.alumno = alumno;}
	}

	public ArrayList<Asignatura> getColeccionAsignaturas() {
		return coleccionAsignaturas;
	}

	public void setColeccionAsignaturas(ArrayList<Asignatura> coleccionAsignaturas) throws OperationNotSupportedException {
	
		if(coleccionAsignaturas==null) {
			throw new NullPointerException("ERROR: La lista de asignaturas de una matrícula no puede ser nula.");
			}	
		else{
			int horas=0;
			int sumaHoras=0;
			
			 for (int i=0;i<coleccionAsignaturas.size();i++) {
				if(coleccionAsignaturas.get(i)!=null) {
					horas=coleccionAsignaturas.get(i).getHorasAnuales();
					sumaHoras=sumaHoras+horas;
					}
				}
			 
			 if (sumaHoras<=MAXIMO_NUMERO_HORAS_MATRICULA) {
				 this.coleccionAsignaturas=coleccionAsignaturas;
			 }
			 else {
				 throw new OperationNotSupportedException ("ERROR: No se puede realizar la matrícula ya que supera el máximo de horas permitidas (1000 horas).");
			 }
		}
	}
	
	private boolean superaMaximoNumeroHorasMatricula(ArrayList<Asignatura> coleccionAsignaturas) {
		int horas=0;
		int sumaHoras=0;
		
		
		 for (int i=0;i<coleccionAsignaturas.size();i++) {
			if(coleccionAsignaturas.get(i)!=null) {
				horas=coleccionAsignaturas.get(i).getHorasAnuales();
				sumaHoras=horas++;
			}
		 }
		 
		 if (sumaHoras<=MAXIMO_NUMERO_HORAS_MATRICULA) {
			 return true;
		 }else {
			 return false;
		 }

	}
	
	private String asignaturasMatricula() {
		if(superaMaximoNumeroHorasMatricula(coleccionAsignaturas)==true) {
			return coleccionAsignaturas.toString();
		}
		else {throw new IllegalArgumentException("Error: El numero de asignaturas supera el maximo");}
	}

	public int getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(int idMatricula) {
		if (idMatricula<1) {
			throw new IllegalArgumentException("ERROR: El identificador de una matrícula no puede ser menor o igual a 0.");
		}
		this.idMatricula = idMatricula;
	}

	public String getCursoAcademico() {
		return cursoAcademico;
	}

	public void setCursoAcademico(String cursoAcademico) {
		if (cursoAcademico==null) {
			throw new NullPointerException("ERROR: El curso académico de una matrícula no puede ser nulo.");
		}
		else if(cursoAcademico.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El curso académico de una matrícula no puede estar vacío.");
		}
		else if(!cursoAcademico.matches(ER_CURSO_ACADEMICO)) {
			throw new IllegalArgumentException("ERROR: El formato del curso académico no es correcto.");
		}
		else {this.cursoAcademico = cursoAcademico;}
	}

	public LocalDate getFechaMatriculacion() {
		return fechaMatriculacion;
	}

	public void setFechaMatriculacion(LocalDate fechaMatriculacion) {
		if (fechaMatriculacion==null) {
			throw new NullPointerException("ERROR: La fecha de matriculación de una mátricula no puede ser nula.");
		}
		else if(fechaMatriculacion.isBefore(LocalDate.now().minusDays(MAXIMO_DIAS_ANTERIOR_MATRICULA))) {
			throw new IllegalArgumentException("ERROR: La fecha de matriculación no puede ser anterior a 15 días.");
		}
		else if(fechaMatriculacion.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("ERROR: La fecha de matriculación no puede ser posterior a hoy.");
		}
		else {this.fechaMatriculacion = fechaMatriculacion;}
	}

	public LocalDate getFechaAnulacion() {
		return fechaAnulacion;
	}

	public void setFechaAnulacion(LocalDate fechaAnulacion) {
		if (fechaAnulacion==null) {
			throw new NullPointerException("La fecha anulacion no puede ser nula");	
		}
		else if(fechaAnulacion.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("ERROR: La fecha de anulación de una matrícula no puede ser posterior a hoy.");
		}
		
		else if(fechaAnulacion.isBefore(fechaMatriculacion)) {
			throw new IllegalArgumentException("ERROR: La fecha de anulación no puede ser anterior a la fecha de matriculación.");
		}
		else if(fechaAnulacion.isAfter(fechaMatriculacion.plusMonths(MAXIMO_MESES_ANTERIOR_ANULACION))) {
			throw new IllegalArgumentException("ERROR: La fecha de anulación de la matrícula no puede superar 6 meses");
		}
		else {this.fechaAnulacion = fechaAnulacion;}
	}


	@Override
	public int hashCode() {
		return Objects.hash(idMatricula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matricula other = (Matricula) obj;
		return idMatricula == other.idMatricula;
	}

	@Override
	public String toString() {
		if (fechaAnulacion!=null) {
			return "idMatricula="+idMatricula+", curso académico="+cursoAcademico+", fecha matriculación="+
					fechaMatriculacion.format(formatoFechaString)+", fecha anulación="+fechaAnulacion.format(formatoFechaString)+
					", alumno="+alumno.imprimir()+", Asignaturas={ }";
		}
		else {
			return "idMatricula="+idMatricula+", curso académico="+cursoAcademico+", fecha matriculación="+
					fechaMatriculacion.format(formatoFechaString)+", alumno="+alumno.imprimir()+", Asignaturas={ }";
		}
	}
	
	public String imprimir() {
		return "idMatricula="+idMatricula+", curso académico="+cursoAcademico+", fecha matriculación="+fechaMatriculacion.format(formatoFechaString)+
				", alumno={"+alumno.imprimir()+"}";
	}

	@Override
	public int compareTo(Matricula o) {
		// TODO Auto-generated method stub
		int resultado = this.fechaMatriculacion.compareTo(o.fechaMatriculacion);
		
		if (resultado==0) {
			resultado = this.alumno.getNombre().compareTo(o.getAlumno().getNombre());
		}
        
		return resultado; 
	}

}
