package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

/**
 * @author mkjodhani
 * @version 2.0
 * @project Tenant Management System
 * @since 15/03/23
 */
public class GUI extends Application implements Observer {
    Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/screen/Home.fxml"));
        Scene scene = new Scene(root,700,700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tenant Management System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("sadsdcsdsc");
        stage.show();
    }
}
