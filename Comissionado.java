public class Comissionado extends Funcionario {
   
    private float vendasBrutas;
    private float taxaComissao;

    public Comissionado(String nome, String endereco, int cpf, int matricula, float vendasBrutas, float taxaComissao){
        super(nome, endereco, cpf, matricula);
        this.vendasBrutas = vendasBrutas;
        this.taxaComissao = taxaComissao;
    }

}