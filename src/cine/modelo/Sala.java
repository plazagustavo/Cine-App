package cine.modelo;

import java.io.Serializable;

public class Sala implements Serializable {

    
    private int numero;
    private String pelicula;
    private Butaca[][] butacas;
    private static final int FILAS = 5;
    private static final int COLUMNAS = 8;
    
    public Sala(int numero, String pelicula) {
        this.numero = numero;
        this.pelicula = pelicula;
        inicializarButacas();
    }
    
    private void inicializarButacas() {
        butacas = new Butaca[FILAS][COLUMNAS];
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                butacas[i][j] = new Butaca(j + 1, (char) ('A' + i));
            }
        }
    }
    
    public int getNumero() {
        return numero;
    }
    
    public String getPelicula() {
        return pelicula;
    }
    
    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }
    
    public Butaca[][] getButacas() {
        return butacas;
    }
    
    public Butaca getButaca(char fila, int numero) {
        int indFila = fila - 'A';
        if (indFila >= 0 && indFila < FILAS && numero > 0 && numero <= COLUMNAS) {
            return butacas[indFila][numero - 1];
        }
        return null;
    }
    
    public int getButacasLibres() {
        int libres = 0;
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (!butacas[i][j].isOcupada()) {
                    libres++;
                }
            }
        }
        return libres;
    }
    
    public int getFilas() {
        return FILAS;
    }
    
    public int getColumnas() {
        return COLUMNAS;
    }
    
    @Override
    public String toString() {
        return "Sala " + numero + " - " + pelicula + " (Libres: " + getButacasLibres() + ")";
    }
}