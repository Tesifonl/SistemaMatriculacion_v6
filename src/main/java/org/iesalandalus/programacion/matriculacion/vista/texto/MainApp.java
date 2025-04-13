package org.iesalandalus.programacion.matriculacion.vista.texto;


import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;


public class MainApp {

    
    public static void main(String[] args)  {
    	
 	
    	Modelo modelo=procesarArgumentosFuenteDatos(args);
    	VistaTexto vistaTexto =new VistaTexto();
    	Controlador controlador=new Controlador(modelo, vistaTexto);
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
