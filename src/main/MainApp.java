package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    // Load the FXML file
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/Gui/Main.fxml"));

    // Load the FXML file
    AnchorPane root = loader.load();

    // Create the scene
    Scene scene = new Scene(root);
    primaryStage.setTitle("Pet Shop Application");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
