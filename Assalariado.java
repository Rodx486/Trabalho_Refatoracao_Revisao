public class Assalariado extends Funcionario {

    private float salarioSemanal;

    public Assalariado(String nome, String endereco, int cpf, int matricula, float salarioSemanal) {
        super(nome, endereco, cpf, matricula);
        setSalarioSemanal(salarioSemanal);

    }

    public void setSalarioSemanal(float salarioSemanal) {
        if (salarioSemanal < 0) {
            throw new DadosInvalidosException("Salário semanal não pode ser negativo.");
        }
        this.salarioSemanal = salarioSemanal;
    }

    @Override
    public float calcularPagamento() {

        return salarioSemanal;

    }

}
