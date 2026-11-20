
public class Horista extends Funcionario {
    // valor da hora é definido na hora de instanciar o objeto em memoria
    private float valorHora = 0;

    public Horista(String nome, String endereco, int cpf, int matricula, float valorhora) {

        // Chamamos o construtor da super classe
        super(nome, endereco, cpf, matricula);
        this.valorHora = valorhora;

    }

    @Override
    public float calcularPagamento() {
        float totalHoras = 0;
        for (float horasDia : getHorasPorDia()) {
            totalHoras += horasDia;
        }
        if (totalHoras <= 40) {
            return totalHoras * valorHora;
        } else {
            float horasExtras = totalHoras - 40;
            return (40 * valorHora) + (horasExtras * valorHora * 1.5f);
        }
    }

}