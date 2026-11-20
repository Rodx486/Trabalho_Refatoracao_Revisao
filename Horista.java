import java.util.ArrayList;
import java.util.List;

public class Horista extends Funcionario  {

    private float salarioBase = 1500;
    private float valorHora
   
    public Horista(String nome, String endereco, int cpf, int matricula, float salarioBase){
        
        // Chamamos o construtor da super classe
        super(nome, endereco, matricula, matricula);
        this.salarioBase = salarioBase;

    }
    
    @Override 
   
    public float calcularPagamento() {

        float salarioHora = salarioBase/40;
        // TODO Auto-generated method stub
        return 0;
    }



    
}