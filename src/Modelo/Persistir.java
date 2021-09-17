package Modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Persistir implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public void guardarObjetos(String ruta, Object modelo) {
        try {
            FileOutputStream fileOut = new FileOutputStream(ruta);
            ObjectOutputStream entradaDeDatos = new ObjectOutputStream(fileOut);
            entradaDeDatos.writeObject(modelo);
            entradaDeDatos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Object cargarObjetos(String ruta) {
        Object aux = new Object();

        try {
            ObjectInputStream salidaDeDatos = new ObjectInputStream(new FileInputStream(ruta));
            aux = salidaDeDatos.readObject();
            salidaDeDatos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 

        return aux;
    }


}