/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agencia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 *
 * @author Grupo12
 */
public class Controlador {

    private Ventana vista;
    private Modelo modelo;
    
    public Controlador (Modelo modelo, Ventana vista){
        this.vista = vista;
        this.modelo = modelo;
        
        crearvuelos();
        vista.cargarListaVuelos(modelo.vuelos);
        CargarDesplegableAeropuertosSalida();
        
        
        // Carga los aeropuertos de llegada para el aeropuerto de salida
        // que aparezca seleccionado
        try {
            int cont = 0;
            vista.resetDesplegableAeropuertosLlegada();
            ListaAeropuertos aeropuertosLlegada;
            aeropuertosLlegada = modelo.vuelos.buscarAeropuertosLleg(vista.AeropuertoSalida());
            for (Iterator i = aeropuertosLlegada.getAeropuertos().iterator() ; i.hasNext() ; ){
                vista.insertarAeropuertoLlegada((Aeropuerto)i.next());
                cont++;
            }
            if(cont==0){
                vista.insertarAeropuertoLlegada(null);
            }
        } catch (Exception exception) {
        }
        
        //inicializacion de los listener y de algunos campos de informacion
        vista.desplegableAeropuertosSalidaListener(new DesplegableAeropuertosSalidaListener());
        vista.buscarVuelosListener(new BuscarVuelosListener());
        vista.vendedorListener(new VendedorListener());
        vista.comprarListener(new ComprarListener());
        vista.guardarListener(new GuardarListener());
        vista.salirListener(new SalirListener());
        vista.acercaDeListener(new AcercaDeListener());
        vista.verTodosVuelosListener(new VerTodosVuelosListener());
        vista.vercambiarListener(new VerCambiarListener());
        vista.setInformacionGeneralClientes("Introduce el DNI para consultar los vuelos comprados");
    }
    
    //ejemplo para crear vuelos a mano
    public void crearvuelos(){
        GregorianCalendar fecha = new GregorianCalendar();
        modelo.vuelos.insertarVuelo(new Vuelo(new Avion("McDonnell Douglas MD-81:Spanair:166:6"),new Aeropuerto("AGP:Malaga"),new Aeropuerto("﻿LCG:La Corunia:Alvedro"),fecha,44444, 25, 250));
        modelo.vuelos.insertarVuelo(new Vuelo(new Avion("Boeing 757:Iberia:248:4"),new Aeropuerto("AGP:Malaga"),new Aeropuerto("ZAZ:Zaragoza"),fecha,22222, 50, 250));
        modelo.vuelos.insertarVuelo(new Vuelo(new Avion("Boeing 757:Iberia:248:4"),new Aeropuerto("VLC:Valencia"),new Aeropuerto("OVD:Asturias"),fecha,33333, 40, 250));
    }
    // Retorna un numero entre 0 y 11 que corresponde al mes
    public int month(String m) {

		int mo = 0;

		if(m.equals("Enero"))
		mo = 0;
		else if(m.equals("Febrero"))
		mo = 1;
		else if(m.equals("Marzo"))
		mo = 2;
		else if(m.equals("Abril"))
		mo = 3;
		else if(m.equals("Mayo"))
		mo = 4;
		else if(m.equals("Junio"))
		mo = 5;
		else if(m.equals("Julio"))
		mo = 6;
		else if(m.equals("Agosto"))
		mo = 7;
		else if(m.equals("Septiembre"))
		mo = 8;
		else if(m.equals("Octubre"))
		mo = 9;
		else if(m.equals("Noviembre"))
		mo = 10;
		else
		mo = 11;
		return(mo);
	}
    
    
    
    private void CargarDesplegableAeropuertosSalida(){
            try {
                vista.resetDesplegableAeropuertosSalida();
                for (Iterator i = modelo.aeropuertos.getAeropuertos().iterator() ; i.hasNext() ; ){
                    vista.insertarAeropuertoSalida((Aeropuerto)i.next());
                }

            } catch (Exception exception) {
            }
    }
    private void CargarDesplegableAeropuertosVendedor(){
            try {
                vista.resetDesplegableAeropuertosSalidaVendedor();
                vista.resetDesplegableAeropuertosLlegadaVendedor();
                for (Iterator i = modelo.aeropuertos.getAeropuertos().iterator() ; i.hasNext() ; ){
                    vista.insertarAeropuertoSalidaVendedor((Aeropuerto)i.next());
                }
                for (Iterator i = modelo.aeropuertos.getAeropuertos().iterator() ; i.hasNext() ; ){
                    vista.insertarAeropuertoLlegadaVendedor((Aeropuerto)i.next());
                }

            } catch (Exception exception) {
            }
    }
    private void CargarDesplegableAvionesVendedor(){
            try {
                vista.resetDesplegableAvionesVendedor();
                for (Iterator i = modelo.aviones.getAviones().iterator() ; i.hasNext() ; ){
                    vista.insertarAvionesDesplegable((Avion)i.next());
                }
            } catch (Exception exception) {
            }
    }
    

    
    
          
    
    //ventana principal
    
    class VendedorListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(modelo.password.comprobarPassword(vista.getPassword())){
                vista.cambiarPasswordListener(new CambiarPasswordListener());
                vista.setOnAdministrador();
                vista.insertarAeropuertoListener(new InsertarAeropuertoListener());
                vista.eliminarAeropuertoListener(new EliminarAeropuertoListener());
                vista.cargarListaAeropuertos(modelo.aeropuertos);
                vista.insertarAvionListener(new InsertarAvionListener());
                vista.eliminarAvionListener(new EliminarAvionListener());
                vista.insertarVueloListener(new InsertarVueloListener());
                vista.eliminarVueloListener(new EliminarVueloListener());
                vista.verClientesVuelo(new VerClientesVueloListener());
               // vista.cargarListaPasajerosTuristaVendedor(modelo.vuelo);
                //vista.cargarListaPasajerosPrimeraVendedor(modelo.vuelo);
                CargarDesplegableAeropuertosVendedor();
                CargarDesplegableAvionesVendedor();
                vista.cargarListaVuelosVendedor(modelo.vuelos);
                vista.cargarListaAviones(modelo.aviones);
                vista.setInformacionAdminGeneral("Correcta, opciones de administración");
            }
            else{
                vista.setOffAdministrador();
                vista.setInformacionAdminGeneral("Incorrecta, no puedes acceder al menu administración");
                
            }
        }
    }
    
    class GuardarListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            modelo.aeropuertos.escribirAeropuertos();
            modelo.vuelos.escribirVuelos();
            modelo.aviones.escribirAviones();
            vista.setInformacionGeneral("Todos los cambios han sido guardados");
        }
    }
    class SalirListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    class AcercaDeListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            vista.onAcercaDeNextfly();
        }
    }
    
    
    class VerTodosVuelosListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            vista.cargarListaVuelos(modelo.vuelos);
            vista.setInformacionGeneral("Listado de todos los vuelos disponibles en la agencia");
        }
    }
    class VerCambiarListener implements ActionListener{
      public void actionPerformed(ActionEvent e) {
          try {
              vista.setInformacionGeneralClientes("Deberias introducir un DNI");
              vista.cargarListaVuelosClientes(modelo.vuelos.buscarPasajeroDNI(vista.compdni()));
              vista.setInformacionGeneralClientes("Vuelos comprados por el cliente con DNI: " + vista.compdni());
              vista.anularBilleteListener(new AnularBilleteListener());
          } catch (Exception exception) {
          }

        }
    }
    
    class AnularBilleteListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(vista.isVueloSeleccionadoClientes()){
                if(vista.vueloSeleccionadoClientes().eliminarPasajero(vista.compdni())){
                vista.setInformacionGeneralClientes("Pasajero "+ vista.compdni() +" eliminado.");
                vista.cargarListaVuelosClientes(modelo.vuelos.buscarPasajeroDNI(vista.compdni()));
                }
            }
            else{
                vista.setInformacionGeneralClientes("Deberías seleccionar el vuelo que quieres cancelar");
            }
            
        }
    }
    
    class DesplegableAeropuertosSalidaListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try {
                int cont = 0;
                vista.resetDesplegableAeropuertosLlegada();
                ListaAeropuertos aeropuertosLlegada;
                aeropuertosLlegada = modelo.vuelos.buscarAeropuertosLleg(vista.AeropuertoSalida());
                for (Iterator i = aeropuertosLlegada.getAeropuertos().iterator() ; i.hasNext() ; ){
                    cont++;
                    vista.insertarAeropuertoLlegada((Aeropuerto)i.next());
                }
                if (cont==0){
                    vista.insertarAeropuertoLlegada(null);
                }
            } catch (Exception exception) {
            }
        }
    }
    
    
    
    class BuscarVuelosListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
          ListaVuelos vuelosEncontrados = new ListaVuelos();
          Aeropuerto aeropuertoSalida, aeropuertoLlegada;
          aeropuertoSalida = vista.AeropuertoSalida();
          aeropuertoLlegada = vista.AeropuertoLlegada();
          GregorianCalendar fecha = new GregorianCalendar();
          fecha.set(vista.getAnio(), month(vista.getMes()), vista.getDia());
          int diasflex = vista.getDiasFlexibles();
          vuelosEncontrados = modelo.vuelos.buscarVuelos2(aeropuertoSalida, aeropuertoLlegada, fecha, diasflex);
          vista.cargarListaVuelos(vuelosEncontrados);
          if (aeropuertoLlegada == null){
              vista.setInformacionGeneral("No se han encontrado vuelos con esos datos");
          }
          else{
              StringBuffer info = (new StringBuffer().append("Vuelos de "));
              info.append(aeropuertoSalida).append(" a ").append(aeropuertoLlegada).append(" en las fechas indicadas");
              vista.setInformacionGeneral(info.toString());
          }
        }
    }
    
    class ComprarListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(vista.isVueloSeleccionado()){
                vista.compra(vista.vueloSeleccionado().getPrecioPrimera(), vista.vueloSeleccionado().getPrecioTurista());
                vista.resetFormaPago();
                vista.cargarFormaPago();
                vista.comprarBilleteListener(new ComprarBilleteListener());
                vista.cancelarListener(new CancelarListener());
                vista.setInformacionGeneral("");
            }
            else{
                vista.setInformacionGeneral("Deberías seleccionar un vuelo");
            }
        }
    }
    
    
    //ventana comprar billete
    
    class ComprarBilleteListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(vista.getRadioButtonSelected().compareTo("turista") == 0){
                comprarTurista();
            }
            else if (vista.getRadioButtonSelected().compareTo("primera") == 0){
                comprarPrimera();
            }
            else{
                vista.setInformacionGeneral("No se pudo realizar la compra, debe seleccionar un tipo de billete");
            }
        }
    }
    public void comprarTurista(){
        try {
            vista.vueloSeleccionado().insertarPasajeroTurista(new Pasajero(vista.nombre(), vista.DNI(),vista.formaPago()));
        } catch (Exception exception) {
        }
        vista.cargarListaVuelos(modelo.vuelos);
        vista.cancelacompra();
    }
    public void comprarPrimera(){
        try {
            vista.vueloSeleccionado().insertarPasajeroPrimera(new Pasajero(vista.nombre(), vista.DNI(),vista.formaPago()));
        } catch (Exception exception) {
        }

        vista.cargarListaVuelos(modelo.vuelos);
        vista.cancelacompra();
    
    }
    class CancelarListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            vista.cancelacompra();
        }
    }
    
    
    
    //ventana vendedor/administrador
        
        //pestaña aeropuertos
    
    class EliminarAeropuertoListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            modelo.aeropuertos.eliminarAeropuerto(vista.aeropuertoSeleccionado());
            vista.cargarListaAeropuertos(modelo.aeropuertos);
            CargarDesplegableAeropuertosSalida();
        }
    }
    
    class InsertarAeropuertoListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(!(vista.nuevoCodigo().isEmpty()||vista.nuevaCiudad().isEmpty())){
                modelo.aeropuertos.insertarAeropuerto(new Aeropuerto(vista.nuevoCodigo(),vista.nuevaCiudad(),vista.nuevoNombre()));
                vista.cargarListaAeropuertos(modelo.aeropuertos);
                vista.insertarAeropuertoSalida(new Aeropuerto(vista.nuevoCodigo(),vista.nuevaCiudad(),vista.nuevoNombre()));
            }
            
        }
    }
    
    
    
        //pestaña aviones
    
    class EliminarAvionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            modelo.aviones.eliminarAvion(vista.avionSeleccionado());
            vista.cargarListaAviones(modelo.aviones);
        }
    }
    
    class InsertarAvionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(vista.nuevoModelo().isEmpty() || vista.nuevaCompania().isEmpty()){
                vista.mostrarInformeAviones("Debes rellenar todos los campos");
            }
            else{
                modelo.aviones.insertarAvion(new Avion(vista.nuevoModelo(),vista.nuevaCompania(),vista.nuevaPlazasTurista(),vista.nuevaPlazasPrimera()));
                vista.cargarListaAviones(modelo.aviones);
                vista.mostrarInformeAviones("Nuevo avión creado");
            }
        }
    }
    
    
    
        //pestaña vuelos
    class InsertarVueloListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            modelo.vuelos.insertarVuelo(new Vuelo(vista.getAvion(),vista.getAeropuertoSalidaVendedor(), vista.getAeropuertoLlegadaVendedor(),new GregorianCalendar(vista.getAnioVendedor(),month(vista.getMesVendedor()),vista.getDiaVendedor()),vista.getnuevoNumRefVendedor(),vista.getPrecioTuristaVendedor(),vista.getPrecioPrimeraVendedor()));
            vista.cargarListaVuelosVendedor(modelo.vuelos);            
        }
    }
    class EliminarVueloListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            modelo.vuelos.eliminarVuelo2(vista.vueloSeleccionadoVendedor());
            vista.cargarListaVuelosVendedor(modelo.vuelos);
        }                           
    }
    
    class VerClientesVueloListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
                vista.verClientes(vista.vueloSeleccionadoVendedor());
                
                vista.anularBilletePrimeraListener(new AnularBilletePrimeraListener());
                vista.anularBilleteTuristaListener(new AnularBilleteTuristaListener());
                vista.volverListener(new VolverListener());
                vista.setInformacionGeneral("");
        }
    }
    
    class AnularBilletePrimeraListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
          try{
            if(vista.vueloSeleccionadoVendedor().eliminarPasajero(vista.pasajeroSeleccionadoPrimera().getDni())){
                vista.verClientes(vista.vueloSeleccionadoVendedor());
                vista.cargarListaVuelos(modelo.vuelos);
             }   
            } catch (Exception exception){
            }
        }
    }
    class AnularBilleteTuristaListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
           try{
            if(vista.vueloSeleccionadoVendedor().eliminarPasajero(vista.pasajeroSeleccionadoTurista().getDni())){
                vista.verClientes(vista.vueloSeleccionadoVendedor());
                vista.cargarListaVuelos(modelo.vuelos);
            }
           } catch (Exception exception){
           }
        }
    }
    
    class VolverListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            vista.cargarListaVuelosVendedor(modelo.vuelos);
            vista.volverVendedor();
        }
    }
    
    
        //pestaña password
    
    class CambiarPasswordListener implements ActionListener{
        public void actionPerformed(ActionEvent ven){
            if(modelo.password.comprobarPassword(vista.getPasswordActual())){
                if(vista.getNuevaPassword().compareTo(vista.getNuevaPasswordRepeat()) == 0){
                    modelo.password.cambiarPassword(vista.getNuevaPassword());
                    vista.mostrarInformePassword("Password cambiada correctamente");
                }
                else{
                    vista.mostrarInformePassword("La password no se ha podido cambiar, revisa los campos introducidos");
                }
            }
            else{
                vista.mostrarInformePassword("La password no se ha podido cambiar, revisa los campos introducidos");
            }
        }
    }
}
