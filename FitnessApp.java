import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Model.Atividade;
import Model.Utilizador;

public class FitnessApp {
    private static List<Utilizador> utilizadores = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        run();
    }

    public void exportarAtividadesParaCSV(String email) {
        // Buscar o utilizador com base no email fornecido
        Utilizador utilizador = getUtilizadorbyemail(email);
        if (utilizador == null) {
            System.out.println("Utilizador não encontrado.");
            return;
        }

        Map<String, Atividade> atividades = utilizador.getAtividades();

        // Verificar se o utilizador possui atividades
        if (atividades.isEmpty()) {
            System.out.println("Este utilizador não possui atividades.");
            return;
        }

        String nomeArquivo = "atividades_" + email + ".csv";

        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            // Escrever o cabeçalho do arquivo CSV
            writer.append("Codigo,Descricao,Data,Duracao\n");

            // Escrever cada atividade no arquivo CSV
            for (Map.Entry<String, Atividade> entry : atividades.entrySet()) {
                Atividade atividade = entry.getValue();
                writer.append(atividade.getCodigo()).append(",");
                writer.append(atividade.getDescricao()).append(",");
                writer.append(atividade.getData().toString()).append(",");
                writer.append(String.valueOf(atividade.getDuracao())).append("\n");
            }

            System.out.println("As atividades foram exportadas para o arquivo: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao exportar as atividades para o arquivo CSV: " + e.getMessage());
        }
    }

    private Utilizador getUtilizadorbyemail(String email) {
        // Implemente a lógica para encontrar o utilizador com base no email
        return null;
    }
}