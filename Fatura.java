public class Fatura implements Pagamento {

    private String codigo;
    private String descricao;
    private int quantidade;
    private float precoUnitario;

    public Fatura(String codigo, String descricao, int quantidade, float precoUnitario) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(float precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    @Override
    public float calcularPagamento() {
        return quantidade * precoUnitario;
    }

    @Override
    public String toString() {
        return "Fatura #" + codigo + " - " + descricao + " (" + quantidade + "x R$ " + precoUnitario + ")";
    }

}
