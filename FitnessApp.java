import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FitnessApp {
    private static List<Utilizador> utilizadores = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        exibirMensagemBoasVindas();
        exibirMenuInicial();
    }

    private static void exibirMensagemBoasVindas() {
        System.out.println("Bem-vindo à nossa aplicação de Fitness!");
        System.out.println("Aqui poderá realizar planos de treino, verificar estatísticas e competir com outros utilizadores.");
        System.out.println("Boa sorte!\n");
    }

    private static void exibirMenuInicial() {
        System.out.println("Para iniciar, escolha uma opção:");
        System.out.println("1. Login");
        System.out.println("2. Criar conta");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha pendente

        if (opcao == 1) {
            fazerLogin();
        } else if (opcao == 2) {
            criarConta();
        } else {
            System.out.println("Opção inválida. Por favor, escolha novamente.\n");
            exibirMenuInicial();
        }
    }

    private static void fazerLogin() {
        System.out.println("Por favor, insira seu Email:");
        String email = scanner.nextLine();

        System.out.println("Por favor, insira sua Palavra-passe:");
        String senha = scanner.nextLine();

        Utilizador utilizador = encontrarUtilizadorPorEmail(email);
        if (utilizador != null && utilizador.getPassword().equals(senha)) {
            System.out.println("Login bem-sucedido!");
            // Lógica para a página principal após o login
        } else {
            System.out.println("Email ou Palavra-passe incorretos. Por favor, tente novamente.\n");
            exibirMenuInicial();
        }
    }

    private static void criarConta() {
        System.out.println("Por favor, insira seu Email:");
        String email = scanner.nextLine();

        // Verificar se o email já está em uso
        if (encontrarUtilizadorPorEmail(email) != null) {
            System.out.println("O email já está em uso. Por favor, insira um email diferente.\n");
            criarConta();
            return;
        }

        System.out.println("Por favor, insira sua Palavra-passe:");
        String senha = scanner.nextLine();

        System.out.println("Por favor, insira seu nome:");
        String nome = scanner.nextLine();

        // Resto da lógica para ler outras informações do usuário e criar um novo Utilizador

        Utilizador novoUtilizador = new Utilizador(email, senha, nome, ...);
        utilizadores.add(novoUtilizador);

        System.out.println("Conta criada com sucesso!\n");
        exibirMenuInicial();
    }

    private static Utilizador encontrarUtilizadorPorEmail(String email) {
        for (Utilizador utilizador : utilizadores) {
            if (utilizador.getEmail().equals(email)) {
                return utilizador;
            }
        }
        return null;
    }
}