public class Assalariado extends Funcionario {

    private float salarioSemanal = 1500;

    public Assalariado(String nome, String endereco, int cpf, int matricula, float salarioSemanal) {
        super(nome, endereco, cpf, matricula);
        this.salarioSemanal = salarioSemanal;

    }

    @Override
    public float calcularPagamento() {

        return salarioSemanal;

    }

}
