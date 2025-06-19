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
        
        if (vehiculo != null) {
            
            vehiculo.setMarca(marca);
            vehiculo.setModelo(modelo);
            vehiculo.setCantHoras(horas);
            vehiculo.setPrecioHora(precio);
            
            switch(tipo){
                case "AUTO" -> ((Auto)vehiculo).setCantPuertas(datoExtra);

                case "MOTO" -> ((Moto)vehiculo).setcilindrada(datoExtra);

                case "CAMIONETA" -> ((Camioneta)vehiculo).setCapacidadCarga(datoExtra);
            }
        }
        
        else{
            
            switch(tipo){
            
                case "AUTO" -> this.vehiculo = new Auto(datoExtra, patente, marca, modelo, horas, precio);
            
                case "MOTO" -> this.vehiculo = new Moto(datoExtra, patente, marca, modelo, horas, precio);
            
                case "CAMIONETA" -> this.vehiculo = new Camioneta(datoExtra, patente, marca, modelo, horas, precio);                        
            
            }
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
    
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo; 
        if(vehiculo != null){
            this.txtPatente.setDisable(true);
            this.txtMarca.setText(vehiculo.getMarca());
            this.txtModelo.setText(vehiculo.getModelo());
            this.txtCantHoras.setText(String.valueOf(vehiculo.getCantHoras()));
            this.txtPrecio.setText(String.valueOf(vehiculo.getPrecioHora()));
            
            if( vehiculo instanceof Auto auto) {
                this.txtDatoExtra.setText(String.valueOf(auto.getCantPuertas()));
                CBTipo.setValue("AUTO");
            }
            if (vehiculo instanceof Camioneta camioneta) {
                this.txtDatoExtra.setText(String.valueOf(camioneta.getCapacidadCarga()));
                CBTipo.setValue("CAMIONETA");
            }
            if (vehiculo instanceof Moto moto){
                this.txtDatoExtra.setText(String.valueOf(moto.getcilindrada()));
                CBTipo.setValue("MOTO");
            }
        }
    }
    
    private void cerrar(){
        Stage stage = (Stage)btnCancelar.getScene().getWindow();
        stage.close();
    }
}