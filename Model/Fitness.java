package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fitness implements Serializable{ //este é o nosso model
    private Map <String, Utilizador> users;
    private Map <String, Comparator<Utilizador>> comparadores;

    public Fitness() {
        users = new HashMap<>();
        comparadores = new HashMap<>();
    }

    public int quantosUtilizadores(){
        return users.size();
    }

    public void CriarUtilizador(String username, Utilizador utilizador) {
        if (!users.containsKey(username)) {
            users.put(username, utilizador);
        } else {
            System.out.println("Username já utilizado. Por favor escolha um diferente.");
        }
    }

    public Utilizador CriarUtilizador(String morada, String email, String password, String username, Genero genero, double altura, double peso, LocalDate data_nascimento, String desporto_favorito, String tipo_atleta) {
        if (tipo_atleta == "profissional") {
            return new Utilizadorpro(morada, email, password, username, genero, altura, peso, data_nascimento, desporto_favorito, tipo_atleta);
        } else if (tipo_atleta == "amador") {
            return new Utilizadorama(morada, email, password, username, genero, altura, peso, data_nascimento, desporto_favorito, tipo_atleta);
        } else if (tipo_atleta == "praticante ocasional"){
            return new Utilizadorpratoc(morada, email, password, username, genero, altura, peso, data_nascimento, desporto_favorito, tipo_atleta);
        } else {
            System.out.println("Os únicos tipos de atleta disponíveis são 'profissional', 'amador' ou 'praticante ocasional'");
            return null;
        }
    }

    public void addUtilizador(String username, Utilizador utilizador) {
        users.put(username, utilizador);
    }

    public Utilizador getUtilizador(String username) {
        return users.get(username);
    }

    public List<String> getAllUsernames() {
        return new ArrayList<>(users.keySet());
    }

    public void removerUtilizador(String username) {
        users.remove(username);
    }

    public List<Atividade> getAllActivitiesOfUser(String username) {
        Utilizador utilizador = users.get(username);
        if (utilizador != null) {
            return new ArrayList<>(utilizador.getAtividades().values());
        } else {
            return new ArrayList<>();
        }
    }

    public Atividade getAtividades(String username, int index) {
        Utilizador utilizador = users.get(username);
        if (utilizador != null) {
            Map<String, Atividade> atividadesMap = utilizador.getAtividades();
            List<Atividade> atividades = new ArrayList<>(atividadesMap.values());
            if (index >= 0 && index < atividades.size()) {
                return atividades.get(index);
            }
        }
        return null;
    }

    public List<Atividade> getAllActivities() {
        List<Atividade> allActivities = new ArrayList<>();
        for (Utilizador utilizador : users.values()) {
            Map<String, Atividade> atividadesMap = utilizador.getAtividades();
            allActivities.addAll(atividadesMap.values());
        }
        return allActivities;
    }

    // Method to retrieve all usernames and their corresponding number of activities
    public Map<String, Integer> getUserActivityCounts() {
        Map<String, Integer> userActivityCounts = new HashMap<>();
        for (Map.Entry<String, Utilizador> entry : users.entrySet()) {
            String username = entry.getKey();
            int activityCount = entry.getValue().getAtividades().size();
            userActivityCounts.put(username, activityCount);
        }
        return userActivityCounts;
    }

    public PlanosdeTreino criarPlanosdeTreino(Utilizador utilizador, List<Atividade> atividades, List<DiaSemana> diasSemana, Recorrencia recorrencia){
        PlanoTreino planoTreino = new PlanoTreino(utilizador, atividades, diasSemana, recorrencia);
        // Aqui você pode adicionar o novo plano de treino à estrutura de dados do utilizador
        utilizador.adicionarPlanoTreino(planoTreino);
    }

    /*public void grava(String Filename){
        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(Filename));
            os.writeObject(this);
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    
    public void grava(String Filename) throws IOException{
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(Filename));
        os.writeObject(this);
        os.close();
    }

    
    /*public static Fitness carrega(String Filename){
        Fitness f = null;
        ObjectInputStream is = null;
        try{
            is = new ObjectInputStream(
                new FileInputStream(Filename)
            );
            f = (Fitness) is.readObject();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            f = new Fitness();
        }
        return f;
    }*/

    public static Fitness carrega(String Filename) throws IOException, ClassNotFoundException{
        Fitness f = null;
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(Filename));
        f = (Fitness) is.readObject();
        is.close();

        return f;
    }

    public void toCSV(String filename, String user) throws IOException {
        PrintWriter pw = new PrintWriter(filename);
        Utilizador utilizador = users.get(user);
        if (utilizador != null) {
            utilizador.getAtividades().entrySet().forEach(entry -> {
                String activityId = entry.getKey();
                Atividade activity = entry.getValue();
                pw.println(activityId + "," + activity.toString());
            });
        } else {
            System.out.println("Utilizador não encontrado.");
        }
        pw.close();
    }

    public void toCSV(String filename) {
        try (PrintWriter pw = new PrintWriter(filename)) {
            users.values().forEach(
                u -> getAllActivitiesOfUser(u.getusername()).forEach(
                    a -> pw.println(a.toString())
                )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//todos os métodos importantes: setuser, adduser, getallatividades... CriarUtilizador, quantosusers