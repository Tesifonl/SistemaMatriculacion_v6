package org.iesalandalus.programacion.matriculacion;




import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.vista.Vista;
import org.iesalandalus.programacion.matriculacion.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.matriculacion.vista.texto.VistaTexto;

import javax.naming.OperationNotSupportedException;


public class MainApp {
   		public static void main(String[] args)   {
    	Modelo modelo=procesarArgumentosFuenteDatos(args);
    	Vista vista = procesarArgumentosVista (args);
    	Controlador controlador=new Controlador(modelo, vista);
		controlador.comenzar();

        }

	private static Modelo procesarArgumentosFuenteDatos (String[] args) {
		Modelo modelo = null;

		for (String argumento : args) {
			if (argumento.equalsIgnoreCase("-fdmemoria")) {
				modelo = new Modelo(FactoriaFuenteDatos.MEMORIA);
			} else if (argumento.equalsIgnoreCase("-fdmysql")) {
				modelo = new Modelo(FactoriaFuenteDatos.MYSQL);
			} else /* (argumento.equalsIgnoreCase("-fdfichero"))*/{
				modelo = new Modelo(FactoriaFuenteDatos.FICHEROS);
			}
			/*else{
				modelo = new Modelo(FactoriaFuenteDatos.MEMORIA);
			}*/
		}

		return modelo;
	}

	private static Vista procesarArgumentosVista (String[] args) {
		Vista vista = null;

		for (String argumento : args) {
			if (argumento.equalsIgnoreCase("-vTexto")) {
				vista = new VistaTexto();

			} else if (argumento.equalsIgnoreCase("-vGrafica")) {
				vista = new VistaGrafica();
			}
		}

		return vista;
	}

}
