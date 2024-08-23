package src.main.java.controller.DAOclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.main.java.controller.DBHandler.DataBaseHandler;
import src.main.java.model.Noleggio;

public class ClienteDAO {
    
    private DataBaseHandler dbHandler = new DataBaseHandler();

    public ClienteDAO(DataBaseHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    /**
     * OP4 - REGISTRARE UN NUOVO CLIENTE
     * 
     * @param cliente
     */
    public String addClient(String CF, String patente, String via, int numeroCivico, String citta, String CAP) {
        String query = "INSERT INTO clienti (CFCliente, numeroPatenteGuida, indirizzoFatturazione_via, indirizzoFatturazione_numeroCivico,"+ 
                        "indirizzoFatturazione_città, indirizzoFatturazione_CAP) VALUES (?, ?, ?, ?, ?, ?)"; 
        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, CF);
                pstmt.setString(2, patente);
                pstmt.setString(3, via);
                pstmt.setInt(4, numeroCivico);
                pstmt.setString(5, citta);
                pstmt.setString(6, CAP);
                int affRows = pstmt.executeUpdate();

                if(affRows > 0) {
                    return "Inserimento avvenuto correttamente";
                } else {
                    return "Inserimento non riuscito. Riprovare.";
                }
            } catch (SQLException e) {
                return e.getMessage();
            }
    }


    /**
     * OP5 - VISUALIZZARE LO STORICO NOLEGGI DI UN CLIENTE
     * 
     * @param codiceFiscale
     */
    public Noleggio storicoNoleggi (String codiceFiscale) {

        String query =  "SELECT * FROM noleggi WHERE codPrenotazione = (SELECT codPrenotazione" +
                        " FROM prenotazioni WHERE cliente = ?)";

        Noleggio noleggioTemp = new Noleggio();

        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, codiceFiscale);
                
                ResultSet rs = pstmt.executeQuery();

                while(rs.next()){
                    noleggioTemp.setCodNoleggio(rs.getInt("codNoleggio"));
                    noleggioTemp.setCodPrenotazione(rs.getInt("codPrenotazione"));
                    noleggioTemp.setIdVeicolo(rs.getInt("veicolo"));
                    noleggioTemp.setCosto(rs.getFloat("costo"));
                }
                return noleggioTemp;

            } catch (SQLException e) {
                noleggioTemp.setErrorMessage("ERRORE: " + e.getMessage());;
                return noleggioTemp;
            }
    }

    /**
     * OP6 - VISUALIZZARE I 3 CLIENTI CON PIU NOLEGGI
     */
    public void classificaClienti() {

        String query =  "SELECT CFCliente FROM clienti ORDER BY numeroNoleggiConclusi DESC LIMIT 3";

        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
                
                ResultSet rs = pstmt.executeQuery();

                while(rs.next()){
                    String codiceFiscale = rs.getString("CFCliente");
                    System.out.println("CF CLIENTE: " + codiceFiscale);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
