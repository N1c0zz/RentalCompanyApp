package src.main.java.model.classes;

public class Impiegato {

    private String CFImpiegato;
    private String errorMessage;

    public Impiegato() {

    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    public Impiegato(String CFImpiegato) {
        this.CFImpiegato = CFImpiegato;
    }    
    
    public String getCFImpiegato() {
        return CFImpiegato;
    }

    public void setCFImpiegato(String cFImpiegato) {
        CFImpiegato = cFImpiegato;
    }

    @Override
    public String toString() {
        return "Impiegato [CFImpiegato=" + CFImpiegato + "]";
    }
}