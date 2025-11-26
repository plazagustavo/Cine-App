package cine.modelo;

import java.io.Serializable;

public class Butaca implements Serializable {
    
    private int numero;
    private char fila;
    private boolean ocupada;
    
    public Butaca(int numero, char fila) {
        this.numero = numero;
        this.fila = fila;
        this.ocupada = false;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public char getFila() {
        return fila;
    }
    
    public boolean isOcupada() {
        return ocupada;
    }
    
    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
    
    public String getUbicacion() {
        return fila + "-" + numero;
    }
}
    
