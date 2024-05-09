package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
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

    public void criarUtilizador(String username, Utilizador utilizador) {
        if (!users.containsKey(username)) {
            users.put(username, utilizador);
        } else {
            System.out.println("Username já utilizado. Por favor escolha um diferente.");
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