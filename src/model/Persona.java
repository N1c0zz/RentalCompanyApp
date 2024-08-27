package src.model;

public class Persona {
    
    private String CF;
    private String ruolo;
    private String nome;
    private String cognome;
    private String indirizzo_via;
    private int indirizzo_numeroCivico;
    private String indirizzo_citta;
    private int indirizzo_CAP;
    private String numeroDiTelefono;
    private String indirizzoEmail;
    private String errorMessage;

    public Persona() {

    }

    public Persona(String CF, String ruolo, String nome, String cognome, String indirizzo_via, int indirizzo_numeroCivico, String indirizzo_citta,
                   int indirizzo_CAP, String numeroDiTelefono, String indirizzoEmail) {
        super();
        this.CF = CF;
        this.ruolo = ruolo;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo_via = indirizzo_via;
        this.indirizzo_numeroCivico = indirizzo_numeroCivico;
        this.indirizzo_citta = indirizzo_citta;
        this.indirizzo_CAP = indirizzo_CAP;
        this.numeroDiTelefono = numeroDiTelefono;
        this.indirizzoEmail = indirizzoEmail;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }   

    public String getCF() {
        return CF;
    }

    public void setCF(String cF) {
        CF = cF;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo_via() {
        return indirizzo_via;
    }

    public void setIndirizzo_via(String indirizzo_via) {
        this.indirizzo_via = indirizzo_via;
    }

    public int getIndirizzo_numeroCivico() {
        return indirizzo_numeroCivico;
    }

    public void setIndirizzo_numeroCivico(int indirizzo_numeroCivico) {
        this.indirizzo_numeroCivico = indirizzo_numeroCivico;
    }

    public String getIndirizzo_citta() {
        return indirizzo_citta;
    }

    public void setIndirizzo_citta(String indirizzo_citta) {
        this.indirizzo_citta = indirizzo_citta;
    }

    public int getIndirizzo_CAP() {
        return indirizzo_CAP;
    }

    public void setIndirizzo_CAP(int indirizzo_CAP) {
        this.indirizzo_CAP = indirizzo_CAP;
    }

    public String getNumeroDiTelefono() {
        return numeroDiTelefono;
    }

    public void setNumeroDiTelefono(String numeroDiTelefono) {
        this.numeroDiTelefono = numeroDiTelefono;
    }

    public String getIndirizzoEmail() {
        return indirizzoEmail;
    }
    
    public void setIndirizzoEmail(String indirizzoEmail) {
        this.indirizzoEmail = indirizzoEmail;
    }
    @Override
    public String toString() {
        return "Persone [CF=" + CF + ", ruolo=" + ruolo + ", nome=" + nome + ", cognome=" + cognome + ", indirizzo_via="
                + indirizzo_via + ", indirizzo_numeroCivico=" + indirizzo_numeroCivico + ", indirizzo_citta="
                + indirizzo_citta + ", indirizzo_CAP=" + indirizzo_CAP + ", numeroDiTelefono=" + numeroDiTelefono
                + ", indirizzoEmail=" + indirizzoEmail + "]";
    }

}
