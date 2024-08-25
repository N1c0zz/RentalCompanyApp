package src.main.java.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import src.main.java.application.RentalCompanyApp;
import src.main.java.controller.DAOclasses.ClienteDAO;
import src.main.java.controller.DAOclasses.PersonaDAO;
import src.main.java.controller.DBHandler.DataBaseHandler;

public class ClientScene {

    private final RentalCompanyApp app;
    private final DataBaseHandler dbHandler = new DataBaseHandler();
    private final ClienteDAO cliente = new ClienteDAO(dbHandler);
    private final PersonaDAO persona = new PersonaDAO(dbHandler);

    public ClientScene(RentalCompanyApp app) {
        this.app = app;
    }

    public Scene createClientScene() {
        BorderPane mainLayout = new BorderPane();

        // Menu superiore
        HBox topMenu = new TopMenu(app).createTopMenu(mainLayout);

        // Menu laterale per le sotto-operazioni
        VBox sideMenu = new VBox(10);
        sideMenu.setPadding(new Insets(10));
        Button operazione1Button = new Button("Registrare un nuovo cliente");
        Button operazione2Button = new Button("Visualizza lo storico noleggi di un cliente");
        Button operazione3Button = new Button("Visualizza i 10 migliori clienti");

        // Imposta le azioni per i bottoni
        operazione1Button.setOnAction(e -> clientOperazione1(mainLayout));
        operazione2Button.setOnAction(e -> clientOperazione2(mainLayout));
        operazione3Button.setOnAction(e -> clientOperazione3(mainLayout));

        sideMenu.getChildren().addAll(operazione1Button, operazione2Button, operazione3Button);

        mainLayout.setTop(topMenu);
        mainLayout.setLeft(sideMenu);

        // Contenuto principale iniziale
        Label homeLabel = new Label("Sezione Clienti");
        mainLayout.setCenter(homeLabel);

        return new Scene(mainLayout, 800, 600);
    }

    // Metodo per mostrare la vista dell'operazione 1
    private void clientOperazione1(BorderPane mainLayout) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(new Label("Registra qui un nuovo cliente"));
        
        // Creazione dei campi di testo
        TextField codiceFiscale = new TextField();
        codiceFiscale.setPromptText("Inserisci il codice fiscale");
        TextField nome = new TextField();
        nome.setPromptText("Inserisci il nome");
        TextField cognome = new TextField();
        cognome.setPromptText("Inserisci il cognome");
        TextField indirizzo_via = new TextField();
        indirizzo_via.setPromptText("Inserisci la via di residenza");
        TextField indirizzo_numeroCivico = new TextField();
        indirizzo_numeroCivico.setPromptText("Inserisci il numero civico di residenza");
        TextField indirizzo_città = new TextField();
        indirizzo_città.setPromptText("Inserisci la città di residenza");
        TextField indirizzo_CAP = new TextField();
        indirizzo_CAP.setPromptText("Inserisci il CAP di residenza");
        TextField numeroDiTelefono = new TextField();
        numeroDiTelefono.setPromptText("Inserisci il numero di telefono");
        TextField indirizzoEmail = new TextField();
        indirizzoEmail.setPromptText("Inserisci indirizzo email (facoltativo)");
        TextField numeroPatente = new TextField();
        numeroPatente.setPromptText("Inserisci il numero della patente di guida");
        TextField fatturazione_via = new TextField();
        fatturazione_via.setPromptText("Inserisci la via dell'indirizzo di fatturazione");
        TextField fatturazione_numeroCivico = new TextField();
        fatturazione_numeroCivico.setPromptText("Inserisci il numero civico dell'indirizzo di fatturazione");
        TextField fatturazione_città = new TextField();
        fatturazione_città.setPromptText("Inserisci la città dell'indirizzo di fatturazione");
        TextField fatturazione_CAP = new TextField();
        fatturazione_CAP.setPromptText("Inserisci il CAP dell'indirizzo di fatturazione");
        
        Button aggiungiCliente = new Button("Aggiungi Nuovo Cliente");
        TextField response = new TextField();
        response.setPromptText("Response");
        response.setEditable(false);
        response.setMinSize(300, 300);
    
        vbox.getChildren().addAll(codiceFiscale, nome, cognome, indirizzo_via,
                indirizzo_numeroCivico, indirizzo_città, indirizzo_CAP, numeroDiTelefono,
                indirizzoEmail, numeroPatente, fatturazione_via, fatturazione_numeroCivico,
                fatturazione_città, fatturazione_CAP, aggiungiCliente, response);
    
        aggiungiCliente.setOnAction(e -> {
            StringBuilder errors = new StringBuilder();
    
            // Validazione campi obbligatori
            if (codiceFiscale.getText().isEmpty()) {
                errors.append("Codice fiscale è obbligatorio.\n");
            }
            if (nome.getText().isEmpty()) {
                errors.append("Nome è obbligatorio.\n");
            }
            if (cognome.getText().isEmpty()) {
                errors.append("Cognome è obbligatorio.\n");
            }
            if (indirizzo_via.getText().isEmpty()) {
                errors.append("Indirizzo (via) è obbligatorio.\n");
            }
            if (indirizzo_numeroCivico.getText().isEmpty()) {
                errors.append("Indirizzo (numero civico) è obbligatorio.\n");
            }
            if (indirizzo_città.getText().isEmpty()) {
                errors.append("Città di residenza è obbligatoria.\n");
            }
            if (indirizzo_CAP.getText().isEmpty()) {
                errors.append("CAP di residenza è obbligatorio.\n");
            }
            if (numeroDiTelefono.getText().isEmpty()) {
                errors.append("Numero di telefono è obbligatorio.\n");
            }
            if (numeroPatente.getText().isEmpty()) {
                errors.append("Numero di patente è obbligatorio.\n");
            }
            if (fatturazione_via.getText().isEmpty()) {
                errors.append("Indirizzo di fatturazione (via) è obbligatorio.\n");
            }
            if (fatturazione_numeroCivico.getText().isEmpty()) {
                errors.append("Indirizzo di fatturazione (numero civico) è obbligatorio.\n");
            }
            if (fatturazione_città.getText().isEmpty()) {
                errors.append("Città di fatturazione è obbligatoria.\n");
            }
            if (fatturazione_CAP.getText().isEmpty()) {
                errors.append("CAP di fatturazione è obbligatorio.\n");
            }
    
            // Validazione formati
            if (!indirizzo_numeroCivico.getText().matches("\\d+")) {
                errors.append("Numero civico deve essere un numero.\n");
            }
            if (!fatturazione_numeroCivico.getText().matches("\\d+")) {
                errors.append("Numero civico di fatturazione deve essere un numero.\n");
            }
            if (!indirizzo_CAP.getText().matches("\\d{5}")) {
                errors.append("CAP di residenza non valido. Deve essere un numero di 5 cifre.\n");
            }
            if (!fatturazione_CAP.getText().matches("\\d{5}")) {
                errors.append("CAP di fatturazione non valido. Deve essere un numero di 5 cifre.\n");
            }
            if (!numeroDiTelefono.getText().matches("\\d{10}")) {
                errors.append("Numero di telefono non valido. Deve essere un numero di 10 cifre.\n");
            }
            if (!indirizzoEmail.getText().isEmpty() && !indirizzoEmail.getText().matches("^(.+)@(.+)$")) {
                errors.append("Indirizzo email non valido.\n");
            }
    
            if (errors.length() > 0) {
                response.setText("Errori:\n" + errors.toString());
                return;
            }
    
            // Se tutto è valido, si procede con l'aggiunta del cliente
            String temp1 = persona.addPerson(
                    codiceFiscale.getText(), nome.getText(), cognome.getText(),
                    indirizzo_via.getText(), Integer.parseInt(indirizzo_numeroCivico.getText()),
                    indirizzo_città.getText(), indirizzo_CAP.getText(),
                    numeroDiTelefono.getText(), indirizzoEmail.getText());
    
            String temp2 = cliente.addClient(
                    codiceFiscale.getText(), numeroPatente.getText(),
                    fatturazione_via.getText(), Integer.parseInt(fatturazione_numeroCivico.getText()),
                    fatturazione_città.getText(), fatturazione_CAP.getText());
            if(temp1.equals(temp2)){
                response.setText("Operazione riuscita.");
            } else {
                response.setText("Operazione non riuscita. Riprovare.");
            }
        });
    
        mainLayout.setCenter(vbox);
    }    

    // Metodo per mostrare la vista dell'operazione 2
    @SuppressWarnings("unchecked")
    private void clientOperazione2(BorderPane mainLayout) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(new Label("Visualizza lo storico dei noleggi di un cliente"));
    
        TextField codiceFiscale = new TextField();
        codiceFiscale.setPromptText("Inserisci il codice fiscale del cliente");
    
        Button visualizzaStorico = new Button("Visualizza storico noleggi");
        TextField response = new TextField();
        response.setPromptText("Response");
        response.setEditable(false);
    
        // TableView per visualizzare lo storico dei noleggi
        TableView<List<String>> table = new TableView<>();
    
        TableColumn<List<String>, String> codNoleggio = new TableColumn<>("Codice Noleggio");
        codNoleggio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
    
        TableColumn<List<String>, String> codPrenotazione = new TableColumn<>("Codice Prenotazione");
        codPrenotazione.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
    
        TableColumn<List<String>, String> veicolo = new TableColumn<>("Id Veicolo");
        veicolo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
    
        TableColumn<List<String>, String> costo = new TableColumn<>("Costo");
        costo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));
    
        TableColumn<List<String>, String> dataInizio = new TableColumn<>("Data Inizio");
        dataInizio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(4)));
    
        TableColumn<List<String>, String> dataFine = new TableColumn<>("Data Fine");
        dataFine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(5)));
    
        TableColumn<List<String>, String> statoPrenotazione = new TableColumn<>("Stato Prenotazione");
        statoPrenotazione.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(6)));
    
        table.getColumns().addAll(codNoleggio, codPrenotazione, veicolo, costo, dataInizio, dataFine, statoPrenotazione);
    
        vbox.getChildren().addAll(codiceFiscale, visualizzaStorico, table, response);
    
        visualizzaStorico.setOnAction(e -> {
            String codiceFiscaleInput = codiceFiscale.getText().trim();
            
            // Controllo sul codice fiscale
            if (!isValidCodiceFiscale(codiceFiscaleInput)) {
                response.setText("Codice fiscale non valido. Deve essere di 16 caratteri alfanumerici.");
                return;
            }
    
            List<String> flatList = cliente.storicoNoleggi(codiceFiscaleInput);
            List<List<String>> lista = new ArrayList<>();
    
            // Raggruppa i dati usando il delimitatore ","
            for (String record : flatList) {
                // Divide ogni record in base al delimitatore
                String[] values = record.split(",");
                if (values.length == 7) { // Assicurati che ci siano esattamente 7 colonne
                    lista.add(Arrays.asList(values));
                } else {
                    // Gestisci il caso in cui i dati non siano nel formato previsto
                    response.setText("Formato dei dati non valido: " + Arrays.toString(values));
                    return;
                }
            }
    
            if (lista.isEmpty()) {
                response.setText("Nessun noleggio trovato per il codice fiscale specificato.");
            } else {
                response.setText("");
                ObservableList<List<String>> data = FXCollections.observableArrayList(lista);
                table.setItems(data);
            }
        });
    
        mainLayout.setCenter(vbox);
    }
    
    // Metodo per validare il codice fiscale
    private boolean isValidCodiceFiscale(String codiceFiscale) {
        return codiceFiscale.length() == 16 && codiceFiscale.matches("[A-Za-z0-9]+");
    }
    


    // Metodo per mostrare la vista dell'operazione 3
    private void clientOperazione3(BorderPane mainLayout) {
        // Configurazione della VBox come layout principale della vista
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(new Label("Visualizza i 10 clienti con più noleggi"));

        // Creazione del TextField per mostrare eventuali messaggi di risposta
        TextField response = new TextField();
        response.setPromptText("Response");
        response.setEditable(false);

        Button visualizzaClienti = new Button("Visualizza migliori clienti");

        // Creazione della TableView per mostrare i Codici Fiscali dei clienti
        TableView<String> table = new TableView<>();
        TableColumn<String, String> CFcliente = new TableColumn<>("Codice Fiscale Cliente");

        // Configurazione del CellValueFactory per mostrare i dati correttamente
        CFcliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));

        // Aggiunta della colonna alla TableView
        table.getColumns().add(CFcliente);
    
        // Aggiunta dei componenti alla VBox
        vbox.getChildren().addAll(visualizzaClienti, table, response);

        // Configurazione dell'azione del pulsante
        visualizzaClienti.setOnAction(e -> {
            List<String> lista = cliente.classificaClienti(); // Recupero della lista di clienti
            if (lista.isEmpty()) {
                response.setText("Nessun cliente trovato.");
                table.setItems(FXCollections.observableArrayList()); // Pulisci la tabella
            } else {
                response.setText("");
                ObservableList<String> data = FXCollections.observableArrayList(lista);
                table.setItems(data); // Popola la tabella con i dati
            }
        });

        // Imposta la VBox come contenuto principale del layout
        mainLayout.setCenter(vbox);
    }
}
