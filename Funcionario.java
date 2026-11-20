import java.util.ArrayList;
import java.util.List;

public abstract class Funcionario implements Pagamento {

    private String nome;
    private String endereco;
    private int cpf;
    private int matricula;
    private boolean alertaHoraExtra = false;

    // máximo de 10 horas diárias por dia
    private List<Float> horasPorDia = new ArrayList<>();

    public Funcionario(String nome, String endereco, int cpf, int matricula) {
        this.nome = nome;
        this.endereco = endereco;
        this.cpf = cpf;
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public int getCpf() {
        return cpf;
    }

    public int getMatricula() {
        return matricula;
    }

    public boolean isAlertaHoraExtra() {
        return alertaHoraExtra;

    }

    public List<Float> getHorasPorDia() {
        return horasPorDia;
    }

    public void registrarHorasDias(float horas) throws ExcessoJornadaException {
        this.horasPorDia.add(horas);
        if (horas > 10) {

            this.alertaHoraExtra = true;
            throw new ExcessoJornadaException("ALERTA TRABALHISTA: excesso de hora extra" + " NOME: " + getNome());
        }

    }

    @Override
    public abstract float calcularPagamento();

    @Override
    public String toString() {
        return nome + " (Matrícula: " + matricula + ")";
    }

}