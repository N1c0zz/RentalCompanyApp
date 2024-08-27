package src.model;

public class Utilizzo {
    
    private int codPrenotazione;
    private int idVeicolo;
    private String errorMessage;

    public Utilizzo() {

    }

    public Utilizzo(int codPrenotazione, int idVeicolo){
        this.codPrenotazione = codPrenotazione;
        this.idVeicolo = idVeicolo;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getCodPrenotazione() {
        return codPrenotazione;
    }
    public void setCodPrenotazione(int codPrenotazione) {
        this.codPrenotazione = codPrenotazione;
    }
    public int getIdVeicolo() {
        return idVeicolo;
    }
    public void setIdVeicolo(int idVeicolo) {
        this.idVeicolo = idVeicolo;
    }
    @Override
    public String toString() {
        return "Utilizzo [codPrenotazione=" + codPrenotazione + ", idVeicolo=" + idVeicolo + "]";
    }
}
