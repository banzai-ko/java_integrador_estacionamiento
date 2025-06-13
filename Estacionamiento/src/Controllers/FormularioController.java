/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Auto;
import models.*;
/**
 *
 * @author box
 */
   
public class FormularioController implements Initializable {
    @FXML
    ChoiceBox <String> CBTipo;
    
    @FXML
    Button btnAceptar;
    
    @FXML
    Button btnCancelar;
    
    @FXML
    TextField txtPatente;
    
    @FXML
    TextField txtMarca;
    
    @FXML
    TextField txtModelo;
    
    @FXML
    TextField txtCantHoras;
    
    @FXML
    TextField txtPrecio;
    
    @FXML
    TextField txtDatoExtra;
    
    private Vehiculo vehiculo;
    private Vehiculo vehiculoAModificar;
    
    
    @FXML
    Label lblDatoExtra;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.CBTipo.getItems().addAll("AUTO", "CAMIONETA", "MOTO");
        this.CBTipo.setValue("AUTO");
    }    
    
    @FXML
    public void cambiandoTipo(){
        switch(CBTipo.getValue()){
            
            case "AUTO" -> lblDatoExtra.setText("CANTIDAD DE PUERTAS");
            
            case "MOTO" -> lblDatoExtra.setText("CILINDRADA");
            
            case "CAMIONETA" -> lblDatoExtra.setText("CAPACIDAD DE CARGA");
        
        }
        
    }
    
    @FXML
    public void aceptar(){
        String tipo = CBTipo.getValue();
        String patente = txtPatente.getText();
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        int horas = Integer.parseInt(txtCantHoras.getText());
        Double precio = Double.parseDouble((txtPrecio.getText()));
        int datoExtra = Integer.parseInt(txtDatoExtra.getText());
        
        if (vehiculoAModificar != null){
            
            vehiculoAModificar.setMarca(marca);
            vehiculoAModificar.setModelo(modelo);
            vehiculoAModificar.setCantHoras(horas);
            vehiculoAModificar.setPrecioHora(precio);
            
            switch(tipo){
            
            case "AUTO" -> ((Auto)vehiculoAModificar).setCantPuertas(datoExtra);
            
            case "MOTO" -> ((Moto)vehiculoAModificar).setcilindrada(datoExtra);
            
            case "CAMIONETA" -> ((Camioneta)vehiculoAModificar).setCantPuertas(datoExtra);
            
        }
            
            this.vehiculo = this.vehiculoAModificar;
        }
        
        else{
            System.out.println("LLegué");
            System.out.println(tipo);
            switch(tipo){
            
            case "AUTO" -> this.vehiculo = new Auto(datoExtra, patente, marca, modelo, horas, precio);
            
            case "MOTO" -> this.vehiculo = new Moto(datoExtra, patente, marca, modelo, horas, precio);
            
            case "CAMIONETA" -> this.vehiculo = new Camioneta(datoExtra, patente, marca, modelo, horas, precio);
            
        }
            System.out.println(this.vehiculo);
            
        }
        
        this.cerrar();
    }
    
    @FXML
    public void cancelar(){
        this.cerrar();
    }
    
    public Vehiculo getVehiculo(){
        
        return this.vehiculo;
    }
    
    private void cerrar(){
        Stage stage = (Stage)btnCancelar.getScene().getWindow();
        stage.close();
    }
}