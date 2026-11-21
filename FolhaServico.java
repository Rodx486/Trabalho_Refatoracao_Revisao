import java.util.ArrayList;
import java.util.List;

public class FolhaServico {

    private HistoricoTransacoes historico = new HistoricoTransacoes();
    private List<String> auditoriaTrabalhista = new ArrayList<>();

    public void processarLote(Pagamento[] lote) throws FalhaProcessamentoFolhaException {
        historico.limpar();
        int estornados = 0;

        try {
            for (Pagamento p : lote) {

                if (p instanceof Horista) {

                    Horista h = (Horista) p;
                    h.validarJornada();
                }
                p.calcularPagamento();
                historico.registraPagamento(p);

            }

        } catch (ExcessoJornadaException e) {
            String registro = "PENDÊNCIA TRABALHISTA | Colaborador: " + e.getNomeColaborador()
                    + " | Data: " + e.getDataRegistro()
                    + " | Horas: " + e.getHorasLancadas() + "h"
                    + " (Limite: " + e.getLimiteLocal() + "h)";
            auditoriaTrabalhista.add(registro);
            System.out.println("  [AUDITORIA] " + registro);
            estornados = historico.executarRollback();

            throw new FalhaProcessamentoFolhaException(
                    "Falha trabalhista ao processar lote de pagamentos.", e);
        } catch (DadosInvalidosException e) {
            // 1. Desfaz (estorna) todos os pagamentos já feitos neste lote
            estornados = historico.executarRollback();
            // 2. Relança como exceção da folha, guardando 'e' como causa raiz
            throw new FalhaProcessamentoFolhaException(
                    "Dados inválidos detectados durante o processamento do lote.", e);
        } finally {
            // Executa SEMPRE (mesmo depois do 'throw' acima!), antes de voltar pro Main
            System.out.println("\n--- RESUMO DE AUDITORIA (FINALLY) ---");
            System.out.println("Operações concluídas: " + historico.getTotalConcluidos());
            System.out.println("Operações estornadas: " + estornados);
            System.out.println("Recursos de log descarregados e ciclo encerrado.");
        }

    }

    public List<String> getAuditoriaTrabalhista() {
    return auditoriaTrabalhista;
}
}
