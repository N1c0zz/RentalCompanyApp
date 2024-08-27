package src.model;

public class Prenotazione {
    
    private int codPrenotazione;
    private String cliente;
    private int numeroFattura;
    private String statoPrenotazione;
    private String dataInizio;
    private String dataFine;
    private String numeroNoleggiRichiesti;
    private String errorMessage;

    public Prenotazione() {

    }

    public Prenotazione(String cliente, int numeroFattura, String statoPrenotazione, String dataInizio, 
                        String dataFine, String numeroNoleggiRichiesti) {
        this.cliente = cliente;
        this.numeroFattura = numeroFattura;
        this.statoPrenotazione = statoPrenotazione;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.numeroNoleggiRichiesti = numeroNoleggiRichiesti;
    }

    public Prenotazione(int codPrenotazione, String cliente, int numeroFattura, String statoPrenotazione, String dataInizio, 
                        String dataFine, String numeroNoleggiRichiesti) {
        this.codPrenotazione = codPrenotazione;
        this.cliente = cliente;
        this.numeroFattura = numeroFattura;
        this.statoPrenotazione = statoPrenotazione;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.numeroNoleggiRichiesti = numeroNoleggiRichiesti;
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
    public String getCliente() {
        return cliente;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    public int getNumeroFattura() {
        return numeroFattura;
    }
    public void setNumeroFattura(int numeroFattura) {
        this.numeroFattura = numeroFattura;
    }
    public String getStatoPrenotazione() {
        return statoPrenotazione;
    }
    public void setStatoPrenotazione(String statoPrenotazione) {
        this.statoPrenotazione = statoPrenotazione;
    }
    public String getDataInizio() {
        return dataInizio;
    }
    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }
    public String getDataFine() {
        return dataFine;
    }
    public void setDataFine(String dataFine) {
        this.dataFine = dataFine;
    }
    public String getNumeroNoleggiRichiesti() {
        return numeroNoleggiRichiesti;
    }
    public void setNumeroNoleggiRichiesti(String numeroNoleggiRichiesti) {
        this.numeroNoleggiRichiesti = numeroNoleggiRichiesti;
    }
    @Override
    public String toString() {
        return "Prenotazione [codPrenotazione=" + codPrenotazione + ", cliente=" + cliente + ", numeroFattura="
                + numeroFattura + ", statoPrenotazione=" + statoPrenotazione + ", dataInizio=" + dataInizio
                + ", dataFine=" + dataFine + ", numeroNoleggiRichiesti=" + numeroNoleggiRichiesti + "]";
    }
}
