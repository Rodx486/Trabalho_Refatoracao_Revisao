public class Main {

    public static void main(String[] args) {
        FolhaServico servico = new FolhaServico();

        // =====================================================================
        // CENÁRIO 1: CAMINHO FELIZ (pagamentos válidos dentro do limite legal)
        // =====================================================================
        System.out.println("============================================================");
        System.out.println("CENÁRIO 1: CAMINHO FELIZ");
        System.out.println("============================================================");

        Fatura fatura1 = new Fatura("FAT-001", "Teclado Mecânico", 3, 250.0f);
        Assalariado assalariado1 = new Assalariado(
                "Ana Silva", "Rua A, 100", 111111, 101, 1500.0f);

        Horista horistaRegular = new Horista(
                "Carlos Souza", "Rua B, 200", 222222, 102, 50.0f);
        horistaRegular.registrarHorasDia("16/11", 9.0f);
        horistaRegular.registrarHorasDia("17/11", 9.0f);
        horistaRegular.registrarHorasDia("18/11", 9.0f);
        horistaRegular.registrarHorasDia("19/11", 9.0f);
        horistaRegular.registrarHorasDia("20/11", 9.0f); // 45h/semana, máx 9h/dia (dentro da lei)

        Comissionado comissionado1 = new Comissionado(
                "Mariana Costa", "Rua D, 400", 444444, 104, 10000.0f, 10.0f);
        AssalariadoComissionado assalariadoCom1 = new AssalariadoComissionado(
                "Pedro Rocha", "Rua E, 500", 555555, 105, 8000.0f, 5.0f, 1000.0f);

        Pagamento[] loteFeliz = {
                fatura1, assalariado1, horistaRegular, comissionado1, assalariadoCom1
        };

        try {
            servico.processarLote(loteFeliz);
            System.out.println("[MAIN] Lote 1 processado com sucesso!");
        } catch (FalhaProcessamentoFolhaException e) {
            System.out.println("[MAIN] Erro inesperado: " + e.getMessage());
            e.printStackTrace(System.out);
        }

        // =====================================================================
        // CENÁRIO 2: VIOLAÇÃO TRABALHISTA (Checked - Horista com 12h no dia)
        // =====================================================================
        System.out.println("\n============================================================");
        System.out.println("CENÁRIO 2: VIOLAÇÃO TRABALHISTA (CHECKED EXCEPTION)");
        System.out.println("============================================================");

        Horista horistaViolacao = new Horista(
                "Bruno Lima", "Rua C, 300", 333333, 103, 40.0f);
        horistaViolacao.registrarHorasDia("16/11", 8.0f);
        horistaViolacao.registrarHorasDia("17/11", 8.0f);
        horistaViolacao.registrarHorasDia("18/11", 12.0f); // > 10h -> dispara ExcessoJornadaException

        Pagamento[] loteViolacao = { horistaViolacao };

        try {
            servico.processarLote(loteViolacao);
        } catch (FalhaProcessamentoFolhaException e) {
            System.out.println("\n[MAIN] Falha capturada na camada de visão: " + e.getMessage());
            System.out.println("[MAIN] Causa raiz: " + e.getCause().getMessage());
            System.out.println("[MAIN] Pilha de chamadas (stack trace):");
            e.printStackTrace(System.out);
        }

        // =====================================================================
        // CENÁRIO 3: FALHA EM LOTE DE 5 ITENS (4º falha -> Rollback LIFO 3->2->1)
        // =====================================================================
        System.out.println("\n============================================================");
        System.out.println("CENÁRIO 3: FALHA NO 4º ITEM COM ROLLBACK EM PILHA (LIFO)");
        System.out.println("============================================================");

        Fatura item1 = new Fatura("FAT-002", "Monitor 27\"", 2, 1200.0f);
        Assalariado item2 = new Assalariado(
                "Fernanda Alves", "Rua F, 600", 666666, 106, 2200.0f);
        Comissionado item3 = new Comissionado(
                "Lucas Mendes", "Rua G, 700", 777777, 107, 15000.0f, 8.0f);

        Horista item4ComFalha = new Horista(
                "Rafael Dias", "Rua H, 800", 888888, 108, 45.0f);
        item4ComFalha.registrarHorasDia("19/11", 12.0f); // 4º item gera falha trabalhista!

        Assalariado item5 = new Assalariado(
                "Julia Martins", "Rua I, 900", 999999, 109, 1800.0f);

        Pagamento[] loteRollback = { item1, item2, item3, item4ComFalha, item5 };

        try {
            servico.processarLote(loteRollback);
        } catch (FalhaProcessamentoFolhaException e) {
            System.out.println("\n[MAIN] Lote abortado e revertido: " + e.getMessage());
            System.out.println("[MAIN] Causa raiz: " + e.getCause().getMessage());
        }

        // =====================================================================
        // TESTE EXTRA: VALIDAÇÃO DE DOMÍNIO (Unchecked - DadosInvalidosException)
        // =====================================================================
        System.out.println("\n============================================================");
        System.out.println("TESTE EXTRA: DADOS INVÁLIDOS (UNCHECKED EXCEPTION)");
        System.out.println("============================================================");

        try {
            new Fatura("FAT-ERR", "Mouse com quantidade inválida", 0, 100.0f);
        } catch (DadosInvalidosException e) {
            System.out.println("[MAIN] DadosInvalidosException capturada: " + e.getMessage());
        }

        // =====================================================================
        // ESTADO FINAL DO SISTEMA — RELATÓRIO DE AUDITORIA TRABALHISTA
        // =====================================================================
        System.out.println("\n============================================================");
        System.out.println("ESTADO FINAL — RELATÓRIO DE AUDITORIA TRABALHISTA");
        System.out.println("============================================================");

        if (servico.getAuditoriaTrabalhista().isEmpty()) {
            System.out.println("Nenhuma pendência trabalhista registrada.");
        } else {
            for (String pendencia : servico.getAuditoriaTrabalhista()) {
                System.out.println("- " + pendencia);
            }
        }
    }
}
