package src.main.java.application;

import javafx.application.Application;
import javafx.stage.Stage;
import src.main.java.view.StartScene;


public class RentalCompanyApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Imposta la scena iniziale
        primaryStage.setScene(new StartScene(this).createStartScene());
        primaryStage.setTitle("Gestione Noleggio Auto");
        primaryStage.show();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {

        launch(args);
    }

}
