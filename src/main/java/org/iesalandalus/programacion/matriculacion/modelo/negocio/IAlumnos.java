package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;

public interface IAlumnos {

	public void comenzar();
	public void terminar();
	public ArrayList<Alumno> get();
	public int getTamano();
	public void insertar(Alumno alumno) throws OperationNotSupportedException;
	public Alumno buscar (Alumno alumno);
	public void borrar(Alumno alumno) throws OperationNotSupportedException;
}
