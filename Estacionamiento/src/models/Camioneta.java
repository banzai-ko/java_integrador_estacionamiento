/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import Interfaces.*;
/**
 *
 * @author box
 */
public class Camioneta extends Vehiculo implements ICobrable, ISerializableCsv {
    private int capacidadCarga;

    public Camioneta(int capacidadCarga, String patente, String marca, String modelo, int cantHoras, double precioHora) {
        this(patente, marca, modelo, cantHoras, precioHora);
        this.capacidadCarga = capacidadCarga;
    }
    
        public Camioneta(String patente, String marca, String modelo, int cantHoras, double precioHora) {
        super(patente, marca, modelo, cantHoras, precioHora);
       
    }
    public Camioneta() {
    }

     public int getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(int capacidadCarga) {
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
        sb.append(super.toCSV()).append(this.calcularPrecio()).append(",Camioneta");
        sb.append(",").append(this.capacidadCarga);
        
        return sb.toString();
    }
    
    @Override
    public Camioneta fromCSV(String line) {
        String[] result = line.split(",");
        String patente = result[0];
        String marca = result[1];
        String modelo = result[2];
        int cantHoras = Integer.parseInt(result[3]);
        double precioHora = Double.parseDouble(result[4]);
        String tipo = result[6];
        int capacidadCarga = Integer.parseInt(result[7]);
        Camioneta camioneta = new Camioneta(capacidadCarga, patente, marca, modelo, cantHoras, precioHora);
        
        return camioneta;
    }
        
}