/*
 * Pasajero.java
 *
 * Created on 30 de noviembre de 2007, 22:53
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package agencia;

import java.io.Serializable;

    
/**
 * Clase que implementa un pasajero
 * @author moix
 */
enum FormaPago{
    Metalico, Tarjeta, Cheque;
}
public class Pasajero implements Serializable {
    
    /**
     * Nombre completo del pasajero
     */
    private String nombre;
    /**
     * Documento Nacional de Identidad (sin letra)
     */
    private int dni;    
    
    /**
     * Caracter espacio
     * Por defecto \t
     */
    private FormaPago formaPago;
    private final String espacio="\t";
    
    /**
     * Creates a new instance of Pasajero
     * @param nombre Nombre
     * @param dni DNI
     */
    public Pasajero(String nombre, int dni, FormaPago formaPago) {
        this.nombre = nombre;
        this.dni = dni;
        this.formaPago = formaPago;
    }
    public Pasajero(String nombre, int dni) {
        this.nombre = nombre;
        this.dni = dni;
        this.formaPago = FormaPago.Metalico;
    }
    
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void setDni(int dni){
        this.dni = dni;
    }
    
    public int getDni(){
        return this.dni;
    }
    
    @Override
    public String toString(){
        StringBuffer pasajero = (new StringBuffer("Nombre: ")).append(nombre).append(espacio).append("  dni: ").append(dni).append("\n");
        return pasajero.toString();
    }
    
    @Override
    public boolean equals(Object O){
        return ( dni == ((Pasajero)O).getDni() );
    }
    
}
