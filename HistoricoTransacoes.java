import java.util.ArrayDeque;
import java.util.Deque;

public class HistoricoTransacoes {

    private Deque<Pagamento> pilha = new ArrayDeque<>();

    public void registraPagamento(Pagamento p) {
        pilha.push(p);
        System.out.printf("Pagamento empilhado: %s (R$ %.2f)%n", p, p.calcularPagamento());

    }

    public int executarRollback() {
        System.out.println("\n  >>> INICIANDO ROLLBACK (ORDEM LIFO) <<<");
        int estornados = 0;
        // (while) executa se a pilha não estiver vazia
        while (!pilha.isEmpty()) {

            Pagamento item = pilha.pop();
            estornados++;
            System.out.printf("  [POP #%d - ESTORNO] Cancelado: %s (R$ %.2f)%n",
                    estornados, item, item.calcularPagamento());

        }
        return estornados;
    }

    public int getTotalConcluidos() {
        return pilha.size();
    }

    public void limpar() {
        pilha.clear();
    }

}