/*
 * Vuelo.java
 *
 * Created on 30 de noviembre de 2007, 17:02
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package agencia;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Clase vuelo
 * @author moix
 */
public class Vuelo implements Serializable{
    /**
     * Caracter delimitador
     */
    private final String delim = ":";
    private final String espacio = " ";
    /**
     * Avion utilizado para el vuelo
     */
    private Avion avion;
    /**
     * Aeropuerto de salida
     */
    private Aeropuerto aeropuertoSalida;
    /**
     * aeropuerto de llegada
     */
    private Aeropuerto aeropuertoLlegada;
    /**
     * fecha de salida del vuelo
     */
    private GregorianCalendar fechaVuelo;
    private int referenciaVuelo;
    /**
     * Lista con los pasajeros confirmados en clase turista
     */
    private List<Pasajero> pasajerosTurista;
    /**
     * Lista con los pasajeros confirmados en primera clase
     */
    private List<Pasajero> pasajerosPrimera;
    /**
     * Precio de una plaza en clase turista
     */
    private int precioTurista;
    /**
     * precio de una plaza en primera clase
     */
    private int precioPrimera;
    
    
    /**
     * Creates a new instance of Vuelo
     * @param avion 
     * @param aeropuertoSalida 
     * @param aeropuertoLlegada 
     * @param fechaVuelo 
     * @param referenciaVuelo 
     * @param precioTurista 
     * @param precioPrimera 
     */
    public Vuelo(Avion avion, Aeropuerto aeropuertoSalida, Aeropuerto aeropuertoLlegada, GregorianCalendar fechaVuelo, int referenciaVuelo, int precioTurista, int precioPrimera) {
        this.avion = avion;
        this.aeropuertoSalida = aeropuertoSalida;
        this.aeropuertoLlegada = aeropuertoLlegada;
        this.fechaVuelo = fechaVuelo;
        this.referenciaVuelo = referenciaVuelo;
        pasajerosTurista = new Vector<Pasajero>(avion.getPlazasTurista());
        pasajerosPrimera = new Vector<Pasajero>(avion.getPlazasPrimera());
        this.precioTurista=precioTurista;
        this.precioPrimera=precioPrimera;
    }
    
    public Vuelo(){
        this.avion = null;
        this.aeropuertoSalida = null;
        this.aeropuertoLlegada = null;
        this.fechaVuelo = null;
        this.referenciaVuelo = 0;
        pasajerosTurista = null;
        pasajerosPrimera = null;
        this.precioTurista = 0;
        this.precioPrimera = 0;
    }
    
    public void setAvion(Avion avion){
        this.avion = avion;
        pasajerosTurista = new Vector<Pasajero>(avion.getPlazasTurista());
        pasajerosPrimera = new Vector<Pasajero>(avion.getPlazasPrimera());
    }
    
    public Avion getAvion(){
        return avion;
    }
    
    public void setAeropuertoSalida(Aeropuerto aeropuertoSalida){
        this.aeropuertoSalida = aeropuertoSalida;
    }
    
    public Aeropuerto getAeropuertoSalida(){
        return aeropuertoSalida;
    }
    
    public void setAeropuertoLlegada(Aeropuerto aeropuertoLlegada){
        this.aeropuertoLlegada = aeropuertoLlegada;
    }
    
    public Aeropuerto getAeropuertoLlegada(){
        return aeropuertoLlegada;
    }
    
    public void setFechaVuelo(GregorianCalendar fechaVuelo){
        this.fechaVuelo = fechaVuelo;
    }
    
    public GregorianCalendar getFechaVuelo(){
        return fechaVuelo;
    }
    
    public void setReferenciaVuelo(int referenciaVuelo){
        this.referenciaVuelo = referenciaVuelo;
    }
    
    public int getReferenciaVuelo(){
        return referenciaVuelo;
    }
    
    public List<Pasajero> getPasajerosTurista(){
        return this.pasajerosTurista;
    }
    
    public List<Pasajero> getPasajerosPrimera(){
        return this.pasajerosPrimera;
    }
    public int getPrecioPrimera(){
        return this.precioPrimera;
    }
    public int getPrecioTurista(){
        return this.precioTurista;
    }
    
    //insertar nuevo pasajero, devuelve true si se he podido insertar
    public boolean insertarPasajeroTurista(Pasajero nuevoPasajero){
        if (!this.pasajerosTurista.contains((Object)nuevoPasajero) && !this.pasajerosPrimera.contains((Object)nuevoPasajero) && (this.pasajerosTurista.size() < this.avion.getPlazasTurista())){
            pasajerosTurista.add(nuevoPasajero);
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean insertarPasajeroPrimera(Pasajero nuevoPasajero){
        if (!this.pasajerosPrimera.contains((Object)nuevoPasajero) && !this.pasajerosTurista.contains((Object)nuevoPasajero) && (this.pasajerosPrimera.size() < this.avion.getPlazasPrimera())){
            pasajerosPrimera.add(nuevoPasajero);
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Busca un pasajero a partir de su DNI en clase turista
     * @param dni DNI del pasajero que quiere buscar
     * @return True si ha encontrado al pasajero, false si no lo ha encontrado
     */
    public Pasajero buscarPasajeroTurista(int dni){
        Pasajero pasajero = null;
        boolean encontrado = false;
        for (Iterator i = pasajerosTurista.iterator() ; !encontrado && i.hasNext() ;){
            pasajero = (Pasajero) i.next();
            if (pasajero.getDni() == dni ){
                encontrado = true;
            }
            else{
                pasajero = null;
            }
        }
        
        return pasajero;
    }
    
    /**
     * Busca un pasajero a partir de su DNI en primera clase
     * @param dni DNI del pasajero que quiere buscar
     * @return True si lo ha encontrado, False si no lo ha encontrado
     */
    public Pasajero buscarPasajeroPrimera(int dni){
        Pasajero pasajero = null;
        boolean encontrado = false;
        for (Iterator i = pasajerosPrimera.iterator() ; !encontrado && i.hasNext() ;){
            pasajero = (Pasajero) i.next();
            if (pasajero.getDni() == dni ){
                encontrado = true;
            }
            else{
                pasajero = null;
            }
        }
        return pasajero;
    }
    
    public boolean eliminarPasajeroTurista(Pasajero eliminaPasajero){
        if (this.pasajerosTurista.contains(eliminaPasajero)){
            pasajerosTurista.remove(eliminaPasajero);
            return true;
        }
        else{
            return false;
        }
    }
    public boolean eliminarPasajeroPrimera(Pasajero eliminaPasajero){
        if (this.pasajerosPrimera.contains(eliminaPasajero)){
            pasajerosPrimera.remove(eliminaPasajero);
            return true;
        }
        else{
            return false;
        }
    }    
    
    public boolean eliminarPasajero(int dni){
        Pasajero pasajero=null;
        pasajero = this.buscarPasajeroTurista(dni);
        if(pasajero==null){
            pasajero = this.buscarPasajeroPrimera(dni);
            if(pasajero!=null){
                this.eliminarPasajeroPrimera(pasajero);
                return true;
            }
        }
        else{
            this.eliminarPasajeroTurista(pasajero);
            return true;
        }
        return false;
    }
    /**
     * Devuelve una cadena de caracteres con informacion sobre el vuelo
     * @return cadena imprimible String con la informacion del objeto
     */
    @Override
    public String toString(){
        
        StringBuffer vuelo = (new StringBuffer().append(referenciaVuelo));
        vuelo.append("                   ").append(pasajerosTurista.size());
        vuelo.append(" / ").append(pasajerosPrimera.size());
        vuelo.append("              ").append(fechaVuelo.getTime().toLocaleString());
        vuelo.append("     ").append(aeropuertoSalida.toString()).append(" / ");
        vuelo.append(aeropuertoLlegada.toString());
        return vuelo.toString();
    }
    
    
    @Override
    public boolean equals(Object vuelo2){
        return this.referenciaVuelo == ((Vuelo)vuelo2).getReferenciaVuelo();
    }

}



