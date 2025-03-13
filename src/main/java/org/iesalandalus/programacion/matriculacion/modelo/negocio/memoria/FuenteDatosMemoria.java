package org.iesalandalus.programacion.matriculacion.modelo.negocio.memoria;

import org.iesalandalus.programacion.matriculacion.modelo.negocio.IAlumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IAsignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.ICiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IMatriculas;

public class FuenteDatosMemoria implements IFuenteDatos{

	@Override
	public IAlumnos crearAlumnos() {
		// TODO Auto-generated method stub
		Alumnos alumnos=new Alumnos();
		return alumnos;
	}

	@Override
	public ICiclosFormativos crearCiclosFormativos() {
		// TODO Auto-generated method stub
		CiclosFormativos cicloFormativo=new CiclosFormativos();
		return cicloFormativo;
	}

	@Override
	public IAsignaturas crearAsignaturas() {
		// TODO Auto-generated method stub
		Asignaturas asignaturas=new Asignaturas();
		return asignaturas;
	}

	@Override
	public IMatriculas crearMatriculas() {
		// TODO Auto-generated method stub
		Matriculas matriculas=new Matriculas();
		return matriculas;
	}

}
