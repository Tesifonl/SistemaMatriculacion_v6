package org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;


import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Curso;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.EspecialidadProfesorado;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Grado;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.GradoD;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.GradoE;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Modalidad;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IAsignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades.MySQL;
import org.iesalandalus.programacion.matriculacion.vista.Consola;

public class Asignaturas implements IAsignaturas{
	
	private Connection conexion = null;

	private static Asignaturas instancia;
	
	public Asignaturas() {
		comenzar();
	}
	
	public static Asignaturas getInstancia() {
		if (instancia==null)
			instancia=new Asignaturas();

		return instancia;
	}

	@Override
	public void comenzar() {
		// TODO Auto-generated method stub
		conexion=MySQL.establecerConexion();
	}

	@Override
	public void terminar() {
		// TODO Auto-generated method stub
		MySQL.cerrarConexion();
	}

	
	public Curso getCurso(String curso) {
		if (curso!=null) {
			Curso cursoRecibido=Curso.valueOf(curso);
			return cursoRecibido;
		}
		else {
			throw new NullPointerException("No se ha recibido el curso");
		}
						
	}
	
	public EspecialidadProfesorado getEspecialidadProfesorado(String especialidad) {
		if (especialidad!=null) {
			EspecialidadProfesorado epecialidadRecibida=EspecialidadProfesorado .valueOf(especialidad);
			return epecialidadRecibida;
		}
		else {
			throw new NullPointerException("No se ha recibido el curso");
		}
						
	}
	
	@Override
	public ArrayList<Asignatura> get() {
		// TODO Auto-generated method stub
		ArrayList<Asignatura> asignaturas=new ArrayList<>();
		
		try {
			
			Statement statement=conexion.createStatement();
			ResultSet registros=statement.executeQuery("select codigo,nombre,horasAnuales,curso,horasDesdoble,especialidadProfesorado,codigoCicloFormativo from asignatura order by nombre");
			
			while(registros.next()) {
				String codigo=registros.getString(1);
				String nombre=registros.getString(2);
				int horasAnuales=registros.getInt(3);
				Curso curso=getCurso(registros.getString(4));
				int horasDesdoble=registros.getInt(5);
				EspecialidadProfesorado especialidadProfesorado=getEspecialidadProfesorado(registros.getString(6));
				int codigoCicloFormativo=registros.getInt(7);
				
				
				CiclosFormativos ciclosCopia=new CiclosFormativos();
				ArrayList <CicloFormativo> copiaArray=ciclosCopia.get();
				CicloFormativo nuevoCiclo=null;
				
				
				for (CicloFormativo ciclofor: copiaArray) {
					int i=+1;
					if (ciclofor.getCodigo()==codigoCicloFormativo) {
						nuevoCiclo=copiaArray.get(i);
					}
					else {
						throw new NullPointerException("No se ha encontrado el alumno en la matricula");
					}
				}
				
				CicloFormativo cicloFormativoLocalizado=ciclosCopia.buscar(nuevoCiclo);
				
				Asignatura asignatura=new Asignatura(codigo,nombre,horasAnuales, curso,horasDesdoble,
						especialidadProfesorado,cicloFormativoLocalizado);
				
				asignaturas.add(asignatura);
			}
			
		}
		catch(SQLException e) {
			throw new IllegalArgumentException("ERROR:" + e.getMessage());
		}
		
		
		return asignaturas;
	}

	@Override
	public int getTamano() {
		// TODO Auto-generated method stub
		int tamano=0;
		
		try {
			
			Statement statement=conexion.createStatement();
			ResultSet registros=statement.executeQuery("select count(*) from asignatura");
			
			if(registros.next()) {
				tamano=registros.getInt(1);
			}
			
		}
		catch(SQLException e) {
			throw new IllegalArgumentException("ERROR:" + e.getMessage());
		}
			
		return tamano;
	}
	@Override
	public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
		// TODO Auto-generated method stub
		
		if(asignatura==null) {
			throw new NullPointerException("No se ha recibido las asignaturas a insertar");
		}
		else {
			
			CiclosFormativos ciclosFormativos=new CiclosFormativos();
			if (ciclosFormativos.buscar(asignatura.getCicloFormativo())==null) {
				System.out.println("Error: No existe este ciclo formativo en el sistema,introduzcalo previamente a la asignatura");
			}
			else {
				try {
					PreparedStatement preparedStatement=conexion.prepareStatement("insert into asignatura values (?,?,?,?,?,?,?)");
					preparedStatement.setString(1, asignatura.getCodigo());
					preparedStatement.setString(2, asignatura.getNombre());
					preparedStatement.setInt(3, asignatura.getHorasAnuales());
					preparedStatement.setString(4,asignatura.getCurso().toString());
					preparedStatement.setInt(5, asignatura.getHorasDesdoble());
					preparedStatement.setString(6,asignatura.getEspecialidadProfesorado().toString());
					preparedStatement.setInt(7, asignatura.getCicloFormativo().getCodigo());
					preparedStatement.executeUpdate();
					
					System.out.println("Asignatura insertada correctamente");
				
				
				}
				catch (SQLIntegrityConstraintViolationException e) {
					throw new OperationNotSupportedException("ERROR:" + e.getMessage());
				} 
				catch (SQLException e) {
					throw new OperationNotSupportedException("ERROR:" + e.getMessage());
				}
			}
		}
	}
	@Override
	public Asignatura buscar(Asignatura asignatura) {
		// TODO Auto-generated method stub
		Asignatura asignaturaLocalizada=null;
		
		if(asignatura==null) {
			throw new NullPointerException("No se puede buscar un alumno nulo");
		}
		else {
			try {
				PreparedStatement preparedStatement=conexion.prepareStatement("select codigo,nombre,horasAnuales,curso,horasDesdoble,especialidadProfesorado,codigoCicloFormativo from asignatura where codigo = ?");
				preparedStatement.setString(1, asignatura.getCodigo());
				ResultSet registros=preparedStatement.executeQuery();
				
				if (registros.next()) {
					String codigo=registros.getString(1);
					String nombre=registros.getString(2);
					int horasAnuales=registros.getInt(3);
					Curso curso=getCurso(registros.getString(4));
					int horasDesdoble=registros.getInt(5);
					EspecialidadProfesorado especialidadProfesorado=getEspecialidadProfesorado(registros.getString(6));;
					int codigoCicloFormativo=registros.getInt(7);
					
					CiclosFormativos ciclosCopia=new CiclosFormativos();
					ArrayList <CicloFormativo> copiaArray=ciclosCopia.get();
					CicloFormativo nuevoCiclo=null;
					
					
					for (CicloFormativo ciclofor: copiaArray) {
						int i=+1;
						if (ciclofor.getCodigo()==codigoCicloFormativo) {
							nuevoCiclo=copiaArray.get(i);
						}
						else {
							throw new NullPointerException("No se ha encontrado el ciclo formatico  en las asignaturas");
						}
					}
					
					CicloFormativo cicloLocalizado=ciclosCopia.buscar(nuevoCiclo);
					
					asignaturaLocalizada=new Asignatura(codigo,nombre,horasAnuales, curso,horasDesdoble,
							especialidadProfesorado,cicloLocalizado);
				}
			}
			catch (SQLException e) {
				throw new IllegalArgumentException("ERROR:" + e.getMessage());
			}
			
			return asignaturaLocalizada;
		}
	}

	@Override
	public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
		// TODO Auto-generated method stub
		if(asignatura==null) {
			throw new NullPointerException("No se puede buscar una asignatura nula");
		}
		else {
			try {
				PreparedStatement preparedStatement=conexion.prepareStatement("delete from asignatura where codigo = ?");
				preparedStatement.setString(1, asignatura.getCodigo());
				
				
				if (preparedStatement.executeUpdate()==0) {
					throw new OperationNotSupportedException("ERROR: No existe ninguna asignatura con los datos indicados.");
				}
			}
			catch (SQLException e) {
				throw new IllegalArgumentException("ERROR:" + e.getMessage());
			}
		}
	}



}
