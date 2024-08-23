package src.main.java.model;

public class Sanzione {
    
    private int codSanzione;
    private String motivazione;
    private float costoApplicato;
    private String errorMessage;

    public Sanzione() {

    }

    public Sanzione(int codSanzione, String motivazione, float costoApplicato) {
        this.codSanzione = codSanzione;
        this.motivazione = motivazione;
        this.costoApplicato = costoApplicato;
    }

    public Sanzione(String motivazione, float costoApplicato) {
        this.motivazione = motivazione;
        this.costoApplicato = costoApplicato;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }    

    public int getCodSanzione() {
        return codSanzione;
    }
    
    public void setCodSanzione(int codSanzione) {
        this.codSanzione = codSanzione;
    }

    public String getMotivazione() {
        return motivazione;
    }

    public void setMotivazione(String motivazione) {
        this.motivazione = motivazione;
    }

    public float getCostoApplicato() {
        return costoApplicato;
    }

    public void setCostoApplicato(float costoApplicato) {
        this.costoApplicato = costoApplicato;
    }

    @Override
    public String toString() {
        return "Sanzione [codSanzione=" + codSanzione + ", motivazione=" + motivazione + ", costoApplicato="
                + costoApplicato + "]";
    }
}
