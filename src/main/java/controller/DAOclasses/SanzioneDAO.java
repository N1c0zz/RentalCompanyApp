package src.main.java.controller.DAOclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import src.main.java.controller.DBHandler.DataBaseHandler;

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

        String query1 = "INSERT INTO sanzioni (motivazione, costoApplicato) VALUES (?, ?)";

        String query2 = "INSERT INTO inclusioni (fattura, codSanzione) VALUES ((SELECT fattura" +
                        " FROM prenotazioni WHERE codPrenotazione = ?), LAST_INSERT_ID())";
        
        String query3 = "UPDATE fatture SET importoTotale = importoTotale + (SELECT SUM(s.costoApplicato)" +
                        " FROM inclusioni i JOIN sanzioni s ON i.codSanzione = s.codSanzione WHERE i.fattura" +
                        " = (SELECT fattura FROM prenotazioni WHERE codPrenotazione = ?))" +
                        " WHERE numeroFattura = (SELECT fattura FROM prenotazioni WHERE codPrenotazione = ?)";

        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt1 = conn.prepareStatement(query1);
             PreparedStatement pstmt2 = conn.prepareStatement(query2);
             PreparedStatement pstmt3 = conn.prepareStatement(query3);) {

                pstmt1.setString(1, motivazione);
                pstmt1.setFloat(2, costoApplicato);
                pstmt2.setInt(1, codPrenotazione);
                pstmt3.setInt(1, codPrenotazione);
                pstmt3.setInt(2, codPrenotazione);

                int affRows1 = pstmt1.executeUpdate();
                int affRows2 = pstmt2.executeUpdate();
                int affRows3 = pstmt3.executeUpdate();

                if(affRows1 > 0 && affRows2 > 0 && affRows3 > 0){
                    return "Sanzione generata correttamente";
                } else {
                    return "Generazione non riuscita. Riprovare";
                }
                
            } catch (SQLException e) {
                return e.getMessage();
            }
    }
}
