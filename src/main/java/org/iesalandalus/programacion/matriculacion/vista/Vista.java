package org.iesalandalus.programacion.matriculacion.vista;

import javafx.application.Application;
import org.iesalandalus.programacion.matriculacion.controlador.Controlador;

import javax.naming.OperationNotSupportedException;

public abstract class Vista {

    private static Controlador controlador;

    public void setControlador (Controlador controlador){
        if(controlador!=null){
            this.controlador=controlador;
        }else {
            throw new NullPointerException("Error: se ha recibido un controlador nulo");
        }
    }

    public static Controlador getControlador(){
        return controlador;
    }

    public abstract void  comenzar() ;
    public abstract void  terminar();

}
