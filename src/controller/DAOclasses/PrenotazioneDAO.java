package src.controller.DAOclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import src.controller.DBHandler.DataBaseHandler;

public class PrenotazioneDAO {

    private DataBaseHandler dbHandler = new DataBaseHandler();

    public PrenotazioneDAO (DataBaseHandler dbHandler) {
        this.dbHandler = dbHandler;
    }
    /**
     * OP7 - REGISTRARE UNA NUOVA PRENOTAZIONE
     * Metodo che registra una prenotazione con un solo
     * veicolo richiesto.
     * 
     * @param prenotazione
     */
public String registraPrenotazione(String CF, String dataInizio, String dataFine, int idVeicolo) {
    String queryPrenotazione = "INSERT INTO prenotazioni (cliente, dataInizio, dataFine) VALUES (?, ?, ?)";
    String queryUtilizzo = "INSERT INTO utilizzi (idPrenotazione, numVeicolo) VALUES (?, ?)";

    try (Connection conn = dbHandler.setSQLDataSource().getConnection()) {

        conn.setAutoCommit(false);

        try (PreparedStatement pstmtPrenotazione = conn.prepareStatement(queryPrenotazione, Statement.RETURN_GENERATED_KEYS)) {
            pstmtPrenotazione.setString(1, CF);
            pstmtPrenotazione.setString(2, dataInizio);
            pstmtPrenotazione.setString(3, dataFine);
            int affRowsPrenotazione = pstmtPrenotazione.executeUpdate();

            if (affRowsPrenotazione > 0) {
                try (ResultSet rs = pstmtPrenotazione.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idPrenotazione = rs.getInt(1);

                        try (PreparedStatement pstmtUtilizzo = conn.prepareStatement(queryUtilizzo)) {
                            pstmtUtilizzo.setInt(1, idPrenotazione);
                            pstmtUtilizzo.setInt(2, idVeicolo);
                            int affRowsUtilizzo = pstmtUtilizzo.executeUpdate();

                            if (affRowsUtilizzo > 0) {
                                conn.commit();
                                return "Prenotazione registrata correttamente";
                            } else {
                                conn.rollback();
                                return "Registrazione dell'utilizzo non riuscita. Riprovare.";
                            }
                        }
                    } else {
                        conn.rollback();
                        return "Errore nella generazione dell'ID prenotazione. Riprovare.";
                    }
                }
            } else {
                return "Registrazione della prenotazione non riuscita. Riprovare.";
            }
        }
    } catch (SQLException e) {
        return e.getMessage();
    }
}

    /**
     * OP7 - REGISTRARE UNA NUOVA PRENOTAZIONE
     * Metodo che registra una prenotazione con 2
     * veicoli richiesti.
     * 
     * @param prenotazione
     */
    public String registraDoppiaPrenotazione(String CF, String dataInizio, String dataFine, int idVeicolo1, int idVeicolo2) {
        String queryPrenotazione = "INSERT INTO prenotazioni (cliente, dataInizio, dataFine, numeroNoleggiRichiesti) VALUES (?, ?, ?, 2)";
        String queryUtilizzo1 = "INSERT INTO utilizzi (idPrenotazione, numVeicolo) VALUES (?, ?)";
        String queryUtilizzo2 = "INSERT INTO utilizzi (idPrenotazione, numVeicolo) VALUES (?, ?)";
    
        try (Connection conn = dbHandler.setSQLDataSource().getConnection()) {
            conn.setAutoCommit(false);
    
            try (PreparedStatement pstmtPrenotazione = conn.prepareStatement(queryPrenotazione, Statement.RETURN_GENERATED_KEYS)) {
                pstmtPrenotazione.setString(1, CF);
                pstmtPrenotazione.setString(2, dataInizio);
                pstmtPrenotazione.setString(3, dataFine);
                int affRowsPrenotazione = pstmtPrenotazione.executeUpdate();
    
                if (affRowsPrenotazione > 0) {
                    try (ResultSet rs = pstmtPrenotazione.getGeneratedKeys()) {
                        if (rs.next()) {
                            int idPrenotazione = rs.getInt(1);
    
                            try (PreparedStatement pstmtUtilizzo1 = conn.prepareStatement(queryUtilizzo1)) {
                                pstmtUtilizzo1.setInt(1, idPrenotazione);
                                pstmtUtilizzo1.setInt(2, idVeicolo1);
                                int affRowsUtilizzo1 = pstmtUtilizzo1.executeUpdate();
    
                                try (PreparedStatement pstmtUtilizzo2 = conn.prepareStatement(queryUtilizzo2)) {
                                    pstmtUtilizzo2.setInt(1, idPrenotazione);
                                    pstmtUtilizzo2.setInt(2, idVeicolo2);
                                    int affRowsUtilizzo2 = pstmtUtilizzo2.executeUpdate();
    
                                    if (affRowsUtilizzo1 > 0 && affRowsUtilizzo2 > 0) {
                                        conn.commit();
                                        return "Prenotazione registrata correttamente";
                                    } else {
                                        conn.rollback();
                                        return "Registrazione degli utilizzi non riuscita. Riprovare.";
                                    }
                                }
                            }
                        } else {
                            conn.rollback();
                            return "Errore nella generazione dell'ID prenotazione. Riprovare.";
                        }
                    }
                } else {
                    return "Registrazione della prenotazione non riuscita. Riprovare.";
                }
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
    
    /**
     * OP8 - MODIFICARE UNA PRENOTAZIONE
     * Modifica una prenotazione cambiando dataInizio e
     * dataFine.
     * 
     * @param prenotazione
     */
    public String modificaPrenotazione(String dataInizio, String dataFine, int codPrenotazione, int idVeicolo) {
        String queryAggiornaPrenotazione = "UPDATE prenotazioni SET dataInizio = ?, dataFine = ? WHERE codPrenotazione = ?";
        String queryAggiornaUtilizzo = "UPDATE utilizzo SET numVeicolo = ? WHERE idPrenotazione = ?";
    
        Connection conn = null;
        try {
            conn = dbHandler.setSQLDataSource().getConnection();
            conn.setAutoCommit(false);
    
            try (PreparedStatement pstmtAggiornaPrenotazione = conn.prepareStatement(queryAggiornaPrenotazione)) {
                pstmtAggiornaPrenotazione.setString(1, dataInizio);
                pstmtAggiornaPrenotazione.setString(2, dataFine);
                pstmtAggiornaPrenotazione.setInt(3, codPrenotazione);
                int affRowsPrenotazione = pstmtAggiornaPrenotazione.executeUpdate();
    
                try (PreparedStatement pstmtAggiornaUtilizzo = conn.prepareStatement(queryAggiornaUtilizzo)) {
                    pstmtAggiornaUtilizzo.setInt(1, idVeicolo);
                    pstmtAggiornaUtilizzo.setInt(2, codPrenotazione);
                    int affRowsUtilizzo = pstmtAggiornaUtilizzo.executeUpdate();
    
                    if (affRowsPrenotazione > 0 && affRowsUtilizzo > 0) {
                        conn.commit();
                        return "Prenotazione modificata correttamente";
                    } else {
                        conn.rollback();
                        return "Modifica non riuscita. Riprovare.";
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
    
    
    /**
     * OP9 - CANCELLARE UNA PRENOTAZIONE
     * Eliminare la prenotazione rispettiva al codPrenotazione
     * inserito.
     * 
     * @param prenotazione
     */
    public String eliminaPrenotazione(int codPrenotazione) {
        String queryEliminaPrenotazione = "DELETE FROM prenotazioni WHERE codPrenotazione = ?";
        String queryEliminaUtilizzo = "DELETE FROM utilizzo WHERE idPrenotazione = ?";
        
        Connection conn = null;
        try {
            conn = dbHandler.setSQLDataSource().getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtEliminaPrenotazione = conn.prepareStatement(queryEliminaPrenotazione)) {
                pstmtEliminaPrenotazione.setInt(1, codPrenotazione);
                int affRowsPrenotazione = pstmtEliminaPrenotazione.executeUpdate();
    
                try (PreparedStatement pstmtEliminaUtilizzo = conn.prepareStatement(queryEliminaUtilizzo)) {
                    pstmtEliminaUtilizzo.setInt(1, codPrenotazione);
                    int affRowsUtilizzo = pstmtEliminaUtilizzo.executeUpdate();
    
                    if (affRowsPrenotazione > 0 && affRowsUtilizzo > 0) {
                        conn.commit();
                        return "Prenotazione eliminata correttamente";
                    } else {
                        conn.rollback();
                        return "Eliminazione non riuscita. Riprovare.";
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
