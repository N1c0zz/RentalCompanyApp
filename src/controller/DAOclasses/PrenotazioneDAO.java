package src.controller.DAOclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

        String query = "INSERT INTO prenotazioni (cliente, dataInizio, dataFine) VALUES (?, ?, ?); " +
                        "INSERT INTO utilizzi (idPrenotazione, numVeicolo) VALUES (LAST_INSERT_ID(), ?)"; 
                       
        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, CF);
                pstmt.setString(2, dataInizio);
                pstmt.setString(3, dataInizio);
                pstmt.setInt(4, idVeicolo);
                int affRows = pstmt.executeUpdate();
                if(affRows > 0){
                    return "Prenotazione registrata correttamente";
                }else{
                    return "Registrazione non riuscita. Riprovare.";
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
    public String registraDoppiaPrenotazione(String CF, String dataInizio, String dataFine, int idVeicolo) {

        String query = "INSERT INTO prenotazioni (cliente, dataInizio, dataFine, numeroNoleggiRichiesti) VALUES (?, ?, ?, 2); " +
                        "INSERT INTO utilizzi (idPrenotazione, numVeicolo) VALUES (LAST_INSERT_ID(), ?)"; 
                       
        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, CF);
                pstmt.setString(2, dataInizio);
                pstmt.setString(3, dataInizio);
                pstmt.setInt(4, idVeicolo);
                int affRows = pstmt.executeUpdate();
                if(affRows > 0){
                    return "Prenotazione registrata correttamente";
                }else{
                    return "Registrazione non riuscita. Riprovare.";
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

        String query = "UPDATE prenotazioni SET dataInizio = ?, dataFine = ? WHERE codPrenotazione = ?; " +
                        "UPDATE utilizzo SET numVeicolo = ? WHERE idPrenotazione = ?";

        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, dataInizio);
                pstmt.setString(2, dataFine);
                pstmt.setInt(3, codPrenotazione);
                pstmt.setInt(4, idVeicolo);
                pstmt.setInt(5, codPrenotazione);
                int affRows = pstmt.executeUpdate();
                if(affRows > 0){
                    return "Prenotazione registrata correttamente";
                }else{
                    return "Registrazione non riuscita. Riprovare.";
                }
            } catch (SQLException e) {
                return e.getMessage();
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

        String query = "DELETE FROM prenotazioni WHERE codPrenotazione = ?; " + 
                        "DELETE FROM utilizzo WHERE idPrenotazione = ?";

        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, codPrenotazione);
                pstmt.setInt(2, codPrenotazione);
                int affRows = pstmt.executeUpdate();
                if(affRows > 0){
                    return "Prenotazione registrata correttamente";
                }else{
                    return "Registrazione non riuscita. Riprovare.";
                }
            } catch (SQLException e) {
                return e.getMessage();
            }
    }
}
