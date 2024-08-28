package src.controller.DAOclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import src.controller.DBHandler.DataBaseHandler;

public class SanzioneDAO {

    private DataBaseHandler dbHandler = new DataBaseHandler();

    public SanzioneDAO(DataBaseHandler dbHandler) {
        this.dbHandler = dbHandler;
    }
    /**
     * OP15 - EMETTERE UNA SANZIONE RELATIVA AD UNA PRENOTAZIONE
     * 
     * @param codPrenotazione
     * @param motivazione
     * @param costoApplicato
     */
    public String emettiSanzione(int codPrenotazione, String motivazione, float costoApplicato) {
        String queryInserisciSanzione = "INSERT INTO sanzioni (motivazione, costoApplicato) VALUES (?, ?)";
        String queryInserisciInclusione = "INSERT INTO inclusioni (fattura, codSanzione) VALUES ((SELECT numeroFattura FROM fatture WHERE prenotazione = ?), LAST_INSERT_ID())";
        String queryAggiornaFattura = "UPDATE fatture SET importoTotale = importoTotale + (SELECT SUM(s.costoApplicato) FROM inclusioni i JOIN sanzioni s ON i.codSanzione = s.codSanzione WHERE i.fattura = (SELECT fattura FROM inclusioni WHERE codSanzione = LAST_INSERT_ID())) WHERE prenotazione = ?";
    
        Connection conn = null;
        try {
            conn = dbHandler.setSQLDataSource().getConnection();
            conn.setAutoCommit(false);
    
            try (PreparedStatement pstmtInserisciSanzione = conn.prepareStatement(queryInserisciSanzione)) {
                pstmtInserisciSanzione.setString(1, motivazione);
                pstmtInserisciSanzione.setFloat(2, costoApplicato);
                int affRowsSanzione = pstmtInserisciSanzione.executeUpdate();
    
                try (PreparedStatement pstmtInserisciInclusione = conn.prepareStatement(queryInserisciInclusione)) {
                    pstmtInserisciInclusione.setInt(1, codPrenotazione);
                    int affRowsInclusione = pstmtInserisciInclusione.executeUpdate();

                    try (PreparedStatement pstmtAggiornaFattura = conn.prepareStatement(queryAggiornaFattura)) {
                        pstmtAggiornaFattura.setInt(1, codPrenotazione);
                        int affRowsFattura = pstmtAggiornaFattura.executeUpdate();
    
                        if (affRowsSanzione > 0 && affRowsInclusione > 0 && affRowsFattura > 0) {
                            conn.commit();
                            return "Sanzione generata correttamente";
                        } else {
                            conn.rollback();
                            return "Generazione non riuscita. Riprovare";
                        }
                    }
                }
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
