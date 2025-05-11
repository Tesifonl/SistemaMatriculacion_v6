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

public class ControladorInsertarAsignatura {

    @FXML private Button btAceptarInsertarAsignatura;
    @FXML private Button btCancelarInsertarAsignatura;
    @FXML private TextField tfCodigoAsignatura;
    @FXML private TextField tfCodigoCicloFormativo;
    @FXML private TextField tfHorasAnuales;
    @FXML private TextField tfHorasDesdoble;
    @FXML private TextField tfNombreAsignatura;
    @FXML private RadioButton rbCursoPrimero;
    @FXML private RadioButton rbCursoSegundo;
    @FXML private RadioButton rbEspecialidadInformatica;
    @FXML private RadioButton rbEspecialidadSistemas;
    @FXML private ToggleGroup relacionGrupo;
    @FXML private ToggleGroup relacionEspecialidad;


    private ObservableList<Asignatura> obsListadoAsignaturas= FXCollections.observableArrayList();
    private List<Asignatura> coleccionAsignaturas=new ArrayList<>();

    public void cargaDatos(List<Asignatura> coleccionAsignaturas, ObservableList<Asignatura> obsListadoAsignaturas){
        this.coleccionAsignaturas=coleccionAsignaturas;
        this.obsListadoAsignaturas=obsListadoAsignaturas;
    }

    @FXML void aceptarInsertarAsignatura(ActionEvent event) {
        Curso curso=null;
        EspecialidadProfesorado especialidadProfesorado=null;
        try {
            RadioButton rbSeleccionado=(RadioButton) relacionGrupo.getSelectedToggle();
            if(rbSeleccionado.equals(rbCursoPrimero)) {
                curso=Curso.PRIMERO;
            }else {
                curso=Curso.SEGUNDO;
            }

            RadioButton rbSeleccionadoSegundo=(RadioButton) relacionEspecialidad.getSelectedToggle();
            if(rbSeleccionadoSegundo.equals(rbEspecialidadInformatica)) {
                especialidadProfesorado=EspecialidadProfesorado.INFORMATICA;
            }else if (rbSeleccionadoSegundo.equals(rbEspecialidadSistemas)) {
                especialidadProfesorado=EspecialidadProfesorado.SISTEMAS;
            }else{
                especialidadProfesorado=EspecialidadProfesorado.FOL;
            }


            Grado gradoFicticio=new GradoE("DW",1,1);
            CicloFormativo cicloFormativoFicticio =new CicloFormativo(Integer.parseInt(tfCodigoCicloFormativo.getText()),"Semipresencial",gradoFicticio,"DAW",100);
            CicloFormativo cicloFormativo=VistaGrafica.getControlador().buscarCicloFormativo(cicloFormativoFicticio);

            Asignatura asignatura=new Asignatura(tfCodigoAsignatura.getText(),tfNombreAsignatura.getText(),Integer.parseInt(tfHorasAnuales.getText()),curso,Integer.parseInt(tfHorasDesdoble.getText()),especialidadProfesorado,cicloFormativo);
            if (coleccionAsignaturas.contains(asignatura)){
                Dialogos.mostrarDialogoError("Isertar Asignatura","La asignatura ya existe");
            }else{


                VistaGrafica.getControlador().insertarAsignatura(asignatura);
                coleccionAsignaturas=VistaGrafica.getControlador().getAsignaturas();
                obsListadoAsignaturas.setAll(coleccionAsignaturas);
                Dialogos.mostrarDialogoInformacion("Insertar Asignatura", "Asignatura insertada correctamente");

                ((Stage)btAceptarInsertarAsignatura.getScene().getWindow()).close();
            }
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            Dialogos.mostrarDialogoError("Error datos", e.getMessage());
        }
    }

    @FXML void cancelarInsertarAsignatura(ActionEvent event) {
        ((Stage) btCancelarInsertarAsignatura.getScene().getWindow()).close();
    }




}
