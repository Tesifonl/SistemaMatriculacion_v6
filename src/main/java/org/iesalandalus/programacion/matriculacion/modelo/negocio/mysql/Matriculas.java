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
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Curso;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.EspecialidadProfesorado;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Grado;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IMatriculas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades.MySQL;

public class Matriculas implements IMatriculas{
	
	private Connection conexion = null;

	private static Matriculas instancia;
	
	public Matriculas() {
		comenzar();
	}
	
	private static Matriculas getInstancia() {
		if (instancia==null)
			instancia=new Matriculas();

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

	public ArrayList<Asignatura> getAsignaturasMatricula(int identificadorMatricula) throws OperationNotSupportedException{
		
		ArrayList<Matricula> arrayMatriculas=get();
		ArrayList<Asignatura> arrayAsignaturas=null;
		boolean encontrado=false;
		
		for (Matricula matriculaBuscada:  arrayMatriculas) {
			int i=+1;
			if (matriculaBuscada.getIdMatricula()== identificadorMatricula) {
				arrayAsignaturas= arrayMatriculas.get(i).getColeccionAsignaturas();
			}else {
				encontrado=true;
			}
			
		}
		
		return arrayAsignaturas;
		
	} 
	
	@Override
	public ArrayList<Matricula> get() throws OperationNotSupportedException {
		// TODO Auto-generated method stub
		ArrayList<Matricula> matriculas=new ArrayList<>();
		
		try {
			
			Statement statement=conexion.createStatement();
			ResultSet registros=statement.executeQuery("select idMatricula,cursoAcademico,fechaMatriculacion,fechaAnulacion,dni from matricula order by idMatricula");
			
			while(registros.next()) {
				int idMatricula=registros.getInt(1);
				String cursoAcademico=registros.getString(2);
				LocalDate fechaMatriculacion=registros.getDate(3).toLocalDate();
				LocalDate fechaAnulacion=registros.getDate(4).toLocalDate();
				String dni=registros.getString(5);

				
				ArrayList<Asignatura> coleccionAsignaturas=getAsignaturasMatricula(idMatricula);
				
				Alumnos alumnos= new Alumnos();
				ArrayList <Alumno> copiaAlumnos=alumnos.get();
				Alumno alumnoBuscado=null;
		
				
				for(Alumno alumnoEncontrado: copiaAlumnos) {
					int i=+1;
					if(alumnoEncontrado.getDni().equals(dni)) {
						alumnoBuscado=copiaAlumnos.get(i);
					}else {
						throw new NullPointerException("No se ha encontrado el alumno en la matricula");
					}
				}
				
				
				Matricula matricula=new Matricula(idMatricula,cursoAcademico,fechaMatriculacion,alumnoBuscado,coleccionAsignaturas);
				
				matriculas.add(matricula);
			}
			
		}
		catch(SQLException e) {
			throw new IllegalArgumentException("ERROR:" + e.getMessage());
		}
		
		
		return matriculas;
	}

	@Override
	public int getTamano() {
		// TODO Auto-generated method stub
		int tamano=0;
		
		try {
			
			Statement statement=conexion.createStatement();
			ResultSet registros=statement.executeQuery("select count(*) from matricula");
			
			if(registros.next()) {
				tamano=registros.getInt(1);
			}
			
		}
		catch(SQLException e) {
			throw new IllegalArgumentException("ERROR:" + e.getMessage());
		}
			
		return tamano;
	}
	
	
	public void insertarAsignaturasMatricula(int idMatricula,ArrayList<Asignatura> coleccionAsignaturas) throws OperationNotSupportedException {
		if(coleccionAsignaturas==null) {
			throw new NullPointerException(" No se ha recibido la matricula a insertar");		
		}
		else {
			try {
				
				for (Asignatura asignaturaRecibida: coleccionAsignaturas) {
				PreparedStatement preparedStatement=conexion.prepareStatement("insert into asignaturasMatricula values (?,?,?,?,?)");
				preparedStatement.setInt(1, idMatricula);
				preparedStatement.setString(1, asignaturaRecibida.getNombre());
				preparedStatement.executeUpdate();
				}
			}
			catch (SQLIntegrityConstraintViolationException e) {
				throw new OperationNotSupportedException("ERROR: Ya existe una asignatura igual.");
			} 
			catch (SQLException e) {
				throw new OperationNotSupportedException("ERROR:" + e.getMessage());
			}
		}
	}
	
	@Override
	public void insertar(Matricula matricula) throws OperationNotSupportedException {
		// TODO Auto-generated method stub
		
		if(matricula==null) {
			throw new NullPointerException(" No se ha recibido la matricula a insertar");		
		}
		else {
			Alumnos alumnos=new Alumnos();
			Asignaturas asignaturas=new Asignaturas();
			if (alumnos.buscar(matricula.getAlumno())==null ) {
				System.out.println("Error: No existe este alumno en el sistema,introduzcalo previamente a la matricula");
				}
				else {
					try {
						PreparedStatement preparedStatement=conexion.prepareStatement("insert into matricula values (?,?,?,?,?)");
						preparedStatement.setInt(1, matricula.getIdMatricula());
						preparedStatement.setString(2, matricula.getCursoAcademico());
						preparedStatement.setDate(3, Date.valueOf(matricula.getFechaMatriculacion()));
						preparedStatement.setNull(4, java.sql.Types.DATE);
						preparedStatement.setString(5, matricula.getAlumno().getDni());
						preparedStatement.executeUpdate();
				
				
					/*ArrayList<Asignatura> coleccionAsignaturas=getAsignaturasMatricula(matricula.getIdMatricula());
				
					Alumnos alumnos1= new Alumnos();
					ArrayList <Alumno> copiaAlumnos=alumnos1.get();
					Alumno alumnoBuscado=null;
		
				
					for(Alumno alumnoEncontrado: copiaAlumnos) {
						int i=+1;
						if(alumnoEncontrado.getDni().equals(matricula.getAlumno().getDni())) {alumnoBuscado=copiaAlumnos.get(i);
						}else {
							throw new NullPointerException("No se ha encontrado el alumno en la matricula");
						}
					}*/
				
				//alumnos.insertar(alumnoBuscado);
				//LO MISMO TENGO QUE HACER CON EL ARRAY DE ASIGNATURAS.
				
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
	public Matricula buscar(Matricula matricula) throws OperationNotSupportedException {
		// TODO Auto-generated method stub
		Matricula matriculaLocalizada=null;
		
		if(matricula==null) {
			throw new NullPointerException("No se puede buscar una matricula nula");
		}
		else {
			try {
				PreparedStatement preparedStatement=conexion.prepareStatement("select idMatricula, cursoAcademico, fechaMatriculacion,fechaAnulacion, dni from matricula where idCodigo = ?");
				preparedStatement.setInt(1, matricula.getIdMatricula());
				ResultSet registros=preparedStatement.executeQuery();
				
				if (registros.next()) {
					int idMatricula=registros.getInt(1);
					String cursoAcademico=registros.getString(2);
					LocalDate fechaMatriculacion=registros.getDate(3).toLocalDate();
					LocalDate fechaAnulacion=registros.getDate(4).toLocalDate();
					String dni=registros.getString(5);
					
					ArrayList<Asignatura> coleccionAsignaturas=getAsignaturasMatricula(matricula.getIdMatricula());
					
					Alumnos alumnos= new Alumnos();
					ArrayList <Alumno> copiaAlumnos=alumnos.get();
					Alumno alumnoBuscado=null;
			
					
					for(Alumno alumnoEncontrado: copiaAlumnos) {
						int i=+1;
						if(alumnoEncontrado.getDni().equals(dni)) {
							alumnoBuscado=copiaAlumnos.get(i);
						}else {
							throw new NullPointerException("No se ha encontrado el alumno en la matricula");
						}
					}
					
					matriculaLocalizada=new Matricula(idMatricula,cursoAcademico,fechaMatriculacion,alumnoBuscado,coleccionAsignaturas);
				}
			}
			catch (SQLException e) {
				throw new IllegalArgumentException("ERROR:" + e.getMessage());
			}
			
			return matriculaLocalizada;
		}
	}

	@Override
	public void borrar(Matricula matricula) throws OperationNotSupportedException {
		// TODO Auto-generated method stub
		if(matricula==null) {
			throw new NullPointerException(" No se ha recibido la matricula a insertar");		
		}
		else {
			try {
				PreparedStatement preparedStatement=conexion.prepareStatement("INSERT INTO matricula (fecha_anulacion) VALUES (?)");
				preparedStatement.setDate(1, Date.valueOf(matricula.getFechaAnulacion()));
				preparedStatement.executeUpdate();

			}
			catch (SQLIntegrityConstraintViolationException e) {
				throw new OperationNotSupportedException("ERROR: Ya existe una asignatura igual.");
			} 
			catch (SQLException e) {
				throw new OperationNotSupportedException("ERROR:" + e.getMessage());
			}
		}
	}

	@Override
	public ArrayList<Matricula> get(Alumno alumno) throws OperationNotSupportedException {
		// TODO Auto-generated method stub
		ArrayList<Matricula>coleccionMatriculas=new ArrayList<>();
		
		if(alumno==null) {
			throw new NullPointerException("No se puede buscar una matricula de un alumno recibido nulo");
		}
		else {
			try {
				PreparedStatement preparedStatement=conexion.prepareStatement("select idMatricula, cursoAcademico, fechaMatriculacion,fechaAnulacion, dni from matricula where dni = ?");
				preparedStatement.setString(1, alumno.getDni());
				ResultSet registros=preparedStatement.executeQuery();
				
				if (registros.next()) {
					while(registros.next()) {
						int idMatricula=registros.getInt(1);
						String cursoAcademico=registros.getString(2);
						LocalDate fechaMatriculacion=registros.getDate(3).toLocalDate();
						LocalDate fechaAnulacion=registros.getDate(4).toLocalDate();
						String dni=registros.getString(5);

						
						ArrayList<Asignatura> coleccionAsignaturas=getAsignaturasMatricula(idMatricula);
						Matricula nuevaMatricula=new Matricula(idMatricula,cursoAcademico,fechaMatriculacion,alumno,coleccionAsignaturas);
						coleccionMatriculas.add(nuevaMatricula);
					
					}
				}
			}
			catch (SQLException e) {
				throw new IllegalArgumentException("ERROR:" + e.getMessage());
			}
			
			return coleccionMatriculas;
		}
	}

	@Override
	public ArrayList<Matricula> get(String cursoAcademico) throws OperationNotSupportedException  {
		// TODO Auto-generated method stub
		ArrayList<Matricula>coleccionMatriculas=new ArrayList<>();
		
		if(cursoAcademico==null) {
			throw new NullPointerException("No se puede buscar una matricula de un curso academico recibido nulo");
		}
		else {
			try {
				PreparedStatement preparedStatement=conexion.prepareStatement("select idMatricula, cursoAcademico, fechaMatriculacion,fechaAnulacion, dni from matricula where cursoAcademico = ?");
				preparedStatement.setString(1, cursoAcademico);
				ResultSet registros=preparedStatement.executeQuery();
				
				if (registros.next()) {
					while(registros.next()) {
						int idMatricula=registros.getInt(1);
						String cursoAcademicoLocalizado=registros.getString(2);
						LocalDate fechaMatriculacion=registros.getDate(3).toLocalDate();
						LocalDate fechaAnulacion=registros.getDate(4).toLocalDate();
						String dni=registros.getString(5);

						
						ArrayList<Asignatura> coleccionAsignaturas=getAsignaturasMatricula(idMatricula);
						
						Alumnos alumnos= new Alumnos();
						ArrayList <Alumno> copiaAlumnos=alumnos.get();
						Alumno alumnoBuscado=null;
				
						
						for(Alumno alumnoEncontrado: copiaAlumnos) {
							int i=+1;
							if(alumnoEncontrado.getDni().equals(dni)) {
								alumnoBuscado=copiaAlumnos.get(i);
							}else {
								throw new NullPointerException("No se ha encontrado el alumno en la matricula");
							}
						}
						
						Matricula nuevaMatricula=new Matricula(idMatricula,cursoAcademicoLocalizado,fechaMatriculacion,alumnoBuscado,coleccionAsignaturas);
						coleccionMatriculas.add(nuevaMatricula);
					
					}
				}
			}
			catch (SQLException e) {
				throw new IllegalArgumentException("ERROR:" + e.getMessage());
			}
			
			return coleccionMatriculas;
		}
	}

	@Override
	public ArrayList<Matricula> get(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
		// TODO Auto-generated method stub
		ArrayList<Matricula>coleccionMatriculas=new ArrayList<>();
		
		if(cicloFormativo==null) {
			throw new NullPointerException("No se puede buscar una matricula de un curso academico recibido nulo");
		}
		else {
			try {
				PreparedStatement preparedStatement=conexion.prepareStatement("select codigo,codigoCicloFormativo from asignatura where codigoCicloFormativo = ?");
				preparedStatement.setInt(1, cicloFormativo.getCodigo());
				ResultSet registros=preparedStatement.executeQuery();

				
				if (registros.next()) {
					
					String codigo=registros.getString(1);
					int codigoCicloFormativo=registros.getInt(2);
					
					PreparedStatement preparedStatement2=conexion.prepareStatement("select idMatricula, codigo from asignaturasMatricula where codigo = ?");
					preparedStatement2.setInt(1,codigoCicloFormativo);
					ResultSet registros2=preparedStatement.executeQuery();
					
					while(registros2.next()){
						
					
					int idMatricula=registros2.getInt(1);
					int codigo2=registros2.getInt(2);
					
					PreparedStatement preparedStatement3=conexion.prepareStatement("select idMatricula, cursoAcademico, fechaMatriculacion,fechaAnulacion, dni from matricula where idMatricula = ?");
					preparedStatement3.setInt(1,codigo2);
					ResultSet registros3=preparedStatement.executeQuery();
					
					int idMatricula2=registros.getInt(1);
					String cursoAcademicoLocalizado=registros.getString(2);
					LocalDate fechaMatriculacion=registros.getDate(3).toLocalDate();
					LocalDate fechaAnulacion=registros.getDate(4).toLocalDate();
					String dni=registros.getString(5);
					
					
					
					ArrayList<Asignatura> coleccionAsignaturas=getAsignaturasMatricula(idMatricula);
					
					Alumnos alumnos= new Alumnos();
					ArrayList <Alumno> copiaAlumnos=alumnos.get();
					Alumno alumnoBuscado=null;
			
					
					for(Alumno alumnoEncontrado: copiaAlumnos) {
						int i=+1;
						if(alumnoEncontrado.getDni().equals(dni)) {
							alumnoBuscado=copiaAlumnos.get(i);
						}else {
							throw new NullPointerException("No se ha encontrado el alumno en la matricula");
						}
					}
					
					Matricula nuevaMatricula=new Matricula(idMatricula,cursoAcademicoLocalizado,fechaMatriculacion,alumnoBuscado,coleccionAsignaturas);
					coleccionMatriculas.add(nuevaMatricula);
					

					}		
				}
				else {
					throw new NullPointerException("No existe este codigo de ciclo formativo en el sistema");
				}
			}
				
			catch (SQLException e) {
				throw new IllegalArgumentException("ERROR:" + e.getMessage());
			}
			
			return coleccionMatriculas;
		}
	}



}
