package com.example.pokerwebapp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/***
 * Singleton pattern for Environment Properties
 * @author Prof. H. Rocha
 */
public class EnvProperties {
    private static EnvProperties Instance = null;

    private Properties Prop;

    private EnvProperties() throws IOException{
        this.Prop = new Properties();
        this.readFromFile(".env");
    }

    public static EnvProperties getInstance(){
        if(Instance==null){
            try {
                Instance = new EnvProperties();
            }catch(IOException ex){
                System.out.println(ex);
            }
        }
        return Instance;
    }

    public Properties getProperties() {
        return Prop;
    }

    public static String get(String key){
        return getInstance().getProperties().getProperty(key);
    }

    public void readFromFile(String filename) throws IOException {
        File EnvFile = new File(filename);
        System.out.println(EnvFile.getAbsolutePath());
        if(EnvFile.exists()) {
            this.Prop.load(new FileInputStream(EnvFile));
        }else{
            throw new IOException(".env file do not exist");
        }
    }

}