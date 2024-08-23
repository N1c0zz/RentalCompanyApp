package src.main.java.controller.DAOclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import src.main.java.controller.DBHandler.DataBaseHandler;

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
    public String addPerson(String CF, String nome, String cognome, String via, int civico, String citta,
                            String CAP, String numeroTelefono, String indirizzoEmail) {
        String query = "INSERT INTO persone (codiceFiscale, nome, cognome, indirizzo_via, indirizzo_numeroCivico, indirizzo_città,"+ 
                        "indirizzo_CAP, numeroDiTelefono, indirizzoEmail) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
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
                
                if(affRows > 0) {
                    return "Inserimento avvenuto correttamente";
                    
                }else {
                    return "Inserimento non riuscito. Riprovare.";
                }

            } catch (SQLException e) {
                return e.getMessage();
            }
    }
}
