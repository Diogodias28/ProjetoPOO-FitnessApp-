//controller e view

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import Model.Fitness;
import Model.Genero;
import View.NewMenu;

public class FitnessDelegate {
    private Fitness model;
    private Scanner is = new Scanner(System.in);

    public FitnessDelegate(){
        try{
            this.model= Fitness.carrega("bd.obj");
        } 
        catch (IOException| ClassNotFoundException e){
            System.out.println(e.getMessage());
            this.model = new Fitness();
        }
    }

    public void run(){
        NewMenu menu = new NewMenu(new String[] {"Menu utilizador", "Menu Atividades", "Gravar"});

        menu.setHandler(1, ()->menuUtilizadores());
        /*menu.setHandler(2, ()->ExisteUtilizador());*/
        /*menu.setHandler(3, ()->gravar());*/

        menu.run();
        this.gravar();
    }

    private void menuUtilizadores(){
        NewMenu menu = new NewMenu(new String[] {"novo utilizador", "existe utilizador"});

        menu.setHandler(1, ()->AddUtilizador());
        menu.setHandler(2, ()->ExisteUtilizador());

        menu.setPreCondition(2, ()->model.quantosUtilizadores()>0);

        menu.run();
    }

    private void AddUtilizador(){
        System.out.println("Adicionar Utilizador");
        System.out.println("Email: ");
        String email = is.nextLine();
        //...
        try{
            this.model.CriarUtilizador(email, "1123", "Jose", Genero.Masculino, 1.90, 87, LocalDate.now(), "corrida");
        }
        /*catch (ExcecaoExiste e){
            //...
        }*/
    }

    private void ExisteUtilizador(){
        System.out.println("Existe Utilizador");
    }

    private void gravar(){
        try {
            model.grava("bod.obj");
        } catch (IOException e){
            System.out.println("Erro ao gravar");
        }
    }
}
