/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package estacionamiento;
import Controllers.VistaController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author box
 */
public class Estacionamiento extends Application {

    @Override
    public void start(Stage stage) throws Exception { 
        //Entry Point de mi aplicación
        //Cargo la vista de fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Vista.fxml"));
        
        //Creo la escena
        Scene scene = new Scene(loader.load()); //Utilizo el metodo que viene con FXMLLoader
        VistaController controladorVista = loader.getController();
        
        //Seteo la escena en el stage
        stage.setScene(scene);
        
        //Muestro
        stage.setOnCloseRequest(e -> controladorVista.GuardarArchivo()); 
        stage.show();
        
    }
    
    public static void main(String[] args) {
        Application.launch();
    }
    
}
