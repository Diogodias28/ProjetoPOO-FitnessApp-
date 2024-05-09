package Model;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Canoagem extends Distancia{
    private double direcao;
    private double velvento;
    private double nvoltas;
    private String embarcacao;

    public Canoagem(){
        super("","", LocalDate.EPOCH, 0, Dificuldade.DIFICIL, 0);
        this.direcao = 0;
        this.velvento = 0;
        this.nvoltas = 0;
        this.embarcacao = "";
    }

    public Canoagem(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int distancia, double direcao, double velvento, double nvoltas, String embarcacao) {
        super(codigo, descricao, data, duracao, dificuldade, distancia);
        this.direcao = direcao;
        this.velvento = velvento;
        this.nvoltas = nvoltas;
        this.embarcacao = embarcacao;
    }

    public Canoagem(Canoagem outro) {
        super(outro.getCodigo(), outro.getDescricao(), LocalDate.EPOCH, outro.getDuracao(), outro.getDificuldade(), outro.getdistancia());
        this.direcao = outro.getDirecao();
        this.velvento = outro.getVelvento();
        this.nvoltas = outro.getNvoltas();
        this.embarcacao = outro.getEmbarcacao();
    }

    public double getDirecao() {
        return direcao;
    }

    public void setDirecao(Double direcao) {
        this.direcao = direcao;
    }

    public double getVelvento() {
        return velvento;
    }

    public void setVelvento(double velvento) {
        this.velvento = velvento;
    }

    public double getNvoltas() {
        return nvoltas;
    }

    public void setNvoltas(double nvoltas) {
        this.nvoltas = nvoltas;
    }

    public String getEmbarcacao() {
        return embarcacao;
    }

    public void setEmbarcacao(String embarcacao) {
        this.embarcacao = embarcacao;
    }

    public double caloriasCanoagem() {
        long idade =  ChronoUnit.YEARS.between(LocalDate.now(),
                getUser().getData_nascimento());
        double calorias = calorias(getUser()); //não sei se usamos utilizador como parametro ou fazemos getuser
        return calorias*getDirecao()*idade/4;
    }

    @Override
    public Atividade clone() {
        return new Canoagem(this);
    }

    @Override
    public String toString() {
        return "Canoagem{" +
                super.toString()+
                "direcao=" + direcao +
                ", velvento=" + velvento +
                ", nvoltas=" + nvoltas +
                ", embarcacao='" + embarcacao + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Canoagem Canoagem = (Canoagem) o;
        return Double.compare(Canoagem.getDirecao(), getDirecao()) == 0
                && Double.compare(Canoagem.getVelvento(), getVelvento()) == 0
                && Double.compare(Canoagem.getNvoltas(), getNvoltas()) == 0
                && this.getEmbarcacao().equals(Canoagem.getEmbarcacao());
    }

}