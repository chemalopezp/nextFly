/*
 * Aeropuerto.java
 *
 * Created on 27 de noviembre de 2007, 23:34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package agencia;

import java.io.Serializable;
import java.util.StringTokenizer;

/**
 * Clase Aeropuerto
 * @author moix
 */
public class Aeropuerto implements Serializable, Comparable<Aeropuerto>{
   
    /**
     * Codigo IATA de 3 letras del aeropuerto
     */
    private String codigo;
    /**
     * Ciudad en la que esta situada el aeropuerto
     */
    private String ciudad;
    /**
     * Nombre del aeropuerto (opcional)
     */
    private String nombre;
    
    /**
     * Caracter delimitador para almacenar el aeropuerto en ficheros
     * Por defecto :
     */
    private final String delim = ":";
    private final String coma = ", ";
    
    
    public Aeropuerto(){
        this.codigo = null;
        this.ciudad = null;
        this.nombre = null;
    }
    
    
    /**
     * Constructor de Aeropuerto con codigo y ciudad
     * @param codigo Codigo de 3 letras del aeropuerto
     * @param ciudad Nombre de la ciudad
     */
    public Aeropuerto(String codigo, String ciudad) {
        this.codigo = codigo;
        this.ciudad = ciudad;
        this.nombre = null;
    }

    /**
     * Creates a new instance of Aeropuerto
     * @param codigo Codigo de 3 letras
     * @param ciudad Nombre de la ciudad
     * @param nombre Nombre del aeropuerto
     */
    public Aeropuerto(String codigo, String ciudad, String nombre) {
        this.codigo = codigo;
        this.ciudad = ciudad;
        this.nombre = nombre;
    }
    
    /**
     * Crea un nuevo objeto Aeropuerto a partir de una linea string con formato:
     *     "codigo:ciudad" o "codigo:ciudad:nombre" Caracter delimitador :
     * @param aeropuerto linea string con formato:
     *     "codigo:ciudad" o "codigo:ciudad:nombre"
     */
    public Aeropuerto(String aeropuerto) {
        StringTokenizer separador = new StringTokenizer(aeropuerto,delim);
        if (separador.countTokens() == 2){
            this.codigo = separador.nextToken();
            this.ciudad = separador.nextToken();
            this.nombre = null;
        }else if (separador.countTokens() == 3){
            this.codigo = separador.nextToken();
            this.ciudad = separador.nextToken();
            this.nombre = separador.nextToken();
        }
    }
    
    /**
     * Devuelve el codigo del aeropuerto
     * @return codigo
     */
    public String getCodigo(){
        return codigo;
    }
    
    /**
     * Modifica el atributo codigo del aeropuerto
     * @param codigo codigo
     */
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    
    /**
     * Devuelve el nombre de la ciudad
     * @return Nombre de la ciudad
     */
    public String getCiudad(){
        return ciudad;
    }
    
    /**
     * Modifica el atributo ciudad
     * @param ciudad Nuevo atributo ciudad
     */
    public void setCiudad(String ciudad){
        this.ciudad = ciudad;
    }
    
    /**
     * Devuelve el atributo nombre
     * @return nombre del aeropuerto
     */
    public String getNombre(){
        return nombre;
    }
    
    /**
     * Modifica el atributo nombre
     * @param nombre Nuevo nombre del aeropuerto
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    /**
     * Metodo que devuelve los atributos con formato Codigo:Ciudad:Nombre
     * @return cadena con formato Codigo:Ciudad:Nombre
     */
    public String toString(){
        StringBuffer aeropuerto = (new StringBuffer(ciudad));
        if (this.nombre != null){
            aeropuerto.append(coma).append(nombre);
        }
        return aeropuerto.toString();
    }
    public String toStringGuardar(){
        StringBuffer aeropuerto = (new StringBuffer(codigo)).append(delim).append(ciudad);
        if (this.nombre != null){
            aeropuerto.append(delim).append(nombre);
        }
        return aeropuerto.toString();
    }
    
    /**
     * Verifica si el codigo del aeropuerto es igual que el que se le pasa por parametro
     * @param O Objeto que se quiere comprobar la igualdad
     * @return True si los codigos de ambos aeropuertos son iguales, False si no lo son
     */
    /*public boolean equals(Object O){   ---------------->> era lo que daba problemas al eliminar un aeropuerto
        return codigo.equalsIgnoreCase(((Aeropuerto)O).getCodigo());
    }*/

    /**
     * Compara si el codigo del aeropuerto es igual que el codigo del aeropuerto que le pasa por parametro
     * @param comparable Aeropuerto a comparar
     * @return 0 si los codigos de ambos aeropuertos son iguales, 1 si no lo son
     */
    public int compareTo(Aeropuerto comparable) {
        if(this.codigo.equalsIgnoreCase(comparable.getCodigo())){
            return 0;
        }
        else{
            return 1;
        }
    }
    
}