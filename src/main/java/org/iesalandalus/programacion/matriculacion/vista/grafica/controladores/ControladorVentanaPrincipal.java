package org.iesalandalus.programacion.matriculacion.vista.grafica.controladores;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.vista.grafica.VistaGrafica;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ControladorVentanaPrincipal {

    @FXML private TableView<Alumno> tvAlumnos;
    @FXML private TableColumn<Alumno, String> tcAlumnoCorreo;
    @FXML private TableColumn<Alumno, String> tcAlumnoDni;
    @FXML private TableColumn<Alumno, String> tcAlumnoFechaNacimiento;
    @FXML private TableColumn<Alumno, String> tcAlumnoNombre;
    @FXML private TableColumn<Alumno, String> tcAlumnoTelefono;

    private ObservableList<Alumno> obsListadoAlumnos= FXCollections.observableArrayList();
    private List<Alumno> coleccionAlumnos=new ArrayList<>();

    @FXML private TableView<CicloFormativo> tvCiclosFormativos;
    @FXML private TableColumn<CicloFormativo, String> tcCiclosFormativosCodigo;
    @FXML private TableColumn<CicloFormativo, String> tcCiclosFormativosFamiliaProfesional;
    @FXML private TableColumn<CicloFormativo, String> tcCiclosFormativosGrado;
    @FXML private TableColumn<CicloFormativo, String> tcCiclosFormativosHoras;
    @FXML private TableColumn<CicloFormativo, String> tcCiclosFormativosNombre;
    @FXML private TableColumn<CicloFormativo, String> tcCiclosFormativosNombreGrado;
    @FXML private TableColumn<CicloFormativo, Integer> tcCiclosFormativosNumAnios;
    private ObservableList<CicloFormativo> obsListadoCiclosFormativos= FXCollections.observableArrayList();
    private List<CicloFormativo> coleccionCiclosFormativos=new ArrayList<>();

    @FXML private TableView<Asignatura> tvAsignaturas;
    @FXML private TableColumn<Asignatura, Integer> tcAsignatuasCodigoCiclo;
    @FXML private TableColumn<Asignatura, Integer> tcAsignaturasCodigo;
    @FXML private TableColumn<Asignatura, String> tcAsignaturasCurso;
    @FXML private TableColumn<Asignatura, String> tcAsignaturasEspecialProfesor;
    @FXML private TableColumn<Asignatura, Integer> tcAsignaturasHorasAnual;
    @FXML private TableColumn<Asignatura, Integer> tcAsignaturasHorasDesdoble;
    @FXML private TableColumn<Asignatura, String> tcAsignaturasNombre;
    private ObservableList<Asignatura> obsListadoAsignaturas= FXCollections.observableArrayList();
    private List<Asignatura> coleccionAsignaturas=new ArrayList<>();

    @FXML private TableView<Matricula> tvMatriculas;
    @FXML private TableColumn<Matricula, String> tcMatrciulaCursoAcademico;
    @FXML private TableColumn<Matricula, String> tcMatriculaDNI;
    @FXML private TableColumn<Matricula, String> tcMatriculaFechaAnulacion;
    @FXML private TableColumn<Matricula, String> tcMatriculaFechaMatriculacion;
    @FXML private TableColumn<Matricula, Integer> tcMatriculasIDMatricula;
    private ObservableList<Matricula> obsListadoMatriculas= FXCollections.observableArrayList();
    private List<Matricula> coleccionMatriculas=new ArrayList<>();

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void inicializaObservables() throws OperationNotSupportedException {

        coleccionAlumnos= VistaGrafica.getControlador().getAlumnos();
        //coleccionAlumnos.add(new Alumno("Juan","11111111h","juan@gmail.com","333333333",LocalDate.of(2002, 9, 15)));
        obsListadoAlumnos.setAll(coleccionAlumnos);

        coleccionCiclosFormativos=VistaGrafica.getControlador().getCiclosFormativos();
        obsListadoCiclosFormativos.setAll(coleccionCiclosFormativos);

        coleccionAsignaturas=VistaGrafica.getControlador().getAsignaturas();
        obsListadoAsignaturas.setAll(coleccionAsignaturas);

        coleccionMatriculas=VistaGrafica.getControlador().getMatriculas();
        obsListadoMatriculas.setAll(coleccionMatriculas);
    }

    @FXML private void initialize() throws OperationNotSupportedException {
        inicializaObservables();

        tcAlumnoNombre.setCellValueFactory(new PropertyValueFactory<Alumno,String>("nombre"));
        //tcAlumnoNombre.setCellValueFactory(alumno -> new SimpleStringProperty(alumno.getValue().getNombre()));
        tcAlumnoTelefono.setCellValueFactory(new PropertyValueFactory<Alumno,String>("telefono"));
        //tcAlumnoTelefono.setCellValueFactory(alumno -> new SimpleStringProperty(alumno.getValue().getTelefono()));
        tcAlumnoCorreo.setCellValueFactory(new PropertyValueFactory<Alumno,String>("correo"));
        //tcAlumnoCorreo.setCellValueFactory(alumno -> new SimpleStringProperty(alumno.getValue().getCorreo()));
        tcAlumnoDni.setCellValueFactory(new PropertyValueFactory<Alumno,String>("dni"));
        //tcAlumnoDni.setCellValueFactory(alumno -> new SimpleStringProperty(alumno.getValue().getDni()));
        tcAlumnoFechaNacimiento.setCellValueFactory(new PropertyValueFactory<Alumno,String>("fechaNacimiento"));
        //tcAlumnoFechaNacimiento.setCellValueFactory(alumno -> new SimpleStringProperty(alumno.getValue().getFechaNacimiento().format(FORMATO_FECHA)));
        tvAlumnos.setItems(obsListadoAlumnos);


        tcCiclosFormativosCodigo.setCellValueFactory(new PropertyValueFactory<CicloFormativo,String>("codigo"));
        tcCiclosFormativosFamiliaProfesional.setCellValueFactory(new PropertyValueFactory<CicloFormativo,String>("familiaProfesional"));
        tcCiclosFormativosGrado.setCellValueFactory(new PropertyValueFactory<CicloFormativo,String>("grado"));
        tcCiclosFormativosNombre.setCellValueFactory(new PropertyValueFactory<CicloFormativo,String>("nombre"));
        tcCiclosFormativosHoras.setCellValueFactory(new PropertyValueFactory<CicloFormativo,String>("horas"));
        //tcCiclosFormativosNombreGrado.setCellValueFactory(new PropertyValueFactory<Grado,String>("nombreGrado"));
        //tcCiclosFormativosNumAnios.setCellValueFactory(new PropertyValueFactory<Grado,Integer>("numAniosGrado"));
        tcCiclosFormativosNombreGrado.setCellValueFactory(cicloFormativo -> new SimpleStringProperty(cicloFormativo.getValue().getGrado().getNombre()));
        tcCiclosFormativosNumAnios.setCellValueFactory(cicloFormativo -> new SimpleIntegerProperty(cicloFormativo.getValue().getGrado().getNumAnios()).asObject());
        tvCiclosFormativos.setItems(obsListadoCiclosFormativos);

        tcAsignaturasCodigo.setCellValueFactory(new PropertyValueFactory<Asignatura,Integer>("codigo"));
        tcAsignaturasNombre.setCellValueFactory(new PropertyValueFactory<Asignatura,String>("nombre"));
        tcAsignaturasCurso.setCellValueFactory(new PropertyValueFactory<Asignatura,String>("curso"));
        tcAsignaturasEspecialProfesor.setCellValueFactory(new PropertyValueFactory<Asignatura,String>("especialidadProfesorado"));
        tcAsignaturasHorasAnual.setCellValueFactory(new PropertyValueFactory<Asignatura,Integer>("horasAnuales"));
        tcAsignaturasHorasDesdoble.setCellValueFactory(new PropertyValueFactory<Asignatura,Integer>("horasDesdoble"));
        tcAsignatuasCodigoCiclo.setCellValueFactory(asignatura -> new SimpleIntegerProperty(asignatura.getValue().getCicloFormativo().getCodigo()).asObject());
        tvAsignaturas.setItems(obsListadoAsignaturas);

        tcMatriculasIDMatricula.setCellValueFactory(new PropertyValueFactory<Matricula,Integer>("idMatricula"));
        tcMatrciulaCursoAcademico.setCellValueFactory(new PropertyValueFactory<Matricula,String>("cursoAcademico"));
        tcMatriculaFechaMatriculacion.setCellValueFactory(new PropertyValueFactory<Matricula,String>("fechaMatriculacion"));
        tcMatriculaFechaAnulacion.setCellValueFactory(new PropertyValueFactory<Matricula,String>("fechaAnulacion"));
        tcMatriculaDNI.setCellValueFactory(new PropertyValueFactory<Matricula,String>("dni"));
        tvMatriculas.setItems(obsListadoMatriculas);
    }

}
