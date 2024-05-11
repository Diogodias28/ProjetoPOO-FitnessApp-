//controller e view

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import Model.Atividade;
import Model.Distancia;
import Model.DistanciaeAltimetria;
import Model.Fitness;
import Model.Genero;
import Model.Reps;
import Model.RepsPesos;
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
        NewMenu menu = new NewMenu(new String[] {"Criar utilizador", "Fazer login", "Gravar"});

        menu.setHandler(1, ()->CriarUtilizador());
        menu.setHandler(2, ()->{
            Utilizador utilizador = FazerLogin();
            if (utilizador != null) {
                PaginaInicial(utilizador);
            }
        });
        menu.setHandler(3, ()->gravar());

        menu.setPreCondition(2, ()->model.quantosUtilizadores()>0);

        menu.run();
        this.gravar();
    }

    private Utilizador FazerLogin(){
        System.out.println("Username: ");
        String username = is.nextLine();
        System.out.println("Password: ");
        String password = is.nextLine();
        if (model.ExisteUtilizador2(username, password)) {
            Utilizador utilizador = model.getUtilizador(username);
            return utilizador;
        }else {
            return null;
        }
    }

    private void PaginaInicial(Utilizador utilizador){
        NewMenu menu = new NewMenu(new String[] {"Regista Atividade", "Criar Plano de Treino", "Verificar estatísticas"});
        
        menu.setHandler(1, ()->MenuOpcoesAtividades(utilizador));
        menu.setHandler(2, ()->MenuOpcoesAtividades(utilizador));
        menu.setHandler(3, ()->MenuOpcoesAtividades(utilizador));

        menu.setPreCondition(2, ()->model.quantosUtilizadores()>0);

        menu.run();
    }

    private void CriarUtilizador(){
        System.out.println("Adicionar Utilizador");
        System.out.println("Morada: ");
        String morada = is.nextLine();
        System.out.println("Email: ");
        String email = is.nextLine();
        System.out.println("Password: ");
        String password = is.nextLine();
        System.out.println("Username: ");
        String username = is.nextLine();
        model.ExisteUtilizador(username);
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
        this.model.CriarUtilizador(morada, email, password, username, generoEnum, altura, peso, data, desportofav, tipoatl); //falta tratar das exceções
    }

    private void MenuOpcoesAtividades(Utilizador utilizador) {
        System.out.println("Estas são as nossas atividades disponíveis. Escolha uma delas: ");
        System.out.println("(Abdominal, Agachamentos com peso, Agachamentos, BTT, Burpees, Caminhada, Canoagem, Ciclismo, Corrida, Curl Bicep, Elevacoes Laterais, Flexao, Fly, Leg Press, Mountain Climber, Natacao, Prancha, Remada, Trail)");
        String descricao = is.nextLine();
        Atividade atividade = model.getAtividade(descricao);

        if (model.ExisteAtividade(descricao)) {
            System.out.println("Atividade encontrada!");
            utilizador.addAtividade(atividade);
        } else {
            System.out.println("Atividade não encontrada, reveja as nossas opções disponíveis.");
            return;
        }
        System.out.println("Realize a atividade e de seguida preencha os dados requisitados.");
        if (atividade instanceof DistanciaeAltimetria) {
            System.out.println("Indique qual foi a duração da atividade: ");
            int duracao = is.nextInt();
            atividade.setDuracao(duracao);
            System.out.println("Indique qual foi a distância da atividade: ");
            double distancia = is.nextDouble();
            ((DistanciaeAltimetria)atividade).setDistancia(distancia);
            System.out.println("Indique qual foi a altimetria da atividade: ");
            int altimetria = is.nextInt();
            ((DistanciaeAltimetria)atividade).setAltimetria(altimetria);
        } else if (atividade instanceof Distancia) {
            System.out.println("Indique qual foi a duração da atividade: ");
            int duracao = is.nextInt();
            atividade.setDuracao(duracao);
            System.out.println("Indique qual foi a distância da atividade: ");
            double distancia = is.nextDouble();
            ((Distancia)atividade).setDistancia(distancia);
        } else if (atividade instanceof Reps) {
            System.out.println("Indique quantas foram as repetições da atividade: ");
            int reps = is.nextInt();
            ((Reps)atividade).setreps(reps);
        } else {
            System.out.println("Indique quantas foram as repetições da atividade: ");
            int reps = is.nextInt();
            ((RepsPesos)atividade).setreps(reps);
            System.out.println("Indique qual foi o peso que usou na atividade: ");
            int peso = is.nextInt();
            ((RepsPesos)atividade).setpeso(peso);
        }
        double calorias = model.realizarAtividade(utilizador, atividade);
        
        System.out.println("A sua atividade foi realizada com sucesso! Queimou " + calorias + "calorias!");
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
