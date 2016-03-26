/*
 * Main.java
 *
 * Created on 27 de noviembre de 2007, 23:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package agencia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.GregorianCalendar;
import java.util.Random;


/**
 * 
 * Clase Main
 * @author moix
 */
public class Main {

    
    /**
     * Constructor
     */
    public Main() {
    }
    
    /**
     * Programa principal
     * @param args "Vendedor" o "Cliente"
     * @throws java.lang.Exception Excepciones
     */
    public static void main(String[] args) throws Exception{
        try {
            
            if (args.length == 0){
                System.out.println("Argumentos insuficientes");
                //MainVendedor();
            }
            else{
                if (args[0].compareTo("MainVendedor") == 0){
                  //  MainVendedor();
                }
                else if (args[0].compareTo("MainCliente") == 0){
                    MainCliente();
                }
                else{
                    System.out.println("Argumento no valido");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    
    
    
    /**
     * Metodo que contiene las opciones para un cliente
     * @throws java.lang.Exception Excepcion
     */
    private static void MainCliente() throws Exception{
        
        int aux, i = 0;
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        ListaAeropuertos aeropuertos = new ListaAeropuertos("aeropuertos.txt");
        ListaVuelos vuelos = new ListaVuelos("listaVuelos.out");
        ListaAviones aviones = new ListaAviones("aviones.txt");
        GregorianCalendar fecha = new GregorianCalendar();
        int year, mes, dia, diasflex, dni;
        Aeropuerto aeropuertoSalida, aeropuertoLlegada;
        String nombre, clase;
        
        Vuelo vuelo = null;
        Pasajero pasajero = null;
        do{
                System.out.println("Menu: ");
                System.out.println("1)  Ver aeropuertos disponibles  \t 2)  Visualizar los vuelos");
                System.out.println("3)  Comprar vuelo                \t 4)  Anular billete ");
                System.out.println("5)  Salir del programa \n");
            try {
                i = Integer.parseInt(entrada.readLine());
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
                switch (i){
                    
                    case 1:
                        System.out.println(aeropuertos);
                        break;
                    case 2:
                        System.out.println(vuelos);
                        break;
                    case 3:
                        System.out.println("Ciudad de salida: ");
                        aeropuertoSalida = aeropuertos.seleccionarAeropuerto(entrada.readLine());
                        // Metodo que busques los destinos de un aeropuerto
                        
                        System.out.println("Los destinos para este aeropuerto son: ");
                        //si no encontramos vuelos desde ese destino no seguimos con la compra del billete
                        if (vuelos.buscarVuelos(aeropuertoSalida)){
                            System.out.println("Escriba una ciudad de las anteriores: (pulse enter para cancelar)");
                            aeropuertoLlegada = aeropuertos.seleccionarAeropuerto(entrada.readLine());
                            
                            //si no encontramos vuelos hacia ese destino no seguimos con la compra del billete
                            System.out.println("Los vuelos con ese origen y destino son: ");
                            if ((aeropuertoLlegada != null) && (vuelos.buscarVuelos(aeropuertoSalida, aeropuertoLlegada))){
                                System.out.println("Introduzca la fecha:\n Anio: ");
                                year = Integer.parseInt(entrada.readLine());
                                System.out.println("Mes: ");
                                mes = Integer.parseInt(entrada.readLine());
                                mes--;
                                System.out.println("Dia del mes: ");
                                dia = Integer.parseInt(entrada.readLine());
                                System.out.println("Dias flexibles: ");
                                diasflex = Integer.parseInt(entrada.readLine());
                                fecha.set(year, mes, dia);
                                System.out.println(fecha.getTime().toString());                                
                                System.out.println("Los vuelos que se ajustan a esas fechas son: ");
                                if (vuelos.buscarVuelos(aeropuertoSalida, aeropuertoLlegada, fecha, diasflex)){
                                    System.out.println("Introduzca el num. ref. del vuelo que desea:");
                                    vuelo = vuelos.buscarVuelo(Integer.parseInt(entrada.readLine()));
                                    
                                    System.out.println("Nombre: ");
                                    nombre = entrada.readLine();
                                    System.out.println("DNI: ");
                                    dni = Integer.parseInt(entrada.readLine());
                                    
                                    System.out.println("Turista o Primera Clase? (T/P): ");
                                    clase = entrada.readLine();
                                    if(clase.compareToIgnoreCase("T") == 0){
                                        vuelo.insertarPasajeroTurista(new Pasajero(nombre,dni));
                                    }
                                    else if(clase.compareToIgnoreCase("P") == 0){
                                        vuelo.insertarPasajeroPrimera(new Pasajero(nombre,dni));
                                    }
                                    else{
                                        System.out.println("Clase incorrecta, se le asignara turista");
                                        vuelo.insertarPasajeroTurista(new Pasajero(nombre,dni));
                                    }
                                }
                                else{
                                    System.out.println("No hay vuelos que se ajusten a esas fechas");
                                }
                            }
                            else{
                                System.out.println("No hay vuelos a ese destino");
                            }
                        }
                        else{
                            System.out.println("No hay vuelos desde ese origen");
                        }
                        break;
                    case 4:
                        System.out.println("Num ref del vuelo del que desea anular su billete: ");
                        vuelo = vuelos.buscarVuelo(Integer.parseInt(entrada.readLine()));
                        if (vuelo != null){
                            System.out.println("DNI del pasajero: ");
                            dni = Integer.parseInt(entrada.readLine());
                            pasajero = vuelo.buscarPasajeroTurista(dni);
                            if(pasajero != null){
                                vuelo.eliminarPasajeroTurista(pasajero);
                            }
                            else{
                                pasajero=vuelo.buscarPasajeroPrimera(dni);
                                if(pasajero != null){
                                    vuelo.eliminarPasajeroPrimera(pasajero);
                                }
                                else{
                                    System.out.println("Pasajero no encontrado \n");
                                }
                            }
                        }
                        else{
                            System.out.println("No hay un vuelo con esa referencia");
                        }
                        break;
                    case 5:
                        System.out.println("Se sale del menu cliente");
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }
            
            
            }while (i != 5);
        
        vuelos.escribirVuelos();
    }
    
    
    
    
    /**
     * Metodo que contiene las opciones para un vendedor
     * @throws java.lang.Exception Excepcion
     */
    /*private static void MainVendedor() throws Exception{
        
        int aux, i = 0;
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        Password password = new Password("ficheroPassword.txt");
        Random aleatorio = new Random();
        ListaAeropuertos aeropuertos = new ListaAeropuertos("aeropuertos.txt");
        ListaVuelos vuelos = new ListaVuelos("listaVuelos.out");
        ListaAviones aviones = new ListaAviones("aviones.txt");
        GregorianCalendar fecha = new GregorianCalendar();
        Aeropuerto aeropuertoSalida;
        Aeropuerto aeropuertoLlegada;
        Vuelo vuelo = null;
        Pasajero pasajero = null;
        int year, mes, dia, diasflex, hor, min, precioPrimera, precioTurista, dni;
        String nombre, clase;
        
        if (password.comprobarPassword()){
            
            do{
                System.out.println("Menu: ");
                System.out.println("1)  Ver aeropuertos disponibles  \t 2)  Visualizar los vuelos");
                System.out.println("3)  Listar pasajeros de un vuelo \t 4)  Ver flota de aviones disponible");
                System.out.println("5)  Insertar nuevo aeropuerto    \t 6)  Insertar nuevo vuelo ");
                System.out.println("7)  Eliminar aeropuerto          \t 8)  Eliminar vuelo ");
                System.out.println("9)  Insertar avion               \t 10) Eliminar avion ");
                System.out.println("11) Insertar nuevo pasajero      \t 12) Eliminar pasajero ");
                System.out.println("13) Cambiar password             \t 14) Salir del programa ");
                i = Integer.parseInt(entrada.readLine());
            
                switch (i){
                    
                    case 1:
                        System.out.println(aeropuertos);
                        break;
                    case 2:
                        System.out.println(vuelos);
                        break;
                    case 3:
                        System.out.println("Esta es la lista de vuelos:");
                        System.out.println(vuelos);
                        System.out.println("Num ref del vuelo que quiere listar: ");
                        System.out.println(vuelos.verPasajerosVuelo(vuelos.buscarVuelo(Integer.parseInt(entrada.readLine()))));
                        
                        break;
                    case 4:
                        System.out.println(aviones.toString());
                        break;
                    case 5:
                        System.out.println("Introduzca los datos del aerouerto con el formato: ");
                        System.out.println("codigo:ciudad:nombre  o  codigo:ciudad");
                        String nuevoAeropuerto = entrada.readLine();
                        aeropuertos.insertarAeropuerto(new Aeropuerto(nuevoAeropuerto));
                        break;
                    case 6:
                        System.out.println("Ciudad de salida: ");
                        aeropuertoSalida = aeropuertos.seleccionarAeropuerto(entrada.readLine());
                        System.out.println("Ciudad de llegada: ");
                        aeropuertoLlegada = aeropuertos.seleccionarAeropuerto(entrada.readLine());
                        System.out.println("Seleccione el avion del vuelo (introduzca el num ref) de los posibles\n"+aviones.toString());
                        Avion avionVuelo = aviones.seleccionarAvion(Integer.parseInt(entrada.readLine()));
                        
                        System.out.println("Introduzca la fecha:\n Anio: ");
                        year = Integer.parseInt(entrada.readLine());
                        System.out.println("Mes (0 enero ... 11 diciembre): ");
                        mes = Integer.parseInt(entrada.readLine());
                        System.out.println("Dia del mes (0 enero ... 11 diciembre): ");
                        dia = Integer.parseInt(entrada.readLine());
                        System.out.println("Hora: ");
                        hor = Integer.parseInt(entrada.readLine());
                        System.out.println("Minutos: ");
                        min = Integer.parseInt(entrada.readLine());
                        fecha.set(year, mes, dia, hor, min);
                        System.out.println("Introduzca los precios de los billetes:\n");
                        System.out.println("precio primera clase: ");
                        precioPrimera = Integer.parseInt(entrada.readLine());
                        System.out.println("precio clase turista: ");
                        precioTurista = Integer.parseInt(entrada.readLine());
                        vuelos.insertarVuelo(new Vuelo(avionVuelo, aeropuertoSalida, aeropuertoLlegada, fecha, aleatorio.nextInt(100000), precioTurista, precioPrimera));
                        break;
                    case 7:
                        System.out.println("Ciudad del aeropuerto: ");
                        Aeropuerto aeropuertoEliminar = aeropuertos.seleccionarAeropuerto(entrada.readLine());
                        aeropuertos.eliminarAeropuerto(aeropuertoEliminar);
                        break;
                    case 8:
                        System.out.println("Esta es la lista de vuelos:");
                        System.out.println(vuelos);
                        System.out.println("Num de ref del vuelo que quiere eliminar: ");
                        vuelos.eliminarVuelo(Integer.parseInt(entrada.readLine()));
                        break;
                    case 9:
                        System.out.println("Introduzca los datos del nuevo avion con el formato: ");
                        System.out.println("modelo:compania:plazas turista:plazas primera");
                        String nuevoAvion = entrada.readLine();
                        aviones.insertarAvion(new Avion(nuevoAvion));
                        break;
                    case 10:
                        System.out.println("Seleccione el avion a eliminar (introduzca el num ref) de los posibles\n"+aviones.toString());
                        aviones.eliminarAvion(aviones.seleccionarAvion(Integer.parseInt(entrada.readLine())));
                        break;
                    case 11:
                        System.out.println("Ciudad de salida: ");
                        aeropuertoSalida = aeropuertos.seleccionarAeropuerto(entrada.readLine());
                        // Metodo que busques los destinos de un aeropuerto
                        
                        System.out.println("Los destinos para este aeropuerto son: ");
                        //si no encontramos vuelos desde ese destino no seguimos con la compra del billete
                        if (vuelos.buscarVuelos(aeropuertoSalida)){
                            System.out.println("Ciudad de las anterioes a la que quiere viajar: (pulse enter para cancelar si no aparece la deseada)");
                            aeropuertoLlegada = aeropuertos.seleccionarAeropuerto(entrada.readLine());
                            
                            //si no encontramos vuelos hacia ese destino no seguimos con la compra del billete
                            System.out.println("Los vuelos con ese origen y destino son: ");
                            if ((aeropuertoLlegada != null) && (vuelos.buscarVuelos(aeropuertoSalida, aeropuertoLlegada))){
                                System.out.println("Introduzca la fecha:\n Anio: ");
                                year = Integer.parseInt(entrada.readLine());
                                System.out.println("Mes (0 enero ... 11 diciembre): ");
                                mes = Integer.parseInt(entrada.readLine());
                                System.out.println("Dia del mes: ");
                                dia = Integer.parseInt(entrada.readLine());
                                System.out.println("Dias flexibles: ");
                                diasflex = Integer.parseInt(entrada.readLine());
                                fecha.set(year, mes, dia);
                                System.out.println(fecha.getTime().toString());                                
                                System.out.println("Los vuelos que se ajustan a esas fechas son: ");
                                if (vuelos.buscarVuelos(aeropuertoSalida, aeropuertoLlegada, fecha, diasflex)){
                                    System.out.println("Introduzca el num. ref. del vuelo que desea:");
                                    vuelo = vuelos.buscarVuelo(Integer.parseInt(entrada.readLine()));
                                    
                                    System.out.println("Nombre: ");
                                    nombre = entrada.readLine();
                                    System.out.println("DNI: ");
                                    dni = Integer.parseInt(entrada.readLine());
                                    
                                    System.out.println("Turista o Primera Clase? (T/P): ");
                                    clase = entrada.readLine();
                                    if(clase.compareToIgnoreCase("T") == 0){
                                        vuelo.insertarPasajeroTurista(new Pasajero(nombre,dni));
                                    }
                                    else if(clase.compareToIgnoreCase("P") == 0){
                                        vuelo.insertarPasajeroPrimera(new Pasajero(nombre,dni));
                                    }
                                    else{
                                        System.out.println("Clase incorrecta, se le asignara turista");
                                        vuelo.insertarPasajeroTurista(new Pasajero(nombre,dni));
                                    }
                                }
                                else{
                                    System.out.println("No hay vuelos que se ajusten a esas fechas");
                                }
                            }
                            else{
                                System.out.println("No hay vuelos a ese destino");
                            }
                        }
                        else{
                            System.out.println("No hay vuelos desde ese origen");
                        }
                        break;
                    case 12:
                        System.out.println("Num ref del vuelo del que desea anular su billete: ");
                        vuelo = vuelos.buscarVuelo(Integer.parseInt(entrada.readLine()));
                        if (vuelo != null){
                            System.out.println("DNI del pasajero: ");
                            dni = Integer.parseInt(entrada.readLine());
                            pasajero = vuelo.buscarPasajeroTurista(dni);
                            if(pasajero != null){
                                vuelo.eliminarPasajeroTurista(pasajero);
                            }
                            else{
                                pasajero=vuelo.buscarPasajeroPrimera(dni);
                                if(pasajero != null){
                                    vuelo.eliminarPasajeroPrimera(pasajero);
                                }
                                else{
                                    System.out.println("Pasajero no encontrado \n");
                                }
                            }
                        }
                        else{
                            System.out.println("No hay un vuelo con esa referencia");
                        }
                        break;
                    case 13:
                        password.cambiarPassword();
                        break;
                    case 14:
                        System.out.println("Se sale del menu vendedor");
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }
            
            
            }while (i != 14);
        }
        
        else{
            System.out.println("La password no es correcta, no puedes acceder a este menu");
        }
        
        aeropuertos.escribirAeropuertos();
        vuelos.escribirVuelos();
        aviones.escribirAviones();
        
    }*/
}
