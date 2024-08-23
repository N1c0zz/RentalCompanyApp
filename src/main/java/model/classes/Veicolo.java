package src.main.java.model.classes;

public class Veicolo {
    
    private int idVeicolo;
    private int numeroSchedaTecnica;
    private String targa;
    private int chilometraggio;
    private String statoManutenzione;
    private float costoPerGiornata;
    private String errorMessage;

    public Veicolo() {

    }

    public Veicolo(int idVeicolo, int numeroSchedaTecnica, String targa, int chilometraggio, String statoManutenzione, float costoPerGiornata) {
        this.idVeicolo = idVeicolo;
        this.numeroSchedaTecnica = numeroSchedaTecnica;
        this.targa = targa;
        this.chilometraggio = chilometraggio;
        this.statoManutenzione = statoManutenzione;
        this.costoPerGiornata = costoPerGiornata;
    }

    public Veicolo(int numeroSchedaTecnica, String targa, int chilometraggio, String statoManutenzione, float costoPerGiornata) {
        this.numeroSchedaTecnica = numeroSchedaTecnica;
        this.targa = targa;
        this.chilometraggio = chilometraggio;
        this.statoManutenzione = statoManutenzione;
        this.costoPerGiornata = costoPerGiornata;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }  

    public int getIdVeicolo() {
        return idVeicolo;
    }
    public void setIdVeicolo(int idVeicolo) {
        this.idVeicolo = idVeicolo;
    }
    public int getNumeroSchedaTecnica() {
        return numeroSchedaTecnica;
    }
    public void setNumeroSchedaTecnica(int numeroSchedaTecnica) {
        this.numeroSchedaTecnica = numeroSchedaTecnica;
    }
    public String getTarga() {
        return targa;
    }
    public void setTarga(String targa) {
        this.targa = targa;
    }
    public int getChilometraggio() {
        return chilometraggio;
    }
    public void setChilometraggio(int chilometraggio) {
        this.chilometraggio = chilometraggio;
    }
    public String getStatoManutenzione() {
        return statoManutenzione;
    }
    public void setStatoManutenzione(String statoManutenzione) {
        this.statoManutenzione = statoManutenzione;
    }
    public float getCostoPerGiornata() {
        return costoPerGiornata;
    }
    public void setCostoPerGiornata(float costoPerGiornata) {
        this.costoPerGiornata = costoPerGiornata;
    }
    @Override
    public String toString() {
        return "Veicolo [idVeicolo=" + idVeicolo + ", numeroSchedaTecnica=" + numeroSchedaTecnica + ", targa=" + targa
                + ", chilometraggio=" + chilometraggio + ", statoManutenzione=" + statoManutenzione
                + ", costoPerGiornata=" + costoPerGiornata + "]";
    }
}
