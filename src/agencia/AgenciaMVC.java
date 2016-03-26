/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agencia;

import java.awt.Image;

/**
 *
 * @author Grupo12
 */
public class AgenciaMVC {
    public static void main(String[] args){
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            Modelo modelo = new Modelo();
            Ventana vista= new Ventana(modelo);
            public void run() {
                vista.setLocation(100, 100);
                vista.setVisible(true);
            }
            Controlador controlador = new Controlador(modelo, vista);
        });
        
    }
}
