/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author box
 */
public class CSVUtilsGeneric<T extends Vehiculo> {
    
    public void escribirCSV(ArrayList<T> lista, String encabezado)
    {
        String archivoCsv = "datos.csv";

        // Escribir encabezado + datos
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCsv))) {
            bw.write(encabezado);
            
            for (T item : lista) {
                bw.write(item.toCSV());
                bw.newLine();
            }
            
        } catch (IOException e) {
            System.err.println("Error al escribir el CSV: " + e.getMessage());
        }
    }

    public ArrayList<Vehiculo> leerCSV(String ruta){
        // Leer el CSV
        ArrayList<Vehiculo> lista = new ArrayList<>(); 
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] columnas = linea.split(",");
                Vehiculo instancia = null;
                switch (columnas[6]){
                    
                    case "Auto" -> instancia = new Auto(columnas[0],columnas[1],columnas[2], Integer.parseInt(columnas[3]), Double.parseDouble(columnas[4]));
                            
                    case "Camioneta" -> instancia = new Camioneta(columnas[0],columnas[1],columnas[2], Integer.parseInt(columnas[3]), Double.parseDouble(columnas[4]));
       
                    case "Moto" -> instancia = new Moto(columnas[0],columnas[1],columnas[2], Integer.parseInt(columnas[3]), Double.parseDouble(columnas[4]));
                }
                lista.add(instancia);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el CSV: " + e.getMessage());
        }
        return lista;
    }
    
}
