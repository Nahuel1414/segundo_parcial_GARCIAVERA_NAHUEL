package examen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Examen extends Application{

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../views/view.fxml"));
        
        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        
        stage.setTitle("FX10");
        stage.show();
    }
}

