package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fitness implements Serializable{ //este é o nosso model
    private Map <String, Utilizador> utilizadores;
    private Map <String, Atividade> atividades;
    private Map <String, Comparator<Utilizador>> comparadores;

    public Fitness() {
        utilizadores = new HashMap<>();
        comparadores = new HashMap<>();
    }

    public int quantosUtilizadores(){
        return utilizadores.size();
    }

    public boolean ExisteUtilizador(String username) {
        if (!utilizadores.containsKey(username)) {
            return false;
        } else {
            System.out.println("Este utilizador foi criado.");
            return true;
        }
    }

    public boolean ExisteAtividade(String descricao){
        if (!atividades.containsKey(descricao)) {
            return false;
        } else {
            return true;
        }
    }

    public Atividade criarAtividade(String descricao) {
        switch (descricao.toLowerCase()) {
            case "Abdominal":
                return new Abdominal();
            case "Agachamentos Com Peso":
                return new AgachamentoComPeso();
            case "Agachamentos":
                return new Agachamentos();
            case "BTT":
                return new BTT();
            case "Burpees":
                return new Burpees();
            case "Caminhada":
                return new Caminhada();
            case "Canoagem":
                return new Canoagem();
            case "Ciclismo":
                return new Ciclismo();
            case "Corrida":
                return new Corrida();
            case "Curl Bicep":
                return new CurlBicep();
            case "Elevações Laterais":
                return new ElevacoesLaterais();
            case "Flexão":
                return new Flexao();
            case "Fly":
                return new Fly();
            case "Leg Press":
                return new LegPress();
            case "Mountain Climber":
                return new MountainClimber();
            case "Natação":
                return new Natacao();
            case "Prancha":
                return new Prancha();
            case "Remada":
                return new Remada();
            case "Trail":
                return new Trail();
            default:
                return null;
        }
    }

    public Utilizador CriarUtilizador(String morada, String email, String password, String username, Genero genero, double altura, double peso, LocalDate data_nascimento, String desporto_favorito, String tipo_atleta) {
        if (tipo_atleta.compareTo("profissional") == 0) {
            return new Utilizadorpro(morada, email, password, username, genero, altura, peso, data_nascimento, desporto_favorito, tipo_atleta);
        } else if (tipo_atleta.compareTo("amador") == 0) {
            return new Utilizadorama(morada, email, password, username, genero, altura, peso, data_nascimento, desporto_favorito, tipo_atleta);
        } else if (tipo_atleta.compareTo("praticante ocasional") == 0){
            return new Utilizadorpratoc(morada, email, password, username, genero, altura, peso, data_nascimento, desporto_favorito, tipo_atleta);
        } else {
            System.out.println("Os únicos tipos de atleta disponíveis são 'profissional', 'amador' ou 'praticante ocasional'");
            return null;
        }
    }

    public void addUtilizador(String username, Utilizador utilizador) {
        utilizadores.put(username, utilizador);
    }

    public Utilizador getUtilizador(String username) {
        return utilizadores.get(username);
    }

    public List<String> getAllUsernames() {
        return new ArrayList<>(utilizadores.keySet());
    }

    public void removerUtilizador(String username) {
        utilizadores.remove(username);
    }

    public List<Atividade> getAllActivitiesOfUser(String username) {
        Utilizador utilizador = utilizadores.get(username);
        if (utilizador != null) {
            return new ArrayList<>(utilizador.getAtividades().values());
        } else {
            return new ArrayList<>();
        }
    }

    public Atividade getAtividades(String username, int index) {
        Utilizador utilizador = utilizadores.get(username);
        if (utilizador != null) {
            Map<String, Atividade> atividadesMap = utilizador.getAtividades();
            List<Atividade> atividades = new ArrayList<>(atividadesMap.values());
            if (index >= 0 && index < atividades.size()) {
                return atividades.get(index);
            }
        }
        return null;
    }

    public List<Atividade> ListarTodasasAtividadesdeumUser() {
        List<Atividade> allActivities = new ArrayList<>();
        for (Utilizador utilizador : utilizadores.values()) {
            Map<String, Atividade> atividadesMap = utilizador.getAtividades();
            allActivities.addAll(atividadesMap.values());
        }
        return allActivities;
    }

    // Method to retrieve all usernames and their corresponding number of activities
    public Map<String, Integer> getUserActivityCounts() {
        Map<String, Integer> userActivityCounts = new HashMap<>();
        for (Map.Entry<String, Utilizador> entry : utilizadores.entrySet()) {
            String username = entry.getKey();
            int activityCount = entry.getValue().getAtividades().size();
            userActivityCounts.put(username, activityCount);
        }
        return userActivityCounts;
    }

    public PlanoTreino criarPlanodeTreino(Utilizador utilizador, int recorrencia, List<Atividade> atividadesDisponiveis, Dificuldade dificuldade) {
        LocalDate dataInicioSemana = LocalDate.now().with(DayOfWeek.MONDAY);
        PlanoTreino planoTreino = new PlanoTreino();

        Map<LocalDate, Integer> atividadesPorDia = new HashMap<>();

        List<LocalDate> diasDisponiveis = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            diasDisponiveis.add(dataInicioSemana.plusDays(i));
            atividadesPorDia.put(dataInicioSemana.plusDays(i), 0);
        }

        for (Atividade atividade : atividadesDisponiveis) {
            if (atividade.getDificuldade().equals(dificuldade)) {
                LocalDate diaDisponivel = encontrarDiaDisponivel(atividadesPorDia, diasDisponiveis, dificuldade);
                
                if (diaDisponivel != null) {
                    atividade.setData(diaDisponivel);
                    utilizador.addAtividade(atividade.clone());
                    atividadesPorDia.put(diaDisponivel, atividadesPorDia.get(diaDisponivel) + 1);
                    planoTreino.adicionarAtividade(atividade.clone());
                }
            }
        }
        return planoTreino;
    }

    public PlanoTreino criarPlanodeTreinoPorTipo(Utilizador utilizador, int recorrencia, List<Atividade> atividadesDisponiveis, Class<? extends Atividade> tipoAtividade) {
        LocalDate dataInicioSemana = LocalDate.now().with(DayOfWeek.MONDAY);
        PlanoTreino planoTreino = new PlanoTreino();

        Map<LocalDate, Integer> atividadesPorDia = new HashMap<>();

        List<LocalDate> diasDisponiveis = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            diasDisponiveis.add(dataInicioSemana.plusDays(i));
            atividadesPorDia.put(dataInicioSemana.plusDays(i), 0);
        }

        for (Atividade atividade : atividadesDisponiveis) {
            if (tipoAtividade.isInstance(atividade)) {
                Dificuldade dificuldade = atividade.getDificuldade();
                LocalDate diaDisponivel = encontrarDiaDisponivel(atividadesPorDia, diasDisponiveis, dificuldade);

                if (diaDisponivel != null) {
                    atividade.setData(diaDisponivel);
                    utilizador.addAtividade(atividade.clone());
                    atividadesPorDia.put(diaDisponivel, atividadesPorDia.get(diaDisponivel) + 1);
                    planoTreino.adicionarAtividade(atividade.clone());
                }
            }
        }
        return planoTreino;
    }

    private LocalDate encontrarDiaDisponivel(Map<LocalDate, Integer> atividadesPorDia, List<LocalDate> diasDisponiveis, Dificuldade dificuldade) {
        Collections.shuffle(diasDisponiveis);

        // Iterar sobre os dias disponíveis para encontrar o primeiro dia com espaço para mais atividades
        for (LocalDate dia : diasDisponiveis) {
            int atividadesRealizadas = atividadesPorDia.get(dia);
            int limiteDiario = calcularLimiteDiario(dificuldade);

            if (atividadesRealizadas < limiteDiario) {
                return dia;
            }
        }
        return null;
    }

    private int calcularLimiteDiario(Dificuldade dificuldade) {
        switch (dificuldade) {
            case DIFICIL:
                return 1;
            case MEDIO:
                return 2;
            case FACIL:
                return 2;
            default:
                return 1;
        }
    }

    private double realizarAtividade(Utilizador utilizador, Atividade atividade) {
        utilizador.addAtividade(atividade);
        double caloriasGastas = atividade.calorias(utilizador);
        
        return caloriasGastas;
    }

    public Utilizador encontrarUtilizadorMaisCaloriconumIntervalo(LocalDate inicioPeriodo, LocalDate fimPeriodo) {
        Utilizador utilizadorMaisCalorico = null;
        double maxCalorias = Double.MIN_VALUE;

        for (Utilizador utilizador : utilizadores.values()) {
            double totalCalorias = utilizador.calcularTotalCalorias(inicioPeriodo, fimPeriodo);
            if (totalCalorias > maxCalorias) {
                maxCalorias = totalCalorias;
                utilizadorMaisCalorico = utilizador;
            }
        }

        return utilizadorMaisCalorico;
    }

    public Utilizador encontrarUtilizadorMaisCaloricoDesdeSempre() {
        return encontrarUtilizadorMaisCaloriconumIntervalo(LocalDate.MIN, LocalDate.now());
    }

    public Utilizador encontrarUtilizadorMaisAtivo(LocalDate inicioPeriodo, LocalDate fimPeriodo) {
        Utilizador utilizadorMaisAtivo = null;
        int maxAtividades = Integer.MIN_VALUE;

        for (Utilizador utilizador : utilizadores.values()) {
            int numAtividades = 0;
            for (Atividade atividade : utilizador.getAtividades().values()) {
                LocalDate dataAtividade = atividade.getData();
                if (!dataAtividade.isBefore(inicioPeriodo) && !dataAtividade.isAfter(fimPeriodo)) {
                    numAtividades++;
                }
            }
            if (numAtividades > maxAtividades) {
                maxAtividades = numAtividades;
                utilizadorMaisAtivo = utilizador;
            }
        }

        return utilizadorMaisAtivo;
    }

    public Utilizador encontrarUtilizadorMaisAtivoDesdeSempre() {
        return encontrarUtilizadorMaisAtivo(LocalDate.MIN, LocalDate.now());
    }

    public String encontrarTipoAtividadeMaisRealizada() {
        Map<String, Integer> contagemTiposAtividade = new HashMap<>();

        for (Utilizador utilizador : utilizadores.values()) {
            for (Atividade atividade : utilizador.getAtividades().values()) {
                String codigoAtividade = atividade.getCodigo();
                contagemTiposAtividade.put(codigoAtividade, contagemTiposAtividade.getOrDefault(codigoAtividade, 0) + 1);
            }
        }

        String codigoMaisRealizado = null;
        int maxOcorrencias = 0;
        for (Map.Entry<String, Integer> entry : contagemTiposAtividade.entrySet()) {
            if (entry.getValue() > maxOcorrencias) {
                maxOcorrencias = entry.getValue();
                codigoMaisRealizado = entry.getKey();
            }
        }

        return codigoMaisRealizado;
    }

    public void adicionarUtilizador(String id, Utilizador utilizador) {
        utilizadores.put(id, utilizador);
    }

    public double calcularDistanciaTotalUtilizador(LocalDate inicioPeriodo, LocalDate fimPeriodo, Utilizador utilizador) {
        double distanciaTotal = 0.0;

        for (Atividade atividade : utilizador.getAtividades().values()) {
            LocalDate dataAtividade = atividade.getData();
            if (!dataAtividade.isBefore(inicioPeriodo) && !dataAtividade.isAfter(fimPeriodo)) {
                if (atividade instanceof DistanciaeAltimetria) {
                    distanciaTotal += ((DistanciaeAltimetria) atividade).getdistancia();
                } else if (atividade instanceof Distancia) {
                    distanciaTotal += ((Distancia) atividade).getdistancia();
                }
            }
        }

        return distanciaTotal;
    }

    public double calcularDistanciaTotalUtilizadorDesdeSempre(Utilizador utilizador) {
        return calcularDistanciaTotalUtilizador(LocalDate.MIN, LocalDate.now(), utilizador);
    }
    
    public int calcularAltimetriaTotalUtilizador(LocalDate inicioPeriodo, LocalDate fimPeriodo, Utilizador utilizador) {
        int altimetriaTotal = 0;

        for (Atividade atividade : utilizador.getAtividades().values()) {
            if (atividade instanceof DistanciaeAltimetria) {
                LocalDate dataAtividade = atividade.getData();
                if (!dataAtividade.isBefore(inicioPeriodo) && !dataAtividade.isAfter(fimPeriodo)) {
                    altimetriaTotal += ((DistanciaeAltimetria) atividade).getaltimetria();
                }
            }
        }
        return altimetriaTotal;
    }

    public int calcularAltimetriaTotalUtilizadorDesdeSempre(Utilizador utilizador) {
        return calcularAltimetriaTotalUtilizador(LocalDate.MIN, LocalDate.now(), utilizador);
    }

    public static PlanoTreino encontrarPlanoMaisExigente(List<Utilizador> utilizadores) {
        PlanoTreino planoMaisExigente = null;
        double maxCalorias = Double.MIN_VALUE;
        
        for (Utilizador utilizador : utilizadores) {
            for (PlanoTreino plano : utilizador.getPlanosTreino()) {
                double caloriasGastas = plano.calcularTotalCaloriasPT(plano.getAtividades(), utilizador);
                if (caloriasGastas > maxCalorias) {
                    maxCalorias = caloriasGastas;
                    planoMaisExigente = plano;
                }
            }
        }
        
        return planoMaisExigente;
    }

    public List<Atividade> listarAtividadesUtilizador(Utilizador utilizador) {
        return new ArrayList<>(utilizador.getAtividades().values());
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
        Utilizador utilizador = utilizadores.get(user);
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
            utilizadores.values().forEach(
                u -> getAllActivitiesOfUser(u.getusername()).forEach(
                    a -> pw.println(a.toString())
                )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void avancarTempo(int dias) {
        LocalDate novaData = LocalDate.now().plusDays(dias);
        // Atualizar todas as atividades agendadas, estatísticas, etc. de acordo com a nova data
    }
}
//todos os métodos importantes: setuser, adduser, getallatividades... CriarUtilizador, quantosutilizadores