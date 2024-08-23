package src.main.java.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import src.main.java.application.RentalCompanyApp;

public class StartScene {
    private final RentalCompanyApp app;

    public StartScene(RentalCompanyApp app) {
        this.app = app;
    }

    public Scene createStartScene() {
        
        Button startButton = new Button("Accedi all'applicazione");
        startButton.setOnAction(e -> app.getPrimaryStage().setScene(new ClientScene(app).createClientScene()));

        
        VBox vbox = new VBox(20, startButton);
        vbox.setAlignment(Pos.CENTER);

        
        Scene startScene = new Scene(vbox, 800, 600);
        return startScene;
    }
    
}
