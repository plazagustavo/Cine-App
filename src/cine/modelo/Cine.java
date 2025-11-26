package cine.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cine implements Serializable {
    
    private String nombre;
    private List<Sala> salas;
    private List<Entrada> entradas;
    private List<Cliente> clientes;
    
    public Cine(String nombre) {
        this.nombre = nombre;
        this.salas = new ArrayList<>();
        this.entradas = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void agregarSala(Sala sala) {
        salas.add(sala);
    }
    
    public void agregarEntrada(Entrada entrada) {
        entradas.add(entrada);
    }
    
    public void registrarCliente(Cliente cliente) {
        if (!existeCliente(cliente.getEmail())) {
            clientes.add(cliente);
        }
    }
    
    public boolean existeCliente(String email) {
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
    
    public Cliente validarLogin(String email, String contraseña) {
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email) && cliente.getContraseña().equals(contraseña)) {
                return cliente;
            }
        }
        return null;
    }
    
    public Cliente obtenerCliente(String email) {
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email)) {
                return cliente;
            }
        }
        return null;
    }
    
    public List<Sala> getSalas() {
        return salas;
    }
    
    public List<Entrada> getEntradas() {
        return entradas;
    }
    
    public List<Cliente> getClientes() {
        return clientes;
    }
}