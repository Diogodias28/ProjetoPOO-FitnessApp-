package Model;
import java.time.LocalDate;


public abstract class Atividade {
    private String codigo;
    private String descricao;
    private LocalDate data;
    private int duracao;
    private Utilizador user;
    private Dificuldade dificuldade;

    public Atividade() {
        this.codigo = "";
        this.descricao = "";
        this.data = LocalDate.ofEpochDay(0);
        this.duracao = 0;
        this.user = new Utilizador();
        this.dificuldade= Dificuldade.FACIL;
    }

    public Atividade(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.data = data;
        this.duracao = duracao;
        this.dificuldade= dificuldade;
    }

    public Atividade(Atividade outro){
        this.codigo = outro.getCodigo();
        this.descricao = outro.getDescricao();
        this.data = outro.getData();
        this.duracao = outro.getDuracao();
        this.dificuldade= outro.getDificuldade();
    }
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public Dificuldade getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(Dificuldade dificuldade) {
        this.dificuldade = dificuldade;
    }

    @Override
    public String toString() {
        return "Atividades{" +
                "codigo='" + codigo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                ", duracao=" + duracao +
                ", dificuldade='" + dificuldade + '\'' +
                '}';
    }

    public Utilizador getUser() {
        return user;
    }

    public void setUser(Utilizador user) {
        this.user = user;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atividade Atividades = (Atividade) o;
        return this.duracao == Atividades.getDuracao()
                && this.codigo.equals(Atividades.getCodigo())
                && this.descricao.equals(Atividades.getDescricao())
                && this.data.equals(Atividades.getData())
                && this.dificuldade.equals(Atividades.getDificuldade());
    }


    public abstract double calorias(Utilizador utilizador);
    public  abstract  Atividade clone();
}