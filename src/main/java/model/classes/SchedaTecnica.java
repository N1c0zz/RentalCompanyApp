package src.main.java.model.classes;

public class SchedaTecnica {
    
    private int numeroScheda;
    private String tipoVeicolo;
    private String casaProduttrice;
    private String modello;
    private int annoDiProduzione;
    private int numeroPosti;
    private int numeroPorte;
    private float capacitàBagagliaio;
    private String errorMessage;

    public SchedaTecnica() {

    }
    
    public SchedaTecnica(int numeroScheda, String tipoVeicolo, String casaProduttrice, String modello, int annoDiProduzione,
                            int numeroPosti, int numeroPorte, float capacitàBagagliaio) {
        this.numeroScheda = numeroScheda;
        this.tipoVeicolo = tipoVeicolo;
        this.casaProduttrice = casaProduttrice;
        this.modello = modello;
        this.annoDiProduzione = annoDiProduzione;
        this.numeroPosti = numeroPosti;
        this.numeroPorte = numeroPorte;
        this.capacitàBagagliaio = capacitàBagagliaio;
    }

    public SchedaTecnica(String tipoVeicolo, String casaProduttrice, String modello, int annoDiProduzione,
                            int numeroPosti, int numeroPorte, float capacitàBagagliaio) {
        this.tipoVeicolo = tipoVeicolo;
        this.casaProduttrice = casaProduttrice;
        this.modello = modello;
        this.annoDiProduzione = annoDiProduzione;
        this.numeroPosti = numeroPosti;
        this.numeroPorte = numeroPorte;
        this.capacitàBagagliaio = capacitàBagagliaio;
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

    public float getCapacitàBagagliaio() {
        return capacitàBagagliaio;
    }

    public void setCapacitàBagagliaio(float capacitàBagagliaio) {
        this.capacitàBagagliaio = capacitàBagagliaio;
    }

    @Override
    public String toString() {
        return "SchedaTecnica [numeroScheda=" + numeroScheda + ", tipoVeicolo=" + tipoVeicolo + ", casaProduttrice="
                + casaProduttrice + ", modello=" + modello + ", annoDiProduzione=" + annoDiProduzione + ", numeroPosti="
                + numeroPosti + ", numeroPorte=" + numeroPorte + ", capacitàBagagliaio=" + capacitàBagagliaio + "]";
    }
}
