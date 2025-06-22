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
public class Moto extends Vehiculo implements ICobrable, ISerializableCsv {
    private int cilindrada;

    public Moto(int cilindrada, String patente, String marca, String modelo, int cantHoras, double precioHora) {
        this(patente, marca, modelo, cantHoras, precioHora);
        this.cilindrada = cilindrada;
    }
    
    public Moto() { 
    }
    
    public Moto(String patente, String marca, String modelo, int cantHoras, double precioHora) {
        super(patente, marca, modelo, cantHoras, precioHora);
    }
    
    public int getcilindrada() {
        return cilindrada;
    }

    public void setcilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Moto: ").append(System.lineSeparator());
        sb.append(super.toString());
        sb.append("Cilindrada= ").append(cilindrada);

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
        
        return 0.10;
    } 
    
    @Override
    public String toCSV(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toCSV()).append(this.calcularPrecio()).append(",Moto");
        sb.append(",").append(this.cilindrada);
        
        return sb.toString();
    }
    
    @Override
    public Moto fromCSV(String line) {
        String[] result = line.split(",");
        String patente = result[0];
        String marca = result[1];
        String modelo = result[2];
        int cantHoras = Integer.parseInt(result[3]);
        double precioHora = Double.parseDouble(result[4]);
        String tipo = result[6];
        int cilindrada = Integer.parseInt(result[7]);
        Moto moto = new Moto(cilindrada, patente, marca, modelo, cantHoras, precioHora);
        
        return moto;
    }
}
