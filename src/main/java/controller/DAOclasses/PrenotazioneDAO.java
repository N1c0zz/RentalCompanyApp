package src.main.java.controller.DAOclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import src.main.java.controller.DBHandler.DataBaseHandler;

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

        String query1 = "INSERT INTO prenotazioni (cliente, dataInizio, dataFine) VALUES (?, ?, ?)";

        String query2 = "INSERT INTO utilizzi (idPrenotazione, numVeicolo) VALUES (LAST_INSERT_ID(), ?)"; 
                       
        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt1 = conn.prepareStatement(query1);
             PreparedStatement pstmt2 = conn.prepareStatement(query2)) {
                pstmt1.setString(1, CF);
                pstmt1.setString(2, dataInizio);
                pstmt1.setString(3, dataInizio);
                pstmt2.setInt(1, idVeicolo);
                int affRows1 = pstmt1.executeUpdate();
                int affRows2 = pstmt2.executeUpdate();
                if(affRows1 > 0 && affRows2 > 0){
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

        String query1 = "INSERT INTO prenotazioni (cliente, dataInizio, dataFine, numeroNoleggiRichiesti) VALUES (?, ?, ?, ?)";

        String query2 = "INSERT INTO utilizzi (idPrenotazione, numVeicolo) VALUES (LAST_INSERT_ID(), ?)";

        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt1 = conn.prepareStatement(query1);
             PreparedStatement pstmt2 = conn.prepareStatement(query2)) {
                pstmt1.setString(1, CF);
                pstmt1.setString(2, dataInizio);
                pstmt1.setString(3, dataInizio);
                pstmt1.setInt(4, 2);
                pstmt2.setInt(1, idVeicolo);
                int affRows1 = pstmt1.executeUpdate();
                int affRows2 = pstmt2.executeUpdate();
                if(affRows1 > 0 && affRows2 > 0){
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
        String query1 = "UPDATE prenotazioni SET dataInizio = ?, dataFine = ? WHERE codPrenotazione = ?";

        String query2 = "UPDATE utilizzo SET numVeicolo = ? WHERE idPrenotazione = ?";

        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt1 = conn.prepareStatement(query1);
             PreparedStatement pstmt2 = conn.prepareStatement(query2)) {
                pstmt1.setString(1, dataInizio);
                pstmt1.setString(2, dataFine);
                pstmt1.setInt(3, codPrenotazione);
                pstmt2.setInt(1, idVeicolo);
                pstmt2.setInt(2, codPrenotazione);
                int affRows1 = pstmt1.executeUpdate();
                int affRows2 = pstmt2.executeUpdate();
                if(affRows1 > 0 && affRows2 > 0){
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
        String query1 = "DELETE FROM prenotazioni WHERE codPrenotazione = ?"; 

        String query2 = "DELETE FROM utilizzo WHERE idPrenotazione = ?";

        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt1 = conn.prepareStatement(query1);
             PreparedStatement pstmt2 = conn.prepareStatement(query2)) {
                pstmt1.setInt(1, codPrenotazione);
                pstmt2.setInt(1, codPrenotazione);
                int affRows1 = pstmt1.executeUpdate();
                int affRows2 = pstmt2.executeUpdate();
                if(affRows1 > 0 && affRows2 > 0){
                    return "Prenotazione registrata correttamente";
                }else{
                    return "Registrazione non riuscita. Riprovare.";
                }
            } catch (SQLException e) {
                return e.getMessage();
            }
    }
    
}
