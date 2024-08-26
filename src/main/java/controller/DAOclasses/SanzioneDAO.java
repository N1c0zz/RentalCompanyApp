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

        String query = "INSERT INTO sanzioni (motivazione, costoApplicato) VALUES (?, ?);" +
                        "INSERT INTO inclusioni (fattura, codSanzione) VALUES ((SELECT numeroFattura" +
                        " FROM fatture WHERE prenotazione = ?), LAST_INSERT_ID());" +
                        "UPDATE fatture SET importoTotale = importoTotale + (SELECT SUM(s.costoApplicato)" +
                        " FROM inclusioni i JOIN sanzioni s ON i.codSanzione = s.codSanzione WHERE i.fattura" +
                        " = (SELECT fattura FROM inclusioni WHERE codSanzione = LAST_INSERT_ID()))" +
                        " WHERE prenotazione = ?";

        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setString(1, motivazione);
                pstmt.setFloat(2, costoApplicato);
                pstmt.setInt(3, codPrenotazione);
                pstmt.setInt(4, codPrenotazione);

                int affRows = pstmt.executeUpdate();

                if(affRows > 0){
                    return "Sanzione generata correttamente";
                } else {
                    return "Generazione non riuscita. Riprovare";
                }
                
            } catch (SQLException e) {
                return e.getMessage();
            }
    }
}
