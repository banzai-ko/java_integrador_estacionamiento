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
public class Camioneta extends Vehiculo implements ICobrable {
    private int capacidadCarga;

    public Camioneta(int capacidadCarga, String patente, String marca, String modelo, int cantHoras, double precioHora) {
        this(patente, marca, modelo, cantHoras, precioHora);
        this.capacidadCarga = capacidadCarga;
    }
    
        public Camioneta(String patente, String marca, String modelo, int cantHoras, double precioHora) {
        super(patente, marca, modelo, cantHoras, precioHora);
       
    }

     public int getCantPuertas() {
        return capacidadCarga;
    }

    public void setCantPuertas(int capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Camioneta: ").append(System.lineSeparator());
        sb.append(super.toString());
        sb.append("capacidadCarga=").append(capacidadCarga);

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
        return 0.30;
    }
    
        public String toCSV(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toCSV()).append(this.calcularPrecio()).append("Camioneta");
        
        return sb.toString();
    }
}