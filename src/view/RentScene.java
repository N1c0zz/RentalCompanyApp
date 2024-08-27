package src.main.java.application.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import src.main.java.application.RentalCompanyApp;
import src.main.java.application.controller.DAOclasses.NoleggioDAO;
import src.main.java.application.controller.DBHandler.DataBaseHandler;

public class RentScene {

    private RentalCompanyApp app;
    private DataBaseHandler dbHandler = new DataBaseHandler();
    private NoleggioDAO noleggio = new NoleggioDAO(dbHandler);
    
    public RentScene(RentalCompanyApp app) {
        this.app = app;
    }

    public Scene createRentScene() {
        BorderPane mainLayout = new BorderPane();

        HBox topMenu = new TopMenu(app).createTopMenu(mainLayout);
        VBox sideMenu = new VBox(10);
        sideMenu.setPadding(new Insets(10));
        Button operazione1Button = new Button("Attivare un nuovo noleggio");
        Button operazione2Button = new Button("Termina un noleggio");

        operazione1Button.setOnAction(e -> {
            attivaNoleggio(mainLayout);
            app.getPrimaryStage().setFullScreen(true);
            app.getPrimaryStage().setFullScreenExitKeyCombination(KeyCombination.keyCombination("ESC"));});
        operazione2Button.setOnAction(e -> {
            terminaNoleggio(mainLayout);
            app.getPrimaryStage().setFullScreen(true);
            app.getPrimaryStage().setFullScreenExitKeyCombination(KeyCombination.keyCombination("ESC"));});

        sideMenu.getChildren().addAll(operazione1Button, operazione2Button);

        mainLayout.setTop(topMenu);
        mainLayout.setLeft(sideMenu);
        Label homeLabel = new Label("Sezione Noleggi");
        mainLayout.setCenter(homeLabel);

        return new Scene(mainLayout, 800, 600);
    }

    private void attivaNoleggio(BorderPane mainLayout) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(new Label("Attiva un nuovo noleggio per una prenotazione"));
    
        TextField codPrenotazione = new TextField();
        codPrenotazione.setPromptText("Inserisci il codice della prenotazione");
    
        Button attivaNoleggio = new Button("Attiva noleggio");
    
        TextField response = new TextField();
        response.setPromptText("Response");
        response.setEditable(false);
        response.setMinSize(200, 200);
    
        vbox.getChildren().addAll(codPrenotazione, attivaNoleggio, response);
    
        attivaNoleggio.setOnAction(e -> {
            String codicePrenotazioneInput = codPrenotazione.getText().trim();
    
            if (codicePrenotazioneInput.isEmpty()) {
                response.setText("Errore: Il codice della prenotazione non può essere vuoto.");
                return;
            }
    
            int codicePrenotazione;
            try {
                codicePrenotazione = Integer.parseInt(codicePrenotazioneInput);
            } catch (NumberFormatException ex) {
                response.setText("Errore: Il codice della prenotazione deve essere un numero intero valido.");
                return;
            }
    
            String risultato = noleggio.attivaNoleggio(codicePrenotazione);
            response.setText(risultato);
        });
    
        mainLayout.setCenter(vbox);
    }
    

    private void terminaNoleggio(BorderPane mainLayout) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(new Label("Termina un noleggio"));
    
        TextField codNoleggio = new TextField();
        codNoleggio.setPromptText("Inserisci il codice del noleggio da terminare");
    
        Button terminaNoleggio = new Button("Termina noleggio");
    
        TextField response = new TextField();
        response.setPromptText("Response");
        response.setEditable(false);
        response.setMinSize(200, 200);
    
        vbox.getChildren().addAll(codNoleggio, terminaNoleggio, response);
    
        terminaNoleggio.setOnAction(e -> {
            String codiceNoleggioInput = codNoleggio.getText().trim();
    
            if (codiceNoleggioInput.isEmpty()) {
                response.setText("Errore: Il codice del noleggio non può essere vuoto.");
                return;
            }
    
            int codiceNoleggio;
            try {
                codiceNoleggio = Integer.parseInt(codiceNoleggioInput);
            } catch (NumberFormatException ex) {
                response.setText("Errore: Il codice del noleggio deve essere un numero intero valido.");
                return;
            }
    
            String risultato = noleggio.terminaNoleggio(codiceNoleggio);
            response.setText(risultato);
        });
    
        mainLayout.setCenter(vbox);
    }
    
}
