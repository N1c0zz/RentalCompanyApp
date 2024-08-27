package src.controller.DAOclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import src.controller.DBHandler.DataBaseHandler;

public class FatturaDAO {

    private DataBaseHandler dbHandler = new DataBaseHandler();

    public FatturaDAO(DataBaseHandler dbHandler) {
        this.dbHandler = dbHandler;
    }
    /**
     * OP14 - EMETTERE UNA NUOVA FATTURA PER UNA PRENOTAZIONE
     *
     * @param metodoDiPagamento
     * @param numeroPrenotazione
     */
    public String emettiFattura(String metodoDiPagamento, int numeroPrenotazione) {
    
        String query = "INSERT INTO fatture (prenotazione, data, metodoDiPagamento, importoTotale)"+ 
                        " VALUES (?, CURDATE(), ?, (SELECT SUM(n.costo) AS costoTotale FROM noleggi n" + 
                        " JOIN prenotazioni p ON n.codPrenotazione = p.codPrenotazione WHERE" +
                        " p.codPrenotazione = ?))";

        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, numeroPrenotazione);
                pstmt.setString(2, metodoDiPagamento);
                pstmt.setInt(3, numeroPrenotazione);

                int affRows = pstmt.executeUpdate();

                if(affRows > 0) {
                    return "Fattura emessa correttamente";
                } else {
                    return "Emissione fattura non riuscita. Riprovare.";
                }
            } catch (SQLException e) {
                return e.getMessage();
            }
    } 
    /**
     * OP16 - VISUALIZZARE IL FATTURATO MENSILE
     * 
     * @return lista di stringhe.
     */
    public List<String> fatturatoMensile () {

        String query = "SELECT DATE_FORMAT (data, '%Y-%m') AS mese_anno, SUM(importoTotale) AS fatturato_mensile" +
                        " FROM fatture GROUP BY DATE_FORMAT(data, '%Y-%m') ORDER BY mese_anno";

        List<String> lista = new ArrayList<>();

        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
                ResultSet rs = pstmt.executeQuery();

                while(rs.next()) {
                    String mese_anno = rs.getString("mese_anno");
                    Float fatturato = rs.getFloat("fatturato_mensile");
                    lista.add(mese_anno);
                    lista.add(Float.toString(fatturato));
                }
                return lista;

            } catch (SQLException e) {
                lista.add(e.getMessage());
                return lista;
            }
    }
}
