/*
 * Password.java
 *
 * Created on 4 de diciembre de 2007, 21:47
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package agencia;

//import org.apache.xerces.impl.dv.util.Base64;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;

/**
 * Clase Password
 * @author moix
 */
public class Password {
    
    /**
     * Fichero donde se guarda la password
     */
    private File ficheroPassword;
    
    
    public Password(String ficheroPassword){
        this.ficheroPassword = new File(ficheroPassword);
    }
    
    
    public boolean comprobarPassword(String password){
        
        boolean devolver = false;
        try{
            if (ficheroPassword.exists()){
                BufferedReader lector = new BufferedReader(new FileReader(ficheroPassword));
                String hashPassword = lector.readLine();
                password = hashPassword(password);
                if(password.charAt(password.length()-1)=='\n')          //segun el sistema la funcion hash a veces devolvia con \n
                    password=password.substring(0,password.length()-1);
                
                
                if (hashPassword.compareTo(password) == 0){
                    devolver = true;
                }
                else{
                    devolver = false;
                }
            }
            else{
                System.out.println("¡El fichero de password ha sido borrado!");
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return devolver;
    }
    
    
    public void cambiarPassword(String nuevaPassword){
        try{
            if (ficheroPassword.exists()){
                ficheroPassword.delete();
                BufferedWriter escritor = new BufferedWriter(new FileWriter(ficheroPassword));
                escritor.write(hashPassword(nuevaPassword));
                escritor.flush();
                escritor.close();
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    private String hashPassword(String password){
        
        String hash = null;
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(password.getBytes());
            //hash = '1'; //Base64.encode(md5.digest()).toString();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return hash;
    }   
    
    
}
