package cine;

import cine.modelo.Cine;
import cine.modelo.Sala;
import cine.persistencia.PersistenciaDatos;
import cine.controlador.ViewLoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Cine cineTemp = PersistenciaDatos.cargarCine();
        final Cine cine = (cineTemp == null) ? crearCineInicial() : cineTemp;
        
        // Cargar vista login
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vista/ViewLogin.fxml"));
        Parent root = loader.load();
        ViewLoginController controller = loader.getController();
        controller.setCine(cine);
        controller.setStage(primaryStage);
        
        Scene scene = new Scene(root);
        primaryStage.setTitle("Portal Cinemas");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        primaryStage.setOnCloseRequest(e -> {
            PersistenciaDatos.guardarCine(cine);
        });
    }
    
    private Cine crearCineInicial() {
        Cine cine = new Cine("Portal Cinemas");
        
        // Crear salas
        cine.agregarSala(new Sala(1, "Avatar 3"));
        cine.agregarSala(new Sala(2, "Oppenheimer"));
        cine.agregarSala(new Sala(3, "Barbie"));
        cine.agregarSala(new Sala(4, "Dune 2"));
        cine.agregarSala(new Sala(5, "Insidious"));
        cine.agregarSala(new Sala(6, "Cars"));
        
        return cine;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}