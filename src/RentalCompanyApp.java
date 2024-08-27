package src;

import javafx.application.*;
import javafx.stage.Stage;
import src.view.StartScene;

public class RentalCompanyApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        
        primaryStage.setFullScreen(true);
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
