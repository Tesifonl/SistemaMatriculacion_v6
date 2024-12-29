package org.iesalandalus.programacion.matriculacion;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.vista.Vista;


public class MainApp {
	
	public static final int CAPACIDAD=100;
    
    public static void main(String[] args) throws OperationNotSupportedException {
    	
    	Modelo modelo=new Modelo();
    	Vista vista=new Vista();
    	Controlador controlador=new Controlador(modelo, vista);
    	controlador.comenzar();
    }



}
