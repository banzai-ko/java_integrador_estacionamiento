/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;
import Excepciones.VehiculoRepetidoException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.*;

/**
 *
 * @author box
 */
public class VistaController implements Initializable {

    @FXML
    private Button btnAgregar;
    
    @FXML
    private Button btnModificar;
    
    @FXML
    private Button btnEliminar;
        
    @FXML 
    private ListView<Vehiculo> listViewVehiculos;

    private Estacionamiento estacionamiento;
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        estacionamiento = new Estacionamiento();
    }    
    
    @FXML
        public void agregar(ActionEvent evento){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Formulario.fxml"));
        
        Scene scene = new Scene(loader.load());
        FormularioController controlador = loader.getController();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);               
        stage.showAndWait();
        Vehiculo nuevo = controlador.getVehiculo();
                if(nuevo != null){
                    this.estacionamiento.agregarVehiculo(nuevo);
                    this.refrescarVista();
                    System.out.println("Agregado");
                }
            }
            
            catch(IOException  | VehiculoRepetidoException e){
                           
            }
        }
        
       @FXML
        public void modificar(ActionEvent evento){
            
            
        }
        
        @FXML
        public void eliminar(ActionEvent evento){
            
            
        }
        
        public void refrescarVista(){
            this.listViewVehiculos.getItems().clear();
            this.listViewVehiculos.getItems().addAll(estacionamiento.getVehiculos());
        }   
}

