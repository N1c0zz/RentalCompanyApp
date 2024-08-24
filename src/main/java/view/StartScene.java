package src.main.java.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import src.main.java.application.RentalCompanyApp;

public class StartScene {
    private final RentalCompanyApp app;

    public StartScene(RentalCompanyApp app) {
        this.app = app;
    }

    public Scene createStartScene() {
        // Creazione dei campi di input per nome utente e password
        Label userLabel = new Label("Nome utente:");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Inserisci nome utente");

        Label passLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Inserisci password");

        Label messageLabel = new Label(); // Per mostrare eventuali messaggi di errore

        Button loginButton = new Button("Accedi");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            
            // Simula la verifica delle credenziali (da sostituire con la logica reale)
            if (authenticate(username, password)) {
                app.getPrimaryStage().setScene(new ClientScene(app).createClientScene());
            } else {
                messageLabel.setText("Credenziali non valide. Riprova.");
            }
        });

        VBox vbox = new VBox(10, userLabel, usernameField, passLabel, passwordField, loginButton, messageLabel);
        vbox.setAlignment(Pos.CENTER);

        Scene startScene = new Scene(vbox, 800, 600);
        return startScene;
    }

    // Metodo di esempio per la verifica delle credenziali
    private boolean authenticate(String username, String password) {
        // Questo è solo un esempio; sostituisci con la tua logica di autenticazione.
        return "admin".equals(username) && "password".equals(password);
    }
}

