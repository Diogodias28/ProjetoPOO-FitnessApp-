package View;
import java.util.Scanner;

public class PaginaInicial {
    private Scanner scanner;

    public PaginaInicial() {
        this.scanner = new Scanner(System.in);
    }

    public void exibirPaginaPrincipal() {
        System.out.println("Bem-vindo à sua página principal!");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Iniciar nova atividade");
        System.out.println("2. Criar plano de treino");
        System.out.println("3. Ver as minhas estatísticas");
        System.out.println("4. Tabela de pontos");
        System.out.println("5. Logout");
    }

    public int lerOpcaoPaginaPrincipal() {
        System.out.print("Opção: ");
        return scanner.nextInt();
    }

    // Métodos adicionais conforme necessário para interações específicas do usuário
    // ...

    public void iniciarNovaAtividade() {
        // Capturar detalhes da nova atividade do usuário (como tipo, duração, etc.)
        // e encaminhar para o controlador apropriado
        Controller.controller.iniciarNovaAtividade();
    }

    public void verMinhasEstatisticas() {
        Controller.controller.verMinhasEstatisticas();
    }

    public void criarPlanoDeTreino() {
        Controller.controller.criarPlanoDeTreino();
    }

    public void verTabelaDePontos() {
        Controller.controller.verTabelaDePontos();
    }
}