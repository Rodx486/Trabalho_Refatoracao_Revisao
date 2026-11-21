public class ExcessoJornadaException extends Exception {

    private String nomeColaborador;
    private String dataRegistro;
    private float horasLancadas;
    private float limiteLocal;

    public ExcessoJornadaException(String mensagem, String nomeColaborador, String dataRegistro, float horasLancadas, float limiteLocal) {
        super(mensagem);
        this.nomeColaborador = nomeColaborador;
        this.dataRegistro = dataRegistro;
        this.horasLancadas = horasLancadas;
        this.limiteLocal = limiteLocal;
    }

    public String getNomeColaborador() {
        return nomeColaborador;
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public float getHorasLancadas() {
        return horasLancadas;
    }

    public float getLimiteLocal() {
        return limiteLocal;
    }
    
}
