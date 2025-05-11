package org.iesalandalus.programacion.matriculacion.vista.grafica.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.matriculacion.vista.grafica.utilidades.Dialogos;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class ControladorInsertarCicloFormativo {

    @FXML private Button btAceptarInsertarCicloFormativo;
    @FXML private Button btCancelarInsertarCicloFormativo;
    @FXML private TextField tfCodigo;
    @FXML private TextField tfFamiliaProfesional;
    @FXML private TextField tfHoras;
    //@FXML private TextField tfModalidad;
    @FXML private TextField tfNombre;
    @FXML private TextField tfNombreGrado;
    @FXML private TextField tfNumeroAnios;
    @FXML private TextField tfNumEdiciones;
    @FXML private RadioButton rbTipoGradoD;
    @FXML private RadioButton rbTipoGradoE;
    @FXML private RadioButton rbModalidadPresencial;
    @FXML private RadioButton rbModalidadSemipresencial;
    @FXML private ToggleGroup tamano;
    @FXML private ToggleGroup tamanoSegundo;


    private ObservableList<CicloFormativo> obsListadoCiclosFormativos= FXCollections.observableArrayList();
    private List<CicloFormativo> coleccionCiclosFormativos=new ArrayList<>();

    public void cargaDatos(List<CicloFormativo> coleccionCiclosFormativos, ObservableList<CicloFormativo> obsListadoCiclosFormativos){
        this.coleccionCiclosFormativos=coleccionCiclosFormativos;
        this.obsListadoCiclosFormativos=obsListadoCiclosFormativos;
    }

    @FXML void aceptarInsertarCicloFormativo(ActionEvent event) {

        try {
            Modalidad modalidad=null;
            Grado grado=null;
            RadioButton rbSeleccionado=(RadioButton) tamano.getSelectedToggle();
            if (rbSeleccionado.equals(rbTipoGradoD))
            {
                RadioButton rbSeleccionadoSegundo=(RadioButton) tamanoSegundo.getSelectedToggle();
                if(rbSeleccionadoSegundo.equals(rbModalidadPresencial)) {
                    modalidad = Modalidad.PRESENCIAL;
                    grado = new GradoD(tfNombreGrado.getText(), Integer.parseInt(tfNumeroAnios.getText()), modalidad);
                }else {
                    modalidad = Modalidad.SEMIPRESENCIAL;
                    grado = new GradoD(tfNombreGrado.getText(), Integer.parseInt(tfNumeroAnios.getText()), modalidad);
                }
            }else{
                 grado=new GradoE (tfNombreGrado.getText(),Integer.parseInt(tfNumeroAnios.getText()),Integer.parseInt(tfNumEdiciones.getText()));

            }

            CicloFormativo cicloFormativo =new CicloFormativo(Integer.parseInt(tfCodigo.getText()),tfFamiliaProfesional.getText(),grado,tfNombre.getText(),Integer.parseInt(tfHoras.getText()));

            if (coleccionCiclosFormativos.contains(cicloFormativo)){
                Dialogos.mostrarDialogoError("Isertar Ciclo Formativo","El ciclo formativo ya existe");
            }else{
                VistaGrafica.getControlador().insertarCicloFormativo(cicloFormativo);
                coleccionCiclosFormativos=VistaGrafica.getControlador().getCiclosFormativos();
                obsListadoCiclosFormativos.setAll(coleccionCiclosFormativos);
                Dialogos.mostrarDialogoInformacion("Insertar Ciclo Formativo", "Ciclo formativo insertado correctamente");

                ((Stage)btAceptarInsertarCicloFormativo.getScene().getWindow()).close();
            }
        } catch (OperationNotSupportedException | IllegalArgumentException |com.mysql.cj.jdbc.exceptions.OperationNotSupportedException |NullPointerException e) {
            Dialogos.mostrarDialogoError("Error datos", e.getMessage());
        }

    }


    @FXML public void initialize() {

        //tfModalidad.setDisable(true);
        tfNumeroAnios.setDisable(true);
        tfNumEdiciones.setDisable(true);
        rbModalidadPresencial.setDisable(true);
        rbModalidadSemipresencial.setDisable(true);


        tamano.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle == rbTipoGradoD) {
                //tfModalidad.setDisable(false);
                rbModalidadPresencial.setDisable(false);
                rbModalidadSemipresencial.setDisable(false);
                tfNumeroAnios.setDisable(false);
                tfNumEdiciones.setDisable(true);
            } else if (newToggle == rbTipoGradoE) {
                //tfModalidad.setDisable(true);
                rbModalidadPresencial.setDisable(true);
                rbModalidadSemipresencial.setDisable(true);
                tfNumeroAnios.setDisable(false);
                tfNumEdiciones.setDisable(false);
            }
        });

    }


    @FXML void cancelarInsertarCicloFormativo(ActionEvent event) {
        ((Stage) btCancelarInsertarCicloFormativo.getScene().getWindow()).close();
    }




}
