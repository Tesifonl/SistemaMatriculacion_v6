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
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IAlumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades.MySQL;

public class Alumnos implements IAlumnos{
	
	private Connection conexion = null;

	private static Alumnos instancia;
	
	
	public Alumnos() {
		comenzar();
	}
	
	private static Alumnos getInstancia() {
		if (instancia==null)
			instancia=new Alumnos();

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

	@Override
	public ArrayList<Alumno> get() {
		// TODO Auto-generated method stub
		ArrayList<Alumno> alumnos=new ArrayList<>();
		
		try {
			
			Statement statement=conexion.createStatement();
			ResultSet registros=statement.executeQuery("select nombre,telefono,correo,dni,fechaNacimiento from alumno order by nombre");
			
			while(registros.next()) {
				String nombre=registros.getString(1);
				String telefono=registros.getString(2);
				String correo=registros.getString(3);
				String dni=registros.getString(4);
				LocalDate fechaNacimiento=registros.getDate(5).toLocalDate();
				
				Alumno alumno=new Alumno(nombre,dni, correo, telefono, fechaNacimiento);
				
				alumnos.add(alumno);
			}
			
		}
		catch(SQLException e) {
			throw new IllegalArgumentException("ERROR:" + e.getMessage());
		}
		
		
		return alumnos;
	}

	@Override
	public int getTamano() {
		// TODO Auto-generated method stub
	
		int tamano=0;
		
		try {
			
			Statement statement=conexion.createStatement();
			ResultSet registros=statement.executeQuery("select count(*) from alumno");
			
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
	public void insertar(Alumno alumno) throws OperationNotSupportedException {
		// TODO Auto-generated method stub
		
		if(alumno==null) {
			throw new NullPointerException("No se ha recibido el alumno a insertar");
		}
		else {
			try {
				PreparedStatement preparedStatement=conexion.prepareStatement("insert into alumno values (?,?,?,?,?)");
				preparedStatement.setString(1, alumno.getNombre());
				preparedStatement.setString(2, alumno.getTelefono());
				preparedStatement.setString(3, alumno.getCorreo());
				preparedStatement.setString(4, alumno.getDni());
				preparedStatement.setDate(5, Date.valueOf(alumno.getFechaNacimiento()));
				preparedStatement.executeUpdate();
				
			}
			catch (SQLIntegrityConstraintViolationException e) {
				throw new OperationNotSupportedException("ERROR: Ya existe un alumno igual.");
			} 
			catch (SQLException e) {
				throw new OperationNotSupportedException("ERROR:" + e.getMessage());
			}
		}
	}

	@Override
	public Alumno buscar(Alumno alumno) {
		// TODO Auto-generated method stub
		Alumno alumnoLocalizado=null;
	
		if(alumno==null) {
			throw new NullPointerException("No se puede buscar un alumno nulo");
		}
		else {
			try {
				PreparedStatement preparedStatement=conexion.prepareStatement("select nombre,telefono,correo,dni,fechaNacimiento from alumno where dni = ?");
				preparedStatement.setString(1, alumno.getDni());
				ResultSet registros=preparedStatement.executeQuery();
				
				if (registros.next()) {
					String nombre=registros.getString(1);
					String telefono=registros.getString(2);
					String correo=registros.getString(3);
					String dni=registros.getString(4);
					LocalDate fechaNacimiento=registros.getDate(5).toLocalDate();
					
					alumnoLocalizado=new Alumno(nombre,dni, correo, telefono, fechaNacimiento);
				}
			}
			catch (SQLException e) {
				throw new IllegalArgumentException("ERROR:" + e.getMessage());
			}
			
			return alumnoLocalizado;
		}
	}

	@Override
	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		// TODO Auto-generated method stub
		if(alumno==null) {
			throw new NullPointerException("No se puede buscar un alumno nulo");
		}
		else {
			try {
				PreparedStatement preparedStatement=conexion.prepareStatement("delete from alumno where dni = ?");
				preparedStatement.setString(1, alumno.getDni());
				preparedStatement.executeUpdate();
				
				
				if (preparedStatement.executeUpdate()==0) {
					throw new OperationNotSupportedException("ERROR: No existe ningún alumno con los datos indicados.");
				}
			}
			catch (SQLException e) {
				throw new IllegalArgumentException("ERROR:" + e.getMessage());
			}
		}
	}


}
