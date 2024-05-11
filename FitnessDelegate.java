//controller e view

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import Model.Fitness;
import Model.Genero;
import Model.Utilizador;
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
        menu.setHandler(2, ()->menuAtividades());
        /*menu.setHandler(3, ()->gravar());*/

        menu.run();
        this.gravar();
    }

    private void menuAtividades(){
        NewMenu menu = new NewMenu(new String[] {"Regista Atividade", "Criar Plano de Treino", "", ""});

        menu.setHandler(1, ()->MenuOpcoesAtividades());
        menu.setHandler(2, ()->ExisteUtilizador());

        menu.setPreCondition(2, ()->model.quantosUtilizadores()>0);

        menu.run();
    }

    private void menuUtilizadores(){
        NewMenu menu = new NewMenu(new String[] {"novo utilizador", "existe utilizador"});

        menu.setHandler(1, ()->AddUtilizador());
        menu.setHandler(2, ()->ExisteUtilizador());

        menu.setPreCondition(2, ()->model.quantosUtilizadores()>0);

        menu.run();
    }

    private void MenuOpcoesAtividades(){
        System.out.println("Estas são as nossas atividades disponíveis. Escolha uma delas: ");
        System.out.println("(Abdominal, Agachamentos com peso, Agachamentos, BTT, Burpees, Caminhada, Canoagem, Ciclismo, Corrida, Curl Bicep, Elevacoes Laterais, Flexao, Fly, Leg Press, Mountain Climber, Natacao, Prancha, Remada, Trail)");
        String descricao = is.nextLine();
        if (model.ExisteAtividade(descricao)){
            addAtividade()
        } else {
            System.out.println("Atividade não encontrada, reveja as nossas opções disponíveis.");
        }
    }

    /*MenuOpcoesDistancia(){
        NewMenu menu = new NewMenu(new String[] {"Natacao", "Canoagem", "Reps", "RepsPesos"});

        menu.setHandler(1, ()->MenuOpcoesDistancia());
        menu.setHandler(2, ()->MenuOpcoesDistanciaeAltimetria());
        menu.setHandler(3, ()->MenuOpcoesReps());
        menu.setHandler(4, ()->MenuOpcoesRepsPesos());
    }

    MenuOpcoesDistanciaeAltimetria(){
        NewMenu menu = new NewMenu(new String[] {"BTT", "Caminhada", "Ciclismo", "Corrida", "Trail"});

        menu.setHandler(1, ()->BTT());
        menu.setHandler(2, ()->Caminhada());
        menu.setHandler(3, ()->Ciclismo());
        menu.setHandler(4, ()->Corrida());
        menu.setHandler(4, ()->Trail());
    }

    MenuOpcoesReps(){
        NewMenu menu = new NewMenu(new String[] {"Abdominal", "Agachamentos", "Burpees", "Flexao", "MountainClimber", "Prancha"});

        menu.setHandler(1, ()->Abdominal());
        menu.setHandler(2, ()->Agachamentos());
        menu.setHandler(3, ()->Burpees());
        menu.setHandler(4, ()->Flexao());
    }

    MenuOpcoesRepsPesos(){
        NewMenu menu = new NewMenu(new String[] {"CurlBicep", "ElevacoesLaterais", "Fly", "LegPress", "Remada"});

        menu.setHandler(1, ()->MenuOpcoesDistancia());
        menu.setHandler(2, ()->MenuOpcoesDistanciaeAltimetria());
        menu.setHandler(3, ()->MenuOpcoesReps());
        menu.setHandler(4, ()->MenuOpcoesRepsPesos());
    }*/

    private void AddUtilizador(){
        System.out.println("Adicionar Utilizador");
        System.out.println("Morada: ");
        String morada = is.nextLine();
        System.out.println("Email: ");
        String email = is.nextLine();
        System.out.println("Password: ");
        String password = is.nextLine();
        System.out.println("Username: ");
        String username = is.nextLine();
        System.out.println("Género (Masculino, Feminino, Outro): ");
        String genero = is.nextLine();
        Genero generoEnum = null;
        switch (genero.toLowerCase()) {
            case "masculino":
                generoEnum = Genero.Masculino;
                break;
            case "feminino":
                generoEnum = Genero.Feminino;
                break;
            case "outro":
                generoEnum = Genero.Outro;
                break;
            default:
                System.out.println("Género inválido. Por favor, insira Masculino, Feminino ou Outro.");
                return;
        }
        System.out.println("Altura: ");
        double altura = is.nextDouble();
        System.out.println("Peso: ");
        double peso = is.nextDouble();
        is.nextLine();
        System.out.println("Data de nascimento: ");
        String datanasc = is.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate data = LocalDate.parse(datanasc, formatter);
        try {
            data = LocalDate.parse(datanasc, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido. Use o formato dd-MM-yyyy.");
            return;
        }
        System.out.println("Desporto favorito: ");
        String desportofav = is.nextLine();
        System.out.println("Tipo de atleta(profissional, amador, praticante ocasional): ");
        String tipoatl = is.nextLine();
        Utilizador utilizador = this.model.CriarUtilizador(morada, email, password, username, generoEnum, altura, peso, data, desportofav, tipoatl); //falta tratar das exceções
        model.addUtilizador(username, utilizador);
    }

    private void ExisteUtilizador(){
        System.out.println("Username: ");
        String username = is.nextLine();
        model.ExisteUtilizador(username);
    }

    private void gravar(){
        try {
            model.grava("bod.obj");
        } catch (IOException e){
            System.out.println("Erro ao gravar");
        }
    }
}
