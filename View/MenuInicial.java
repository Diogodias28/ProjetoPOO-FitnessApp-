package View;
import java.util.Scanner;

public class MenuInicial {
    private Scanner scanner;

    public MenuInicial() {
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
}