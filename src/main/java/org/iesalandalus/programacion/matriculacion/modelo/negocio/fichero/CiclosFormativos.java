package org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero;


import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.ICiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades.MySQL;

import javax.naming.OperationNotSupportedException;
import java.sql.*;
import java.util.ArrayList;

public class CiclosFormativos implements ICiclosFormativos {
	
	private Connection conexion = null;

	private static CiclosFormativos instancia;
	
	
	public CiclosFormativos() {
		comenzar();
	}
	
	private static CiclosFormativos getInstancia() {
		if (instancia==null)
			instancia=new CiclosFormativos();

		return instancia;
	}
	
	@Override
	public void comenzar() {
		// TODO Auto-generated method stub
		//conexion=MySQL.establecerConexion();
	}

	@Override
	public void terminar() {
		// TODO Auto-generated method stub
		//MySQL.cerrarConexion();
	}

	
	public Grado getGrado(String tipoGrado, String nombreGrado, int numAniosGrado, String modalidad, int numEdiciones) {
		Grado nuevoGrado=null;
		
		if (tipoGrado==null) {
			throw new NullPointerException("Se ha recibido un tipo de grado nulo");
		}else {
		
		
		if (tipoGrado.equalsIgnoreCase("gradod")) {
			if (nombreGrado!=null && modalidad!=null) {
				if(modalidad.equalsIgnoreCase("presencial")) {
					nuevoGrado=new GradoD(nombreGrado,numAniosGrado,Modalidad.PRESENCIAL);
				}else {
					nuevoGrado=new GradoD(nombreGrado,numAniosGrado,Modalidad.SEMIPRESENCIAL);
				}
			}
			else {
				throw new NullPointerException("Se ha recibido un tipo de grado nulo");
			}
		}
		
		if (tipoGrado.equalsIgnoreCase("gradoe")) {
			if (nombreGrado!=null)  {
			nuevoGrado=new GradoE(nombreGrado,numAniosGrado,numEdiciones);
			}
			else {
				throw new NullPointerException("Se ha recibido un tipo de grado nulo");
			}
		}
		
		return nuevoGrado;
	
		}
	}
	
	@Override
	public ArrayList<CicloFormativo> get() {
		// TODO Auto-generated method stub
		ArrayList<CicloFormativo> ciclosFormativos=new ArrayList<>();
		
		try {
			
			Statement statement=conexion.createStatement();
			ResultSet registros=statement.executeQuery("select codigo,familiaProfesional,grado,nombre,horas,nombreGrado,numAniosGrado,modalidad,numEdiciones from cicloFormativo order by nombre");
			
			while(registros.next()) {
				int codigo=registros.getInt(1);
				String familiaProfesional=registros.getString(2);
				String tipoGrado=registros.getString(3);
				String nombre=registros.getString(4);
				int horas=registros.getInt(5);
				String nombreGrado=registros.getString(6);
				int numAniosGrado=registros.getInt(7);
				String modalidad=registros.getString(8);
				int numEdiciones=registros.getInt(9);
				
				Grado nuevoGrado=getGrado( tipoGrado,nombreGrado, numAniosGrado, modalidad, numEdiciones);
				
				CicloFormativo cicloFormativo=new CicloFormativo(codigo,familiaProfesional,nuevoGrado,nombre,horas);
				
				ciclosFormativos.add(cicloFormativo);
			}
			
		}
		catch(SQLException e) {
			throw new IllegalArgumentException("ERROR:" + e.getMessage());
		}
		
		
		return ciclosFormativos;
	}

	@Override
	public int getTamano() {
		// TODO Auto-generated method stub
	    int tamano = 0;


	    try (PreparedStatement preparedStatement = conexion.prepareStatement("select count(*) from cicloFormativo");
			 ResultSet registros = preparedStatement.executeQuery()) {

	        if (registros.next()) {
	            tamano = registros.getInt(1);
	        }

	    } catch (SQLException e) {
	        throw new IllegalArgumentException("ERROR al obtener el tamaño: " + e.getMessage());
	    }

	    return tamano;
	}
	

	
	@Override
	public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
		// TODO Auto-generated method stub
		if(cicloFormativo==null) {
			throw new NullPointerException(" No se ha recibido el ciclo a insertar");
		}
		else {
			try {
				Grado grado=cicloFormativo.getGrado();
				
	
				
				PreparedStatement preparedStatement=conexion.prepareStatement("insert into cicloFormativo values (?,?,?,?,?,?,?,?,?)");
				
				if(grado instanceof GradoE) {
				GradoE gradoE=(GradoE) grado;
				preparedStatement.setInt(1,cicloFormativo.getCodigo());
				preparedStatement.setString(2,cicloFormativo.getFamiliaProfesional());
				preparedStatement.setString(3,"gradoe");
				preparedStatement.setString(4,cicloFormativo.getNombre());
				preparedStatement.setInt(5,cicloFormativo.getHoras());
				preparedStatement.setString(6,cicloFormativo.getGrado().getNombre());
				preparedStatement.setInt(7,grado.getNumAnios());
				preparedStatement.setString(8,"presencial");
				preparedStatement.setInt(9,gradoE.getNumEdiciones());
				}
				else {
				GradoD gradoD=(GradoD) grado;
				preparedStatement.setInt(1,cicloFormativo.getCodigo());
				preparedStatement.setString(2,cicloFormativo.getFamiliaProfesional());
				preparedStatement.setString(3,"gradod");
				preparedStatement.setString(4,cicloFormativo.getNombre());
				preparedStatement.setInt(5,cicloFormativo.getHoras());
				preparedStatement.setString(6,gradoD.getNombre());
				preparedStatement.setInt(7,grado.getNumAnios());
				if(gradoD.getModalidad().equals(Modalidad.PRESENCIAL)) {
				preparedStatement.setString(8,"presencial");
				}else {preparedStatement.setString(8,"semipresencial");}
				preparedStatement.setInt(9,0);
				}
				preparedStatement.executeUpdate();
				

			}
			catch (SQLIntegrityConstraintViolationException e) {
				throw new OperationNotSupportedException("ERROR: Ya existe un ciclo formativo igual.");
			} 
			catch (SQLException e) {
				throw new OperationNotSupportedException("ERROR:" + e.getMessage());
			}
		}
	}

	@Override
	public CicloFormativo buscar(CicloFormativo cicloFormativo) {
		// TODO Auto-generated method stub
		CicloFormativo nuevoCicloFormativo=null;
		
		if(cicloFormativo==null) {
			throw new NullPointerException("No se puede buscar un ciclo formativo nulo");
		}
		else {
			try {
				PreparedStatement preparedStatement=conexion.prepareStatement("select codigo,familiaProfesional,grado,nombre,horas,nombreGrado,numAniosGrado,modalidad,numEdiciones from cicloFormativo where codigo = ?");
				preparedStatement.setInt(1, cicloFormativo.getCodigo());
				ResultSet registros=preparedStatement.executeQuery();
				
				if (registros.next()) {
					int codigo=registros.getInt(1);
					String familiaProfesional=registros.getString(2);
					String tipoGrado=registros.getString(3);
					String nombre=registros.getString(4);
					int horas=registros.getInt(5);
					String nombreGrado=registros.getString(6);
					int numAniosGrado=registros.getInt(7);
					String modalidad=registros.getString(8);
					int numEdiciones=registros.getInt(9);
					
					Grado nuevoGrado=getGrado( tipoGrado,nombreGrado, numAniosGrado, modalidad, numEdiciones);
					
					nuevoCicloFormativo=new CicloFormativo(codigo,familiaProfesional,nuevoGrado,nombre,horas);
					
				}
			}
			catch (SQLException e) {
				throw new IllegalArgumentException("ERROR:" + e.getMessage());
			}
			
			return nuevoCicloFormativo;
		}
	}

	@Override
	public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
		// TODO Auto-generated method stub
		if(cicloFormativo==null) {
			throw new NullPointerException("No se puede buscar un ciclo formativo nulo");
		}
		else {
			try {
				PreparedStatement preparedStatement=conexion.prepareStatement("delete from cicloFormativo where codigo = ?");
				preparedStatement.setInt(1, cicloFormativo.getCodigo());
				
				
				if (preparedStatement.executeUpdate()==0) {
					throw new OperationNotSupportedException("ERROR: No existe ningún ciclo formativo con los datos indicados.");
				}
			}
			catch (SQLException e) {
				throw new IllegalArgumentException("ERROR:" + e.getMessage());
			}
		}
	}


}
