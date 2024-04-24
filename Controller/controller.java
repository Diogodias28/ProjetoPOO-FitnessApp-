package Controller;

import Model.Utilizador; // Supondo que você tenha uma classe de modelo Usuario

public class controller {
    private Utilizador user;

    public controller(Utilizador user) {
        this.user = user;
    }

    public static void iniciarNovaAtividade() {
        // Lógica para iniciar nova atividade
        // obtidas da lógica de negócios.
    }

    public static void verMinhasEstatisticas() {
        // Lógica para exibir as estatísticas do usuário
        // obtidas da lógica de negócios.
    }

    public static void criarPlanoDeTreino() {
        // Lógica para capturar os detalhes do novo plano de treino do usuário
        // e passar essas informações para a lógica de negócios.
    }

    public static void verTabelaDePontos() {
        // Lógica para exibir a tabela de pontos
        // obtida da lógica de negócios.
    }
}