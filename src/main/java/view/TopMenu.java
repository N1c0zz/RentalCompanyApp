package src.main.java.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import src.main.java.application.RentalCompanyApp;

public class TopMenu {

    private final RentalCompanyApp app;

    public TopMenu(RentalCompanyApp app) {
        this.app = app;
    }

    public HBox createTopMenu(BorderPane mainLayout) {
        Button clientButton = new Button("Gestione Clienti");
        Button vehiclesButton = new Button("Gestione Veicoli");
        Button reservationsButton = new Button("Gestione Prenotazioni");
        Button rentsButton = new Button("Gestione Noleggi");
        Button invoicesButton = new Button("Gestione Fatture");
        Button sanctionsButton = new Button("Gestione Sanzioni");

        clientButton.setOnAction(e -> app.getPrimaryStage().setScene(new ClientScene(app).createClientScene()));
        vehiclesButton.setOnAction(e -> app.getPrimaryStage().setScene(new VehicleScene(app).createVehicleScene()));
        reservationsButton.setOnAction(e -> app.getPrimaryStage().setScene(new ReservationScene(app).createReservationScene()));
        rentsButton.setOnAction(e -> app.getPrimaryStage().setScene(new RentScene(app).createRentScene()));
        invoicesButton.setOnAction(e -> app.getPrimaryStage().setScene(new InvoiceScene(app).createInvoiceScene()));
        sanctionsButton.setOnAction(e -> app.getPrimaryStage().setScene(new SanctionScene(app).createSanctionScene()));

        HBox topMenu = new HBox(10, clientButton, vehiclesButton, reservationsButton, rentsButton, invoicesButton, sanctionsButton);
        topMenu.setPadding(new Insets(10));
        return topMenu;
    }
}
