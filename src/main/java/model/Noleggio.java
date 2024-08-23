package src.main.java.model;

public class Noleggio {
    
    private int codNoleggio;
    private int codPrenotazione;
    private int idVeicolo;
    private float costo;
    private String errorMessage;

    public Noleggio() {

    }

    public Noleggio(int codNoleggio, int codPrenotazione, int idVeicolo, float costo) {
        this.codNoleggio = codNoleggio;
        this.codPrenotazione = codPrenotazione;
        this.idVeicolo = idVeicolo;
        this.costo = costo;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getCodNoleggio() {
        return codNoleggio;
    }

    public void setCodNoleggio(int codNoleggio) {
        this.codNoleggio = codNoleggio;
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

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "Noleggio [codNoleggio=" + codNoleggio + ", codPrenotazione=" + codPrenotazione + ", idVeicolo="
                + idVeicolo + ", costo=" + costo + "]";
    }
}
