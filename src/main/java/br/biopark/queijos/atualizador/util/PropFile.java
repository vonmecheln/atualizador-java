/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.biopark.queijos.atualizador.util;

import br.biopark.queijos.atualizador.enumerator.EPropertie;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Renato
 */
public class PropFile {
    
    public void createFile(){
        
        try (OutputStream output = new FileOutputStream("config.properties")) {

            Properties prop = new Properties();

            // set the properties value
            prop.setProperty(EPropertie.URL_BASE_GIT.getDescricao(), "");
            
            prop.setProperty(EPropertie.DATABASE_CONNECTOR.getDescricao(), "");
            prop.setProperty(EPropertie.DATABASE_URL.getDescricao(), "");
            prop.setProperty(EPropertie.DATABASE_USER.getDescricao(), "");
            prop.setProperty(EPropertie.DATABASE_PASSWORD.getDescricao(), "");
            prop.setProperty(EPropertie.DATABASE_SCRIPT_LOCATION.getDescricao(), "");
            prop.setProperty(EPropertie.APLICATION_NAME.getDescricao(), "");
            prop.setProperty(EPropertie.APLICATION_VERSION.getDescricao(), "1.0.0");
            prop.setProperty(EPropertie.LOCAL_DESTINO.getDescricao(), "");
            prop.setProperty(EPropertie.UPDATE_CHECK_FREQUENCY.getDescricao(), "");
            
            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            Logger.getLogger(PropFile.class.getName()).log(Level.SEVERE, null, io);
        }
        
    }
    
    public String readPropertie(EPropertie propriedade){
    
       try (InputStream input = new FileInputStream("config.properties")) {

            Properties prop = new Properties();
            // load a properties file
            prop.load(input);

            // get the property value and print it out
            return prop.getProperty(propriedade.getDescricao());
        
        } catch (FileNotFoundException fe){
            createFile();
            readPropertie(propriedade);
        } catch (IOException ex) {
            Logger.getLogger(PropFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return null;
    }
    
    public void setProperty(String valor){
        
        try (OutputStream output = new FileOutputStream("config.properties")) {

            Properties prop = new Properties();

            // set the properties value
            prop.setProperty(EPropertie.DATABASE_CONNECTOR.getDescricao(), valor);
            
            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            Logger.getLogger(PropFile.class.getName()).log(Level.SEVERE, null, io);
        }
        
    }
    
}
