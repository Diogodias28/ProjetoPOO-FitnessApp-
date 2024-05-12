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
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fitness implements Serializable{ //este é o nosso model
    private Map <String, Utilizador> utilizadores;
    private List <Atividade> atividades;
    private Map<String, Atividade> mapaAtividades;
    private Map <String, Comparator<Utilizador>> comparadores;

    public Fitness() {
        utilizadores = new HashMap<>();
        atividades = atividadesDisponiveis();
        mapaAtividades = criarMapaAtividades(atividades);
        comparadores = new HashMap<>();
    }

    public int quantosUtilizadores(){
        return utilizadores.size();
    }

    public int quantosPlanos(Utilizador utilizador){
        return utilizador.getPlanosTreino().size();
    }

    public int quantasAtividades(Utilizador utilizador){
        return utilizador.getAtividades().size();
    }

    public boolean ExisteUtilizador(String username) {
        if (!utilizadores.containsKey(username)) {
            return false;
        } else {
            System.out.println("Este utilizador já existe.");
            return true;
        }
    }

    public Map<String, Atividade> criarMapaAtividades(List<Atividade> atividades) {
        Map<String, Atividade> mapaAtividades = new HashMap<>();
        for (Atividade atividade : atividades) {
            mapaAtividades.put(atividade.getDescricao(), atividade);
        }
        return mapaAtividades;
    }

    public List<Atividade> atividadesDisponiveis() {
        List<Atividade> atividades = new ArrayList<>();
        atividades.add(new Abdominal());
        atividades.add(new AgachamentoComPeso());
        atividades.add(new Agachamentos());
        atividades.add(new BTT());
        atividades.add(new Burpees());
        atividades.add(new Caminhada());
        atividades.add(new Canoagem());
        atividades.add(new Ciclismo());
        atividades.add(new Corrida());
        atividades.add(new CurlBicep());
        atividades.add(new ElevacoesLaterais());
        atividades.add(new Flexao());
        atividades.add(new Fly());
        atividades.add(new LegPress());
        atividades.add(new MountainClimber());
        atividades.add(new Natacao());
        atividades.add(new Prancha());
        atividades.add(new Remada());
        atividades.add(new Trail());
        return atividades;
    }

    public boolean ExisteUtilizador2(String username, String password) {
        if (!utilizadores.containsKey(username)) {
            System.out.println("O utilizador não existe.");
            return false;
        }
    
        String passwordArmazenada = utilizadores.get(username).getPassword();
    
        if (!password.equals(passwordArmazenada)) {
            System.out.println("A senha está incorreta.");
            return false;
        }
    
        return true;
    }

    public boolean ExisteAtividade(String descricao){
        if (!mapaAtividades.containsKey(descricao)) {
            return false;
        } else {
            return true;
        }
    }

    public Atividade getAtividade(String descricao) {
        Atividade atividade = mapaAtividades.get(descricao);
        return atividade;
    }

    public Utilizador CriarUtilizador(String morada, String email, String password, String username, Genero genero, double altura, double peso, LocalDate data_nascimento, String desporto_favorito, String tipo_atleta) {
        Utilizador novoUtilizador = null;
    
        if (tipo_atleta.compareTo("profissional") == 0) {
            novoUtilizador = new Utilizadorpro(morada, email, password, username, genero, altura, peso, data_nascimento, desporto_favorito, tipo_atleta);
        } else if (tipo_atleta.compareTo("amador") == 0) {
            novoUtilizador = new Utilizadorama(morada, email, password, username, genero, altura, peso, data_nascimento, desporto_favorito, tipo_atleta);
        } else if (tipo_atleta.compareTo("praticante ocasional") == 0) {
            novoUtilizador = new Utilizadorpratoc(morada, email, password, username, genero, altura, peso, data_nascimento, desporto_favorito, tipo_atleta);
        } else {
            System.out.println("Os únicos tipos de atleta disponíveis são 'profissional', 'amador' ou 'praticante ocasional'");
        }
    
        if (novoUtilizador != null) {
            addUtilizador(username, novoUtilizador);
        }
    
        return novoUtilizador;
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

    public List<Atividade> getAllAtividadesdeumUtilizador(String username) {
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

    public Map<String, Integer> getUserNumerodeAtividades() {
        Map<String, Integer> userActivityCounts = new HashMap<>();
        for (Map.Entry<String, Utilizador> entry : utilizadores.entrySet()) {
            String username = entry.getKey();
            int activityCount = entry.getValue().getAtividades().size();
            userActivityCounts.put(username, activityCount);
        }
        return userActivityCounts;
    }

    public PlanoTreino criarPlanodeTreino(Utilizador utilizador, int recorrencia, Dificuldade dificuldade) {
        LocalDate hoje = LocalDate.now();
        LocalDate proximaSegunda = hoje.plusWeeks(1).with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        PlanoTreino planoTreino = new PlanoTreino();
    
        Map<LocalDate, Integer> atividadesPorDia = new HashMap<>();
        List<LocalDate> diasDisponiveis = new ArrayList<>();
        inicializarDiasDisponiveis(proximaSegunda, atividadesPorDia, diasDisponiveis);
    
        for (Atividade atividade : atividades) {
            try {
                if (atividade.getDificuldade().equals(dificuldade)) {
                    LocalDate diaDisponivel = encontrarDiaDisponivel(atividadesPorDia, diasDisponiveis, atividade.getDificuldade(), proximaSegunda);
    
                    if (diaDisponivel != null) {
                        realizarAtividade(utilizador, atividadesPorDia, planoTreino, atividade, diaDisponivel, proximaSegunda);
                    }
                }
            } catch (Exception e) {
                System.out.println("Erro ao processar atividade: " + e.getMessage());
            }
        }
    
        LocalDate dataInicio = planoTreino.calcularDataPrimeiraAtividade(planoTreino.getAtividades());
        LocalDate dataFim = planoTreino.calcularDataUltimaAtividade(planoTreino.getAtividades());
        planoTreino.setDataInicio(dataInicio);
        planoTreino.setDataFim(dataFim);
    
        utilizador.adicionarPlanoTreino(planoTreino);
    
        System.out.println("Plano de treino criado com sucesso!");
    
        return planoTreino;
    }    

    public PlanoTreino criarPlanodeTreinoPorTipo(Utilizador utilizador, int recorrencia, String tipoAtividade) {
        LocalDate hoje = LocalDate.now();
        LocalDate proximaSegunda = hoje.plusWeeks(1).with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        PlanoTreino planoTreino = new PlanoTreino();
        
        Map<LocalDate, Integer> atividadesPorDia = new HashMap<>();
        List<LocalDate> diasDisponiveis = new ArrayList<>();
        inicializarDiasDisponiveis(proximaSegunda, atividadesPorDia, diasDisponiveis);
        
        Class<? extends Atividade> classeAtividade = mapearClasseAtividade(tipoAtividade);
        if (classeAtividade == null) {
            System.out.println("Tipo de atividade inválido: " + tipoAtividade);
            return null;
        }
    
        for (Atividade atividade : atividades) {
            try {
                if (classeAtividade.isInstance(atividade)) {
                    LocalDate diaDisponivel = encontrarDiaDisponivel(atividadesPorDia, diasDisponiveis, atividade.getDificuldade(), proximaSegunda);
            
                    if (diaDisponivel != null) {
                        realizarAtividade(utilizador, atividadesPorDia, planoTreino, atividade, diaDisponivel, proximaSegunda);
                    }
                }
            } catch (Exception e) {
                System.out.println("Erro ao processar atividade: " + e.getMessage());
            }
        }
        
        LocalDate dataInicio = planoTreino.calcularDataPrimeiraAtividade(planoTreino.getAtividades());
        LocalDate dataFim = planoTreino.calcularDataUltimaAtividade(planoTreino.getAtividades());
        planoTreino.setDataInicio(dataInicio);
        planoTreino.setDataFim(dataFim);
        
        utilizador.adicionarPlanoTreino(planoTreino);
        
        System.out.println("Plano de treino criado com sucesso!");
    
        return planoTreino;
    }
    
    private LocalDate encontrarDiaDisponivel(Map<LocalDate, Integer> atividadesPorDia, List<LocalDate> diasDisponiveis, Dificuldade dificuldade, LocalDate proximaSegunda) {
        Collections.shuffle(diasDisponiveis);
        
        for (LocalDate dia : diasDisponiveis) {
            if (dia.isEqual(proximaSegunda) || dia.isAfter(proximaSegunda)) {
                int atividadesRealizadas = atividadesPorDia.getOrDefault(dia, 0);
                int limiteDiario = calcularLimiteDiario(dificuldade);
        
                if (atividadesRealizadas < limiteDiario) {
                    return dia;
                }
            }
        }
        return null;
    }
    
    private void inicializarDiasDisponiveis(LocalDate dataInicioSemana, Map<LocalDate, Integer> atividadesPorDia, List<LocalDate> diasDisponiveis) {
        for (int i = 0; i < 7; i++) {
            LocalDate dia = dataInicioSemana.plusDays(i);
            diasDisponiveis.add(dia);
            atividadesPorDia.put(dia, 0);
        }
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
    
    private Class<? extends Atividade> mapearClasseAtividade(String tipoAtividade) {
        switch (tipoAtividade) {
            case "Distancia":
                return Distancia.class;
            case "Distancia e altimetria":
                return DistanciaeAltimetria.class;
            case "Repeticoes":
                return Reps.class;
            case "Repeticoes com pesos":
                return RepsPesos.class;
            default:
                return null;
        }
    }
    
    private void realizarAtividade(Utilizador utilizador, Map<LocalDate, Integer> atividadesPorDia, PlanoTreino planoTreino, Atividade atividade, LocalDate diaDisponivel, LocalDate proximaSegunda) {
        if (diaDisponivel != null && (diaDisponivel.isEqual(proximaSegunda) || diaDisponivel.isAfter(proximaSegunda))) {
            atividade.setData(diaDisponivel);
        
            utilizador.addAtividade(atividade.clone());
        
            atividadesPorDia.put(diaDisponivel, atividadesPorDia.getOrDefault(diaDisponivel, 0) + 1);
        
            planoTreino.adicionarAtividade(atividade.clone());
        }
    }
    

    public double realizarAtividade(Utilizador utilizador, Atividade atividade) {
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

    public PlanoTreino encontrarPlanoMaisExigente() {
        PlanoTreino planoMaisExigente = null;
        double maxCalorias = Double.MIN_VALUE;
        
        for (Utilizador utilizador : utilizadores.values()) {
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
    
    public void grava(String Filename) throws IOException{
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(Filename));
        os.writeObject(this);
        os.close();
    }

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
                u -> getAllAtividadesdeumUtilizador(u.getusername()).forEach(
                    a -> pw.println(a.toString())
                )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void avancarAteDataEspecifica(int dias, Utilizador utilizador) {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataEspecifica = dataAtual.plusDays(dias);
    
        while (!dataAtual.isAfter(dataEspecifica)) {
            realizarAtividades(dataAtual, utilizador);
            dataAtual = dataAtual.plusDays(1);
        }
    }
    
    private void realizarAtividades(LocalDate data, Utilizador utilizador) {
        System.out.println("Realizando atividades para " + data);
        
        List<PlanoTreino> planosTreino = utilizador.getPlanosTreino();
        
        for (PlanoTreino plano : planosTreino) {
            List<Atividade> atividades = plano.getAtividades();
            
            for (Atividade atividade : atividades) {
                if (atividade.getData().isEqual(data)) {
                    System.out.println("Realizando atividade: " + atividade);
                    double caloriasGastas = realizarAtividade(utilizador, atividade);
                    System.out.println("Calorias gastas: " + caloriasGastas);
                }
            }
        }
    }
}