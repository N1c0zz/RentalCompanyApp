package src.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import src.RentalCompanyApp;
import src.controller.DAOclasses.ClienteDAO;
import src.controller.DAOclasses.PersonaDAO;
import src.controller.DBHandler.DataBaseHandler;

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

        HBox topMenu = new TopMenu(app).createTopMenu(mainLayout);

        VBox sideMenu = new VBox(10);
        sideMenu.setPadding(new Insets(10));
        Button operazione1Button = new Button("Registrare un nuovo cliente");
        Button operazione2Button = new Button("Visualizza lo storico noleggi di un cliente");
        Button operazione3Button = new Button("Visualizza i 10 migliori clienti");

        operazione1Button.setOnAction(e -> {
            registraCliente(mainLayout);
            app.getPrimaryStage().setFullScreen(true);
            app.getPrimaryStage().setFullScreenExitKeyCombination(KeyCombination.keyCombination("ESC"));});
        operazione2Button.setOnAction(e -> {
            visualizzaStoricoNoleggi(mainLayout);
            app.getPrimaryStage().setFullScreen(true);
            app.getPrimaryStage().setFullScreenExitKeyCombination(KeyCombination.keyCombination("ESC"));});
        operazione3Button.setOnAction(e -> {
            miglioriClienti(mainLayout);
            app.getPrimaryStage().setFullScreen(true);
            app.getPrimaryStage().setFullScreenExitKeyCombination(KeyCombination.keyCombination("ESC"));});

        sideMenu.getChildren().addAll(operazione1Button, operazione2Button, operazione3Button);

        mainLayout.setTop(topMenu);
        mainLayout.setLeft(sideMenu);

        Label homeLabel = new Label("Sezione Clienti");
        mainLayout.setCenter(homeLabel);

        return new Scene(mainLayout, 800, 600);
    }

private void registraCliente(BorderPane mainLayout) {
    VBox vbox = new VBox(10);
    vbox.setPadding(new Insets(10));
    vbox.getChildren().add(new Label("Registra qui un nuovo cliente"));

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
    TextField indirizzo_citta = new TextField();
    indirizzo_citta.setPromptText("Inserisci la citta di residenza");
    TextField indirizzo_CAP = new TextField();
    indirizzo_CAP.setPromptText("Inserisci il CAP di residenza");
    TextField numeroDiTelefono = new TextField();
    numeroDiTelefono.setPromptText("Inserisci il numero di telefono");
    TextField indirizzoEmail = new TextField();
    indirizzoEmail.setPromptText("Inserisci indirizzo email (facoltativo)");
    TextField numeroPatente = new TextField();
    numeroPatente.setPromptText("Inserisci il numero della patente di guida");

    CheckBox copiaIndirizzoResidenza = new CheckBox("Usa l'indirizzo di residenza per la fatturazione");

    TextField fatturazione_via = new TextField();
    fatturazione_via.setPromptText("Inserisci la via dell'indirizzo di fatturazione");
    TextField fatturazione_numeroCivico = new TextField();
    fatturazione_numeroCivico.setPromptText("Inserisci il numero civico dell'indirizzo di fatturazione");
    TextField fatturazione_citta = new TextField();
    fatturazione_citta.setPromptText("Inserisci la citta dell'indirizzo di fatturazione");
    TextField fatturazione_CAP = new TextField();
    fatturazione_CAP.setPromptText("Inserisci il CAP dell'indirizzo di fatturazione");

    Button aggiungiCliente = new Button("Aggiungi Nuovo Cliente");
    TextArea response = new TextArea();
    response.setPromptText("Response");
    response.setPrefRowCount(5);
    response.setEditable(false);
    response.setMinSize(150, 150);

    vbox.getChildren().addAll(codiceFiscale, nome, cognome, indirizzo_via,
            indirizzo_numeroCivico, indirizzo_citta, indirizzo_CAP, numeroDiTelefono,
            indirizzoEmail, numeroPatente, copiaIndirizzoResidenza, fatturazione_via, fatturazione_numeroCivico,
            fatturazione_citta, fatturazione_CAP, aggiungiCliente, response);

    copiaIndirizzoResidenza.setOnAction(e -> {
        if (copiaIndirizzoResidenza.isSelected()) {
            fatturazione_via.setText(indirizzo_via.getText());
            fatturazione_numeroCivico.setText(indirizzo_numeroCivico.getText());
            fatturazione_citta.setText(indirizzo_citta.getText());
            fatturazione_CAP.setText(indirizzo_CAP.getText());
            fatturazione_via.setDisable(true);
            fatturazione_numeroCivico.setDisable(true);
            fatturazione_citta.setDisable(true);
            fatturazione_CAP.setDisable(true);
        } else {
            fatturazione_via.clear();
            fatturazione_numeroCivico.clear();
            fatturazione_citta.clear();
            fatturazione_CAP.clear();
            fatturazione_via.setDisable(false);
            fatturazione_numeroCivico.setDisable(false);
            fatturazione_citta.setDisable(false);
            fatturazione_CAP.setDisable(false);
        }
    });

    aggiungiCliente.setOnAction(e -> {
        StringBuilder errors = new StringBuilder();

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
        if (indirizzo_citta.getText().isEmpty()) {
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
        if (!copiaIndirizzoResidenza.isSelected()) {
            if (fatturazione_via.getText().isEmpty()) {
                errors.append("Indirizzo di fatturazione (via) è obbligatorio.\n");
            }
            if (fatturazione_numeroCivico.getText().isEmpty()) {
                errors.append("Indirizzo di fatturazione (numero civico) è obbligatorio.\n");
            }
            if (fatturazione_citta.getText().isEmpty()) {
                errors.append("Città di fatturazione è obbligatoria.\n");
            }
            if (fatturazione_CAP.getText().isEmpty()) {
                errors.append("CAP di fatturazione è obbligatorio.\n");
            }
        }

        if (!indirizzo_numeroCivico.getText().matches("\\d+")) {
            errors.append("Numero civico deve essere un numero.\n");
        }
        if (!fatturazione_numeroCivico.getText().matches("\\d+") && !copiaIndirizzoResidenza.isSelected()) {
            errors.append("Numero civico di fatturazione deve essere un numero.\n");
        }
        if (!indirizzo_CAP.getText().matches("\\d{5}")) {
            errors.append("CAP di residenza non valido. Deve essere un numero di 5 cifre.\n");
        }
        if (!fatturazione_CAP.getText().matches("\\d{5}") && !copiaIndirizzoResidenza.isSelected()) {
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

        String temp1 = persona.aggiungiPersona(
                codiceFiscale.getText(), nome.getText(), cognome.getText(),
                indirizzo_via.getText(), Integer.parseInt(indirizzo_numeroCivico.getText()),
                indirizzo_citta.getText(), indirizzo_CAP.getText(),
                numeroDiTelefono.getText(), indirizzoEmail.getText());

        String temp2 = cliente.aggiungiCliente(
                codiceFiscale.getText(), numeroPatente.getText(),
                fatturazione_via.getText(), Integer.parseInt(fatturazione_numeroCivico.getText()),
                fatturazione_citta.getText(), fatturazione_CAP.getText());
        if(temp1.equals(temp2)){
            response.setText("Operazione riuscita.");
        } else {
            response.setText("Operazione non riuscita. Riprovare" + "\nDettagli errore: " 
                        + "\nInserimento Persona :" + temp1 + "\nInserimento Cliente: " + temp2);
        }
    });

    mainLayout.setCenter(vbox);
}
    

    @SuppressWarnings("unchecked")
    private void visualizzaStoricoNoleggi(BorderPane mainLayout) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(new Label("Visualizza lo storico dei noleggi di un cliente"));
    
        TextField codiceFiscale = new TextField();
        codiceFiscale.setPromptText("Inserisci il codice fiscale del cliente");
    
        Button visualizzaStorico = new Button("Visualizza storico noleggi");
        TextField response = new TextField();
        response.setPromptText("Response");
        response.setEditable(false);

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
            
            if (!isValidCodiceFiscale(codiceFiscaleInput)) {
                response.setText("Codice fiscale non valido. Deve essere di 16 caratteri alfanumerici.");
                return;
            }
    
            List<String> flatList = cliente.storicoNoleggi(codiceFiscaleInput);
            List<List<String>> lista = new ArrayList<>();
    
            for (String record : flatList) {
                String[] values = record.split(",");
                if (values.length == 7) {
                    lista.add(Arrays.asList(values));
                } else {
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
    
    private boolean isValidCodiceFiscale(String codiceFiscale) {
        return codiceFiscale.length() == 16 && codiceFiscale.matches("[A-Za-z0-9]+");
    }
    
    private void miglioriClienti(BorderPane mainLayout) {

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(new Label("Visualizza i 10 clienti con piu' noleggi"));

        TextField response = new TextField();
        response.setPromptText("Response");
        response.setEditable(false);

        Button visualizzaClienti = new Button("Visualizza migliori clienti");

        TableView<String> table = new TableView<>();
        TableColumn<String, String> CFcliente = new TableColumn<>("Codice Fiscale Cliente");

        CFcliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));

        table.getColumns().add(CFcliente);
    
        vbox.getChildren().addAll(visualizzaClienti, table, response);

        visualizzaClienti.setOnAction(e -> {
            List<String> lista = cliente.classificaClienti();
            if (lista.isEmpty()) {
                response.setText("Nessun cliente trovato.");
                table.setItems(FXCollections.observableArrayList());
            } else {
                response.setText("");
                ObservableList<String> data = FXCollections.observableArrayList(lista);
                table.setItems(data);
            }
        });

        mainLayout.setCenter(vbox);
    }
}
