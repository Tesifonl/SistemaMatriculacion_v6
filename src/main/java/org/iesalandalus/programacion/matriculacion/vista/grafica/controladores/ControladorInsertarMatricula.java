package org.iesalandalus.programacion.matriculacion.vista.grafica.controladores;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.matriculacion.vista.grafica.utilidades.Dialogos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControladorInsertarMatricula {


    @FXML
    private Button btAceptarInsertarMatricula;
    @FXML
    private Button btCancelarInsertarMatricula;
    @FXML
    private Button rbIncorporarAsignaturas;
    @FXML
    private TextField tfCursoAcademico;
    @FXML
    private TextField tfDni;
    @FXML
    private TextField tfFechaMatriculacion;
    @FXML
    private TextField tfIDMatricula;
    @FXML
    private DatePicker dpFechaMatriculacion;

    @FXML
    private TableView<Asignatura> tvAsignaturas;
    @FXML
    private TableColumn<Asignatura, Integer> tcAsignaturasCodigo;
    @FXML
    private TableColumn<Asignatura, String> tcAsignaturasNombre;


    private ObservableList<Matricula> obsListadoMatriculas = FXCollections.observableArrayList();
    private List<Matricula> coleccionMatriculas = new ArrayList<>();

    private ObservableList<Asignatura> obsListadoAsignaturas = FXCollections.observableArrayList();
    private List<Asignatura> coleccionAsignaturas = new ArrayList<>();


    public void cargaDatos(List<Matricula> coleccionMatriculas, ObservableList<Matricula> obsListadoMatriculas) {
        this.coleccionMatriculas = coleccionMatriculas;
        this.obsListadoMatriculas = obsListadoMatriculas;
    }


    @FXML
    void aceptarInsertarMatricula(ActionEvent event) {
        try {

            Alumno alumnoFicticio = new Alumno("Tesi", tfDni.getText(), "Tesi@gmail.com", "999999999", LocalDate.of(1979, 1, 8));
            Alumno alumno = VistaGrafica.getControlador().buscarAlumno(alumnoFicticio);

            ArrayList<Asignatura> coleccionAsignaturas = null;
            coleccionAsignaturas = incorporarAsignaturas(event);

            Matricula matricula = new Matricula(Integer.parseInt(tfIDMatricula.getText()), tfCursoAcademico.getText(), dpFechaMatriculacion.getValue(), alumno, coleccionAsignaturas);

            if (coleccionMatriculas.contains(matricula)) {
                Dialogos.mostrarDialogoError("Isertar Matricula", "La matricula ya existe");
            } else {


                VistaGrafica.getControlador().insertarMatricula(matricula);
                coleccionMatriculas = VistaGrafica.getControlador().getMatriculas();
                obsListadoMatriculas.setAll(coleccionMatriculas);
                Dialogos.mostrarDialogoInformacion("Insertar Matricula", "Matricula insertada correctamente");

                ((Stage) btAceptarInsertarMatricula.getScene().getWindow()).close();
            }
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            Dialogos.mostrarDialogoError("Error datos", e.getMessage());
        }
    }

    @FXML
    void cancelarInsertarMatricula(ActionEvent event) {
        ((Stage) btCancelarInsertarMatricula.getScene().getWindow()).close();
    }


    public void inicializaObservables() {
        try {
            coleccionAsignaturas = VistaGrafica.getControlador().getAsignaturas();
            obsListadoAsignaturas.setAll(coleccionAsignaturas);
        } catch (NullPointerException e) {
            Dialogos.mostrarDialogoError("Error datos", e.getMessage());
        }

    }


    @FXML
    private void initialize() throws OperationNotSupportedException {
        inicializaObservables();

        tcAsignaturasCodigo.setCellValueFactory(new PropertyValueFactory<Asignatura, Integer>("codigo"));
        tcAsignaturasNombre.setCellValueFactory(new PropertyValueFactory<Asignatura, String>("nombre"));
        tvAsignaturas.setItems(obsListadoAsignaturas);

    }

    @FXML ArrayList<Asignatura> incorporarAsignaturas(ActionEvent event) {

        Asignatura asignatura = tvAsignaturas.getSelectionModel().getSelectedItem();
        if (asignatura == null) {
            Dialogos.mostrarDialogoAdvertencia("Insertar asignatura", "Debes seleccionar una asignatura para realizar esta operación");
            return null;
        } else {
            ArrayList<Asignatura> coleccionAsignaturas = new ArrayList<>();
            coleccionAsignaturas.add(asignatura);
            rbIncorporarAsignaturas.getStyleClass().add("boton-verde");
            return coleccionAsignaturas;
        }
    }
}
