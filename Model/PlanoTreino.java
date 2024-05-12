package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List; 

public class PlanoTreino implements Serializable{
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private List<Atividade> atividades;
    private Utilizador user;

    public PlanoTreino() {
        this.dataInicio = null;
        this.dataFim = null;
        this.atividades = new ArrayList<>();
        this.user = getUser();
    }

    public PlanoTreino(LocalDate dataInicio, LocalDate dataFim, List<Atividade> atividades) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.atividades = atividades;
    }

    public PlanoTreino(List<Atividade> atividades) {
        this.dataInicio = calcularDataPrimeiraAtividade(atividades);
        this.dataFim = calcularDataUltimaAtividade(atividades);
        this.atividades = atividades;
    }

    public PlanoTreino(PlanoTreino outro) {
        this.dataInicio = outro.getDataInicio();
        this.dataFim = outro.getDataFim();
        this.atividades = outro.getAtividades();
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Utilizador getUser() {
        return user;
    }

    public void setUser(Utilizador user) {
        this.user = user;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void adicionarAtividade(Atividade atividade) {
        atividades.add(atividade);
    }

    public LocalDate calcularDataPrimeiraAtividade(List<Atividade> atividades) {
        if (atividades.isEmpty()) {
            return null;
        }
    
        LocalDate menorData = atividades.get(0).getData();
    
        for (Atividade atividade : atividades) {
            LocalDate dataAtividade = atividade.getData();
            if (dataAtividade.isBefore(menorData)) {
                menorData = dataAtividade;
            }
        }
    
        return menorData;
    }

    public LocalDate calcularDataUltimaAtividade(List<Atividade> atividades) {
        if (atividades.isEmpty()) {
            return null;
        }

        LocalDate maiorData = atividades.get(0).getData();

        for (Atividade atividade : atividades) {
            LocalDate dataAtividade = atividade.getData();
            if (dataAtividade.isAfter(maiorData)) {
                maiorData = dataAtividade;
            }
        }

        return maiorData;
    }

    public double calcularTotalCaloriasPT(List<Atividade> atividades, Utilizador utilizador) {
        double totalCalorias = 0.0;
        for (Atividade atividade : atividades) {
            totalCalorias += atividade.calorias(utilizador);
        }
        return totalCalorias;
    }

    public PlanoTreino clone() {
        return new PlanoTreino(this);
    }
}