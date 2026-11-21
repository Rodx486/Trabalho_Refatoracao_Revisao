public abstract class Funcionario implements Pagamento {

    private String nome;
    private String endereco;
    private int cpf;
    private int matricula;
    private boolean alertaHoraExtra = false;

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

    protected void setAlertaHoraExtra(boolean alertaHoraExtra) {
        this.alertaHoraExtra = alertaHoraExtra;
    }

    @Override
    public abstract float calcularPagamento();

    @Override
    public String toString() {
        return nome + " (Matrícula: " + matricula + ")";
    }

}