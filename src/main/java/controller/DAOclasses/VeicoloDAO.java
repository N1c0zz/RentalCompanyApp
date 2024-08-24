package src.main.java.controller.DAOclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList; 
import java.util.List;

import src.main.java.controller.DBHandler.DataBaseHandler;

public class VeicoloDAO {

    private DataBaseHandler dbHandler = new DataBaseHandler();

    public VeicoloDAO(DataBaseHandler dbHandler) {
        this.dbHandler = dbHandler;
    }
    
    /**
     * OP1--REGISTRARE UN NUOVO VEICOLO
     * 
     * @param veicolo
     */
    public String addVehicle(int schedaTecnica, String targa, int chilometraggio, float costoPerGiornata) {

        String query = "INSERT INTO veicoli (schedaTecnica, targa, chilometraggio, costoPerGiornata) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, schedaTecnica);
                pstmt.setString(2, targa);
                pstmt.setInt(3, chilometraggio);
                pstmt.setFloat(4, costoPerGiornata);
                int affRows = pstmt.executeUpdate();

                if(affRows > 0){
                    return "Inserimento avvenuto con successo";
                } else {
                    return "Inserimento non riuscito";
                }
                
            } catch (SQLException e) {
                return e.getMessage();
            }
    }

    /*
     * OP11 - TASSO DI UTILIZZO DI UN VEICOLO
     */
    public List<String> tassoDiUtilizzo(int idveicolo) {

        String query = "SELECT v.idVeicolo, MONTH(p.dataInizio) AS mese, YEAR(p.dataInizio) AS anno," +
                        "(SUM(DATEDIFF(LEAST(LAST_DAY(p.dataInizio), p.dataFine), GREATEST(DATE_FORMAT" +
                        "(p.dataInizio, '%Y-%m-01'), p.dataInizio)) + 1) / DAY(LAST_DAY(p.dataInizio))) * 100 AS tasso_utilizzo_mensile" +
                        " FROM utilizzi u JOIN prenotazioni p ON u.idPrenotazione = p.codPrenotazione" +
                        " JOIN veicoli v ON u.numVeicolo = v.idVeicolo WHERE v.idVeicolo = ?" +
                        " AND p.dataInizio <= LAST_DAY(p.dataInizio) GROUP BY v.idVeicolo, mese, anno";
        List<String> lista = new ArrayList<>();

        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, idveicolo);

                ResultSet rs = pstmt.executeQuery();

                while(rs.next()) {
                    int idVeicolo = rs.getInt("idVeicolo");
                    int mese = rs.getInt("mese");
                    int anno = rs.getInt("anno");
                    float tassoUtilizzo = rs.getFloat("tasso_utilizzo_mensile");
                    lista.add(Integer.toString(idVeicolo) + "," + Integer.toString(mese) + "," + 
                                Integer.toString(anno) + "," + Float.toString(tassoUtilizzo));
                }
                return lista;

            } catch (SQLException e) {
                lista.add(e.getMessage());
                return lista;
            }
    }

    /**
     * OP12 - VISUALIZZARE I VEICOLI PIU NOLEGGIATI
     */
    public List<String> veicoliPiuNoleggiati() {

        String query = "SELECT v.idVeicolo, CONCAT(YEAR(p.dataInizio), '-', MONTH(p.dataInizio)) AS mese_anno," +
                        "(SUM(DATEDIFF(LEAST(LAST_DAY(p.dataInizio), p.dataFine), GREATEST(DATE_FORMAT" +
                        "(p.dataInizio, '%Y-%m-01'), p.dataInizio)) + 1) / DAY(LAST_DAY(p.dataInizio))) * 100 AS tasso_utilizzo_mensile" +
                        " FROM utilizzi u JOIN prenotazioni p ON u.idPrenotazione = p.codPrenotazione" +
                        " JOIN veicoli v ON u.numVeicolo = v.idVeicolo GROUP BY v.idVeicolo, YEAR(p.dataInizio), MONTH(p.dataInizio)" +
                        " ORDER BY tasso_utilizzo_mensile DESC";

        List<String> lista = new ArrayList<>();

        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

                ResultSet rs = pstmt.executeQuery();

                while(rs.next()) {
                    int idVeicolo = rs.getInt("idVeicolo");
                    String mese_e_anno = rs.getString("mese_anno");
                    float tassoUtilizzo = rs.getFloat("tasso_utilizzo_mensile");
                    lista.add(Integer.toString(idVeicolo) + "," + mese_e_anno + "," + Float.toString(tassoUtilizzo));
                }
                return lista;
                } catch (SQLException e) {
                    lista.add(e.getMessage());
                    return lista;
                }
    }

    /**
     * 
     * OP2 - VISUALIZZARE LA DISPONIBILITA' DI UN VEICOLO
     * 
     * @param idVeicolo
     * @param dataInizio
     * @param dataFine
     */
    public String verificaDisponibilitaVeicolo(int idVeicolo, String dataInizio, String dataFine) {

        String query =  " SELECT CASE WHEN EXISTS (SELECT * FROM prenotazioni p JOIN utilizzi u ON p.codPrenotazione = u.idPrenotazione" +
                        " WHERE u.numVeicolo = ? AND ? <= p.dataFine" +
                        " AND ? >= p.dataInizio)" +
                        " THEN 'Esiste già una prenotazione in questo intervallo di date per questo veicolo'" +
                        " ELSE 'La prenotazione per questo veicolo può essere effettuata'"+
                        " END AS risultato";

        try (Connection conn = dbHandler.setSQLDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, idVeicolo);
                pstmt.setString(2, dataInizio);
                pstmt.setString(3, dataFine);
                
                ResultSet rs = pstmt.executeQuery();

                while(rs.next()){
                    String risultato = rs.getString("risultato");
                    return risultato;
                }
                
                return "La ricerca non ha prodotto risultato";

            } catch (SQLException e) {
                return e.getMessage();
            }
    }
}
