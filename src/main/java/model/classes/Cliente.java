package src.main.java.model.classes;

public class Cliente {
    
    private String CFCliente;
    private String numeroPatenteGuida;
    private String indirizzoFatturazione_via;
    private int indirizzoFatturazione_numeroCivico;
    private String indirizzoFatturazione_città;
    private int indirizzoFatturazione_CAP;
    private int numeroNoleggiConclusi;
    private String errorMessage;

    public Cliente() {

    }

    public Cliente(String CFCliente, String numeroPatenteGuida, String indirizzoFatturazione_via, int indirizzoFatturazione_numeroCivico,
                    String indirizzoFatturazione_città, int indirizzoFatturazione_CAP, int numeroNoleggiConclusi) {

        this.CFCliente = CFCliente;
        this.numeroPatenteGuida = numeroPatenteGuida;
        this.indirizzoFatturazione_via = indirizzoFatturazione_via;
        this.indirizzoFatturazione_numeroCivico = indirizzoFatturazione_numeroCivico;
        this.indirizzoFatturazione_città = indirizzoFatturazione_città;
        this.indirizzoFatturazione_CAP = indirizzoFatturazione_CAP;
        this.numeroNoleggiConclusi = numeroNoleggiConclusi;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCFCliente() {
        return CFCliente;
    }
    public void setCFCliente(String cFCliente) {
        CFCliente = cFCliente;
    }
    public String getNumeroPatenteGuida() {
        return numeroPatenteGuida;
    }
    public void setNumeroPatenteGuida(String numeroPatenteGuida) {
        this.numeroPatenteGuida = numeroPatenteGuida;
    }
    public String getIndirizzoFatturazione_via() {
        return indirizzoFatturazione_via;
    }
    public void setIndirizzoFatturazione_via(String indirizzoFatturazione_via) {
        this.indirizzoFatturazione_via = indirizzoFatturazione_via;
    }
    public int getIndirizzoFatturazione_numeroCivico() {
        return indirizzoFatturazione_numeroCivico;
    }
    public void setIndirizzoFatturazione_numeroCivico(int indirizzoFatturazione_numeroCivico) {
        this.indirizzoFatturazione_numeroCivico = indirizzoFatturazione_numeroCivico;
    }
    public String getIndirizzoFatturazione_città() {
        return indirizzoFatturazione_città;
    }
    public void setIndirizzoFatturazione_città(String indirizzoFatturazione_città) {
        this.indirizzoFatturazione_città = indirizzoFatturazione_città;
    }
    public int getIndirizzoFatturazione_CAP() {
        return indirizzoFatturazione_CAP;
    }
    public void setIndirizzoFatturazione_CAP(int indirizzoFatturazione_CAP) {
        this.indirizzoFatturazione_CAP = indirizzoFatturazione_CAP;
    }
    public int getNumeroNoleggiConclusi() {
        return numeroNoleggiConclusi;
    }
    public void setNumeroNoleggiConclusi(int numeroNoleggiConclusi) {
        this.numeroNoleggiConclusi = numeroNoleggiConclusi;
    }
    @Override
    public String toString() {
        return "Cliente [CFCliente=" + CFCliente + ", numeroPatenteGuida=" + numeroPatenteGuida
                + ", indirizzoFatturazione_via=" + indirizzoFatturazione_via + ", indirizzoFatturazione_numeroCivico="
                + indirizzoFatturazione_numeroCivico + ", indirizzoFatturazione_città=" + indirizzoFatturazione_città
                + ", indirizzoFatturazione_CAP=" + indirizzoFatturazione_CAP + ", numeroNoleggiConclusi="
                + numeroNoleggiConclusi + "]";
    }

}
