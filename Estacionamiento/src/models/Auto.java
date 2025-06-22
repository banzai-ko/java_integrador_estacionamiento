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
public class Auto extends Vehiculo implements ICobrable, ISerializableCsv{
    
    private int cantPuertas;

    public Auto(int cantidadPuertas, String patente, String marca, String modelo, int cantHoras, double precioHora) {
        this(patente, marca, modelo, cantHoras, precioHora);
        this.cantPuertas = cantidadPuertas;
    }
    
    public Auto(String patente, String marca, String modelo, int cantHoras, double precioHora) {
        super(patente, marca, modelo, cantHoras, precioHora);
    }
    
    public Auto() {
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
        sb.append(super.toString());
        sb.append("cantPuertas=").append(cantPuertas);

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
    
    @Override
    public Auto fromCSV(String line) {
        System.out.println(line);
        String[] result = line.split(",");
        String patente = result[0];
        String marca = result[1];
        String modelo = result[2];
        int cantHoras = Integer.parseInt(result[3]);
        double precioHora = Double.parseDouble(result[4]);
        String tipo = result[6];
        int cantPuertas = Integer.parseInt(result[7]);
        Auto auto = new Auto(cantPuertas, patente, marca, modelo, cantHoras, precioHora);
        
        return auto;
    }
}
