/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;
import Excepciones.VehiculoRepetidoException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private Button btnExit;
        
    @FXML 
    private ListView<Vehiculo> listViewVehiculos;

    private Estacionamiento estacionamiento;
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        estacionamiento = new Estacionamiento();
        File csvFile = new File("data.csv");
        if (csvFile.exists()) {
            ArrayList<Vehiculo> listaAutos = models.CsvSerializer.leerCSV("data.csv");
            for (Vehiculo item : listaAutos) {
                estacionamiento.agregarVehiculo(item);
            }
        } else {
            System.out.println("Archivo data.csv no encontrado. Se cargará una lista vacía.");
        }

        this.refrescarVista();
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
        public void modificar(ActionEvent evento) {
            Vehiculo seleccionado = listViewVehiculos.getSelectionModel().getSelectedItem();
            if (seleccionado == null) {
                System.out.println("No hay vehículo seleccionado para modificar.");
                return;
            }

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Formulario.fxml"));
                Scene scene = new Scene(loader.load());
                FormularioController controlador = loader.getController();

                // Pasar el vehículo seleccionado para prellenar el formulario
                controlador.setVehiculo(seleccionado);

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                Vehiculo modificado = controlador.getVehiculo();
                if (modificado != null) {
                    estacionamiento.modificarVehiculo(seleccionado, modificado);
                    refrescarVista();
                    System.out.println("Vehículo modificado.");
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (VehiculoRepetidoException ex) {
                System.out.println("Ya existe un vehículo igual.");
            }
        }

        
        @FXML
        public void eliminar(ActionEvent evento) {
            Vehiculo seleccionado = listViewVehiculos.getSelectionModel().getSelectedItem();
            if (seleccionado == null) {
                System.out.println("No hay vehículo seleccionado para eliminar.");
                return;
            }

            try {
                estacionamiento.eliminarVehiculo(seleccionado);
                refrescarVista();
                System.out.println("Vehículo eliminado.");
            } catch (Exception e) {
                System.out.println("Error al eliminar: " + e.getMessage());
            }
        }
        
        public void refrescarVista(){
            this.listViewVehiculos.getItems().clear();
            this.listViewVehiculos.getItems().addAll(estacionamiento.getVehiculos());
        }
        
        @FXML
        public void exit(ActionEvent evento) {
            this.cerrar();
        }

        private void cerrar() {
        try {
            if (!estacionamiento.getVehiculos().isEmpty()){
                models.CsvSerializer.serializeToCsv(estacionamiento.getVehiculos(), "data.csv");
            }
            
        } catch (IOException ex) {
            System.out.println("No file to write >>> EXIT!");
        }
            Stage stage = (Stage) btnExit.getScene().getWindow();
            
            stage.close();
        }
}   

