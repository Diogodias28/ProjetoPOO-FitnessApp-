package User;
import java.util.ArrayList;
import Model.Atividade;

class PlanoTreino {
    private ArrayList<Atividade> atividades;
    private String descricao;
    private int duracaoTotal;

    public PlanoTreino(String descricao) {
        this.descricao = descricao;
        this.atividades = new ArrayList<>();
        this.duracaoTotal = 0;
    }

    public void adicionarAtividade(Atividade atividade) {
        this.atividades.add(atividade);
        this.duracaoTotal += atividade.getDuracao();
    }

    public void removerAtividade(Atividade atividade) {
        this.atividades.remove(atividade);
        this.duracaoTotal -= atividade.getDuracao();
    }

    public String getDescricao() {
        return descricao;
    }

    public int getDuracaoTotal() {
        return duracaoTotal;
    }

    public ArrayList<Atividade> getAtividades() {
        return atividades;
    }
}