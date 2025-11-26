package cine.persistencia;

import cine.modelo.Cine;
import java.io.*;

public class PersistenciaDatos {
    private static final String ARCHIVO_CINE = "cine.ser";
    
    public static void guardarCine(Cine cine) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_CINE))) {
            oos.writeObject(cine);
            System.out.println("Cine guardado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
    
    public static Cine cargarCine() {
        File archivo = new File(ARCHIVO_CINE);
        if (!archivo.exists()) {
            return null;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_CINE))) {
            Cine cine = (Cine) ois.readObject();
            System.out.println("Cine cargado correctamente.");
            return cine;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar: " + e.getMessage());
            return null;
        }
    }
}