package org.iesalandalus.programacion.matriculacion;


import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.vista.FactoriaVista;
import org.iesalandalus.programacion.matriculacion.vista.Vista;
import org.iesalandalus.programacion.matriculacion.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.matriculacion.vista.texto.VistaTexto;


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
			else {
				modelo = new Modelo(FactoriaFuenteDatos.MYSQL);
			}
		}
    	
    	return modelo;
    }

	private static Vista procesarArgumentosVista (String[] args) {
		Vista vista=null;

		for (String argumento : args) {
			if (argumento.equalsIgnoreCase("-vTexto"))
			{
				vista = new VistaTexto();
			}
			else {
				vista = new VistaGrafica();
			}
		}

		return vista;
	}
    
}
