package src.main.java.controller.DAOclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import src.main.java.controller.DBHandler.DataBaseHandler;

public class NoleggioDAO {
    
    private DataBaseHandler dbHandler = new DataBaseHandler();

    public NoleggioDAO (DataBaseHandler dbHandler) {
        this.dbHandler = dbHandler;
    }
    /**
     * OP10 - ATTIVARE UN NOLEGGIO
     * Attiva un noleggio relativo ad una determinata
     * prenotazione andando a prendere da Utilizzi il veicolo
     * da noleggiare, da Veicoli il relativo costo e da Prenotazioni
     * il numero di giorni per i quali viene noleggiato, facendo il
     * calcolo del costo totale.
     * 
     * @param noleggio
     */
    public String attivaNoleggio(int codPrenotazione) {
        String query = "INSERT INTO noleggi (codPrenotazione, idVeicolo, costo)" +
                        "SELECT u.codPrenotazione, u.idVeicolo, (DATEDIFF(p.dataFine, p.dataInizio) + 1) * v.costoPerGiornata AS costo" +
                        "FROM utilizzi u JOIN prenotazioni p ON u.idPrenotazione = p.codPrenotazione" +
                        "JOIN veicoli v ON u.numVeicolo = v.idVeicolo" +
                        "WHERE p.codPrenotazione = ?";

        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, codPrenotazione);
                int affRows1 = pstmt.executeUpdate();

                if(affRows1 > 0){
                    return "Noleggio attivato correttamente.";
                } else {
                    return "Attivazione non riuscita. Riprovare";
                }

            } catch (SQLException e) {
                return e.getMessage();
            }
    }

    /**
     * OP11 - TERMINARE UN NOLEGGIO
     * Aggiorno inizialmente le tabelle Prenotazioni e Clienti
     * assegnando la prenotazione relativa come conclusa, e 
     * incrementando il numero di noleggi conclusi dal relativo
     * cliente.
     * 
     * @param noleggio
     */
    public String terminaNoleggio(int codNoleggioDaModificare) {
        String query = "UPDATE prenotazioni AS p" +
                        " JOIN clienti AS c ON c.CFCliente = p.cliente" +
                        " JOIN noleggi AS v ON v.codPrenotazione = p.codPrenotazione" +
                        " SET p.statoPrenotazione = \"Conclusa\", c.numeroNoleggiConclusi = c.numeroNoleggiConclusi + p.numeroNoleggiRichiesti" +
                        " WHERE v.codNoleggio = ?; " +
                        "DELETE FROM utilizzi WHERE idPrenotazione = (SELECT codPrenotazione FROM noleggi WHERE codNoleggio = ?); " +
                        "DELETE FROM noleggi WHERE codNoleggio = ?";

        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, codNoleggioDaModificare);
                pstmt.setInt(2, codNoleggioDaModificare);
                pstmt.setInt(3, codNoleggioDaModificare);

                int affRows = pstmt.executeUpdate();

                if(affRows > 0)
                    return "Noleggio terminato correttamente";
                else
                    return "Terminazione noleggio non riuscita. Riprovare";

            } catch (SQLException e) {
                return e.getMessage();
            }
    }
}
