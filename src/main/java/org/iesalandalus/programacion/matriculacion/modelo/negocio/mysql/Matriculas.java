package org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql;

import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IMatriculas;

public class Matriculas implements IMatriculas{

	@Override
	public void comenzar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void terminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Matricula> get() throws OperationNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTamano() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void insertar(Matricula matricula) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Matricula buscar(Matricula matricula) throws OperationNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void borrar(Matricula matricula) throws OperationNotSupportedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Matricula> get(Alumno alumno) throws OperationNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Matricula> get(String cursoAcademico) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Matricula> get(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}



}
