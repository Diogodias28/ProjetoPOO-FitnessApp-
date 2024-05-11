package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.PlanoTreino;
import Model.Utilizador;
import Model.Recordes;

public class controller {
    private Utilizador user;

    public controller(Utilizador user) {
        this.user = user;
    }

    public static void iniciarNovaAtividade() {
        
    }

    public static void verMinhasEstatisticas() {
        
    }

    public static void criarPlanoDeTreino() {
        
    }

    public static void verTabelaDePontos(String[] args) {
        List<Utilizador> utilizadores = new ArrayList<>();
        Utilizador utilizadorComMaisCalorias = Recordes.obterUtilizadorComMaisCalorias(utilizadores);
        Utilizador utilizadorComMaisAtividades = Recordes.obterUtilizadorComMaisAtividades(utilizadores);
        String tipoAtividadeMaisRealizada = Recordes.obterTipoAtividadeMaisRealizada(utilizadores);
        double quilometrosTotalizados = Recordes.calcularQuilometrosTotalizados(utilizadorComMaisCalorias);
        double altimetriaTotalizada = Recordes.calcularAltimetriaTotalizada(utilizadorComMaisCalorias);
        Recordes.listarAtividades(utilizadorComMaisCalorias);

        System.out.println("Utilizador com mais atividades: " + utilizadorComMaisAtividades);
        System.out.println("Tipo de atividade mais realizada: " + tipoAtividadeMaisRealizada);
        System.out.println("Quilômetros totalizados: " + quilometrosTotalizados);
        System.out.println("Altimetria totalizada: " + altimetriaTotalizada);
    }
}