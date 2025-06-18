/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import Interfaces.ICobrable;

/**
 *
 * @author box
 */
public class Auto extends Vehiculo implements ICobrable {
    
    private int cantPuertas;

    public Auto(int cantidadPuertas, String patente, String marca, String modelo, int cantHoras, double precioHora) {
        this(patente, marca, modelo, cantHoras, precioHora);
        this.cantPuertas = cantidadPuertas;
    }
    
    public Auto(String patente, String marca, String modelo, int cantHoras, double precioHora) {
        super(patente, marca, modelo, cantHoras, precioHora);
    }
    
    public int getCantPuertas() {
        return cantPuertas;
    }

    public void setCantPuertas(int cantPuertas) {
        this.cantPuertas = cantPuertas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Auto: ").append(System.lineSeparator());
        sb.append(super.toString()).append(",");
        sb.append("cantPuertas= ");
        sb.append(this.cantPuertas);
        sb.append('}');
        

        return sb.toString();
    }
    
    @Override
    public double calcularPrecio() {
        
        double valor = this.precioHora * this.cantHoras;
        
        double valorIncrementado = valor + (valor * this.getIncremento());
        
        return valorIncrementado;
    }

    @Override
    public double getIncremento() {
        return 0.20;
    }
    
    @Override
    public String toCSV(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toCSV()).append(this.calcularPrecio()).append(",Auto");
        sb.append(",").append(this.cantPuertas);
        
        return sb.toString();
    }
    
    
     
    
}
