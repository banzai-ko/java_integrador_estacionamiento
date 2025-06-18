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
       System.out.println("Vehículo Agregado");
   }
           
           

    
public void modificarVehiculo(Vehiculo vehiculoActual, Vehiculo vehiculoModificado) {
        int index = this.vehiculos.indexOf(vehiculoActual);
        if (index == -1) {
            //throw new VehiculoNoEncontradoException("El vehículo a modificar no se encuentra.");
            System.out.println("VEHICULO NOT FOUND");
        }

        for (Vehiculo v : vehiculos) {
            if (!v.equals(vehiculoActual) && v.equals(vehiculoModificado)) {
                throw new VehiculoRepetidoException("Ya existe un vehículo igual al modificado.");
            }
        }

        this.vehiculos.set(index, vehiculoModificado);
        System.out.println("Vehículo modificado.");
    }        
        
public  void eliminarVehiculo(Vehiculo vehiculo){
    
    this.vehiculos.remove(vehiculo);
    
}
    
//public Vehiculo getVehiculo(Vehiculo comparable){
//   Vehiculo retorno = comparable;
//    for(Vehiculo vehiculo : this.vehiculos){
//        if(vehiculo.equals(comparable)){
//            
//            retorno = vehiculo;
//        }
//        
//    }
//    
//    return retorno;
//}   

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }
}
