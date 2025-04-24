package org.iesalandalus.programacion.matriculacion.vista.grafica;




import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.iesalandalus.programacion.matriculacion.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.matriculacion.vista.grafica.utilidades.Dialogos;




public class LanzadorVentanaPrincipal extends Application {


    public static void comenzar(){
        launch();
    }

    @Override
    public void start(Stage escenarioPrincipal) throws Exception {
        try
        {

            FXMLLoader fxmlLoader = new FXMLLoader(LocalizadorRecursos.class.getResource("/vistas/VentanaPrincipal.fxml"));
            Parent raiz=fxmlLoader.load();


            Scene escena = new Scene(raiz, 800, 600);
            escenarioPrincipal.setTitle("Hola mundo desde JavaFX");
            escenarioPrincipal.setScene(escena);
            //escenarioPrincipal.setOnCloseRequest(e->confirmarSalida(escenarioPrincipal,e));
            escenarioPrincipal.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void confirmarSalida(Stage escenarioPrincipal, WindowEvent e){
        if (Dialogos.mostrarDialogoConfirmacion("vistas/Ventana Principal","¿Ralmente quiere salir")){
            escenarioPrincipal.close();
        }else{
            e.consume();
            //Esto lo que hace es continuar en la pantalla por la que ibamos.
        }
    }


}
