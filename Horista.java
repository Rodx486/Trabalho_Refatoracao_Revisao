public class Horista extends Funcionario {
    // valor da hora é definido na hora de instanciar o objeto em memoria
    private float valorHora = 0;
    private String[] datas = new String[7];
    private float[] horasPorDia = new float[7];
    private int qtdDias = 0;

    public Horista(String nome, String endereco, int cpf, int matricula, float valorhora) {
        // Chamamos o construtor da super classe
        super(nome, endereco, cpf, matricula);
        setValorHora(valorhora);
    }

    public void setValorHora(float valorHora) {
        if (valorHora < 0) {
            throw new DadosInvalidosException("Valor da hora não pode ser negativo.");
        }
        this.valorHora = valorHora;
    }

    public void registrarHorasDia(String data, float horas) {
        if (horas < 0) {
            throw new DadosInvalidosException("Horas trabalhadas não podem ser negativas.");
        }
        if (qtdDias >=7) {
            throw new DadosInvalidosException("Limite de 7 dias na semana atingido.");
        }
        datas[qtdDias] = data;
        horasPorDia[qtdDias] = horas;
        qtdDias++;
    }

    public void validarJornada() throws ExcessoJornadaException {
        final float LIMITE_LEGAL = 10.0f;
        for (int i = 0; i < qtdDias; i++) {
            if (horasPorDia[i] > LIMITE_LEGAL) {
                setAlertaHoraExtra(true);
                throw new ExcessoJornadaException(
                        "Excesso de jornada diária para " + getNome() + " em " + datas[i],
                        getNome(),
                        datas[i],
                        horasPorDia[i],
                        LIMITE_LEGAL);
            }
        }
    }

    @Override
    public float calcularPagamento() {
        float totalHoras = 0;
        for (int i = 0; i < qtdDias; i++) {
            totalHoras += horasPorDia[i];
        }
        if (totalHoras <= 40) {
            return totalHoras * valorHora;
        } else {
            float horasExtras = totalHoras - 40;
            return (40 * valorHora) + (horasExtras * valorHora * 1.5f);
        }
    }

}
