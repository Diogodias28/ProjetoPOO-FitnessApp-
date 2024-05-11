package Model;
import java.time.LocalDate;
import java.util.List;

public class PlanoTreino {
    private LocalDate data;
    private List<Atividade> atividades;
    private int iteracoes;

    public PlanoTreino(LocalDate data, List<Atividade> atividades, int iteracoes) {
        this.data = data;
        this.atividades = atividades;
        this.iteracoes = iteracoes;
    }

    public PlanoTreino(LocalDate data, int iteracoes) {
        this.data = data;
        this.atividades = null;
        this.iteracoes = iteracoes;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    public int getIteracoes() {
        return iteracoes;
    }

    public void setIteracoes(int iteracoes) {
        this.iteracoes = iteracoes;
    }
}