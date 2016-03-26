/*
 * ListaAeropuertos.java
 *
 * Created on 28 de noviembre de 2007, 0:48
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package agencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Clase que almacena todos los aeropuertos para facilitar busqueda y almacenamiento
 * @author moix
 */
public class ListaAeropuertos {
    
    /**
     * Coleccion List, implementada como Vector con todos los aeropuertos
     */
    private List<Aeropuerto> aeropuertos = new Vector<Aeropuerto>(34,1);
    /**
     * Fichero donde se almacenara la lista de aeropuertos
     */
    private File ficheroAeropuertos;
    
    /**
     * Crea una nueva ListaAeropuertos
     * @param ficheroAeropuertos Fichero en el que se guardara la lista de aeropuertos
     */
    //crear listaaeropuertos a partir del fichero
    public ListaAeropuertos(String ficheroAeropuertos) {
        this.ficheroAeropuertos = new File(ficheroAeropuertos);
        if (this.ficheroAeropuertos.exists()){
            leeAeropuertos();
        } else {
            try {
                this.ficheroAeropuertos.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    /**
     * Lee el ficheroAeropuertos y agrega los aeropuertos a ListaAeropuertos
     */
    private void leeAeropuertos(){
        try {
            BufferedReader lector = new BufferedReader(new FileReader(ficheroAeropuertos));
            String lineaAeropuerto = lector.readLine();
            while(lineaAeropuerto != null){
                Aeropuerto aeropuertoInsertar = new Aeropuerto(lineaAeropuerto);
                if(!aeropuertos.contains(aeropuertoInsertar)){
                    aeropuertos.add(aeropuertoInsertar);
                    lineaAeropuerto = lector.readLine();
                }
                else{
                    lineaAeropuerto = lector.readLine();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //inserta un aeropuerto nuevo en la lista si no esta insertado ya
    /**
     * Agrega un nuevo aeropuerto
     * @param aeropuerto Nuevo aeropuerto a insertar
     */
    public void insertarAeropuerto(Aeropuerto aeropuerto){
        if(!aeropuertos.contains(aeropuerto))
            aeropuertos.add(aeropuerto);
    }
    
    /**
     * Elimina un aeropuerto
     * @param aeropuerto Aeropuerto a eliminar
     */
    public void eliminarAeropuerto(Aeropuerto aeropuerto){
        if(aeropuertos.contains(aeropuerto))
            aeropuertos.remove(aeropuerto);
    }
    
    //actualiza el fichero con los aeropuertos --> borra y crea
    /**
     * Escribe la lista de aeropuertos en el fich
     */
    public void escribirAeropuertos(){
        try {
            if(ficheroAeropuertos.exists()) ficheroAeropuertos.delete();
            BufferedWriter escritor = new BufferedWriter(new FileWriter(ficheroAeropuertos));
            for (Iterator i = aeropuertos.iterator() ; i.hasNext() ; ){
                escritor.write(((Aeropuerto)i.next()).toStringGuardar());
                escritor.newLine();
            }
            escritor.flush();
            escritor.close();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    /**
     * Devuelve el numero total de aeropuertos
     * @return numero de aeropuertos
     */
    public int numeroAeropuertos(){
        return aeropuertos.size();
    }
    
    /**
     * Selecciona un aeropuerto a partir del nombre de ciudad
     * @param ciudadAeropuerto Nombre de la ciudad de la que deseamos seleccionar un aeropuerto
     * @return Aeropuerto seleccionado
     */
    public Aeropuerto seleccionarAeropuerto(String ciudadAeropuerto){
        Aeropuerto devolver = null;
        boolean encontrado = false;
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        
        for (Iterator i = aeropuertos.iterator() ; !encontrado ;){
            devolver = (Aeropuerto)i.next();
            if(devolver.getCiudad().equalsIgnoreCase(ciudadAeropuerto)){
                System.out.println(""+devolver.toString()+"? (Si/No)");     //preguntamos si el aeropuerto encontrado es el que quiere ya que hay algunas ciudades que tienen mas de un aeropuerto
                try {
                    if (entrada.readLine().equalsIgnoreCase("si")){
                        encontrado = true;
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            else{
                encontrado = !(i.hasNext());         //no se ha encontrado aeropuerto en la ciudad indicada
                if (encontrado == true){
                    devolver = null;
                }
            }
        }
        return devolver;
        
    }
    
    public List<Aeropuerto> getAeropuertos(){
        return this.aeropuertos;
    }
    
       
    /**
     * Devuelve una cadena de caracteres con todos los aeropuertos
     * @return Cadena de caracteres con todos los aeropuertos de la ListaAeropuertos
     */
    public String toString(){
        StringBuffer texto = new StringBuffer();
        for (Iterator i = aeropuertos.iterator() ; i.hasNext() ; ){
                texto.append(i.next().toString()).append('\n');
            }
        return texto.toString();
    }
     /*public <List>Aeropuerto listaeropuertos(){
         return this.aeropuertos;
     
    }*/
}