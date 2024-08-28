package src.controller.DAOclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import src.controller.DBHandler.DataBaseHandler;

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
        String query = "INSERT INTO noleggi (codPrenotazione, veicolo, costo)" +
                        " SELECT u.idPrenotazione, u.numVeicolo, (DATEDIFF(p.dataFine, p.dataInizio) + 1) * v.costoPerGiornata AS costo" +
                        " FROM utilizzi u JOIN prenotazioni p ON u.idPrenotazione = p.codPrenotazione" +
                        " JOIN veicoli v ON u.numVeicolo = v.idVeicolo" +
                        " WHERE p.codPrenotazione = ?";

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
        String queryAggiornaPrenotazione = "UPDATE prenotazioni AS p " +
                                           "JOIN clienti AS c ON c.CFCliente = p.cliente " +
                                           "SET p.statoPrenotazione = 'Conclusa', " +
                                           "c.numeroNoleggiConclusi = c.numeroNoleggiConclusi + p.numeroNoleggiRichiesti " +
                                           "WHERE p.codPrenotazione = (SELECT codPrenotazione FROM noleggi WHERE codNoleggio = ?)";
    
        String queryEliminaUtilizzi = "DELETE FROM utilizzi WHERE idPrenotazione = " +
                                       "(SELECT codPrenotazione FROM noleggi WHERE codNoleggio = ?)";
    
        Connection conn = null;
        try {
            conn = dbHandler.setSQLDataSource().getConnection();
            conn.setAutoCommit(false);
    
            int affRowsPrenotazione;
            try (PreparedStatement pstmtAggiornaPrenotazione = conn.prepareStatement(queryAggiornaPrenotazione)) {
                pstmtAggiornaPrenotazione.setInt(1, codNoleggioDaModificare);
                affRowsPrenotazione = pstmtAggiornaPrenotazione.executeUpdate();
            }
    
            int affRowsUtilizzi;
            try (PreparedStatement pstmtEliminaUtilizzi = conn.prepareStatement(queryEliminaUtilizzi)) {
                pstmtEliminaUtilizzi.setInt(1, codNoleggioDaModificare);
                affRowsUtilizzi = pstmtEliminaUtilizzi.executeUpdate();
            }
    
            if (affRowsPrenotazione > 0 && affRowsUtilizzi > 0) {
                conn.commit();
                return "Noleggio terminato correttamente";
            } else {
                conn.rollback();
                return "Terminazione noleggio non riuscita. Riprovare";
            }
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            return e.getMessage();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }
}
