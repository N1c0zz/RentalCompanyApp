package src.main.java.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import src.main.java.application.RentalCompanyApp;
import src.main.java.controller.DAOclasses.FatturaDAO;
import src.main.java.controller.DBHandler.DataBaseHandler;

public class InvoiceScene {

    private RentalCompanyApp app;
    private DataBaseHandler dbHandler = new DataBaseHandler();
    private FatturaDAO fattura = new FatturaDAO(dbHandler);

    public InvoiceScene(RentalCompanyApp app){
        this.app = app;
    }

    public Scene createInvoiceScene() {
        BorderPane mainLayout = new BorderPane();

        // Menu superiore
        HBox topMenu = new TopMenu(app).createTopMenu(mainLayout);

        // Menu laterale per le sotto-operazioni
        VBox sideMenu = new VBox(10);
        sideMenu.setPadding(new Insets(10));
        Button operazione1Button = new Button("Emttere una nuova fattura");
        Button operazione2Button = new Button("Operazione Home 2");
        Button operazione3Button = new Button("Operazione Home 3");

        // Imposta le azioni per i bottoni
        operazione1Button.setOnAction(e -> invoiceOperazione1(mainLayout));
        operazione2Button.setOnAction(e -> invoiceOperazione2(mainLayout));
        operazione3Button.setOnAction(e -> invoiceOperazione3(mainLayout));

        sideMenu.getChildren().addAll(operazione1Button, operazione2Button, operazione3Button);

        mainLayout.setTop(topMenu);
        mainLayout.setLeft(sideMenu);

        // Contenuto principale iniziale
        Label homeLabel = new Label("Sezione Fatture");
        mainLayout.setCenter(homeLabel);

        return new Scene(mainLayout, 800, 600);
    }

    // Metodo per mostrare la vista dell'operazione 1
    private void invoiceOperazione1(BorderPane mainLayout) {
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
            response.setText(fattura.emettiFattura(metodoDiPagamento.getText(), 
                            Integer.parseInt(codPrenotazione.getText())));
        });
        
        mainLayout.setCenter(vbox);
    }

    // Metodo per mostrare la vista dell'operazione 2
    private void invoiceOperazione2(BorderPane mainLayout) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(new Label("Operazione Home 2"));
        vbox.getChildren().add(new TextField("Campo A"));
        vbox.getChildren().add(new TextField("Campo B"));

        mainLayout.setCenter(vbox);
    }

    // Metodo per mostrare la vista dell'operazione 3
    private void invoiceOperazione3(BorderPane mainLayout) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(new Label("Operazione Home 3"));
        vbox.getChildren().add(new TextField("Campo X"));
        vbox.getChildren().add(new TextField("Campo Y"));

        mainLayout.setCenter(vbox);
    }
}
