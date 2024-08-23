package src.main.java.view;

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
import src.main.java.model.classes.Noleggio;

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
        Button operazione3Button = new Button("Operazione Home 3");

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
        TextField codiceFiscale = new TextField();
        codiceFiscale.setPromptText("inserisci il codice fiscale");
        TextField nome = new TextField();
        nome.setPromptText("inserisci il nome");
        TextField cognome = new TextField();
        cognome.setPromptText("inserisci il cognome");
        TextField indirizzo_via = new TextField();
        indirizzo_via.setPromptText("inserisci la via di residenza");
        TextField indirizzo_numeroCivico = new TextField();
        indirizzo_numeroCivico.setPromptText("inserisci il numero civico di residenza");
        TextField indirizzo_città = new TextField();
        indirizzo_città.setPromptText("inserisci la città di residenza");
        TextField indirizzo_CAP = new TextField();
        indirizzo_CAP.setPromptText("inserisci il CAP di residenza");
        TextField numeroDiTelefono = new TextField();
        numeroDiTelefono.setPromptText("inserisci il numero di telefono");
        TextField indirizzoEmail = new TextField();
        indirizzoEmail.setPromptText("inserisci indirizzo email (facolatativo)");
        TextField numeroPatente = new TextField();
        numeroPatente.setPromptText("inserisci il numero della patente di guida");
        TextField fatturazione_via = new TextField();
        fatturazione_via.setPromptText("inserisci la via dell'indirizzo di fatturazione");
        TextField fatturazione_numeroCivico = new TextField();
        fatturazione_numeroCivico.setPromptText("inserisci il numero civico dell'indirizzo di fatturazione");
        TextField fatturazione_città = new TextField();
        fatturazione_città.setPromptText("inserisci la città dell'indirizzo di fatturazione");
        TextField fatturazione_CAP = new TextField();
        fatturazione_CAP.setPromptText("inserisci il CAP dell'indirizzo di fatturazione");
        Button aggiungiCliente = new Button("Aggiungi Nuovo Cliente");
        TextField response = new TextField();
        response.setPromptText("response");
        response.setEditable(false);
        response.setMinSize(300, 300);

        vbox.getChildren().addAll(codiceFiscale, nome, cognome, indirizzo_via,
                                    indirizzo_numeroCivico, indirizzo_città, 
                                    indirizzo_CAP, numeroDiTelefono, indirizzoEmail,
                                    numeroPatente, fatturazione_via, fatturazione_numeroCivico,
                                    fatturazione_città, fatturazione_CAP,
                                    aggiungiCliente, response);
        
        aggiungiCliente.setOnAction(e -> {

            String temp1 = persona.addPerson(codiceFiscale.getText(), nome.getText(), cognome.getText(), indirizzo_via.getText(), 
                                            Integer.parseInt(indirizzo_numeroCivico.getText()), indirizzo_città.getText(), 
                                            indirizzo_CAP.getText(), numeroDiTelefono.getText(), indirizzoEmail.getText());
            String temp2 = cliente.addClient(codiceFiscale.getText(), numeroPatente.getText(), fatturazione_via.getText(),
                                            Integer.parseInt(fatturazione_numeroCivico.getText()), fatturazione_città.getText(),
                                            fatturazione_CAP.getText());
            response.setText("Risultato registrazione Persona: " + temp1 + "\n Risultato registrazione come cliente: " + temp2);
        });

        mainLayout.setCenter(vbox);
    }

    // Metodo per mostrare la vista dell'operazione 2
    @SuppressWarnings("unchecked")
    private void clientOperazione2(BorderPane mainLayout) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(new Label("Visualizza lo storico dei noleggi di un cliente"));
        TextField CF = new TextField();
        CF.setPromptText("Inserire il codice fiscale del cliente");
        Button visualizzaStorico = new Button("Visualizza storico noleggi");
        TextField response = new TextField();
        response.setPromptText("Response");
        response.setEditable(false);

        TableView<Noleggio> table = new TableView<>();
        TableColumn<Noleggio,String> codNoleggio = new TableColumn<>("Codice Noleggio");
        codNoleggio.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getCodNoleggio())));
        TableColumn<Noleggio,String> codPrenotazione = new TableColumn<>("Codice Prenotazione");
        codPrenotazione.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getCodPrenotazione())));
        TableColumn<Noleggio,String> veicolo = new TableColumn<>("Veicolo");
        veicolo.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getIdVeicolo())));
        TableColumn<Noleggio,String> costo = new TableColumn<>("Costo");
        costo.setCellValueFactory(cellData -> new SimpleStringProperty(Float.toString(cellData.getValue().getCosto())));

        table.getColumns().addAll(codNoleggio, codPrenotazione, veicolo, costo);
        vbox.getChildren().addAll(CF, visualizzaStorico, table, response);
        visualizzaStorico.setOnAction(e -> {
            Noleggio temp = cliente.storicoNoleggi(CF.getText());

            ObservableList<Noleggio> data = FXCollections.observableArrayList(
                temp
            );
            response.setText(temp.getErrorMessage());
            table.setItems(data);
        });

        mainLayout.setCenter(vbox);
    }

    // Metodo per mostrare la vista dell'operazione 3
    private void clientOperazione3(BorderPane mainLayout) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(new Label("Operazione Home 3"));
        vbox.getChildren().add(new TextField("Campo X"));
        vbox.getChildren().add(new TextField("Campo Y"));

        mainLayout.setCenter(vbox);
    }
    
}
