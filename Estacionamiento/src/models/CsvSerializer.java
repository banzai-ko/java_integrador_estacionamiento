/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import Interfaces.ISerializer;
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
public class CsvSerializer {
     
    public static <T extends Vehiculo & ISerializer> void serializeToCsv(
            ArrayList<Vehiculo> list, 
            String filePath
    ) throws IOException {
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Vehiculo item : list) {
                bw.write(item.toCSV());
                bw.newLine();
            }
        } catch(IOException e) {
            System.err.println("Error al escribir CSV" + e.getMessage());
        }
    
    }   
    
    public static ArrayList<Vehiculo> leerCSV(String ruta){
        // Leer el CSV
        ArrayList<Vehiculo> lista = new ArrayList<>(); 
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] columnas   = linea.split(",");
                Vehiculo instancia = null;
                String tipo = columnas[6];
                String patente = columnas[0];
                String marca = columnas[1];
                String modelo = columnas[2];
                int año = Integer.parseInt(columnas[3]);
                double valor = Double.parseDouble(columnas[4]);

                switch (tipo) {
                    case "Auto" -> {
                        int puertas = Integer.parseInt(columnas[7]);
                        instancia = new Auto(puertas, patente, marca, modelo, año, valor);
                    }
                    case "Camioneta" -> {
                        int capacidadCarga = Integer.parseInt(columnas[7]);
                        instancia = new Camioneta(capacidadCarga, patente, marca, modelo, año, valor);
                    }

                    case "Moto" -> {
                        int cilindrada = Integer.parseInt(columnas[7]);
                        instancia = new Moto(cilindrada, patente, marca, modelo, año, valor);
                    }
                    default -> 
                        throw new IllegalArgumentException("Tipo de vehículo desconocido: " + tipo);
                }
                    lista.add(instancia);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el CSV: " + e.getMessage());
        }
        return lista;
    }
}