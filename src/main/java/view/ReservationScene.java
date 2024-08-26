package src.main.java.view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import src.main.java.application.RentalCompanyApp;
import src.main.java.controller.DAOclasses.PrenotazioneDAO;
import src.main.java.controller.DBHandler.DataBaseHandler;

public class ReservationScene {

    private RentalCompanyApp app;
    private DataBaseHandler dbHandler = new DataBaseHandler();
    private PrenotazioneDAO prenotazione = new PrenotazioneDAO(dbHandler);

    public ReservationScene (RentalCompanyApp app) {
        this.app = app;
    }

    public Scene createReservationScene() {
        BorderPane mainLayout = new BorderPane();

        HBox topMenu = new TopMenu(app).createTopMenu(mainLayout);

        VBox sideMenu = new VBox(10);
        sideMenu.setPadding(new Insets(10));
        Button operazione1Button = new Button("Registrare una nuova prenotazione");
        Button operazione2Button = new Button("Modifica una prenotazione");
        Button operazione3Button = new Button("Cancella una prenotazione");

        operazione1Button.setOnAction(e -> {
            registraPrenotazione(mainLayout);
            app.getPrimaryStage().setFullScreen(true);
            app.getPrimaryStage().setFullScreenExitKeyCombination(KeyCombination.keyCombination("ESC"));});
        operazione2Button.setOnAction(e -> {
            modificaPrenotazione(mainLayout);
            app.getPrimaryStage().setFullScreen(true);
            app.getPrimaryStage().setFullScreenExitKeyCombination(KeyCombination.keyCombination("ESC"));});
        operazione3Button.setOnAction(e -> {
            cancellaPrenotazione(mainLayout);
            app.getPrimaryStage().setFullScreen(true);
            app.getPrimaryStage().setFullScreenExitKeyCombination(KeyCombination.keyCombination("ESC"));});

        sideMenu.getChildren().addAll(operazione1Button, operazione2Button, operazione3Button);

        mainLayout.setTop(topMenu);
        mainLayout.setLeft(sideMenu);

        Label homeLabel = new Label("Sezione Prenotazioni");
        mainLayout.setCenter(homeLabel);

        return new Scene(mainLayout, 800, 600);
    }

    private void registraPrenotazione(BorderPane mainLayout) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(new Label("Registra una nuova prenotazione"));

        TextField cliente = new TextField();
        cliente.setPromptText("Inserisci il codice fiscale del cliente");

        TextField dataInizio = new TextField();
        dataInizio.setPromptText("Inserisci la data di inizio prenotazione (formato anno-mese-giorno)");

        TextField dataFine = new TextField();
        dataFine.setPromptText("Inserisci la data di fine prenotazione (formato anno-mese-giorno)");

        TextField veicoloRichiesto = new TextField();
        veicoloRichiesto.setPromptText("Inserisci l'id del veicolo richiesto");

        ComboBox<Integer> numeroVeicoliRichiesti = new ComboBox<>();
        numeroVeicoliRichiesti.setPromptText("Scegli il numero di veicoli richiesti");
        numeroVeicoliRichiesti.setItems(FXCollections.observableArrayList(1, 2));
        numeroVeicoliRichiesti.setEditable(false);

        Button registraPrenotazione = new Button("Registra prenotazione");

        TextField response = new TextField();
        response.setPromptText("Response");
        response.setEditable(false);
        response.setMinSize(200, 200);

        vbox.getChildren().addAll(cliente, dataInizio, dataFine, veicoloRichiesto,
                                numeroVeicoliRichiesti, registraPrenotazione, response);

        registraPrenotazione.setOnAction(e -> {
            String codiceFiscale = cliente.getText().trim();
            String startDateStr = dataInizio.getText().trim();
            String endDateStr = dataFine.getText().trim();
            String veicoloStr = veicoloRichiesto.getText().trim();

            if (codiceFiscale.isEmpty()) {
                response.setText("Errore: Il codice fiscale non può essere vuoto.");
                return;
            }

            LocalDate dataInizioParsed, dataFineParsed;
            try {
                dataInizioParsed = LocalDate.parse(startDateStr);
                dataFineParsed = LocalDate.parse(endDateStr);

                if (dataFineParsed.isBefore(dataInizioParsed)) {
                    response.setText("Errore: La data di fine prenotazione non può essere precedente alla data di inizio.");
                    return;
                }
            } catch (DateTimeParseException ex) {
                response.setText("Errore: Le date devono essere nel formato 'anno-mese-giorno'.");
                return;
            }

            int veicoloId;
            try {
                veicoloId = Integer.parseInt(veicoloStr);
            } catch (NumberFormatException ex) {
                response.setText("Errore: L'id del veicolo deve essere un numero intero valido.");
                return;
            }

            Integer numVeicoli = numeroVeicoliRichiesti.getValue();
            if (numVeicoli == null) {
                response.setText("Errore: Seleziona il numero di veicoli richiesti.");
                return;
            }

            String temp1;
            if (numVeicoli == 1) {
                temp1 = prenotazione.registraPrenotazione(codiceFiscale, startDateStr, endDateStr, veicoloId);
            } else {
                temp1 = prenotazione.registraDoppiaPrenotazione(codiceFiscale, startDateStr, endDateStr, veicoloId);
            }
            response.setText(temp1);
        });

        mainLayout.setCenter(vbox);
    }


    private void modificaPrenotazione(BorderPane mainLayout) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(new Label("Modifica una prenotazione"));
    
        TextField dataInizio = new TextField();
        dataInizio.setPromptText("Inserisci la nuova data di inizio prenotazione (formato anno-mese-giorno)");
    
        TextField dataFine = new TextField();
        dataFine.setPromptText("Inserisci la nuova data di fine prenotazione (formato anno-mese-giorno)");
    
        TextField codPrenotazione = new TextField();
        codPrenotazione.setPromptText("Inserisci il codice della prenotazione da modificare");
    
        TextField idVeicolo = new TextField();
        idVeicolo.setPromptText("Inserisci l'id del nuovo veicolo");
    
        Button modificaPrenotazione = new Button("Modifica prenotazione");
    
        TextField response = new TextField();
        response.setPromptText("Response");
        response.setEditable(false);
        response.setMinSize(200, 200);
    
        vbox.getChildren().addAll(dataInizio, dataFine, codPrenotazione, idVeicolo,
                                  modificaPrenotazione, response);
    
        modificaPrenotazione.setOnAction(e -> {
            String startDateStr = dataInizio.getText().trim();
            String endDateStr = dataFine.getText().trim();
            String codPrenotazioneStr = codPrenotazione.getText().trim();
            String idVeicoloStr = idVeicolo.getText().trim();
    
            LocalDate dataInizioParsed, dataFineParsed;
            try {
                dataInizioParsed = LocalDate.parse(startDateStr);
                dataFineParsed = LocalDate.parse(endDateStr);
    
                if (dataFineParsed.isBefore(dataInizioParsed)) {
                    response.setText("Errore: La data di fine prenotazione non può essere precedente alla data di inizio.");
                    return;
                }
            } catch (DateTimeParseException ex) {
                response.setText("Errore: Le date devono essere nel formato 'anno-mese-giorno'.");
                return;
            }
    
            int codicePrenotazione;
            try {
                codicePrenotazione = Integer.parseInt(codPrenotazioneStr);
            } catch (NumberFormatException ex) {
                response.setText("Errore: Il codice della prenotazione deve essere un numero intero valido.");
                return;
            }
    
            int veicoloId;
            try {
                veicoloId = Integer.parseInt(idVeicoloStr);
            } catch (NumberFormatException ex) {
                response.setText("Errore: L'id del veicolo deve essere un numero intero valido.");
                return;
            }
    
            String risultato = prenotazione.modificaPrenotazione(startDateStr, endDateStr, codicePrenotazione, veicoloId);
            response.setText(risultato);
        });
    
        mainLayout.setCenter(vbox);
    }
    
    private void cancellaPrenotazione(BorderPane mainLayout) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(new Label("Cancella una prenotazione"));
    
        TextField codPrenotazione = new TextField();
        codPrenotazione.setPromptText("Inserisci il codice della prenotazione da cancellare");
    
        Button cancellaPrenotazione = new Button("Cancella prenotazione");
    
        TextField response = new TextField();
        response.setPromptText("Response");
        response.setEditable(false);
        response.setMinSize(200, 200);
    
        vbox.getChildren().addAll(codPrenotazione, cancellaPrenotazione, response);
    
        cancellaPrenotazione.setOnAction(e -> {
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
    
            String risultato = prenotazione.eliminaPrenotazione(codicePrenotazione);
            response.setText(risultato);
        });
    
        mainLayout.setCenter(vbox);
    }
}
