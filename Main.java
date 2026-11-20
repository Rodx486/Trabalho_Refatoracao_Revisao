import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // 1. Instanciando faturas e os 4 tipos de funcionários
        Fatura fatura = new Fatura("FAT-001", "Teclado Mecânico", 3, 250.0f);

        Assalariado assalariado = new Assalariado(
                "Ana Silva", "Rua A, 100", 111111, 101, 1500.0f);

        Horista horistaRegular = new Horista(
                "Carlos Souza", "Rua B, 200", 222222, 102, 50.0f);

        Horista horistaExcesso = new Horista(
                "Bruno Lima", "Rua C, 300", 333333, 103, 40.0f);

        Comissionado comissionado = new Comissionado(
                "Mariana Costa", "Rua D, 400", 444444, 104, 10000.0f, 10.0f);

        AssalariadoComissionado assalariadoComissionado = new AssalariadoComissionado(
                "Pedro Rocha", "Rua E, 500", 555555, 105, 8000.0f, 5.0f, 1000.0f);

        // 2. Registrando horas diárias (tratando exceção checked > 10h/dia)
        System.out.println("=== REGISTRO DE JORNADA DIÁRIA ===");

        // Carlos: 5 dias de 9h = 45h na semana (1h extra/dia -> dentro da lei)
        for (int dia = 1; dia <= 5; dia++) {
            registrarJornada(horistaRegular, 9.0f);
        }

        // Bruno: 4 dias de 8h + 1 dia de 12h = 44h na semana (4h extras no 5º dia -> viola lei)
        for (int dia = 1; dia <= 4; dia++) {
            registrarJornada(horistaExcesso, 8.0f);
        }
        registrarJornada(horistaExcesso, 12.0f);

        // 3. Processamento polimórfico da folha de pagamentos e contas a pagar
        List<Pagamento> pagamentos = new ArrayList<>();
        pagamentos.add(fatura);
        pagamentos.add(assalariado);
        pagamentos.add(horistaRegular);
        pagamentos.add(horistaExcesso);
        pagamentos.add(comissionado);
        pagamentos.add(assalariadoComissionado);

        System.out.println("\n=== FOLHA DE PAGAMENTO E CONTAS A PAGAR (POLIMÓRFICA) ===");
        float totalGeral = 0;

        for (Pagamento item : pagamentos) {
            float valor = item.calcularPagamento();
            totalGeral += valor;

            String marcacao = "";
            if (item instanceof Funcionario) {
                Funcionario f = (Funcionario) item;
                if (f.isAlertaHoraExtra()) {
                    marcacao = "  [!] MARCAÇÃO NA FOLHA: > 2h extras diárias (risco trabalhista)";
                }
            }

            System.out.printf("[%s] %s -> R$ %.2f%s%n",
                    item.getClass().getSimpleName(),
                    item,
                    valor,
                    marcacao);
        }

        System.out.printf("%nTOTAL GERAL A PAGAR: R$ %.2f%n", totalGeral);
    }

    private static void registrarJornada(Funcionario funcionario, float horas) {
        try {
            funcionario.registrarHorasDias(horas);
        } catch (ExcessoJornadaException e) {
            System.out.println("Exceção capturada: " + e.getMessage());
        }
    }
}
