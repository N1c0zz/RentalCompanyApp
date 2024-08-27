package src.view;

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
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import src.RentalCompanyApp;
import src.controller.DAOclasses.FatturaDAO;
import src.controller.DBHandler.DataBaseHandler;

import java.util.*;

import javafx.util.Pair;

public class InvoiceScene {

    private RentalCompanyApp app;
    private DataBaseHandler dbHandler = new DataBaseHandler();
    private FatturaDAO fattura = new FatturaDAO(dbHandler);

    public InvoiceScene(RentalCompanyApp app){
        this.app = app;
    }

    public Scene createInvoiceScene() {
        BorderPane mainLayout = new BorderPane();

        HBox topMenu = new TopMenu(app).createTopMenu(mainLayout);

        VBox sideMenu = new VBox(10);
        sideMenu.setPadding(new Insets(10));
        Button operazione1Button = new Button("Emettere una nuova fattura");
        Button operazione2Button = new Button("Visualizza il fatturato mensile");

        operazione1Button.setOnAction(e -> {
            emettiFattura(mainLayout);
            app.getPrimaryStage().setFullScreen(true);
            app.getPrimaryStage().setFullScreenExitKeyCombination(KeyCombination.keyCombination("ESC"));});
        operazione2Button.setOnAction(e -> {
            fatturatoMensile(mainLayout);
            app.getPrimaryStage().setFullScreen(true);
            app.getPrimaryStage().setFullScreenExitKeyCombination(KeyCombination.keyCombination("ESC"));});

        sideMenu.getChildren().addAll(operazione1Button, operazione2Button);

        mainLayout.setTop(topMenu);
        mainLayout.setLeft(sideMenu);

        Label homeLabel = new Label("Sezione Fatture");
        mainLayout.setCenter(homeLabel);

        return new Scene(mainLayout, 800, 600);
    }

    private void emettiFattura(BorderPane mainLayout) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(new Label("Compila una nuova fattura per una prenotazione"));
    
        TextField codPrenotazione = new TextField();
        codPrenotazione.setPromptText("Inserisci il codice della prenotazione associata");
    
        TextField metodoDiPagamento = new TextField();
        metodoDiPagamento.setPromptText("Inserisci il metodo di pagamento utilizzato");
    
        Button emettiFattura = new Button("Emetti fattura");
    
        TextField response = new TextField();
        response.setPromptText("Response");
        response.setEditable(false);
        response.setMinSize(200, 200);
    
        vbox.getChildren().addAll(codPrenotazione, metodoDiPagamento, emettiFattura, response);
    
        emettiFattura.setOnAction(e -> {
            String codicePrenotazioneInput = codPrenotazione.getText().trim();
            String metodoDiPagamentoInput = metodoDiPagamento.getText().trim();
    
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
    
            if (metodoDiPagamentoInput.isEmpty()) {
                response.setText("Errore: Il metodo di pagamento non può essere vuoto.");
                return;
            }
    
            String risultato = fattura.emettiFattura(metodoDiPagamentoInput, codicePrenotazione);
            response.setText(risultato);
        });
    
        mainLayout.setCenter(vbox);
    }
    
    @SuppressWarnings("unchecked")
    private void fatturatoMensile(BorderPane mainLayout) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(new Label("Visualizza il fatturato mensile"));

        Button visualizzaFatturato = new Button("Visualizza fatturato mensile");
        TextField response = new TextField();
        response.setPromptText("Response");
        response.setEditable(false);

        TableView<Pair<String, String>> table = new TableView<>();

        TableColumn<Pair<String, String>, String> meseAnnoCol = new TableColumn<>("Mese e anno");
        meseAnnoCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey()));

        TableColumn<Pair<String, String>, String> fatturatoCol = new TableColumn<>("Fatturato");
        fatturatoCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue()));

        table.getColumns().addAll(meseAnnoCol, fatturatoCol);
        vbox.getChildren().addAll(visualizzaFatturato, table, response);

        visualizzaFatturato.setOnAction(e -> {
            List<String> lista = fattura.fatturatoMensile();

            if (lista.size() == 1) {
            response.setText(lista.get(0));
            } else {
                response.setText("");
                List<Pair<String, String>> data = new ArrayList<>();

                for (int i = 0; i < lista.size(); i += 2) {
                    String meseAnno = lista.get(i);
                    String fatturato = lista.get(i + 1);
                    data.add(new Pair<>(meseAnno, fatturato));
                }

                ObservableList<Pair<String, String>> observableData = FXCollections.observableArrayList(data);
                table.setItems(observableData);
            }
        });

        mainLayout.setCenter(vbox);
    }
}
