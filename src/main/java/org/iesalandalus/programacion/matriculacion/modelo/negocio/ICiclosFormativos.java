package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;



public interface ICiclosFormativos {
	
	public void comenzar();
	public void terminar();
	public ArrayList<CicloFormativo> get();
	public int getTamano();
	public CicloFormativo buscar (CicloFormativo cicloFormativo);
	public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException;
}
