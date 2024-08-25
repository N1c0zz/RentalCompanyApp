package src.main.java.controller.DAOclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.main.java.controller.DBHandler.DataBaseHandler;
import src.main.java.model.SchedaTecnica;

public class SchedaTecnicaDAO {

    private DataBaseHandler dbHandler = new DataBaseHandler();

    public SchedaTecnicaDAO() {

    }
    /**
     * OP3 - VISUALIZZARE LA SCHEDA TECNICA DI UN VEICOLO
     * 
     * @param idVeicolo
     */
    public SchedaTecnica ottieniSchedaTecnica(int idVeicolo) {
        String query = "SELECT * FROM schedeTecniche WHERE numeroScheda = (SELECT schedaTecnica" +
                        " FROM veicoli WHERE idVeicolo = ?)";

        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, idVeicolo);
                
                ResultSet rs = pstmt.executeQuery();

                while(rs.next()){
                    SchedaTecnica schedaTecnica = new SchedaTecnica();
                    schedaTecnica.setNumeroScheda(rs.getInt("numeroScheda"));
                    schedaTecnica.setTipoVeicolo(rs.getString("tipoVeicolo"));
                    schedaTecnica.setCasaProduttrice(rs.getString("casaProduttrice"));
                    schedaTecnica.setModello(rs.getString("modello"));
                    schedaTecnica.setAnnoDiProduzione(rs.getInt("annoDiProduzione"));
                    schedaTecnica.setNumeroPosti(rs.getInt("numeroPosti"));
                    schedaTecnica.setNumeroPorte(rs.getInt("numeroPorte"));
                    schedaTecnica.setCapacitàBagagliaio(rs.getFloat("capacitàBagagliaio"));
                    return schedaTecnica;
                }

            return new SchedaTecnica();
            } catch (SQLException e) {
                e.getMessage();
                return new SchedaTecnica();
            }
    }
}
