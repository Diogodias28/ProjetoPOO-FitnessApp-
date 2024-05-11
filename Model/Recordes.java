package Model;
import java.util.*;

public class Recordes {

    public static Utilizador obterUtilizadorComMaisCalorias(List<Utilizador> utilizadores) {
        Utilizador utilizadorComMaisCalorias = null;
        double maxCalorias = Double.MIN_VALUE;
        for (Utilizador utilizador : utilizadores) {
            double calorias = utilizador.calcularTotalCalorias();
            if (calorias > maxCalorias) {
                maxCalorias = calorias;
                utilizadorComMaisCalorias = utilizador;
            }
        }
        return utilizadorComMaisCalorias;
    }

    public static Utilizador obterUtilizadorComMaisAtividades(List<Utilizador> utilizadores) {
        Utilizador utilizadorComMaisAtividades = null;
        int maxAtividades = Integer.MIN_VALUE;
        for (Utilizador utilizador : utilizadores) {
            int numAtividades = utilizador.getAtividades().size();
            if (numAtividades > maxAtividades) {
                maxAtividades = numAtividades;
                utilizadorComMaisAtividades = utilizador;
            }
        }
        return utilizadorComMaisAtividades;
    }

    public static String obterTipoAtividadeMaisRealizada(List<Utilizador> utilizadores) {
        Map<String, Integer> contadorAtividades = new HashMap<>();
        for (Utilizador utilizador : utilizadores) {
            for (Atividade atividade : utilizador.getAtividades()) {
                String tipoAtividade = atividade.getTipo();
                contadorAtividades.put(tipoAtividade, contadorAtividades.getOrDefault(tipoAtividade, 0) + 1);
            }
        }
        return Collections.max(contadorAtividades.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public static double calcularQuilometrosTotalizados(Utilizador utilizador) {
        double quilometrosTotalizados = 0;
        for (Atividade atividade : utilizador.getAtividades()) {
            quilometrosTotalizados += atividade.getDistancia();
        }
        return quilometrosTotalizados;
    }

    public static double calcularAltimetriaTotalizada(Utilizador utilizador) {
        double altimetriaTotalizada = 0;
        for (Atividade atividade : utilizador.getAtividades()) {
            altimetriaTotalizada += atividade.getAltimetria();
        }
        return altimetriaTotalizada;
    }

    public static void listarAtividades(Utilizador utilizador) {
        System.out.println("Atividades do utilizador " + utilizador.getUsername() + ":");
        for (Atividade atividade : utilizador.getAtividades()) {
            System.out.println(atividade);
        }
    }
}
