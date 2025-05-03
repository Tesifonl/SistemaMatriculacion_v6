package org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Curso;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.EspecialidadProfesorado;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Grado;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.GradoD;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.GradoE;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Modalidad;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IMatriculas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades.MySQL;
import org.iesalandalus.programacion.utilidades.Entrada;

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

		ArrayList<Asignatura> arrayAsignaturas=new ArrayList<>();
		Asignaturas asignaturas=new Asignaturas();
		boolean encontrado=false;
		
		try {
			
			PreparedStatement preparedStatement=conexion.prepareStatement("select idMatricula, codigo from asignaturasMatricula where idMatricula = ?");
			preparedStatement.setInt(1, identificadorMatricula);
			ResultSet registros=preparedStatement.executeQuery();
			
			while(registros.next()) {
				int idMatricula=registros.getInt(1);
				String codigo=registros.getString(2);
				Asignatura asignaturaNueva=new Asignatura(codigo,"Ficticio",100,Curso.PRIMERO,5,EspecialidadProfesorado.INFORMATICA,new CicloFormativo(9988,"SemiPresencial",new GradoD("ficticio",2,Modalidad.PRESENCIAL),"ficticio",100));	
				Asignatura asignaturaLocalizada=asignaturas.buscar(asignaturaNueva);
				if (asignaturaLocalizada!=null) {
					arrayAsignaturas.add(asignaturaLocalizada);
				}else {
					encontrado=true;
				}
	
			}
			}
			catch(SQLException e) {
				throw new IllegalArgumentException("ERROR:" + e.getMessage());
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
		        LocalDate fechaAnulacion = null;
		        if (registros.getDate(4) != null) {
		            fechaAnulacion = registros.getDate(4).toLocalDate();
		        }
				String dni=registros.getString(5);

				
				ArrayList<Asignatura> coleccionAsignaturas=getAsignaturasMatricula(idMatricula);
				
				Alumnos alumnos= new Alumnos();
				ArrayList <Alumno> copiaAlumnos=alumnos.get();
				Alumno alumnoBuscado=null;
				boolean encontrado=false;
		
				
				for(Alumno alumnoEncontrado: copiaAlumnos) {
					int i=+1;
					if(alumnoEncontrado.getDni().equals(dni)) {
						alumnoBuscado=alumnoEncontrado;
					}else {
						encontrado=true;
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
				Asignaturas asignaturas=new Asignaturas();
				boolean encontrado=false;
				
				for (Asignatura asignaturaRecibida: coleccionAsignaturas) {
					if(asignaturas.buscar(asignaturaRecibida)!=null) {
						PreparedStatement preparedStatement=conexion.prepareStatement("insert into asignaturasMatricula values (?,?)");
						preparedStatement.setInt(1, idMatricula);
						preparedStatement.setString(2, asignaturaRecibida.getCodigo());
						preparedStatement.executeUpdate();}
					else {encontrado=true;}
				}
			}
			catch (SQLIntegrityConstraintViolationException e) {
				throw new OperationNotSupportedException("ERROR:"+ e.getMessage());
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
			boolean noEncontrado=false;
			boolean encontrado=false;
			
			ArrayList<Asignatura> nuevoArray=matricula.getColeccionAsignaturas();
			for (Asignatura asign: nuevoArray) {
				if(asignaturas.buscar(asign)!=null) {
					encontrado=true;
				}else {
					noEncontrado=true;
				}
			}
			
			
			if (alumnos.buscar(matricula.getAlumno())==null || noEncontrado) {
				System.out.println("Error: No existe este alumno en el sistema, o algunas de las asignaturas recibidas no han sido dadas de alta. Introduzcalo previamente a la matricula");
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
						insertarAsignaturasMatricula(matricula.getIdMatricula(),matricula.getColeccionAsignaturas());
						System.out.println("Matricula insertada correctamente");

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
				PreparedStatement preparedStatement=conexion.prepareStatement("select idMatricula, cursoAcademico, fechaMatriculacion,fechaAnulacion, dni from matricula where idMatricula = ?");
				preparedStatement.setInt(1, matricula.getIdMatricula());
				ResultSet registros=preparedStatement.executeQuery();
				
				if (registros.next()) {
					int idMatricula=registros.getInt(1);
					String cursoAcademico=registros.getString(2);
					LocalDate fechaMatriculacion=registros.getDate(3).toLocalDate();
			        LocalDate fechaAnulacion = null;
			        if (registros.getDate(4) != null) {
			            fechaAnulacion = registros.getDate(4).toLocalDate();
			        }
				
					String dni=registros.getString(5);
					
					ArrayList<Asignatura> coleccionAsignaturas=getAsignaturasMatricula(matricula.getIdMatricula());
					
					Alumnos alumnos= new Alumnos();
					ArrayList <Alumno> copiaAlumnos=alumnos.get();
					Alumno alumnoBuscado=null;
					boolean encontrado=false;
			
					
					for(Alumno alumnoEncontrado: copiaAlumnos) {
						int i=+1;
						if(alumnoEncontrado.getDni().equals(dni)) {
							alumnoBuscado=copiaAlumnos.get(i);
						}else {
							encontrado=true;
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

				PreparedStatement preparedStatement=conexion.prepareStatement("UPDATE matricula SET fechaAnulacion = ? WHERE idMatricula = ?");
				preparedStatement.setDate(1, Date.valueOf(LocalDate.now().toString()));
				preparedStatement.setInt(2, matricula.getIdMatricula());
				preparedStatement.executeUpdate();
				System.out.println("Fecha anulacion introducida");

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
					
						int idMatricula=registros.getInt(1);
						String cursoAcademico=registros.getString(2);
						LocalDate fechaMatriculacion=registros.getDate(3).toLocalDate();
				        LocalDate fechaAnulacion = null;
				        if (registros.getDate(4) != null) {
				            fechaAnulacion = registros.getDate(4).toLocalDate();
				        }
						String dni=registros.getString(5);

						
						ArrayList<Asignatura> coleccionAsignaturas=getAsignaturasMatricula(idMatricula);
						Matricula nuevaMatricula=new Matricula(idMatricula,cursoAcademico,fechaMatriculacion,alumno,coleccionAsignaturas);
	
						coleccionMatriculas.add(nuevaMatricula);	

				
				}
				else {
					System.out.println("No existen matriculas para este alumno");
				}
			}
			catch (SQLException e) {
				throw new IllegalArgumentException("ERROR:" + e.getMessage());
			}
			System.out.println(coleccionMatriculas);
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
					
						int idMatricula=registros.getInt(1);
						String cursoAcademicoLocalizado=registros.getString(2);
						LocalDate fechaMatriculacion=registros.getDate(3).toLocalDate();
				        LocalDate fechaAnulacion = null;
				        if (registros.getDate(4) != null) {
				            fechaAnulacion = registros.getDate(4).toLocalDate();}
				        
						String dni=registros.getString(5);

						
						ArrayList<Asignatura> coleccionAsignaturas=getAsignaturasMatricula(idMatricula);
						
						Alumnos alumnos= new Alumnos();
						ArrayList <Alumno> copiaAlumnos=alumnos.get();
						Alumno alumnoBuscado=null;
						int i=0;
						boolean encontrado=false;
						
						for(Alumno alumnoEncontrado: copiaAlumnos) {
							i++;
							if(alumnoEncontrado.getDni().equals(dni)) {
								alumnoBuscado=copiaAlumnos.get(i-1);
								Matricula nuevaMatricula=new Matricula(idMatricula,cursoAcademicoLocalizado,fechaMatriculacion,alumnoBuscado,coleccionAsignaturas);
								coleccionMatriculas.add(nuevaMatricula);
							}else {
								encontrado=true;
							}
						}
					}
				
				else {
					System.out.println("No existen matriculas para curso academico");
				}
			}
			catch (SQLException e) {
				throw new IllegalArgumentException("ERROR:" + e.getMessage());
			}
			System.out.println(coleccionMatriculas);
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
					ResultSet registros2=preparedStatement2.executeQuery();
					
					while(registros2.next()){
						
					
					int idMatricula=registros2.getInt(1);
					int codigo2=registros2.getInt(2);
					
					PreparedStatement preparedStatement3=conexion.prepareStatement("select idMatricula, cursoAcademico, fechaMatriculacion,fechaAnulacion, dni from matricula where idMatricula = ?");
					preparedStatement3.setInt(1,idMatricula);
					ResultSet registros3=preparedStatement3.executeQuery();
					
					if(registros3.next()) {
						int idMatricula2=registros3.getInt(1);
						String cursoAcademicoLocalizado=registros3.getString(2);
						LocalDate fechaMatriculacion=registros3.getDate(3).toLocalDate();
						LocalDate fechaAnulacion = null;
						if (registros3.getDate(4) != null) {
							fechaAnulacion = registros3.getDate(4).toLocalDate();
						}
					String dni=registros3.getString(5);
					
					
					
					ArrayList<Asignatura> coleccionAsignaturas=getAsignaturasMatricula(idMatricula);
					
					Alumnos alumnos= new Alumnos();
					ArrayList <Alumno> copiaAlumnos=alumnos.get();
					Alumno alumnoBuscado=null;
					int i=0;
					boolean encontrado=false;
					
					for(Alumno alumnoEncontrado: copiaAlumnos) {
						i++;
						if(alumnoEncontrado.getDni().equals(dni)) {
							alumnoBuscado=copiaAlumnos.get(i-1);
							Matricula nuevaMatricula=new Matricula(idMatricula2,cursoAcademicoLocalizado,fechaMatriculacion,alumnoBuscado,coleccionAsignaturas);
							coleccionMatriculas.add(nuevaMatricula);
						}else {
							encontrado=true;
						}
					}
					}
					}		
				}
				else {
					throw new NullPointerException("No existe este codigo de ciclo formativo en el sistema");
				}
			}
				
			catch (SQLException e) {
				throw new IllegalArgumentException("ERROR:" + e.getMessage());
			}
			System.out.println(coleccionMatriculas);
			return coleccionMatriculas;
		}
	}



}
