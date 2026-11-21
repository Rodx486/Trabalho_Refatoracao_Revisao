public class Comissionado extends Funcionario {

    private float vendasBrutas;
    private float taxaComissao;

    public Comissionado(String nome, String endereco, int cpf, int matricula, float vendasBrutas, float taxaComissao) {
        super(nome, endereco, cpf, matricula);
        setVendasBrutas(vendasBrutas);
        setTaxaComissao(taxaComissao);
    }

    public void setVendasBrutas(float vendasBrutas) {
        if (vendasBrutas < 0) {
            throw new DadosInvalidosException("Vendas brutas não podem ser negativas.");
        }
        this.vendasBrutas = vendasBrutas;
    }

    public void setTaxaComissao(float taxaComissao) {
        if (taxaComissao < 0 || taxaComissao > 100) {
            throw new DadosInvalidosException("Taxa de comissão deve estar entre 0% e 100%.");
        }
        this.taxaComissao = taxaComissao;
    }

    @Override
    public float calcularPagamento() {
        return vendasBrutas * (taxaComissao / 100f);
        /*
         * Resumindo: como taxaComissao é float, tanto taxaComissao
         * 100 quanto taxaComissao / 100f funcionam perfeitamente —
         * 100f é apenas uma forma mais explícita e segura
         */
    }

}