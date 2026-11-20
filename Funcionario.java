import java.util.ArrayList;
import java.util.List;

public abstract class Funcionario implements Pagamento{

    private String nome;
    private String endereco;
    private int cpf;
    private int matricula;

     // máximo de 10 horas diárias por dia 
    private List<Float> horasPorDia = new ArrayList<>();

    public Funcionario(String nome, String endereco, int cpf, int matricula){
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

    public List<Float> getHorasPorDia() {
        return horasPorDia;
    }

    



   
    

}