/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agencia;

import java.util.GregorianCalendar;

/**
 *
 * @author Usuario
 */
public class Modelo {

    public ListaAeropuertos aeropuertos = new ListaAeropuertos("aeropuertos.txt");
    public ListaVuelos vuelos = new ListaVuelos("listaVuelos.out");
    public ListaAviones aviones = new ListaAviones("aviones.txt");
    public GregorianCalendar fecha = new GregorianCalendar();
    public Vuelo vuelo = null;
    public Pasajero pasajero = null;
    public Password password = new Password("ficheroPassword.txt");
    
    
    
}


