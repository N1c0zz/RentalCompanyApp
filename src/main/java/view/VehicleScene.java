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
import src.main.java.controller.DAOclasses.SchedaTecnicaDAO;
import src.main.java.controller.DAOclasses.VeicoloDAO;
import src.main.java.controller.DBHandler.DataBaseHandler;
import src.main.java.model.SchedaTecnica;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VehicleScene {

    private RentalCompanyApp app;
    private DataBaseHandler dbHandler = new DataBaseHandler();
    private VeicoloDAO veicolo = new VeicoloDAO(dbHandler);
    private SchedaTecnicaDAO schedaTecnica = new SchedaTecnicaDAO();

    public VehicleScene(RentalCompanyApp app) {
        this.app = app;
    }
    
    public Scene createVehicleScene() {
        BorderPane mainLayout = new BorderPane();

        // Menu superiore
        HBox topMenu = new TopMenu(app).createTopMenu(mainLayout);

        // Menu laterale per le sotto-operazioni
        VBox sideMenu = new VBox(10);
        sideMenu.setPadding(new Insets(10));
        Button operazione1Button = new Button("Registrazione di un nuovo veicolo");
        Button operazione2Button = new Button("Disponibilità di un veicolo");
        Button operazione3Button = new Button("Scheda tecnica di un veicolo");
        Button operazione4Button = new Button("Veicoli più noleggiati");
        Button operazione5Button = new Button("Tasso di utilizzo di un veicolo");

        // Imposta le azioni per i bottoni
        operazione1Button.setOnAction(e -> vehicleOperazione1(mainLayout));
        operazione2Button.setOnAction(e -> vehicleOperazione2(mainLayout));
        operazione3Button.setOnAction(e -> vehicleOperazione3(mainLayout));
        operazione4Button.setOnAction(e -> vehicleOperazione4(mainLayout));
        operazione5Button.setOnAction(e -> vehicleOperazione5(mainLayout));

        sideMenu.getChildren().addAll(operazione1Button, operazione2Button, operazione3Button, 
                                        operazione4Button, operazione5Button);

        mainLayout.setTop(topMenu);
        mainLayout.setLeft(sideMenu);

        // Contenuto principale iniziale
        Label homeLabel = new Label("Sezione Veicoli");
        mainLayout.setCenter(homeLabel);

        return new Scene(mainLayout, 800, 600);
    }

    // Metodo per mostrare la vista dell'operazione 1
    private void vehicleOperazione1(BorderPane mainLayout) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(new Label("Registra qui un nuovo veicolo"));
    
        TextField numeroSchedaTecnica = new TextField();
        numeroSchedaTecnica.setPromptText("Inserisci numero scheda tecnica");
    
        TextField targa = new TextField();
        targa.setPromptText("Inserisci la targa del veicolo");
    
        TextField chilometraggio = new TextField();
        chilometraggio.setPromptText("Inserisci chilometraggio veicolo");
    
        TextField costoPerGiornata = new TextField();
        costoPerGiornata.setPromptText("Inserisci il costo per giornata");
    
        Button aggiungiVeicolo = new Button("Aggiungi Nuovo Veicolo");
    
        TextField response = new TextField();
        response.setPromptText("Response");
        response.setEditable(false);
        response.setMinSize(300, 300);
    
        vbox.getChildren().addAll(numeroSchedaTecnica, targa, chilometraggio, costoPerGiornata, aggiungiVeicolo, response);
    
        aggiungiVeicolo.setOnAction(e -> {
            // Controllo sul numero scheda tecnica
            String numeroSchedaTecnicaStr = numeroSchedaTecnica.getText().trim();
            if (numeroSchedaTecnicaStr.isEmpty()) {
                response.setText("Errore: Il numero della scheda tecnica non può essere vuoto.");
                return;
            }
    
            int numeroScheda;
            try {
                numeroScheda = Integer.parseInt(numeroSchedaTecnicaStr);
            } catch (NumberFormatException ex) {
                response.setText("Errore: Il numero della scheda tecnica deve essere un numero intero valido.");
                return;
            }
    
            // Controllo sulla targa
            String targaStr = targa.getText().trim();
            if (targaStr.isEmpty()) {
                response.setText("Errore: La targa del veicolo non può essere vuota.");
                return;
            }
            // Qui si potrebbe aggiungere un controllo di formato se necessario
    
            // Controllo sul chilometraggio
            String chilometraggioStr = chilometraggio.getText().trim();
            int chilometraggioInt;
            try {
                chilometraggioInt = Integer.parseInt(chilometraggioStr);
                if (chilometraggioInt < 0) {
                    response.setText("Errore: Il chilometraggio deve essere un valore positivo.");
                    return;
                }
            } catch (NumberFormatException ex) {
                response.setText("Errore: Il chilometraggio deve essere un numero intero valido.");
                return;
            }
    
            // Controllo sul costo per giornata
            String costoPerGiornataStr = costoPerGiornata.getText().trim();
            float costo;
            try {
                costo = Float.parseFloat(costoPerGiornataStr);
                if (costo <= 0) {
                    response.setText("Errore: Il costo per giornata deve essere un valore positivo.");
                    return;
                }
            } catch (NumberFormatException ex) {
                response.setText("Errore: Il costo per giornata deve essere un numero valido.");
                return;
            }
    
            // Se tutti i controlli sono passati, registra il veicolo
            String risultato = veicolo.addVehicle(numeroScheda, targaStr, chilometraggioInt, costo);
            response.setText(risultato);
        });
    
        mainLayout.setCenter(vbox);
    }
    

    // Metodo per mostrare la vista dell'operazione 2
    private void vehicleOperazione2(BorderPane mainLayout) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(new Label("Controlla la disponibilità di un veicolo"));

        TextField idVeicolo = new TextField();
        idVeicolo.setPromptText("Id del veicolo da cercare");

        TextField dataInizio = new TextField();
        dataInizio.setPromptText("Inserisci la data di inizio nel formato: anno-mese-giorno");

        TextField dataFine = new TextField();
        dataFine.setPromptText("Inserisci la data di fine nel formato: anno-mese-giorno");

        Button verificaDisponibilita = new Button("Verifica Disponibilità");

        TextField response = new TextField();
        response.setPromptText("Response");
        response.setEditable(false);
        response.setMinSize(300, 300);

        vbox.getChildren().addAll(idVeicolo, dataInizio, dataFine, verificaDisponibilita, response);

        verificaDisponibilita.setOnAction(e -> {
            // Controllo sull'ID del veicolo
            String idVeicoloStr = idVeicolo.getText().trim();
            if (idVeicoloStr.isEmpty()) {
                response.setText("Errore: L'ID del veicolo non può essere vuoto.");
                return;
            }

            int id;
            try {
                id = Integer.parseInt(idVeicoloStr);
            } catch (NumberFormatException ex) {
                response.setText("Errore: L'ID del veicolo deve essere un numero intero valido.");
                return;
            }

            // Controllo sulla data di inizio
            String dataInizioStr = dataInizio.getText().trim();
            LocalDate dataInizioDate;
            try {
                dataInizioDate = LocalDate.parse(dataInizioStr);
            } catch (DateTimeParseException ex) {
                response.setText("Errore: La data di inizio deve essere nel formato anno-mese-giorno.");
                return;
            }

            // Controllo sulla data di fine
            String dataFineStr = dataFine.getText().trim();
            LocalDate dataFineDate;
            try {
                dataFineDate = LocalDate.parse(dataFineStr);
            } catch (DateTimeParseException ex) {
                response.setText("Errore: La data di fine deve essere nel formato anno-mese-giorno.");
                return;
            }

            // Verifica che la data di fine non sia precedente alla data di inizio
            if (dataFineDate.isBefore(dataInizioDate)) {
                response.setText("Errore: La data di fine non può essere precedente alla data di inizio.");
                return;
            }

            // Se tutti i controlli sono passati, verifica la disponibilità del veicolo
            String risultato = veicolo.verificaDisponibilitaVeicolo(id, dataInizioStr, dataFineStr);
            response.setText(risultato);
        });

        mainLayout.setCenter(vbox);
    }


    // Metodo per mostrare la vista dell'operazione 3
    @SuppressWarnings("unchecked")
    private void vehicleOperazione3(BorderPane mainLayout) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(new Label("Visualizza la scheda tecnica di un veicolo"));
    
        TextField idVeicolo = new TextField();
        idVeicolo.setPromptText("Inserire l'id del veicolo da cercare");
    
        Button visualizzaScheda = new Button("Visualizza scheda tecnica");
    
        TextField response = new TextField();
        response.setPromptText("Response");
        response.setEditable(false);
    
        TableView<SchedaTecnica> table = new TableView<>();
        TableColumn<SchedaTecnica, String> numeroScheda = new TableColumn<>("Numero Scheda");
        numeroScheda.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getNumeroScheda())));
    
        TableColumn<SchedaTecnica, String> tipoVeicolo = new TableColumn<>("Tipo Veicolo");
        tipoVeicolo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipoVeicolo()));
    
        TableColumn<SchedaTecnica, String> casaProduttrice = new TableColumn<>("Casa Produttrice");
        casaProduttrice.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCasaProduttrice()));
    
        TableColumn<SchedaTecnica, String> modello = new TableColumn<>("Modello");
        modello.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModello()));
    
        TableColumn<SchedaTecnica, String> annoDiProduzione = new TableColumn<>("Anno di Produzione");
        annoDiProduzione.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getAnnoDiProduzione())));
    
        TableColumn<SchedaTecnica, String> numeroPosti = new TableColumn<>("Numero Posti");
        numeroPosti.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getNumeroPosti())));
    
        TableColumn<SchedaTecnica, String> numeroPorte = new TableColumn<>("Numero Porte");
        numeroPorte.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getNumeroPorte())));
    
        TableColumn<SchedaTecnica, String> bagagliaio = new TableColumn<>("Capacità Bagagliaio");
        bagagliaio.setCellValueFactory(cellData -> new SimpleStringProperty(Float.toString(cellData.getValue().getCapacitàBagagliaio())));
    
        table.getColumns().addAll(numeroScheda, tipoVeicolo, casaProduttrice, modello, annoDiProduzione,
                                    numeroPosti, numeroPorte, bagagliaio);
    
        vbox.getChildren().addAll(idVeicolo, visualizzaScheda, table, response);
    
        visualizzaScheda.setOnAction(e -> {
            String idVeicoloStr = idVeicolo.getText().trim();
    
            // Controllo sull'ID del veicolo
            if (idVeicoloStr.isEmpty()) {
                response.setText("Errore: L'ID del veicolo non può essere vuoto.");
                return;
            }
    
            int id;
            try {
                id = Integer.parseInt(idVeicoloStr);
            } catch (NumberFormatException ex) {
                response.setText("Errore: L'ID del veicolo deve essere un numero intero valido.");
                return;
            }
    
            // Recupero della scheda tecnica
            SchedaTecnica scheda = schedaTecnica.getSchedaTecnica(id);
            if (scheda == null) {
                response.setText("Errore: Nessuna scheda tecnica trovata per l'ID del veicolo specificato.");
                table.getItems().clear();
            } else {
                ObservableList<SchedaTecnica> data = FXCollections.observableArrayList(scheda);
                table.setItems(data);
                response.setText("Scheda tecnica visualizzata con successo.");
            }
        });
    
        mainLayout.setCenter(vbox);
    }
    

    @SuppressWarnings("unchecked")
    private void vehicleOperazione4(BorderPane mainLayout) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(new Label("Visualizza i veicoli più noleggiati"));
        Button visualizzaClassifica = new Button("Visualizza i veicoli più noleggiati");
        TextField response = new TextField();
        response.setPromptText("Response");
        response.setEditable(false);

        // TableView configurata per gestire liste di stringhe
        TableView<List<String>> table = new TableView<>();

        // Configura le colonne
        TableColumn<List<String>, String> idVeicoloCol = new TableColumn<>("Id Veicolo");
        idVeicoloCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));

        TableColumn<List<String>, String> meseAnnoCol = new TableColumn<>("Mese e anno");
        meseAnnoCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));

        TableColumn<List<String>, String> tassoUtilizzoCol = new TableColumn<>("Tasso di utilizzo");
        tassoUtilizzoCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));

        table.getColumns().addAll(idVeicoloCol, meseAnnoCol, tassoUtilizzoCol);
        vbox.getChildren().addAll(visualizzaClassifica, table, response);

        visualizzaClassifica.setOnAction(e -> {
            List<String> veicoli = veicolo.veicoliPiuNoleggiati(); // Presumo che questo metodo ritorni una lista di stringhe

            if (veicoli.isEmpty()) {
                response.setText("Nessun dato disponibile.");
                table.setItems(FXCollections.observableArrayList()); // Pulisce la tabella
            } else {
                response.setText("");
                List<List<String>> data = new ArrayList<>();

            for (String veicoloData : veicoli) {
                // Divide la stringa usando il delimitatore ";" (puoi cambiare il delimitatore se necessario)
                String[] values = veicoloData.split(",");
                data.add(Arrays.asList(values));
            }

            table.setItems(FXCollections.observableArrayList(data));
        }
    });

    mainLayout.setCenter(vbox);
}


    @SuppressWarnings("unchecked")
    private void vehicleOperazione5(BorderPane mainLayout) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(new Label("Visualizza il tasso di utilizzo di un veicolo"));
    
        TextField numVeicolo = new TextField();
        numVeicolo.setPromptText("Inserisci l'id del veicolo interessato");
    
        Button visualizzaTassoUtilizzo = new Button("Visualizza tasso di utilizzo");
    
        TextField response = new TextField();
        response.setPromptText("Response");
        response.setEditable(false);
    
        TableView<List<String>> table = new TableView<>();
    
        TableColumn<List<String>, String> idVeicoloCol = new TableColumn<>("Id Veicolo");
        idVeicoloCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
    
        TableColumn<List<String>, String> meseCol = new TableColumn<>("Mese");
        meseCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
    
        TableColumn<List<String>, String> annoCol = new TableColumn<>("Anno");
        annoCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
    
        TableColumn<List<String>, String> tassoUtilizzoCol = new TableColumn<>("Tasso di utilizzo");
        tassoUtilizzoCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));
    
        table.getColumns().addAll(idVeicoloCol, meseCol, annoCol, tassoUtilizzoCol);
        vbox.getChildren().addAll(numVeicolo, visualizzaTassoUtilizzo, table, response);
    
        visualizzaTassoUtilizzo.setOnAction(e -> {
            String idVeicoloStr = numVeicolo.getText().trim();
            
            // Controllo sull'ID del veicolo
            if (idVeicoloStr.isEmpty()) {
                response.setText("Errore: L'ID del veicolo non può essere vuoto.");
                return;
            }
    
            int idVeicolo;
            try {
                idVeicolo = Integer.parseInt(idVeicoloStr);
            } catch (NumberFormatException ex) {
                response.setText("Errore: L'ID del veicolo deve essere un numero intero valido.");
                return;
            }
    
            List<String> lista = veicolo.tassoDiUtilizzo(idVeicolo);
    
            if (lista == null || lista.isEmpty()) {
                response.setText("Questo veicolo non è mai stato utilizzato.");
                table.setItems(FXCollections.observableArrayList()); // Pulisce la tabella
            } else {
                response.setText("");
                List<List<String>> data = new ArrayList<>();
    
                for (String veicoloData : lista) {
                    // Divide la stringa usando il delimitatore specifico (esempio: virgola)
                    String[] values = veicoloData.split(","); 
                    if (values.length == 4) { 
                        data.add(Arrays.asList(values));
                    } else {
                        System.err.println("Formato stringa non valido: " + veicoloData);
                    }
                }
    
                table.setItems(FXCollections.observableArrayList(data));
            }
        });
    
        mainLayout.setCenter(vbox);
    }
    
    
}   
