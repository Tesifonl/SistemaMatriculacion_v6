package org.iesalandalus.programacion.matriculacion.vista;


import org.iesalandalus.programacion.matriculacion.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.matriculacion.vista.texto.VistaTexto;

public enum FactoriaVista {


    GRAFICA {
        @Override
         public Vista crear() {
            // TODO Auto-generated method stub
            return new VistaGrafica();
        }
    },
    TEXTO {
        @Override
         public Vista crear() {
            // TODO Auto-generated method stub
            return new VistaTexto();
        }
    };

   public abstract Vista crear();


}
