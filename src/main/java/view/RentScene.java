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
import src.main.java.controller.DAOclasses.NoleggioDAO;
import src.main.java.controller.DBHandler.DataBaseHandler;

public class RentScene {

    private RentalCompanyApp app;
    private DataBaseHandler dbHandler = new DataBaseHandler();
    private NoleggioDAO noleggio = new NoleggioDAO(dbHandler);
    
    public RentScene(RentalCompanyApp app) {
        this.app = app;
    }

    public Scene createRentScene() {
        BorderPane mainLayout = new BorderPane();

        // Menu superiore
        HBox topMenu = new TopMenu(app).createTopMenu(mainLayout);

        // Menu laterale per le sotto-operazioni
        VBox sideMenu = new VBox(10);
        sideMenu.setPadding(new Insets(10));
        Button operazione1Button = new Button("Attivare un nuovo noleggio");
        Button operazione2Button = new Button("Termina un noleggio");

        // Imposta le azioni per i bottoni
        operazione1Button.setOnAction(e -> rentOperazione1(mainLayout));
        operazione2Button.setOnAction(e -> rentOperazione2(mainLayout));

        sideMenu.getChildren().addAll(operazione1Button, operazione2Button);

        mainLayout.setTop(topMenu);
        mainLayout.setLeft(sideMenu);

        // Contenuto principale iniziale
        Label homeLabel = new Label("Sezione Noleggi");
        mainLayout.setCenter(homeLabel);

        return new Scene(mainLayout, 800, 600);
    }

    // Metodo per mostrare la vista dell'operazione 1
    private void rentOperazione1(BorderPane mainLayout) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(new Label("Attiva un nuovo noleggio per una prenotazione"));
        TextField codPrenotazione = new TextField();
        codPrenotazione.setPromptText("Inserisci il codice della prenotazione");
        Button attivaNoleggio = new Button("Attiva noleggio");
        TextField response = new TextField();
        response.setPromptText("Response");
        response.setEditable(false);
        response.setMinSize(200, 200);

        vbox.getChildren().addAll(codPrenotazione, attivaNoleggio, response);

        attivaNoleggio.setOnAction(e -> {
            String temp1 = noleggio.attivazioneNoleggio(Integer.parseInt(codPrenotazione.getText()));

            response.setText(temp1);
        });

        mainLayout.setCenter(vbox);
    }

    // Metodo per mostrare la vista dell'operazione 2
    private void rentOperazione2(BorderPane mainLayout) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(new Label("Termina un noleggio"));
        TextField codNoleggio = new TextField();
        codNoleggio.setPromptText("Inserisci il codice del noleggio da terminare");
        Button terminaNoleggio = new Button("Termina noleggio");
        TextField response = new TextField();
        response.setPromptText("Response");
        response.setEditable(false);
        response.setMinSize(200, 200);

        vbox.getChildren().addAll(codNoleggio, terminaNoleggio, response);

        terminaNoleggio.setOnAction(e -> {
            String temp1 = noleggio.terminaNoleggio(Integer.parseInt(codNoleggio.getText()));

            response.setText(temp1);
        });

        mainLayout.setCenter(vbox);
    }
}
