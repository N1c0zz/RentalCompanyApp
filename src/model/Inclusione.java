package src.model;

public class Inclusione {
    
    private int numeroFattura;
    private int codSanzione;
    private String errorMessage;

    public Inclusione() {

    }

    public Inclusione(int numeroFattura, int codSanzione) {
        this.numeroFattura = numeroFattura;
        this.codSanzione = codSanzione;
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

    public int getCodSanzione() {
        return codSanzione;
    }

    public void setCodSanzione(int codSanzione) {
        this.codSanzione = codSanzione;
    }
    
    @Override
    public String toString() {
        return "Inclusione [numeroFattura=" + numeroFattura + ", codSanzione=" + codSanzione + "]";
    }

}
