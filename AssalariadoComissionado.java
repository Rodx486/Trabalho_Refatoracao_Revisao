public class AssalariadoComissionado extends Comissionado{
    private float salarioBase;

    public AssalariadoComissionado (String nome, String endereco, int cpf, int matricula, float vendasBrutas, float taxaComissao, float salarioBase){
        super(nome, endereco, cpf, matricula, vendasBrutas, taxaComissao);
        this.salarioBase = salarioBase;
    }
    
    @Override
    public float calcularPagamento() {
        
        return (salarioBase * 1.10f) + super.calcularPagamento();
    }
    
}
