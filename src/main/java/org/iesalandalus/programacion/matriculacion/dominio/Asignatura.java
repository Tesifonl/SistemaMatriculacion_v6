package org.iesalandalus.programacion.matriculacion.dominio;

import java.util.Objects;

public class Asignatura {
	public static final int MAX_NUM_HORAS_ANUALES=300;
	public static final int MAX_NUM_HORAS_DESDOBLES=6;
	private String ER_CODIGO="[0-9]{4}";
	private String codigo;
	private String nombre;
	private int horasAnuales;
	private Curso curso;
	private int horasDesdoble;
	private EspecialidadProfesorado especialidadProfesorado;
	private CicloFormativo cicloFormativo;
	
	public Asignatura(String codigo,String nombre,int horasAnuales, Curso curso,int horasDesdoble,
			EspecialidadProfesorado especialidadProfesorado,CicloFormativo cicloFormativo) {
			setCodigo(codigo);
			setNombre(nombre);
			setHorasAnuales(horasAnuales);
			setCurso(curso);
			setHorasDesdoble(horasDesdoble);
			setEspecialidadProfesorado(especialidadProfesorado);
			setCicloFormativo(cicloFormativo);
	}
	
	public Asignatura(Asignatura asignatura) {
		if (asignatura==null) {
			throw new NullPointerException("ERROR: No es posible copiar una asignatura nula.");
		}
		else {
			setCodigo(asignatura.getCodigo());
			setNombre(asignatura.getNombre());
			setHorasAnuales(asignatura.getHorasAnuales());
			setCurso(asignatura.getCurso());
			setHorasDesdoble(asignatura.getHorasDesdoble());
			setEspecialidadProfesorado(asignatura.getEspecialidadProfesorado());
			setCicloFormativo(asignatura.getCicloFormativo());
		
		}
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		if (codigo==null) {
			throw new NullPointerException("ERROR: El código de una asignatura no puede ser nulo.");
		}
		else if(codigo.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El código de una asignatura no puede estar vacío.");
		}
		else if(!codigo.matches(ER_CODIGO)) {
			throw new IllegalArgumentException("ERROR: El código de la asignatura no tiene un formato válido.");
		}
		else {this.codigo = codigo;}
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre==null) {
			throw new NullPointerException("ERROR: El nombre de una asignatura no puede ser nulo.");
		}
		else if(nombre.trim()=="") {
			throw new IllegalArgumentException("ERROR: El nombre de una asignatura no puede estar vacío.");
		}
		else {this.nombre = nombre;}
	}

	public int getHorasAnuales() {
		return horasAnuales;
	}

	public void setHorasAnuales(int horasAnuales) {
		if (horasAnuales<1||horasAnuales>MAX_NUM_HORAS_ANUALES) {
			throw new IllegalArgumentException("ERROR: El número de horas de una asignatura no puede ser menor o igual a 0 ni mayor a 300.");
		}else {this.horasAnuales = horasAnuales;}
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		if (curso==null) {
			throw new NullPointerException("ERROR: El curso de una asignatura no puede ser nulo.");
		}
		else {this.curso= curso;}
	}

	public int getHorasDesdoble() {
		return horasDesdoble;
	}

	public void setHorasDesdoble(int horasDesdoble) {
		if (horasDesdoble<0||horasDesdoble>MAX_NUM_HORAS_DESDOBLES) {
			throw new IllegalArgumentException("ERROR: El número de horas de desdoble de una asignatura no puede ser menor a 0 ni mayor a 6.");
		}else {this.horasDesdoble = horasDesdoble;}
	}

	public EspecialidadProfesorado getEspecialidadProfesorado() {
		return especialidadProfesorado;
	}

	public void setEspecialidadProfesorado(EspecialidadProfesorado especialidadProfesorado) {
		if (especialidadProfesorado==null) {
			throw new NullPointerException("ERROR: La especialidad del profesorado de una asignatura no puede ser nula.");
		}
		else {this.especialidadProfesorado= especialidadProfesorado;}
	}

	public CicloFormativo getCicloFormativo() {
		return cicloFormativo;
	}

	public void setCicloFormativo(CicloFormativo cicloFormativo) {
		if (cicloFormativo==null) {
			throw new NullPointerException("ERROR: El ciclo formativo de una asignatura no puede ser nulo.");
		}
		else {this.cicloFormativo=cicloFormativo;}
	}

	@Override
	public int hashCode() {
		return Objects.hash(cicloFormativo, codigo, curso, especialidadProfesorado, horasAnuales, horasDesdoble,
				nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Asignatura other = (Asignatura) obj;
		return Objects.equals(cicloFormativo, other.cicloFormativo) && Objects.equals(codigo, other.codigo)
				&& curso == other.curso && especialidadProfesorado == other.especialidadProfesorado
				&& horasAnuales == other.horasAnuales && horasDesdoble == other.horasDesdoble
				&& Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Código="+codigo+", nombre="+nombre+", horas anuales="+horasAnuales+", curso="+curso+", horas desdoble="+horasDesdoble+
				", ciclo formativo=Código ciclo formativo="+cicloFormativo.getCodigo()+", nombre ciclo formativo="+cicloFormativo.getNombre()+
				", especialidad profesorado="+especialidadProfesorado;

	}
	
	public String imprimir() {
		return "Código asignatura="+codigo+", nombre asignatura="+nombre+", ciclo formativo=Código ciclo formativo="+cicloFormativo.getCodigo()+", nombre ciclo formativo="+cicloFormativo.getNombre();

	}
	
}
