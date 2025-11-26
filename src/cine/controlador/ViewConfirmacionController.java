package cine.controlador;

import cine.modelo.Butaca;
import cine.modelo.Cine;
import cine.modelo.Cliente;
import cine.modelo.Entrada;
import cine.modelo.Sala;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors; 

public class ViewConfirmacionController {
    
    private static final double PRECIO_BUTACA_UNITARIO = 1000.00; 

    @FXML
    private Label lblPelicula;
    @FXML
    private Label lblSala;
    @FXML
    private Label lblButaca; 
    @FXML
    private Label lblPrecio; 

    private Cine cine;
    private Cliente cliente;
    private Sala sala;

    private List<Butaca> butacasSeleccionadas;
    private Stage stage;
    private double precioTotal;
    
    
    public void setCine(Cine cine) {
        this.cine = cine;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void setSala(Sala sala) {
        this.sala = sala;
    }
    
    public void setButacas(List<Butaca> butacas) {
        this.butacasSeleccionadas = butacas;
        cargarDatosConfirmacion();
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    
    private void cargarDatosConfirmacion() {

        if (lblPelicula != null && sala != null && butacasSeleccionadas != null && !butacasSeleccionadas.isEmpty()) {
            

            this.precioTotal = butacasSeleccionadas.size() * PRECIO_BUTACA_UNITARIO;
            

            String ubicaciones = butacasSeleccionadas.stream()
                                                    .map(Butaca::getUbicacion)
                                                    .collect(Collectors.joining(", "));
            
            lblPelicula.setText(sala.getPelicula());
            lblSala.setText(String.valueOf(sala.getNumero()));
            lblButaca.setText(ubicaciones); 
            lblPrecio.setText(String.format("$%.2f", precioTotal)); 
        }
    }

    @FXML
    private void btnConfirmar() {
        if (butacasSeleccionadas == null || butacasSeleccionadas.isEmpty()) {
            mostrarAlerta("Error", "No se pudo completar la compra. No hay butacas seleccionadas.", AlertType.ERROR);
            return;
        }


        for (Butaca butaca : butacasSeleccionadas) {
            // Marcar la butaca como ocupada
            butaca.setOcupada(true);

            // Crear una Entrada para cada butaca
            Entrada nuevaEntrada = new Entrada(cliente, sala, butaca, PRECIO_BUTACA_UNITARIO); // Usamos el precio unitario

            // Añadir la entrada al modelo Cine
            cine.agregarEntrada(nuevaEntrada); 
        }
        

        mostrarAlerta("Éxito", 
                      "¡Compra de " + butacasSeleccionadas.size() + 
                      " butaca(s) realizada con éxito!\nTotal Pagado: " + 
                      String.format("$%.2f", precioTotal), 
                      AlertType.INFORMATION);


        if (stage != null) {
            stage.close(); 
        }
    }
    
    @FXML
    private void btnCancelar() {
        // Cierra la ventana de confirmación
        if (stage != null) {
            stage.close();
        }
    }
    
    private void mostrarAlerta(String titulo, String mensaje, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}