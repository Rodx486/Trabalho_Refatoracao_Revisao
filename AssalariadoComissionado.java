public class AssalariadoComissionado extends Comissionado {
    private float salarioBase;

    public AssalariadoComissionado(String nome, String endereco, int cpf, int matricula, float vendasBrutas,
            float taxaComissao, float salarioBase) {
        super(nome, endereco, cpf, matricula, vendasBrutas, taxaComissao);
        setSalarioBase(salarioBase);
    }

    public void setSalarioBase(float salarioBase) {
        if (salarioBase < 0) {
            throw new DadosInvalidosException("Salário base não pode ser negativo.");
        }
        this.salarioBase = salarioBase;
    }

    @Override
    public float calcularPagamento() {

        return (salarioBase * 1.10f) + super.calcularPagamento();
    }

}
