package src.main.java.controller.DAOclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import src.main.java.controller.DBHandler.DataBaseHandler;

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
    public List<String> storicoNoleggi (String codiceFiscale) {

        String query =  "SELECT n.codNoleggio, n.codPrenotazione, n.veicolo, n.costo, p.dataInizio, p.dataFine," +
                        "p.statoPrenotazione FROM noleggi n JOIN prenotazioni p ON n.codPrenotazione = p.codPrenotazione" +
                        " JOIN clienti c ON p.cliente = c.CFCLiente WHERE c.CFCliente = ? ORDER BY p.dataInizio DESC;";

        List<String> list = new ArrayList<>();

        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, codiceFiscale);
                
                ResultSet rs = pstmt.executeQuery();

                while(rs.next()){
                    list.add(rs.getString(Integer.parseInt("codNoleggio")));
                    list.add(rs.getString(Integer.parseInt("codPrenotazione")));
                    list.add(rs.getString(Integer.parseInt("veicolo")));
                    list.add(rs.getString(Integer.parseInt("costo")));
                    list.add(rs.getString("dataInizio"));
                    list.add(rs.getString("dataFine"));
                }
                return list;

            } catch (SQLException e) {
                list.add(e.getMessage());
                return list;
            }
    }

    /**
     * OP6 - VISUALIZZARE I 3 CLIENTI CON PIU NOLEGGI
     */
    public List<String> classificaClienti() {

        String query =  "SELECT CFCliente FROM clienti ORDER BY numeroNoleggiConclusi DESC LIMIT 3";

        List<String> list = new ArrayList<>();

        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
                
                ResultSet rs = pstmt.executeQuery();

                while(rs.next()){
                    list.add(rs.getString("CFCliente"));
                }
                return list;

            } catch (SQLException e) {
                list.add(e.getMessage());
                return list;
            }
    }
}
