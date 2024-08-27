package src.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import src.RentalCompanyApp;
import src.controller.DAOclasses.SanzioneDAO;
import src.controller.DBHandler.DataBaseHandler;

public class SanctionScene {

    private RentalCompanyApp app;
    private DataBaseHandler dbHandler = new DataBaseHandler();
    private SanzioneDAO sanzione = new SanzioneDAO(dbHandler);

    public SanctionScene(RentalCompanyApp app){
        this.app = app;
    }

    public Scene createSanctionScene() {
        BorderPane mainLayout = new BorderPane();

        HBox topMenu = new TopMenu(app).createTopMenu(mainLayout);

        VBox sideMenu = new VBox(10);
        sideMenu.setPadding(new Insets(10));
        Button operazione1Button = new Button("Emettere una nuova sanzione");

        operazione1Button.setOnAction(e -> {
            emettiSanzione(mainLayout);
            app.getPrimaryStage().setFullScreen(true);
            app.getPrimaryStage().setFullScreenExitKeyCombination(KeyCombination.keyCombination("ESC"));});

        sideMenu.getChildren().addAll(operazione1Button);

        mainLayout.setTop(topMenu);
        mainLayout.setLeft(sideMenu);

        Label homeLabel = new Label("Sezione Sanzioni");
        mainLayout.setCenter(homeLabel);

        return new Scene(mainLayout, 800, 600);
    }

    private void emettiSanzione(BorderPane mainLayout) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(new Label("Emettere una nuova sanzione"));
    
        TextField codPrenotazione = new TextField();
        codPrenotazione.setPromptText("Inserisci il codice della prenotazione associata");
    
        TextField motivazione = new TextField();
        motivazione.setPromptText("Inserisci la motivazione della sanzione");
    
        TextField costoApplicato = new TextField();
        costoApplicato.setPromptText("Inserisci il costo aggiuntivo per la sanzione");
    
        Button generaSanzione = new Button("Genera sanzione");
    
        TextField response = new TextField();
        response.setPromptText("Response");
        response.setEditable(false);
        response.setMinSize(200, 200);
    
        vbox.getChildren().addAll(codPrenotazione, motivazione, costoApplicato, generaSanzione, response);
    
        generaSanzione.setOnAction(e -> {
            String codPrenotazioneStr = codPrenotazione.getText().trim();
            if (codPrenotazioneStr.isEmpty()) {
                response.setText("Errore: Il codice della prenotazione non può essere vuoto.");
                return;
            }
            
            int codicePrenotazione;
            try {
                codicePrenotazione = Integer.parseInt(codPrenotazioneStr);
            } catch (NumberFormatException ex) {
                response.setText("Errore: Il codice della prenotazione deve essere un numero intero valido.");
                return;
            }
    
            String motivazioneStr = motivazione.getText().trim();
            if (motivazioneStr.isEmpty()) {
                response.setText("Errore: La motivazione della sanzione non può essere vuota.");
                return;
            }
    
            String costoApplicatoStr = costoApplicato.getText().trim();
            float costo;
            try {
                costo = Float.parseFloat(costoApplicatoStr);
                if (costo <= 0) {
                    response.setText("Errore: Il costo della sanzione deve essere un valore positivo.");
                    return;
                }
            } catch (NumberFormatException ex) {
                response.setText("Errore: Il costo della sanzione deve essere un numero valido.");
                return;
            }
    
            String risultato = sanzione.emettiSanzione(codicePrenotazione, motivazioneStr, costo);
            response.setText(risultato);
        });
    
        mainLayout.setCenter(vbox);
    }
}
