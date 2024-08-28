package src.controller.DAOclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import src.controller.DBHandler.DataBaseHandler;

public class PersonaDAO {
    
    private DataBaseHandler dbHandler = new DataBaseHandler();

    public PersonaDAO(DataBaseHandler dbHandler) {
        this.dbHandler = dbHandler;
    }
    /**
     * OP4 - REGISTRARE UNA NUOVA PERSONA
     * 
     * @param persona
     */
    public String aggiungiPersona(String CF, String nome, String cognome, String via, int civico, String citta,
                                String CAP, String numeroTelefono, String indirizzoEmail) {
        String query = "INSERT INTO persone (codiceFiscale, nome, cognome, indirizzo_via, indirizzo_numeroCivico, indirizzo_citta, " +
                    "indirizzo_CAP, numeroDiTelefono, indirizzoEmail) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        try {
            conn = dbHandler.setSQLDataSource().getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, CF);
                pstmt.setString(2, nome);
                pstmt.setString(3, cognome);
                pstmt.setString(4, via);
                pstmt.setInt(5, civico);
                pstmt.setString(6, citta);
                pstmt.setString(7, CAP);
                pstmt.setString(8, numeroTelefono);
                pstmt.setString(9, indirizzoEmail);
                int affRows = pstmt.executeUpdate();

                if (affRows > 0) {
                    conn.commit();
                    return "Inserimento avvenuto correttamente";
                } else {
                    conn.rollback();
                    return "Inserimento non riuscito. Riprovare.";
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
