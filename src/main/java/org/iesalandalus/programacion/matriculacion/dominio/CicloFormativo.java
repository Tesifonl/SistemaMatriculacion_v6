package org.iesalandalus.programacion.matriculacion.dominio;

import java.util.Objects;

public class CicloFormativo {
	
	public static final int MAXIMO_NUMERO_HORAS=2000;
	private int codigo;
	private String familiaProfesional;
	private Grado grado;
	private String nombre;
	private int horas;
	
	
	public CicloFormativo(int codigo, String familiaProfesional,Grado grado, String nombre,int horas) {
		setCodigo(codigo);
		setFamiliaProfesional(familiaProfesional);
		setGrado(grado);
		setNombre(nombre);
		setHoras(horas);
		
	}
	
	public CicloFormativo(CicloFormativo cicloFormativo) {
		if (cicloFormativo==null) {
			throw new NullPointerException("ERROR: No es posible copiar un ciclo formativo nulo.");
		}
		else {
			setCodigo(cicloFormativo.getCodigo());
			setFamiliaProfesional(cicloFormativo.getFamiliaProfesional());
			setGrado(cicloFormativo.getGrado());
			setNombre(cicloFormativo.getNombre());
			setHoras(cicloFormativo.getHoras());
		}
	}

	public int getCodigo() {
		return codigo;
	}

	private void setCodigo(int codigo) {
		if(codigo<1000 || codigo>9999) {
			throw new IllegalArgumentException("ERROR: El identificador debe ser un numero de 4 cifras");
		}
		else {this.codigo = codigo;}
	}

	public String getFamiliaProfesional() {
		return familiaProfesional;
	}

	public void setFamiliaProfesional(String familiaProfesional) {
		if (familiaProfesional==null) {
			throw new NullPointerException("ERROR: La familia profesional de un ciclo formativo no puede ser nula.");
		}
		else if(familiaProfesional.trim()=="") {
			throw new IllegalArgumentException("ERROR: La familia profesional no puede estar vacía.");
		}
		else {this.familiaProfesional = familiaProfesional;}
	}

	public Grado getGrado() {
		return grado;
	}

	public void setGrado(Grado grado) {
		if (grado==null) {
			throw new NullPointerException("ERROR: El grado de un ciclo formativo no puede ser nulo.");
		}
		else {this.grado = grado;}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre==null) {
			throw new NullPointerException("ERROR: El nombre de un ciclo formativo no puede ser nulo.");
		}
		else if(nombre.trim()=="") {
			throw new IllegalArgumentException("ERROR: El nombre de un ciclo formativo no puede estar vacío.");
		}
		else {this.nombre = nombre;}
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		if(horas<1 || horas>MAXIMO_NUMERO_HORAS) {
			throw new IllegalArgumentException("ERROR: El número de horas de un ciclo formativo no puede ser menor o igual a 0 ni mayor a 2000.");
		}
		else {this.horas = horas;}
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, familiaProfesional, grado, horas, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CicloFormativo other = (CicloFormativo) obj;
		return codigo == other.codigo && Objects.equals(familiaProfesional, other.familiaProfesional)
				&& grado == other.grado && horas == other.horas && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Código ciclo formativo="+codigo+", familia profesional="+familiaProfesional+", grado="+grado+
				", nombre ciclo formativo="+nombre+", horas="+horas;
	}
	
	public String imprimir() {
		return "Código ciclo formativo="+codigo+", nombre ciclo formativo="+nombre;
	
	}
	
	
	
}
