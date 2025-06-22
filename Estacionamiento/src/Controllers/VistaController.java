/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;
import Excepciones.VehiculoRepetidoException;
import Interfaces.ISerializableCsv;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    
    private CSVUtilsGeneric<ISerializableCsv> herramientaCsv;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        estacionamiento = new Estacionamiento();
        herramientaCsv = new CSVUtilsGeneric<>();
        this.LeerArchivo();
               
    }    
    
    @FXML
    public void agregar(ActionEvent evento){
        this.AbrirFormulario(null);
    }
        
    @FXML
    public void modificar(ActionEvent evento){
         Vehiculo vehiculo = listViewVehiculos.getSelectionModel().getSelectedItem();

         if(vehiculo != null) {
             this.AbrirFormulario(vehiculo);
         }
     }

    @FXML
    public void eliminar(ActionEvent evento){
         Vehiculo vehiculo = listViewVehiculos.getSelectionModel().getSelectedItem();
         if (vehiculo != null) {
             Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
             alerta.setTitle("Confirmar eliminacion");
             alerta.setHeaderText("Estas seguro que quiere eliminar al vehiculo?");
             alerta.setContentText(vehiculo.toString());
             Optional<ButtonType> resultado = alerta.showAndWait();

             if (resultado.isPresent() && resultado.get()== ButtonType.OK) {
                 estacionamiento.eliminarVehiculo(vehiculo);
                 this.refrescarVista();
             }
         }
    }
        
    public void refrescarVista(){
        this.listViewVehiculos.getItems().clear();
        this.listViewVehiculos.getItems().addAll(estacionamiento.getVehiculos());
        this.GuardarArchivo();
    }   
        
    private void AbrirFormulario(Vehiculo vehiculo) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Formulario.fxml"));
    
            Scene scene = new Scene(loader.load());
            FormularioController controlador = loader.getController();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);               
            controlador.setVehiculo(vehiculo);
            stage.showAndWait();
            Vehiculo resultado = controlador.getVehiculo();
                if(resultado != null) { // respuesta controlador
                    if(vehiculo == null){
                        if (!estacionamiento.getVehiculos().contains(resultado)){
                            this.estacionamiento.agregarVehiculo(resultado);
                        }
                    }
                }
                this.refrescarVista();
        }
        catch(IOException | VehiculoRepetidoException e){
            System.out.println("Error:" + e.getMessage());
        }
    }

    public void GuardarArchivo() {
        ArrayList<ISerializableCsv> data = new ArrayList<>();
        for(Vehiculo item : this.estacionamiento.getVehiculos()){
            if(item instanceof ISerializableCsv vehiculo){ // Repasar
                data.add(vehiculo);
            }
        }
        herramientaCsv.escribirCSV(data);
    }

    public void LeerArchivo() {
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        ArrayList<String> resultado = herramientaCsv.leerCSV("datos.csv");

        for (String item : resultado ) {
            if(item == null || item.isEmpty()) {
                continue;
            }
            String[] result = item.split(",");
            Vehiculo vehiculo = null;

            if (result[6].equals("Auto")) {
                vehiculo = new Auto().fromCSV(item);
            }

            if (result[6].equals("Camioneta")) {
                vehiculo = new Camioneta().fromCSV(item);
            }

            if (result[6].equals("Moto")) {
                vehiculo = new Moto().fromCSV(item);
            }
            if (vehiculo != null) {
                vehiculos.add(vehiculo);
            }
        }
        estacionamiento = new Estacionamiento();
        estacionamiento.getVehiculos().addAll(vehiculos);
        this.refrescarVista();
    }
}

