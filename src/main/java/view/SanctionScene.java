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
import src.main.java.controller.DAOclasses.SanzioneDAO;
import src.main.java.controller.DBHandler.DataBaseHandler;

public class SanctionScene {

    private RentalCompanyApp app;
    private DataBaseHandler dbHandler = new DataBaseHandler();
    private SanzioneDAO sanzione = new SanzioneDAO(dbHandler);

    public SanctionScene(RentalCompanyApp app){
        this.app = app;
    }

    public Scene createSanctionScene() {
        BorderPane mainLayout = new BorderPane();

        HBox topMenu = new TopMenu(app).createTopMenu(mainLayout);

        VBox sideMenu = new VBox(10);
        sideMenu.setPadding(new Insets(10));
        Button operazione1Button = new Button("Emettere una nuova sanzione");

        operazione1Button.setOnAction(e -> sanctionOperazione1(mainLayout));

        sideMenu.getChildren().addAll(operazione1Button);

        mainLayout.setTop(topMenu);
        mainLayout.setLeft(sideMenu);

        Label homeLabel = new Label("Sezione Sanzioni");
        mainLayout.setCenter(homeLabel);

        return new Scene(mainLayout, 800, 600);
    }

    private void sanctionOperazione1 (BorderPane mainLayout) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(new Label("Emettere una nuova sanzione"));
        TextField codPrenotazione = new TextField();
        codPrenotazione.setPromptText("Inserisci il codice della prenotazione associata");
        TextField motivazione = new TextField();
        motivazione.setPromptText("Inserisci la motivazione della sanzione");
        TextField costoApplicato = new TextField();
        costoApplicato.setPromptText("Inserisci il costo aggiuntivo per la sanzione");
        Button generaSanzione = new Button("Genera sanzione");
        TextField response = new TextField();
        response.setPromptText("Response");
        response.setEditable(false);
        response.setMinSize(200, 200);

        vbox.getChildren().addAll(codPrenotazione, motivazione, costoApplicato,
                                    generaSanzione, response);
        generaSanzione.setOnAction(e -> {
            response.setText(sanzione.emissioneSanzione(Integer.parseInt(codPrenotazione.getText()),
                            motivazione.getText(), Float.parseFloat(costoApplicato.getText())));
        });      
        mainLayout.setCenter(vbox);
    }
}
