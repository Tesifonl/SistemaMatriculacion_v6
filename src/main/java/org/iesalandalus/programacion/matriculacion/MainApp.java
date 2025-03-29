package org.iesalandalus.programacion.matriculacion;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades.MySQL;
import org.iesalandalus.programacion.matriculacion.vista.Vista;


public class MainApp {

    
    public static void main(String[] args)  {
    	
    	//MySQL.establecerConexion();
    	//MySQL.cerrarConexion();
    	
    	Modelo modelo=new Modelo();
    	Vista vista=new Vista();
    	Controlador controlador=new Controlador(modelo, vista);
    	controlador.comenzar();
    }



}
