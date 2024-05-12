//controller e view

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Model.Atividade;
import Model.Dificuldade;
import Model.Distancia;
import Model.DistanciaeAltimetria;
import Model.Fitness;
import Model.Genero;
import Model.PlanoTreino;
import Model.Reps;
import Model.RepsPesos;
import Model.Utilizador;
import View.NewMenu;

public class FitnessDelegate implements Serializable {
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
        Runtime.getRuntime().addShutdownHook(new Thread(() -> gravar()));
    }

    public void run(){
        NewMenu menu = new NewMenu(new String[] {"Criar utilizador", "Fazer login", "Ver todos os usernames","Gravar"});

        menu.setHandler(1, ()->CriarUtilizador());
        menu.setHandler(2, ()->{
            Utilizador utilizador = FazerLogin();
            if (utilizador != null) {
                PaginaInicial(utilizador);
            }
        });
        menu.setHandler(3, ()->MostrarUsers());
        menu.setHandler(4, ()->gravar());

        menu.setPreCondition(2, ()->model.quantosUtilizadores()>0);
        menu.setPreCondition(3, ()->model.quantosUtilizadores()>0);

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
        NewMenu menu = new NewMenu(new String[] {"Regista Atividade", "Criar Plano de Treino", "Verificar estatísticas", "Ver planos de treino do utilizador", "Saltar Tempo"});
        
        menu.setHandler(1, ()->RegistaAtividade(utilizador));
        menu.setHandler(2, ()->MenuPlanosTreino(utilizador));
        menu.setHandler(3, ()->MenuEstatisticas(utilizador));
        menu.setHandler(4, ()->ConsultarPlanos(utilizador));
        menu.setHandler(5, ()->SaltarTempo(utilizador));

        menu.setPreCondition(4, ()->model.quantosPlanos(utilizador)>0);
        

        menu.run();
    }

    private void CriarUtilizador(){
        System.out.println("Adicionar Utilizador");
        System.out.println("Morada: ");
        String morada = is.nextLine();
        System.out.println("Email: ");
        String email = is.nextLine();
        System.out.println("Username: ");
        String username = is.nextLine();
        model.ExisteUtilizador(username);
        System.out.println("Password: ");
        String password = is.nextLine();
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

    public void MostrarUsers(){
        List<String> usernames = model.getAllUsernames();

        System.out.println("Estes são os usernames que já foram criados:");
        for (String username : usernames) {
            System.out.println("- " + username);
        }
    }

    private void RegistaAtividade(Utilizador utilizador) {
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
            System.out.println("Indique qual foi a frequência cardíaca média registada durante atividade: ");
            int frequenciaCardiacaMedia = is.nextInt();
            atividade.setFrequenciaCardiacaMedia(frequenciaCardiacaMedia);
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
            System.out.println("Indique qual foi a frequência cardíaca média registada durante atividade: ");
            int frequenciaCardiacaMedia = is.nextInt();
            atividade.setFrequenciaCardiacaMedia(frequenciaCardiacaMedia);
            System.out.println("Indique qual foi a duração da atividade: ");
            int duracao = is.nextInt();
            atividade.setDuracao(duracao);
            System.out.println("Indique qual foi a distância da atividade: ");
            double distancia = is.nextDouble();
            ((Distancia)atividade).setDistancia(distancia);
        } else if (atividade instanceof Reps) {
            System.out.println("Indique qual foi a frequência cardíaca média registada durante atividade: ");
            int frequenciaCardiacaMedia = is.nextInt();
            atividade.setFrequenciaCardiacaMedia(frequenciaCardiacaMedia);
            System.out.println("Indique quantas foram as repetições da atividade: ");
            int reps = is.nextInt();
            ((Reps)atividade).setreps(reps);
        } else {
            System.out.println("Indique qual foi a frequência cardíaca média registada durante atividade: ");
            int frequenciaCardiacaMedia = is.nextInt();
            atividade.setFrequenciaCardiacaMedia(frequenciaCardiacaMedia);
            System.out.println("Indique quantas foram as repetições da atividade: ");
            int reps = is.nextInt();
            ((RepsPesos)atividade).setreps(reps);
            System.out.println("Indique qual foi o peso que usou na atividade: ");
            int peso = is.nextInt();
            ((RepsPesos)atividade).setpeso(peso);
        }
        double calorias = model.realizarAtividade(utilizador, atividade);
        
        System.out.println("A sua atividade foi realizada com sucesso! Queimou " + calorias + " calorias!");
    }

    public void MenuPlanosTreino(Utilizador utilizador){
        NewMenu menu = new NewMenu(new String[] {"Plano de treino por tipo", "Plano de treino por dificuldade"});
        
        menu.setHandler(1, ()->PlanoTipo(utilizador));
        menu.setHandler(2, ()->PlanoDificuldade(utilizador));

        menu.run();
    }

    public void PlanoTipo(Utilizador utilizador){

        System.out.println("Indique qual a recorrência semanal que pretende para as atividades: ");
        int recorrencia = is.nextInt();
        is.nextLine();
        System.out.println("Indique qual o tipo de atividade que pretende: ");
        String tipoAtividade = is.nextLine();

        PlanoTreino plano = model.criarPlanodeTreinoPorTipo(utilizador, recorrencia, tipoAtividade);

        List<Atividade> atividades = plano.getAtividades();
        System.out.println("Plano de treino com inicio em " + plano.getDataInicio() + " e fim em " + plano.getDataFim() + " contém as seguintes atividades:");
        
        for (Atividade atividade : atividades) {
            System.out.println("- " + atividade.getDescricao() + " ("+ atividade.getData()+ ", "+ atividade.calorias(utilizador)+")");
        }
    }

    public void PlanoDificuldade(Utilizador utilizador){

        System.out.println("Indique qual a recorrência semanal que pretende para as atividades: ");
        int recorrencia = is.nextInt();
        is.nextLine();

        System.out.println("Indique qual o tipo de dificuldade que pretende (DIFICL, MEDIO, FACIL): ");
        String dificuldadeInput = is.nextLine().toUpperCase();

        Dificuldade dificuldade = null;
        try {
            dificuldade = Dificuldade.valueOf(dificuldadeInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Dificuldade inválida. Por favor, escolha entre DIFICL, MEDIO e FACIL.");
            return;
        }

        PlanoTreino plano = model.criarPlanodeTreino(utilizador, recorrencia, dificuldade);

        List<Atividade> atividades = plano.getAtividades();
        System.out.println("Plano de treino com inicio em " + plano.getDataInicio() + " e fim em " + plano.getDataFim() + " contém as seguintes atividades:");
        
        for (Atividade atividade : atividades) {
            System.out.println("- " + atividade.getDescricao() + " ("+ atividade.getData()+ ", "+ atividade.calorias(utilizador)+")");
        }
    }

    public void MenuEstatisticas(Utilizador utilizador) {
        NewMenu menu = new NewMenu(new String[] {"Utilizador com mais calorias", "Utilizador com mais atividades", "Atividade mais realizada", "Quantos quilómetros realizou", "Quantos metros de altimetria totalizou", "Plano de treino mais exigente", "Listar as atividades"});
        
        menu.setHandler(1, ()->RecordeCalorias());
        menu.setHandler(2, ()->RecordeAtividades());
        menu.setHandler(3, ()->AtividadeMaisRealizada());
        menu.setHandler(4, ()->Quilometros(utilizador));
        menu.setHandler(5, ()->Altimetria(utilizador));
        menu.setHandler(6, ()->PlanoMaisExigente());
        menu.setHandler(7, ()->ListaAtividades(utilizador));

        menu.setPreCondition(2, ()->model.quantasAtividades(utilizador)>0);

        menu.run();
    }

    public void ConsultarPlanos(Utilizador utilizador) {
        System.out.println("O utilizador " + utilizador.getusername() + " possui os seguintes planos de Treino por fazer:");

        ArrayList <PlanoTreino> planos = utilizador.getPlanosTreino();

        for (PlanoTreino plano : planos) {
            List<Atividade> atividades = plano.getAtividades();
            if (plano.getDataInicio().isAfter(model.getDataAtual())) {
                System.out.println("Plano de treino com inicio em " + plano.getDataInicio() + " e fim em " + plano.getDataFim() + " contém as seguintes atividades:");
                for (Atividade atividade : atividades) {
                    System.out.println("- " + atividade.getDescricao() + " ("+ atividade.getData()+ ")");
                }
            }
        }
    }

    public void RecordeCalorias(){
        NewMenu menu = new NewMenu(new String[] {"Num Período", "Desde sempre"});
        
        menu.setHandler(1, ()->CaloriasnumPeriodo());
        menu.setHandler(2, ()->CaloriasdesdeSempre());

        menu.run();
    }

    public void CaloriasnumPeriodo() {
        System.out.println("Insira a data inicial do perído de tempo:");
        String inicioString = is.nextLine();
        LocalDate inicio = LocalDate.parse(inicioString);
        System.out.println("Insira a data inicial do perído de tempo:");
        String FimString = is.nextLine();
        LocalDate fim = LocalDate.parse(FimString);
        Utilizador user = model.encontrarUtilizadorMaisCaloriconumIntervalo(inicio, fim);
        String maiscalor= user.getusername();
        System.out.println("O utilizador que mais calorias consumiu durante o perído estipulado foi: "+ maiscalor);
    }

    public void CaloriasdesdeSempre(){
        Utilizador user = model.encontrarUtilizadorMaisCaloricoDesdeSempre();
        String maiscalor= user.getusername();
        System.out.println("O utilizador que mais calorias consumiu desde o início foi: "+ maiscalor);
    }

    public void RecordeAtividades(){
        NewMenu menu = new NewMenu(new String[] {"Num Período", "Desde sempre"});
        
        menu.setHandler(1, ()->AtividadesnumPeriodo());
        menu.setHandler(2, ()->AtividadesdesdeSempre());

        menu.run();
    }

    public void AtividadesnumPeriodo() {
        System.out.println("Insira a data inicial do perído de tempo:");
        String inicioString = is.nextLine();
        LocalDate inicio = LocalDate.parse(inicioString);
        System.out.println("Insira a data inicial do perído de tempo:");
        String FimString = is.nextLine();
        LocalDate fim = LocalDate.parse(FimString);
        Utilizador user = model.encontrarUtilizadorMaisAtivo(inicio, fim);
        String maisativo= user.getusername();
        System.out.println("O utilizador que realizou mais atividades durante o perído estipulado foi: "+ maisativo);
    }

    public void AtividadesdesdeSempre(){
        Utilizador user = model.encontrarUtilizadorMaisAtivoDesdeSempre();
        String maisativo= user.getusername();
        System.out.println("O utilizador que realizou mais atividades desde o início foi: "+ maisativo);
    }

    public void AtividadeMaisRealizada(){
        String ativ = model.encontrarTipoAtividadeMaisRealizada();
        System.out.println("A atividade mais realizada foi: "+ ativ);

    }

    public void Quilometros(Utilizador utilizador){
        NewMenu menu = new NewMenu(new String[] {"Num Período", "Desde sempre"});
        
        menu.setHandler(1, ()->QuilometrosnumPeriodo(utilizador));
        menu.setHandler(2, ()->QuilometrosdesdeSempre(utilizador));

        menu.run();
    }

    public void QuilometrosnumPeriodo(Utilizador utilizador) {
        System.out.println("Insira a data inicial do perído de tempo:");
        String inicioString = is.nextLine();
        LocalDate inicio = LocalDate.parse(inicioString);
        System.out.println("Insira a data inicial do perído de tempo:");
        String FimString = is.nextLine();
        LocalDate fim = LocalDate.parse(FimString);
        Double distancia = model.calcularDistanciaTotalUtilizador(inicio, fim, utilizador);
        String username = utilizador.getusername();
        System.out.println("O utilizador "+ username+ " percorreu "+ distancia+ " quilómetros no período estipulado.");
    }

    public void QuilometrosdesdeSempre(Utilizador utilizador){
        Double distancia = model.calcularDistanciaTotalUtilizadorDesdeSempre(utilizador);
        String username = utilizador.getusername();
        System.out.println("O utilizador "+ username+ " percorreu "+ distancia+ " quilómetros desde o início.");
    }

    public void Altimetria(Utilizador utilizador) {
        NewMenu menu = new NewMenu(new String[] {"Num Período", "Desde sempre"});
        
        menu.setHandler(1, ()->AltimetrianumPeriodo(utilizador));
        menu.setHandler(2, ()->AltimetriasdeSempre(utilizador));

        menu.run();
    }

    public void AltimetrianumPeriodo(Utilizador utilizador) {
        System.out.println("Insira a data inicial do perído de tempo:");
        String inicioString = is.nextLine();
        LocalDate inicio = LocalDate.parse(inicioString);
        System.out.println("Insira a data inicial do perído de tempo:");
        String FimString = is.nextLine();
        LocalDate fim = LocalDate.parse(FimString);
        int altimetria = model.calcularAltimetriaTotalUtilizador(inicio, fim, utilizador);
        String username = utilizador.getusername();
        System.out.println("O utilizador "+ username+ " percorreu "+ altimetria+ " quilómetros no período estipulado.");
    }

    public void AltimetriasdeSempre(Utilizador utilizador){
        int altimetria = model.calcularAltimetriaTotalUtilizadorDesdeSempre(utilizador);
        String username = utilizador.getusername();
        System.out.println("O utilizador "+ username+ " percorreu "+ altimetria+ " quilómetros desde o início.");
    }

    public void PlanoMaisExigente() {
        PlanoTreino plano = model.encontrarPlanoMaisExigente();
        List<Atividade> atividades = plano.getAtividades();
        System.out.println("O plano de treino mais exigente contém as seguintes atividades:");
        
        for (Atividade atividade : atividades) {
            System.out.println("- " + atividade.getDescricao() + " ("+ atividade.getData()+ ")");
        }
    }

    public void ListaAtividades(Utilizador utilizador) {
        List<Atividade> ativ = model.ListarTodasasAtividadesdeumUser();
        String username = utilizador.getusername();
        System.out.println("O utilizador "+ username+ " realizou as seguintes atividades: ");
        for (Atividade atividade : ativ) {
            System.out.println("- " + atividade.getDescricao());
        }
    }

    public void SaltarTempo(Utilizador utilizador){
        System.out.println("Indique quantos dias pretende avançar: ");
        int dias = is.nextInt();
        is.nextLine();

        model.avancarAteDataEspecifica(dias, utilizador);
    }

    private void gravar(){
        try {
            model.grava("bd.obj");
        } catch (IOException e){
            System.out.println("Erro ao gravar");
        }
    }
}
