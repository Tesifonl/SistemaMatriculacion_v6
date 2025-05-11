package org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero.utilidades;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Curso;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.EspecialidadProfesorado;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IAsignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades.MySQL;

import javax.naming.OperationNotSupportedException;
import java.sql.*;
import java.util.ArrayList;


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
			Curso cursoRecibido=Curso.valueOf(curso.toUpperCase());
			return cursoRecibido;
		}
		else {
			throw new NullPointerException("No se ha recibido el curso");
		}
						
	}
	
	public EspecialidadProfesorado getEspecialidadProfesorado(String especialidad) {
		if (especialidad!=null) {
			EspecialidadProfesorado epecialidadRecibida=EspecialidadProfesorado .valueOf(especialidad.toUpperCase());
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
				String curso=registros.getString(4);
				int horasDesdoble=registros.getInt(5);
				String especialidadProfesorado=registros.getString(6);
				int codigoCicloFormativo=registros.getInt(7);
				
				

				Curso curso1=getCurso(curso);
				EspecialidadProfesorado especialidadProfesorado1=getEspecialidadProfesorado(especialidadProfesorado);
				CiclosFormativos ciclosFormativos1=new CiclosFormativos();
				
				ArrayList <CicloFormativo> copiaArray=ciclosFormativos1.get();
				CicloFormativo nuevoCiclo=null;
				boolean encontrado=false;
				int i=0;
				
				for (CicloFormativo ciclofor: copiaArray) {
					
					i++;
					if (ciclofor.getCodigo()==codigoCicloFormativo) {
						nuevoCiclo=copiaArray.get(i-1);
						
						Asignatura asignatura=new Asignatura(codigo,nombre,horasAnuales, curso1,horasDesdoble,especialidadProfesorado1,nuevoCiclo);
						
						asignaturas.add(asignatura);
					}
					else {
						encontrado=true;
					}
				}
				
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
					String curso=registros.getString(4);
					int horasDesdoble=registros.getInt(5);
					String especialidadProfesorado=registros.getString(6);
					int codigoCicloFormativo=registros.getInt(7);
					
					Curso curso1=getCurso(curso);
					EspecialidadProfesorado especialidadProfesorado1=getEspecialidadProfesorado(especialidadProfesorado);
					CiclosFormativos ciclosFormativos1=new CiclosFormativos();
					
					ArrayList <CicloFormativo> copiaArray=ciclosFormativos1.get();
					CicloFormativo nuevoCiclo=null;
					boolean encontrado=false;
					int i=0;
					
					for (CicloFormativo ciclofor: copiaArray) {
						i++;
						if (ciclofor.getCodigo()==codigoCicloFormativo) {
							nuevoCiclo=copiaArray.get(i-1);
							asignaturaLocalizada=new Asignatura(codigo,nombre,horasAnuales, curso1,horasDesdoble,especialidadProfesorado1,nuevoCiclo);
						}
						else {
							encontrado=true;
							}
					}
				
			
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
			
			CiclosFormativos ciclosFormativos=new CiclosFormativos();
			ciclosFormativos.borrar(buscar(asignatura).getCicloFormativo());
			
		}
	}



}
