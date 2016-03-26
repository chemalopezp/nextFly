/*
 * ListaVuelos.java
 *
 * Created on 1 de diciembre de 2007, 18:00
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package agencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Clase que contiene la lista de vuelos
 * @author moix
 */
public class ListaVuelos{
    
    /**
     * Lista con todos los vuelos programados
     */
    private List<Vuelo> vuelos = new Vector<Vuelo>(0,1);
    /**
     * Nombre del fichero donde se almacenaran los vuelos
     */
    private String ficheroVuelos;
    
    /**
     * Crea un objeto ListaVuelos a partir del nombre del fichero
     * @param ficheroVuelos Nombre del fichero donde se guardara la lista de vuelos
     */
    public ListaVuelos(String ficheroVuelos){
        this.ficheroVuelos = ficheroVuelos;
        leerVuelos();
    }
    public ListaVuelos(){
        this.ficheroVuelos = null;
        this.vuelos = null;
    }
    
    
    /**
     * Lee el fichero ficheroVuelos e inserta los vuelos encontrados a la lista
     */
    private void leerVuelos(){
        try {
            File fichero = new File(ficheroVuelos);
            if (fichero.exists()){
                FileInputStream fis = new FileInputStream(ficheroVuelos);
                if (fis.available() > 0){ //para comprobar que el fichero no esta vacio
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    this.vuelos = ((List<Vuelo>) ois.readObject());
                    ois.close();
                    fis.close();
                }
            }
            else{
                fichero.createNewFile();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }
    
    
    public List<Vuelo> getVuelos(){
        return this.vuelos;
    }
    //inserta un vuelo nuevo en la lista si no esta insertado ya
    /**
     * Agrega un vuelo a la lista
     * @param vuelo Vuelo que desea agregar
     */
    public void insertarVuelo(Vuelo vuelo){
        if(!vuelos.contains(vuelo))
            vuelos.add(vuelo);
    }
    
    public void eliminarVuelo2(Vuelo vuelo){
        if(vuelos.contains(vuelo))
            vuelos.remove(vuelo);
    }
    
    
    /**
     * Elimina un vuelo de la lista
     * @param numeroReferencia Numero de ref del vuelo que desea eliminar
     */
    public void eliminarVuelo(int numeroReferencia){
        Vuelo vuelo = null;
        boolean encontrado = false;
        for (Iterator i = vuelos.iterator() ; !encontrado && i.hasNext() ;){//si no ponemos las dos condiciones al borrar el último daria error
            vuelo = (Vuelo) i.next();
            if (vuelo.getReferenciaVuelo() == numeroReferencia ){
                vuelos.remove(vuelo);
                encontrado = true;
            }
        }
    }
    
    /**
     * Busca un vuelo segun su num ref
     * @param numeroReferencia Numero de referencia del vuelo buscado
     * @return Objeto vuelo buscado
     */
    public Vuelo buscarVuelo(int numeroReferencia){
        Vuelo vuelo = null;
        boolean encontrado = false;
        for (Iterator i = vuelos.iterator() ; !encontrado && i.hasNext() ;){
            vuelo = (Vuelo) i.next();
            if (vuelo.getReferenciaVuelo() == numeroReferencia ){
                encontrado = true;
            }
            else{
                vuelo = null;
            }
        }
        return vuelo;
    }
    
    //busca los vuelos con un aeropuerto de salida
    /**
     * Imprime por pantalla los aeropuertos de destino a partir de un aeropuerto de salida
     * @param salida Aeropuerto de salida
     * @return True si ha encontrado algun vuelo desde el aeropuerto de salida, False si no ha encontrado ninguno
     */
    public boolean buscarVuelos(Aeropuerto salida){
        Vuelo vuelo;
        boolean devolver = false;
        
        for (Iterator i = vuelos.iterator() ; i.hasNext() ; ){
            vuelo = ((Vuelo) i.next());
            if (salida.compareTo(vuelo.getAeropuertoSalida()) == 0){
                System.out.println(vuelo.getAeropuertoLlegada().toString());
                devolver = true;
            }
            else{
                vuelo = null;
            }
        }
        return devolver;
    }
    public ListaAeropuertos buscarAeropuertosLleg(Aeropuerto salida){
        ListaAeropuertos aeropuertosLleg = new ListaAeropuertos("llegada.txt");
        Vuelo vuelo;
        for (Iterator i = vuelos.iterator() ; i.hasNext() ; ){
            vuelo = ((Vuelo) i.next());
            if (salida.compareTo(vuelo.getAeropuertoSalida()) == 0){
                aeropuertosLleg.insertarAeropuerto(vuelo.getAeropuertoLlegada());
            }
            else{
                vuelo = null;
            }
        }
        return aeropuertosLleg;
    }
    
    //buscamos vuelos con aeropuerto de salida y de llegada
    /**
     * Verifica si hay vuelos entre dos aeropuertos
     * @param aeropuertoSalida Aeropuerto desde el que se quiere partir
     * @param aeropuertoLlegada Aeropuerto al que se quiere llegar
     * @return True si hay vuelos que unan los dos aeropuertos, False si no hay
     */
    public boolean buscarVuelos(Aeropuerto aeropuertoSalida, Aeropuerto aeropuertoLlegada){
        Vuelo vuelo;
        boolean devolver = false;
        
        for (Iterator i = vuelos.iterator() ; i.hasNext() ; ){
            vuelo = ((Vuelo) i.next());
            if (  (aeropuertoSalida.compareTo(vuelo.getAeropuertoSalida()) == 0) 
               && (aeropuertoLlegada.compareTo(vuelo.getAeropuertoLlegada()) == 0)){
                System.out.println(vuelo.getAeropuertoLlegada().toString());
                devolver = true;
            }
        }
        return devolver;
    }
    
    /**
     * Imprime los vuelos que unen dos aeropuertos en una fecha flexible
     * @param aeropuertoSalida Aeropuerto de Salida
     * @param aeropuertoLlegada Aeropuerto de llegada
     * @param fecha Fecha aproximada en la que se quiere viajar
     * @param diasflex Dias hacia adelante y hacia atras en los que se buscaran vuelos
     * @return True si ha encontrado vuelos, False si no ha encontrado
     */
    public ListaVuelos buscarVuelos2(Aeropuerto aeropuertoSalida, Aeropuerto aeropuertoLlegada, GregorianCalendar fecha, int diasflex){
        ListaVuelos vuelosEncontrados = new ListaVuelos("aux.out");
        Vuelo vuelo;
        GregorianCalendar fechaArriba,fechaAbajo;
        fechaArriba=(GregorianCalendar) fecha.clone();
        fechaAbajo=(GregorianCalendar) fecha.clone();
        fechaArriba.add(GregorianCalendar.DAY_OF_MONTH,diasflex);
        fechaAbajo.add(GregorianCalendar.DAY_OF_MONTH,-diasflex);
        for (Iterator i = vuelos.iterator() ; i.hasNext() ; ){
            vuelo = ((Vuelo) i.next());
            if ((aeropuertoSalida.compareTo(vuelo.getAeropuertoSalida())   == 0)    //compara aeropuerto de salida
            && (aeropuertoLlegada.compareTo(vuelo.getAeropuertoLlegada()) == 0)    //compara aeropuerto de llegada
            && ((vuelo.getFechaVuelo().compareTo(fechaArriba) <=0 )  && (vuelo.getFechaVuelo().compareTo(fechaAbajo) >=0))){
                vuelosEncontrados.insertarVuelo(vuelo);
            }
        }
        return vuelosEncontrados;
    }

    public boolean buscarVuelos(Aeropuerto aeropuertoSalida, Aeropuerto aeropuertoLlegada, GregorianCalendar fecha, int diasflex){
        Vuelo vuelo;
        boolean devolver = false;
        GregorianCalendar fechaArriba,fechaAbajo;
        fechaArriba=(GregorianCalendar) fecha.clone();
        fechaAbajo=(GregorianCalendar) fecha.clone();
        fechaArriba.add(GregorianCalendar.DAY_OF_MONTH,diasflex);
        fechaAbajo.add(GregorianCalendar.DAY_OF_MONTH,-diasflex);
        //System.out.println(fechaArriba.getTime().toString());
        //System.out.println(fechaAbajo.getTime().toString());
        for (Iterator i = vuelos.iterator() ; i.hasNext() ; ){
            vuelo = ((Vuelo) i.next());
            if ((aeropuertoSalida.compareTo(vuelo.getAeropuertoSalida())   == 0)    //compara aeropuerto de salida
            && (aeropuertoLlegada.compareTo(vuelo.getAeropuertoLlegada()) == 0)    //compara aeropuerto de llegada
            && ((vuelo.getFechaVuelo().compareTo(fechaArriba) <=0 )  && (vuelo.getFechaVuelo().compareTo(fechaAbajo) >=0))){
                System.out.println(vuelo.toString());
                devolver = true;
            }
        }
        return devolver;
    }
    /**
     * Busca vuelos entre dos aeropuertos en el mismo dia que la fecha dada
     * @param aeropuertoSalida Aeropuerto de salida
     * @param aeropuertoLlegada Aeropueto de llegada
     * @param fecha Fecha exacta en la que se quiere viajar
     * @deprecated Sustituido por el anterior, que busca con flexibilidad de dias
     */
    public void buscarVuelos(Aeropuerto aeropuertoSalida, Aeropuerto aeropuertoLlegada, GregorianCalendar fecha){
        Vuelo vuelo;
        for (Iterator i = vuelos.iterator() ; i.hasNext() ; ){
            vuelo = ((Vuelo) i.next());
                if (   aeropuertoSalida.compareTo(vuelo.getAeropuertoSalida())   == 0    //compara aeropuerto de salida
                   &&  aeropuertoLlegada.compareTo(vuelo.getAeropuertoLlegada()) == 0  //compara aeropuerto de llegada
                   &&  fecha.DAY_OF_YEAR == vuelo.getFechaVuelo().DAY_OF_YEAR   ){     //compara la fecha del vuelo?¿?¿?¿?¿?¿?¿?¿?¿?¿?¿ta mal, no se puede acceder asi a los atributos
                System.out.println("Los vuelos que coinciden con esos datos son: ");
                System.out.println(vuelo.toString());
                }
                else{
                System.out.println("No se encontraron vuelos con esos datos");
                }
            }        
    }
    
    //actualiza el fichero con los vuelos --> borra y crea
    /**
     * Escribe la lista de vuelos en ficheroVuelos
     */
    public void escribirVuelos(){
        try {
            FileOutputStream fos = new FileOutputStream(ficheroVuelos);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.vuelos);
            oos.flush();
            fos.flush();
            oos.close();
            fos.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Devuelve los viajeros de un vuelo
     * @param vuelo Vuelo del que se desea ver los pasajeros
     * @return Cadena de caracteres con los pasajeros de un vuelo
     */
    public String verPasajerosVuelo(Vuelo vuelo){
        StringBuffer texto = new StringBuffer();
        texto.append("Pasajeros de primera clase:\n");
        for (Iterator i = vuelo.getPasajerosPrimera().iterator() ; i.hasNext() ;){
            texto.append(((Pasajero) i.next()).toString());
        }
        texto.append("Pasajeros de clase turista:\n");
        for (Iterator i = vuelo.getPasajerosTurista().iterator() ; i.hasNext() ;){
            texto.append(((Pasajero) i.next()).toString());
        }
        return texto.toString();
    }
    

    
    public ListaVuelos buscarPasajeroDNI(int dni){
        ListaVuelos listav = new ListaVuelos("fichero.out");
        Pasajero pasajero = null;
        Vuelo vuelo;
            for (Iterator i = vuelos.iterator() ; i.hasNext() ;){
                vuelo = (Vuelo) i.next();
                pasajero = vuelo.buscarPasajeroTurista(dni);
                if(pasajero == null){
                    pasajero = vuelo.buscarPasajeroPrimera(dni);
                }
                if(pasajero != null){
                    listav.insertarVuelo(vuelo);
                }
            }
        return listav;
    }
   
    /**
     * Numero total de vuelos
     * @return numero total de vuelos
     */
    public int numeroVuelos(){
        //return vuelos.size();
        int cont = 0;
        if(vuelos != null){
            for (Iterator i = vuelos.iterator() ; i.hasNext() ; ){
                i.next();
                cont++;
            }
        }
        return cont;
    }
    
       
    /**
     * Devuelve una cadena de caracteres con la lista de vuelos
     * @return cadena de caracteres con la lista de vuelos
     */
    public String toString(){
        StringBuffer texto = new StringBuffer();
        for (Iterator i = vuelos.iterator() ; i.hasNext() ; ){
                texto.append(i.next().toString()).append("\n");
            }
        return texto.toString();
    }
}