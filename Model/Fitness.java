package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
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

    public void CriarUtilizador(){
        
    }

    public void AddUtilizador(){

    }

    public void getallatividades(){

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

    public void toCSV(String Filename, String user) throws IOException {
            PrintWriter pw= new PrintWriter(Filename);
            users.get(user).getAtividades()
                .forEach(a->pw.println(a.asCSV()));
    }

    public void asCSV(){

    }

    public void toCSV(String Filename){
        try{
            PrintWriter pw = new PrintWriter(Filename);
            users.values().forEach(
                u->getAtividades()
                .forEach(a->pw.println(a.asCSV))
            );
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//todos os métodos importantes: setuser, adduser, getallatividades... CriarUtilizador, quantosusers