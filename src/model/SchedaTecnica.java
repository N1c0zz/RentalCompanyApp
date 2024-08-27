package src.main.java.application.model;

public class SchedaTecnica {
    
    private int numeroScheda;
    private String tipoVeicolo;
    private String casaProduttrice;
    private String modello;
    private int annoDiProduzione;
    private int numeroPosti;
    private int numeroPorte;
    private float capacitaBagagliaio;
    private String errorMessage;

    public SchedaTecnica() {

    }
    
    public SchedaTecnica(int numeroScheda, String tipoVeicolo, String casaProduttrice, String modello, int annoDiProduzione,
                            int numeroPosti, int numeroPorte, float capacitaBagagliaio) {
        this.numeroScheda = numeroScheda;
        this.tipoVeicolo = tipoVeicolo;
        this.casaProduttrice = casaProduttrice;
        this.modello = modello;
        this.annoDiProduzione = annoDiProduzione;
        this.numeroPosti = numeroPosti;
        this.numeroPorte = numeroPorte;
        this.capacitaBagagliaio = capacitaBagagliaio;
    }

    public SchedaTecnica(String tipoVeicolo, String casaProduttrice, String modello, int annoDiProduzione,
                            int numeroPosti, int numeroPorte, float capacitaBagagliaio) {
        this.tipoVeicolo = tipoVeicolo;
        this.casaProduttrice = casaProduttrice;
        this.modello = modello;
        this.annoDiProduzione = annoDiProduzione;
        this.numeroPosti = numeroPosti;
        this.numeroPorte = numeroPorte;
        this.capacitaBagagliaio = capacitaBagagliaio;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getNumeroScheda() {
        return numeroScheda;
    }

    public void setNumeroScheda(int numeroScheda) {
        this.numeroScheda = numeroScheda;
    }

    public String getTipoVeicolo() {
        return tipoVeicolo;
    }

    public void setTipoVeicolo(String tipoVeicolo) {
        this.tipoVeicolo = tipoVeicolo;
    }

    public String getCasaProduttrice() {
        return casaProduttrice;
    }

    public void setCasaProduttrice(String casaProduttrice) {
        this.casaProduttrice = casaProduttrice;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public int getAnnoDiProduzione() {
        return annoDiProduzione;
    }

    public void setAnnoDiProduzione(int annoDiProduzione) {
        this.annoDiProduzione = annoDiProduzione;
    }
    public int getNumeroPosti() {
        return numeroPosti;
    }

    public void setNumeroPosti(int numeroPosti) {
        this.numeroPosti = numeroPosti;
    }

    public int getNumeroPorte() {
        return numeroPorte;
    }
    public void setNumeroPorte(int numeroPorte) {
        this.numeroPorte = numeroPorte;
    }

    public float getCapacitaBagagliaio() {
        return capacitaBagagliaio;
    }

    public void setCapacitaBagagliaio(float capacitaBagagliaio) {
        this.capacitaBagagliaio = capacitaBagagliaio;
    }

    @Override
    public String toString() {
        return "SchedaTecnica [numeroScheda=" + numeroScheda + ", tipoVeicolo=" + tipoVeicolo + ", casaProduttrice="
                + casaProduttrice + ", modello=" + modello + ", annoDiProduzione=" + annoDiProduzione + ", numeroPosti="
                + numeroPosti + ", numeroPorte=" + numeroPorte + ", capacitaBagagliaio=" + capacitaBagagliaio + "]";
    }
}
