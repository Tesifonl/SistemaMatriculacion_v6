package org.iesalandalus.programacion.matriculacion.vista.grafica.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.matriculacion.vista.grafica.utilidades.Dialogos;

import javax.naming.OperationNotSupportedException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ControladorInsertarAlumno {

    private ObservableList<Alumno> obsListadoAlumnos= FXCollections.observableArrayList();
    private List<Alumno> coleccionAlumnos=new ArrayList<>();

    @FXML private DatePicker dpInsertarAlumnoFecha;
    @FXML private TextField tfInsertarAlumnoCorreo;
    @FXML private TextField tfInsertarAlumnoDni;
    @FXML private TextField tfInsertarAlumnoNombre;
    @FXML private TextField tfInsertarAlumnoTelefono;

    @FXML private Button btAceptarInsertarAlumno;
    @FXML private Button btCancelarInsertarAlumno;

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public void cargaDatos(List<Alumno> coleccionAlumnos, ObservableList<Alumno> obsListadoAlumnos){
        this.coleccionAlumnos=coleccionAlumnos;
        this.obsListadoAlumnos=obsListadoAlumnos;
    }

    @FXML void aceptarInsertarAlumno(ActionEvent event)  {

        try {
        Alumno alumno=new Alumno(tfInsertarAlumnoNombre.getText(),tfInsertarAlumnoDni.getText(),tfInsertarAlumnoCorreo.getText(),tfInsertarAlumnoTelefono.getText(),dpInsertarAlumnoFecha.getValue());

        if (coleccionAlumnos.contains(alumno)){
            Dialogos.mostrarDialogoError("Isertar Alumno","El alumno ya existe");
        }else{


            VistaGrafica.getControlador().insertarAlumno(alumno);
            coleccionAlumnos=VistaGrafica.getControlador().getAlumnos();
            obsListadoAlumnos.setAll(coleccionAlumnos);
            Dialogos.mostrarDialogoInformacion("Insertar Persona", "Persona insertada correctamente");

            ((Stage)btAceptarInsertarAlumno.getScene().getWindow()).close();
        }
    } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
        Dialogos.mostrarDialogoError("Error datos", e.getMessage());
    }
    }

    @FXML void cancelarInsertarAlumno(ActionEvent event) {
        ((Stage) btCancelarInsertarAlumno.getScene().getWindow()).close();
    }

}
