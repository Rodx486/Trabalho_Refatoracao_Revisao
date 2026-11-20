public class Comissionado extends Funcionario {

    private float vendasBrutas;
    private float taxaComissao;

    public Comissionado(String nome, String endereco, int cpf, int matricula, float vendasBrutas, float taxaComissao) {
        super(nome, endereco, cpf, matricula);
        this.vendasBrutas = vendasBrutas;
        this.taxaComissao = taxaComissao;
    }


    @Override
    public float calcularPagamento() {
        return vendasBrutas * (taxaComissao / 100f);
        /*Resumindo: como taxaComissao é float, tanto taxaComissao
          100 quanto taxaComissao / 100f funcionam perfeitamente —
          100f é apenas uma forma mais explícita e segura */
    }

}