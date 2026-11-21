public class Fatura implements Pagamento {

    private String codigo;
    private String descricao;
    private int quantidade;
    private float precoUnitario;

    public Fatura(String codigo, String descricao, int quantidade, float precoUnitario) {
        this.codigo = codigo;
        this.descricao = descricao;
        setQuantidade(quantidade);
        setPrecoUnitario(precoUnitario);
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

    public float getPrecoUnitario() {
        return precoUnitario;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new DadosInvalidosException("Alerta: Quantida não pode ser menor ou igual a zero! ");
        }
        this.quantidade = quantidade;
    }

    public void setPrecoUnitario(float precoUnitario) {
        if(precoUnitario < 0) {
            throw new DadosInvalidosException("Alerta: Preço Unitario não pode ser menor do que zero!");
        }
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
