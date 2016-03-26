/*
 * Avion.java
 *
 * Created on 28 de noviembre de 2007, 2:49
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package agencia;

import java.io.Serializable;
import java.util.StringTokenizer;

/**
 * Clase Avion
 * @author moix
 */
public class Avion implements Serializable{
    
    
    /**
     * Modelo del avion
     */
    public String modelo;
    /**
     * Empresa propietaria del avion
     */
    public String compania;
    /**
     * Plazas en clase turista
     */
    public int plazasTurista;
    /**
     * Plazas en primera clase
     */
    public int plazasPrimera;
    
    /**
     * Caracter espacio
     * Por defecto \n\t
     */
    private final String espacio = "    ";
    /**
     * Caracter delimitador de avion
     * Por defecto :
     */
    private final String delim = ":";
    
    
    /**
     * Crea un nuevo objeto Avion a partir de sus atributos
     * @param modelo Modelo del avion
     * @param compania Empresa
     * @param plazasTurista Plazas en clase turista
     * @param plazasPrimera Plazas en primera clase
     */
    public Avion(String modelo, String compania, int plazasTurista, int plazasPrimera) {
        this.modelo = modelo;
        this.compania = compania;
        this.plazasTurista = plazasTurista;
        this.plazasPrimera = plazasPrimera;
    }
    
    /**
     * Crea un nuevo objeto Avion a partir de una linea con formato 
     * modelo:compania:plazasTurista:plazasPrimera\n\t
     * @param nuevoAvion Cadena de caracteres con formato modelo:compania:plazasTurista:plazasPrimera\n\t
     */
    public Avion(String nuevoAvion){
        StringTokenizer separador = new StringTokenizer(nuevoAvion,delim);
        try{
            this.modelo = separador.nextToken();
            this.compania = separador.nextToken();
            this.plazasTurista = Integer.parseInt(separador.nextToken());
            this.plazasPrimera = Integer.parseInt(separador.nextToken());
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    /**
     * Devuelve el modelo del avion
     * @return Modelo del avion
     */
    public String getModelo(){
        return modelo;
    }
    
    /**
     * Modifica el modelo del avion
     * @param modelo Nuevo modelo del avion
     */
    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    
    /**
     * Devuelve la empresa propietaria
     * @return Empresa
     */
    public String getCompania(){
        return compania;
    }
    
    /**
     * Modifica la empresa
     * @param compania Nueva empresa
     */
    public void setCompania(String compania){
        this.compania = compania;
    }
    
    /**
     * Devuelve el numero de plazas en clase turista
     * @return Plazas en clase turista
     */
    public int getPlazasTurista(){
        return plazasTurista;
    }
        
    /**
     * Modifica el numero de plazas en clase turista
     * @param plazasturista Nuevas plazas turista
     */
    public void setPlazasTurista(int plazasturista){
        this.plazasTurista = plazasturista;
    }

    /**
     * Devuelve el numero de plazas en primera clase
     * @return Numero de plazas en primera clase
     */
    public int getPlazasPrimera(){
        return plazasPrimera;
    }
        
    /**
     * Modifica el numero de plazas en primera clase
     * @param plazasprimera Nuevo numero de plazas en primera clase
     */
    public void setPlazasPrimera(int plazasprimera){
        this.plazasTurista = plazasprimera;
    }
    
    /**
     * Devuelve una cadena de caracteres con informacion y los atributos del avion
     * @return Informacion sobre el avion
     */
    public String toString(){
        StringBuffer avion = (new StringBuffer("Compañía: ")).append(compania).append(espacio);
        avion.append("Plazas turista/primera: ").append(plazasTurista).append("/").append(plazasPrimera).append(espacio);
        avion.append("Modelo: ").append(modelo);
        
        
        return avion.toString();
    }
    public String aCadena(){
        StringBuffer avion = (new StringBuffer(compania));
        avion.append("  ").append(plazasTurista);
        avion.append("  ").append(plazasPrimera);
        //avion.append("\t").append(modelo);        
        return avion.toString();
    }
    /**
     * Devuelve una cadena de caracteres con formato 
     * modelo:compania:plazasTurista:plazasPrimera
     * @return Cadena de caracteres con formato modelo:compania:plazasTurista:plazasPrimera
     */
    public String toFile(){
        StringBuffer avion = (new StringBuffer(modelo)).append(delim).append(compania).append(delim).append(plazasTurista).append(delim).append(plazasPrimera);
        return avion.toString();
    }
    
    
}




