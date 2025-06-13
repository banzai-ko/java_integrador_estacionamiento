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
public class Moto extends Vehiculo implements ICobrable {
    private int cilindrada;

    public Moto(int cilindrada, String patente, String marca, String modelo, int cantHoras, double precioHora) {
        this(patente, marca, modelo, cantHoras, precioHora);
        this.cilindrada = cilindrada;
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
        sb.append(super.toCSV()).append(this.calcularPrecio()).append("Moto");
        
        return sb.toString();
    }
}
