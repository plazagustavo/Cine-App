 package cine.controlador;

import cine.modelo.Butaca;
import cine.modelo.Cine;
import cine.modelo.Cliente;
import cine.modelo.Sala;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;  
import java.util.List;      //
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ViewButacasController {
    @FXML
    private GridPane gridButacas;
    @FXML
    private VBox mainVBox;
    
    private Cine cine;
    private Cliente cliente;
    private Sala sala;

    private List<Butaca> butacasSeleccionadas = new ArrayList<>(); 
    private Stage stage;
    
    // Colores para manejo de estilos
    private static final String COLOR_DISPONIBLE = "-fx-background-color: #51CF66; -fx-text-fill: white; -fx-font-weight: bold;";
    private static final String COLOR_SELECCIONADO = "-fx-background-color: #0000FF; -fx-text-fill: white; -fx-font-weight: bold;";
    private static final String COLOR_OCUPADA = "-fx-background-color: #FF2400; -fx-text-fill: white; -fx-font-weight: bold;";
    
    public void setCine(Cine cine) {
        this.cine = cine;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void setSala(Sala sala) {
        this.sala = sala;
        dibujarButacas();
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    private void dibujarButacas() {
        gridButacas.setHgap(10);
        gridButacas.setVgap(10);
        gridButacas.setAlignment(Pos.CENTER);
        
        Butaca[][] butacas = sala.getButacas();
        for (int i = 0; i < sala.getFilas(); i++) {
            for (int j = 0; j < sala.getColumnas(); j++) {
                Butaca butaca = butacas[i][j];
                Button btn = crearBotonButaca(butaca);
                gridButacas.add(btn, j, i);
            }
        }
    }
    
    private Button crearBotonButaca(Butaca butaca) {
        Button btn = new Button(butaca.getUbicacion());
        btn.setPrefSize(50, 50);
        
        if (butaca.isOcupada()) {
            btn.setStyle(COLOR_OCUPADA);
            btn.setDisable(true);
        } else {
            btn.setStyle(COLOR_DISPONIBLE);
            btn.setOnAction(e -> seleccionarButaca(butaca, btn));
        }
        
        return btn;
    }
    
    private void seleccionarButaca(Butaca butaca, Button btn) {
        if (butacasSeleccionadas.contains(butaca)) {
            // Deseleccionar: Si ya estaba seleccionada, la quitamos
            butacasSeleccionadas.remove(butaca);
            btn.setStyle(COLOR_DISPONIBLE);
            mostrarAlerta("Deseleccionado", "Butaca " + butaca.getUbicacion() + " quitada de la selección. Butacas elegidas: " + butacasSeleccionadas.size());
        } else {
            // Seleccionar: Si no estaba seleccionada, la agregamos
            butacasSeleccionadas.add(butaca);
            btn.setStyle(COLOR_SELECCIONADO);
            mostrarAlerta("Seleccionado", "Butaca " + butaca.getUbicacion() + " seleccionada. Butacas elegidas: " + butacasSeleccionadas.size());
        }
    }
    
    @FXML
    private void btnComprar() {
        if (butacasSeleccionadas.isEmpty()) { 
            mostrarAlerta("Error", "Debes seleccionar al menos una butaca.");
            return;
        }
        
        try {
            abrirConfirmacion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void abrirConfirmacion() throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/cine/vista/ViewConfirmacion.fxml"));
        Parent root = loader.load();

        ViewConfirmacionController controller = loader.getController();
        controller.setCine(cine);
        controller.setCliente(cliente);
        controller.setSala(sala);
        controller.setButacas(butacasSeleccionadas); 

        Stage ventana = new Stage();
        ventana.setTitle("Confirmar Compra");
        ventana.setScene(new Scene(root, 500, 400));

        controller.setStage(ventana);

        ventana.show();


        if (stage != null) {
            stage.close();
        }
    }
    
    @FXML
    private void btnCancelar() {
        stage.close();
    }
    
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}