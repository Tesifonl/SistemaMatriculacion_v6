package org.iesalandalus.programacion.matriculacion;


import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.vista.Vista;

import com.mysql.cj.jdbc.exceptions.OperationNotSupportedException;


public class MainApp {

    
    public static void main(String[] args) throws OperationNotSupportedException  {
    	
    	//MySQL.establecerConexion();
    	//MySQL.cerrarConexion();
    	
    	Modelo modelo=procesarArgumentosFuenteDatos(args);
    	Vista vista=new Vista();
    	Controlador controlador=new Controlador(modelo, vista);
    	controlador.comenzar();
    }

    private static Modelo procesarArgumentosFuenteDatos (String[] args) {
    	Modelo modelo=null;
    	
		for (String argumento : args) {
			if (argumento.equalsIgnoreCase("-fdmemoria")) 
			{
				modelo = new Modelo(FactoriaFuenteDatos.MEMORIA);
			} 
			else if (argumento.equalsIgnoreCase("-fdmysql")) 
			{
				modelo = new Modelo(FactoriaFuenteDatos.MYSQL);
			}
		}
    	
    	return modelo;
    }
    
}
