import java.util.Scanner;

public class Interface {
    private Scanner scanner;

    public Interface() {
        this.scanner = new Scanner(System.in);
    }

    public void exibirMensagemBoasVindas() {
        System.out.println("Bem-vindo à nossa aplicação de Fitness!");
        System.out.println("Aqui poderá realizar planos de treino, verificar estatísticas e competir com outros utilizadores.");
        System.out.println("Boa sorte!\n");
    }

    public void exibirMenuInicial() {
        System.out.println("Escolha uma opção:");
        System.out.println("1. Login");
        System.out.println("2. Criar conta");
    }

    public int lerOpcaoMenuInicial() {
        System.out.print("Opção: ");
        return scanner.nextInt();
    }

    // Métodos para ler dados do usuário durante o processo de login ou criação de conta
    // ...

    public void exibirPaginaPrincipal() {
        System.out.println("\nBem-vindo à sua página principal!");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Iniciar nova atividade");
        System.out.println("2. Criar plano de treino");
        System.out.println("3. Ver as minhas estatísticas");
        System.out.println("4. Tabelas com outros utilizadores");
        System.out.println("5. Logout");
    }

    public int lerOpcaoPaginaPrincipal() {
        System.out.print("Opção: ");
        return scanner.nextInt();
    }

    // Métodos adicionais conforme necessário para interações específicas do usuário
    // ...
}