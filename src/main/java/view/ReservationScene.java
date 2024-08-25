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

        // Menu superiore
        HBox topMenu = new TopMenu(app).createTopMenu(mainLayout);

        // Menu laterale per le sotto-operazioni
        VBox sideMenu = new VBox(10);
        sideMenu.setPadding(new Insets(10));
        Button operazione1Button = new Button("Registrare una nuova prenotazione");
        Button operazione2Button = new Button("Modifica una prenotazione");
        Button operazione3Button = new Button("Cancella una prenotazione");

        // Imposta le azioni per i bottoni
        operazione1Button.setOnAction(e -> reservationOperazione1(mainLayout));
        operazione2Button.setOnAction(e -> reservationOperazione2(mainLayout));
        operazione3Button.setOnAction(e -> reservationOperazione3(mainLayout));

        sideMenu.getChildren().addAll(operazione1Button, operazione2Button, operazione3Button);

        mainLayout.setTop(topMenu);
        mainLayout.setLeft(sideMenu);

        // Contenuto principale iniziale
        Label homeLabel = new Label("Sezione Prenotazioni");
        mainLayout.setCenter(homeLabel);

        return new Scene(mainLayout, 800, 600);
    }

    // Metodo per mostrare la vista dell'operazione 1
private void reservationOperazione1(BorderPane mainLayout) {
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

        // Controllo sul codice fiscale
        if (codiceFiscale.isEmpty()) {
            response.setText("Errore: Il codice fiscale non può essere vuoto.");
            return;
        }

        // Controllo sulle date
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

        // Controllo sull'id del veicolo richiesto
        int veicoloId;
        try {
            veicoloId = Integer.parseInt(veicoloStr);
        } catch (NumberFormatException ex) {
            response.setText("Errore: L'id del veicolo deve essere un numero intero valido.");
            return;
        }

        // Controllo sul numero di veicoli richiesti
        Integer numVeicoli = numeroVeicoliRichiesti.getValue();
        if (numVeicoli == null) {
            response.setText("Errore: Seleziona il numero di veicoli richiesti.");
            return;
        }

        // Se tutti i controlli sono passati, registra la prenotazione
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


    // Metodo per mostrare la vista dell'operazione 2
    private void reservationOperazione2(BorderPane mainLayout) {
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
    
            // Controllo sulle date
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
    
            // Controllo sul codice della prenotazione
            int codicePrenotazione;
            try {
                codicePrenotazione = Integer.parseInt(codPrenotazioneStr);
            } catch (NumberFormatException ex) {
                response.setText("Errore: Il codice della prenotazione deve essere un numero intero valido.");
                return;
            }
    
            // Controllo sull'id del veicolo
            int veicoloId;
            try {
                veicoloId = Integer.parseInt(idVeicoloStr);
            } catch (NumberFormatException ex) {
                response.setText("Errore: L'id del veicolo deve essere un numero intero valido.");
                return;
            }
    
            // Se tutti i controlli sono passati, modifica la prenotazione
            String risultato = prenotazione.modificaPrenotazione(startDateStr, endDateStr, codicePrenotazione, veicoloId);
            response.setText(risultato);
        });
    
        mainLayout.setCenter(vbox);
    }
    

    // Metodo per mostrare la vista dell'operazione 3
    private void reservationOperazione3(BorderPane mainLayout) {
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
    
            // Controllo sul codice della prenotazione
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
    
            // Se il controllo è passato, elimina la prenotazione
            String risultato = prenotazione.eliminaPrenotazione(codicePrenotazione);
            response.setText(risultato);
        });
    
        mainLayout.setCenter(vbox);
    }
    
    
}
