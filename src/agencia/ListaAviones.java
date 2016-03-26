/*
 * ListaAviones.java
 *
 * Created on 5 de diciembre de 2007, 21:25
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package agencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Clase que almacena todos los aviones para facilitar su busqueda y almacenamiento
 * @author moix
 */
public class ListaAviones {
    
    /**
     * Lista donde se almacenaran los objetos Avion
     */
    private List<Avion> aviones = new Vector<Avion>(15,5);
    /**
     * Fichero donde se guardara la lista de aviones
     */
    private File ficheroAviones;
    
    /**
     * Crea un nuevo objeto de ListaAviones
     * @param ficheroAviones Fichero donde se almacenara la lista de aviones
     */
    public ListaAviones(String ficheroAviones) {
        this.ficheroAviones = new File(ficheroAviones);
        if (this.ficheroAviones.exists()){
            leeAviones();
        } else {
            try {
                this.ficheroAviones.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    /**
     * Lee el ficheroAviones y guarda en la lista los aviones encontrados
     */
    private void leeAviones(){
        try {
            BufferedReader lector = new BufferedReader(new FileReader(ficheroAviones));
            String lineaAvion = lector.readLine();
            while(lineaAvion != null){
                Avion avionInsertar = new Avion(lineaAvion);
                if(!aviones.contains(avionInsertar)){
                    aviones.add(avionInsertar);
                    lineaAvion = lector.readLine();
                }
                else{
                    lineaAvion = lector.readLine();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    //actualiza el fichero con los aviones --> borra y crea
    /**
     * Escribe el ficheroAviones con los aviones que hay en la lista
     */
    public void escribirAviones(){
        try {
            if(ficheroAviones.exists()) ficheroAviones.delete();
            BufferedWriter escritor = new BufferedWriter(new FileWriter(ficheroAviones));
            for (Iterator i = aviones.iterator() ; i.hasNext() ; ){
                escritor.write(((Avion)i.next()).toFile());
                escritor.newLine();
            }
            escritor.flush();
            escritor.close();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    //inserta un avion nuevo en la lista si no esta insertado ya
    /**
     * Agrega un avion a la lista
     * @param avion Avion que desea insertar
     */
    public void insertarAvion(Avion avion){
        if(!aviones.contains(avion))
            aviones.add(avion);
    }
    
    /**
     * Elimina un avion de la lista
     * @param avion Avion que desea eliminar
     */
    public void eliminarAvion(Avion avion){
        if(aviones.contains(avion))
            aviones.remove(avion);
    }
    
    /**
     * Devuelve un avion
     * @param numeroPosicion Posicion en la que esta almacenado el avion
     * @return Avion seleccionado
     */
    public Avion seleccionarAvion(int numeroPosicion){
        
        if(numeroPosicion < aviones.size()){
            return (aviones.get(numeroPosicion));
        }
        else{
            return null;
        }
        
    }
    
    /**
     * Devuelve una cadena de caracteres con la lista de aviones
     * @return Cada linea tendra formato Avion numero i + informacion sobre el avion
     */
    public String toString(){
        StringBuffer texto = new StringBuffer();
        int contador = 0;
        for (Iterator i = aviones.iterator() ; i.hasNext() ; ){
            texto.append("Avion numero "+contador+++" : ").append(i.next().toString()).append('\n');
        }
        return texto.toString();
    }
    
      public List<Avion> getAviones(){
        return this.aviones;
    }
}
