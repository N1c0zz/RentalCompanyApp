package src.main.java.model.classes;

public class Fattura {

    private int numeroFattura;
    private String data;
    private String metodoDiPagamento;
    private float importoTotale;
    private String errorMessage;

    public Fattura() {

    }

    public Fattura(int numeroFattura, String data, String metodoDiPagamento, float importoTotale) {
        this.numeroFattura = numeroFattura;
        this.data = data;
        this.metodoDiPagamento = metodoDiPagamento;
        this.importoTotale = importoTotale;
    }

    public Fattura(String data, String metodoDiPagamento, float importoTotale) {
        this.data = data;
        this.metodoDiPagamento = metodoDiPagamento;
        this.importoTotale = importoTotale;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }    
    
    public int getNumeroFattura() {
        return numeroFattura;
    }
    
    public void setNumeroFattura(int numeroFattura) {
        this.numeroFattura = numeroFattura;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMetodoDiPagamento() {
        return metodoDiPagamento;
    }

    public void setMetodoDiPagamento(String metodoDiPagamento) {
        this.metodoDiPagamento = metodoDiPagamento;
    }
    
    public float getImportoTotale() {
        return importoTotale;
    }

    public void setImportoTotale(float importoTotale) {
        this.importoTotale = importoTotale;
    }

    @Override
    public String toString() {
        return "Fattura [numeroFattura=" + numeroFattura + ", data=" + data + ", metodoDiPagamento=" + 
                metodoDiPagamento + ", importoTotale=" + importoTotale + "]";
    }
}