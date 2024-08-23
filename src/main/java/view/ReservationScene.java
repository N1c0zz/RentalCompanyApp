package src.main.java.view;

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
        numeroVeicoliRichiesti.setItems(FXCollections.observableArrayList(
            1,
            2
        ));
        numeroVeicoliRichiesti.setEditable(false);
        Button registraPrenotazione = new Button("Registra prenotazione");
        TextField response = new TextField();
        response.setPromptText("Response");
        response.setEditable(false);
        response.setMinSize(200, 200);

        vbox.getChildren().addAll(cliente, dataInizio, dataFine, veicoloRichiesto,
                                    numeroVeicoliRichiesti, registraPrenotazione, response);
        registraPrenotazione.setOnAction(e -> {
            String temp1 = new String();
            if(numeroVeicoliRichiesti.getValue() == 1){
                temp1 = prenotazione.registraPrenotazione(cliente.getText(), dataInizio.getText(), dataFine.getText(),
                                                            Integer.parseInt(veicoloRichiesto.getText()));
            } else {
                temp1 = prenotazione.registraDoppiaPrenotazione(cliente.getText(), dataInizio.getText(), dataFine.getText(),
                                                        Integer.parseInt(veicoloRichiesto.getText()));                          
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
            String temp1 = new String();
            
            temp1 = prenotazione.modificaPrenotazione(dataInizio.getText(), dataFine.getText(),
                                                     Integer.parseInt(codPrenotazione.getText()),
                                                     Integer.parseInt(idVeicolo.getText()));
            response.setText(temp1);
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
            String temp1 = new String();
            
            temp1 = prenotazione.eliminaPrenotazione(Integer.parseInt(codPrenotazione.getText()));

            response.setText(temp1);
        });

        mainLayout.setCenter(vbox);
    }
    
}
