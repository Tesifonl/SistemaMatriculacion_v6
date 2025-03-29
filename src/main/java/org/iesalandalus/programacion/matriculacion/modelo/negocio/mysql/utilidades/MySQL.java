package org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
	
	private static final String HOST="database-1.cvfy4igo31vd.us-east-1.rds.amazonaws.com";
	private static final String ESQUEMA="sistemamatriculacion" ;
	private static final String USUARIO="admin" ;
	private static final String CONTRASENA="Tescargab230513. " ;
	private static Connection conexion;
	
	
	private void MySQL() {
		
	}
	
	public static Connection establecerConexion() {
		if (conexion != null)
			return conexion;
		try {
			String urlConexion = String.format("jdbc:mysql://%s/%s?user=%s&password=%s&useSSL=false&serverTimezon=UTC", HOST, ESQUEMA, USUARIO, CONTRASENA);
			conexion = DriverManager.getConnection(urlConexion);
			System.out.println("Conexión a MySQL realizada correctamente.");
		} catch (SQLException e) {
			System.out.println("ERROR MySQL:  "+ e.toString());
		}
		return conexion;
	}
	
	
	public static void cerrarConexion() {
		try {
			if (conexion != null) {
				conexion.close();
				conexion = null;
				System.out.println("Conexión a MySQL cerrada correctamente.");
			}
		} catch (SQLException e) {
			System.out.println("ERROR MySQL: "+ e.toString());
		}
	}
}


