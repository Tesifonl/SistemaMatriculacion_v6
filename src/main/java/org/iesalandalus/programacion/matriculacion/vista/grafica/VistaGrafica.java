package org.iesalandalus.programacion.matriculacion.vista.grafica;


import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.vista.Vista;

public  class VistaGrafica extends Vista {

    private static VistaGrafica instancia;


    public static VistaGrafica getInstancia(){
        if (instancia==null)
            instancia=new VistaGrafica();

        return instancia;
    }


    @Override
    public void comenzar(){
    LanzadorVentanaPrincipal.comenzar();

    }

    @Override
    public void terminar() {
        Controlador controlador=getControlador();
        controlador.terminar();
    }

}
