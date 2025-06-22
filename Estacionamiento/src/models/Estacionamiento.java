/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.ArrayList;
import Excepciones.*;

/**
 *
 * @author box
 */
public class Estacionamiento {
    private ArrayList<Vehiculo> vehiculos;
    private int cantidadMaxLugares;

    public Estacionamiento(int cantidadMaxLugares) {
        this.cantidadMaxLugares = cantidadMaxLugares;
        this.vehiculos = new ArrayList<>();
    }

    public Estacionamiento() {
        this(5);
    }
    
    public void agregarVehiculo(Vehiculo vehiculo){
       if (this.vehiculos.contains(vehiculo)){
           throw new VehiculoRepetidoException("El vehiculo ya se encuentra en el estacionamiento!");
       }
       if (this.vehiculos.size() == this.cantidadMaxLugares){
           throw new CantidadSuperadaException("El estacionamiento ya se encuentra lleno!");
       }
       this.vehiculos.add(vehiculo);
       System.out.println("Vehículo Agregado >>>" + vehiculo.toString());
   }
        
    public  void eliminarVehiculo(Vehiculo vehiculo){

        this.vehiculos.remove(vehiculo);

    }
    
    public ArrayList<Vehiculo> getVehiculos() {
        
        return vehiculos;
    }
}
