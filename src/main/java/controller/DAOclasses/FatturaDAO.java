package src.main.java.controller.DAOclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import src.main.java.controller.DBHandler.DataBaseHandler;

public class FatturaDAO {

    private DataBaseHandler dbHandler = new DataBaseHandler();

    public FatturaDAO(DataBaseHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    /**
     * OP13 - EMETTERE UNA NUOVA FATTURA
     *
     * @param metodoDiPagamento
     * @param numeroPrenotazione
     */
    public String emettiFattura(String metodoDiPagamento, int numeroPrenotazione) {
    
        String query1 = "INSERT INTO fatture (data, metodoDiPagamento, importoTotale)"+ 
                        " VALUES (CURDATE(), ?, (SELECT SUM(n.costo) AS costoTotale FROM noleggi n" + 
                        " JOIN prenotazioni p ON n.codPrenotazione = p.codPrenotazione WHERE" +
                        " p.codPrenotazione = ?))";
        
        String query2 = "UPDATE prenotazioni SET fattura = LAST_INSERT_ID()" +
                        " WHERE codPrenotazione = ?";

        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt1 = conn.prepareStatement(query1);
             PreparedStatement pstmt2 = conn.prepareStatement(query2)) {
                pstmt1.setString(1, metodoDiPagamento);
                pstmt1.setInt(2, numeroPrenotazione);
                pstmt2.setInt(1, numeroPrenotazione);

                int affRows1 = pstmt1.executeUpdate();
                int affRows2 = pstmt2.executeUpdate();

                if(affRows1 > 0 && affRows2 > 0) {
                    return "Fattura emessa correttamente";
                } else {
                    return "Emissione fattura non riuscita. Riprovare.";
                }

            } catch (SQLException e) {
                return e.getMessage();
            }
    }
    
}
