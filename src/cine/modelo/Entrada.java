package cine.modelo;

import java.io.Serializable;


public class Entrada implements Serializable {
    
    private Cliente cliente;
    private Sala sala;
    private Butaca butaca;
    private double precio;
    
    public Entrada(Cliente cliente, Sala sala, Butaca butaca, double precio) {
        this.cliente = cliente;
        this.sala = sala;
        this.butaca = butaca;
        this.precio = precio;
        butaca.setOcupada(true);
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public Sala getSala() {
        return sala;
    }
    
    public Butaca getButaca() {
        return butaca;
    }
    

    
    public double getPrecio() {
        return precio;
    }
    
    @Override
    public String toString() {

        return cliente.getNombre() + " - Sala " + sala.getNumero() + 
               " - " + butaca.getUbicacion() + " - $" + String.format("%.2f", precio);
    }
}